package com.example.dao;

import java.io.*;
import java.sql.Connection;

import connect.ConnectionDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import objet.Etudiant;
import objet.Etudiant_Dao;


public class FrontServlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = request.getPathInfo().substring(1);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = ConnectionDB.makaConPsql();

        if (name != null) {
            switch (name) {
                case "create":
                    Etudiant eT = new Etudiant(request.getParameter("nom"),request.getParameter("prenom"));
                    eT.Insert(con);
                    break;
                case "update":
                    break;
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Etudiant e = Etudiant_Dao.selectById(id,con);
                    Etudiant_Dao.delete(Etudiant.class,"idEtudiant",request.getParameter("id"),con);
                    break;
                default:

            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}