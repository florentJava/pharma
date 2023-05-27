package controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modele.Medicaments;

public class UpdateMedicamentController implements Initializable {



	@FXML
	private TextField dci;

	@FXML
	private TextField dosage;

	@FXML
	private TextField groupe;

	@FXML
	private TextField image;

	@FXML
	private TextArea libelle;

	@FXML
	private TextField prix_vente;

	@FXML
	private TextField qte_seuil;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dci.setEditable(false);
		dosage.setEditable(false);

		numericOnly(prix_vente);
		numericOnly(qte_seuil);



	}

	public void setNouveauMedicament(Medicaments md) {

		dci.setEditable(false);
		dosage.setEditable(false);

		dci.textProperty().bindBidirectional(md.dciProperty());
		dosage.textProperty().bindBidirectional(md.dosageProperty());
		groupe.textProperty().bindBidirectional(md.groupeProperty());
		libelle.textProperty().bindBidirectional(md.libelleProperty());
		prix_vente.textProperty().bindBidirectional(md.prix_venteProperty(),NumberFormat.getNumberInstance());
		qte_seuil.textProperty().bindBidirectional(md.qte_seuilProperty(),NumberFormat.getNumberInstance());
		image.textProperty().bindBidirectional(md.imageProperty());

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
}


