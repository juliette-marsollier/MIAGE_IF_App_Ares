package modele;

import java.util.ArrayList;
import java.util.List;

public class Permission {

	private int type;
	private String description;
	private List<Personne> personnes;
	private List<Projet> projets;

	public Permission(int type,String description) {
		this.type = type;
		this.description = description;
		personnes = new ArrayList<Personne>();
		projets = new ArrayList<Projet>();
	}

	public int getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public List<Projet> getProjets() {
		return projets;
	}

}
