package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

public class AuthentificationService {
    
    private Map<String, Utilisateur> utilisateursDb = new HashMap<>();

    public void enregistrerUtilisateur(Utilisateur utilisateur) {
        utilisateursDb.put(utilisateur.getEmail(), utilisateur);
    }

    /**
     * Tente de connecter un utilisateur.
     * @return L'utilisateur connecté, ou null si échec.
     */
    public Utilisateur login(String email, String password) {
        Utilisateur user = utilisateursDb.get(email);
        
        if (user != null && user.verifierMotDePasse(password)) {
            return user;
        }
        return null; // Échec de connexion (mauvais email ou mauvais mot de passe)
    }
}
