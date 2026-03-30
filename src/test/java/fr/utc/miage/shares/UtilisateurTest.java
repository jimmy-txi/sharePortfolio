package fr.utc.miage.shares;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurTest {

    private static final String VALID_EMAIL = "user@mail.fr";
    private static final String INVALID_EMAIL = "invalid";
    private static final String VALID_PASSWORD = "password";

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
        assertTrue(u.verifierMotDePasse(""), "getEmail should return false with a wrong password");
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
    
    private static class UtilisateurImpl extends Utilisateur {
        public UtilisateurImpl(String email, String password) {
            super(email, password);
        }
    }
}
