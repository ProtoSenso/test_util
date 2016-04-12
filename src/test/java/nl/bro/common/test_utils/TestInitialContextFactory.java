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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class TestInitialContextFactory implements InitialContextFactory {

    private static Context context = mock( Context.class );

    public static void setEntry(String lookup, Object object) {
        try {
            when( context.lookup( lookup ) ).thenReturn( object );
        }
        catch ( NamingException ex ) {
            System.err.println( ex.toString() );
        }
    }

    @Override
    public Context getInitialContext(Hashtable<?, ?> arg0) throws NamingException {
        return context;
    }
}
