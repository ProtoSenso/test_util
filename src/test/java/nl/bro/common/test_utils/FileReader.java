/**
 * Copyright 2012-2014 TNO Geologische Dienst Nederland
 *
 * Alle rechten voorbehouden.
 * Niets uit deze software mag worden vermenigvuldigd en/of openbaar gemaakt door middel van druk, fotokopie,
 * microfilm of op welke andere wijze dan ook, zonder voorafgaande toestemming van TNO.
 *
 * Indien deze software in opdracht werd uitgebracht, wordt voor de rechten en verplichtingen van opdrachtgever
 * en opdrachtnemer verwezen naar de Algemene Voorwaarden voor opdrachten aan TNO, dan wel de betreffende
 * terzake tussen de partijen gesloten overeenkomst.
 */
package nl.bro.common.test_utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author T.V.C.S. Tseng
 */
public class FileReader {

    private FileReader() {
        // never to be used
    }

    public static byte[] read(String fileName) throws IOException {
        String path = FileReader.class.getClassLoader().getResource( fileName ).getFile();
        File file = new File( path );
        fileName = file.getName();
        return FileReader.read( file );
    }

    private static byte[] read(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream( file );
            int read = 0;
            while ( ( read = ios.read( buffer ) ) != -1 ) {
                ous.write( buffer, 0, read );
            }
        }
        finally {
            try {
                if ( ous != null ) {
                    ous.close();
                }
            }
            catch ( IOException e ) {
                System.err.println( e.toString() );
            }

            try {
                if ( ios != null ) {
                    ios.close();
                }
            }
            catch ( IOException e ) {
                System.err.println( e.toString() );
            }
        }
        return ous.toByteArray();
    }
}
