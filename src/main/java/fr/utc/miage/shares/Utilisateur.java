package fr.utc.miage.shares;

public abstract class Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public Utilisateur(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifierMotDePasse(String motDePasseSaisi) {
        return this.password.equals(motDePasseSaisi);
    }

    

}
