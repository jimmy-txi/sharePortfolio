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

import java.util.HashMap;
import java.util.Map;

public class AuthentificationService {
    
    private final Map<String, Utilisateur> utilisateursDb = new HashMap<>();

    /**
     * Enregistre un utilisateur dans la "BD"
     *
     * @param utilisateur l'utilisateur à enregistrer, not null, unique email
     */
    public void enregistrerUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("utilisateur cannot be null");
        }
        if (utilisateursDb.containsKey(utilisateur.getEmail())) {
            throw new IllegalArgumentException("L'email existe déjà");
        }
        utilisateursDb.put(utilisateur.getEmail(), utilisateur);
    }

    /**
     * Tente de connecter un utilisateur.
     * @return L'utilisateur connecté, ou null si échec.
     */
    public Utilisateur login(String email, String password) {
        Utilisateur user = utilisateursDb.get(email);
        
        if (user != null && user.verifierMotDePasse(password)) {
            return user;
        }
        return null; // Échec de connexion (mauvais email ou mauvais mot de passe)
    }
}
