package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB extends Parametre {

	@Override
	public Connection connect() throws SQLException {


		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(this.getUrl(),this.getUser(), this.getPassword());
			return con;
		}catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return null;
	}
}


