package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import modele.Fournisseur;

public class GestionFournisseurDao implements DAO<Fournisseur> {

	@Override
	public Fournisseur get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fournisseur> getAll() throws SQLException {

		String sql ="SELECT * FROM Fournisseur";
		ConnectionDB con= new ConnectionDB();
		List<Fournisseur> liste = new ArrayList<>();


		Connection conn=con.connect();
		ResultSet rs =  null;

		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);

		while(rs.next()) {

			liste.add(new Fournisseur(rs.getInt("id"),rs.getString("nom"),rs.getString("tel"),rs.getString("email")));
		}		

		return liste;
	}

	@Override
	public int save(Fournisseur t) throws SQLException {

		int et=0;
		try {
			String sql ="INSERT INTO fournisseur (nom,tel,email) VALUES (?,?,?) ";
			ConnectionDB con= new ConnectionDB();
			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getNom());
			stat.setString(2, t.getTell());
			stat.setString(3, t.getEmail());
			et =stat.executeUpdate();
			conn.close();
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
		}

	@Override
	public int insert(Fournisseur t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Fournisseur t) throws SQLException {
		int et=0;
		try {
			String sql ="UPDATE fournisseur SET nom=?, tel=?,email=? where id=?";
			ConnectionDB con= new ConnectionDB();
			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getNom());
			stat.setString(2, t.getTell());
			stat.setString(3, t.getEmail());
			stat.setInt(4, t.getId());

			et =stat.executeUpdate();
			conn.close();
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;

	}

	@Override
	public int delete(Fournisseur t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
