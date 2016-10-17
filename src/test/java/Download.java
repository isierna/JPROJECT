
import java.io.*;
import java.net.URL;

/**
 * Created by Ira on 10/17/16.
 */
public class Download {
    static String directory = "/Users/Ira/Pictures/2/";
    static String image_url = "https://cdn.shopify.com/s/files/1/0051/4802/products/productpic2atommug_1024x1024.jpg?v=1448907134";

    public static void downloadImage() throws IOException {
        new File(directory).mkdir();

        URL url = new URL(image_url);
        String file = url.getFile();
        String destination = directory + file.substring(file.lastIndexOf("/"), file.indexOf("?"));

        InputStream inputStream = url.openStream();
        OutputStream outputStream = new FileOutputStream(destination);

        byte[] bt = new byte[2048];
        int length;

        while ((length = inputStream.read(bt)) != -1) {
            outputStream.write(bt,0,length);
        }

        inputStream.close();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        downloadImage();
        System.out.println("DONE");
    }
}
