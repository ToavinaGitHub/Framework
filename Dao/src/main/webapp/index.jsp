<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="connect.ConnectionDB" %>
<%@page import="objet.*" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%
    Connection c = ConnectionDB.getConnection();
    Vector<Etudiant> allEtu = Etudiant_Dao.selectAll(c);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des étudiants</title>
</head>
<body>
<h1>Liste des étudiants</h1>

<%-- Afficher la liste des étudiants --%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for(int i=0;i<allEtu.size();i++) { %>
        <tr>
            <td><%= allEtu.get(i).getIdEtudiant()%></td>
            <td><%= allEtu.get(i).getNomEtudiant()%></td>
            <td><%= allEtu.get(i).getPrenomEtudiant()%></td>
            <td>
                <a href="hello/update?id=<%= allEtu.get(i).getIdEtudiant()%>">Editer</a> |
                <a href="hello/delete?id=<%= allEtu.get(i).getIdEtudiant()%>">Supprimer</a>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>

<%-- Formulaire d'ajout d'un nouvel étudiant --%>
<h2>Ajouter un nouvel étudiant</h2>
<form method="post" action="ajouterEtudiant">
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom"><br>
    <label for="prenom">Prénom:</label>
    <input type="text" id="prenom" name="prenom"><br>
    <input type="submit" value="Ajouter">
</form>

</body>
</html>
