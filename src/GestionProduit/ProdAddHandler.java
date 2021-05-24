package GestionProduit;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ProdAddHandler {
    IProduitDao pdao = new ProduitDaoImp();

    ProdAddHandler ProdAddtWindow= null;

        FormProduiWindow formProd=null;
        public ProdAddHandler(FormProduiWindow formProd){
            this.formProd =formProd;
        }

    public  void addClick(){
            String designation =formProd.ProdDesignTextField.getText();
            int qte =Integer.valueOf(formProd.ProdQteTextField.getText());
            double prix=Double.valueOf(formProd.ProdPrixTextField.getText());
            LocalDate date= formProd.ProdDateSaisie.getValue();
            Date dateSql = Date.valueOf(date);
            Produit p=new Produit(0,designation, qte,prix,date);
            pdao.create(p);


    }
    public void updateProdListWindow(){
        IProduitDao pdao = new ProduitDaoImp();
        List<Produit> list= pdao.findAll();

    }

}
