package Controleur;


 
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
 
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import Modele.Noeud;
import Modele.Troncon;
import Modele.Zone;



/**
 * @author Yousra
 */


public class ParseurXML {
	
	public ParseurXML() {
		
		}
	/**
	 * @param File xmlFilePath le chemin du fichier xml Plan
	 * @return Zone
	 */
	public boolean verifierUnfichierXML(String xmlFilePath, String xsdFilePath){
		try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
	}
	public Zone RecupererZoneXML(String xmlFilePathPlan, String xsdFilePathPlan) throws FileNotFoundException, NumberFormatException, SAXException, org.xml.sax.SAXException {
		
		File xml = new File(xmlFilePathPlan);
		 Zone zone=null;
	
		if (!xml.exists()) {
			throw new FileNotFoundException();
		}
		else {
			try {
				
				verifierUnfichierXML(xmlFilePathPlan, xsdFilePathPlan);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				org.w3c.dom.Document document = dBuilder.parse(xml);           
                Element racine = document.getDocumentElement();
               
               if (racine.getNodeName().equals("Reseau")) 
               {   
            	  //On r�cup�re tous les noeuds d'une zone
                   NodeList listeNoeudsXML = racine.getElementsByTagName("Noeud");
                   Set<Noeud> listeNoeuds = new HashSet<Noeud>();
                   
                   for(int i=0; i<listeNoeudsXML.getLength();i++) 
                   {
                	   Noeud noeud = new Noeud((Element)listeNoeudsXML.item(i));
                	   listeNoeuds.add(noeud);              	   
                   }
                   
                   Set<Troncon> listeTousLesTroncons = new HashSet<Troncon>();
                   for(int i=0; i<listeNoeudsXML.getLength();i++) 
                   {                	    
                	   Element noeudElement = (Element) listeNoeudsXML.item(i);
                	   //Integer idNoeudCourant = Integer.parseInt(noeudElement.getAttribute("id"));
                	   
                	   //On r�cup�re tous les troncons sortant d'un noeud
                	   NodeList listeTronconsNoeudXML = noeudElement.getElementsByTagName("LeTronconSortant");
                	   Set<Troncon> listeTronconsNoeud = new HashSet<Troncon>();
                	   
                	   for (int j=0; j<listeTronconsNoeudXML.getLength();j++) 
                	   {
                		   Element tronconCourantElement = (Element) listeTronconsNoeudXML.item(j);
                		   Troncon troncon = new Troncon();

                		   troncon.construireTronconAPartirDeDOMXML(tronconCourantElement,i,listeNoeuds);
                		   
                		   //Ajout � la liste des tron�ons du noeud courant
                		   listeTronconsNoeud.add(troncon);
                		   //Ajout � la liste des tous les tron�ons
                		   listeTousLesTroncons.add(troncon);

                	   }               	   
                   }
                   zone= new Zone();
                   zone.setNoeuds(listeNoeuds);
                   zone.setTroncons(listeTousLesTroncons);
                   
               }	
               else {
            	   throw new SAXException();
               }
			}
			catch (ParserConfigurationException e) {
				System.out.println(e);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return zone;
	}
}
