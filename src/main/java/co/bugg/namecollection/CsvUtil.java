package co.bugg.namecollection;

import com.google.common.io.Files;
import org.apache.commons.io.Charsets;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvUtil {
    /**
     * Write new CSV entry to names file
     * @param name Username to be written
     */
    public static void write(String name) {
        File file = new File(NameCollection.fullPath + "names.csv");

        // Create the file if it doesn't exist already
        if(!file.exists()) {
            try {
                boolean createStatus = file.createNewFile();
                if(createStatus) {
                    System.out.println("Created new names file");
                } else {
                    System.out.println("Didn't create new names file; already exists");
                }
            } catch (IOException e) {
                System.out.println("Unable to create names file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        try {
            String fileContents = Files.toString(file, Charsets.UTF_8);
            // Creating regex for finding entry in the file contents
            String regex = "(?:^| )" + name + ",";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fileContents);

            // If the user isn't already in the file
            if(!matcher.find()) {
                Files.append(name + ", ", file, Charsets.UTF_8);
                System.out.println("Appended new name " + name);
            } else {
                System.out.println("User " + name + " already listed");
            }
        } catch (IOException e) {
            System.out.println("Unable to append name: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates the directory if it doesn't exist already. Runs at preInit
     */
    public static void createDir(String dirPath) {
        File dir = new File(dirPath);

        if(!dir.isDirectory()) {
            boolean success = dir.mkdir();

            if(success) {
                System.out.println("Created directory " + dirPath + ".");
            } else {
                System.out.println("-----------------------------------------------");
                System.out.println("UNABLE TO CREATE DIRECTORY! MOD MIGHT NOT WORK.");
                System.out.println("-----------------------------------------------");
            }
        }
    }
}
