package peterson.erik.updatedasv;

import java.io.IOException;

public class ConverterMain {
    public static void main(String[] args) {
        try {
            CreateHTMLFiles.createFiles();
        } catch ( IOException e) {
            System.err.println("Exception converting HTML files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
