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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ComptCourtierTest {

    @AfterEach
    void tearDown() {
        CompteCourtier.viderComptesMap();
    }

    public static final String NOM_COURTIER = "Boursorama";
    public static final String IDENTIFIANT = "123456789";

    public static final String NOM_COURTIER_MODIF = "Fortuneo";
    public static final String IDENTIFIANT_MODIF = "987654321";

    // [Test-63]: test constructeur for compte courtier 
    @Test
    void testConstructeur() {
        CompteCourtier compteCourtier = new CompteCourtier(NOM_COURTIER, IDENTIFIANT);
        assertAll(
            "Cree un compte courtier avec des champs valides",
            ()-> assertNotNull(compteCourtier),
            ()-> assertEquals(NOM_COURTIER, compteCourtier.getNomCourtier()),
            ()-> assertEquals(IDENTIFIANT, compteCourtier.getIdentifiant())
        );
    }

    // [Test-63]: test getters for compte courtier 
    @Test
    void testGetters() {
        CompteCourtier compteCourtier = new CompteCourtier(NOM_COURTIER, IDENTIFIANT);
        assertAll(
            "Verifie les getters",
            ()-> assertEquals(NOM_COURTIER, compteCourtier.getNomCourtier()),
            ()-> assertEquals(IDENTIFIANT, compteCourtier.getIdentifiant())
        );
    }

    // [Test-63]: test setters for compte courtier 
    @Test
    void testSetters() {
        CompteCourtier compteCourtier = new CompteCourtier(NOM_COURTIER, IDENTIFIANT);
        compteCourtier.setNomCourtier(NOM_COURTIER_MODIF);
        compteCourtier.setIdentifiant(IDENTIFIANT_MODIF);
        assertAll(
            "Verifie les setters",
            ()-> assertEquals(NOM_COURTIER_MODIF, compteCourtier.getNomCourtier()),
            ()-> assertEquals(IDENTIFIANT_MODIF, compteCourtier.getIdentifiant())
        );
    }

    // [Test-63]: test toString for compte courtier 
    @Test
    void testToString() {
        CompteCourtier compteCourtier = new CompteCourtier(NOM_COURTIER, IDENTIFIANT);
        assertAll(
            "Verifie le toString",
            ()-> assertEquals("CompteCourtier [nomCourtier=" + NOM_COURTIER + ", identifiant=" + IDENTIFIANT + "]", compteCourtier.toString())
        );
    }

    // [Test-63]: test creer compte courtier valide 
    @Test
    void testCreerCompteCourtierValide() {
        CompteCourtier compteCourtier = CompteCourtier.creerCompteCourtier(NOM_COURTIER, IDENTIFIANT);
        assertAll(
            "Cree un compte courtier avec des champs valides",
            ()-> assertNotNull(compteCourtier),
            ()-> assertEquals(NOM_COURTIER, compteCourtier.getNomCourtier()),
            ()-> assertEquals(IDENTIFIANT, compteCourtier.getIdentifiant())
        );
    }

    // [Test-63]: test creer compte courtier avec des champs nuls 
    @Test
    void testCreerCompteCourtierChampsNuls() {
        assertAll(
            "Cree un compte courtier avec des champs nuls",
            ()-> assertThrows(IllegalArgumentException.class, () -> CompteCourtier.creerCompteCourtier(null, IDENTIFIANT)),
            ()-> assertThrows(IllegalArgumentException.class, () -> CompteCourtier.creerCompteCourtier(NOM_COURTIER, null))
        );
    }

    // [Test-63]: test creer compte courtier avec identifiant existant 
    @Test
    void testCreerCompteCourtierIdentifiantExistant() {
        CompteCourtier.creerCompteCourtier(NOM_COURTIER, IDENTIFIANT);
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> CompteCourtier.creerCompteCourtier(NOM_COURTIER_MODIF, IDENTIFIANT)
        );
        assertEquals("L'identifiant existe déjà", exception.getMessage());
    }

    // [Test-63]: test constructeur avec des champs nuls 
    @Test
    void testConstructeurChampsNuls() {
        assertAll(
            "Cree un compte courtier directement avec des champs nuls",
            ()-> assertThrows(IllegalArgumentException.class, () -> new CompteCourtier(null, IDENTIFIANT)),
            ()-> assertThrows(IllegalArgumentException.class, () -> new CompteCourtier(NOM_COURTIER, null))
        );
    }

}
