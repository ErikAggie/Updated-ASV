package peterson.erik.updatedasv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* package */ class CreateHTMLFiles {

    // Converts old file names (abbreviation) to book names
    private static final Map<String, String> BOOK_NAME_MAP = new HashMap<>();

    static {
        BOOK_NAME_MAP.put("GEN", "Genesis");
        BOOK_NAME_MAP.put("EXO", "Exodus");
        BOOK_NAME_MAP.put("LEV", "Leviticus");
        BOOK_NAME_MAP.put("NUM", "Numbers");
        BOOK_NAME_MAP.put("DEU", "Deuteronomy");
        BOOK_NAME_MAP.put("JOS", "Joshua");
        BOOK_NAME_MAP.put("JDG", "Judges");
        BOOK_NAME_MAP.put("RUT", "Ruth");
        BOOK_NAME_MAP.put("1SA", "1 Samuel");
        BOOK_NAME_MAP.put("2SA", "2 Samuel");
        BOOK_NAME_MAP.put("1KI", "1 Kings");
        BOOK_NAME_MAP.put("2KI", "2 Kings");
        BOOK_NAME_MAP.put("1CH", "1 Chronicles");
        BOOK_NAME_MAP.put("2CH", "2 Chronicles");
        BOOK_NAME_MAP.put("EZR", "Ezra");
        BOOK_NAME_MAP.put("NEH", "Nehemiah");
        BOOK_NAME_MAP.put("EST", "Esther");
        BOOK_NAME_MAP.put("JOB", "Job");
        BOOK_NAME_MAP.put("PSA", "Psalms");
        BOOK_NAME_MAP.put("PRO", "Proverbs");
        BOOK_NAME_MAP.put("ECC", "Ecclesiastes");
        BOOK_NAME_MAP.put("SNG", "Song of Solomon");
        BOOK_NAME_MAP.put("ISA", "Isaiah");
        BOOK_NAME_MAP.put("JER", "Jeremiah");
        BOOK_NAME_MAP.put("LAM", "Lamentations");
        BOOK_NAME_MAP.put("EZK", "Ezekiel");
        BOOK_NAME_MAP.put("DAN", "Daniel");
        BOOK_NAME_MAP.put("HOS", "Hosea");
        BOOK_NAME_MAP.put("JOL", "Joel");
        BOOK_NAME_MAP.put("AMO", "Amos");
        BOOK_NAME_MAP.put("OBA", "Obadiah");
        BOOK_NAME_MAP.put("JON", "Jonah");
        BOOK_NAME_MAP.put("MIC", "Micah");
        BOOK_NAME_MAP.put("NAM", "Nahum");
        BOOK_NAME_MAP.put("HAB", "Habakkuk");
        BOOK_NAME_MAP.put("ZEP", "Zephaniah");
        BOOK_NAME_MAP.put("HAG", "Haggai");
        BOOK_NAME_MAP.put("ZEC", "Zechariah");
        BOOK_NAME_MAP.put("MAL", "Malachi");
        BOOK_NAME_MAP.put("MAT", "Matthew");
        BOOK_NAME_MAP.put("MRK", "Mark");
        BOOK_NAME_MAP.put("LUK", "Luke");
        BOOK_NAME_MAP.put("JHN", "John");
        BOOK_NAME_MAP.put("ACT", "Acts");
        BOOK_NAME_MAP.put("ROM", "Romans");
        BOOK_NAME_MAP.put("1CO", "1 Corinthians");
        BOOK_NAME_MAP.put("2CO", "2 Corinthians");
        BOOK_NAME_MAP.put("GAL", "Galatians");
        BOOK_NAME_MAP.put("EPH", "Ephesians");
        BOOK_NAME_MAP.put("PHP", "Philippians");
        BOOK_NAME_MAP.put("COL", "Colossians");
        BOOK_NAME_MAP.put("1TH", "1 Thessalonians");
        BOOK_NAME_MAP.put("2TH", "2 Thessalonians");
        BOOK_NAME_MAP.put("1TI", "1 Timothy");
        BOOK_NAME_MAP.put("2TI", "2 Timothy");
        BOOK_NAME_MAP.put("TIT", "Titus");
        BOOK_NAME_MAP.put("PHM", "Philemon");
        BOOK_NAME_MAP.put("HEB", "Hebrews");
        BOOK_NAME_MAP.put("JAS", "James");
        BOOK_NAME_MAP.put("1PE", "1 Peter");
        BOOK_NAME_MAP.put("2PE", "2 Peter");
        BOOK_NAME_MAP.put("1JN", "1 John");
        BOOK_NAME_MAP.put("2JN", "2 John");
        BOOK_NAME_MAP.put("3JN", "3 John");
        BOOK_NAME_MAP.put("JUD", "Jude");
        BOOK_NAME_MAP.put("REV", "Revelation");
    }

    // This matches only chapter files, e.g. GEN01.htm or 1CH01.htm
    private static final Pattern CHAPTER_PATTERN = Pattern.compile("^[123]?[A-Z]+(\\d+)\\.htm$");


    /* package */ static void createFiles() throws IOException {

        // Make sure we have a place to go...
        File updatedFilesBaseDirectory = new File(Util.UPDATED_TEXT_DIR);
        if ( !updatedFilesBaseDirectory.exists()) {
            updatedFilesBaseDirectory.mkdir();
        }

        Set<String> originalFileKeys = BOOK_NAME_MAP.keySet();
        File[] files = new File(Util.ORIGINAL_TEXT_DIR).listFiles();
        for ( File file : files) {
            String originalFilename = file.getName();
            Matcher chapterMatcher = CHAPTER_PATTERN.matcher(originalFilename);
            if ( !chapterMatcher.matches()) {
                // We don't care about book-level files, etc.
                continue;
            }
            for ( String key : originalFileKeys) {
                if ( originalFilename.startsWith(key)) {
//                    System.out.println("Found " + BOOK_NAME_MAP.get(key) + " " + chapterMatcher.group(1));

                    Document originalDoc = Jsoup.parse(file, "UTF-8", "http://example.com/");
                    StringBuilder newText = new StringBuilder();
                    newText.append("<html><body>");
                    Elements divElements = originalDoc.getElementsByTag("div");
                    for ( Element paragraph : divElements) {
                        String classname = paragraph.className();
                        switch ( classname) {
                            // We want
                            //   'p' (paragraph),
                            //   'q' (quote),
                            //   'm' (beginning of quote?)
                            //   'mt' (beginning of book)
                            //   'b' (break)
                            //   'nb' (no break?)
                            //   'pi' (paragraph indented?)
                            //   'd' (dedication? happens at the beginning of some Psalms)
                            //   'qs' ("Selah")
                            case "p":
                            case "q":
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
                                continue;
                            default:
                                System.out.println("Another kind of div class: " + classname);
                                continue;
                        }

                        String text = paragraph.html();
                        text = text.replaceAll("\\n", "");
                        newText.append("<div class=\"" + classname + "\" >").append(text).append("</div>");
                    }
                    newText.append("</body></html>");
//                    System.out.println("Now have " + newText.toString());

                    File newBookDirectory = new File(updatedFilesBaseDirectory, BOOK_NAME_MAP.get(key));
                    if ( !newBookDirectory.exists()) {
                        newBookDirectory.mkdir();
                    }

                    File newFile = new File(newBookDirectory, BOOK_NAME_MAP.get(key) + chapterMatcher.group(1) + ".htm");
                    FileWriter fileWriter = new FileWriter(newFile);
                    fileWriter.append(newText.toString());
                    fileWriter.close();
                    break;
                }
            }
        }
    }
}
