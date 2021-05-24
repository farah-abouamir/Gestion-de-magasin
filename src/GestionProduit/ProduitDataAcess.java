package GestionProduit;

import GestionProduit.Produit;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ProduitDataAcess {
    //charger le driver
    // se connecter a la bd
    // ecrire et envoyerrequete sql
    //recupere les produits
    // renvoyer les produits : List<produit>


     String db="magasin";
     String user="root";
     String pwd="";
     String url="jdbc:mysql://localhost:3306/"+db;
     Connection connection=null;

    public ProduitDataAcess(){
        try {
            connection=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connected...");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    List<Produit>getAll(){
        PreparedStatement pst=null;
        ResultSet rs;
        List<Produit>list=new ArrayList<Produit>();
        String sql="select*from produit";
        try {
            pst=connection.prepareStatement(sql);
            System.out.println("Succes d'exec de la requete!!");
            rs=pst.executeQuery();
            while(rs.next()) {
                //System.out.println(rs.getLong("id")+" "+rs.getString("designation"));Date date=rs.getDate("date");
                Date date =rs.getDate("date");
                list.add(new Produit(rs.getLong("id"),rs.getString("designation"),rs.getInt("qte"),rs.getDouble("prix"), date.toLocalDate()));
            }
        }catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
        //pst
        return list;
    }

    List<Produit>getProduitByKeyWord(String des){
        PreparedStatement pst=null;
        ResultSet rs;
        List<Produit>list=new ArrayList<Produit>();
        String sql="select*from produit where designation like ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, des+"%");
            System.out.println("Succes d'exec de la requete!!");
            rs=pst.executeQuery();
            while(rs.next()) {
                Date date=rs.getDate("date");
                list.add(new Produit(rs.getLong("id"),rs.getString("designation"),rs.getInt("qte"),rs.getDouble("prix"), date.toLocalDate()));
            }
        }catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
}