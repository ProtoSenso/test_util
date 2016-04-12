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

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author T.V.C.S. Tseng
 */
public class MockingFrame {

    private MockingFrame() {
        // not needed
    }

    private static final Map<String, String> JNDI_NAMES_FOR_INTERFACE = ImmutableMap.<String, String> builder()
        .put( "nl.bro.intakebatchregister.logic.IntakeBatchRegister", "ejb:ibr-ear/ibr-ejb/IntakeBatchRegisterImpl!nl.bro.intakebatchregister.logic.IntakeBatchRegister" )
        .put( "nl.bro.common.transactionregister.TransactionRegister", "ejb:tr-ear/tr-ejb/TransactionRegisterImpl!nl.bro.common.transactionregister.TransactionRegister" )
        .put( "nl.bro.common.oamregister.service.OrganisationsAndMandatesRegister", "ejb:omr-ear/omr-ejb/OrganisationsAndMandatesRegisterImpl!nl.bro.common.oamregister.service.OrganisationsAndMandatesRegister" )
        .put( "nl.bro.ss.cpt.ip.logic.IpCptLogic", "java:app/ip-cpt-ejb/IpCptLogicBean!nl.bro.ss.cpt.ip.logic.IpCptLogic" )
    .build();

    private static String theirSavedCtx;

    public static void beforeClass() {
        // backup
        theirSavedCtx = System.getProperty( "java.naming.factory.initial" );
        // mock context
        System.setProperty( "java.naming.factory.initial", TestInitialContextFactory.class.getCanonicalName() );
    }

    public static void afterClass() {
        // restore
        if ( theirSavedCtx != null ) {
            System.setProperty( "java.naming.factory.initial", theirSavedCtx );
        }
    }

    /**
     * create a JNDI mock for a 'known' interface
     *
     * @param clazzToMock
     * @return
     */
    public static <T> T registerAndMock(Class<T> clazzToMock) {
        return registerAndMock( clazzToMock, JNDI_NAMES_FOR_INTERFACE.get( clazzToMock.getName() ) );
    }

    /**
     * create a JNDI mock for an 'internal' interface
     *
     * @param clazzToMock
     * @return
     */

    public static <T> T registerAndMock(Class<T> clazzToMock, String name) {
        T res = mock( clazzToMock );
        TestInitialContextFactory.setEntry( name, res );
        return res;
    }
}
