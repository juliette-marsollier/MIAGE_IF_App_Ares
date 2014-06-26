package test;

import java.util.Date;

import modele.ComposantProjet;
import modele.Permission;
import modele.Personne;
import modele.Projet;
import modele.ScrumDeScrum;
import modele.Sprint;
import modele.Tache;
import junit.framework.TestCase;

public class TestCreationProjet extends TestCase {

	public void testNewTache() {
		Date dateDepart = new Date();
		Date deadLine = new Date();
		String description = "Test de la nouvelle tache";
		double avancement = 0.4;
		int poids = 3;
		int numero = 1;

		Tache tache = new Tache(numero, dateDepart, deadLine, poids, description, avancement);
		assertEquals(tache.getNumero(), numero);
		assertEquals(tache.getDateDepart(), dateDepart);
		assertEquals(tache.getDeadLine(), deadLine);
		assertEquals(tache.getPoids(), poids);
		assertEquals(tache.getDescription(), description);
		assertEquals(tache.getAvancement(), avancement);

		assertNotNull(tache.getPersonnes());
		assertNotNull(tache.getAlertes());

	}

	public void testNewSprint() {
		int numero = 1;
		Date dateDepart = new Date();
		Date deadLine = new Date();
		Sprint sprint = new Sprint(numero, dateDepart, deadLine);
		assertEquals(sprint.getNumero(), numero);
		assertEquals(sprint.getDateDepart(), dateDepart);
		assertEquals(sprint.getDeadLine(), deadLine);
		assertNotNull(sprint.getTaches());
	}

	public void testNewProjet() {
		String nom = "Nouveau projet";
		String description = "Test du nouveau projet";
		Date dateDepart = new Date();
		Date deadLine = new Date();
		String type = "Support Front Office";

		Projet projet = new Projet(nom, description, dateDepart, deadLine, type);
		assertEquals(projet.getNom(), nom);
		assertEquals(projet.getDescription(), description);
		assertEquals(projet.getDateDepart(), dateDepart);
		assertEquals(projet.getDeadLine(), deadLine);
		assertEquals(projet.getType(), type);
		assertNotNull(projet.getPermissions());
		assertNotNull(projet.getSprints());

	}

	public void testNewScrumDeScrum() {
		String description = "Nouveau Scrum de Scrum";
		ScrumDeScrum sds = new ScrumDeScrum(description);
		assertEquals(sds.getDescription(), description);
	}

	public void testNewPersonne() {
		String nom = "Toto";
		String prenom = "Titi";
		String statut = "Chef de projet";
		Personne p = new Personne(nom, prenom, statut);
		assertEquals(p.getNom(), nom);
		assertEquals(p.getPrenom(), prenom);
		assertEquals(p.getStatut(), statut);
		assertNotNull(p.getComposantProjet());
		assertNotNull(p.getPermissions());

	}

	public void testNewPermission() {
		int type = 1;
		String description = "Permission de créer un projet";
		Permission permission = new Permission(type, description);
		assertEquals(permission.getType(), type);
		assertEquals(permission.getDescription(), description);
		assertNotNull(permission.getPersonnes());
		assertNotNull(permission.getProjets());
	}

	// UserStorie 1
	public void testCreationSDS() {
		// création d'un SDS
		String description = "Nouveau Scrum de Scrum";
		ScrumDeScrum sds = new ScrumDeScrum(description);

		// création de plusieurs projets
		Date dateDepart = new Date();
		Date deadLine = new Date();

		Projet projet1 = new Projet("Projet 1", "Projet pour gérer le support FO", dateDepart, deadLine, "Support Front office");
		Projet projet2 = new Projet("Projet 2", "Projet pour gérer le pricing de produit", dateDepart, deadLine, "Pricing Produit");

		// ajout des projets dans le sds
		sds.getProjets().add(projet1);
		sds.getProjets().add(projet2);

		testAjoutSDS(sds);

		// création des sprint
		Sprint sprint1 = new Sprint(1, dateDepart, deadLine);
		Sprint sprint2 = new Sprint(2, dateDepart, deadLine);
		Sprint sprint3 = new Sprint(3, dateDepart, deadLine);

		// ajout des sprints
		projet1.getSprints().add(sprint1);
		projet1.getSprints().add(sprint2);
		projet1.getSprints().add(sprint3);

		// projet2.getSprints().add(sprint2);
		projet2.getSprints().add(sprint3);

		testAjoutProjet(projet1, 3);
		testAjoutProjet(projet2, 1);
		testAjoutSDS(sds);

		// création des tâches
		Tache tache1 = new Tache(1, dateDepart, deadLine, 3, "Test des postes", 0.0);
		Tache tache2 = new Tache(2, dateDepart, deadLine, 5, "Test des claviers", 0.1);

		Tache tache5 = new Tache(5, dateDepart, deadLine, 8, "Methode à revoir", 0.0);
		Tache tache6 = new Tache(6, dateDepart, deadLine, 8, "Xla a unifier", 0.0);

		Tache tache3 = new Tache(3, dateDepart, deadLine, 13, "Test de pricing produit", 0.4);
		Tache tache4 = new Tache(4, dateDepart, deadLine, 5, "Dicuter avec les traders de nouvel méthode", 0.0);

		// Ajout des tâches dans les Sprints
		sprint1.getTaches().add(tache1);
		sprint1.getTaches().add(tache2);
		sprint2.getTaches().add(tache5);
		sprint2.getTaches().add(tache6);
		sprint3.getTaches().add(tache3);
		sprint3.getTaches().add(tache4);

		testAjoutTache(sprint1, 2);
		testAjoutTache(sprint2, 2);
		testAjoutTache(sprint3, 2);

		// création de personne
		Personne p1 = new Personne("Sayd", "Marc", "Chef de projet");
		Personne p2 = new Personne("Marsollier", "Juliette", "Developer");
		Personne p3 = new Personne("Lesne", "Loic", "Developer");

		Permission permission = new Permission(1, "Droit de créer un projet");
		permission.getPersonnes().add(p1);
		permission.getProjets().add(projet1);
		permission.getProjets().add(projet2);

		assertTrue(permission.getProjets().size() == 2);

	}

