package edu.java.dao.models.modelClient;

public class Client {
    private String matricule;
    private String nom;
    private String permis;
    private String adresse;
    private String telephone;

    public Client(){}
    
    public Client(String matricule, String nom, String permis, String adresse, String telephone) {
        this.matricule = matricule;
        this.nom = nom;
        this.permis = permis;
        this.adresse = adresse;
        this.telephone = telephone;
    }
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPermis() {
        return permis;
    }
    public void setPermis(String permis) {
        this.permis = permis;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @Override
    public String toString() {
        return "Client [matricule=" + matricule + ", nom=" + nom + ", permis=" + permis + ", adresse=" + adresse
                + ", telephone=" + telephone + "]";
    }

    
}
