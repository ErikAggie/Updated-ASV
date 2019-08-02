package peterson.erik.updatedasv;

import peterson.erik.updatedasv.convert.ConversionItem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Util {
    /* package */ static final String ORIGINAL_TEXT_DIR = "originalText";
    public static final String CLEANED_UP_TEXT_DIR = "ASV";
    public static final String UPDATED_TEXT_DIR = "Updated-ASV";
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

    // For cases where the previous chapter isn't <current chapter - 1>, i.e. book boundaries
    public static final Map<String, String> NEXT_CHAPTER_MAP = new HashMap<>();
    static {

        NEXT_CHAPTER_MAP.put("Genesis50.htm", "../Exodus/Exodus01.htm");
        NEXT_CHAPTER_MAP.put("Exodus40.htm", "../Leviticus/Leviticus01.htm");
        NEXT_CHAPTER_MAP.put("Leviticus27.htm", "../Numbers/Numbers01.htm");
        NEXT_CHAPTER_MAP.put("Numbers36.htm", "../Deuteronomy/Deuteronomy01.htm");
        NEXT_CHAPTER_MAP.put("Deuteronomy34.htm", "../Joshua/Joshua01.htm");
        NEXT_CHAPTER_MAP.put("Joshua24.htm", "../Judges/Judges01.htm");
        NEXT_CHAPTER_MAP.put("Judges21.htm", "../Ruth/Ruth01.htm");
        NEXT_CHAPTER_MAP.put("Ruth04.htm", "../1 Samuel/1 Samuel01.htm");
        NEXT_CHAPTER_MAP.put("1 Samuel31.htm", "../2 Samuel/2 Samuel01.htm");
        NEXT_CHAPTER_MAP.put("2 Samuel24.htm", "../1 Kings/1 Kings01.htm");
        NEXT_CHAPTER_MAP.put("1 Kings22.htm", "../2 Kings/2 Kings01.htm");
        NEXT_CHAPTER_MAP.put("2 Kings25.htm", "../1 Chronicles/1 Chronicles01.htm");
        NEXT_CHAPTER_MAP.put("1 Chronicles29.htm", "../2 Chronicles/2 Chronicles01.htm");
        NEXT_CHAPTER_MAP.put("2 Chronicles36.htm", "../Ezra/Ezra01.htm");
        NEXT_CHAPTER_MAP.put("Ezra10.htm", "../Nehemiah/Nehemiah01.htm");
        NEXT_CHAPTER_MAP.put("Nehemiah13.htm", "../Esther/Esther01.htm");
        NEXT_CHAPTER_MAP.put("Esther10.htm", "../Job/Job01.htm");
        NEXT_CHAPTER_MAP.put("Job42.htm", "../Psalms/Psalms001.htm");
        NEXT_CHAPTER_MAP.put("Psalms150.htm", "../Proverbs/Proverbs01.htm");
        NEXT_CHAPTER_MAP.put("Proverbs31.htm", "../Ecclesiastes/Ecclesiastes01.htm");
        NEXT_CHAPTER_MAP.put("Ecclesiastes12.htm", "../Song of Solomon/Song of Solomon01.htm");
        NEXT_CHAPTER_MAP.put("Song of Solomon08.htm", "../Isaiah/Isaiah01.htm");
        NEXT_CHAPTER_MAP.put("Isaiah66.htm", "../Jeremiah/Jeremiah01.htm");
        NEXT_CHAPTER_MAP.put("Jeremiah52.htm", "../Lamentations/Lamentations01.htm");
        NEXT_CHAPTER_MAP.put("Lamentations05.htm", "../Ezekiel/Ezekiel01.htm");
        NEXT_CHAPTER_MAP.put("Ezekiel48.htm", "../Daniel/Daniel01.htm");
        NEXT_CHAPTER_MAP.put("Daniel12.htm", "../Hosea/Hosea01.htm");
        NEXT_CHAPTER_MAP.put("Hosea14.htm", "../Joel/Joel01.htm");
        NEXT_CHAPTER_MAP.put("Joel03.htm", "../Amos/Amos01.htm");
        NEXT_CHAPTER_MAP.put("Amos09.htm", "../Obadiah/Obadiah01.htm");
        NEXT_CHAPTER_MAP.put("Obadiah01.htm", "../Jonah/Jonah01.htm");
        NEXT_CHAPTER_MAP.put("Jonah04.htm", "../Micah/Micah01.htm");
        NEXT_CHAPTER_MAP.put("Micah07.htm", "../Nahum/Nahum01.htm");
        NEXT_CHAPTER_MAP.put("Nahum03.htm", "../Habakkuk/Habakkuk01.htm");
        NEXT_CHAPTER_MAP.put("Habakkuk03.htm", "../Zephaniah/Zephaniah01.htm");
        NEXT_CHAPTER_MAP.put("Zephaniah03.htm", "../Haggai/Haggai01.htm");
        NEXT_CHAPTER_MAP.put("Haggai02.htm", "../Zechariah/Zechariah01.htm");
        NEXT_CHAPTER_MAP.put("Zechariah14.htm", "../Malachi/Malachi01.htm");
        NEXT_CHAPTER_MAP.put("Malachi04.htm", "../Matthew/Matthew01.htm");
        NEXT_CHAPTER_MAP.put("Matthew28.htm", "../Mark/Mark01.htm");
        NEXT_CHAPTER_MAP.put("Mark16.htm", "../Luke/Luke01.htm");
        NEXT_CHAPTER_MAP.put("Luke24.htm", "../John/John01.htm");
        NEXT_CHAPTER_MAP.put("John21.htm", "../Acts/Acts01.htm");
        NEXT_CHAPTER_MAP.put("Acts28.htm", "../Romans/Romans01.htm");
        NEXT_CHAPTER_MAP.put("Romans16.htm", "../1 Corinthians/1 Corinthians01.htm");
        NEXT_CHAPTER_MAP.put("1 Corinthians16.htm", "../2 Corinthians/2 Corinthians01.htm");
        NEXT_CHAPTER_MAP.put("2 Corinthians13.htm", "../Galatians/Galatians01.htm");
        NEXT_CHAPTER_MAP.put("Galatians06.htm", "../Ephesians/Ephesians01.htm");
        NEXT_CHAPTER_MAP.put("Ephesians06.htm", "../Philippians/Philippians01.htm");
        NEXT_CHAPTER_MAP.put("Philippians04.htm", "../Colossians/Colossians01.htm");
        NEXT_CHAPTER_MAP.put("Colossians04.htm", "../1 Thessalonians/1 Thessalonians01.htm");
        NEXT_CHAPTER_MAP.put("1 Thessalonians05.htm", "../2 Thessalonians/2 Thessalonians01.htm");
        NEXT_CHAPTER_MAP.put("2 Thessalonians03.htm", "../1 Timothy/1 Timothy01.htm");
        NEXT_CHAPTER_MAP.put("1 Timothy06.htm", "../2 Timothy/2 Timothy01.htm");
        NEXT_CHAPTER_MAP.put("2 Timothy04.htm", "../Titus/Titus01.htm");
        NEXT_CHAPTER_MAP.put("Titus03.htm", "../Philemon/Philemon01.htm");
        NEXT_CHAPTER_MAP.put("Philemon01.htm", "../Hebrews/Hebrews01.htm");
        NEXT_CHAPTER_MAP.put("Hebrews13.htm", "../James/James01.htm");
        NEXT_CHAPTER_MAP.put("James05.htm", "../1 Peter/1 Peter01.htm");
        NEXT_CHAPTER_MAP.put("1 Peter05.htm", "../2 Peter/2 Peter01.htm");
        NEXT_CHAPTER_MAP.put("2 Peter03.htm", "../1 John/1 John01.htm");
        NEXT_CHAPTER_MAP.put("1 John05.htm", "../2 John/2 John01.htm");
        NEXT_CHAPTER_MAP.put("2 John01.htm", "../3 John/3 John01.htm");
        NEXT_CHAPTER_MAP.put("3 John01.htm", "../Jude/Jude01.htm");
        NEXT_CHAPTER_MAP.put("Jude01.htm", "../Revelation/Revelation01.htm");
    }

    public static final Map<String, String> PREVIOUS_CHAPTER_MAP = new HashMap<>();
    static {

        PREVIOUS_CHAPTER_MAP.put("Revelation01.htm", "../Jude/Jude01.htm");
        PREVIOUS_CHAPTER_MAP.put("Jude01.htm", "../3 John/3 John01.htm");
        PREVIOUS_CHAPTER_MAP.put("3 John01.htm", "../2 John/2 John01.htm");
        PREVIOUS_CHAPTER_MAP.put("2 John01.htm", "../1 John/1 John05.htm");
        PREVIOUS_CHAPTER_MAP.put("1 John01.htm", "../2 Peter/2 Peter03.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Peter01.htm", "../1 Peter/1 Peter05.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Peter01.htm", "../James/James05.htm");
        PREVIOUS_CHAPTER_MAP.put("James01.htm", "../Hebrews/Hebrews13.htm");
        PREVIOUS_CHAPTER_MAP.put("Hebrews01.htm", "../Philemon/Philemon01.htm");
        PREVIOUS_CHAPTER_MAP.put("Philemon01.htm", "../Titus/Titus03.htm");
        PREVIOUS_CHAPTER_MAP.put("Titus01.htm", "../2 Timothy/2 Timothy04.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Timothy01.htm", "../1 Timothy/1 Timothy06.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Timothy01.htm", "../2 Thessalonians/2 Thessalonians03.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Thessalonians01.htm", "../1 Thessalonians/1 Thessalonians05.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Thessalonians01.htm", "../Colossians/Colossians04.htm");
        PREVIOUS_CHAPTER_MAP.put("Colossians01.htm", "../Philippians/Philippians04.htm");
        PREVIOUS_CHAPTER_MAP.put("Philippians01.htm", "../Ephesians/Ephesians06.htm");
        PREVIOUS_CHAPTER_MAP.put("Ephesians01.htm", "../Galatians/Galatians06.htm");
        PREVIOUS_CHAPTER_MAP.put("Galatians01.htm", "../2 Corinthians/2 Corinthians13.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Corinthians01.htm", "../1 Corinthians/1 Corinthians16.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Corinthians01.htm", "../Romans/Romans16.htm");
        PREVIOUS_CHAPTER_MAP.put("Romans01.htm", "../Acts/Acts28.htm");
        PREVIOUS_CHAPTER_MAP.put("Acts01.htm", "../John/John21.htm");
        PREVIOUS_CHAPTER_MAP.put("John01.htm", "../Luke/Luke24.htm");
        PREVIOUS_CHAPTER_MAP.put("Luke01.htm", "../Mark/Mark16.htm");
        PREVIOUS_CHAPTER_MAP.put("Mark01.htm", "../Matthew/Matthew28.htm");
        PREVIOUS_CHAPTER_MAP.put("Matthew01.htm", "../Malachi/Malachi04.htm");
        PREVIOUS_CHAPTER_MAP.put("Malachi01.htm", "../Zechariah/Zechariah14.htm");
        PREVIOUS_CHAPTER_MAP.put("Zechariah01.htm", "../Haggai/Haggai02.htm");
        PREVIOUS_CHAPTER_MAP.put("Haggai01.htm", "../Zephaniah/Zephaniah03.htm");
        PREVIOUS_CHAPTER_MAP.put("Zephaniah01.htm", "../Habakkuk/Habakkuk03.htm");
        PREVIOUS_CHAPTER_MAP.put("Habakkuk01.htm", "../Nahum/Nahum03.htm");
        PREVIOUS_CHAPTER_MAP.put("Nahum01.htm", "../Micah/Micah07.htm");
        PREVIOUS_CHAPTER_MAP.put("Micah01.htm", "../Jonah/Jonah04.htm");
        PREVIOUS_CHAPTER_MAP.put("Jonah01.htm", "../Obadiah/Obadiah01.htm");
        PREVIOUS_CHAPTER_MAP.put("Obadiah01.htm", "../Amos/Amos09.htm");
        PREVIOUS_CHAPTER_MAP.put("Amos01.htm", "../Joel/Joel03.htm");
        PREVIOUS_CHAPTER_MAP.put("Joel01.htm", "../Hosea/Hosea14.htm");
        PREVIOUS_CHAPTER_MAP.put("Hosea01.htm", "../Daniel/Daniel12.htm");
        PREVIOUS_CHAPTER_MAP.put("Daniel01.htm", "../Ezekiel/Ezekiel48.htm");
        PREVIOUS_CHAPTER_MAP.put("Ezekiel01.htm", "../Lamentations/Lamentations05.htm");
        PREVIOUS_CHAPTER_MAP.put("Lamentations01.htm", "../Jeremiah/Jeremiah52.htm");
        PREVIOUS_CHAPTER_MAP.put("Jeremiah01.htm", "../Isaiah/Isaiah66.htm");
        PREVIOUS_CHAPTER_MAP.put("Isaiah01.htm", "../Song of Solomon/Song of Solomon08.htm");
        PREVIOUS_CHAPTER_MAP.put("Song of Solomon01.htm", "../Ecclesiastes/Ecclesiastes12.htm");
        PREVIOUS_CHAPTER_MAP.put("Ecclesiastes01.htm", "../Proverbs/Proverbs31.htm");
        PREVIOUS_CHAPTER_MAP.put("Proverbs01.htm", "../Psalms/Psalms150.htm");
        PREVIOUS_CHAPTER_MAP.put("Psalms001.htm", "../Job/Job42.htm");
        PREVIOUS_CHAPTER_MAP.put("Job01.htm", "../Esther/Esther10.htm");
        PREVIOUS_CHAPTER_MAP.put("Esther01.htm", "../Nehemiah/Nehemiah13.htm");
        PREVIOUS_CHAPTER_MAP.put("Nehemiah01.htm", "../Ezra/Ezra10.htm");
        PREVIOUS_CHAPTER_MAP.put("Ezra01.htm", "../2 Chronicles/2 Chronicles36.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Chronicles01.htm", "../1 Chronicles/1 Chronicles29.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Chronicles01.htm", "../2 Kings/2 Kings25.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Kings01.htm", "../1 Kings/1 Kings22.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Kings01.htm", "../2 Samuel/2 Samuel24.htm");
        PREVIOUS_CHAPTER_MAP.put("2 Samuel01.htm", "../1 Samuel/1 Samuel31.htm");
        PREVIOUS_CHAPTER_MAP.put("1 Samuel01.htm", "../Ruth/Ruth04.htm");
        PREVIOUS_CHAPTER_MAP.put("Ruth01.htm", "../Judges/Judges21.htm");
        PREVIOUS_CHAPTER_MAP.put("Judges01.htm", "../Joshua/Joshua24.htm");
        PREVIOUS_CHAPTER_MAP.put("Joshua01.htm", "../Deuteronomy/Deuteronomy34.htm");
        PREVIOUS_CHAPTER_MAP.put("Deuteronomy01.htm", "../Numbers/Numbers36.htm");
        PREVIOUS_CHAPTER_MAP.put("Numbers01.htm", "../Leviticus/Leviticus27.htm");
        PREVIOUS_CHAPTER_MAP.put("Leviticus01.htm", "../Exodus/Exodus40.htm");
        PREVIOUS_CHAPTER_MAP.put("Exodus01.htm", "../Genesis/Genesis50.htm");
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
            "<div class=copyright>" +
                    "This text is based on the American Standard Version, which is in the public domain. " +
                    "This modification is Copyright 2019 by Erik Peterson. All rights reserved. " +
                    "Future permissions will likely be more open.</div>";

    public static final List<ConversionItem> CONVERSION_ITEMS = new LinkedList<>();
    static {
        // These changes are denoted like this:
        // * The text to match (split into groups for word reordering
        // * The corresponding text sections--in the same order; this is important in case the text crosses a div or span boundary
        // * The order to put the new segments in (i.e. if there is any reordering)

        /*
         * Six-word replacements
         */
        CONVERSION_ITEMS.add(new ConversionItem("an angel of the Lord appeareth", "an angel of the Lord appeared"));
        CONVERSION_ITEMS.add(new ConversionItem("to be tempted of the devil", "to be tempted by the devil"));

        /*
         * Five-word replacements
         */
        CONVERSION_ITEMS.add(new ConversionItem("for thus it becometh us", "for it is becoming of us"));
        CONVERSION_ITEMS.add(new ConversionItem("Then saith Jesus unto him", "Then Jesus said to him"));
        CONVERSION_ITEMS.add(new ConversionItem("Then the devil leaveth him", "Then the devil left him"));
        CONVERSION_ITEMS.add(new ConversionItem("And he saith unto them", "And he said to them"));
        CONVERSION_ITEMS.add(new ConversionItem("ye shall not be as", "you will not be like"));

        /*
         * Four-word replacements
         */
        CONVERSION_ITEMS.add(new ConversionItem("Art in no wise", "Are in no wise"));
        CONVERSION_ITEMS.add(new ConversionItem("mocked of the Wise-men", "mocked by the Wise-men"));
        CONVERSION_ITEMS.add(new ConversionItem("cometh John the Baptist", "John the Baptist came"));
        CONVERSION_ITEMS.add(new ConversionItem("Abraham to our father", "Abraham as our father"));
        CONVERSION_ITEMS.add(new ConversionItem("that bringeth not forth", "that does not bring forth"));
        CONVERSION_ITEMS.add(new ConversionItem("Then cometh Jesus from", "Then Jesus went from"));
        CONVERSION_ITEMS.add(new ConversionItem("and comest thou to", "and you come to"));
        CONVERSION_ITEMS.add(new ConversionItem("Then he suffereth him.", "Then he suffered him."));
        CONVERSION_ITEMS.add(new ConversionItem("every word that proceedeth", "every word that proceeds"));
        CONVERSION_ITEMS.add(new ConversionItem("and saith unto him", "and said to him"));
        CONVERSION_ITEMS.add(new ConversionItem("the devil taketh him", "the devil took him"));
        CONVERSION_ITEMS.add(new ConversionItem("will I give thee", "I will give to you"));
        CONVERSION_ITEMS.add(new ConversionItem("that time began Jesus", "that time Jesus began"));
        CONVERSION_ITEMS.add(new ConversionItem("Come ye after me", "Come after me"));
        CONVERSION_ITEMS.add(new ConversionItem("and there rememberest that", "and there remember that"));
        CONVERSION_ITEMS.add(new ConversionItem("leave there thy gift", "leave your gift"));
        CONVERSION_ITEMS.add(new ConversionItem("every one that looketh", "every one that looks"));
        CONVERSION_ITEMS.add(new ConversionItem("every one that putteth", "every one that puts"));
        CONVERSION_ITEMS.add(new ConversionItem("is put away committeth", "is put away commits"));
        CONVERSION_ITEMS.add(new ConversionItem("all things be accomplished", "all things are accomplished"));
        CONVERSION_ITEMS.add(new ConversionItem("ye do not your", "you do not do your"));
        CONVERSION_ITEMS.add(new ConversionItem("what reward have ye", "what reward do you have"));
        CONVERSION_ITEMS.add(new ConversionItem("thy right hand doeth", "your right hand does"));
        CONVERSION_ITEMS.add(new ConversionItem("Our Father who art", "Our Father who is"));
        CONVERSION_ITEMS.add(new ConversionItem("thy Father, who seeth", "your Father, who seeth"));

        /*
         * Three-word replacements
         */
        CONVERSION_ITEMS.add(new ConversionItem("be thou there", "stay there"));
        CONVERSION_ITEMS.add(new ConversionItem("Make ye ready", "Make ready"));
        CONVERSION_ITEMS.add(new ConversionItem("the axe lieth", "the axe lays"));
        CONVERSION_ITEMS.add(new ConversionItem("he that cometh", "he that comes"));
        CONVERSION_ITEMS.add(new ConversionItem("Then was Jesus", "Then Jesus was"));
        CONVERSION_ITEMS.add(new ConversionItem("he afterward hungered.", "he hungered."));
        CONVERSION_ITEMS.add(new ConversionItem("and showeth him", "and showed him"));
        CONVERSION_ITEMS.add(new ConversionItem("Get thee hence", "Go away"));
        CONVERSION_ITEMS.add(new ConversionItem("so persecuted they", "so they persecuted"));
        CONVERSION_ITEMS.add(new ConversionItem("the salt have", "the salt has"));
        CONVERSION_ITEMS.add(new ConversionItem("it shineth unto", "it shines to"));
        CONVERSION_ITEMS.add(new ConversionItem("thy brother hath", "your brother has"));
        CONVERSION_ITEMS.add(new ConversionItem("come out thence", "come out of there"));
        CONVERSION_ITEMS.add(new ConversionItem("her hath committed", "her has committed"));
        CONVERSION_ITEMS.add(new ConversionItem("right eye causeth", "right eye causes"));
        CONVERSION_ITEMS.add(new ConversionItem("right hand causeth", "right hand causes"));
        CONVERSION_ITEMS.add(new ConversionItem("maketh her an", "makes her an"));
        CONVERSION_ITEMS.add(new ConversionItem("him that asketh", "him that asks"));
        CONVERSION_ITEMS.add(new ConversionItem("turn not thou", "turn not"));
        CONVERSION_ITEMS.add(new ConversionItem("for he maketh", "for he makes"));
        CONVERSION_ITEMS.add(new ConversionItem("and sendeth rain", "and sends rain"));
        CONVERSION_ITEMS.add(new ConversionItem("thou doest alms", "you do alms"));
        CONVERSION_ITEMS.add(new ConversionItem("Father who seeth", "Father who sees"));
        CONVERSION_ITEMS.add(new ConversionItem("when thou prayest", "when you pray"));
        CONVERSION_ITEMS.add(new ConversionItem("your Father knoweth", "your Father knows"));
        CONVERSION_ITEMS.add(new ConversionItem("when thou fastest", "when you fast"));

        /*
         * Two-word replacements
         */
        CONVERSION_ITEMS.add(new ConversionItem("thou son", "son"));
        CONVERSION_ITEMS.add(new ConversionItem("come forth", "come"));
        CONVERSION_ITEMS.add(new ConversionItem("cometh forth", "comes"));
        CONVERSION_ITEMS.add(new ConversionItem("Repent ye;", "Repent,"));
        CONVERSION_ITEMS.add(new ConversionItem("thou art", "you are"));
        CONVERSION_ITEMS.add(new ConversionItem("thou wilt", "you will"));
        CONVERSION_ITEMS.add(new ConversionItem("holden with", "held by"));
        CONVERSION_ITEMS.add(new ConversionItem("be exceeding", "be exceedingly"));
        CONVERSION_ITEMS.add(new ConversionItem("canst not", "cannot"));
        CONVERSION_ITEMS.add(new ConversionItem("whosoever smiteth", "whosoever smites"));

        /*
         * Single-word replacements (place last so longer matches are done first...)
         */
        CONVERSION_ITEMS.add(new ConversionItem("beget", "father"));
        CONVERSION_ITEMS.add(new ConversionItem("begat", "fathered"));
        CONVERSION_ITEMS.add(new ConversionItem("privily", "privately"));
        CONVERSION_ITEMS.add(new ConversionItem("thy", "your"));
        CONVERSION_ITEMS.add(new ConversionItem("Thy", "Your"));
        // Make sure "shall" comes before "shalt", otherwise "shalt" will go to "will"
        CONVERSION_ITEMS.add(new ConversionItem("shall", "will"));
        CONVERSION_ITEMS.add(new ConversionItem("shalt", "shall"));
        CONVERSION_ITEMS.add(new ConversionItem("unto", "to"));
        CONVERSION_ITEMS.add(new ConversionItem("thee", "you"));
        CONVERSION_ITEMS.add(new ConversionItem("thou", "you"));
        CONVERSION_ITEMS.add(new ConversionItem("Thou", "You"));
        CONVERSION_ITEMS.add(new ConversionItem("ye", "you"));
        CONVERSION_ITEMS.add(new ConversionItem("Ye", "You"));
        CONVERSION_ITEMS.add(new ConversionItem("thither", "there"));
        CONVERSION_ITEMS.add(new ConversionItem("Judæa", "Judea"));
        CONVERSION_ITEMS.add(new ConversionItem("Wise-men", "wise men"));
        CONVERSION_ITEMS.add(new ConversionItem("hewn", "cut"));
        CONVERSION_ITEMS.add(new ConversionItem("leathern", "leather"));
        CONVERSION_ITEMS.add(new ConversionItem("thyself", "yourself"));
        CONVERSION_ITEMS.add(new ConversionItem("thence", "there"));
        CONVERSION_ITEMS.add(new ConversionItem("divers", "various"));
        CONVERSION_ITEMS.add(new ConversionItem("wherewith", "with what"));
        CONVERSION_ITEMS.add(new ConversionItem("thenceforth", "thereafter"));
        CONVERSION_ITEMS.add(new ConversionItem("aught", "anything"));
        CONVERSION_ITEMS.add(new ConversionItem("thine", "your"));
        CONVERSION_ITEMS.add(new ConversionItem("canst", "can"));
        CONVERSION_ITEMS.add(new ConversionItem("doest", "do"));



        //CONVERSION_ITEMS.add(new ConversionItem("didst", "not", "thou", "did", "not", "you"}, new int[] {0, 2, 1}));
        //CONVERSION_ITEMS.add(new ConversionItem("Didst", "not", "thou", "Did", "not", "you"}, new int[] {0, 2, 1}));

        // Test for items that cover multiple segments
        //CONVERSION_ITEMS.add(new ConversionItem("unto thy handmaid, saying, Assuredly", "nothing"));
    }
}
