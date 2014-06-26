package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tache implements ComposantProjet {

	public int numero;
	public Date dateDepart;
	public Date deadLine;
	public int poids;
	public String description;
	public double avancement;
	public List<Alerte> alertes;
	public List<Personne> personnes;

	public Tache(int numero, Date dateDepart, Date deadLine, int poids, String description, double avancement) {
		this.numero = numero;
		this.dateDepart = dateDepart;
		this.deadLine = deadLine;
		this.poids = poids;
		this.description = description;
		this.avancement = avancement;
		this.alertes = new ArrayList<Alerte>();
		this.personnes = new ArrayList<Personne>();
	}

	public int getNumero() {
		return numero;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public int getPoids() {
		return poids;
	}

	public String getDescription() {
		return description;
	}

	public List<Alerte> getAlertes() {
		return alertes;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	@Override
	public double getAvancement() {
		// TODO Auto-generated method stub
		return avancement;
	}

	@Override
	public String getDetailAlert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNombrePersonne() {
		// TODO Auto-generated method stub
		return 0;
	}

}
