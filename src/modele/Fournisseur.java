package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fournisseur {
	private IntegerProperty id;
	private StringProperty nom;
	private StringProperty tell;
	private StringProperty email;
	
	
	public Fournisseur(Integer id,String nom, String tell, String email) {
		super();
		this.id =new SimpleIntegerProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.tell = new SimpleStringProperty(tell);
		this.email = new SimpleStringProperty(email);
	}



	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}



	public String getNom() {
		return nom.get();
	}


	public void setNom(String nom) {
		this.nom.set(nom);;
	}


	public String getTell() {
		return tell.get();
	}


	public void setTell(String tell) {
		this.tell.set(tell);
	}


	public String getEmail() {
		return email.get();
	}


	public void setEmail(String email) {
		this.email.set(email);
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);;
	}
	
	
	public  IntegerProperty idProperty() {
		return this.id;
	}
	
	public  StringProperty nomProperty() {
		return this.nom;
	}
	
	public  StringProperty tellProperty() {
		return this.tell;
	}
	
	public  StringProperty emailProperty() {
		return this.email;
	}



	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", tell=" + tell + ", email=" + email + "]";
	}
	
	
	

}
