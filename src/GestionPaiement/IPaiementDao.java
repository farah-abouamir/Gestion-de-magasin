package GestionPaiement;

import GestionProduit.IDao;
import GestionVentes.Vente;

import java.util.List;

public interface IPaiementDao extends IDao<Paiement> {
    public List<Type> getAllType();
    public List<Paiement> getAllPaim(Vente v);
}
