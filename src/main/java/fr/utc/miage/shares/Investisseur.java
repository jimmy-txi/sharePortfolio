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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Investisseur extends Utilisateur {

    private String nom;
    private String prenom;
    private final Portfolio portfolio;
    private static Map<String, Investisseur> investisseursMap = new HashMap<>();

    // for record transactions sale
    private List<Transaction> transactionsSale = new ArrayList<>();

    // for record transaction buy
    private List<Transaction> transactionsBuy = new ArrayList<>();

    /**
     * Gets the sale transactions history.
     *
     * @return the list of sale transactions
     */
    public List<Transaction> getTransactionsSale() {
        return transactionsSale;
    }

    /**
     * Adds a sale transaction to the history.
     *
     * @param transaction the transaction to add
     */
    public void addTransactionSale(Transaction transaction) {
        this.transactionsSale.add(transaction);
    }

    /**
     * Gets the buy transactions history.
     *
     * @return the list of buy transactions
     */
    public List<Transaction> getTransactionsBuy() {
        return transactionsBuy;
    }

    /**
     * Adds a buy transaction to the history.
     *
     * @param transaction the transaction to add
     */
    public void addTransactionBuy(Transaction transaction) {
        this.transactionsBuy.add(transaction);
    }

    /**
     * Gets all transactions history grouped by type.
     * [US]: Historique Transactions #3
     * [Test]: Consulter l'historique des transactions avec des données existantes #64
     * [Test]: Affichage de l'historique des transactions lorsqu'il est vide #81
     *
     * @return a map with "sale" and "buy" keys containing respective transaction lists
     */
    public Map<String, List<Transaction>> getTransactionsHistory() {
        Map<String, List<Transaction>> transactionsHistory = new HashMap<>();
        transactionsHistory.put("sale", this.transactionsSale);
        transactionsHistory.put("buy", this.transactionsBuy);
        return transactionsHistory;
    }

    /**
     * Creates an investor with specified params.
     *
     * @param nom The investor's name, not null, not empty
     * @param prenom The investor's first name, not null, not empty
     * @param email The investor's email, not null, respects a regex
     * @param password The investor's password, not null, not empty
     */
    public Investisseur(String nom, String prenom, String email, String password) {
        super(email, password);
        if (nom == null || prenom == null) {
            throw new IllegalArgumentException("Nom and prenom cannot be null");
        }
        if (nom.isEmpty() || prenom.isEmpty()) {
            throw new IllegalArgumentException("Nom and prenom cannot be empty");
        }
        this.nom = nom;
        this.prenom = prenom;
        this.portfolio = new Portfolio();
    }

    /**
     * Gets the name of the investor.
     *
     * @return the name of the investor
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the name of the investor.
     *
     * @param nom the name of the investor, not null
     */
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Nom cannot be null or empty");
        }
        this.nom = nom;
    }

    /**
     * Gets the first name of the investor.
     *
     * @return the first name of the investor
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets the first name of the investor.
     *
     * @param prenom the first name of the investor, not null
     */
    public void setPrenom(String prenom) {
        if (prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Prenom cannot be null or empty");
        }
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Investisseur [nom=" + nom + ", prenom=" + prenom + ", email=" + getEmail()
                + "]";
    }

    // for SonarQube
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // for SonarQube
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Creates and registers a new investor.
     * [US-30]: Connexion Compte
     *
     * @param nom the investor's name, not null, not empty
     * @param prenom the investor's first name, not null, not empty
     * @param email the investor's email, not null, valid format
     * @param password the investor's password, not null, not empty
     * @return the newly created investor
     * @throws IllegalArgumentException if any field is null, email is invalid, or email already exists
     */
    public static Investisseur creerInvestisseur(String nom, String prenom, String email, String password) {
        if (email == null || password == null || nom == null || prenom == null) {
            throw new IllegalArgumentException("Tous les champs doivent être remplis");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("L'email n'est pas valide");
        }
        if (investisseursMap.containsKey(email)) {
            throw new IllegalArgumentException("L'email existe déjà");
        }
        Investisseur nouvelInvestisseur = new Investisseur(nom, prenom, email, password);
        investisseursMap.put(email, nouvelInvestisseur);
        return nouvelInvestisseur;
    }

    /**
     * Clears the investors registry. Used for test isolation.
     */
    public static void clearInvestisseursMap() {
        investisseursMap.clear();
    }

    /**
     * [US-30]: Authentifie un investisseur with his email and password.
     *
     * @param email the investor's email
     * @param password the password
     * @return the investor if credentials are valid
     * @throws IllegalArgumentException if credentials are invalid
     */
    public static Investisseur authentifier(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("L'email et le mot de passe ne peuvent pas être null");
        }
        Investisseur investisseur = investisseursMap.get(email);
        if (investisseur == null || !investisseur.verifierMotDePasse(password)) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect");
        }
        return investisseur;
    }

    /**
     * Deletes an investor from the registry.
     *
     * @param email the investor's email, not null
     * @throws IllegalArgumentException if email is null or not found
     */
    public static void deleteInvestisseur(String email) {
        if (email == null) {
            throw new IllegalArgumentException("L'email ne peut pas être null");
        }
        if (!investisseursMap.containsKey(email)) {
            throw new IllegalArgumentException("L'email n'existe pas");
        }
        investisseursMap.remove(email);
    }

    /**
     * Buys a specified quantity of a given action and updates the portfolio accordingly.
     *
     * @param a the action to buy (must not be null)
     * @param quantity the quantity to buy (must be positive)
     */
    public void buy(Action a, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (a == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        this.portfolio.addActionQuantity(a, quantity);
    }

    /**
     * Sells a specified quantity of a given action and updates the portfolio accordingly.
     *
     * @param a the action to sell (must not be null)
     * @param quantity the quantity to sell (must be positive and less than or equal to the quantity owned)
     */
    public void sell(Action a, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (a == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        if (this.portfolio.getActionQuantity(a) < quantity) {
            throw new IllegalArgumentException("Not enough quantity to sell");
        }
        this.portfolio.removeActionQuantity(a, quantity);
    }
}
