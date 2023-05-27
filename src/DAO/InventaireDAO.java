package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import modele.Inventaire;
import modele.Medicaments;

public class InventaireDAO implements DAO<Inventaire> {

	@Override
	public Inventaire get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inventaire> getAll() throws SQLException {
		String sql ="SELECT * FROM inventaire";
		ConnectionDB con= new ConnectionDB();
		List<Inventaire> liste = new ArrayList<>();
		System.out.println("InventaireDAO.getAll()");

		Connection conn=con.connect();
		ResultSet rs =  null;

		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);

		while(rs.next()) {

			liste.add(new Inventaire(rs.getInt("id_vente"),rs.getString("dci"),rs.getString("dosage"),rs.getInt("qte_vente"),rs.getInt("prix"),rs.getInt("numero_lot"),rs.getDate("date_v"),rs.getTime("heure")));
		}		
		System.out.println("InventaireDAO.getAll()");
		return liste;
	}
	
	
	public List<Inventaire> getDay() throws SQLException {
		
		String sql ="		SELECT * FROM `inventaire` WHERE date_v =' +"+Date.valueOf(LocalDate.now())+"'";
		ConnectionDB con= new ConnectionDB();
		List<Inventaire> liste = new ArrayList<>();
		
		System.out.println("InventaireDAO.getDay()");
		Connection conn=con.connect();
		ResultSet rs =  null;

		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);

		while(rs.next()) {

			liste.add(new Inventaire(rs.getInt("id_vente"),rs.getString("dci"),rs.getString("dosage"),rs.getInt("qte_vente"),rs.getInt("prix"),rs.getInt("numero_lot"),rs.getDate("date_v"),rs.getTime("heure")));
		}		
		System.out.println("InventaireDAO.getAll()");
		return liste;

	}

	@Override
	public int save(Inventaire t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Inventaire t) throws SQLException {	

		int et=0;
		try {
			String sql ="INSERT INTO inventaire  (dci,dosage,qte_vente,prix,numero_lot,id_vente) VALUES (?,?,?,?,?,?) ";
			ConnectionDB con= new ConnectionDB();
			Connection conn=con.connect();

			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getDci());
			stat.setString(2, t.getDosage());
			stat.setInt(3, t.getQte_vente());
			stat.setInt(4, t.getPrix());
			stat.setInt(5, t.getNumero_lot());
			stat.setInt(6, t.getId_vente());

			et =stat.executeUpdate();
			conn.close();
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
	}

	@Override
	public int update(Inventaire t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Inventaire t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
