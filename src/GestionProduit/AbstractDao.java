package GestionProduit;

import java.sql.Connection;

public class AbstractDao {
    protected Connection  connection= SingleConnection.getConnection();

}
