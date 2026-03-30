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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SystemeManagerTest {

    //Update System
    //[US]: Mise à Jour Système #5
    //[Test]: Déclencher l'application d'une nouvelle mise à jour système #69
    @Test
    void testAppliquerNouvelleVersion() {
        Administrateur admin = new Administrateur("admin@test.com", "password123");
        SystemeManager systeme = new SystemeManager();
        systeme.setVersionDisponible("v1.2");
        systeme.appliquerMiseAJour(admin);
        assertAll(
            // The system goes through maintenance mode
            () -> assertTrue(systeme.isPasseParMaintenance(), "The system must have temporarily switched to maintenance mode"),
            // and restarts 
            () -> assertEquals(SystemeManager.Etat.EN_LIGNE, systeme.getEtat(), "The system must restart and become online again"),
            // to display version "v1.2" in the footer
            () -> assertEquals("v1.2", systeme.getVersionCourante(), "Version 'v1.2' must be applied (and displayed in the footer)")
        );
    }

    @Test
    void testAppliquerNouvelleVersionAvecAdminNull() {
        SystemeManager systeme = new SystemeManager();
        systeme.setVersionDisponible("v1.2");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            systeme.appliquerMiseAJour(null);
        });
        
        assertEquals("Only an administrator can apply an update.", exception.getMessage());
    }
}
