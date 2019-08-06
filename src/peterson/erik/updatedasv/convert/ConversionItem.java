package peterson.erik.updatedasv.convert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConversionItem {
    private final String textToMatch;
    private final String textReplacement;
    private final Pattern patternToMatch;
    private final Pattern beginningPatternToMatch;
    private long numReplacements;

    public ConversionItem(String textToMatch, String textReplacement) {
        this.textToMatch = textToMatch;
        this.textReplacement = textReplacement;

        // The pattern we want is the string preceded and followed by non-word characters (space, punctuation, etc.)
        patternToMatch = Pattern.compile("([\\W]{1})(" + textToMatch + ")([\\W]+)");
        // The special case beginning match (i.e. no text before the match begins)
        beginningPatternToMatch = Pattern.compile("^(" + textToMatch + ")([\\W]+)");
    }

    /* package */ String makeConversion(String originalText) {

        // Do a quick test since, most of the time, we won't need to run the matcher...
        if (!originalText.contains(textToMatch)) {
            return originalText;
        }

        Matcher matcher = patternToMatch.matcher(originalText);
        String newText = matcher.replaceAll(x -> x.group(1) + textReplacement + x.group(3));
        matcher = beginningPatternToMatch.matcher(newText);
        newText = matcher.replaceAll(x -> textReplacement + x.group(2));
        if (!newText.equals(originalText)) {
            numReplacements++;
            return newText;
        } else {
            return originalText;
        }
    }

    public void warnIfNoMatchesFound() {
        if ( numReplacements == 0) {
            System.err.println("No matches found for " + textToMatch + ". This is probably not what you intended. :)");
        }
    }
}
