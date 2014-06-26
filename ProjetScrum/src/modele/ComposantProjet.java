package modele;

public interface ComposantProjet {

	//somme poid*avancement / somme (poids)
	public double getAvancement();
	public String getDetailAlert();
	public int getNombrePersonne();
}
