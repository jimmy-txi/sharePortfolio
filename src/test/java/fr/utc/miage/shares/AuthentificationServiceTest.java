package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthentificationServiceTest {

    private AuthentificationService authService;
    private Administrateur adminTest;

    @BeforeEach
    void setUp() {
        authService = new AuthentificationService();
        // Création d'un administrateur pour les tests
        adminTest = new Administrateur("Navarre", "David", "admin@irit.fr", "motdepasseSecurise123");
        authService.enregistrerUtilisateur(adminTest);
    }

    @Test
    void testLogin_Succes_AdministrateurValide() {
        // Sous-problème 1 : Connexion réussie avec les bons identifiants
        Utilisateur userConnecte = authService.login("admin@irit.fr", "motdepasseSecurise123");
        
        assertNotNull(userConnecte, "L'utilisateur devrait être connecté");
        assertTrue(userConnecte instanceof Administrateur, "L'utilisateur connecté doit être un Administrateur");
        assertEquals("admin@irit.fr", userConnecte.getEmail());
    }

    @Test
    void testLogin_Echec_MauvaisMotDePasse() {
        // Sous-problème 2 : Refus si le mot de passe est faux
        Utilisateur userConnecte = authService.login("admin@irit.fr", "fauxMotDePasse");
        
        assertNull(userConnecte, "La connexion doit échouer avec un mauvais mot de passe");
    }

    @Test
    void testLogin_Echec_EmailInexistant() {
        // Sous-problème 3 : Refus si l'email n'existe pas
        Utilisateur userConnecte = authService.login("inconnu@irit.fr", "motdepasseSecurise123");
        
        assertNull(userConnecte, "La connexion doit échouer avec un email inconnu");
    }
}