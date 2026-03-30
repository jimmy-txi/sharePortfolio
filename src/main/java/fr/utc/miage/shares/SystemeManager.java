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

/**
 * Class managing the system state and version for the application.
 */
public class SystemeManager {

    /**
     * Represents the current state of the application.
     */
    public enum Etat {
        EN_LIGNE,
        MAINTENANCE
    }

    private String versionCourante = "v1.0";
    private String versionDisponible = null;
    private Etat etat = Etat.EN_LIGNE;
    private boolean passeParMaintenance = false;

    public void setVersionDisponible(String versionDisponible) {
        this.versionDisponible = versionDisponible;
    }

    public String getVersionCourante() {
        return versionCourante;
    }

    public Etat getEtat() {
        return etat;
    }

    public boolean isPasseParMaintenance() {
        return passeParMaintenance;
    }

    /**
     * Applies the new version of the system.
     * This method simulates the update process (maintenance -> installation -> restart).
     *
     * @param admin The administrator triggering the update (required).
     */
    public void appliquerMiseAJour(Administrateur admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Only an administrator can apply an update.");
        }
        
        if (versionDisponible != null && !versionDisponible.isEmpty()) {
            // Temporarily switch to maintenance mode
            this.etat = Etat.MAINTENANCE;
            this.passeParMaintenance = true;
            
            // Install the update and restart
            this.versionCourante = this.versionDisponible;
            this.versionDisponible = null;
            this.etat = Etat.EN_LIGNE;
        }
    }
}
