package modele;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medicaments {

	

	protected StringProperty DCI;
	protected IntegerProperty prix_vente;
	protected StringProperty forme;
	protected StringProperty image;
   	protected StringProperty dosage;
	protected IntegerProperty qte_stock;
	protected IntegerProperty qte_seuil;
	protected StringProperty groupe;
	protected StringProperty libelle;

	public Medicaments(String dCI, int prix_vente,String image, String forme, String dosage, int qte_stock, String groupe,
			String libelle,int qte_seuil) {
		super();
		DCI = new SimpleStringProperty(dCI);
		this.prix_vente = new SimpleIntegerProperty(prix_vente);
		this.image=new SimpleStringProperty(image);
		this.forme = new SimpleStringProperty(forme);
		this.dosage = new SimpleStringProperty(dosage);
		this.qte_stock = new SimpleIntegerProperty(qte_stock);
		this.groupe = new SimpleStringProperty(groupe);
		this.libelle = new SimpleStringProperty(libelle);
		this.qte_seuil = new SimpleIntegerProperty(qte_seuil);
	}

	public Medicaments() {
		super();
	}

	public String getDCI() {
		return DCI.get();
	}
	
	public StringProperty dciProperty() {
		return this.DCI;
	}
	
	public void setDCI(String dCI) {
		DCI.set(dCI);;
	}
	
	public String getImage(){
		return image.get();
	}
	
	public StringProperty imageProperty() {
		return image;
	}
	
	public void setImage(String image) {
		this.image.set(image);
	}
	
	
	
	public int getPrix_vente() {
		return prix_vente.get();
	}
	
	public IntegerProperty prix_venteProperty() {
		return this.prix_vente;
	}
	
	public void setPrix_vente(int prix_vente) {
		this.prix_vente.set(prix_vente);
	}
	public String getForme() {
		return forme.get();
	}
	
	public StringProperty formeProperty() {
		return this.forme;
	}
	
	public void setForme(String forme) {
		this.forme.set(forme);
	}
	public String getDosage() {
		return dosage.get();
	}
	
	public StringProperty dosageProperty() {
		return this.dosage;
	}
	
	public void setDosage(String dosage) {
		this.dosage.set(dosage);
	}
	public int getQte_stock() {
		return qte_stock.get();
	}
	
	public IntegerProperty qte_stockProperty() {
		return this.qte_stock;
	}
	public void setQte_stock(int q) {
		this.qte_stock.set(q);
	}
	public String getGroupe() {
		return groupe.get();
	}
	public StringProperty groupeProperty() {
		return this.groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe.set(groupe);;
	}

	public String getLibelle() {
		return libelle.get();
	}
	
	public StringProperty libelleProperty() {
		return this.libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle.set(libelle);
	}

	public int getQte_seuil() {
		return qte_seuil.get();
	}
	
	public IntegerProperty qte_seuilProperty() {
		return this.qte_seuil;
	}

	public void setQte_seuil(int qte_seuil) {
		this.qte_seuil.set(qte_seuil);
	}


	@Override
	public String toString() {
		return "Medicaments [DCI=" + DCI + ", prix_vente=" + prix_vente + ", forme=" + forme + ", dosage=" + dosage
				+ ", qte_stock=" + qte_stock + ", qte_seuil=" + qte_seuil + ", groupe=" + groupe + ", libelle="
				+ libelle + "]";
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
