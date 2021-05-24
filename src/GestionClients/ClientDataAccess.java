package GestionClients;

import java.sql.*;
import java.util.*;


    public class ClientDataAccess {

        String db="magasin";
        String user="root";
        String pwd="";
        String url="jdbc:mysql://localhost:3306/"+db;
        Connection connection=null;

        public ClientDataAccess(){
            try {
                connection= DriverManager.getConnection(url, user, pwd);
                System.out.println("Connected...");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        List<Client> getAll(){
            PreparedStatement pst=null;
            ResultSet rs;
            List<Client>list=new ArrayList<Client>();
            String sql="select*from client";
            try {
                pst=connection.prepareStatement(sql);
                System.out.println("Succes d'exec de la requete!!");
                rs=pst.executeQuery();
                while(rs.next()) {
                    list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse")));
                }
            }catch(SQLException exp) {
                System.out.println(exp.getMessage());
            }
            return list;
        }

        List<Client>getClientByKeyWord(String nom){
            PreparedStatement pst=null;
            ResultSet rs;
            List<Client>list=new ArrayList<Client>();
            String sql="select*from produit where client like ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, nom+"%");
                System.out.println("Succes d'exec de la requete!!");
                rs=pst.executeQuery();
                while(rs.next()) {
                    list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse")));
                }
            }catch(SQLException exp) {
                System.out.println(exp.getMessage());
            }
            return list;
        }
    }

