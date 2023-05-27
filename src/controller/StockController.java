package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import DAO.GestionFournisseurDao;
import DAO.GestionLotDAO;
import DAO.GestionMedicamentDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Fournisseur;
import modele.Lot;
import modele.Medicaments;

public class StockController implements Initializable {


	//********************************************************GENERALE****************************


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
	private void fonctionLien(ActionEvent action) {


		Stage stage =(Stage) vente.getScene().getWindow();

		switch (((JFXButton)	action.getSource()).getId()) {

		case "vente":

			try {

				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/vente/vente.fxml"))));
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

			try {

				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/acceuil/Statistique.fxml"))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		default:
			break;
		}
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			loadData();
			loadData2();
			loadData3();

			numericOnly(update_fournisseur_ID);
			numericOnly(update_fournisseur_tell);
			numericOnly(ajouter_tell_fourn);


		} catch (SQLException e) {
			e.printStackTrace();
		}

	}







	//********************************************************SPECIAL LOT******************************************************


	@FXML
	private TableColumn<Lot, Date> lotColumDate_liv;

	@FXML
	private TextField searchDci_lot;
	
	@FXML
	private TextField searchLot;

	@FXML
	private TableColumn<Lot,StringProperty> lotColumDosage;


	@FXML
	private TableColumn<Lot,IntegerProperty> lotColumQte_liv;

	@FXML
	private TableColumn<Lot,IntegerProperty> lotColumQte_stock;

	@FXML
	private TableColumn<Lot, StringProperty> lotColumFourn;

	@FXML
	private TableColumn<Lot,IntegerProperty> lotColumNum;

	@FXML
	private TableColumn<Lot,Date> lotColumPeremption;

	@FXML
	private TableColumn<Lot,StringProperty> lotColumProd;

	@FXML
	private TableView<Lot> tableLot;

	ObservableList<Lot> listeLot = FXCollections.observableArrayList();

	@FXML
	private JFXButton dashboard;

	@FXML
	private JFXButton statistique;

	@FXML
	private JFXButton stock;

	@FXML
	private Label titre;

	@FXML
	private JFXButton vente;

	@FXML
	private DatePicker dateFiltre;


	@FXML
	private TextField searchFournisseur;



	public void loadData() throws SQLException {

		GestionLotDAO glot = new GestionLotDAO();
		listeLot.addAll( (ArrayList<Lot>) glot.getAll());	

		lotColumFourn.setCellValueFactory(new PropertyValueFactory<Lot,StringProperty>("nom_fourn"));
		lotColumNum.setCellValueFactory(new PropertyValueFactory<Lot,IntegerProperty>("num"));
		lotColumPeremption.setCellValueFactory(new PropertyValueFactory<Lot,Date>("date_peremtion"));
		lotColumProd.setCellValueFactory(new PropertyValueFactory<Lot,StringProperty>("DCI"));

		lotColumDate_liv.setCellValueFactory(new PropertyValueFactory<Lot,Date>("date_livraison"));
		lotColumDosage.setCellValueFactory(new PropertyValueFactory<Lot,StringProperty>("dosage"));
		lotColumQte_liv.setCellValueFactory(new PropertyValueFactory<Lot,IntegerProperty>("qte_livraison"));
		lotColumQte_stock.setCellValueFactory(new PropertyValueFactory<Lot,IntegerProperty>("qte_stock"));

		tableLot.setItems(listeLot);


		FilteredList<Lot> listeFiltrerLot = new FilteredList<>(listeLot,b->true); 


		searchDci_lot.textProperty().addListener(((observable, oldValue, newValue) -> {

			listeFiltrerLot.setPredicate(lot -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}


				String lowerCaseFilter=newValue.toLowerCase();

				if(lot.getDCI().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		SortedList<Lot> sortedMedi =new SortedList<>(listeFiltrerLot);

		sortedMedi.comparatorProperty().bind(tableLot.comparatorProperty());
		tableLot.setItems(sortedMedi);
		
		searchFournisseur.textProperty().addListener(((observable, oldValue, newValue) -> {

			listeFiltrerLot.setPredicate(fournisseur -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}


				String lowerCaseFilter=newValue.toLowerCase();

				if((""+fournisseur.getNom_fourn()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		sortedMedi.comparatorProperty().bind(tableLot.comparatorProperty());
		tableLot.setItems(sortedMedi);
		
		searchLot.textProperty().addListener(((observable, oldValue, newValue) -> {

			listeFiltrerLot.setPredicate(fournisseur -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}


				String lowerCaseFilter=newValue.toLowerCase();

				if((""+fournisseur.getNum()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		sortedMedi.comparatorProperty().bind(tableLot.comparatorProperty());
		tableLot.setItems(sortedMedi);
		
		


	}



	//*****************************************************SPECIAL FOURNISSEUR**************************************************

	@FXML
	private TableView<Fournisseur> table_fournisseur;

	@FXML
	private TableColumn<Fournisseur, IntegerProperty> fourni_colum_id;

	@FXML
	private TableColumn<Fournisseur, StringProperty> fourni_colum_mail;

	@FXML
	private TableColumn<Fournisseur, StringProperty> fourni_colum_nom;

	@FXML
	private TableColumn<Fournisseur, StringProperty> fourni_colum_tell;

	@FXML
	private Button ajouter_fourn_btn;

	@FXML
	private TextField ajouter_mail_fourn;

	@FXML
	private TextField ajouter_nom_fourn;

	@FXML
	private TextField ajouter_tell_fourn;

	@FXML
	private Button update_fournisseur_btn;

	@FXML
	private TextField update_fournisseur_mail;

	@FXML
	private TextField update_fournisseur_nom;

	@FXML
	private TextField update_fournisseur_tell;

	@FXML
	private TextField update_fournisseur_ID;

	@FXML
	private TextField searchFourni;




	ObservableList<Fournisseur> listeFournisseur = FXCollections.observableArrayList();

	@FXML
	void update_fournisseur(ActionEvent event) throws SQLException {


		if(update_fournisseur_ID.getText()!=""&&update_fournisseur_nom.getText()!=""&&update_fournisseur_tell.getText()!=""&&update_fournisseur_mail.getText()!="") {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			Optional<ButtonType> btnClik = alert.showAndWait();

			if(btnClik.get()==ButtonType.OK) {

				Fournisseur fournisseurAide = new Fournisseur( Integer.parseInt(update_fournisseur_ID.getText()),update_fournisseur_nom.getText(),update_fournisseur_tell.getText(),update_fournisseur_mail.getText());

				update_fournisseur_nom.setText("");
				update_fournisseur_tell.setText("");

				update_fournisseur_mail.setText("");
				update_fournisseur_ID.setText("");


				GestionFournisseurDao gestFournisseurDao = new GestionFournisseurDao();

				switch (gestFournisseurDao.update(fournisseurAide)
						) {
						case 1: {
							Alert alertEtat = new Alert(AlertType.INFORMATION);
							alertEtat.setContentText("ajout effectuer: succes");
							alertEtat.showAndWait();
							break;
						}
						case 0:
							Alert alertEtat2 = new Alert(AlertType.ERROR);
							alertEtat2.showAndWait();
				}

				listeFournisseur.clear();
				loadData2();

			}else {
				System.out.println("StockController.update_fournisseur()");
			}
		}

	}

	@FXML
	void Ajouter_Fournisseur(ActionEvent event) throws SQLException {

		if(ajouter_nom_fourn.getText()!=""&&ajouter_tell_fourn.getText()!=""&&ajouter_mail_fourn.getText()!="") {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			Optional<ButtonType> btnClik = alert.showAndWait();

			if(btnClik.get()==ButtonType.OK) {
				Fournisseur fournisseurAide = new Fournisseur(0,ajouter_nom_fourn.getText(),ajouter_tell_fourn.getText(),ajouter_mail_fourn.getText());

				ajouter_nom_fourn.setText("");
				ajouter_tell_fourn.setText("");
				ajouter_mail_fourn.setText("");

				GestionFournisseurDao gestFournisseurDao = new GestionFournisseurDao();
				switch (gestFournisseurDao.update(fournisseurAide)
						) {
						case 1: {
							Alert alertEtat = new Alert(AlertType.INFORMATION);
							alertEtat.setContentText("ajout effectuer: succes");
							alertEtat.showAndWait();
							break;
						}
						case 0:
							Alert alertEtat2 = new Alert(AlertType.ERROR);
							alertEtat2.showAndWait();
				}

			}
		}
	}

	@FXML
	void tableMouseClicked(MouseEvent event) {


		if((table_fournisseur.getSelectionModel().getSelectedItem()!=null)&&event.getClickCount()==2){

			Fournisseur  fourn = table_fournisseur.getSelectionModel().getSelectedItem();

			System.out.println(fourn);
			update_fournisseur_ID.setText(fourn.getId()+"");
			update_fournisseur_nom.setText(fourn.getNom());
			update_fournisseur_mail.setText(fourn.getEmail());
			update_fournisseur_tell.setText(fourn.getTell());


		}

	}

	public void loadData2() throws SQLException {

		GestionFournisseurDao gFournisseur = new GestionFournisseurDao();
		listeFournisseur.addAll( (ArrayList<Fournisseur>) gFournisseur.getAll());	

		fourni_colum_id.setCellValueFactory(new PropertyValueFactory<Fournisseur,IntegerProperty>("id"));
		fourni_colum_mail.setCellValueFactory(new PropertyValueFactory<Fournisseur,StringProperty>("email"));
		fourni_colum_nom.setCellValueFactory(new PropertyValueFactory<Fournisseur,StringProperty>("nom"));
		fourni_colum_tell.setCellValueFactory(new PropertyValueFactory<Fournisseur,StringProperty>("tell"));

		table_fournisseur.setItems(listeFournisseur);

		FilteredList<Fournisseur> listeFiltrer = new FilteredList<>(listeFournisseur,b->true); 


		searchFourni.textProperty().addListener(((observable, oldValue, newValue) -> {
			listeFiltrer.setPredicate(Fournisseur -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}

				String lowerCaseFilter=newValue.toLowerCase();

				if(Fournisseur.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Fournisseur.getId().toString().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		SortedList<Fournisseur> sortedMedi =new SortedList<>(listeFiltrer);

		sortedMedi.comparatorProperty().bind(table_fournisseur.comparatorProperty());
		table_fournisseur.setItems(sortedMedi);

	}
	//****************************************************************************************************************************



	//******************************************************SPECIAL MEDICAMENT**********************************************

	@FXML
	private Button create_medicament_btn;

	@FXML
	private Button update_medicament_btn;

	@FXML
	private Button delete_medicament_btn;

	@FXML
	private Button rav_medicament_btn;

	@FXML
	private TextField	rechercheProduit;

	@FXML
	private TableColumn<Medicaments, StringProperty> Medi_colum_forme;

	@FXML
	private TableColumn<Medicaments, StringProperty> medi_colum_dci;

	@FXML
	private TableColumn<Medicaments, StringProperty> medi_colum_dosage;

	@FXML
	private TableColumn<Medicaments, StringProperty> medi_colum_groupe;

	@FXML
	private TableColumn<Medicaments, IntegerProperty> medi_colum_prix_vente;

	@FXML
	private TableColumn<Medicaments, IntegerProperty> medi_colum_qte_seuil;

	@FXML
	private TableColumn<Medicaments, IntegerProperty> medi_colum_qte_soct;

	@FXML
	private TableView<Medicaments> table_medi;

	@FXML
	private TextField groupeProduit;

	@FXML
	private TextField formeProduit;

	@FXML
	private TextField qteStockProduit;

	ObservableList<Medicaments> listeMedicament = FXCollections.observableArrayList();


	@FXML
	public void open_a_dialog(ActionEvent action) throws IOException, SQLException {

		FXMLLoader fxmlLoader = new FXMLLoader();

		if(table_medi.getSelectionModel().getSelectedItem()!=null) {


			Medicaments medicament = new Medicaments("", 0,"","","",0,"","", 0);

			switch (((Button) action.getSource()).getId()) {

			case "create_medicament_btn":

				fxmlLoader.setLocation(getClass().getResource("/Interface/acceuil/AjoutMedi.fxml"));		
				DialogPane pane =(DialogPane)fxmlLoader.load();	
				Dialog<ButtonType> dialog = new Dialog<>();

				dialog.setDialogPane(pane);
				dialog.setTitle("nouveau medicament");

				AjoutMedicamentController ajoutControler = fxmlLoader.getController();
				ajoutControler.setNouveauMedicament(medicament);

				Optional<ButtonType> cliekedButton;
				cliekedButton = dialog.showAndWait();

				if(cliekedButton.get()==ButtonType.OK) {
					Alert alert  = new Alert(AlertType.CONFIRMATION);
					Optional<ButtonType> cliekedButton2;

					cliekedButton2=alert.showAndWait();

					if(cliekedButton2.get()==ButtonType.OK) {

						GestionMedicamentDAO gestMedi = new GestionMedicamentDAO();
						try {
							gestMedi.insert(medicament);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						try {
							listeMedicament.clear();
							loadData3();
						} catch (SQLException e) {
							// 
							e.printStackTrace();
						}

					}else {
						open_a_dialog(action);
					}

				}else {
					System.out.println("operation annulle");
				}


				break;

			case "update_medicament_btn":

				medicament = table_medi.getSelectionModel().getSelectedItem();
				fxmlLoader.setLocation(getClass().getResource("/Interface/acceuil/UpdateMedicament.fxml"));				

				DialogPane paneUpdate =(DialogPane)fxmlLoader.load();	
				Dialog<ButtonType> dialogUpdate = new Dialog<>();

				dialogUpdate.setDialogPane(paneUpdate);
				dialogUpdate.setTitle("Update medicament");

				UpdateMedicamentController ajoutControlerUpdate = fxmlLoader.getController();
				ajoutControlerUpdate.setNouveauMedicament(medicament);

				Optional<ButtonType> cliekedButtonUpdate;
				cliekedButtonUpdate = dialogUpdate.showAndWait();

				if(cliekedButtonUpdate.get()==ButtonType.OK) {
					Alert alertUpdate  = new Alert(AlertType.CONFIRMATION);
					Optional<ButtonType> cliekedButton2;

					cliekedButton2=alertUpdate.showAndWait();

					if(cliekedButton2.get()==ButtonType.OK) {

						GestionMedicamentDAO gestMedi = new GestionMedicamentDAO();
						try {
							gestMedi.update(medicament);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


						try {
							listeMedicament.clear();
							loadData3();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					}else {

						listeMedicament.clear();
						loadData3();

					}

				}else {
					System.out.println("operation annulle");
					listeMedicament.clear();
					loadData3();
				}

				break;

			case "delete_medicament_btn":


				Alert alertDelete = new Alert(AlertType.CONFIRMATION);

				Optional<ButtonType> deleteConfirm = alertDelete.showAndWait();

				if(deleteConfirm.get()==ButtonType.OK) {


					medicament = table_medi.getSelectionModel().getSelectedItem();

					GestionMedicamentDAO gestMedi = new GestionMedicamentDAO();

					gestMedi.delete(medicament);

					listeMedicament.clear();
					loadData3();
				}


				break;

			case "rav_medicament_btn":
				System.out.println("StockController.open_a_dialog(ici)");

				medicament = table_medi.getSelectionModel().getSelectedItem();
				fxmlLoader.setLocation(getClass().getResource("/Interface/acceuil/RavitallerMedicament.fxml"));				

				DialogPane paneRav =(DialogPane)fxmlLoader.load();	
				Dialog<ButtonType> dialogRav = new Dialog<>();

				dialogRav.setDialogPane(paneRav);
				dialogRav.setTitle("Ravitaller medicament");

				RavStockController ravController = fxmlLoader.getController();

				Lot lt = new  Lot(0, medicament.getDCI(), medicament.getDosage(),9,3,2,null,4,null);

				ravController.ravittaller(lt);

				Optional<ButtonType> rsOkBtn = dialogRav.showAndWait();

				if (rsOkBtn.get()==ButtonType.OK) {
					medicament.setQte_stock(medicament.getQte_stock()+lt.getQte_livraison());

					GestionLotDAO gestLot = new GestionLotDAO();
					GestionMedicamentDAO gestMedi = new GestionMedicamentDAO();


					if(gestLot.insert(lt)==1) {
						if(gestMedi.update(medicament)==1) {
							Alert alert = new Alert(AlertType.INFORMATION,"reussi");
							alert.show();
						}
					}else {
						Alert alert = new Alert(AlertType.ERROR,"echec");
						alert.show();

					}

				}



				break;
			}

		}

	}

	public void loadData3() throws SQLException {

		GestionMedicamentDAO gmdao = new GestionMedicamentDAO();

		listeMedicament.addAll( (ArrayList<Medicaments>) gmdao.getAll());	

		medi_colum_dci.setCellValueFactory(new PropertyValueFactory<Medicaments,StringProperty>("DCI"));
		medi_colum_dosage.setCellValueFactory(new PropertyValueFactory<Medicaments,StringProperty>("dosage"));
		medi_colum_groupe.setCellValueFactory(new PropertyValueFactory<Medicaments,StringProperty>("groupe"));
		Medi_colum_forme.setCellValueFactory(new PropertyValueFactory<Medicaments,StringProperty>("forme"));

		medi_colum_qte_seuil.setCellValueFactory(new PropertyValueFactory<Medicaments,IntegerProperty>("qte_seuil"));
		medi_colum_prix_vente.setCellValueFactory(new PropertyValueFactory<Medicaments,IntegerProperty>("prix_vente"));
		medi_colum_qte_soct.setCellValueFactory(new PropertyValueFactory<Medicaments,IntegerProperty>("qte_stock"));

		FilteredList<Medicaments> listeFiltrer = new FilteredList<>(listeMedicament,b->true); 


		rechercheProduit.textProperty().addListener(((observable, oldValue, newValue) -> {
			listeFiltrer.setPredicate(medicament -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}


				String lowerCaseFilter=newValue.toLowerCase();

				if(medicament.getDCI().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));


		SortedList<Medicaments> sortedMedi =new SortedList<>(listeFiltrer);

		sortedMedi.comparatorProperty().bind(table_medi.comparatorProperty());
		table_medi.setItems(sortedMedi);



		groupeProduit.textProperty().addListener(((observable, oldValue, newValue) -> {
			listeFiltrer.setPredicate(medicament -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();

				if(medicament.getGroupe().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));

		sortedMedi.comparatorProperty().bind(table_medi.comparatorProperty());
		table_medi.setItems(sortedMedi);


		formeProduit.textProperty().addListener(((observable, oldValue, newValue) -> {
			listeFiltrer.setPredicate(medicament -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();

				if(medicament.getForme().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else {
					return false;
				}

			});
		}));



	}
	@FXML
	public void showDetail(MouseEvent event) throws IOException {

		if((table_medi.getSelectionModel().getSelectedItem()!=null)&&event.getClickCount()==2){

			Medicaments md = table_medi.getSelectionModel().getSelectedItem();

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/Interface/acceuil/medInfo.fxml"));				
			DialogPane pane =(DialogPane)fxmlLoader.load();	

			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(pane);

			MedInfoController dialogController = fxmlLoader.getController();


			dialogController.showMedicament(md);

			dialog.showAndWait();

		}



	}

	//***********************************************************************************************************************


}
