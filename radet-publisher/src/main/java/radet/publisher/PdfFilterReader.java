/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radet.publisher;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Titu
 */
public class PdfFilterReader extends FilterReader {

    public PdfFilterReader(Reader in) {
        super(in);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return super.read(cbuf, off, len);
    }

    @Override
    public int read() throws IOException {
        return super.read();
    }

    private char transform(char c) {
        switch (c) {
            case 'Ń':
                return 't';
            case 'ł':
                return 'S';
        }
        return c;
    }
}
