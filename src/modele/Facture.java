package modele;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * @author ZEUKENG FLORENT
 *
 */
public class Facture extends Medicaments{
	
	public IntegerProperty qte_vendre;
	public IntegerProperty num_lot;


	public Facture(String dCI, int prix_vente, String forme,String image, String dosage, int qte_stock, String groupe,
			String libelle, int qte_seuil, Integer qte_vendre,Integer num_lot) {
		super(dCI, prix_vente,image, forme, dosage, qte_stock, groupe, libelle, qte_seuil);
		this.qte_vendre.set(prix_vente);
		this.num_lot.set(num_lot);
	}
	
	public Facture(Medicaments md) {
		
		super(md.getDCI(),md.getPrix_vente(),md.getImage(), md.getForme(),md.getDosage(),md.getQte_stock(), md.getGroupe(),md.getLibelle(), md.getQte_seuil());

		this.qte_vendre = new SimpleIntegerProperty("this","qte_vendre",1);
		this.num_lot = new SimpleIntegerProperty("this","num_lot");


	}

	
	public Facture() {
	}


	public Integer getQte_vendre() {
		return qte_vendre.get();
	}
	
	public void setQte_vendre(Integer qte_vendre) {
		this.qte_vendre.set(qte_vendre);;
	}
	
	
	
	public IntegerProperty qte_vendre_property() {
		return qte_vendre;
	}
	
	
	public Integer getNum_lot() {
		return num_lot.get();
	}
	
	public void setNum_lot(Integer num) {
		this.num_lot.set(num);;
	}
	
		
	public IntegerProperty num_lot_property () {
		return num_lot;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(DCI, dosage, forme, groupe, libelle, prix_vente, qte_seuil, qte_stock);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicaments other = (Medicaments) obj;
		return Objects.equals(DCI, other.DCI) && Objects.equals(dosage, other.dosage)
				&& Objects.equals(forme, other.forme) && Objects.equals(groupe, other.groupe)
				&& Objects.equals(libelle, other.libelle) && prix_vente == other.prix_vente
				&& qte_seuil == other.qte_seuil && qte_stock == other.qte_stock;
	}


	
	
}
