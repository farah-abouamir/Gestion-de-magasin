package GestionClients;

import java.util.List;

public class ClientAddHandler{

    IClientDao pdao = new ClientDaoImp();

    ClientAddHandler  ClientAddtWindow= null;
    FormClientWindow formClient=null;

    public ClientAddHandler(FormClientWindow formClient){
        this.formClient=formClient;
    }

    public  void addClick(){
        String  nom =formClient.ClientNomTextField.getText();
        String prenom =formClient.ClientPrenomTextField.getText();
        String telephone =formClient.ClientTeleTextField.getText();
        String adresse =formClient.ClientAdresseTextField.getText();

        Client c=new Client(0,nom, prenom,telephone,adresse);
        pdao.create(c);

    }
    public void updateClientListWindow(){
        IClientDao pdao = new ClientDaoImp();
        List<Client> list= pdao.findAll();

    }

}
