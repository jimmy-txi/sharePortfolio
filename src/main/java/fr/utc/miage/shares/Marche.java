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

import java.util.ArrayList;
import java.util.List;

/**
 * Représente le marché financier contenant la liste des actions disponibles.
 */
public class Marche {

    private List<Action> actionsDisponibles;

    public Marche() {
        this.actionsDisponibles = new ArrayList<>();
    }

    /**
     * Permet d'ajouter une action sur le marché pour la rendre disponible.
     * @param action L'action à ajouter.
     */
    public void ajouterAction(Action action) {
        this.actionsDisponibles.add(action);
    }

    /**
     * Renvoie la liste brute des actions disponibles.
     * @return La liste des actions.
     */
    public List<Action> getActionsDisponibles() {
        return actionsDisponibles;
    }

    /**
     * US : Afficher la liste des actions disponibles.
     * Retourne une chaîne de caractères formatée pour l'investisseur.
     * @return L'affichage de la liste.
     */
    public String afficherActionsDisponibles() {
        if (actionsDisponibles.isEmpty()) {
            return "Aucune action n'est disponible sur le marché pour le moment.";
        }

        StringBuilder affichage = new StringBuilder("--- Liste des actions disponibles ---\n");
        for (Action action : actionsDisponibles) {
            affichage.append("- ").append(action.getLibelle()).append("\n");
        }
        return affichage.toString();
    }
}