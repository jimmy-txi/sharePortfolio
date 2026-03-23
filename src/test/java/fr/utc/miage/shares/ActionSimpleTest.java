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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionSimpleTest {

    private static final String FOO_SHARE1 = "Foo Share 1";
    private static final String FOO_SHARE2 = "Foo Share 2";

    @Test
    void testEnregistreActionReussir() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour = new Jour(2026, 76);
        assertAll(
                "Enregistrer une Action Pas Réussir",
                () -> assertDoesNotThrow(() -> action.enrgCours(jour, 150.00f)),
                () -> assertEquals(150.00f, action.valeur(jour))
        );
    }

    @Test
    void testEnregistrerJourAfterTodayThrowException() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final LocalDate dateAfterToday = LocalDate.now().plusDays(10);
        final Jour jourAfterToday = new Jour(dateAfterToday.getYear(), dateAfterToday.getDayOfYear());
        assertThrows(IllegalArgumentException.class, () -> action.enrgCours(jourAfterToday, 150.00f), "Make sure if input a date after today throw an Exception");
    }

    @Test
    void testEnregistrerJourExistantThrowException() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour = new Jour(2026, 76);
        action.enrgCours(jour, 150.00f);
        assertThrows(IllegalArgumentException.class, () -> action.enrgCours(jour, 150.00f), "Make sure do not save same date");

    }

    @Test
    void testSaveActionValueNegative() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour = new Jour(2026, 76);
        final float value = -100f;
        assertThrows(IllegalArgumentException.class, () -> action.enrgCours(jour, value));
    }

    @Test
    void testConsultationPrixInexistantRetourner0() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour = new Jour(2026, 76);
        assertDoesNotThrow(() -> action.valeur(jour));
        assertEquals(0, action.valeur(jour));
    }

    @Test
    void testEquals() {
        final ActionSimple action1 = new ActionSimple(FOO_SHARE1);
        final ActionSimple action2 = new ActionSimple(FOO_SHARE1);
        final ActionSimple action3 = new ActionSimple(FOO_SHARE2);

        assertAll(
                "ActionSimple equality should depend on the label",
                () -> assertEquals(action1, action2),
                () -> assertNotEquals(action1, action3),
                () -> assertNotEquals(null, action1),
                () -> assertNotEquals(action1, new Jour(2026, 76))
        );
    }

    @Test
    void testHashCode() {
        final ActionSimple action1 = new ActionSimple(FOO_SHARE1);
        final ActionSimple action2 = new ActionSimple(FOO_SHARE1);

        assertAll(
                "ActionSimple hashCode should be stable and consistent with equals",
                () -> assertDoesNotThrow(action1::hashCode),
                () -> assertEquals(action1.hashCode(), action2.hashCode())
        );
    }
}
