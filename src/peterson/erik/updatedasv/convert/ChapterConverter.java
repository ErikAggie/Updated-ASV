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

        String bookName = Util.BOOK_NAME_MAP.get(sourceFileKey);
        int chapterNumber = Integer.parseInt(chapterMatcher.group(1));
        String chapterTitle =
                bookName + " " + chapterNumber;

        StringBuilder newText = new StringBuilder();
        newText.append(Util.HEAD_AND_CSS);
        newText.append("<title>").append(chapterTitle).append("</title>");
        newText.append("</head><body>");

        // Fixed top portion with chapter number
        newText.append("<ul class=\"top\">");
        newText.append("<li class=\"chapterTitle\">").append(chapterTitle).append("</li>");
        newText.append("</ul>");

        // Fixed bottom portion with previous/next links
        newText.append("<ul class=\"bottom\">");
        newText.append("<li class=\"previous\"><a href=previous>Previous</a></li>");
        newText.append("<li class=\"next\"><a href=previous>Next</a></li>");
        newText.append("</ul>");

        Document originalDoc = Jsoup.parse(sourceFile, "UTF-8", "http://example.com/");
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
                // We don't need these/will replace them
                case "main":
                case "chapterlabel":
                case "footnote":
                case "copyright":
                case "ms": // The book headings in Psalms. Might be useful someday, but not today...
                    continue;
                default:
                    System.out.println(bookName + " " + chapterNumber + ": Another kind of div class: " + classname);
                    continue;
            }

            String text = paragraph.html();
            text = text.replaceAll("\\n", "");
            newText.append("<div class=\"").append(classname).append("\" >").append(text).append("</div>");
        }
        newText.append(Util.UNMODIFIED_COPYRIGHT);
        newText.append("</body></html>");
//                    System.out.println("Now have " + newText.toString());

        File newBookDirectory = new File(new File(Util.CLEANED_UP_TEXT_DIR), bookName);
        if ( !newBookDirectory.exists()) {
            if ( !newBookDirectory.mkdir()) {
                throw new IOException("Unable to create directory " + newBookDirectory.getAbsolutePath());
            }
        }

        File newFile = new File(newBookDirectory, bookName + chapterMatcher.group(1) + ".htm");
        System.out.println(newBookDirectory.getName() + "/" + newFile.getName());
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.append(newText.toString());
        fileWriter.close();

    }

}
