/*
 * Copyright 2025 David Navarre <David.Navarre at irit.fr>.
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
 * Service de gestion des comptes courtiers.
 * Cette classe centralise la gestion du cycle de vie des {@link CompteCourtier}.
 * Elle remplace les anciens attributs statiques présents dans les entités
 * et doit être instanciée par les composants qui en ont besoin.
 */
public class CompteCourtierService {

    private final Map<String, CompteCourtier> comptesDb = new HashMap<>();

    /**
     * Enregistre un nouveau compte courtier.
     * 
     * @param compteCourtier le compte à enregistrer, ne doit pas être null
     * @throws IllegalArgumentException si le compte est null ou si son identifiant existe déjà
     */
    public void enregistrerCompteCourtier(CompteCourtier compteCourtier) {
        if (compteCourtier == null) {
            throw new IllegalArgumentException("Le compte ne peut pas être null");
        }
        if (comptesDb.containsKey(compteCourtier.getIdentifiant())) {
            throw new IllegalArgumentException("L'identifiant existe déjà");
        }
        comptesDb.put(compteCourtier.getIdentifiant(), compteCourtier);
    }
}

