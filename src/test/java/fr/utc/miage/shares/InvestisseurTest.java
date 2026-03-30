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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.utc.miage.Investisseur;

class InvestisseurTest {
    @Test
    void teseConstructeur() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Cree un investisseur avec des champs valides",
            ()-> assertNotNull(investisseur),
            ()-> assertEquals("Dupont", investisseur.getNom()),
            ()-> assertEquals("Jean", investisseur.getPrenom()),
            ()-> assertEquals("1@gmail.com", investisseur.getEmail()),
            ()-> assertEquals("password123", investisseur.getPassword())

        );
    }


    @Test
    void testGetters() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Verifie les getters",
            ()-> assertEquals("Dupont", investisseur.getNom()),
            ()-> assertEquals("Jean", investisseur.getPrenom()),
            ()-> assertEquals("1@gmail.com", investisseur.getEmail()),
            ()-> assertEquals("password123", investisseur.getPassword())
        );
    }

    @Test
    void testSetters() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        investisseur.setNom("Martin");
        investisseur.setPrenom("Paul");
        investisseur.setPassword("password456");
        assertAll(
            "Cree un ",
            ()-> assertEquals("Martin", investisseur.getNom()),
            ()-> assertEquals("Paul", investisseur.getPrenom()),
            ()-> assertEquals("password456", investisseur.getPassword())
        );
    }

    @Test
    void testToString() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Verifie le toString",
            ()-> assertEquals("Investisseur [nom=Dupont, prenom=Jean, email=1@gmail.com, password=password123]", investisseur.toString())

        );
    }

    @Test
    void testCreerInvestisseurValide() {
        Investisseur investisseur = Investisseur.creerInvestisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Cree un ",
            ()-> assertNotNull(investisseur),
            ()-> assertEquals("Dupont", investisseur.getNom()),
            ()-> assertEquals("Jean", investisseur.getPrenom()),
            ()-> assertEquals("1@gmail.com", investisseur.getEmail()),
            ()-> assertEquals("password123", investisseur.getPassword())

        );
    }

    @Test 
    void testCreerInvestisseurEmailInvalide() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> Investisseur.creerInvestisseur("Dupont", "Jean", "emailSansArobase.com", "password123")
        );
        assertEquals("L'email n'est pas valide", exception.getMessage());
    }

    @Test 
    void testCreerInvestisseurChampsNuls() {
        assertAll(
            "Cree un investisseur avec des champs nuls",
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur(null, "Jean", "1@gmail.com", "password123")),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur("Dupont", null, "1@gmail.com", "password123")),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur("Dupont", "Jean", null, "password123")),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur("Dupont", "Jean", "1@gmail.com", null))
        );
    }

    @Test 
    void testCreerInvestisseurEmailExistant() {
        Investisseur.creerInvestisseur("Dupont", "Jean", "existant@gmail.com", "password123");
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> Investisseur.creerInvestisseur("Martin", "Paul", "existant@gmail.com", "mdp123")
        );
        assertEquals("L'email existe déjà", exception.getMessage());
    }
}
