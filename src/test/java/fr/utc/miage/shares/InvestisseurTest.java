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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class InvestisseurTest {

    public static final String FIRST_NAME = "Dupont";
    public static final String LAST_NAME = "Jean";
    public static final String EMAIL = "user1@gmail.com";
    public static final String PASSWORD = "password123";

    public static final String NEW_FIRST_NAME = "Martin";
    public static final String NEW_LAST_NAME = "Paul";
    public static final String NEW_PASSWORD = "password456";

    public static final String INVALID_EMAIL = "invalid.email.com";
    public static final String EXISTING_EMAIL = "existant@gmail.com";

    public static Investisseur getDefaultInvestisseur() {
        return new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
    }


    @Test
    void teseConstructeur() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
                "Cree un investisseur avec des champs valides",
                ()-> assertNotNull(investisseur),
                ()-> assertEquals("Dupont", investisseur.getNom()),
                ()-> assertEquals("Jean", investisseur.getPrenom()),
                ()-> assertEquals("1@gmail.com", investisseur.getEmail())
        );
    }

    @Test
    void teseConstructeurThrowsWithIncorrectValues() {
        assertAll(
                "Cree un investisseur avec des champs valides",
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(null, "Jean", "1@gmail.com", "password123")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur("", "Jean", "1@gmail.com", "password123")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur("Dupont", null, "1@gmail.com", "password123")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur("Dupont", "", "1@gmail.com", "password123"))
        );
    }


     /**
     * Tests the constructor of the Investisseur class to ensure it creates an instance without throwing exceptions.
     */
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new Investisseur("Dupont", "Jean", "1@gmail.com", "password123"));
    }


    @Test
    void testGetters() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Verifie les getters",
            ()-> assertEquals(FIRST_NAME, investisseur.getNom()),
            ()-> assertEquals(LAST_NAME, investisseur.getPrenom()),
            ()-> assertEquals(EMAIL, investisseur.getEmail())
        );
    }

    @Test
    void testSetters() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        investisseur.setNom(NEW_FIRST_NAME);
        investisseur.setPrenom(NEW_LAST_NAME);
        investisseur.setPassword(NEW_PASSWORD);
        assertAll(
                "Verifie les setters",
                ()-> assertEquals(NEW_FIRST_NAME, investisseur.getNom()),
                ()-> assertEquals(NEW_LAST_NAME, investisseur.getPrenom()),
                ()-> assertTrue(investisseur.verifierMotDePasse(NEW_PASSWORD))
        );
    }

    @Test
    void testSettersWithIncorrectValueThrows() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
                "Verifie que les setters throws",
                ()-> assertThrows(IllegalArgumentException.class, () -> investisseur.setNom(null)),
                ()-> assertThrows(IllegalArgumentException.class, () -> investisseur.setNom("")),
                ()-> assertThrows(IllegalArgumentException.class, () -> investisseur.setPrenom(null)),
                ()-> assertThrows(IllegalArgumentException.class, () -> investisseur.setPrenom(""))
        );
    }

    @Test
    void testToString() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Verifie le toString",
            ()-> assertEquals("Investisseur [nom=" + FIRST_NAME + ", prenom=" + LAST_NAME + ", email=" + EMAIL + "]", investisseur.toString())

        );
    }

     /**
     * Tests the buy method of the Investor class to ensure it throws an IllegalArgumentException when a negative quantity is provided.
     */
    @Test
    void testBuyNegativeQuantity() {
        Action action = ActionSimpleTest.getDefaultActionSimple();
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertThrows(IllegalArgumentException.class, () -> investisseur.buy(action, 0));
    }

    @Test
    void testBuy(){
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        Action action  = ActionSimpleTest.getDefaultActionSimple();
        assertDoesNotThrow(() -> investisseur.buy(action, 1));
    }

    @Test
    void testBuyNullAction(){
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertThrows(IllegalArgumentException.class, () -> investisseur.buy(null, 1));
    }
}
