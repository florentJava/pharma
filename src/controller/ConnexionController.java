package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import DAO.GestionUtilisateurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modele.Utilisateur;

public class ConnexionController {

    @FXML
    private Label error;

    @FXML
    private PasswordField password;

    @FXML
    private JFXButton submit;

    @FXML
    private TextField userName;
    
    

	@FXML
    void goAway(ActionEvent event) throws SQLException, IOException {
    	
    	String motPass = password.getText();
    	String nom = userName.getText();
    	
    	System.out.println(motPass);
    	System.out.println(nom);
    	if(motPass.isBlank()||nom.isBlank()) {
    		
    	}else {
    		GestionUtilisateurDAO gestUser = new GestionUtilisateurDAO();
    		ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    		
    		utilisateurs.addAll(gestUser.getAll());
    		
    		for (Utilisateur utilisateur : utilisateurs) {
    			System.out.println(utilisateur.getPass());
    			
				if(((String)utilisateur.getNom()).equals(nom)) {
					Stage primaryStage = new Stage();
					System.out.println("ConnexionController.goAway()");
					Parent root = FXMLLoader.load(getClass().getResource("/Interface/vente/Vente.fxml"));

					Scene scene = new Scene(root);
					
					primaryStage.setScene(scene);
					primaryStage.show();
					
					
					((Stage) submit.getScene().getWindow()).close();
					
					
				}
			}
    		
    	}

    }

}
