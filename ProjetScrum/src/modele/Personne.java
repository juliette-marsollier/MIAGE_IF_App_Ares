package modele;

import java.util.ArrayList;
import java.util.List;

public class Personne {

	private String nom;
	private String prenom;
	private String statut;
	private List<ComposantProjet> composantProjet;
	private List<Permission> permissions;

	public Personne(String nom, String prenom, String statut) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		composantProjet = new ArrayList<ComposantProjet>();
		permissions = new ArrayList<Permission>();
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getStatut() {
		return statut;
	}

	public List<ComposantProjet> getComposantProjet() {
		return composantProjet;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

}
