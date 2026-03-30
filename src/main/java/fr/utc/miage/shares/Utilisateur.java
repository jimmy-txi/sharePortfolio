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

import java.util.Objects;

public abstract class Utilisateur {
    private final String email;
    private String password;

    /**
     * Creates a new user with the given email and password.
     *
     * @param email The user's email, not null, respects a regex
     * @param password The user's password, not null, not empty
     */
    protected Utilisateur(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Tous les champs doivent être remplis");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("L'email n'est pas valide");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Le password n'est pas valide");
        }

        this.email = email;
        this.password = password;
    }

    /**
     * Gets the user's email
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Verifie si le mot de passe saisi est le mot de passe de l'utilisateur
     *
     * @param motDePasseSaisi le mot de passe à vérifier
     * @return vrai si les mots de passes sont égaux, faux sinon
     */
    public boolean verifierMotDePasse(String motDePasseSaisi) {
        return this.password.equals(motDePasseSaisi);
    }

    /**
     * Sets the user's password
     *
     * @param password the user's password, not null, not empty
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Le password n'est pas valide");
        }
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Utilisateur that)) return false;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
