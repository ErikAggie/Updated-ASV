package peterson.erik.updatedasv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* package */ class CreateHTMLFiles {
    private static final Map<String, String> FILE_NAME_MAP = new HashMap<>();

    static {
        FILE_NAME_MAP.put("GEN", "Genesis");
        FILE_NAME_MAP.put("EXO", "Exodus");
        FILE_NAME_MAP.put("LEV", "Leviticus");
        FILE_NAME_MAP.put("NUM", "Numbers");
        FILE_NAME_MAP.put("DEU", "Deuteronomy");
        FILE_NAME_MAP.put("JOS", "Joshua");
        FILE_NAME_MAP.put("JDG", "Judges");
        FILE_NAME_MAP.put("RUT", "Ruth");
        FILE_NAME_MAP.put("1SA", "1 Samuel");
        FILE_NAME_MAP.put("2SA", "2 Samuel");
        FILE_NAME_MAP.put("1KI", "1 Kings");
        FILE_NAME_MAP.put("2KI", "2 Kings");
        FILE_NAME_MAP.put("1CH", "1 Chronicles");
        FILE_NAME_MAP.put("2CH", "2 Chronicles");
        FILE_NAME_MAP.put("EZR", "Ezra");
        FILE_NAME_MAP.put("NEH", "Nehemiah");
        FILE_NAME_MAP.put("EST", "Esther");
        FILE_NAME_MAP.put("JOB", "Job");
        FILE_NAME_MAP.put("PSA", "Psalms");
        FILE_NAME_MAP.put("PRO", "Proverbs");
        FILE_NAME_MAP.put("ECC", "Ecclesiastes");
        FILE_NAME_MAP.put("SNG", "Song of Solomon");
        FILE_NAME_MAP.put("ISA", "Isaiah");
        FILE_NAME_MAP.put("JER", "Jeremiah");
        FILE_NAME_MAP.put("LAM", "Lamentations");
        FILE_NAME_MAP.put("EZK", "Ezekiel");
        FILE_NAME_MAP.put("DAN", "Daniel");
        FILE_NAME_MAP.put("HOS", "Hosea");
        FILE_NAME_MAP.put("JOL", "Joel");
        FILE_NAME_MAP.put("AMO", "Amos");
        FILE_NAME_MAP.put("OBA", "Obadiah");
        FILE_NAME_MAP.put("JON", "Jonah");
        FILE_NAME_MAP.put("MIC", "Micah");
        FILE_NAME_MAP.put("NAM", "Nahum");
        FILE_NAME_MAP.put("HAB", "Habakkuk");
        FILE_NAME_MAP.put("ZEP", "Zephaniah");
        FILE_NAME_MAP.put("HAG", "Haggai");
        FILE_NAME_MAP.put("ZEC", "Zechariah");
        FILE_NAME_MAP.put("MAL", "Malachi");
        FILE_NAME_MAP.put("MAT", "Matthew");
        FILE_NAME_MAP.put("MRK", "Mark");
        FILE_NAME_MAP.put("LUK", "Luke");
        FILE_NAME_MAP.put("JHN", "John");
        FILE_NAME_MAP.put("ACT", "Acts");
        FILE_NAME_MAP.put("ROM", "Romans");
        FILE_NAME_MAP.put("1CO", "1 Corinthians");
        FILE_NAME_MAP.put("2CO", "2 Corinthians");
        FILE_NAME_MAP.put("GAL", "Galatians");
        FILE_NAME_MAP.put("EPH", "Ephesians");
        FILE_NAME_MAP.put("PHP", "Philippians");
        FILE_NAME_MAP.put("COL", "Colossians");
        FILE_NAME_MAP.put("1TH", "1 Thessalonians");
        FILE_NAME_MAP.put("2TH", "2 Thessalonians");
        FILE_NAME_MAP.put("1TI", "1 Timothy");
        FILE_NAME_MAP.put("2TI", "2 Timothy");
        FILE_NAME_MAP.put("TIT", "Titus");
        FILE_NAME_MAP.put("PHM", "Philemon");
        FILE_NAME_MAP.put("HEB", "Hebrews");
        FILE_NAME_MAP.put("JAS", "James");
        FILE_NAME_MAP.put("1PE", "1 Peter");
        FILE_NAME_MAP.put("2PE", "2 Peter");
        FILE_NAME_MAP.put("1JN", "1 John");
        FILE_NAME_MAP.put("2JN", "2 John");
        FILE_NAME_MAP.put("3JN", "3 John");
        FILE_NAME_MAP.put("JUD", "Jude");
        FILE_NAME_MAP.put("REV", "Revelation");
    }

    // This matches only chapter files, e.g. GEN01.htm or 1CH01.htm
    private static final Pattern CHAPTER_PATTERN = Pattern.compile("^[123]?[A-Z]+(\\d+)\\.htm$");


    /* package */ static void createFiles() throws IOException {
        Set<String> originalFileKeys = FILE_NAME_MAP.keySet();
        File[] files = new File("originalText").listFiles();
        for ( File file : files) {
            String originalFilename = file.getName();
            Matcher chapterMatcher = CHAPTER_PATTERN.matcher(originalFilename);
            if ( !chapterMatcher.matches()) {
                // We don't care about book-level files, etc.
                continue;
            }
            for ( String key : originalFileKeys) {
                if ( originalFilename.startsWith(key)) {
                    System.out.println("Found " + FILE_NAME_MAP.get(key) + " " + chapterMatcher.group(1));
                    Document originalDoc = Jsoup.parse(file, "UTF-8", "http://example.com/");
                    StringBuilder newText = new StringBuilder();
                    newText.append("<html><body>");
                    Elements paragraphElements = originalDoc.getElementsByClass("p");
                    for ( Element paragraph : paragraphElements) {
                        String text = paragraph.html();
                        text = text.replaceAll("\\n", "");
                        newText.append("<p>").append(text).append("<\\p>");
                    }
                    newText.append("<\\body><\\html>");
//                    System.out.println("Now have " + newText.toString());

                    File newFile = new File("updateText/" + FILE_NAME_MAP.get(key) + " " + chapterMatcher.group(1));


                    break;
                }
            }
        }
    }
}
