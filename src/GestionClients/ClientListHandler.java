package GestionClients;


import GestionVentes.FormVenteWindow;
import GestionVentes.LigneCommande;

import java.util.List;

public class ClientListHandler {

        ClientListWindow listWindow = null;
        FormVenteWindow venteWindow= null;
        IClientDao pdao = new ClientDaoImp();
        public ClientListHandler(ClientListWindow listWindow1) {
            this.listWindow = listWindow1;
        }
        public void updateClientListWindow() {
            List<Client> list = pdao.findAll();
            listWindow.clientObservableList.addAll(list);
        }

        public void deleteClient(){
            Client c= listWindow.clientTableView.getSelectionModel().getSelectedItem();
            pdao.delete(c.getId());
            listWindow.clientObservableList.remove(c);

        }

    }
