package GestionVentes;

import GestionClients.ClientDaoImp;
import GestionClients.IClientDao;
import GestionProduit.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VenteDaoImpl extends AbstractDao implements  IVenteDao{

   IClientDao clientDao= new ClientDaoImp();
   ILigneCmdDao ligneCmdDao= new LigneCmdDaoImp();
    @Override
    public Vente find(long id) {

        PreparedStatement pst=null;
        ResultSet rs;
        String sql="select * from vente where id="+id;
        try {
            pst=connection.prepareStatement(sql);
            rs=pst.executeQuery(sql);
            Vente vente;
            if(rs.next()) {
                System.out.println("inside");
                vente= new Vente(rs.getLong("id"), rs.getDouble("total"), (clientDao.find(rs.getLong("id"))),rs.getString("date") ,ligneCmdDao.findAll(rs.getLong("id")));
                return vente;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void  create(Vente v) {

        PreparedStatement pst=null;
        ResultSet rs;
        double total=(v.getLcmds()).stream()
                .mapToDouble(x -> x.getStotal())
                .sum();
        ILigneCmdDao cmd=new LigneCmdDaoImp();
        String sql="insert into vente(date,total,id_client) values('"+v.getDate()+"','"+total+"','"+v.getClient().getId()+"')";
        try {
            pst=connection.prepareStatement(sql);
            if(!pst.execute(sql)){
                v.setId(this.getLastId());
                for (LigneCommande l: v.getLcmds()) {
                    cmd.create(l,v);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Vente obj) {

    }

    @Override
    public List<Vente> findAll() {
        return null;
    }

    @Override
    public List<LigneCommande> findAll(int v) {
        return null;
    }

    @Override
    public long getLastId() {
        PreparedStatement pst=null;
        ResultSet rs;
        String sql="select MAX(id) from vente";
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


}