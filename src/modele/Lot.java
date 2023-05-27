package modele;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lot {
	
	private IntegerProperty num;
	private StringProperty DCI;
	private StringProperty dosage;
	private IntegerProperty nom_fourn;
	private IntegerProperty qte_livraison;
	private IntegerProperty prix_achat;
	private ObjectProperty <LocalDate> date_livraison;
	private IntegerProperty qte_stock;
	private  ObjectProperty <LocalDate> date_peremtion;
	
	public Lot(Integer num, String dCI, String dosage, Integer	nom_fourn,
			Integer qte_livraison, Integer prix_achat, Object date_livraison,
			Integer qte_stock, Object date_peremtion) {
		super();
		this.num = new SimpleIntegerProperty(num);
		this.DCI = new SimpleStringProperty(dCI);
		this.dosage = new SimpleStringProperty(dosage);
		this.nom_fourn = new SimpleIntegerProperty(nom_fourn);
		this.qte_livraison = new SimpleIntegerProperty(qte_livraison);
		this.prix_achat = new SimpleIntegerProperty(prix_achat);
		this.date_livraison=new SimpleObjectProperty<LocalDate>((LocalDate) date_livraison);
		this.qte_stock = new SimpleIntegerProperty(qte_stock);
		this.date_peremtion=new SimpleObjectProperty<LocalDate>((LocalDate) date_peremtion);
	}

	public Integer getNum() {
		return num.get();
	}

	public void setNum(Integer num) {
		this.num.set(num);
	}

	public String getDCI() {
		return DCI.get();
	}

	public void setDCI(String dCI) {
		DCI.set(dCI);
	}

	public String getDosage() {
		return dosage.get();
	}

	public void setDosage(String dosage) {
		this.dosage.set(dosage);
	}

	public Integer	getNom_fourn() {
		return nom_fourn.get();
	}

	public void setNom_fourn(Integer	nom_fourn) {
		this.nom_fourn.set(nom_fourn);
	}

	public Integer getQte_livraison() {
		return qte_livraison.get();
	}

	public void setQte_livraison(Integer qte_livraison) {
		this.qte_livraison.set(qte_livraison);
	}

	public Integer getPrix_achat() {
		return prix_achat.get();
	}

	public void setPrix_achat(Integer prix_achat) {
		this.prix_achat.set(prix_achat);
	}
	
	public LocalDate	getDate_livraison() {
		return date_livraison.get();
	}

	public void setDate_livraison(LocalDate date_livraison) {
		this.date_livraison.set(date_livraison);
	}
	
	public ObjectProperty<LocalDate> date_livraisonProperty(){
		return this.date_livraison;
	}

	public Integer getQte_stock() {
		return qte_stock.get();
	}

	public void setQte_stock(Integer qte_stock) {
		this.qte_stock.get();
	}

	public LocalDate getDate_peremtion() {
		return this.date_peremtion.get();
	}

	public void setDate_peremtion(LocalDate  date_peremtion) {
		this.date_peremtion.set(date_peremtion);
	}
	public ObjectProperty <LocalDate> date_peremtionProperty(){
		return this.date_peremtion;
	}
	

	public IntegerProperty numProperty() {
		return num;
	}
	
	public StringProperty dciProperty() {
		return DCI;
	}
	
	public StringProperty dosageProperty() {
		return dosage;
	}
	
	public IntegerProperty nom_fournProperty() {
		return nom_fourn;
	}

	public IntegerProperty qte_livraisonProperty() {
		return this.qte_livraison;
	}
	
	public IntegerProperty prix_achatProperty() {
		return prix_achat;
	}
	
	
	
	
	public IntegerProperty qte_stockProperty() {
		return qte_stock;
	}
	
	
	
}
