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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionSimpleTest {

    public static ActionSimple getDefaultActionSimple(){
        return new ActionSimple("Action1");
    }

    @Test
    void testEnregistreActionReussir() {
        final ActionSimple action = getDefaultActionSimple();
        final Jour jour = new Jour(2026,76);
        assertAll(
                "Enregistrer une Action Pas Réussir",
                () -> assertDoesNotThrow(() -> action.enrgCours(jour,150.00f)),
                () -> assertEquals(150.00f,action.valeur(jour))
        );
    }

    @Test
    void testConsultationPrixInexistantRetourner0(){
        final ActionSimple action = getDefaultActionSimple();
        final Jour jour = new Jour(2026,76);
        assertDoesNotThrow(() -> action.valeur(jour));
        assertEquals(0,action.valeur(jour));
    }

}
