package peterson.erik.updatedasv;

import peterson.erik.updatedasv.convert.ChapterConverter;
import peterson.erik.updatedasv.convert.ConversionItem;

import java.io.File;
import java.io.IOException;
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
        assert files != null;
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
                    if ( originalFilename.endsWith("01.htm")) {
                        System.out.println(originalFilename);
                    }
                    ChapterConverter chapterConverter = new ChapterConverter(file, chapterMatcher, key);
                    chapterConverter.processFile();
                    break;
                }
            }
        }
        ChapterConverter.outputRunTimes();
        for ( ConversionItem converter : Util.CONVERSION_ITEMS) {
            converter.warnIfNoMatchesFound();
        }
        //ChapterConverter.printMatches();
    }
}
