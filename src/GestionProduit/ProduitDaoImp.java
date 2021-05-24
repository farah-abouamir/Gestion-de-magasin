package GestionProduit;

import GestionProduit.AbstractDao;
import GestionProduit.IProduitDao;
import GestionProduit.Produit;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImp extends AbstractDao implements IProduitDao {

    @Override
    public Produit find(long id) {
        PreparedStatement pst=null;
        ResultSet rs;

        String sql="select * from produit where id="+id;
        try {
            pst=connection.prepareStatement(sql);
            System.out.println("Succes d'exec de la requete!!");
            rs=pst.executeQuery();
            if(rs.next()) {
                Date date =rs.getDate("date");
                return new Produit(rs.getLong("id"),rs.getString("designation"),rs.getInt("qte"),rs.getDouble("prix"),date.toLocalDate());
            }
        }catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public void create(Produit obj) {
        PreparedStatement pst=null;
        String sql="insert into produit(designation,qte,prix,date,total) values (?,?,?,?,?)";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,obj.getDesignation());
            pst.setInt(2,obj.getQte());
            pst.setDouble(3,obj.getPrix());
            Date date=Date.valueOf(obj.getDate());
            pst.setDate(4,date);
            pst.setDouble(5,obj.getTotal());
            System.out.println("Succes d'execution de la requete");
            pst.execute();
        }catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }


    @Override
    public void delete(long id) {
        PreparedStatement pst=null;
        String sql="delete from produit where id="+id;
        try {
            pst=connection.prepareStatement(sql);
            System.out.println("Succes d'exec de la requete!!");
            if(pst.execute(sql)){
                System.out.println("produit supprimée");
            }
        }catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void update(Produit p) {
        PreparedStatement pst=null;
        String sql="update produit set (designation,qte,prix,date,total) values (?,?,?,?,?)  where id = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1,p.getDesignation());
            pst.setInt(2,p.getQte());
            pst.setDouble(3,p.getPrix());
            Date date=Date.valueOf(p.getDate());
            pst.setDate(4,date);
            pst.setDouble(5,p.getTotal());
            System.out.println("Succes d'execution de la requete update");
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produit> findAll() {
            PreparedStatement pst=null;
            ResultSet rs;
            List<Produit>list=new ArrayList<Produit>();
            String sql="select*from produit";
            try {
                pst=connection.prepareStatement(sql);
                System.out.println("Succes d'exec de la requete!!");
                rs=pst.executeQuery();
                while(rs.next()) {

                    Date date =rs.getDate("date");
                    list.add(new Produit(rs.getLong("id"),rs.getString("designation"),rs.getInt("qte"),rs.getDouble("prix"), date.toLocalDate()));
                }
            }catch(SQLException exp) {
                System.out.println(exp.getMessage());
            }

            return list;
        }


    @Override
    public List<Produit> findAll(String designation) {
        PreparedStatement pst=null;
        ResultSet rs;
        List<Produit>list=new ArrayList<Produit>();
        String sql="select * from produit where designation like ?";

        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, designation+"%");
            System.out.println("Succes d'exec de la requete!!");
            rs=pst.executeQuery();
            while(rs.next()) {
                Date date =rs.getDate("date");
                list.add(new Produit(rs.getLong("id"),rs.getString("designation"),rs.getInt("qte"),rs.getDouble("prix"), date.toLocalDate()));
            }
        }catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;

    }
}
