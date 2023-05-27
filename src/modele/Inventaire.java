package modele;

import java.sql.Date;
import java.sql.Time;

public class Inventaire {
	
	private int id_vente;
	private String dci;
	private String dosage;
	private int qte_vente;
	private int prix;
	private int numero_lot;
	private Date date_v;
	private Time heure;
	
	
	
	public Inventaire(int id_vente, String dci, String dosage, int qte_vente, int prix, int numero_lot,
			Date date_v, Time heure) {
		super();
		this.id_vente = id_vente;
		this.dci = dci;
		this.dosage = dosage;
		this.qte_vente = qte_vente;
		this.prix = prix;
		this.numero_lot = numero_lot;
		this.date_v = date_v;
		this.heure = heure;
	}
	public int getId_vente() {
		return id_vente;
	}
	public void setId_vente(int id_vente) {
		this.id_vente = id_vente;
	}
	public String getDci() {
		return dci;
	}
	public void setDci(String dci) {
		this.dci = dci;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public int getQte_vente() {
		return qte_vente;
	}
	public void setQte_vente(int qte_vente) {
		this.qte_vente = qte_vente;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getNumero_lot() {
		return numero_lot;
	}
	public void setNumero_lot(int numero_lot) {
		this.numero_lot = numero_lot;
	}
	public Date getDate_v() {
		return date_v;
	}
	public void setDate_v(Date date_v) {
		this.date_v = date_v;
	}
	public Time getHeure() {
		return heure;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	
	

}
