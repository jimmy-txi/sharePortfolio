package fr.utc.miage.shares;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionSimpleTest {

    private static final String FOO_SHARE1 = "Foo Share 1";

    @Test
    void testEnregistreActionReussir() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour = new Jour(2026,76);
        assertAll(
                "Enregistrer une Action Pas Réussir",
                () -> assertDoesNotThrow(() -> action.enrgCours(jour,150.00f)),
                () -> assertEquals(150.00f,action.valeur(jour))
        );
    }

    @Test
    void testConsultationPrixInexistantRetourner0(){
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour = new Jour(2026,76);
        assertDoesNotThrow(() -> action.valeur(jour));
        assertEquals(0,action.valeur(jour));
    }

}
