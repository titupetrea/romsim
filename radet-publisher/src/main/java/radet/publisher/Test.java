/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radet.publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Titu
 */
public class Test {

    public static void main(String[] args) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir"), "Opriri_programate.pdf");

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Calendar ifModifiedSince = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
        ifModifiedSince.setTimeInMillis(file.lastModified());
        System.out.println(sdf.format(ifModifiedSince.getTime()));
        System.out.println(ifModifiedSince.getTime());

        URL url = new URL("http://radet.ro/opriri/Opriri_programate.pdf");
        URLConnection connection = url.openConnection();
//        connection.setRequestProperty("If-Modified-Since", "Wed, 10 Aug 2016 20:35:57 GMT");

        String ifModifiedSinceString = sdf.format(ifModifiedSince.getTime());
        connection.setRequestProperty("If-Modified-Since", sdf.format(ifModifiedSince.getTime()));
        System.out.println("If-Modified-Since " + ifModifiedSinceString);

        try (InputStream urlInputStream = connection.getInputStream()) {
            try (OutputStream pdfOutputStream = new FileOutputStream(file)) {
                byte[] bytes = IOUtils.toByteArray(urlInputStream);
                System.out.println("#bytes " + bytes.length);
                IOUtils.write(bytes, pdfOutputStream);
            }
        }
        Calendar lastModified = Calendar.getInstance();
        lastModified.setTimeInMillis(connection.getLastModified());
        System.out.println(sdf.format(lastModified.getTime()));
        System.out.println(file.getAbsolutePath());
        System.out.println(connection.getContentLengthLong());
    }
}
