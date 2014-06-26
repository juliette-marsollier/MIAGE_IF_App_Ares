package modele;

import java.util.ArrayList;
import java.util.List;

public class ScrumDeScrum implements ComposantProjet {

	public String description;
	public List<ComposantProjet> projets;

	public ScrumDeScrum(String description) {
		this.description = description;
		projets = new ArrayList<ComposantProjet>();
	}

	public String getDescription() {
		return description;
	}

	public List<ComposantProjet> getProjets() {
		return projets;
	}

	@Override
	public double getAvancement() {
		double avancement = 0;
		int sommePoids = 0;
		double sommeProdPoidsAvancement = 0.0;

		for (ComposantProjet cp0 : projets) {
			if (cp0 instanceof Projet) {
				Projet projet = (Projet) cp0;
				for(ComposantProjet cp : projet.getSprints()){
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
