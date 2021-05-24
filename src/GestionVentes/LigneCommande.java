package GestionVentes;

import GestionProduit.Produit;

public class LigneCommande {

    private Long id;
    private Vente vente;
    private Produit p;
    private int qte;
    private double stotal;

    public LigneCommande(long id, Produit produit_id, int qte, double stotal) {
        this.id=id;
        this.p=produit_id;
        this.qte=qte;
        this.stotal=stotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    CalculerSousTotal();
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.stotal-=this.p.getPrix()*this.qte;
        this.qte = qte;
        this.stotal+=this.p.getPrix()*this.qte;

    }

    public double getStotal() {
        CalculerSousTotal();
        return stotal;
    }

    public void setStotal(double stotal) {
        this.stotal = stotal;
    }

    public LigneCommande( Produit p, int qte, Vente v) {
        this.p = p;
        this.qte = qte;
        this.vente=v;
        CalculerSousTotal();
    }
    public LigneCommande( Produit p, int qte) {

        this.p = p;
        this.qte = qte;

        CalculerSousTotal();
    }



    public void CalculerSousTotal(){
        stotal= qte* p.getPrix();
    }

    public String getProduitDesignation() {
        return p.getDesignation();
    }

    public void setProduitDesignation(String des) {
        this.p.setDesignation(des);
    }

    public double getProduitPrix() {
        return p.getPrix();
    }

    public void setProduitPrix(double prix) {
        this.p.setPrix(prix);
    }
}