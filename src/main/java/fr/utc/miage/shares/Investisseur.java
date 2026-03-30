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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;



public class Investisseur {

    private Random random = new Random();
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private List<CompteCourtier> comptesCourtiers = new ArrayList<>();
    private Portfolio portfolio;
    private static Map<String, Investisseur> investisseursMap = new HashMap<>();
    public Investisseur(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.portfolio = new Portfolio();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // [US-20]: make a liason between investisseur and compte courtier 
    public List<CompteCourtier> getComptesCourtiers() {
        return comptesCourtiers;
    }

    // [US-20]: make a liason between investisseur and compte courtier 
    public void lierCompteCourtier(CompteCourtier compte) {
        if (compte == null) {
            throw new IllegalArgumentException("Le compte courtier ne peut pas être null");
        }
        this.comptesCourtiers.add(compte);
    }

    /**
    *  reset a password of an investisseur and return the new password.
    * @return the new password
    * @throws IllegalArgumentException if the email is not set
    *   */
    public String resetPassword() {
        if (this.email == null) {
            throw new IllegalArgumentException("L'email doit être renseigné pour réinitialiser le mot de passe");
        }   
            byte[] array = new byte[7];
            
            random.nextBytes(array);            
            String newPassword = new String(array, StandardCharsets.UTF_8);
            this.setPassword(newPassword);
            return newPassword;
    }


    @Override
    public String toString() {
        return "Investisseur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password
                + "]";
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

    public static void deleteInvestisseur(String email) {
        if (email == null) {
            throw new IllegalArgumentException("L'email ne peut pas être null");
        }
        if (!investisseursMap.containsKey(email)) {
            throw new IllegalArgumentException("L'email n'existe pas");
        }
        investisseursMap.remove(email);
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
