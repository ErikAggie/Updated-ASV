package peterson.erik.updatedasv.convert;

/**
 * Non-text parts of the text (<span>, <div>, etc.)
 */
public class NonTextSegment implements Segment {
    private String text;

    /* package */ NonTextSegment(String text) {
        this.text = text;
    }

    @Override
    public String getPlainText() {
        return "";
    }

    @Override
    public int getPlainTextLength() {
        return 0;
    }

    @Override
    public String getFullText() {
        return text;
    }
}
