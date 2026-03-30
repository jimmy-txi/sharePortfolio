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
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MarcheTest {

    private Marche marche;
    private ActionSimple axa;
    private ActionSimple total;

    /**
     * Initialisation manuelle pour les tests
     */
    void setUp() {
        marche = new Marche();
        axa = new ActionSimple("AXA");
        total = new ActionSimple("TotalEnergies");
    }

    /**
     * [Test]:Affichage de la liste [pas d'actions] #27
     */
    @Test
    void testAffichageListePasDactions() {
        setUp(); // On initialise un marché vide

        // Vérification 1 : La liste est bien vide
        assertTrue(marche.getActionsDisponibles().isEmpty(), "La liste doit être vide");

        // Vérification 2 : Le message correspond à un marché vide
        String affichage = marche.afficherActionsDisponibles();
        assertEquals("Aucune action n'est disponible sur le marché pour le moment.", affichage);
    }

    /**
     * [Test]: Affichage de la liste des actions #29
     */
    @Test
    void testAffichageListeDesActions() {
        setUp(); // On initialise le marché et les actions

        // L'administrateur ajoute uniquement des actions simples au marché
        marche.ajouterAction(axa);
        marche.ajouterAction(total);

        // Vérification 1 : La liste contient bien les 2 actions
        assertEquals(2, marche.getActionsDisponibles().size(), "Le marché doit contenir 2 actions");

        // Vérification 2 : L'affichage contient bien les noms des actions
        String affichage = marche.afficherActionsDisponibles();
        assertTrue(affichage.contains("AXA"), "L'affichage doit contenir AXA");
        assertTrue(affichage.contains("TotalEnergies"), "L'affichage doit contenir TotalEnergies");
    }
}