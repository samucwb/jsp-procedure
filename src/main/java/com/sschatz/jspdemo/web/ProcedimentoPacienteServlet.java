/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import com.sschatz.jspdemo.commons.Constants;
import com.sschatz.jspdemo.dao.PacienteDao;
import com.sschatz.jspdemo.dao.ProcedimentoDao;
import com.sschatz.jspdemo.dao.ProcedimentoPacienteDao;
import com.sschatz.jspdemo.domain.Paciente;
import com.sschatz.jspdemo.domain.Procedimento;
import com.sschatz.jspdemo.domain.ProcedimentoPaciente;
import com.sschatz.jspdemo.util.ServletTools;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author sschatz
 */
public class ProcedimentoPacienteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private ProcedimentoPacienteDao procedimentoPacienteDao;

    public void init() {
        procedimentoPacienteDao = procedimentoPacienteDao.getInstance();
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
                    listProcedimentoPaciente(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProcedimentoPaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < ProcedimentoPaciente > listProcedimentoPaciente = procedimentoPacienteDao.getAllProcedimentoPaciente();
        request.setAttribute("listProcedimentoPaciente", listProcedimentoPaciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("procedimento_paciente.jsp");
        dispatcher.forward(request, response);
    }

    
     private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        feedListVariables(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("proc_paciente_form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        if (id>0){
            procedimentoPacienteDao = procedimentoPacienteDao.getInstance();
            //ServletTools.displayMessage(response, "Este paciente não pode executar este procedimento :" + id.toString());
            List < ProcedimentoPaciente > listProcedimentoPaciente = procedimentoPacienteDao.getAllProcedimentoPaciente();
            
            for (ProcedimentoPaciente item : listProcedimentoPaciente){
                if (item.getId().equals(id)) {

                    RequestDispatcher dispatcher = request.getRequestDispatcher("proc_paciente_form.jsp");
                    request.setAttribute("id", id);
                    request.setAttribute("entity", item);
                    feedListVariables(request);
                    dispatcher.forward(request, response);
                    return;
                }
            }
            ServletTools.displayMessage(response, "Registro invalido");
            
        } else {
            ServletTools.displayMessage(response, "Registro invalido");
        }
        

    }

    private void deletePaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        procedimentoPacienteDao.deleteProcedimentoPaciente(id);
        response.sendRedirect("procedimento_paciente");
    }
    
    private void feedListVariables(HttpServletRequest request) {
        ProcedimentoDao procedimentoDao = ProcedimentoDao.getInstance();
        List<Procedimento> procedureList = procedimentoDao.getAllProcedimento();
        
        PacienteDao pacienteDao = PacienteDao.getInstance();
        List<Paciente> pacienteList = pacienteDao.getAllPaciente();
        
        request.setAttribute("procedureList", procedureList);
        request.setAttribute("pacienteList", pacienteList);
    }

}