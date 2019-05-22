package peterson.erik.updatedasv.convert;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import peterson.erik.updatedasv.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

public class ChapterConverter {

    private final File sourceFile;
    private final Matcher chapterMatcher;
    private final String sourceFileKey;

    public ChapterConverter(File sourceFile, Matcher chapterMatcher, String sourceFileKey) {
        this.sourceFile = sourceFile;
        this.chapterMatcher = chapterMatcher;
        this.sourceFileKey = sourceFileKey;
    }

    public void cleanUpFile() throws IOException {
        Document originalDoc = Jsoup.parse(sourceFile, "UTF-8", "http://example.com/");
        StringBuilder newText = new StringBuilder();
        newText.append("<html><body>");
        Elements divElements = originalDoc.getElementsByTag("div");
        for ( Element paragraph : divElements) {
            String classname = paragraph.className();
            switch ( classname) {
                // We want
                //   'p' (paragraph),
                //   'q' (quote),
                //   'q2' (Another kind of quote...)
                //   'm' (beginning of quote?)
                //   'mt' (beginning of book)
                //   'b' (break)
                //   'nb' (no break?)
                //   'pi' (paragraph indented?)
                //   'd' (dedication? happens at the beginning of some Psalms)
                //   'qs' ("Selah")
                case "p":
                case "q":
                case "q2":
                case "m":
                case "mt":
                case "b":
                case "nb":
                case "pi":
                case "d":
                case "qs":
                    break;
                // We don't need these
                case "main":
                case "chapterlabel":
                case "footnote":
                case "copyright":
                case "ms": // The book headings in Psalms. Might be useful someday, but not today...
                    continue;
                default:
                    System.out.println(Util.BOOK_NAME_MAP.get(sourceFileKey) + " " + chapterMatcher.group(1) + ": Another kind of div class: " + classname);
                    continue;
            }

            String text = paragraph.html();
            text = text.replaceAll("\\n", "");
            newText.append("<div class=\"" + classname + "\" >").append(text).append("</div>");
        }
        newText.append("</body></html>");
//                    System.out.println("Now have " + newText.toString());

        File newBookDirectory = new File(new File(Util.CLEANED_UP_TEXT_DIR), Util.BOOK_NAME_MAP.get(sourceFileKey));
        if ( !newBookDirectory.exists()) {
            newBookDirectory.mkdir();
        }

        File newFile = new File(newBookDirectory, Util.BOOK_NAME_MAP.get(sourceFileKey) + chapterMatcher.group(1) + ".htm");
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.append(newText.toString());
        fileWriter.close();

    }

}
