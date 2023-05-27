package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;

import DAO.GestionLotDAO;
import DAO.GestionMedicamentDAO;
import DAO.InventaireDAO;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Facture;
import modele.Inventaire;
import modele.Lot;
import modele.Medicaments;

public class VenteController implements Initializable {


	@FXML
	private TextField barreRecherche;

	@FXML
	private JFXButton removeBtn;

	@FXML
	private JFXButton btn1;

	@FXML
	private JFXButton dashboard;

	@FXML
	private TableColumn<Medicaments, String> dci_vente;

	@FXML
	private TableColumn<Facture,String> dosage_facture;

	@FXML
	private TableColumn<Medicaments, String> dosage_vente;

	@FXML
	private TableColumn<Facture,String> nom_facture;

	@FXML
	private TableColumn<Facture,Integer> prix_facture;

	@FXML
	private TableColumn<Medicaments, Integer> prix_vente;

	@FXML
	private TableColumn<Facture,Integer> qte_facture;

	@FXML
	private TableColumn<Medicaments, Integer> qte_vente;

	@FXML
	private JFXButton statistique;

	@FXML
	private JFXButton stock;

	@FXML
	private TableView<Facture> tableFacture;

	@FXML
	private TableView<Medicaments> tableToutMedicaments;

	@FXML
	private Label titre;

	@FXML
	private JFXButton vente;

	@FXML
	private Label totalId;
	
	private int idVente;

	ObservableList<Medicaments> listeMedOb = FXCollections.observableArrayList();
	ObservableList<Facture> listeMedFact = FXCollections.observableArrayList();


	@FXML
	public void ok(ActionEvent action) throws SQLException, MalformedURLException, DocumentException, IOException {



		if(!(listeMedFact.isEmpty())) {

			Alert alert = new Alert(AlertType.CONFIRMATION);

			Optional<ButtonType> rpsBtn = alert.showAndWait();

			if(rpsBtn.get()==ButtonType.OK) {


				GestionLotDAO gestLot = new GestionLotDAO();
				GestionMedicamentDAO gestMedicamentDAO=new GestionMedicamentDAO();
				InventaireDAO inventDAO = new InventaireDAO();

				ArrayList<Inventaire> listeInventaire = new ArrayList<>();
				listeInventaire.addAll(inventDAO.getAll());

				int id = listeInventaire.get(listeInventaire.size()-1).getId_vente()+1;
				idVente=id;


				for (Facture facture : listeMedFact) {

					Lot lot = null;
					lot=gestLot.get(facture.getNum_lot());

					lot.qte_stockProperty().setValue(lot.getQte_stock()-facture.getQte_vendre());			
					gestLot.update(lot);


					Medicaments md =null;
					md = gestMedicamentDAO.get(facture.getDCI(), facture.getDosage());
					md.setQte_stock(md.getQte_stock()-facture.getQte_vendre());
					gestMedicamentDAO.update(md);



					inventDAO.insert(new Inventaire(id,facture.getDCI(),facture.getDosage(),facture.getQte_vendre(),facture.getPrix_vente(),facture.getNum_lot(),null,null));



				}

				imprimer();

				listeMedFact.clear();
				listeMedOb.clear();
				loadData();
				totalId.setText("000");
			}


		}else {
			Alert alt = new Alert(AlertType.INFORMATION, "liste vide !!!!");
			alt.showAndWait();
		}




	}



	@FXML
	private void fonctionLien(ActionEvent action) {


		Stage stage =(Stage) vente.getScene().getWindow();

		switch (((JFXButton)	action.getSource()).getId()) {

		case "statistique":

			System.out.println(((JFXButton)	action.getSource()).getId());	


			try {

				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/acceuil/Statistique.fxml"))));
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

		default:
			break;
		}
	}


	public void loadData() throws SQLException {

		GestionMedicamentDAO gmdao = new GestionMedicamentDAO();

		listeMedOb.addAll( (ArrayList<Medicaments>) gmdao.getAll());	

		dci_vente.setCellValueFactory(new PropertyValueFactory<Medicaments,String>("DCI"));
		dosage_vente.setCellValueFactory(new PropertyValueFactory<Medicaments,String>("dosage"));
		prix_vente.setCellValueFactory(new PropertyValueFactory<Medicaments,Integer>("prix_vente"));
		qte_vente.setCellValueFactory(new PropertyValueFactory<Medicaments,Integer>("qte_stock"));

		tableToutMedicaments.setItems(listeMedOb);


		nom_facture.setCellValueFactory(new PropertyValueFactory<Facture,String>("DCI"));
		dosage_facture.setCellValueFactory(new PropertyValueFactory<Facture,String>("dosage"));
		prix_facture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("prix_vente"));
		qte_facture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("qte_vendre"));

