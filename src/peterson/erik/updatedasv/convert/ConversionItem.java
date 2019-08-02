package peterson.erik.updatedasv.convert;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConversionItem {
    private final String[] textToReplaceWithSeparated;
    private final int[] replacementOrder;
    private final String textToMatchCombined;
    private final String textReplacementCombined;
    private final Pattern patternToMatch;
    private final Pattern beginningPatternToMatch;
    private long numReplacements;

    public ConversionItem(String[] textToMatchSeparated, String[] textToReplaceWithSeparated, int[] replacementOrder) {
        this.textToReplaceWithSeparated = textToReplaceWithSeparated;
        this.replacementOrder = replacementOrder;
        this.textToMatchCombined = String.join(" ", textToMatchSeparated);
        this.textReplacementCombined = String.join(" ", textToReplaceWithSeparated);

        // The pattern we want is the string preceded and followed by non-word characters (space, punctuation, etc.)
        patternToMatch = Pattern.compile("([\\W]{1})(" + textToMatchCombined + ")([\\W]+)");
        // The special case beginning match (i.e. no text before the match begins)
        beginningPatternToMatch = Pattern.compile("^(" + textToMatchCombined + ")([\\W]+)");
    }

    /* package */ void makeConversion(List<Segment> segments) {
        for (Segment segment : segments) {
            String originalText = segment.getPlainText();
            if (originalText.isEmpty()) {
                continue;
            }

            if (originalText.contains("Take heed that ye do not") && textToMatchCombined.equals("ye")) {
                System.out.println("Here!");
            }

            Matcher matcher = patternToMatch.matcher(originalText);
            String newText = matcher.replaceAll(x -> x.group(1) + textReplacementCombined + x.group(3));
            matcher = beginningPatternToMatch.matcher(newText);
            newText = matcher.replaceAll(x -> textReplacementCombined + x.group(2));
            if (!newText.equals(originalText)) {
                numReplacements++;
                segment.updateText(newText);
            }
        }
    }

    public void warnIfNoMatchesFound() {
        if ( numReplacements == 0) {
            System.err.println("No matches found for " + textToMatchCombined + ". This is probably not what you intended. :)");
        }
    }
}
