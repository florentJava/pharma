module projetPharma {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires itextpdf;
	requires com.jfoenix;

	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	opens modele to javafx.graphics,javafx.base, javafx.fxml;


}
