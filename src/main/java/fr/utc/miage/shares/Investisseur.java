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

    // for record transactions sale
    private List<Transaction> transactionsSale = new ArrayList<>();

    // for record transaction buy
    private List<Transaction> transactionsBuy = new ArrayList<>();

    // [US-20]: liaison entre investisseur et compte courtier
    private List<CompteCourtier> comptesCourtiers = new ArrayList<>();

    private static Map<String, Investisseur> investisseursMap = new HashMap<>();

    /**
     * Creates an investor with specified params
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
     * Gets the name of the investor
     *
     * @return the name of the investor
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the name of the investor
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
     * Gets the first name of the investor
     *
     * @return the first name of the investor
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets the first name of the investor
     *
     * @param prenom the first name of the investor, not null
     */
    public void setPrenom(String prenom) {
        if (prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Prenom cannot be null or empty");
        }
        this.prenom = prenom;
    }

    // get transactions sale history
    public List<Transaction> getTransactionsSale() {
        return transactionsSale;
    }

    // add an Action in transaction sale
    public void addTransactionSale(Transaction transaction) {
        this.transactionsSale.add(transaction);
    }

    // get transactions buy history
    public List<Transaction> getTransactionsBuy() {
        return transactionsBuy;
    }

    // add an Action in transaction buy
    public void addTransactionBuy(Transaction transaction) {
        this.transactionsBuy.add(transaction);
    }

    // get all transactions history
    // [US]: Historique Transactions #3
    // [Test]: Consulter l'historique des transactions avec des données existantes #64
    // [Test]: Affichage de l'historique des transactions lorsqu'il est vide #81
    public Map<String, List<Transaction>> getTransactionsHistory() {
        Map<String, List<Transaction>> transactionsHistory = new HashMap<>();
        transactionsHistory.put("sale", this.transactionsSale);
        transactionsHistory.put("buy", this.transactionsBuy);
        return transactionsHistory;
    }

    // [US-20]: make a liaison between investisseur and compte courtier
    public List<CompteCourtier> getComptesCourtiers() {
        return comptesCourtiers;
    }

    // [US-20]: make a liaison between investisseur and compte courtier
    public void lierCompteCourtier(CompteCourtier compte) {
        if (compte == null) {
            throw new IllegalArgumentException("Le compte courtier ne peut pas être null");
        }
        if (this.comptesCourtiers.contains(compte)) {
            throw new IllegalArgumentException("Ce compte courtier est déjà lié à l'investisseur");
        }
        this.comptesCourtiers.add(compte);
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

    public static void clearInvestisseursMap() {
        investisseursMap.clear();
    }

    /**
     *  Buys a specified quantity of a given action and updates the portfolio accordingly.
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
     * @param a the action to sell (must not be null)
     * @param quantity the quantity to sell (must be positive and less than or equal to the quantity owned)
     */

    public void sell(Action a, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if(a == null){
            throw new IllegalArgumentException("Action cannot be null");
        }
        if (this.portfolio.getActionQuantity(a) < quantity) {
            throw new IllegalArgumentException("Not enough quantity to sell");
        }
        this.portfolio.removeActionQuantity(a, quantity);
    }
}
