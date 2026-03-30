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
import org.junit.jupiter.api.TestInstance;

import fr.utc.miage.Investisseur;

import static org.junit.jupiter.api.Assertions.*;

class InvestisseurTest {


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
                ()-> assertThrows(IllegalArgumentException.class, () -> new Investisseur(null, "Jean", "1@gmail.com", "password123")),
                ()-> assertThrows(IllegalArgumentException.class, () -> new Investisseur("", "Jean", "1@gmail.com", "password123")),
                ()-> assertThrows(IllegalArgumentException.class, () -> new Investisseur("Dupont", null, "1@gmail.com", "password123")),
                ()-> assertThrows(IllegalArgumentException.class, () -> new Investisseur("Dupont", "", "1@gmail.com", "password123"))
        );
    }


    @Test
    void testGetters() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Verifie les getters",
            ()-> assertEquals("Dupont", investisseur.getNom()),
            ()-> assertEquals("Jean", investisseur.getPrenom()),
            ()-> assertEquals("1@gmail.com", investisseur.getEmail())
        );
    }

    @Test
    void testSetters() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        investisseur.setNom("Martin");
        investisseur.setPrenom("Paul");
        investisseur.setPassword("password456");
        assertAll(
                "Verifie les setters",
                ()-> assertEquals("Martin", investisseur.getNom()),
                ()-> assertEquals("Paul", investisseur.getPrenom()),
                ()-> assertTrue(investisseur.verifierMotDePasse("password456"))
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
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Verifie le toString",
            ()-> assertEquals("Investisseur [nom=Dupont, prenom=Jean, email=1@gmail.com]", investisseur.toString())

        );
    }
}
