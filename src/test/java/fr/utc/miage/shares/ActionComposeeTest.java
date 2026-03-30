package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ActionComposeeTest {

    private ActionComposee bqInno;
    private ActionSimple axa;
    private ActionSimple bnp;

    
    void setUp() {
        bqInno = new ActionComposee("Banque Innovation");
        axa = new ActionSimple("AXA");
        bnp = new ActionSimple("BNP Paribas");
    }

    @Test
    void testCreationActionComposee() {
        // 1. Appel manuel de l'initialisation
        setUp();
        
        // 2. Sous-problème 1 : Vérifier que l'action composée est bien créée avec son libellé
        assertEquals("Banque Innovation", bqInno.getLibelle(), "Le libellé doit correspondre à celui passé au constructeur");
        assertTrue(bqInno.getMapPanier().isEmpty(), "Le panier doit être vide à la création");
    }

    @Test
    void testEnrgComposition_AjoutActionSimple() {
        // 1. Appel manuel de l'initialisation
        setUp();
        
        // 2. Sous-problème 2 : L'administrateur ajoute des actions simples dans l'action composée
        bqInno.enrgComposition(axa, 0.4f); // 40% d'AXA
        bqInno.enrgComposition(bnp, 0.6f); // 60% de BNP

        // 3. Vérifications
        assertEquals(2, bqInno.getMapPanier().size(), "L'action composée devrait contenir 2 actions");
        assertTrue(bqInno.getMapPanier().containsKey(axa), "Le panier doit contenir l'action AXA");
        assertEquals(0.4f, bqInno.getMapPanier().get(axa), "La proportion d'AXA doit être de 40%");
    }
    
    @Test
    void testEnrgComposition_MiseAJourProportion() {
        // 1. Appel manuel de l'initialisation
        setUp();
        
        // 2. Sous-problème 3 : Modifier la proportion d'une action existante dans le panier
        bqInno.enrgComposition(axa, 0.4f);
        bqInno.enrgComposition(axa, 0.5f); // L'administrateur se ravise et passe à 50%

        // 3. Vérifications
        assertEquals(1, bqInno.getMapPanier().size(), "Il ne doit toujours y avoir qu'une seule action");
        assertEquals(0.5f, bqInno.getMapPanier().get(axa), "La proportion doit avoir été mise à jour à 50%");
    }
}