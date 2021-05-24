package GestionProduit;

import GestionClients.Client;
import GestionProduit.IProduitDao;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class ProdListHandler {

    ProduitsListWindow listWindow = null;
    IProduitDao pdao = new ProduitDaoImp();
    public ProdListHandler(ProduitsListWindow listWindow1) {
        this.listWindow = listWindow1;
    }

    public void updateProdListWindow() {
        List<Produit> list = pdao.findAll();
        listWindow.produitObservableList.addAll(list);
        this.CalculerTotal();
    }
    public void CalculerTotal() {
        double total = 0;
        for (Produit p : listWindow.produitObservableList) {
            total += p.getPrix() * p.getQte();
        }
        listWindow.totalLabelVal.setText(total + "");
    }

    public void deleteProduit(){
        Produit p= listWindow.prodTableView.getSelectionModel().getSelectedItem();
        pdao.delete(p.getId());
        listWindow.produitObservableList.remove(p);
    }

    public void updateProduit() {
        Produit p= listWindow.prodTableView.getSelectionModel().getSelectedItem();
        p.setDesignation(listWindow.Designcolumn.getText());
        pdao.update(p);
        listWindow.produitObservableList.set(listWindow.produitObservableList.indexOf(p),p);


    }



}