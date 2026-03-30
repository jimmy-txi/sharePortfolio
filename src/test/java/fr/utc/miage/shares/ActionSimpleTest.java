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

public class ActionSimpleTest {

    public static ActionSimple getDefaultActionSimple(){
        return new ActionSimple("Action1");
    }
    private static final String FOO_SHARE1 = "Foo Share 1";
    private static final String FOO_SHARE2 = "Foo Share 2";
    private static final Jour jourTest = new Jour(2026,76);

    // for method enrgCours()
    @Test
    void testEnregistreActionReussir() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        assertAll(
                "Enregistrer une Action Pas Réussir",
                () -> assertDoesNotThrow(() -> action.enrgCours(jourTest, 150.00f)),
                () -> assertEquals(150.00f, action.valeur(jourTest))
        );
    }

    // for method enrgCours()
    @Test
    void testEnregistrerJourAfterTodayThrowException() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final LocalDate dateAfterToday = LocalDate.now().plusDays(10);
        final Jour jourAfterToday = new Jour(dateAfterToday.getYear(), dateAfterToday.getDayOfYear());
        assertThrows(IllegalArgumentException.class, () -> action.enrgCours(jourAfterToday, 150.00f), "Make sure if input a date after today throw an Exception");
    }

    // for method enrgCours()
    @Test
    void testEnregistrerJourExistantThrowException() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        action.enrgCours(jourTest, 150.00f);
        assertThrows(IllegalArgumentException.class, () -> action.enrgCours(jourTest, 150.00f), "Make sure do not save same date");

    }

    // for method enrgCours()
    @Test
    void testSaveActionValueNegative() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final float value = -100f;
        assertThrows(IllegalArgumentException.class, () -> action.enrgCours(jourTest, value));
    }

    @Test
    void testConsultationPrixInexistantRetourner0() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        assertDoesNotThrow(() -> action.valeur(jourTest));
        assertEquals(0, action.valeur(jourTest));
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
                () -> assertNotEquals(null, action1)
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

    @Test
    void testGetEvolutionCours() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour1 = new Jour(2026, 1);
        final Jour jour2 = new Jour(2026, 2);

        action.enrgCours(jour1, 1000.0f);
        action.enrgCours(jour2, 900.0f);

        assertEquals(-10.0f, action.getEvolutionCours(jour1, jour2), 0.01f);
    }

    @Test
    void testGetEvolutionCoursWithInvalidDates() {
        final ActionSimple action = new ActionSimple(FOO_SHARE1);
        final Jour jour1 = new Jour(2026, 1);
        final Jour jour2 = new Jour(2026, 2);

        action.enrgCours(jour1, 100.0f);

        assertAll(
                "getEvolutionCours should throw an exception for invalid dates",
                () -> assertThrows(IllegalArgumentException.class, () -> action.getEvolutionCours(jour1, new Jour(2026, 3))),
                () -> assertThrows(IllegalArgumentException.class, () -> action.getEvolutionCours(new Jour(2026, 3), jour2)),
                () -> assertThrows(IllegalArgumentException.class, () -> action.getEvolutionCours(new Jour(2026, 3), new Jour(2026, 4)))
        );
    }
}
