package airChance;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DemandeSaisie {

	    /**
	     * Invite l'utilisateur à saisir un string à l'aide du message
	     *
	     * @param message
	     * @param borneInf
	     * @param borneSup
	     * @return Le string saisi par l'utilisateur
	     */
	    public static String saisirString(String message, int borneInf, int borneSup) {
	        String reponse = null;
	        boolean pattern = false;
	        do {
	            System.out.println(message);
	            System.out.flush();
	            reponse = LectureClavier.lireChaine();
	            
	            if( ( reponse.length()  >= borneInf ) && (reponse.length()  <= borneSup) ){
	                pattern = true;
	            }
	            else if (reponse.length() > borneSup){
	                System.out.println(reponse + " trop long ...\n");
	            }     
	            else{
	                System.out.println(reponse + " trop court ...\n");
	            }
	            
	        } while(!pattern); 
	        System.out.println("\n");

	        return reponse;
	    }

	    /**
	     * Invite l'utilisateur à saisir un int à l'aide du message et s'assure que
	     * la valeur saisie est entre les bones inf et sup
	     *
	     * @param message
	     * @param borneInf
	     * @param borneSup
	     * @return Le int saisi par l'utilisateur
	     */
	    public static int saisirInt(String message, int borneInf, int borneSup) {
	        boolean pattern = false;   
	        int reponse;
	        do {
	            System.out.println(message);
	            System.out.flush();
	            reponse = LectureClavier.lireEntier(">");
	            if( ( reponse  >= borneInf ) && (reponse  <= borneSup) ){
	                pattern = true;
	            }
	            else {
	                System.out.println(message + "\n\n/!\\\n\t"
	                        + "Votre choix n'est pas disponible. "
	                        + "Assurez-vous de choisir entre " + borneInf + " et " + (borneSup - 1) + "\n"
	                        + "\n/!\\\n");
	            }              
	        } while (!pattern);
	        System.out.println("\n");
	        
	        return reponse;
	    }

	    /**
	     * Invite l'utilisateur à saisir un double à l'aide du message et s'assure
	     * que la valeur saisie est entre les bones inf et sup
	     *
	     * @param message
	     * @param borneInf
	     * @param borneSup
	     * @return Le double saisi par l'utilisateur
	     */
	    public static double saisirDouble(String message, int borneInf, int borneSup) {
	        double reponse;
	        do {
	            System.out.println(message);
	            System.out.flush();
	            reponse = LectureClavier.lireDouble(">");
	        } while ((reponse < borneInf) && (reponse > borneSup));

	        return reponse;
	    }
	    
	    /**
	     * Vérifie si la chaine passée en paramètre correpond à la longueur entre borneInf et borneSup
	     * @param chaine
	     * @param borneInf
	     * @param borneSup
	     * @return pattern
	     */
	    public static boolean verifChaine(String chaine, int borneInf, int borneSup){
	        boolean pattern = false;
	        
	        if( ( chaine.length()  >= borneInf ) && (chaine.length()  <= borneSup) ){
	            pattern = true;
	        }
	        else if (chaine.length() > borneSup){
	            System.out.println(chaine + " trop long ...\n");
	        }     
	        else{
	            System.out.println(chaine + " trop court ...\n");
	        }
	            
	        return pattern;
	    }
	    
	    public static Timestamp saisirDateTimestamp(String message, int anneeMin, int anneeMax) throws ParseException {
	    	System.out.println(message + ".\n/!\\ Les dates sont au format (JJ-MM-AAAA HH:MM)");
            String Jour = DemandeSaisie.saisirInt("jour (JJ)", 1, 31) + "";
            String Mois = DemandeSaisie.saisirInt("Mois  (MM)", 1, 12) + "";
            String Annee = DemandeSaisie.saisirInt("Année  (AAAA)", anneeMin, anneeMax) + "";
            String hh = DemandeSaisie.saisirInt("Heure  (HH)", 0, 24) + "";
            System.out.println("");
            String mm = DemandeSaisie.saisirInt("Minutes  (MM)", 0, 60) + "";
            String ss = "00";
            String debut = Jour + "/" + Mois + "/" + Annee +" "+hh+":"+mm+":"+ss;
            
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


			java.util.Date DateDebut = formatter.parse(debut);
			return new Timestamp(DateDebut.getTime());
	    }
	   
	    
	}
