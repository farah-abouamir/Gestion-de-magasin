package GestionProduit;

import java.time.LocalDate;


public class Produit {
    private long id;
    private String designation;
    private int qte;
    private double prix;
    private LocalDate date;
    private double total;


    public Produit(long id, String designation, int qte, double prix, LocalDate date) {
        this.id = id;
        this.designation = designation;
        this.qte = qte;
        this.prix = prix;
        this.date = date;
        this.CalculerTotal();

    }
    public Produit(String designation, int qte, double prix, LocalDate date) {
        this.designation = designation;
        this.qte = qte;
        this.prix = prix;
        this.date = date;
        this.CalculerTotal();

    }

    public long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public int getQte() {
        return qte;
    }

    public double getPrix() {
        return prix;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public void CalculerTotal(){
        this.total=this.prix*this.qte;
    }

}
