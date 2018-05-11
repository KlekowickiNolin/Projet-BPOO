package hearthstone.capacite;
import java.util.List;
import hearthstone.IHearthstone;
import hearthstone.carte.Sort;
import hearthstone.commun.HearthstoneException;;
public class Capacite implements ICapacite  {
	private String nom;
	private String description;
	private String action;
	private String valeur;
	private String declenchement;
	private List<ICible> lesCibles;
	
	private void appliquerCapacite(List<ICible> lesCibles) {
		for(ICible cibleCourante: lesCibles)
		{
			cibleCourante.subir(action, valeur);
		}
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ICible> getLesCibles() {
		return lesCibles;
	}
	public void setLesCibles(List<ICible> lesCibles) {
		this.lesCibles = lesCibles;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getDeclenchement() {
		return declenchement;
	}
	public void setDeclenchement(String declenchement) {
		this.declenchement = declenchement;
	}
	
	////////////////////////////////////////////////////////
	
	
	
	/**
     * Certaines capacité agissent en début de tour (J'ai pas d'exemple mais on ne sait jamais)
     * @throws HearthstoneException En cas de problème...
     */
    public void executerEffetDebutTour() throws HearthstoneException{
    	if (declenchement.contains(IHearthstone.DECLENCHEMENT_DEBUT))
    		appliquerCapacite(lesCibles);
    }
    
    /**
     * Certaines capacité agissent en fin de tour (J'ai pas d'exemple mais on ne sait jamais)
     * @throws HearthstoneException En cas de problème...
     */
    public void executerEffetFinTour() throws HearthstoneException{
    	if (declenchement.contains(IHearthstone.DECLENCHEMENT_FIN))
    		appliquerCapacite(lesCibles);
    }
    
    /**
     * Certaines capacité agissent quand on le demande, et éventuellement sur une cible...
     * @throws HearthstoneException En cas de problème...
     */
    public void executerAction(Object cible) throws HearthstoneException
    {
    	appliquerCapacite(lesCibles);
    }

    /**
     * Certaines capacité agissent en début de mise en jeu. C'est souvent le cas des sorts.
     * @throws HearthstoneException En cas de problème...
     */
    public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException{
    	if (declenchement.contains(IHearthstone.DECLENCHEMENT_ARRIVEE))
    		appliquerCapacite(lesCibles);
    }
    
    /**
     * Certaines capacité agissent lorsque la carte disparaît du jeu (comme le râle d'agonie...). 
     * @throws HearthstoneException En cas de problème...
     */
    public void executerEffetDisparition(Object cible) throws HearthstoneException{
    	if (declenchement.contains(IHearthstone.DECLENCHEMENT_DISPARITION))
    		appliquerCapacite(lesCibles);
    }
    
    
    @Override
	public String toString() {
		return "Capacite [nom=" + nom + ", action=" + action + ", valeur=" + valeur
				+ ", declenchement=" + declenchement + "]";
	}
	////////////////////////////////////////////////////////
    public static Capacite lireDepuisChaine( String laChaine )
	{
		// Lecture d'une capacite depuis une chaine
		// La chaine se compose des champs suivants =
		// Nom;Description;Action;Valeur;Declenchement;Cible
		Capacite result = null;
		String[] champs = laChaine.split( ";" );
		
		if( champs.length != 5 )
			throw new HearthstoneException(
					"La chaine \"" +laChaine+"\" n'est pas une capacite valide");
		
		result = new Capacite();
		result.setNom( champs[0] );
		result.setDescription(champs[1]);
		result.setAction(champs[2]);
		result.setValeur( champs[3] );
		result.setDeclenchement(champs[4]);
		// TODO :  Ajouter la cible plus tard
		return result;
	}

}
