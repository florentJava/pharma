package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import modele.Lot;
import modele.Medicaments;

public class GestionMedicamentDAO implements DAO<Medicaments> {


	public GestionMedicamentDAO() {
		// TODO Auto-generated constructor stub
	}

	public Medicaments get(String dci,String dosage) throws SQLException {

		String sql ="SELECT * FROM medicament where dci ='" +dci+ "' and dosage='"+ dosage+"'";
		ConnectionDB con= new ConnectionDB();
		List<Medicaments> liste = new ArrayList<>();


		Connection conn=con.connect();
		ResultSet rs =  null;

		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);

		while(rs.next()) {

			liste.add(new Medicaments(rs.getString("dci"),rs.getInt("prix_vente"),rs.getString("image"),rs.getString("forme"),rs.getString("dosage"),rs.getInt("qte_stock"),rs.getString("groupe"),rs.getString("libelle"), rs.getInt("qte_seuil")));
		}		
		return liste.get(0);	
		}

	@Override
	public  List<Medicaments> getAll() throws SQLException {
		// TODO Auto-generated method stub

		String sql ="SELECT * FROM medicament";
		ConnectionDB con= new ConnectionDB();
		List<Medicaments> liste = new ArrayList<>();


		Connection conn=con.connect();
		ResultSet rs =  null;

		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);

		while(rs.next()) {

			String id = rs.getString("DCI");
			Medicaments md = new Medicaments();
			liste.add(new Medicaments(rs.getString("DCI"),rs.getInt("prix_vente"),rs.getString("image"),rs.getString("forme"),rs.getString("dosage"),rs.getInt("qte_stock"),rs.getString("groupe"),rs.getString("libelle"),rs.getInt("qte_seuil")));
		}		

		return liste;
	}


	@Override
	public int save(Medicaments t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Medicaments t) throws SQLException {

		int et=0;
		try {
			String sql ="INSERT INTO medicament (dci,prix_vente,forme,dosage,qte_seuil,groupe,libelle,qte_stock,image) VALUES (?,?,?,?,?,?,?,?,?) ";
			ConnectionDB con= new ConnectionDB();
			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getDCI());
			stat.setInt(2, t.getPrix_vente());
			stat.setString(3, t.getForme());
			stat.setString(4, t.getDosage());
			stat.setInt(5, t.getQte_seuil());
			stat.setString(6, t.getGroupe());
			stat.setString(7, t.getLibelle());
			stat.setInt(8, t.getQte_stock());
			stat.setString(9, t.getImage());

			et =stat.executeUpdate();
			conn.close();
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
	}

	@Override
	public int update(Medicaments t) throws SQLException {

		int et=0;
		try {
			String sql ="UPDATE medicament SET dci=?, prix_vente=?,forme=?,dosage=?,qte_seuil=?,groupe=?,libelle=?,qte_stock=?,image=? where dci=? and dosage=?";
			ConnectionDB con= new ConnectionDB();
			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getDCI());
			stat.setInt(2, t.getPrix_vente());
			stat.setString(3, t.getForme());
			stat.setString(4, t.getDosage());
			stat.setInt(5, t.getQte_seuil());
			stat.setString(6, t.getGroupe());
			stat.setString(7, t.getLibelle());
			stat.setInt(8, t.getQte_stock());
			stat.setString(9,t.getImage());
			stat.setString(10,(t.getDCI()));
			stat.setString(11,t.getDosage());


			et =stat.executeUpdate();
			conn.close();
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
	}

	@Override
	public int delete(Medicaments t) {
		int et=0;
		try {
			String sql ="delete from medicament  where dci=? and dosage=?";
			ConnectionDB con= new ConnectionDB();
			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getDCI());
			stat.setString(2, t.getDosage());

			et =stat.executeUpdate();
			conn.close();
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
	}

	@Override
	public Medicaments get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
