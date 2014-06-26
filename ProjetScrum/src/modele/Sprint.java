package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Sprint implements ComposantProjet {

	public int numero;
	public Date dateDepart;
	public Date deadLine;
	public List<ComposantProjet> taches;
	
	
	
	public Sprint(int numero, Date dateDepart, Date deadLine) {
		super();
		this.numero = numero;
		this.dateDepart = dateDepart;
		this.deadLine = deadLine;
		taches = new ArrayList<ComposantProjet>();
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



	public List<ComposantProjet> getTaches() {
		return taches;
	}



	@Override
	public double getAvancement() {
		double avancement=0;
		int sommePoids = 0;
		double sommeProdPoidsAvancement = 0.0;
		
		for(ComposantProjet cp : taches){
			if(cp instanceof Tache){
				Tache tache = (Tache) cp;
				sommePoids+=tache.getPoids();
				sommeProdPoidsAvancement+=tache.getPoids()*tache.getAvancement();
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
