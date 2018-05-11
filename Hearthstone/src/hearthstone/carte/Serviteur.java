package hearthstone.carte;

import hearthstone.commun.HearthstoneException;
import hearthstone.joueur.IJoueur;
import hearthstone.capacite.Capacite;
import hearthstone.capacite.ICapacite;

public class Serviteur implements ICarte{
	private String nom;
	private int cout;
	private int attaque;
	private int pv;
	private ICapacite capacite;	
	private IJoueur proprietaire;
	
	///////////////////////////////////////////////////////////////////////
	
	public ICapacite getCapacite() {
		return capacite;
	}
	public void setCapacite(ICapacite capacite) {
		this.capacite = capacite;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	public void setPV(int pv) {
		this.pv = pv;
	}
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	///////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "Serviteur [nom=" + nom +
				", cout=" + cout +
				", attaque=" + attaque +
				", pv=" + pv +
				", capacite=" + capacite + "]";
	}
	
	///////////////////////////////////////////////////////////////////
	// Implementation de l'interface ICarte
	@Override
	public String getNom() {
		return nom;
	}
	
	@Override
	public int getCout() {
		return cout;
	}
	@Override
	public IJoueur getProprietaire() {
		return proprietaire;
	}
	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean disparait() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/////////////////////////////////////////////////////////////
	// Méthodes statiques

	public static Serviteur lireDepuisChaine( String laChaine )
	{
	// Lecture d'un serviteur depuis une chaine
	// La chaine se compose des champs suivants =
	// Serviteur;Nom du serviteur;Cout en mana;Attaque;PV;Nom de capacité;Héros
	Serviteur result = null;
	String[] champs = laChaine.split( ";" );

	if( champs.length != 7 )
	throw new HearthstoneException(
	"La chaine \"" +laChaine+"\" n'est pas un serviteur valide");
	
	result = new Serviteur();
	result.setNom( champs[1] );
	result.setCout( Integer.parseInt( champs[2] ) );
	result.setAttaque( Integer.parseInt( champs[3] ) );
	result.setPV( Integer.parseInt( champs[4] ) );
	
	if( champs[5] != null && champs[5].length() > 0 )
	{
		// On ajoute une capacité vide avec juste le nom. L'objet sera mis dans le main après 
		// lecture de la liste des capacités dans le fichier
		Capacite capaciteNom = new Capacite();
		capaciteNom.setNom( champs[5] );
		result.setCapacite( capaciteNom );
	}

	return result;
	}
	
	
}
