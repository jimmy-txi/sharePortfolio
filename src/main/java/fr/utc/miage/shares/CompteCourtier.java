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

// [US-20]: create a class for compte courtier 
/**
 * Représente un compte courtier associé à un investisseur.
 */
public class CompteCourtier {
    private String nomCourtier;
    private String identifiant;

    /**
     * Crée un nouveau compte courtier.
     * 
     * @param nomCourtier le nom du courtier
     * @param identifiant l'identifiant du compte
     * @throws IllegalArgumentException si l'un des paramètres est null
     */
    public CompteCourtier(String nomCourtier, String identifiant) {
        if (nomCourtier == null || identifiant == null) {
            throw new IllegalArgumentException("Tous les champs du compte courtier doivent être remplis");
        }
        this.nomCourtier = nomCourtier;
        this.identifiant = identifiant;
    }

    /**
     * Obtient le nom du courtier.
     *
     * @return le nom du courtier
     */
    public String getNomCourtier() {
        return nomCourtier;
    }

    /**
     * Définit le nom du courtier.
     *
     * @param nomCourtier le nom à assigner
     */
    public void setNomCourtier(String nomCourtier) {
        this.nomCourtier = nomCourtier;
    }

    /**
     * Obtient l'identifiant du compte courtier.
     *
     * @return l'identifiant du compte
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit l'identifiant du compte courtier.
     *
     * @param identifiant l'identifiant à assigner
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Retourne une représentation textuelle du compte courtier.
     *
     * @return une chaîne de caractères décrivant le compte courtier
     */
    @Override
    public String toString() {
        return "CompteCourtier [nomCourtier=" + nomCourtier + ", identifiant=" + identifiant + "]";
    }

    /**
     * Vérifie l'égalité entre deux comptes courtiers sur la base de leur identifiant.
     *
     * @param o l'objet à comparer
     * @return {@code true} si les deux objets ont le même identifiant, {@code false} sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompteCourtier that = (CompteCourtier) o;
        return identifiant != null ? identifiant.equals(that.identifiant) : that.identifiant == null;
    }

    /**
     * Retourne le code de hachage du compte courtier basé sur son identifiant.
     *
     * @return le code de hachage
     */
    @Override
    public int hashCode() {
        return identifiant != null ? identifiant.hashCode() : 0;
    }
}
