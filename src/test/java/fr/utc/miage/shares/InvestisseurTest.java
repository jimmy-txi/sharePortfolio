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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;




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
            ()-> assertEquals(EMAIL, investisseur.getEmail()),
            ()-> assertEquals(PASSWORD, investisseur.getPassword())
        );
    }

    @Test
    void testSetters() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        investisseur.setNom(NEW_FIRST_NAME);
        investisseur.setPrenom(NEW_LAST_NAME);
        investisseur.setPassword(NEW_PASSWORD);
        assertAll(
            "Cree un ",
            ()-> assertEquals(NEW_FIRST_NAME, investisseur.getNom()),
            ()-> assertEquals(NEW_LAST_NAME, investisseur.getPrenom()),
            ()-> assertEquals(NEW_PASSWORD, investisseur.getPassword())
        );
    }

    @Test
    void testToString() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Verifie le toString",
            ()-> assertEquals("Investisseur [nom=Dupont, prenom=Jean, email=user1@gmail.com, password=password123]", investisseur.toString())

        );
    }

    @Test
    void testCreerInvestisseurValide() {
        Investisseur investisseur = Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Cree un ",
            ()-> assertNotNull(investisseur),
            ()-> assertEquals(FIRST_NAME, investisseur.getNom()),
            ()-> assertEquals(LAST_NAME, investisseur.getPrenom()),
            ()-> assertEquals(EMAIL, investisseur.getEmail()),
            ()-> assertEquals(PASSWORD, investisseur.getPassword())

        );
    }

    @Test 
    void testCreerInvestisseurEmailInvalide() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, INVALID_EMAIL, PASSWORD)
        );
        assertEquals("L'email n'est pas valide", exception.getMessage());
    }

    @Test 
    void testCreerInvestisseurChampsNuls() {
        assertAll(
            "Cree un investisseur avec des champs nuls",
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur(null, LAST_NAME, EMAIL, PASSWORD)),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur(FIRST_NAME, null, EMAIL, PASSWORD)),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, null, PASSWORD)),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, EMAIL, null))
        );
    }

    @Test 
    void testCreerInvestisseurEmailExistant() {
        Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, EXISTING_EMAIL, PASSWORD);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> Investisseur.creerInvestisseur(NEW_FIRST_NAME, NEW_LAST_NAME, EXISTING_EMAIL, NEW_PASSWORD)
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

    @Test
    void testResetPasswordEmailInexistant() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", null, "password123");
        assertThrows(IllegalArgumentException.class, investisseur::resetPassword);
    }

    @Test
    void testResetPasswordValide() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        String newPassword = investisseur.resetPassword();
        assertNotEquals("password123", newPassword);
    }

    @Test
    void testDeleteInvestisseurExisting() {
        Investisseur.creerInvestisseur("Dupont", "Jean", "1@gmail.com", "password123");
        assertAll(
            "Supprime un investisseur existant",
            ()-> assertDoesNotThrow(() -> Investisseur.deleteInvestisseur("1@gmail.com"))
        );
    }


    @Test
    void testDeleteInvestisseurEmailNotExistingOrNull() {
        assertAll(
            "Supprime un investisseur avec des champs non existants",
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.deleteInvestisseur("emailNonExistant@gmail.com")),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.deleteInvestisseur(null))
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
