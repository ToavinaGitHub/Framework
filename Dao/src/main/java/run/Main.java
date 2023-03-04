package run;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import connect.ConnectionDB;

import objet.DbManager;
import objet.Etudiant;
import objet.modele;
import org.xml.sax.SAXException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

import static objet.DbManager.getCarac;
import static objet.DbManager.getFields;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Connection c = ConnectionDB.getPostgresConnection();

        String[] tabCol = new String[1];
        tabCol[0]="prenomEtudiant";

        Vector<String> tabC = new Vector<String>();
        tabC.add("prenomEtudiant");

        Object[] tabObj = new Object[1];
        tabObj[0]="kioto";

        Vector<Object> tabO = new Vector<Object>();
        tabO.add("loto");

        Object[] tabI = new Object[3];
        tabI[0]=7;
        tabI[1]="Randria";
        tabI[2]="naivo";



        try {
            Etudiant et = new Etudiant(5,"Rakoto","koto");
          //  et.update("prenomEtudiant","kotoo",c);

           // DbManager.update(Etudiant.class,"idEtudiant",5,tabC,tabO,c);

           // DaoGen.insertByName(c,"Etudiant",tabI);

            //DaoGen.delete(Etudiant.class,"idEtudiant",6,c);

            Vector<Object> allObj = DbManager.selectWhere(Etudiant.class,null,1,c);
            for (int i = 0; i < allObj.size(); i++) {
                System.out.printf(((Etudiant)allObj.get(i)).getPrenomEtudiant());
            }

            Vector<Class> cls = DbManager.getClassByAnnotationByPackage("objet", modele.class);

            for (int i = 0; i < cls.size(); i++) {
                System.out.println(cls.get(i).getSimpleName());
            }


        }catch (Exception exe){
            exe.printStackTrace();
        }
        try {
            c.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



}
