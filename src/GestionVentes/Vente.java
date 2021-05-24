package GestionVentes;

import GestionClients.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vente {
    private long id;
    private double total;
    private Client client;
    private String date;
    private List<LigneCommande> ligneCommandes;

    public Vente(int id, double total, Client client) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.date= String.valueOf(LocalDate.now());
    }
    public Vente(Client client,List<LigneCommande> ligneCommandes ) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.date= String.valueOf(LocalDate.now());
    }

    public Vente() {
        this.ligneCommandes=new ArrayList<>();
        this.date= String.valueOf(LocalDate.now());
    }

    public Vente(long id, Client client,String date) {
        this.id = id;
        this.client = client;
        this.date=date;
        CalulerTotal();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getTotal() {
        CalulerTotal();
        return total;
    }
    public Vente(long id, double total, Client client,String date,List<LigneCommande> cmd) {
        this.id = id;
        this.total = total;
        this.client = client;
        this.ligneCommandes=cmd;
        this.date=date;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCommande> getLcmds() {
        return ligneCommandes;
    }
    public void setLcmds(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public  void CalulerTotal(){
        for (LigneCommande lCmd: ligneCommandes) {
            total += lCmd.getStotal();
        }}

    public void addLigneCmd(LigneCommande ligneCommande){
            ligneCommandes.add(ligneCommande);
        }




}

