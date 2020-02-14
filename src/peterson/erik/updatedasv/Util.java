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

    public static final String POPUP_BEGINNING = "<a href=\"#FN1\" class=\"notemark\">";
    public static final String POPUP_MIDDLE = "<span class=\"popup\">";
    public static final String POPUP_END = "</span></a>";

    public static final ConversionItem[] CONVERSION_ITEMS = {
            /*
             * Seven-word replacements
             */
            new ConversionItem("the Son of man hath not where", "the Son of man has nowhere"),

            /*
             * Six-word replacements
             */
            new ConversionItem("an angel of the Lord appeareth", "an angel of the Lord appeared"),
            new ConversionItem("to be tempted of the devil", "to be tempted by the devil"),
            /*
             * Five-word replacements
             */
            new ConversionItem("for thus it becometh us", "for it is becoming of us"),
            new ConversionItem("Then saith Jesus unto him", "Then Jesus said to him"),
            new ConversionItem("Then the devil leaveth him", "Then the devil left him"),
            new ConversionItem("And he saith unto them", "And he said to them"),
            new ConversionItem("ye shall not be as", "you will not be like"),
            new ConversionItem("your heavenly Father knoweth that", "your heavenly Father knows that"),
            new ConversionItem("fill it up taketh from", "fill it up takes from"),
            new ConversionItem("Pray ye therefore the Lord", "Pray you therefore to the Lord"),

            /*
             * Four-word replacements
             */
            new ConversionItem("Art in no wise", "Are in no wise"),
            new ConversionItem("mocked of the Wise-men", "mocked by the Wise-men"),
            new ConversionItem("cometh John the Baptist", "John the Baptist came"),
            new ConversionItem("Abraham to our father", "Abraham as our father"),
            new ConversionItem("that bringeth not forth", "that does not bring forth"),
            new ConversionItem("Then cometh Jesus from", "Then Jesus went from"),
            new ConversionItem("and comest thou to", "and you come to"),
            new ConversionItem("Then he suffereth him.", "Then he suffered him."),
            new ConversionItem("every word that proceedeth", "every word that proceeds"),
            new ConversionItem("and saith unto him", "and said to him"),
            new ConversionItem("the devil taketh him", "the devil took him"),
            new ConversionItem("will I give thee", "I will give to you"),
            new ConversionItem("that time began Jesus", "that time Jesus began"),
            new ConversionItem("Come ye after me", "Come after me"),
            new ConversionItem("and there rememberest that", "and there remember that"),
            new ConversionItem("leave there thy gift", "leave your gift"),
            new ConversionItem("every one that looketh", "every one that looks"),
            new ConversionItem("every one that putteth", "every one that puts"),
            new ConversionItem("is put away committeth", "is put away commits"),
            new ConversionItem("all things be accomplished", "all things are accomplished"),
            new ConversionItem("ye do not your", "you do not do your"),
            new ConversionItem("what reward have ye", "what reward do you have"),
            new ConversionItem("thy right hand doeth", "your right hand does"),
            new ConversionItem("Our Father who art", "Our Father who is"),
            new ConversionItem("thy Father, who seeth", "your Father, who sees"),
            new ConversionItem("heavenly Father feedeth them", "heavenly Father feeds them"),
            new ConversionItem("God doth so clothe", "God so clothes"),
            new ConversionItem("But seek ye first", "But seek first"),
            new ConversionItem("therefore like unto them", "therefore like them"),
            new ConversionItem("that saith unto me", "that says to me"),
            new ConversionItem("that doeth the will", "that does the will"),
            new ConversionItem("one therefore that heareth", "one therefore that hears"),
            new ConversionItem("Jesus saith unto him", "Jesus said to him"),
            new ConversionItem("he saith unto him", "he said to him"),
            new ConversionItem("my servant lieth in", "my servant lies in"),
            new ConversionItem("and he doeth it", "and he does it"),
            new ConversionItem("art thou come hither", "have you come here"),
            new ConversionItem("Son of man hath", "Son of man has"),
            new ConversionItem("Why eateth your Teacher", "Why does your Teacher eat"),
            new ConversionItem("meaneth, I desire mercy", "means, I desire mercy"),
            new ConversionItem("Then come to him", "Then came to him"),
            new ConversionItem("no man putteth a", "no man puts a"),
            new ConversionItem("If I do but", "If I but"),
            new ConversionItem("thy faith hath made", "your faith has made"),
            new ConversionItem("Jesus saith unto them", "Jesus said to them"),
            new ConversionItem("Then saith he to", "Then he said to"),
            new ConversionItem("that fadeth not away", "that does not fade away"),
            new ConversionItem("but giveth grace to", "but gives grace to"),
            new ConversionItem("stand ye fast therein", "you stand fast in it"),

            /*
             * Three-word replacements
             */
            new ConversionItem("be thou there", "stay there"),
            new ConversionItem("Make ye ready", "Make ready"),
            new ConversionItem("the axe lieth", "the axe lays"),
            new ConversionItem("he that cometh", "he that comes"),
            new ConversionItem("Then was Jesus", "Then Jesus was"),
            new ConversionItem("he afterward hungered.", "he hungered."),
            new ConversionItem("and showeth him", "and showed him"),
            new ConversionItem("Get thee hence", "Go away"),
            new ConversionItem("so persecuted they", "so they persecuted"),
            new ConversionItem("the salt have", "the salt has"),
            new ConversionItem("it shineth unto", "it shines to"),
            new ConversionItem("thy brother hath", "your brother has"),
            new ConversionItem("come out thence", "come out of there"),
            new ConversionItem("her hath committed", "her has committed"),
            new ConversionItem("right eye causeth", "right eye causes"),
            new ConversionItem("right hand causeth", "right hand causes"),
            new ConversionItem("maketh her an", "makes her an"),
            new ConversionItem("him that asketh", "him that asks"),
            new ConversionItem("turn not thou", "turn not"),
            new ConversionItem("for he maketh", "for he makes"),
            new ConversionItem("and sendeth rain", "and sends rain"),
            new ConversionItem("thou doest alms", "you do alms"),
            new ConversionItem("Father who seeth", "Father who sees"),
            new ConversionItem("when thou prayest", "when you pray"),
            new ConversionItem("your Father knoweth", "your Father knows"),
            new ConversionItem("when thou fastest", "when you fast"),
            new ConversionItem("therefore pray ye:", "therefore pray:"),
            new ConversionItem("why beholdest thou", "why do you behold"),
            new ConversionItem("but considerest not", "but do not consider"),
            new ConversionItem("how wilt thou", "how will you"),
            new ConversionItem("that asketh receiveth", "that asks receives"),
            new ConversionItem("that seeketh findeth", "that seeks finds"),
            new ConversionItem("him that knocketh", "him that knocks"),
            new ConversionItem("do ye also", "do also"),
            new ConversionItem("Enter ye in", "Enter in"),
            new ConversionItem("that leadeth to", "that leads to"),
            new ConversionItem("and doeth them", "and does them"),
            new ConversionItem("one that heareth", "one that hears"),
            new ConversionItem("be thou made", "be made"),
            new ConversionItem("See thou tell", "See that you tell"),
            new ConversionItem("he saith to", "he said to"),
            new ConversionItem("and he goeth", "and he goes"),
            new ConversionItem("and he cometh", "and he comes"),
            new ConversionItem("whithersoever thou goest", "wherever you go"),
            new ConversionItem("Himself took our", "He himself took our"),
            new ConversionItem("This man blasphemeth.", "This man blasphemes."),
            new ConversionItem("Wherefore think ye", "Why do you think"),
            new ConversionItem("then saith he", "then he said"),
            new ConversionItem("Then saith he", "Then he said"),
            new ConversionItem("he was come", "he came"),
            new ConversionItem("Believe ye that", "Do you believe that"),
            new ConversionItem("Then touched he", "Then he touched"),
            new ConversionItem("demons casteth he", "demons he casts"),
            new ConversionItem("neither as lording", "neither lording"),
            new ConversionItem("God resisteth the", "God resists the"),
            new ConversionItem("because he careth", "because he cares"),
            new ConversionItem("lion, walketh about", "lion, walks about"),
            new ConversionItem("whom withstand stedfast", "withstand him steadfast"),

            /*
             * Two-word replacements
             */
            new ConversionItem("thou son", "son"),
            new ConversionItem("come forth", "come"),
            new ConversionItem("cometh forth", "comes"),
            new ConversionItem("Repent ye;", "Repent,"),
            new ConversionItem("thou art", "you are"),
            new ConversionItem("thou wilt", "you will"),
            new ConversionItem("holden with", "held by"),
            new ConversionItem("be exceeding", "be exceedingly"),
            new ConversionItem("canst not", "cannot"),
            new ConversionItem("whosoever smiteth", "whosoever smites"),
            new ConversionItem("use not", "do not use"),
            new ConversionItem("doth consume", "consumes"),
            new ConversionItem("bringeth forth", "brings forth"),
            new ConversionItem("exceeding fierce,", "exceedingly fierce,"),
            new ConversionItem("fast oft,", "fast often,"),
            new ConversionItem("but sleepeth.", "but sleeps."),
            new ConversionItem("saluteth you", "salutes you"),
            new ConversionItem("so doth", "so does"),

            /*
             * Single-word replacements place last so longer matches are done first...)
             */
            new ConversionItem("beget", "father"),
            new ConversionItem("begat", "fathered"),
            new ConversionItem("privily", "privately"),
            new ConversionItem("thy", "your"),
            new ConversionItem("Thy", "Your"),
            new ConversionItem("shalt", "shall"),
            new ConversionItem("unto", "to"),
            new ConversionItem("thee", "you"),
            new ConversionItem("thou", "you"),
            new ConversionItem("Thou", "You"),
            new ConversionItem("ye", "you"),
            new ConversionItem("Ye", "You"),
            new ConversionItem("thither", "there"),
            new ConversionItem("Judæa", "Judea"),
            new ConversionItem("Wise-men", "wise men"),
            new ConversionItem("hewn", "cut"),
            new ConversionItem("leathern", "leather"),
            new ConversionItem("thyself", "yourself"),
            new ConversionItem("thence", "there"),
            new ConversionItem("divers", "various"),
            new ConversionItem("wherewith", "with what"),
            new ConversionItem("thenceforth", "thereafter"),
            new ConversionItem("aught", "anything"),
            new ConversionItem("thine", "your"),
            new ConversionItem("canst", "can"),
            new ConversionItem("doest", "do"),
            new ConversionItem("shouldest", "should"),
            new ConversionItem("hast", "have"),
            new ConversionItem("whithersoever", "wherever"),
            new ConversionItem("spake", "spoke"),
            new ConversionItem("hereof", "concerning this"),
            new ConversionItem("ensamples", "examples"),
            new ConversionItem("Yea", "Yes"),
            new ConversionItem("doth", "does")


    };
}
