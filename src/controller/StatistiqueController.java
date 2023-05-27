package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatistiqueController implements Initializable {
	


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
    private void fonctionLien(ActionEvent action) {
		System.out.println("hellooooooooooo");
		
		
		Stage stage =(Stage) vente.getScene().getWindow();
		Double width = stage.getWidth();
		Double heigth = stage.getHeight();


		
		switch (((JFXButton)	action.getSource()).getId()) {
		case "vente":
						
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
			
		default:
			break;
		}
		

	
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
