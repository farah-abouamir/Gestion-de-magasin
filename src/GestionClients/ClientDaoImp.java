package GestionClients;

import GestionProduit.AbstractDao;

import java.sql.*;
import java.util.*;

public class ClientDaoImp extends AbstractDao implements IClientDao {

    @Override
    public Client find(long id) {
        PreparedStatement pst = null;
        ResultSet rs;

        String sql = "select * from client where id=" + id;
        try {
            pst = connection.prepareStatement(sql);
            System.out.println("Succes d'exec de la requete!!");
            rs = pst.executeQuery();
            if (rs.next()) {
                return new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }


    @Override
    public void create(Client obj) {
        PreparedStatement pst = null;
        String sql = "insert into client(nom,prenom,telephone,adresse) values (?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getNom());
            pst.setString(2, obj.getPrenom());
            pst.setString(3, obj.getTelephone());
            pst.setString(4, obj.getAdresse());
            System.out.println("Succes d'execution de la requete");
            pst.execute();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }


    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        String sql = "delete from client where id=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1,id);
            System.out.println("Succes d'exec de la requete delete");
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void update(Client obj) {

    }

    @Override
    public List<Client> findAll() {
        PreparedStatement pst = null;
        ResultSet rs;
        List<Client> list = new ArrayList<Client>();
        String sql = "select*from client";
        try {
            pst = connection.prepareStatement(sql);
            System.out.println("Succes d'exec de la requete findAll");
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }


    @Override
    public List<Client> findAll(String nom) {
        PreparedStatement pst = null;
        ResultSet rs;
        List<Client> list = new ArrayList<Client>();
        String sql = "select * from client where nom like ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,nom+"%");
          System.out.println("Succes d'exec de la requete!!");
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;

    }

}