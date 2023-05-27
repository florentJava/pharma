package controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.GestionLotDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Facture;
import modele.Lot;

public class DialogController implements Initializable {

	private Facture fact;

	@FXML
	private TextField nbre;

	@FXML
	private ComboBox<Integer> combo;

	@FXML
	private Label dosageId;

	@FXML
	private TextField code_lot;

	@FXML
	private ImageView imageMedicament;

	@FXML
	private Label nomMed;


	ObservableList<Integer> comboList = FXCollections.observableArrayList();

	public DialogController() {

	}


	public int setQuantite() {
		return Integer.parseInt(nbre.getText());
	}


	public String setNumerolot(){
		return "numlot";

	}

	public Facture getFact() {
		return fact;
	}


	public void setFacture(Facture f) throws SQLException {
		
		String url =f.getImage();

		File file = new File(url);
		Image image = new Image(file.toURI().toString());

		if (file.exists())
			imageMedicament.setImage(image);		


		GestionLotDAO gestlot = new GestionLotDAO();
		ArrayList<Lot> liste = new ArrayList<>();
		ArrayList<Integer> listeInt = new ArrayList<>();


		liste.addAll(gestlot.getAll());

		for (Lot lot : liste) {
			if(lot.getDCI().equals(f.getDCI())&&(lot.getDosage().equals(f.getDosage()))&&(lot.getQte_stock()>0)) 
				comboList.add(lot.getNum());

		}

		this.fact=f;
		code_lot.setEditable(false);
		nbre.textProperty().bindBidirectional(fact.qte_vendre,NumberFormat.getNumberInstance());
		code_lot.textProperty().bindBidirectional(fact.num_lot,NumberFormat.getNumberInstance());


		


		nomMed.setText("");
		nomMed.setText(f.getDCI());

		dosageId.setText("");
		dosageId.setText(f.getDosage());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combo.setItems(comboList);
		numericOnly(code_lot);
		numericOnly(nbre);

	}

	@FXML
	void envoie(ActionEvent event) {
		code_lot.setText(combo.getValue()+"");
	}


	public static void numericOnly(final TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(
					ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}


	public void controle() {
		if(this.nbre.getText()==null) {

		}
	}
}
