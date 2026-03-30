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

public class Administrateur extends Utilisateur {

    /**
     * Creates and admin with specified email and password
     *
     * @param email the admin's email, not null, repsects a regex
     * @param password the admin's password, not null, not empty
     */
    public Administrateur(String email, String password) {
        super(email, password);
    }

    // Pour sonar
    /**
     * Determines if this administrateur is equals to other
     *
     * @param o the other one to compare to
     * @return true if his administrateur is equals to other
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
