package GestionClients;

import GestionProduit.IDao;


import java.util.List;

public interface IClientDao extends IDao<Client> {
    public List<Client> findAll(String nom);

}
