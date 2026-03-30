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

class AdministrateurTest {

    private static final String VALID_EMAIL = "admin@irit.fr";
    private static final String INVALID_EMAIL = "invalid";
    private static final String VALID_PASSWORD = "password";

    public static Administrateur getDefaultAdministrateur() {
        return new Administrateur(VALID_EMAIL, VALID_PASSWORD);
    }

    @Test
    void testConstructorWithCorrectParamsDoesNotThrows() {
        assertDoesNotThrow(() -> new Administrateur(VALID_EMAIL, VALID_PASSWORD),
                "Constructor should not throw when email and password are valid");
    }

    @Test
    void testConstructorWithInvalidEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Administrateur(INVALID_EMAIL, VALID_PASSWORD),
                "Constructor should throw when email is invalid");
    }

    @Test
    void testConstructorWithEmptyEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Administrateur("", VALID_PASSWORD),
                "Constructor should throw when email is invalid");
    }

    @Test
    void testConstructorWithNullEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Administrateur(null, VALID_PASSWORD),
                "Constructor should throw when email is null");
    }

    @Test
    void testConstructorWithNullPasswordThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Administrateur(VALID_EMAIL, null),
                "Constructor should throw when password is null");
    }

    @Test
    void testConstructorWithEmptyPasswordThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Administrateur(VALID_EMAIL, ""),
                "Constructor should throw when password is null");
    }
}