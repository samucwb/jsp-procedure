/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import com.sschatz.jspdemo.commons.Constants;
import com.sschatz.jspdemo.dao.PacienteDao;
import com.sschatz.jspdemo.domain.Paciente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.sschatz.jspdemo.util.ServletTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author sschatz
 */
//@WebServlet("/paciente")
public class PacienteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private PacienteDao pacienteDao;

    public void init() {
        pacienteDao = pacienteDao.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = ServletTools.getAction(request);
        try {
            switch (action) {
                case Constants.NEW:
                    System.out.println("showNEW");
                    showNewForm(request, response);
                    break;
                case Constants.DELETE:
                    System.out.println("showDELETE");
                    deletePaciente(request, response);
                    break;
                case Constants.EDIT:
                    System.out.println("showEditForm");
                    showEditForm(request, response);
                    break;
                default:
                    System.out.println("defaultList");
                    listPaciente(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Paciente > listPaciente = pacienteDao.getAllPaciente();
        request.setAttribute("listPaciente", listPaciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pacienteform.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Paciente existingPaciente = pacienteDao.getPaciente(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pacienteform.jsp");
        request.setAttribute("id", id);
        request.setAttribute("paciente", existingPaciente);
        dispatcher.forward(request, response);

    }

    private void deletePaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pacienteDao.deletePaciente(id);
        response.sendRedirect("paciente");
    }
}