package GestionVentes;

import GestionProduit.AbstractDao;
import GestionProduit.IDao;
import GestionProduit.IProduitDao;
import GestionProduit.ProduitDaoImp;
import javafx.scene.control.Label;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneCmdDaoImp extends AbstractDao implements ILigneCmdDao {
    IProduitDao produitDao= new ProduitDaoImp();

    @Override
    public void create(LigneCommande c) {
    }

    @Override
    public LigneCommande find(long id) {
        return null;
    }

    @Override
    public void create(LigneCommande c, Vente v) {
        PreparedStatement pst=null;
        ResultSet rs;
            String sql="insert into lignecommande(qte,sousTotal,id_produit,id_vente) values('"+c.getQte()+"','"+c.getStotal()+"','"+c.getP().getId()+"','"+v.getId()+"')";
            try {
                pst=connection.prepareStatement(sql);
                System.out.println("Succes d'exec de la requete!!");
                if(!pst.execute(sql)){
                    c.setId(this.getLastId());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    private int getLastId() {
        PreparedStatement pst=null;
        ResultSet rs;
        String sql="select MAX(id) from lignecommande";
        try {
            pst=connection.prepareStatement(sql);
            System.out.println("Succes d'exec de la requete!!");
            rs=pst.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }





    @Override
    public void delete(long id) {

    }

    @Override
    public void update(LigneCommande obj) {

    }

    @Override
    public List<LigneCommande> findAll() {
        return null;
    }

    @Override
    public List<LigneCommande> findAll(long id) {
        ArrayList<LigneCommande> lcmds=new ArrayList<>();
        PreparedStatement pst=null;
        ResultSet rs;
        String sql="select *from lignecommande where id_vente="+id;
        try {
            pst=connection.prepareStatement(sql);
            rs=pst.executeQuery(sql);
            while(rs.next()) {
                lcmds.add(new LigneCommande(rs.getLong("id"),produitDao.find(rs.getLong("id_produit")),rs.getInt("qte"),rs.getDouble("sousTotal")));
            }
            return lcmds;
        } catch (SQLException e) {
            System.out.println("findall");
            // e.getMessage();

        }
        return null;
    }
    }

