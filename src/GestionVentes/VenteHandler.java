package GestionVentes;

import GestionClients.Client;
import GestionClients.ClientDaoImp;
import GestionClients.IClientDao;

import java.util.List;

public class VenteHandler {

    FormVenteWindow venteWindow= null;
    IClientDao clientDao= new ClientDaoImp();



    public VenteHandler(FormVenteWindow venteWindow) {
        this.venteWindow = venteWindow;

    }


    public void AddVenteHandler(){

        List<Client> clients= clientDao.findAll();
        System.out.println("in");
        for (Client c :clients){
          if(  c.getNom()== this.venteWindow.NomClientTextField.getText()){
              this.venteWindow.PrenomClientTextField.setText(c.getPrenom());
              System.out.println("trouvé");
              break;
          }
        }

    }
}
