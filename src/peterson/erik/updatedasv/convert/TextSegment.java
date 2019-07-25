package peterson.erik.updatedasv.convert;

/**
 * Text parts of the text (i.e. not span/div declarations)
 */
public class TextSegment implements Segment {
    private String text;

    /*package*/ TextSegment(String text) {
        this.text = text;
    }

    @Override
    public String getPlainText() {
        return text;
    }

    @Override
    public int getPlainTextLength() {
        return text.length();
    }

    @Override
    public String getFullText() {
        return text;
    }

    @Override
    public void updateText(String newText) {
        this.text = newText;
    }
}
