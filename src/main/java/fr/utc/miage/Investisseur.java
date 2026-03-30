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

import java.util.HashMap;
import java.util.Map;

import fr.utc.miage.shares.Utilisateur;

public class Investisseur extends Utilisateur {
    private String nom;
    private String prenom;

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
}
