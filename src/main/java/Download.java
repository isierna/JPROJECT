
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ira on 10/17/16.
 */
public class Download {
    static String directory = "/Users/Ira/Pictures/2/";
    static String image_url = "https://cdn.shopify.com/s/files/1/0051/4802/products/productpic2atommug_1024x1024.jpg?v=1448907134";
    static String destination;

    public static void downloadImage(String directory, String image_url) throws IOException {
        File file_object = new File(directory);

        if(file_object.list().length>1) {
            System.out.println("you can't use this folder");
        }else {
            System.out.println("go ahead");
            new File(directory).mkdir();

            URL url = new URL(image_url);
            String file = url.getFile();

            destination = directory + file.substring(file.lastIndexOf("/"), file.indexOf("?"));

            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(destination);

            byte[] bt = new byte[2048];
            int length;

            while ((length = inputStream.read(bt)) != -1) {
                outputStream.write(bt, 0, length);
            }

            inputStream.close();
            outputStream.close();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            double file_size_online = (double) connection.getContentLengthLong();
            System.out.println("Size in the web " + file_size_online);


            File file1 = new File("/Users/Ira/Pictures/2/productpic2atommug_1024x1024.jpg");
            file1.exists();
            double file_size_in_folder = file1.length();
            System.out.println("File size " + file_size_in_folder);

            if (file_size_in_folder == file_size_online) {
                System.out.println("File is fully downloaded");
            } else System.out.println("File is corrupted");
        }

    }

    public static void main(String[] args) throws IOException {
        downloadImage(directory, image_url);
        System.out.println("DONE");
    }
}
