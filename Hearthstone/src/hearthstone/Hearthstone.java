package hearthstone;
import hearthstone.carte.ICarte;
import hearthstone.capacite.Capacite;
import hearthstone.capacite.ICapacite;
import hearthstone.carte.Sort;
import hearthstone.carte.Serviteur;
import hearthstone.commun.HearthstoneException;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Hearthstone {

	List<ICarte> lesCartes;
	List<ICapacite> lesCapacites;
	
	public Hearthstone() {
		super();
		this.lesCartes = new ArrayList<ICarte>();
		this.lesCapacites = new ArrayList<ICapacite>();
	}
	
	/////////////////////////////////////////////////////////////////
	private List<ICapacite> lireLesCapacites(String nomFichier) {
		List<ICapacite> result = new ArrayList<ICapacite>();
		
		try {
			InputStream flux=new FileInputStream(nomFichier); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			
			while ((ligne=buff.readLine())!=null)
			{
				
				result.add( Capacite.lireDepuisChaine(ligne) );
			}
			buff.close(); 
			
		} catch (FileNotFoundException e) {
		     
		    e.printStackTrace();
		} catch (IOException e) {
		     
		    e.printStackTrace();
		}		
		
		return result;
	}
	
	private List<ICarte> lireLesCartes(String nomFichier) {
		List<ICarte> result = new ArrayList<ICarte>();
		ICarte carteLue = null;
		
		try {
			InputStream flux=new FileInputStream(nomFichier); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			
			while ((ligne=buff.readLine())!=null)
			{
				if( ligne.startsWith( IHearthstone.TYPELIGNE_SORT))
					carteLue = Sort.lireDepuisChaine( ligne );
				if( ligne.startsWith( IHearthstone.TYPELIGNE_SERVITEUR))
					carteLue = Serviteur.lireDepuisChaine( ligne );
				
				// Si la capacité n'est pas nulle, on cherche l'objet Capacité 
				// d'après son nom
				if( carteLue.getCapacite() != null )
				{
					String nomCapacite = carteLue.getCapacite().getNom();
					
					if( nomCapacite == null || nomCapacite.length() < 1 )
						throw new HearthstoneException( "La capacité de la carte \"" + carteLue.getNom() +"\" n'est pas définie" );
					
					boolean capaciteTrouvee = false;
					for( ICapacite capaciteCourante : lesCapacites )
					{
						if( nomCapacite.contains( capaciteCourante.getNom()) )
						{
							carteLue.setCapacite( capaciteCourante );
							capaciteTrouvee = true;
						}
					}
					
					if( ! capaciteTrouvee )
						throw new HearthstoneException( "La capacité \"" + nomCapacite +"\" de la carte \""+carteLue.getNom() + "\" n'existe pas");
					
				}
				result.add( carteLue );
			}
			buff.close(); 
			
		} catch (FileNotFoundException e) {
		     
		    e.printStackTrace();
		} catch (IOException e) {
		     
		    e.printStackTrace();
		}
		
		
		
		
		return result;
	}
	
	private void afficherLesCapacites(List<ICapacite> listeCapacite) {
		System.out.println( "Liste des capacites définies dans l'application:");
		if( listeCapacite != null && listeCapacite.size() > 0)
		{
			for( ICapacite laCapacite : listeCapacite )
				System.out.println( laCapacite );
		}
		System.out.println( "Fin de la liste");
	}

	private void afficherLesCartes(List<ICarte> listeCarte) {
		System.out.println( "Liste des cartes définies dans l'application:");
		if( listeCarte != null && listeCarte.size() > 0)
		{
			for( ICarte laCarte : listeCarte )
				System.out.println( laCarte );
		}
		System.out.println( "Fin de la liste");
	}
	
	public void lancer()
	{
		try
		{
			lesCapacites.addAll( lireLesCapacites("ressources/ListeDeCapacites.txt"));
			lesCartes.addAll( lireLesCartes("ressources/ListeDeSorts.txt") );
			afficherLesCapacites( lesCapacites );
			afficherLesCartes( lesCartes );
		}
		catch( HearthstoneException e )
		{
			System.out.println("Erreur dans l'application");
			System.out.println( e );
		}
	}
	
	///////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		Hearthstone application = new Hearthstone();
        application.lancer();
	}

}
