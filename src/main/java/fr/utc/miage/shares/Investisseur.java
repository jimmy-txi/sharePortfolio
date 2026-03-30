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

import java.util.HashMap;
import java.util.Map;

public class Investisseur extends Utilisateur {
    private String nom;
    private String prenom;
    private final Portfolio portfolio;
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

    public static void clearInvestisseursMap() {
        investisseursMap.clear();
    }

    public static Investisseur creerInvestisseur(String nom, String prenom, String email, String password) {
        if (investisseursMap.containsKey(email)) {
            throw new IllegalArgumentException("L'email existe déjà");
        }
        Investisseur nouvelInvestisseur = new Investisseur(nom, prenom, email, password);
        investisseursMap.put(email, nouvelInvestisseur);
        return nouvelInvestisseur;
    }

    /**
     * [US-30]: Authentifie un investisseur with his email and password.
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
    *  Buys a specified quantity of a given action and updates the portfolio accordingly.
    *
    * @param a the action to buy (must not be null)
    * @param quantity the quantity to buy (must be positive)
    */
    public void buy(Action a, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if(a == null){
            throw new IllegalArgumentException("Action cannot be null");
        }
        this.portfolio.addActionQuantity(a, quantity);
    }
}