	public void testZoomSurAvancement() {
		ScrumDeScrum sds = creationSDS();
		//Avancement de sds :		
		assertEquals(sds.getAvancement(),0.471,0.01);
		
		Projet p1 = (Projet) sds.getProjets().get(0);
		assertEquals(p1.getAvancement(), 0.55,0.01);
		
		Projet p2 = (Projet) sds.getProjets().get(1);
		assertEquals(p2.getAvancement(), 0.28,0.01);
		
		Sprint s1 = (Sprint) p1.getSprints().get(0);
		assertEquals(s1.getAvancement(), 0.73,0.01);
		
		Sprint s2 = (Sprint) p1.getSprints().get(1);
		assertEquals(s2.getAvancement(), 0.75, 0.01);
		
		Tache t1 = (Tache) s1.getTaches().get(0);
		assertEquals(t1.getAvancement(),0.3);
		
		Tache t2 = (Tache) s1.getTaches().get(1);
		assertEquals(t2.getAvancement(), 1.0);
	}

	private void testAjoutTache(Sprint sprint1, int sizeTache) {
		for (ComposantProjet tache : sprint1.getTaches()) {
			assertTrue(tache instanceof Tache);
		}
		assertTrue(sprint1.getTaches().size() == sizeTache);
	}

	private void testAjoutProjet(Projet projet1, int sizeSprint) {
		for (ComposantProjet sprint : projet1.getSprints()) {
			assertTrue(sprint instanceof Sprint);
		}
		assertTrue(projet1.getSprints().size() == sizeSprint);
	}

	private void testAjoutSDS(ScrumDeScrum sds) {
		for (ComposantProjet projet : sds.getProjets()) {
			assertTrue(projet instanceof Projet);
		}
		assertTrue(sds.getProjets().size() == 2);
	}

	private ScrumDeScrum creationSDS() {
		String description = "Nouveau Scrum de Scrum";
		ScrumDeScrum sds = new ScrumDeScrum(description);

		// création de plusieurs projets
		Date dateDepart = new Date();
		Date deadLine = new Date();

		Projet projet1 = new Projet("Projet 1", "Projet pour gérer le support FO", dateDepart, deadLine, "Support Front office");
		Projet projet2 = new Projet("Projet 2", "Projet pour gérer le pricing de produit", dateDepart, deadLine, "Pricing Produit");

		// ajout des projets dans le sds
		sds.getProjets().add(projet1);
		sds.getProjets().add(projet2);

		// création des sprint
		Sprint sprint1 = new Sprint(1, dateDepart, deadLine);
		Sprint sprint2 = new Sprint(2, dateDepart, deadLine);
		Sprint sprint3 = new Sprint(3, dateDepart, deadLine);

		// ajout des sprints
		projet1.getSprints().add(sprint1);
		projet1.getSprints().add(sprint2);
		projet1.getSprints().add(sprint3);

		// projet2.getSprints().add(sprint2);
		projet2.getSprints().add(sprint3);
		Tache tache1 = new Tache(1, dateDepart, deadLine, 3, "Test des postes", 0.3);
		Tache tache2 = new Tache(2, dateDepart, deadLine, 5, "Test des claviers", 1.0);

		Tache tache5 = new Tache(5, dateDepart, deadLine, 8, "Methode à revoir", 0.6);
		Tache tache6 = new Tache(6, dateDepart, deadLine, 8, "Xla a unifier", 0.9);

		Tache tache3 = new Tache(3, dateDepart, deadLine, 13, "Test de pricing produit", 0.4);
		Tache tache4 = new Tache(4, dateDepart, deadLine, 5, "Dicuter avec les traders de nouvel méthode", 0.0);

		// Ajout des tâches dans les Sprints
		sprint1.getTaches().add(tache1);
		sprint1.getTaches().add(tache2);
		sprint2.getTaches().add(tache5);
		sprint2.getTaches().add(tache6);
		sprint3.getTaches().add(tache3);
		sprint3.getTaches().add(tache4);
		// création de personne
		Personne p1 = new Personne("Sayd", "Marc", "Chef de projet");
		Personne p2 = new Personne("Marsollier", "Juliette", "Developer");
		Personne p3 = new Personne("Lesne", "Loic", "Developer");

		Permission permission = new Permission(1, "Droit de créer un projet");
		permission.getPersonnes().add(p1);
		permission.getProjets().add(projet1);
		permission.getProjets().add(projet2);

		return sds;

	}

}
