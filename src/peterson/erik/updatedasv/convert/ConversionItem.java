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
        patternToMatch = Pattern.compile("[\\W]{1}" + textToMatchCombined + "[\\W]{1}");
        // The special case beginning match (i.e. no text before the match begins)
        beginningPatternToMatch = Pattern.compile("^" + textToMatchCombined + "[\\W]{1}");
    }

    /* package */ void makeConversion(List<Segment> segments) {
        checkInternalMatches(segments);
        checkBeginningMatch(segments);
    }

    public void warnIfNoMatchesFound() {
        if ( numReplacements == 0) {
            System.err.println("No matches found for " + textToMatchCombined + ". This is probably not what you intended. :)");
        }
    }

    private void checkInternalMatches(List<Segment> segments) {
        StringBuilder plainText = new StringBuilder();
        for (Segment segment : segments) {
            plainText.append(segment.getPlainText());
        }

        Matcher matcher = patternToMatch.matcher(plainText.toString());

        // We want to go backwards (replace the last match first). This way we don't have to worry about any
        // counts or whatnot getting off (which, as currently designed, shouldn't happen anyway, but it's better
        // to be safe :) )
        Stack<Integer> matchBeginnings = new Stack<>();
        while ( matcher.find()) {
            // Since we're checking for a word boundary at the front, the string we want starts 1 after the
            // beginning of the match
            matchBeginnings.push(matcher.start()+1);
        }

        if ( matchBeginnings.isEmpty()) {
            return;
        }

        //System.out.println("Found " + matchBeginnings.size() + " matches of " + patternToMatch.toString());

        while ( !matchBeginnings.isEmpty()) {
            int startLocation = matchBeginnings.pop();
            makeReplacement(segments, startLocation);
            numReplacements++;
        }
    }

    private void checkBeginningMatch(List<Segment> segments) {
        StringBuilder plainText = new StringBuilder();
        for (Segment segment : segments) {
            plainText.append(segment.getPlainText());
        }

        Matcher matcher = beginningPatternToMatch.matcher(plainText.toString());
        if (!matcher.find()) {
            return;
        }
        makeReplacement(segments, 0);

//        System.out.println("Found beginning match of " + patternToMatch.toString());
    }

    private void makeReplacement(List<Segment> segments, int matchBeginning) {
        int matchEnd = matchBeginning + textToMatchCombined.length() - 1;


        List<Segment> segmentsToWorryAbout = new LinkedList<>();
        // We have a match! Find where it exists
        int currentPlainTextIndex = 0;
        boolean foundBeginning = false;
        for ( Segment segment : segments) {
            if ( !foundBeginning) {
                if (currentPlainTextIndex + segment.getPlainTextLength() > matchBeginning) {
                    // Found the start!
                    foundBeginning = true;
                    segmentsToWorryAbout.add(segment);
                }
            } else {
                segmentsToWorryAbout.add(segment);
            }
            // See if we've got everything...
            if ( currentPlainTextIndex + segment.getPlainTextLength() > matchEnd) {
                break;
            }
            currentPlainTextIndex += segment.getPlainTextLength();
        }

        // For now we're going to expect that all matches are in one segment. If/when this isn't true, we need
        // some extra logic here to deal with that situation...
        if ( segmentsToWorryAbout.size() > 1) {
            System.err.println("Found " + textToMatchCombined + " in " + segmentsToWorryAbout.size() + " segments");
        }

        Segment segment = segmentsToWorryAbout.get(0);
        String segmentText = segment.getPlainText();
        segment.updateText(segmentText.replace(textToMatchCombined, textReplacementCombined));
    }

/*
        int indexToStartWith = 0;
        while ( true) {
            // Have to do this each time as the text will change as we make matches
            StringBuilder plainText = new StringBuilder();
            for ( Segment segment : segments) {
                plainText.append(segment.getPlainText());
            }
            int startIndex = plainText.indexOf(textToMatch, indexToStartWith);
            int endIndex = startIndex + textToMatch.length() - 1;
            // TODO: must check for a partial match...next character must either be a space or the end of the plain text
            if ( startIndex < 0) {
                break;
            } else {
                indexToStartWith = startIndex+1;
                List<Segment> segmentsToWorryAbout = new LinkedList<>();
                // We have a match! Find where it exists
                int startSegment = 0;
                int endSegment = 0;
                int currentPlainTextIndex = 0;
                Segment beginningMatchSegment = null;
                Segment endingMatchSegment = null;
                boolean foundBeginning = false;
                for ( Segment segment : segments) {
                    if ( !foundBeginning) {
                        if (currentPlainTextIndex + segment.getPlainTextLength() > startIndex) {
                            // Found the start!
                            foundBeginning = true;
                            segmentsToWorryAbout.add(segment);
                        }
                    } else {
                        segmentsToWorryAbout.add(segment);
                        if ( currentPlainTextIndex + segment.getPlainTextLength() > endIndex) {
                            break;
                        }
                    }
                    currentPlainTextIndex += segment.getPlainTextLength();
                }

                StringBuilder affectedSegments = new StringBuilder();
                for ( Segment segment : segmentsToWorryAbout) {
                    affectedSegments.append(segment.getPlainText());
                }
                System.out.println("Found " + textToMatch + " in " + affectedSegments);
            }
        }
    }*/
}
