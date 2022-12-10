package edu.java.dao;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import edu.java.dao.controleurs.controleurClient.ControllerClient;
import edu.java.dao.models.modelClient.Client;

public class TabbedPane implements ActionListener{
    private JFrame jframe;
    private JPanel paneClient;
    private JPanel paneVoiture;
    private JPanel paneLocation;
    private JTabbedPane jtabbedpane;
    private JPanel jpanel;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField field1, field2, field3, field4, field5;
    private JTable jtable;
    private JScrollPane jscrollpane;
    private JButton b1, b2, b3, b4, b5, b6;
    private JToolBar toolbar;
    private JPanel buttonPanel;
    private DefaultTableModel tablemodel;
    private ControllerClient CtrC;

    public TabbedPane(){
        
        CtrC = ControllerClient.getControllerClient();
    //jtabbedpane   
        jframe = new JFrame(); 
        jframe.setTitle("Gestion de Location de Voiture");
        // JPanel mainpane = new JPanel();
        // mainpane.setLayout(new GridLayout(1,1,0,0));
        jtabbedpane = new JTabbedPane(); 
        paneClient = new JPanel();
        paneVoiture = new JPanel();
        paneLocation = new JPanel();
        paneClient.setLayout(new BoxLayout(paneClient, BoxLayout.Y_AXIS));
        // paneClient.setLayout(new BorderLayout());

        jtabbedpane.setBounds(0,0,800,600);  
        // jtabbedpane.setPreferredSize(new Dimension(800,600));

        jtabbedpane.add("Gestion de Client", paneClient);  
        jtabbedpane.add("Gestion de Voiture", paneVoiture);  
        jtabbedpane.add("Gestion de Location", paneLocation);    

        // mainpane.add(jtabbedpane);
        jframe.add(jtabbedpane);  
        jframe.setSize(800,600); 
        jframe.setLocationRelativeTo(null);
        jframe.setLayout(null);  
        jframe.setVisible(true); 
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

//FormClient
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(7,4,0,0));

        label1= new JLabel("Matricule: ");
        label2= new JLabel("Nom: ");
        label3= new JLabel("Permis: ");
        label4= new JLabel("Adresse: ");
        label5= new JLabel("Téléphone: ");

        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        field4 = new JTextField();
        field5 = new JTextField();


        jpanel.add(new JLabel(" "));
        jpanel.add(new JLabel(" "));
        jpanel.add(new JLabel(" "));
        jpanel.add(new JLabel(" "));

        jpanel.add(new JLabel(" "));
        jpanel.add(label1);
        jpanel.add(field1);
        jpanel.add(new JLabel(" "));

        jpanel.add(new JLabel(" "));
        jpanel.add(label2);
        jpanel.add(field2);
        jpanel.add(new JLabel(" "));

        jpanel.add(new JLabel(" "));
        jpanel.add(label3);
        jpanel.add(field3);
        jpanel.add(new JLabel(" "));

        jpanel.add(new JLabel(" "));
        jpanel.add(label4);
        jpanel.add(field4);
        jpanel.add(new JLabel(" "));

        jpanel.add(new JLabel(" "));
        jpanel.add(label5);
        jpanel.add(field5);
        jpanel.add(new JLabel(" "));

//toolbar
        toolbar = new JToolBar();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,6,0,0));

        b1 = new JButton("Lister");
        b2 = new JButton("Chercher");
        b3 = new JButton("Enregistrer");
        b4 = new JButton("Modifier");
        b5 = new JButton("Supprimer");
        b6 = new JButton("Réinitialiser");
        
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);

        toolbar.add(buttonPanel);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

                
//JTable

    tablemodel = new DefaultTableModel();
    tablemodel.addColumn("Matricule");
    tablemodel.addColumn("Nom");
    tablemodel.addColumn("Permis");
    tablemodel.addColumn("Adresse");
    tablemodel.addColumn("Téléphone");

    jtable = new JTable(tablemodel);
    jtable.setAutoCreateRowSorter(true); 
    jscrollpane = new JScrollPane(jtable);

        jtabbedpane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if(jtabbedpane.getSelectedIndex()==1){

                }
                else if(jtabbedpane.getSelectedIndex()==2){

                }
                else{

                }
            }      
        });

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                jtabbedpane.setSelectedIndex(0);
                paneClient.add(toolbar);
                paneClient.add(jpanel);
                paneClient.add(jscrollpane);
                jtabbedpane.validate();
            }
        });

        jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int rowIndex = 0;
                if (!event.getValueIsAdjusting() && jtable.getSelectedRow() != -1) {
                    rowIndex = jtable.getSelectedRow();
                    String data[] = new String[jtable.getColumnCount()];
                    for (int i = 0; i < jtable.getColumnCount(); i++) {   
                    data[i] = jtable.getValueAt(rowIndex, i).toString();
                    }
                    field1.setText(data[0]);
                    field2.setText(data[1]);
                    field3.setText(data[2]);
                    field4.setText(data[3]);
                    field5.setText(data[4]);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent evt){

        if(evt.getSource() == b1){
            List<Client> listeClient = CtrC.CtrC_GetAll();
            tablemodel.setRowCount(0);
            for(Client client: listeClient){
                tablemodel.addRow(new Object[]{client.getMatricule(),client.getNom(),client.getPermis(),client.getAdresse(),client.getTelephone()});
            }
        }
        else if(evt.getSource() == b2){
            String matricule = field1.getText();
            String nom = field2.getText();
            String permis = field3.getText();
            Client unClient = new Client();
            unClient.setMatricule(matricule);
            unClient.setNom(nom);
            unClient.setPermis(permis);
            List<Client> listeClient = CtrC.CtrC_Chercher(unClient);
            if(listeClient == null){
                JOptionPane.showMessageDialog(null, "Client n'exist pas", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                tablemodel.setRowCount(0);
                for(Client client: listeClient){
                    tablemodel.addRow(new Object[]{client.getMatricule(),client.getNom(),client.getPermis(),client.getAdresse(),client.getTelephone()});
                }
            }
        }
        else if(evt.getSource() == b3){
            String matricule = field1.getText();
            String nom = field2.getText();
            String permis = field3.getText();
            String adresse = field4.getText();
            String telephone = field5.getText();
            Client unClient = new Client(matricule, nom, permis, adresse, telephone);
            if(CtrC.CtrC_ChercherID(unClient)){
                JOptionPane.showMessageDialog(null, "Client exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String message = CtrC.CtrC_Enregistrer(unClient);
                JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(evt.getSource() == b4){
            String matricule = field1.getText();
            String nom = field2.getText();
            String permis = field3.getText();
            String adresse = field4.getText();
            String telephone = field5.getText();
            Client unClient = new Client(matricule, nom, permis, adresse, telephone);
            if(!CtrC.CtrC_ChercherID(unClient)){
                JOptionPane.showMessageDialog(null, "Client n'exist pas", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String message = CtrC.CtrC_Modifier(unClient);
                JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(evt.getSource() == b5){
            String message = "";
            Client client = new Client();
            client.setMatricule(field1.getText());
            if(!CtrC.CtrC_ChercherID(client)){
                JOptionPane.showMessageDialog(null, "Client n'exist pas", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                message = CtrC.CtrC_Supprimer(client);
                JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
}
