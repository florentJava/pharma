package modele;

import java.util.Objects;

public class Utilisateur {
	
	private String nom;
	private String pass;
	
	
	public Utilisateur(String nom, String pass) {
		super();
		this.nom = nom;
		this.pass = pass;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nom, pass);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(pass, other.pass);
	}
	
	

	
}
