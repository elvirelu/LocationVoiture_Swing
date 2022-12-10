package edu.java.dao.models.modelClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelClient {
    private static Connection conn = null;
    private static ModelClient instanceModel = null;
    private static final String URL_BD = "jdbc:mysql://localhost/bdLocationVoiture";
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String ENREGISTRER = "INSERT INTO clients VALUES(?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM clients";
    private static final String MODIFIER = "UPDATE clients SET nom=?, permis=?, adresse=?, telephone=? WHERE matricule=?";
    private static final String CHERCHER = "SELECT * FROM clients WHERE matricule=? or nom=? or permis=?";
    private static final String CHERCHERID = "SELECT * FROM clients WHERE matricule=?";
    private static final String SUPPRIMER = "DELETE FROM clients WHERE matricule=?";

    private ModelClient(){}

    public static synchronized ModelClient getClientModel(){
        try{
            if(instanceModel == null){
                instanceModel = new ModelClient();
                // conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            }
            return instanceModel;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public String MdlC_Enregistrer(Client client){
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER);
            stmt.setString(1,client.getMatricule());
            stmt.setString(2,client.getNom());
            stmt.setString(3,client.getPermis());
            stmt.setString(4,client.getAdresse());
            stmt.setString(5,client.getTelephone());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return "Client bien enregistré";
    }

    public List<Client> MdlC_GetAll(){
        PreparedStatement stmt = null;
        List<Client> listeClient = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Client client = new Client();
                client.setMatricule(rs.getString(1));
                client.setNom(rs.getString(2));
                client.setPermis(rs.getString(3));
                client.setAdresse(rs.getString(4));
                client.setTelephone(rs.getString(5));
                listeClient.add(client);
            }
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listeClient;
    }

    public String MdlC_Modifier(Client client){
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setString(1,client.getNom());
            stmt.setString(2,client.getPermis());
            stmt.setString(3,client.getAdresse());
            stmt.setString(4,client.getTelephone());
            stmt.setString(5,client.getMatricule());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return "Client bien modifié";
    }

    public boolean MdlC_ChercherID(Client client){
        PreparedStatement stmt = null;
        boolean trouve;
        try{
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(CHERCHERID);
            stmt.setString(1, client.getMatricule());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                trouve = true;
            }
            else{
                trouve = false;
            }
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return trouve;
    }

    public List<Client> MdlC_Chercher(Client client){
        PreparedStatement stmt = null;
        List<Client> listeClient = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(CHERCHER);
            stmt.setString(1, client.getMatricule());
            stmt.setString(2, client.getNom());
            stmt.setString(3, client.getPermis());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()==false){
                listeClient = null;
            }else{
                do{
                Client unClient = new Client();
                unClient.setMatricule(rs.getString(1));
                unClient.setNom(rs.getString(2));
                unClient.setPermis(rs.getString(3));
                unClient.setAdresse(rs.getString(4));
                unClient.setTelephone(rs.getString(5));
                listeClient.add(unClient);
                }while(rs.next());
            }
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listeClient;
    }

    public String MdlC_Supprimer(Client client){
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setString(1,client.getMatricule());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return "Client bien supprimé";
    }
}
