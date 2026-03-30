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
package fr.utc.miage;

import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    private final Investisseur investisseurTest = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
    private final Action actionTestForSale1 = new ActionSimple("ActionTestForSale1");
    private final Action actionTestForSale2 = new ActionSimple("ActionTestForSale2");
    private final Action actionTestForBuy1 = new ActionSimple("ActionTestForBuy1");
    private final Action actionTestForBuy2 = new ActionSimple("ActionTestForBuy2");

    @Test
    void getTransactionsSale() {
        assertDoesNotThrow(investisseurTest::getTransactionsSale);
    }

    @Test
    void addTransactionSale() {
        assertDoesNotThrow(()->investisseurTest.addTransactionSale(actionTestForSale1));
    }

    @Test
    void getTransactionsBuy() {
        assertDoesNotThrow(investisseurTest::getTransactionsBuy);
    }

    @Test
    void addTransactionBuy() {
        assertDoesNotThrow(()->investisseurTest.addTransactionSale(actionTestForBuy1));
    }

    // get all transactions history
    // [US]: Historique Transactions #3
    // [Test]: Consulter l'historique des transactions avec des données existantes #64
    @Test
    void getTransactionsHistory() {
        investisseurTest.addTransactionSale(actionTestForSale1);
        investisseurTest.addTransactionSale(actionTestForSale2);
        investisseurTest.addTransactionBuy(actionTestForBuy1);
        investisseurTest.addTransactionBuy(actionTestForBuy2);
        assertAll(
            "Verifie l'historique des transactions",
            ()-> assertEquals(actionTestForSale1, investisseurTest.getTransactionsHistory().get("sale").get(0)),
            ()-> assertEquals(actionTestForSale2, investisseurTest.getTransactionsHistory().get("sale").get(1)),
            ()-> assertEquals(actionTestForBuy1, investisseurTest.getTransactionsHistory().get("buy").get(0)),
            ()-> assertEquals(actionTestForBuy2, investisseurTest.getTransactionsHistory().get("buy").get(1))
        );
    }

    // [Test]: Affichage de l'historique des transactions lorsqu'il est vide #81
    @Test
    void getTransactionsHistoryEmpty() {
        assertAll(
            "Verifie l'historique des transactions",
            ()-> assertEquals(0, investisseurTest.getTransactionsHistory().get("sale").size()),
            ()-> assertEquals(0, investisseurTest.getTransactionsHistory().get("buy").size())
        );
    }
}
