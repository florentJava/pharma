package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import modele.Utilisateur;

public class GestionUtilisateurDAO implements DAO<Utilisateur> {

	@Override
	public Utilisateur get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> getAll() throws SQLException {
		String sql ="SELECT * FROM utilisateur";
		ConnectionDB con= new ConnectionDB();
		List<Utilisateur> liste = new ArrayList<>();


		Connection conn=con.connect();
		ResultSet rs =  null;

		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);

		while(rs.next()) {

			liste.add(new Utilisateur(rs.getString("username"), rs.getString("pass")));
		}		

		conn.close();
		return liste;
	}

	@Override
	public int save(Utilisateur t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Utilisateur t) throws SQLException {
		int et=0;
		try {
			String sql ="INSERT utilisateur (username,pass) VALUES (?,?) ";
			ConnectionDB con= new ConnectionDB();

			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getNom());
			stat.setString(2, t.getPass());
			et =stat.executeUpdate();
			conn.close();

		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
	}



	@Override
	public int update(Utilisateur t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utilisateur t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
