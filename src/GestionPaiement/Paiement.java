package GestionPaiement;

import GestionVentes.Vente;

import java.time.LocalDate;

public class Paiement {

        private long id;
        private Vente vente;
        private double montant;
        private String date;
        private Type type;

        public Paiement(long id, Vente v, double montant, String date, Type type) {
            this.id = id;
            this.vente = v;
            this.montant = montant;
            this.date = date;
            this.type = type;
        }

        public Paiement(Vente id_vente,double montant,Type type) {
            this.vente=id_vente;
            this.montant=montant;
            this.date=String.valueOf(LocalDate.now());
            this.type=type;

        }

        public Paiement() {
            this.date=String.valueOf(LocalDate.now());
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

        public double getMontant() {
            return montant;
        }

        public void setMontant(double montant) {
            this.montant = montant;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }


