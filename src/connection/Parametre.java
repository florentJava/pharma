package connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Parametre {

	protected final String url ="jdbc:mysql://localhost:3306/pharmaciemarket3";
	protected final String user = "root";
	protected final String password ="";


	public abstract Connection connect() throws SQLException;
	
	public String getUrl() {
		return url;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}

}
