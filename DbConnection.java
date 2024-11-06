import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

        private static final  String url = "jdbc:mysql://root@127.0.0.1:3306/ticketresev" ;
        private static final  String user = "root" ;
        private static final  String password = "Pree@2006";
    
    public static Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(url, user, password);
    }
}