		tableFacture.setItems(listeMedFact);
		FilteredList<Medicaments> listeFiltrer = new FilteredList<>(listeMedOb,b->true); 


		barreRecherche.textProperty().addListener(((observable, oldValue, newValue) -> {
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

		sortedMedi.comparatorProperty().bind(tableToutMedicaments.comparatorProperty());
		tableToutMedicaments.setItems(sortedMedi);


	}



	@FXML
	int tableToutMouseClicked(MouseEvent event) throws SQLException {

		if((tableToutMedicaments.getSelectionModel().getSelectedItem()!=null)&&event.getClickCount()==2){

			Facture  fact = new Facture(tableToutMedicaments.getSelectionModel().getSelectedItem());

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/Interface/Vente/Dialog.fxml"));				
				DialogPane pane =(DialogPane)fxmlLoader.load();	

				Dialog<ButtonType> dialog = new Dialog<>();
				dialog.setDialogPane(pane);

				DialogController dialogController = fxmlLoader.getController();				
				dialogController.setFacture(fact);

				Optional<ButtonType> ok = dialog.showAndWait();

				if(ok.get()==ButtonType.OK&&fact.getNum_lot()!=0) {	

					if(listeMedFact.contains(fact))
						listeMedFact.remove(fact);

					if(fact.getQte_vendre()>(fact.getQte_stock()-fact.getQte_seuil())){
						Alert alert = new Alert(AlertType.ERROR, "quantite entrez invalide");
						alert.showAndWait();
						return 1;
					}
					listeMedFact.add(fact);
					calculerTotal();

				}else if(ok.get()==ButtonType.OK&&fact.getNum_lot()==0) {

					Alert alert = new Alert(AlertType.ERROR, "numero de lot null !!!!!!");
					alert.showAndWait();
					return 1;

				}



			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}


	@FXML
	void removeFunction(ActionEvent event) {

		if(this.tableFacture.getSelectionModel().getSelectedItem()!=null) {
			Facture ft = this.tableFacture.getSelectionModel().getSelectedItem();
			listeMedFact.remove(ft);
			calculerTotal();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			loadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public void calculerTotal() {
		Integer total =0;

		for (Facture facture : listeMedFact) {

			total += facture.getPrix_vente()*facture.getQte_vendre();
		}
		totalId.setText(total+"");

	}


	void imprimer() throws DocumentException, MalformedURLException, IOException {


		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("facture.pdf"));
		document.open();
		

		String format = "dd/mm/yy hh:mm" ;

		SimpleDateFormat formater = new SimpleDateFormat();
		java.util.Date date = new java.util.Date();

		com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:/Users/ZEUKENG FLORENT/Desktop/projet/eclipse-workspace/projetPharma/src/image/icon.png/");
		img.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);

		document.add(img);

		document.add(new Paragraph("    ********************************************  FLOFLO-PHARMA **********************************")) ;
		document.add(new Paragraph(date.toLocaleString()));
		document.add(new Paragraph("Delivrer par : njetejie Zeukeng Florent"));
		document.add(new Paragraph("numero facture : " + idVente+"\n \n \n \n"));

		
		
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new int[]{1, 2, 1, 1});
		table.addCell(createCell("nom", 2, 1, Element.ALIGN_LEFT));
		table.addCell(createCell("dosage", 2, 1, Element.ALIGN_LEFT));
		table.addCell(createCell("quantite", 2, 1, Element.ALIGN_LEFT));
		table.addCell(createCell("prix achat", 2, 1, Element.ALIGN_LEFT));
		
		
		for (Facture row : listeMedFact) {
			table.addCell(createCell(row.getDCI(), 1, 1, Element.ALIGN_LEFT));
			table.addCell(createCell(row.getDosage(), 1, 1, Element.ALIGN_LEFT));
			table.addCell(createCell(row.getQte_vendre()+"", 1, 1, Element.ALIGN_RIGHT));
			table.addCell(createCell(row.getPrix_vente()+"", 1, 1, Element.ALIGN_RIGHT));
		}
		
		
		table.addCell(createCell("Totals", 2, 3, Element.ALIGN_LEFT));
		table.addCell(createCell(totalId.getText()+"fcfa", 2, 1, Element.ALIGN_RIGHT));
		document.add(table);
		document.close();
		
		Desktop.getDesktop().open(new File("facture.pdf"));


	}


	public PdfPCell createCell(String content, float borderWidth, int colspan, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(content));
		cell.setBorderWidth(borderWidth);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

}
