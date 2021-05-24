package GestionVentes;

import GestionProduit.IDao;

import java.util.List;

public interface IVenteDao extends IDao<Vente> {
    List<LigneCommande> findAll(int v);
    public long getLastId();
}
