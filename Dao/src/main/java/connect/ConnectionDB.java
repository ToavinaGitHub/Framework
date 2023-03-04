package connect;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

public class ConnectionDB {
static String nomConnex;
	
	public static String getNomConnex() {
		return nomConnex;
	}
	public static void setNomConnex(String nomConnex) {
		ConnectionDB.nomConnex = nomConnex;
	}
	public ConnectionDB() throws Exception
	{
		
	}
	 public static Connection makaConPsql()
	 {
		 try
		    {
		      Class.forName("org.postgresql.Driver");
		      Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dao","postgres","mdpProm15");
		      ConnectionDB.setNomConnex("psql");
		      return conn;
		    }
		    catch(Exception e){ 
		      System.out.println("non-connect�");
		    }
		 return null;
	 }
	public static Connection getPostgresConnection() throws ParserConfigurationException, IOException, SAXException {
		Vector<Object> conf = ConnectionDB.getConfig("configDB.xml");
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:"+conf.get(0)+"/"+conf.get(1),conf.get(2).toString(),conf.get(3).toString());
			ConnectionDB.setNomConnex("psql");
			return conn;
		}
		catch(Exception e){
			System.out.println("non-connect�");
		}
		return null;
	}
	 public static Connection makaConOracle()
	 {
		 try
	      {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","Taxibe","Taxibe"); 
	        ConnectionDB.setNomConnex("oracle");
	        return con;
	      }
	     catch(Exception e){ 
	    	 e.printStackTrace();                                  
	    	 System.out.println("non-Connecte");
	    	}
		 return null;
	 }
	 public static Connection getConnection() throws ParserConfigurationException, IOException, SAXException {
		 Vector<Object> conf = ConnectionDB.getConfig("/home/toavina/IdeaProjects/Dao/configDB.xml");
		 try
		 {
			 Class.forName((String) conf.get(4));
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:"+conf.get(0)+"/"+conf.get(1),conf.get(2).toString(),conf.get(3).toString());
			 return conn;
		 }
		 catch(Exception e){
			 System.out.println("non-connect�");
		 }
		 return null;
	 }
	public static Vector<Object> getConfig(String file) throws ParserConfigurationException, IOException, SAXException {
		File inputFile = new File(file);

		Vector<Object> tabObj = new Vector<Object>();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		org.w3c.dom.Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();


		NodeList dbList = doc.getElementsByTagName("database");


		Element db = (Element) dbList.item(0);

		String user = db.getElementsByTagName("user").item(0).getTextContent();


		String dbName = db.getElementsByTagName("dbName").item(0).getTextContent();
		String password = db.getElementsByTagName("password").item(0).getTextContent();
		String className = db.getElementsByTagName("className").item(0).getTextContent();
		String path = db.getElementsByTagName("projectPath").item(0).getTextContent();

		int port = Integer.parseInt(db.getAttribute("port"));
		tabObj.add(port);
		tabObj.add(dbName);
		tabObj.add(user);
		tabObj.add(password);
		tabObj.add(className);
		tabObj.add(path);
		return tabObj;
	}
	 
}
