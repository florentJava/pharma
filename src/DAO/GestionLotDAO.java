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
import modele.Lot;

public class GestionLotDAO implements DAO<Lot> {

	@Override
	public Lot get(int id) throws SQLException {
		
		String sql ="SELECT * FROM lot where num ="+id;
		ConnectionDB con= new ConnectionDB();
		List<Lot> liste = new ArrayList<>();
		
		
		Connection conn=con.connect();
		ResultSet rs =  null;
		
		Statement  statement = conn.createStatement();
		rs = statement.executeQuery(sql);
	
		while(rs.next()) {
		
			liste.add(new Lot(rs.getInt("num"),rs.getString("dci"),rs.getString("dosage"),rs.getInt("fournisseur"),rs.getInt("qte_liv"),rs.getInt("prix_achat"),
					rs.getDate("date_liv").toLocalDate(),rs.getInt("qte_stock"), rs.getDate("date_perem").toLocalDate()));
		}		
		
		return liste.get(0);
}
	

	@Override
	public List<Lot> getAll() throws SQLException {
		
				String sql ="SELECT * FROM lot";
				ConnectionDB con= new ConnectionDB();
				List<Lot> liste = new ArrayList<>();
				
				
				Connection conn=con.connect();
				ResultSet rs =  null;
				
				Statement  statement = conn.createStatement();
				rs = statement.executeQuery(sql);
			
				while(rs.next()) {
				
					liste.add(new Lot(rs.getInt("num"),rs.getString("dci"),rs.getString("dosage"),rs.getInt("fournisseur"),rs.getInt("qte_liv"),rs.getInt("prix_achat"),
							rs.getDate("date_liv").toLocalDate(),rs.getInt("qte_stock"), rs.getDate("date_perem").toLocalDate()));
				}		
				conn.close();
				return liste;
	}

	@Override
	public int save(Lot t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Lot t) throws SQLException {
		int et=0;
		LocalDate date;
		try {
			String sql ="INSERT INTO lot (dci,dosage,fournisseur,qte_liv,qte_stock,date_perem,prix_achat) VALUES (?,?,?,?,?,?,?) ";
			ConnectionDB con= new ConnectionDB();
		
			Connection conn=con.connect();
			
			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			stat.setString(1, t.getDCI());
			stat.setString(2, t.getDosage());
			stat.setInt(3, t.getNom_fourn());
			stat.setInt(4, t.getQte_livraison());
			stat.setInt(5, t.getQte_livraison());
			stat.setDate(6,Date.valueOf(t.getDate_peremtion()));
			stat.setInt(7,t.getPrix_achat());
			et =stat.executeUpdate();
			conn.close();
			
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;	}

	@Override
	public int update(Lot t) throws SQLException {
		
		int et=0;
		try {
			String sql ="UPDATE lot SET   qte_stock=? WHERE num=? ;";
			ConnectionDB con= new ConnectionDB();
		
			Connection conn=con.connect();
			
			PreparedStatement stat;
			stat = conn.prepareStatement(sql);
			
			
			stat.setInt(1, t.getQte_stock());
			
			
			stat.setInt(2, t.getNum());
			
			et =stat.executeUpdate();
			conn.close();
			
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return et;
	}
	
	
		

	@Override
	public int delete(Lot t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
