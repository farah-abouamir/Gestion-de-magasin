package GestionPaiement;

import GestionProduit.AbstractDao;
import GestionVentes.IVenteDao;
import GestionVentes.Vente;
import GestionVentes.VenteDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaiementDaoImp extends AbstractDao implements IPaiementDao{
    IVenteDao venteDao= new VenteDaoImpl();
    @Override
    public Paiement find(long id) {
        return null;
    }

    @Override
    public void create(Paiement p) {
        PreparedStatement pst = null;
        String sql="insert into paiement(montant,date,id_vente,id_type) values('"+p.getMontant()+"','"+p.getDate()+"','"+p.getVente().getId()+"','"+p.getType().getId()+"')";
        try {
            pst = connection.prepareStatement(sql);
            pst.execute(sql);
            System.out.println("Succes d'exec de la requete!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Paiement obj) {

    }

    @Override
    public List<Paiement> findAll() {
        return null;
    }

    @Override
    public List<Type> getAllType() {

            List<Type> types=new ArrayList<Type>();
            PreparedStatement pst=null;
            ResultSet rs=null;
            String sql= "select * from  type";
            try {
                pst = connection.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs!=null){
                    while(rs.next()){
                        Type t=new Type(rs.getLong("id"), rs.getString("type"));
                        types.add(t);
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            return types;
        }

    @Override
    public List<Paiement> getAllPaim(Vente v) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from paiement natural join type where id_vente=" + v.getId();
        List<Paiement> paiementList = new ArrayList<>();
       try{
        pst = connection.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs!=null){
            while(rs.next()){
                Type t=new Type(rs.getLong("id"), rs.getString("type"));
                Paiement paiement=new Paiement(rs.getLong("id"),venteDao.find(rs.getLong("id_Vente")), rs.getDouble("montant"),String.valueOf(rs.getDate("date")),t);
                paiementList.add(paiement);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
			return paiementList;
    }

}

