package peterson.erik.updatedasv.convert;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import peterson.erik.updatedasv.Util;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChapterConverter {

    private final File sourceFile;
    private final Matcher chapterMatcher;
    private final String sourceFileKey;
    private final String destinationFileName;

    private final int chapterNumber;
    private final String bookName;
    private final File cleanedUpDirectory;
    private final File updatedDirectory;
    private final String chapterTitle;

    private static long totalParseTime;
    private static long totalCleanUpTime;
    private static long totalWriteCleanFileTime;
    private static long totalUpdateTime;
    private static long totalWriteUpdateFileTime;

    // Used in searching for stuff. Should only use one such method at a time!
    private static final Map<String, String> SEARCH_MAP = new HashMap<>();

    public ChapterConverter(File sourceFile, Matcher chapterMatcher, String sourceFileKey) {
        this.sourceFile = sourceFile;
        this.chapterMatcher = chapterMatcher;
        this.sourceFileKey = sourceFileKey;

        chapterNumber = Integer.parseInt(chapterMatcher.group(1));
        destinationFileName = getDestinationFileName(chapterNumber);

        bookName = Util.BOOK_NAME_MAP.get(sourceFileKey);
        cleanedUpDirectory = new File(new File(Util.CLEANED_UP_TEXT_DIR), bookName);
        updatedDirectory = new File(new File(Util.UPDATED_TEXT_DIR), bookName);
        chapterTitle =
                bookName + " " + chapterNumber;
    }

    public static void outputRunTimes() {
        System.out.println("Parse:\t" + (totalParseTime / 1000000000.0));
        System.out.println("Clean:\t" + (totalCleanUpTime / 1000000000.0));
        System.out.println("Write (c):\t" + (totalWriteCleanFileTime / 1000000000.0));
        System.out.println("Update:\t" + (totalUpdateTime / 1000000000.0));
        System.out.println("Write (u):\t" + (totalWriteUpdateFileTime / 1000000000.0));
    }

    private String getDestinationFileName(int chapterToLookUp) {
        int chapterNumberLength = chapterMatcher.group(1).length();
        String chapterNumberString;
        switch ( chapterNumberLength) {
            case 2:
                chapterNumberString = String.format("%02d", chapterToLookUp);
                break;
            case 3:
                chapterNumberString = String.format("%03d", chapterToLookUp);
                break;
            default:
                throw new UnsupportedOperationException("Book chapter of " + chapterNumberLength + " characters is not supported!");
        }
        return Util.BOOK_NAME_MAP.get(sourceFileKey) + chapterNumberString + ".htm";
    }

    /**
     * Does the cleanup of the file and then the conversion
     * @throws IOException If there was an error reading/writing
     */
    public void processFile() throws IOException {
        String cleanedUpText = cleanUpFile();
        updateText(cleanedUpText);
    }

    //-------------------------------------------------------------------------------
    // Cleaning up the file
    //-------------------------------------------------------------------------------
    private String cleanUpFile() throws IOException {

        StringBuilder newText = new StringBuilder();
        addHeaderInfo(newText);

        long starttime = System.nanoTime();
        Document originalDoc = Jsoup.parse(sourceFile, "UTF-8", "http://example.com/");
        totalParseTime += (System.nanoTime() - starttime);
        starttime = System.nanoTime();
        Elements divElements = originalDoc.getElementsByTag("div");
        for (Element paragraph : divElements) {
            String classname = paragraph.className();
            switch (classname) {
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
//                    System.out.println("Now have " + newText.toString());

        totalCleanUpTime += (System.nanoTime() - starttime);
        starttime = System.nanoTime();
        writeFile(cleanedUpDirectory, newText.toString() + Util.UNMODIFIED_COPYRIGHT + "</body></html>");
        totalWriteCleanFileTime += (System.nanoTime() - starttime);
        return newText.toString();
    }

    private void writeFile(File directory, String textToWrite) throws IOException {

        if ( !directory.exists()) {
            if ( !directory.mkdirs()) {
                throw new IOException("Unable to create directory " + directory.getAbsolutePath());
            }
        }

        // Now that we have the text, optionally do a search
        //findAddedMaterial(newText.toString());
        //findTypesOfSpans(newText.toString());

        File newFile = new File(directory, destinationFileName);
        //System.out.println(cleanedUpDirectory.getName() + "/" + newFile.getName());
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.append(textToWrite);
        fileWriter.close();

    }

    private void addHeaderInfo(StringBuilder newText) {
        newText.append(Util.HEAD_AND_CSS);
        newText.append("<title>").append(chapterTitle).append("</title>");
        newText.append("</head><body>");

        // Fixed top portion with chapter number
        newText.append("<ul class=\"top\">");
        newText.append("<li class=\"chapterTitle\">").append(chapterTitle).append("</li>");
        newText.append("</ul>");

        // Fixed bottom portion with previous/next links
        newText.append("<ul class=\"bottom\">");

        //System.out.println("Checking for " + destinationFileName);
        String previousChapterPath = Util.PREVIOUS_CHAPTER_MAP.get(destinationFileName);
        if ( previousChapterPath == null) {
            previousChapterPath = getDestinationFileName(chapterNumber-1);
        }
        File previousFile = new File(cleanedUpDirectory, previousChapterPath);
        if ( previousFile.exists()) {
            newText.append("<li class=\"previous\"><a href=\"").append(previousChapterPath).append("\">Previous</a></li>");
        }


        String nextChapterPath = Util.NEXT_CHAPTER_MAP.get(destinationFileName);
        if ( nextChapterPath == null) {
            nextChapterPath = getDestinationFileName(Integer.parseInt(chapterMatcher.group(1))+1);
        }
        File nextFile = new File(cleanedUpDirectory, nextChapterPath);
        if ( nextFile.exists()) {
            newText.append("<li class=\"next\"><a href=\"").append(nextChapterPath).append("\">Next</a></li>");
        }
        newText.append("</ul>");

    }

    //-----------------------------------------------------------------------------------------------------------
    // Converting the file to be easier to read
    //-----------------------------------------------------------------------------------------------------------
    private void updateText(String textToUpdate) throws IOException {
        long starttime = System.nanoTime();
        List<Segment> segments = splitIntoSegment(textToUpdate);
        List<Segment> segmentsToUpdate = new ArrayList<>();
        for ( Segment segment : segments) {
            if ( !segment.getPlainText().isEmpty()) {
                segmentsToUpdate.add(segment);
            }
        }
        for ( ConversionItem item : Util.CONVERSION_ITEMS) {
            item.makeConversion(segmentsToUpdate);
        }

        StringBuilder updatedTextBuilder = new StringBuilder();
        for ( Segment segment : segments) {
            updatedTextBuilder.append(segment.getFullText());
        }
        totalUpdateTime += (System.nanoTime() - starttime);
        starttime = System.nanoTime();
        writeFile(updatedDirectory, updatedTextBuilder.toString() + Util.MODIFIED_COPYRIGHT + "</body></html>");
        totalWriteUpdateFileTime += (System.nanoTime() - starttime);
    }

    private List<Segment> splitIntoSegment(String textToSplit) {
        List<Segment> segments = new LinkedList<>();
        while (!textToSplit.isEmpty()) {
            int locationOfOpenBracket = textToSplit.indexOf('<');
            if ( locationOfOpenBracket < 0) {
                // Nothing left but text
                segments.add(new TextSegment(textToSplit));
                break;
            } else if ( locationOfOpenBracket == 0) {
                // Found a bracket!
                int locationOfCloseBracket = textToSplit.indexOf(">");
                if ( locationOfCloseBracket < 0) {
                    throw new IllegalArgumentException("Open bracket without close bracket! : " + textToSplit);
                }
                segments.add(new NonTextSegment(textToSplit.substring(locationOfOpenBracket, locationOfCloseBracket+1)));
                if ( textToSplit.length() == locationOfCloseBracket+1) {
                    // We're done!
                    break;
                }
                textToSplit = textToSplit.substring(locationOfCloseBracket+1);
            } else {
                segments.add(new TextSegment(textToSplit.substring(0, locationOfOpenBracket)));
                textToSplit = textToSplit.substring(locationOfOpenBracket);
            }
        }
        return segments;
    }

    //-----------------------------------------------------------------------------------------------------------
    // These are searches used at various points in the update effort
    //-----------------------------------------------------------------------------------------------------------
    private static final Pattern ADDED_TEXT_PATTERN = Pattern.compile("<span class=\"add\">(.*?)</span>");
    private static final Pattern SPAN_TYPE_PATTERN = Pattern.compile("<span class=\"(.*?)\"");

    private void findAddedMaterial(String text) {
        Matcher matcher = ADDED_TEXT_PATTERN.matcher(text);
        while ( matcher.find()) {
            SEARCH_MAP.put(matcher.group(1), null);
        }
    }

    private void findTypesOfSpans(String text) {
        Matcher matcher = SPAN_TYPE_PATTERN.matcher(text);
        while ( matcher.find()) {
            SEARCH_MAP.put(matcher.group(1), null);
        }
    }

    public static void printMatches() {
        System.out.println("Matches:");
        List<String> keys = new ArrayList<>(SEARCH_MAP.keySet());
        Collections.sort(keys);
        for ( String key : keys) {
            System.out.println(key);
        }
    }
}
