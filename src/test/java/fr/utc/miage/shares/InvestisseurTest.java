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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class InvestisseurTest {

    @AfterEach
    void tearDown() { // for clear static map after each test
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

    public static final String COURTIER_NAME = "Boursorama";
    public static final String COURTIER_IDENTIFIANT = "123456789";

    public static Investisseur getDefaultInvestisseur() {
        return new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
    }

    @Test
    void testConstructeur() {
        assertDoesNotThrow(() -> new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD),
                "Constructor should not throw with valid parameters");
    }

    @Test
    void testConstructeurThrowsWithIncorrectValues() {
        assertAll(
                "Cree un investisseur avec des champs invalides",
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(null, FIRST_NAME, EMAIL, PASSWORD)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur("", FIRST_NAME, EMAIL, PASSWORD)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(LAST_NAME, null, EMAIL, PASSWORD)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Investisseur(LAST_NAME, "", EMAIL, PASSWORD))
        );
    }

    @Test
    void testConstructeurFields() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Cree un investisseur avec des champs valides",
            () -> assertNotNull(investisseur),
            () -> assertEquals(FIRST_NAME, investisseur.getNom()),
            () -> assertEquals(LAST_NAME, investisseur.getPrenom()),
            () -> assertEquals(EMAIL, investisseur.getEmail())
        );
    }

    @Test
    void testGetters() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Verifie les getters",
            () -> assertEquals(LAST_NAME, investisseur.getNom()),
            () -> assertEquals(FIRST_NAME, investisseur.getPrenom()),
            () -> assertEquals(EMAIL, investisseur.getEmail())
        );
    }

    @Test
    void testSetters() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        investisseur.setNom(NEW_LAST_NAME);
        investisseur.setPrenom(NEW_FIRST_NAME);
        investisseur.setPassword(NEW_PASSWORD);
        assertAll(
                "Verifie les setters",
                () -> assertEquals(NEW_LAST_NAME, investisseur.getNom()),
                () -> assertEquals(NEW_FIRST_NAME, investisseur.getPrenom()),
                () -> assertTrue(investisseur.verifierMotDePasse(NEW_PASSWORD))
        );
    }

    @Test
    void testSettersWithIncorrectValueThrows() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertAll(
                "Verifie que les setters throws",
                () -> assertThrows(IllegalArgumentException.class, () -> investisseur.setNom(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> investisseur.setNom("")),
                () -> assertThrows(IllegalArgumentException.class, () -> investisseur.setPrenom(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> investisseur.setPrenom(""))
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

    @Test
    void testToString() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertAll(
            "Verifie le toString",
            () -> assertEquals("Investisseur [nom=" + LAST_NAME + ", prenom=" + FIRST_NAME + ", email=" + EMAIL + "]", investisseur.toString())
        );
    }

    // --- Transaction History tests ---

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
        assertDoesNotThrow(() -> investisseurTest.addTransactionSale(new Transaction(actionTestForSale1, new Jour(2026, 1), 10.0f)));
    }

    @Test
    void getTransactionsBuy() {
        assertDoesNotThrow(investisseurTest::getTransactionsBuy);
    }

    @Test
    void addTransactionBuy() {
        assertDoesNotThrow(() -> investisseurTest.addTransactionBuy(new Transaction(actionTestForBuy1, new Jour(2026, 1), 15.0f)));
    }

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
            () -> assertEquals(tSale1, investisseurTest.getTransactionsHistory().get("sale").get(0)),
            () -> assertEquals(tSale2, investisseurTest.getTransactionsHistory().get("sale").get(1)),
            () -> assertEquals(tBuy1, investisseurTest.getTransactionsHistory().get("buy").get(0)),
            () -> assertEquals(tBuy2, investisseurTest.getTransactionsHistory().get("buy").get(1))
        );
    }

    // [Test]: Affichage de l'historique des transactions lorsqu'il est vide #81
    @Test
    void getTransactionsHistoryEmpty() {
        assertAll(
            "Verifie l'historique des transactions",
            () -> assertEquals(0, investisseurTest.getTransactionsHistory().get("sale").size()),
            () -> assertEquals(0, investisseurTest.getTransactionsHistory().get("buy").size())
        );
    }

    // --- Reset Password tests ---

    @Test
    void testResetPasswordValide() {
        Investisseur investisseur = new Investisseur("Dupont", "Jean", "1@gmail.com", "password123");
        String newPassword = investisseur.resetPassword();
        assertNotEquals("password123", newPassword);
    }

    // --- Broker Account Liaison tests (US-20) ---

    // [Test-63]: Liaison réussie d'un compte courtier à un investisseur
    @Test
    void testLierCompteCourtierValide() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        CompteCourtier compte = new CompteCourtier(COURTIER_NAME, COURTIER_IDENTIFIANT);
        investisseur.lierCompteCourtier(compte);
        assertAll(
            "Liaison réussie d'un compte courtier",
            () -> assertEquals(1, investisseur.getComptesCourtiers().size()),
            () -> assertEquals(compte, investisseur.getComptesCourtiers().get(0))
        );
    }


    // [Test-77]: Échec de liaison - compte courtier déjà lié
    @Test
    void testLierCompteCourtierDejaLie() {
        Investisseur investisseur = new Investisseur(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        CompteCourtier compte = new CompteCourtier(COURTIER_NAME, COURTIER_IDENTIFIANT);
        investisseur.lierCompteCourtier(compte);
        
        CompteCourtier memeCompte = new CompteCourtier(COURTIER_NAME, COURTIER_IDENTIFIANT);
        assertThrows(
            IllegalArgumentException.class,
            () -> investisseur.lierCompteCourtier(memeCompte)
        );
    }

    // --- Buy tests ---

    /**
     * Tests the buy method of the Investor class to ensure it throws an IllegalArgumentException when a negative quantity is provided.
     */
    @Test
    void testBuyNegativeQuantity() {
        Action action = ActionSimpleTest.getDefaultActionSimple();
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> investisseur.buy(action, 0));
    }

    @Test
    void testBuy() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Action action = ActionSimpleTest.getDefaultActionSimple();
        assertDoesNotThrow(() -> investisseur.buy(action, 1));
    }

    @Test
    void testBuyNullAction() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> investisseur.buy(null, 1));
    }

    // --- Equals / HashCode tests ---

    @Test
    void testEqualsWithSameReturnsTrue() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertTrue(investisseur.equals(investisseur), "equals should return true with the same object");
    }

    @Test
    void testEqualsWithSameMailReturnsTrue() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Investisseur investisseur2 = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertTrue(investisseur.equals(investisseur2), "equals should return true with the same object");
    }

    @Test
    void testEqualsWithDifferentMailReturnsFalse() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        Investisseur investisseur2 = new Investisseur(NEW_FIRST_NAME, NEW_LAST_NAME, EMAIL + "2", PASSWORD);
        assertFalse(investisseur.equals(investisseur2), "equals should return true with the same object");
    }

    @Test
    void testEqualsWithDifferentClassReturnsFalse() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        String investisseur2 = "";
        assertFalse(investisseur.equals(investisseur2), "equals should return true with the same object");
    }

    @Test
    void testHashcodeDoesNotThrow() {
        Investisseur investisseur = new Investisseur(LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
        assertDoesNotThrow(investisseur::hashCode, "hashCode should not throw");
    }
}
