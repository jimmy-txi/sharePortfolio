/*
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
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

class UtilisateurTest {

    private static final String VALID_EMAIL = "user@mail.fr";
    private static final String INVALID_EMAIL = "invalid";
    private static final String VALID_PASSWORD = "password";
    private static final String OTHER_VALID_EMAIL = "other@mail.fr";

    @Test
    void testConstructorWithCorrectParamsDoesNotThrows() {
        assertDoesNotThrow(() -> new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD),
                "Constructor should not throw when email and password are valid");
    }

    @Test
    void testConstructorWithInvalidEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new UtilisateurImpl(INVALID_EMAIL, VALID_PASSWORD),
                "Constructor should throw when email is invalid");
    }

    @Test
    void testConstructorWithEmptyEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new UtilisateurImpl("", VALID_PASSWORD),
                "Constructor should throw when email is invalid");
    }

    @Test
    void testConstructorWithNullEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new UtilisateurImpl(null, VALID_PASSWORD),
                "Constructor should throw when email is null");
    }

    @Test
    void testConstructorWithNullPasswordThrows() {
        assertThrows(IllegalArgumentException.class, () -> new UtilisateurImpl(VALID_EMAIL, null),
                "Constructor should throw when password is null");
    }

    @Test
    void testConstructorWithEmptyPasswordThrows() {
        assertThrows(IllegalArgumentException.class, () -> new UtilisateurImpl(VALID_EMAIL, ""),
                "Constructor should throw when password is null");
    }

    @Test
    void testGetEmailReturnsCorrectEmail() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        String actualEmail = u.getEmail();
        assertEquals(VALID_EMAIL, actualEmail, "getEmail should return the email used in the constructor");
    }

    @Test
    void testVerifierMotDePasseReturnsTrueWithCorrectPassword() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertTrue(u.verifierMotDePasse(VALID_PASSWORD), "verifierMotDePasse should return true with the password used in the constructor");
    }

    @Test
    void testVerifierMotDePasseReturnsFalseWithCorrectPassword() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertFalse(u.verifierMotDePasse(""), "verifierMotDePasse should return false with a wrong password");
    }

    @Test
    void testSetPasswordWithCorrectValueDoesNotThrow() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertDoesNotThrow(() -> u.setPassword(VALID_PASSWORD), "setPassword should not throw when password is correct");
    }

    @Test
    void testSetPasswordWithNullThrows() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertThrows(IllegalArgumentException.class, () -> u.setPassword(null), "setPassword should throw when password is null");
    }

    @Test
    void testSetPasswordWithEmptyThrows() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertThrows(IllegalArgumentException.class,() -> u.setPassword(""), "setPassword should throw when password is empty");
    }

    @Test
    void testEqualsWithSameReturnsTrue() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertTrue(u.equals(u), "equals should return true with the same object");
    }

    @Test
    void testEqualsWithSameMailReturnsTrue() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        Utilisateur u2 = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertTrue(u.equals(u2), "equals should return true with the same object");
    }

    @Test
    void testEqualsWithDifferentMailReturnsFalse() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        Utilisateur u2 = new UtilisateurImpl(OTHER_VALID_EMAIL, VALID_PASSWORD);
        assertFalse(u.equals(u2), "equals should return true with the same object");
    }

    @Test
    void testEqualsWithDifferentClassReturnsFalse() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        String u2 = "";
        assertFalse(u.equals(u2), "equals should return true with the same object");
    }

    @Test
    void testHashcodeDoesNotThrow() {
        Utilisateur u = new UtilisateurImpl(VALID_EMAIL, VALID_PASSWORD);
        assertDoesNotThrow(u::hashCode, "hashCode should not throw");
    }
    
    private static class UtilisateurImpl extends Utilisateur {
        public UtilisateurImpl(String email, String password) {
            super(email, password);
        }
    }
}
