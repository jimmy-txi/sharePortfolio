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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthentificationServiceTest {

    private static final String VALID_PASSWORD = "password";

    @Test
    public void testConstructorDoesNotThrow() {
        assertDoesNotThrow(AuthentificationService::new, "Constructor should not throw");
    }

    @Test
    void testEnregistrerUtilisateurWithAnAdminShouldNotThrow() {
        AuthentificationService authentificationService = new AuthentificationService();
        assertDoesNotThrow(()->authentificationService.enregistrerUtilisateur(AdministrateurTest.getDefaultAdministrateur()),
                "Adding an administrator should not throw");
    }

    @Test
    void testEnregistrerUtilisateurWithAnInvestorShouldNotThrow() {
        AuthentificationService authentificationService = new AuthentificationService();
        assertDoesNotThrow(()->authentificationService.enregistrerUtilisateur(InvestisseurTest.getDefaultInvestisseur()),
                "Adding an investor should not throw");
    }

    @Test
    void testEnregistrerUtilisateurWithNullShouldThrow() {
        AuthentificationService authentificationService = new AuthentificationService();
        assertThrows(IllegalArgumentException.class, ()->authentificationService.enregistrerUtilisateur(null),
                "Adding null should throw");
    }

    @Test
    void testEnregistrerUtilisateurTwiceWithDifferentShouldNotThrow() {
        AuthentificationService authentificationService = new AuthentificationService();
        assertDoesNotThrow(()->authentificationService.enregistrerUtilisateur(AdministrateurTest.getDefaultAdministrateur()),
                "Adding an administrator should not throw");
        assertDoesNotThrow(()->authentificationService.enregistrerUtilisateur(InvestisseurTest.getDefaultInvestisseur()),
                "Adding an investor should not throw");
    }

    @Test
    void testEnregistrerUtilisateurTwiceWithSameEmailShouldThrow() {
        AuthentificationService authentificationService = new AuthentificationService();
        assertDoesNotThrow(()-> authentificationService.enregistrerUtilisateur(AdministrateurTest.getDefaultAdministrateur()),
                "Adding an administrator should not throw");
        assertThrows(IllegalArgumentException.class, ()->authentificationService.enregistrerUtilisateur(AdministrateurTest.getDefaultAdministrateur()),
                "Adding an administrator twice should throw");
    }

    @Test
    void testLoginWhenNoUserShouldReturnNull() {
        AuthentificationService authentificationService = new AuthentificationService();
        assertNull(authentificationService.login("email", "password"),
                "Login should return null when no user is registered");
    }

    @Test
    void testLoginWheWithCorrectEmailAndPasswordShoudReturnUser() {
        AuthentificationService authentificationService = new AuthentificationService();
        Administrateur administrateur = AdministrateurTest.getDefaultAdministrateur();
        authentificationService.enregistrerUtilisateur(administrateur);
        administrateur.setPassword(VALID_PASSWORD);
        assertInstanceOf(Administrateur.class, authentificationService.login(administrateur.getEmail(), VALID_PASSWORD),
                "Login should return null when no user is registered");
    }

    @Test
    void testLoginWheWithCorrectEmailAndWrongPasswordShoudReturnNull() {
        AuthentificationService authentificationService = new AuthentificationService();
        Administrateur administrateur = AdministrateurTest.getDefaultAdministrateur();
        authentificationService.enregistrerUtilisateur(administrateur);
        administrateur.setPassword(VALID_PASSWORD);
        assertNull(authentificationService.login(administrateur.getEmail(), ""),
                "Login should return null when password is wrong");
    }
}