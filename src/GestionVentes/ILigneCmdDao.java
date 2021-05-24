package GestionVentes;


import GestionProduit.IDao;

import java.util.List;

public interface ILigneCmdDao extends IDao<LigneCommande> {

    public List<LigneCommande> findAll(long id);
    public void create(LigneCommande p, Vente v);

}
