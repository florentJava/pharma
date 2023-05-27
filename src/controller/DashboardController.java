package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {


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


		Stage stage =(Stage) vente.getScene().getWindow();

		switch (((JFXButton)	action.getSource()).getId()) {
		case "vente":

			try {
				stage.getScene().setRoot( ((Parent) FXMLLoader.load(getClass().getResource("/Interface/vente/Vente.fxml"))));

			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
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
		}


	}


}
