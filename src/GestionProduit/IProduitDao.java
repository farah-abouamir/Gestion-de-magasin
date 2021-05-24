package GestionProduit;

import GestionProduit.IDao;

import java.util.List;

public interface IProduitDao extends IDao<Produit> {

    public List<Produit> findAll(String designation);

}
