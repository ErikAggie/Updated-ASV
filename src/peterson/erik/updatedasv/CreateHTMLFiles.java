package peterson.erik.updatedasv;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import peterson.erik.updatedasv.convert.ChapterConverter;

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

    // This matches only chapter files, e.g. GEN01.htm or 1CH01.htm
    private static final Pattern CHAPTER_PATTERN = Pattern.compile("^[123]?[A-Z]+(\\d+)\\.htm$");


    /* package */ static void createFiles() throws IOException {

        // Make sure we have a place to go...
        File updatedFilesBaseDirectory = new File(Util.CLEANED_UP_TEXT_DIR);
        if ( !updatedFilesBaseDirectory.exists()) {
            updatedFilesBaseDirectory.mkdir();
        }

        Set<String> originalFileKeys = Util.BOOK_NAME_MAP.keySet();
        File[] files = new File(Util.ORIGINAL_TEXT_DIR).listFiles();
        for ( File file : files) {
            String originalFilename = file.getName();
            Matcher chapterMatcher = CHAPTER_PATTERN.matcher(originalFilename);
            if ( !chapterMatcher.matches()) {
                // We don't care about book-level files, etc.
                continue;
            } else if ( file.getName().startsWith("PSA000")) {
                // This is a set of links to all the Psalms; ignore it
                continue;
            }

            for ( String key : originalFileKeys) {
                if ( originalFilename.startsWith(key)) {
//                    System.out.println("Found " + BOOK_NAME_MAP.get(key) + " " + chapterMatcher.group(1));
                    ChapterConverter chapterConverter = new ChapterConverter(file, chapterMatcher, key);
                    chapterConverter.cleanUpFile();
                    break;
                }
            }
        }
    }
}
