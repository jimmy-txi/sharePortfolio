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

/**
 * Allows the creation of simple Action objects.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionSimple extends Action {

    private static final int DEFAULT_ACTION_VALUE = 0;

    // attribut lien
    private final Map<Jour, Float> mapCours;

    // constructeur
    public ActionSimple(final String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap<>();
    }

    /**
     * Enregistre le cours de l'action pour un jour donné.
     * @param j le jour pour lequel enregistrer le cours
     * @param v le cours de l'action à enregistrer
     * @throws IllegalArgumentException si le jour est après aujourd'hui, si le jour existe déjà ou si la valeur est négative ou nulle
     */
    public void enrgCours(final Jour j, final float v) {
        if (j.isAfterToday()){
            throw new IllegalArgumentException("Date after today");
        }
        if (mapCours.containsKey(j)) {
            throw new IllegalArgumentException("Date Already Exist");
        }

        if (v > 0){
            this.mapCours.put(j, v);
        } else {
            throw new IllegalArgumentException("Value out of range");
        }

    }

    @Override
    public float valeur(final Jour j) {
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }

    // for better SonarQube performance
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    // for better SonarQube performance
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Renvoie l'évolution du cours de l'action entre deux jours donnés en pourcentage.
     * @param j1 le premier jour
     * @param j2 le deuxième jour
     * @return l'évolution du cours de l'action entre les deux jours en pourcentage
     * @throws IllegalArgumentException si l'un des jours est après aujourd'hui ou si l'un des jours n'existe pas pour cette action
    */
    public float getEvolutionCours(Jour j1, Jour j2) {
        if (j1.isAfterToday() || j2.isAfterToday()){
            throw new IllegalArgumentException("Date after today");
        }
        if (!mapCours.containsKey(j1) || !mapCours.containsKey(j2)) {
            throw new IllegalArgumentException("Date not exist for this action");
        }
        float value1 = this.mapCours.get(j1);
        float value2 = this.mapCours.get(j2);
        return (value2 - value1) / value1 * 100;
    }
}
