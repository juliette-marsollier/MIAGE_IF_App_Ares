package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projet implements ComposantProjet {

	private String nom;
	private String description;
	private Date dateDepart;
	private Date deadLine;
	private String type;
	private List<ComposantProjet> sprints;
	private List<Permission> permissions;

	public Projet(String nom, String description, Date dateDepart, Date deadLine, String type) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDepart = dateDepart;
		this.deadLine = deadLine;
		this.type = type;
		sprints = new ArrayList<ComposantProjet>();
		permissions = new ArrayList<Permission>();
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public String getType() {
		return type;
	}

	public List<ComposantProjet> getSprints() {
		return sprints;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	@Override
	public double getAvancement() {
		double avancement=0;
		int sommePoids = 0;
		double sommeProdPoidsAvancement = 0.0;
		
		for(ComposantProjet cp : sprints){
			if(cp instanceof Sprint){
				Sprint sprint = (Sprint) cp;
				for(ComposantProjet cp2 : sprint.getTaches()){
					if(cp2 instanceof Tache){
						Tache tache = (Tache) cp2;
						sommePoids+=tache.getPoids();
						sommeProdPoidsAvancement+=tache.getPoids()*tache.getAvancement();
					}
				}
			}
		}
		
		if(sommePoids!=0)
			avancement = sommeProdPoidsAvancement/sommePoids;
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
