package controller;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.Lot;

public class RavStockController implements Initializable{

    @FXML
    private DatePicker date;

    @FXML
    private TextField dci;

    @FXML
    private TextField dosage;

    @FXML
    private TextField qte;
    
    @FXML
    private TextField fournisseur;
    
    @FXML
    private TextField prix_achat;
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	public void ravittaller(Lot lot ) {
		
		dci.textProperty().bindBidirectional(lot.dciProperty());
		dosage.textProperty().bindBidirectional(lot.dosageProperty());
		qte.textProperty().bindBidirectional(lot.qte_livraisonProperty(),NumberFormat.getNumberInstance());
		date.valueProperty().bindBidirectional(lot.date_peremtionProperty());
		fournisseur.textProperty().bindBidirectional(lot.nom_fournProperty(), NumberFormat.getNumberInstance());
		prix_achat.textProperty().bindBidirectional(lot.prix_achatProperty(), NumberFormat.getCurrencyInstance());
	}

	public LocalDate getDate() {
		return date.getValue();
	}

	public void setDate(DatePicker date) {
		this.date = date;
	}

	public String getDci() {
		return dci.getText();
	}

	public void setDci(TextField dci) {
		this.dci = dci;
	}

	public String getDosage() {
		return dosage.getText();
	}

	public void setDosage(TextField dosage) {
		this.dosage = dosage;
	}

	public Integer getQte() {
		return Integer.parseInt(qte.getText()) ;
	}

	public void setQte(TextField qte) {
		this.qte = qte;
	}

	public Integer getFournisseur() {
		return Integer.parseInt(fournisseur.getText()) ;
	}

	public void setFournisseur(TextField fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Integer getPrix_achat() {
		return Integer.parseInt(prix_achat.getText()) ;
	}

	public void setPrix_achat(TextField prix_achat) {
		this.prix_achat = prix_achat;
	}

}
