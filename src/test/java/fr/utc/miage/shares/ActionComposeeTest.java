/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ActionComposeeTest {

    private ActionComposee bqInno;
    private ActionSimple axa;
    private ActionSimple bnp;

    
    void initial() {
        bqInno = new ActionComposee("Banque Innovation");
        axa = new ActionSimple("AXA");
        bnp = new ActionSimple("BNP Paribas");
    }

    @Test
    void testCreationActionComposee() {
        initial();
        
        //Sous-problème 1 : Vérifier que l'action composée est bien créée avec son libellé
        assertEquals("Banque Innovation", bqInno.getLibelle(), "Le libellé doit correspondre à celui passé au constructeur");
        assertTrue(bqInno.getMapPanier().isEmpty(), "Le panier doit être vide à la création");
    }

    @Test
    void testEnrgComposition_AjoutActionSimple() {
        initial();
        
        //Sous-problème 2 : L'administrateur ajoute des actions simples dans l'action composée
        bqInno.enrgComposition(axa, 0.4f); // 40% d'AXA
        bqInno.enrgComposition(bnp, 0.6f); // 60% de BNP

        //Vérifications
        assertEquals(2, bqInno.getMapPanier().size(), "L'action composée devrait contenir 2 actions");
        assertTrue(bqInno.getMapPanier().containsKey(axa), "Le panier doit contenir l'action AXA");
        assertEquals(0.4f, bqInno.getMapPanier().get(axa), "La proportion d'AXA doit être de 40%");
    }
    
    @Test
    void testEnrgComposition_MiseAJourProportion() {
        initial();
        
        // 2. Sous-problème 3 : Modifier la proportion d'une action existante dans le panier
        bqInno.enrgComposition(axa, 0.4f);
        bqInno.enrgComposition(axa, 0.5f); // L'administrateur se ravise et passe à 50%

        // 3. Vérifications
        assertEquals(1, bqInno.getMapPanier().size(), "Il ne doit toujours y avoir qu'une seule action");
        assertEquals(0.5f, bqInno.getMapPanier().get(axa), "La proportion doit avoir été mise à jour à 50%");
    }

    @Test
    void testValeur() {
        initial();
        
        // 2. Création d'un jour de test
        // (Adaptez les paramètres du constructeur selon votre classe Jour, ex: new Jour(2026, 3, 30))
        Jour jourTest = new Jour(2026,1); 
        
        // 3. Définition du cours des actions simples pour ce jour précis
        // (Adaptez le nom de la méthode 'enrgCours' si elle s'appelle autrement dans votre ActionSimple)
        axa.enrgCours(jourTest, 100.0f); // L'action AXA vaut 100€ ce jour-là
        bnp.enrgCours(jourTest, 50.0f);  // L'action BNP vaut 50€ ce jour-là
        
        // 4. Définition de la composition de notre panier "Banque Innovation"
        bqInno.enrgComposition(axa, 0.4f); // 40% d'AXA
        bqInno.enrgComposition(bnp, 0.6f); // 60% de BNP
        
        // 5. Calcul attendu (sur papier) :
        // (100€ * 0.4) + (50€ * 0.6) = 40€ + 30€ = 70€
        float valeurAttendue = 70.0f;
        
        // 6. Assertion : On vérifie que la méthode valeur(jourTest) renvoie bien 70.0f
        assertEquals(valeurAttendue, bqInno.valeur(jourTest), "La valeur de l'action composée doit être la somme pondérée du cours de ses composants");
    }

    @Test
    void testEqualsComplet() {
        ActionComposee action1 = new ActionComposee("Panier A");
        ActionComposee action2 = new ActionComposee("Panier A");
        ActionComposee action3 = new ActionComposee("Panier B");
        
        // --- TRUE ---
        assertEquals(action1, action1);
        assertEquals(action1, action2);

        // --- FALSE ---
        assertNotEquals(action1, action3);
        assertNotEquals(null, action1);
        assertNotEquals(new Object(), action1);
    }

    @Test
    void testEqualsCoverageCasLimites() {
        ActionComposee actionComposee = new ActionComposee("Total");
        ActionSimple actionSimple = new ActionSimple("Total");

        boolean isEqualToNull = actionComposee.equals(null);
        assertFalse(isEqualToNull, "Une action ne doit pas être égale à null");

        boolean isEqualToDifferentClass = actionComposee.equals(actionSimple);
        assertFalse(isEqualToDifferentClass, "Une ActionComposee ne doit pas être égale à une ActionSimple");
    }

    @Test
    void testHashCode() {
        ActionComposee action1 = new ActionComposee("Total");
        ActionComposee action2 = new ActionComposee("Total");
        assertEquals(action1.hashCode(), action2.hashCode(), "Deux ActionComposee identiques doivent avoir le même hashCode");
    }
}