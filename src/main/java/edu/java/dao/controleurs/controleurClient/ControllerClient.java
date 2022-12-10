package edu.java.dao.controleurs.controleurClient;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import edu.java.dao.models.modelClient.Client;
import edu.java.dao.models.modelClient.ModelClient;

public class ControllerClient {
    private static ControllerClient CtrC_Instance = null;
    private static ModelClient Model_Instance = null;

    private ControllerClient(){}

    public static synchronized ControllerClient getControllerClient(){
        try{
            if(CtrC_Instance == null){
                CtrC_Instance = new ControllerClient();
                Model_Instance = ModelClient.getClientModel();
            }
            return CtrC_Instance;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public String CtrC_Enregistrer(Client client){
        return Model_Instance.MdlC_Enregistrer(client);
    }

    public List<Client> CtrC_GetAll(){
        return Model_Instance.MdlC_GetAll();
    }

    public String CtrC_Modifier(Client client){
        return Model_Instance.MdlC_Modifier(client);
    }

    public boolean CtrC_ChercherID(Client client){
        return Model_Instance.MdlC_ChercherID(client);
    }

    public List<Client> CtrC_Chercher(Client client){
        return Model_Instance.MdlC_Chercher(client);
    }

    public String CtrC_Supprimer(Client client){
        return Model_Instance.MdlC_Supprimer(client);
    }
}
