/*
 * Copyright 2025 David Navarre <David.Navarre at irit.fr>.
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestisseurTest {

    @BeforeEach
    void setUp() {
        Investisseur.clearInvestisseursMap();
    }

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
        return new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
    }

    @Test
    void testConstructeur() {
        assertDoesNotThrow(() -> new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD), "Constructor should not throw with valid parameters");
    }

    @Test
    void testConstructeurThrowsWithIncorrectValues() {
        assertAll(
                "Cree un investisseur avec des champs invalides",
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(null, LAST_NAME, EMAIL, PASSWORD)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur("", LAST_NAME, EMAIL, PASSWORD)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(FIRST_NAME, null, EMAIL, PASSWORD)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(FIRST_NAME, "", EMAIL, PASSWORD))
        );
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
        assertDoesNotThrow(()->investisseurTest.addTransactionSale(new Transaction(actionTestForSale1, new Jour(2026, 1), 10.0f)));
    }

    @Test
    void getTransactionsBuy() {
        assertDoesNotThrow(investisseurTest::getTransactionsBuy);
    }

    @Test
    void addTransactionBuy() {
        assertDoesNotThrow(()->investisseurTest.addTransactionBuy(new Transaction(actionTestForBuy1, new Jour(2026, 1), 15.0f)));
    }

    // get all transactions history
    // [US]: Historique Transactions #3
    // [Test]: Consulter l'historique des transactions avec des données existantes #64
    @Test
    void getTransactionsHistory() {
        Transaction tSale1 = new Transaction(actionTestForSale1, new Jour(2026, 1), 10.0f);
        Transaction tSale2 = new Transaction(actionTestForSale2, new Jour(2026, 2), 12.0f);
        Transaction tBuy1 = new Transaction(actionTestForBuy1, new Jour(2026, 3), 15.0f);
        Transaction tBuy2 = new Transaction(actionTestForBuy2, new Jour(2026, 4), 18.0f);

        investisseurTest.addTransactionSale(tSale1);
        investisseurTest.addTransactionSale(tSale2);
        investisseurTest.addTransactionBuy(tBuy1);
        investisseurTest.addTransactionBuy(tBuy2);

        assertAll(
            "Verifie l'historique des transactions",
            ()-> assertEquals(tSale1, investisseurTest.getTransactionsHistory().get("sale").get(0)),
            ()-> assertEquals(tSale2, investisseurTest.getTransactionsHistory().get("sale").get(1)),
            ()-> assertEquals(tBuy1, investisseurTest.getTransactionsHistory().get("buy").get(0)),
            ()-> assertEquals(tBuy2, investisseurTest.getTransactionsHistory().get("buy").get(1))
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
    void testResetPasswordValide() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        String newPassword = investisseur.resetPassword();
        assertNotEquals("password123", newPassword);
    }

    @Test
    void testSettersWithIncorrectValueThrows() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
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

    @Test
    void testBuyNegativeQuantity() {
        Action action = ActionSimpleTest.getDefaultActionSimple();
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> investisseur.buy(action, 0));
    }

    @Test
    void testBuy(){
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        Action action  = ActionSimpleTest.getDefaultActionSimple();
        assertDoesNotThrow(() -> investisseur.buy(action, 1));
    }

    @Test
    void testBuyNullAction(){
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> investisseur.buy(null, 1));
    }

    @Test
    void testConnexionFonctionne() {
        Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        Investisseur result = Investisseur.authentifier(EMAIL, PASSWORD);
        assertAll(
            "Connexion réussie avec des credentials valides",
            () -> assertNotNull(result),
            () -> assertEquals(EMAIL, result.getEmail())
        );
    }

    @Test
    void testConnexionRefusee() {
        Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Connexion refusée avec des credentials invalides",
            () -> assertThrows(IllegalArgumentException.class,
                () -> Investisseur.authentifier(EMAIL, NEW_PASSWORD)),
            () -> assertThrows(IllegalArgumentException.class,
                () -> Investisseur.authentifier(INVALID_EMAIL, PASSWORD)),
            () -> assertThrows(IllegalArgumentException.class,
                () -> Investisseur.authentifier(null, PASSWORD)),
            () -> assertThrows(IllegalArgumentException.class,
                () -> Investisseur.authentifier(EMAIL, null))
        );
    }

    @Test
    void testEqualsWithSameReturnsTrue() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertEquals(investisseur, investisseur, "equals should return true with the same object");
    }

    @Test
    void testEqualsWithSameMailReturnsTrue() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        Investisseur investisseur2 = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertEquals(investisseur, investisseur2, "equals should return true with the same object");
    }

    @Test
    void testEqualsWithDifferentMailReturnsFalse() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        Investisseur investisseur2 = new Investisseur(NEW_FIRST_NAME, NEW_LAST_NAME, EMAIL + "2", PASSWORD);
        assertNotEquals(investisseur, investisseur2, "equals should return false with different email");
    }

    @Test
    void testEqualsWithDifferentClassReturnsFalse() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        String investisseur2 = "";
        assertNotEquals(investisseur, investisseur2, "equals should return false with different class");
    }

    @Test
    void testHashcodeDoesNotThrow() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertDoesNotThrow(investisseur::hashCode, "hashCode should not throw");
    }

    @Test
    void testDeleteInvestisseurExisting() {
        Investisseur.creerInvestisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Supprime un investisseur existant",
            ()-> assertDoesNotThrow(() -> Investisseur.deleteInvestisseur(EMAIL))
        );
    }

    @Test
    void testDeleteInvestisseurEmailNotExistingOrNull() {
        assertAll(
            "Supprime un investisseur avec des champs non existants / nuls",
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.deleteInvestisseur("emailNonExistant@gmail.com")),
            ()-> assertThrows(IllegalArgumentException.class, () -> Investisseur.deleteInvestisseur(null))
        );
    }

    @Test
    void testSellActionWithNegativeQuantityThrows() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Action action = ActionSimpleTest.getDefaultActionSimple();
        assertThrows(IllegalArgumentException.class, () -> investisseur.sell(action, -10));
    }

    @Test
    void testSellNullActionThrows() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> investisseur.sell(null, 10));
    }

    @Test
    void testSellActionWithValidQuantityDoesNotThrow() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Action action = ActionSimpleTest.getDefaultActionSimple();
        investisseur.buy(action, 10);
        assertDoesNotThrow(() -> investisseur.sell(action, 5));
    }

    @Test
    void testSellActionWithQuantityGreaterThanOwnedThrows() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Action action = ActionSimpleTest.getDefaultActionSimple();
        investisseur.buy(action, 10);
        assertThrows(IllegalArgumentException.class, () -> investisseur.sell(action, 15));
    }

    @Test
    void testSellActionWithQuantityEqualToOwnedDoesNotThrow() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Action action = ActionSimpleTest.getDefaultActionSimple();
        investisseur.buy(action, 10);
        assertDoesNotThrow(() -> investisseur.sell(action, 10));
    }
}
