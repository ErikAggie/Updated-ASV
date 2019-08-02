package peterson.erik.updatedasv;

import java.io.IOException;

public class ConverterMain {
    public static void main(String[] args) {
        try {
            long starttime = System.nanoTime();
            CreateHTMLFiles.createFiles();
            long runtime = System.nanoTime();
            System.out.println("Total:\t" + ((runtime - starttime)/1000000000.0));
        } catch ( IOException e) {
            System.err.println("Exception converting HTML files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
