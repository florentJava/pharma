package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import DAO.InventaireDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.Inventaire;

public class InventaireController implements Initializable {

	@FXML
	private JFXButton inventaire;

	@FXML
	private JFXButton statistique;

	@FXML
	private JFXButton stock;

	@FXML
	private Label titre;

	@FXML
	private JFXButton vente;

	@FXML
	private JFXCheckBox venteJour;
	
	@FXML
    private TextField rc_dci;

    @FXML
    private TextField rc_id;

	@FXML
	private TableView<Inventaire> tableInventaire;


	@FXML
	private TableColumn<Inventaire, Date> colum_date;

	@FXML
	private TableColumn<Inventaire, String> colum_dci;

	@FXML
	private TableColumn<Inventaire, String> colum_dosage;

	@FXML
	private TableColumn<Inventaire, Integer> colum_id;

	@FXML
	private TableColumn<Inventaire, Integer> colum_prix;

	@FXML
	private TableColumn<Inventaire, Integer> column_qte_vente;

	private ObservableList<Inventaire> listeInventaires = FXCollections.observableArrayList();


	private boolean calle = true;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(calle) {
			InventaireDAO inventaireDAO = new InventaireDAO();
			try {
				listeInventaires.addAll(inventaireDAO.getAll());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			calle = false;

		}

		try {
			loadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		numericOnly(rc_id);

	}


	@FXML
	private void fonctionLien(ActionEvent action) {


		Stage stage =(Stage) vente.getScene().getWindow();

		switch (((JFXButton)	action.getSource()).getId()) {

		case "vente":

			System.out.println(((JFXButton)	action.getSource()).getId());	


			try {

				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/vente/Vente.fxml"))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "stock":


			try {
				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/acceuil/Stock.fxml"))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "inventaire":

			try {

				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/acceuil/Inventaire.fxml"))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		case "statistique":

			System.out.println(((JFXButton)	action.getSource()).getId());	


			try {

				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/acceuil/Statistique.fxml"))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
	}


	public  void loadData() throws SQLException {

		InventaireDAO inventaireDAO = new InventaireDAO();

		column_qte_vente.setCellValueFactory(new PropertyValueFactory<Inventaire,Integer>("qte_vente"));
		colum_date.setCellValueFactory(new PropertyValueFactory<Inventaire,Date>("date_v"));
		colum_dci.setCellValueFactory(new PropertyValueFactory<Inventaire,String>("dci"));

		colum_id.setCellValueFactory(new PropertyValueFactory<Inventaire,Integer>("id_vente"));
		colum_dosage.setCellValueFactory(new PropertyValueFactory<Inventaire,String>("dosage"));
		colum_prix.setCellValueFactory(new PropertyValueFactory<Inventaire,Integer>("prix"));


		tableInventaire.setItems(listeInventaires);
		
		
		FilteredList<Inventaire> listeFiltrer = new FilteredList<>(listeInventaires,b->true); 


		rc_dci.textProperty().addListener(((observable, oldValue, newValue) -> {
			listeFiltrer.setPredicate(invent -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}

				String lowerCaseFilter=newValue.toLowerCase();

				if(invent.getDci().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		SortedList<Inventaire> sortedMedi =new SortedList<>(listeFiltrer);

		sortedMedi.comparatorProperty().bind(tableInventaire.comparatorProperty());
		tableInventaire.setItems(sortedMedi);
		
		rc_id.textProperty().addListener(((observable, oldValue, newValue) -> {
			listeFiltrer.setPredicate(invent -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}

				String lowerCaseFilter=newValue.toLowerCase();

				if((invent.getId_vente()+"").toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		sortedMedi.comparatorProperty().bind(tableInventaire.comparatorProperty());
		tableInventaire.setItems(sortedMedi);


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

	
	@FXML
	void ch(ActionEvent event) throws SQLException {

		InventaireDAO inventaireDAO = new InventaireDAO();

		if(venteJour.isSelected()) {
			listeInventaires.clear();
			listeInventaires.addAll(inventaireDAO.getDay());
		}else {
			listeInventaires.clear();
			listeInventaires.addAll(inventaireDAO.getAll());
		}

	}

}
