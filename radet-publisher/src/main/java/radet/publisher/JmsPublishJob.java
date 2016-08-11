/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radet.publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Titu
 */
public class JmsPublishJob {

    private static final String PDF_FILE_NAME = "Opriri_programate.pdf";
    private static final File PDF_FILE = new File(System.getProperty("java.io.tmpdir"), PDF_FILE_NAME);

    private static final String TXT_FILE_NAME = "Opriri_programate.txt";
    private static final File TXT_FILE = new File(System.getProperty("java.io.tmpdir"), TXT_FILE_NAME);

    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
        if (TXT_FILE.exists() == false && TXT_FILE.createNewFile() == false) {
            throw new IOException("Cannot create new file " + TXT_FILE);
        }

        if (downloadPdfIfModifiedSince(PDF_FILE.lastModified()) == false) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(PDF_FILE.lastModified());
            System.out.println("return, pdf file not modified since " + c.getTime());
            return;
        }

        ThermalAgentStoppages oldStoppages = PdfParser.parse(IOUtils.toString(new FileReader(TXT_FILE)));

        extractText();

        ThermalAgentStoppages newStoppages = PdfParser.parse(IOUtils.toString(new FileReader(TXT_FILE)));

        for (ThermalAgentStoppage newStoppage : newStoppages.getStoppages()) {
            for (ThermalAgentStoppage oldStoppage : oldStoppages.getStoppages()) {
                if (newStoppage.hasChanged(oldStoppage)) {
                    
                }
            }
        }
    }

    private static boolean downloadPdfIfModifiedSince(long modifiedSince) throws IOException, MalformedURLException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar ifModifiedSince = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
        ifModifiedSince.setTimeInMillis(modifiedSince);

        URL url = new URL("http://radet.ro/opriri/Opriri_programate.pdf");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("If-Modified-Since", sdf.format(ifModifiedSince.getTime()));
        try (InputStream urlInputStream = connection.getInputStream()) {
            try (OutputStream pdfOutputStream = new FileOutputStream(PDF_FILE)) {
                IOUtils.write(IOUtils.toByteArray(urlInputStream), pdfOutputStream);
            }
        }
        return connection.getContentLengthLong() > 0;
    }

    private static void extractText() throws InterruptedException, IOException {
        List<String> command = new ArrayList();
        command.add("java");
        command.add("-jar");
        command.add("target/dependency/pdfbox-app-2.0.0.jar");
        command.add("ExtractText");
        command.add("-encoding");
        command.add("UTF-8");
        command.add(PDF_FILE.getAbsolutePath());
        command.add(TXT_FILE.getAbsolutePath());
        ProcessBuilder pb = new ProcessBuilder(command);
        final Process p = pb.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int c;
                    while ((c = p.getErrorStream().read()) != -1) {
                        System.err.print(c);
                    }
                } catch (IOException ex) {
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int c;
                    while ((c = p.getInputStream().read()) != -1) {
                        System.out.print(c);
                    }
                } catch (IOException ex) {
                }
            }
        }).start();
        p.waitFor();
    }
}
