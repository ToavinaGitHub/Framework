package com.example.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import connect.ConnectionDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import objet.Etudiant;
import objet.Etudiant_Dao;


public class HelloServlet extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = request.getPathInfo().substring(1);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = ConnectionDB.makaConPsql();
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant e = Etudiant_Dao.selectById(id,con);
        if (name != null) {
            switch (name) {
                case "create":

                    break;
                case "update":

                    break;
                case "delete":
                    e.Delete(con);
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