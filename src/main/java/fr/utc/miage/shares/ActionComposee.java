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
import java.util.Objects;

/**
 * Représente une action composée, c'est-à-dire un panier d'actions.
 */
public class ActionComposee extends Action {

    // Stocke les actions composantes et leur proportion/quantité dans le panier
    private Map<Action, Float> mapPanier;

    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap<>();
    }

    /**
     * Permet à l'administrateur d'ajouter une action (simple ou composée) dans ce panier.
     * * @param action      L'action à ajouter
     * @param pourcentage La quantité ou le pourcentage de cette action dans le panier
     */
    public void enrgComposition(Action action, float pourcentage) {
        this.mapPanier.put(action, pourcentage);
    }

    public Map<Action, Float> getMapPanier() {
        return mapPanier;
    }

    /**
     * Calcule la valeur de l'action composée pour un jour donné.
     * C'est la somme des (valeur de l'action composante * sa proportion).
     * * @param j Le jour pour lequel on veut la valeur
     * @return La valeur totale du panier à ce jour
     */
    @Override
    public float valeur(Jour j) {
        float valeurTotale = 0.0f;
        
        // On parcourt toutes les actions contenues dans notre panier
        for (Map.Entry<Action, Float> entry : mapPanier.entrySet()) {
            Action action = entry.getKey();
            Float proportion = entry.getValue();
            
            // On calcule : Valeur de l'action au jour j * sa proportion
            valeurTotale += action.valeur(j) * proportion;
        }
        
        return valeurTotale;
    }


    @Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    final Action other = (Action) obj;
    
    // On utilise getLibelle() au lieu de libelle en direct
    return Objects.equals(this.getLibelle(), other.getLibelle());
}

}