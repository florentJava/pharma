package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Medicaments;

public class MedInfoController  implements Initializable{

    @FXML
    private Label description;

    @FXML
    private ImageView image;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void showMedicament(Medicaments md) {
		description.setText(md.getLibelle());
		

		String url =md.getImage();
		
			File file = new File(url);
	        Image imageSimple = new Image(file.toURI().toString());
	        if (file.exists())
	        	image.setImage(imageSimple);
	}

}
