package mcmacro.updater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileUtils {
    public static String readFile(File file) {
        String data = null;
        try {
            Scanner myReader = new Scanner(file);
            data = myReader.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
