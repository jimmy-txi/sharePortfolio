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

// [US-20]: create a class for compte courtier 
public class CompteCourtier {
    private String nomCourtier;
    private String identifiant;
    private static Map<String, CompteCourtier> comptesMap = new HashMap<>();

    public CompteCourtier(String nomCourtier, String identifiant) {
        if (nomCourtier == null || identifiant == null) {
            throw new IllegalArgumentException("Tous les champs du compte courtier doivent être remplis");
        }
        this.nomCourtier = nomCourtier;
        this.identifiant = identifiant;
    }

    public static CompteCourtier creerCompteCourtier(String nomCourtier, String identifiant) {
        if (nomCourtier == null || identifiant == null) {
            throw new IllegalArgumentException("Tous les champs du compte courtier doivent être remplis");
        }
        if (comptesMap.containsKey(identifiant)) {
            throw new IllegalArgumentException("L'identifiant existe déjà");
        }
        CompteCourtier nouveauCompte = new CompteCourtier(nomCourtier, identifiant);
        comptesMap.put(identifiant, nouveauCompte);
        return nouveauCompte;
    }

    // Méthode utilitaire pour les tests : réinitialise la mémoire des comptes
    public static void viderComptesMap() {
        comptesMap.clear();
    }

    public String getNomCourtier() {
        return nomCourtier;
    }

    public void setNomCourtier(String nomCourtier) {
        this.nomCourtier = nomCourtier;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public String toString() {
        return "CompteCourtier [nomCourtier=" + nomCourtier + ", identifiant=" + identifiant + "]";
    }
}
