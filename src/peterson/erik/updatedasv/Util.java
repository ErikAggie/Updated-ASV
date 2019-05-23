package peterson.erik.updatedasv;

import java.util.HashMap;
import java.util.Map;

public class Util {
    /* package */ static final String ORIGINAL_TEXT_DIR = "originalText";
    public static final String CLEANED_UP_TEXT_DIR = "ASV";
    //"updatedText";

    public static final Map<String, String> BOOK_NAME_MAP = new HashMap<>();
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

    public static final String HEAD_AND_CSS =
            "<html><head>" +
                "<link type=\"text/css\" href=\"../styles.css\" rel=\"stylesheet\" media=\"all\" />";

    public static final String UNMODIFIED_COPYRIGHT =
            "<div class=copyright>" +
                    "The American Standard Version is in the public domain, and you may use it freely. " +
                    "As with any text--copyrighted or not--be sure to cite the source with something like (ASV) " +
                    "at the end of your quotation.</div>";

    public static final String MODIFIED_COPYRIGHT =
            "<div class=copyright" +
                    "This text is based on the American Standard Version, which is in the public domain. " +
                    "This modification is Copyright 2019 by Erik Peterson. All rights reserved. " +
                    "Future permissions will likely be more open.</div>";
}
