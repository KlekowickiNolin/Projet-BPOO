package hearthstone.carte;

import hearthstone.commun.HearthstoneException;
import hearthstone.joueur.IJoueur;
import hearthstone.capacite.ICapacite;
import hearthstone.capacite.Capacite;

public class Sort implements ICarte{
	private String nom;
	private ICapacite capacite;
	private int cout;
	private IJoueur proprietaire;
	private boolean utilise;
	
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
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	public boolean isUtilise() {
		return utilise;
	}
	public void setUtilise(boolean utilise) {
		this.utilise = utilise;
	}
	///////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "Sort [nom=" + nom + ", cout=" + cout + "]";
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
		// utiliser sort = faire disparaitre
		return isUtilise();
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	/////////////////////////////////////////////////////////////
	// Méthodes statiques
	
	public static Sort lireDepuisChaine( String laChaine )
	{
		// Lecture d'un sort depuis une chaine
		// La chaine se compose des champs suivants =
		// Sort;Nom du sort;Cout en mana;Nom de capacité;Héros
		Sort result = null;
		String[] champs = laChaine.split( ";" );
		
		if( champs.length != 5 )
			throw new HearthstoneException(
					"La chaine \"" +laChaine+"\" n'est pas un sort valide");
		
		result = new Sort();
		result.setNom( champs[1] );
		result.setCout( Integer.parseInt( champs[2] ) );
		// On ajoute une capacité vide avec juste le nom. L'objet sera mis dans le main après 
		// lecture de la liste des capacités dans le fichier
		Capacite capaciteNom = new Capacite();
		capaciteNom.setNom( champs[3] );
		result.setCapacite( capaciteNom );
		return result;
	}
}
