import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ira on 9/29/16.
 */
public class FilePlay {
    public static void main(String[] args) throws IOException {
        ArrayList<File> file_folder = new ArrayList<File>();
        ArrayList<File> new1 = new ArrayList<File>();

        String random = RandomStringUtils.randomAlphabetic(5);
        random = random.toLowerCase();
        System.out.println("My random string is " + random + "\n");

        for(int x=0; x<6; x++) {
            File file1 = new File("/Users/Ira/Pictures/1/" + x + random + ".txt");

            if (file1.createNewFile()) {
                System.out.println("file created");
            } else {
                System.out.println("file already exists");
            }

            file_folder.add(file1);

        }

        //System.out.println("List of my files" + file_folder);

        for(int x=0; x<6; x++) {
            File file_arraylist_value = file_folder.get(x);
            File new_name = new File("/Users/Ira/Pictures/1/file" + x + ".txt");
            file_arraylist_value.renameTo(new_name);

            new1.add(new_name);
            //System.out.println("File title is " + new_name);
        }

        System.out.println("Renamed list of files " + new1 + "\n");
        System.out.println("Old List" + file_folder + "\n");
            //new_name.delete();


        //FileUtils.cleanDirectory(new File("/Users/Ira/Pictures/1/"));

    }
}
