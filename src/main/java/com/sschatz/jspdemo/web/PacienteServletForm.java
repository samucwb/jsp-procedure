/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import com.sschatz.jspdemo.dao.PacienteDao;
import com.sschatz.jspdemo.domain.Paciente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sschatz.jspdemo.util.ServletTools;



/**
 *
 * @author sschatz
 */
//@WebServlet("/pacienteform")
public class PacienteServletForm extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private PacienteDao pacienteDao;
    

    public void init() {
        pacienteDao = pacienteDao.getInstance();
    }
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            if( ServletTools.checkId(request) )
            {
                System.out.println("updatePaciente");
               updatePaciente(request, response); 
            }
            else 
            {
                 System.out.println("insertPaciente");
               insertPaciente(request, response); 
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        
    }


    
    private void insertPaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String dsNomePaciente = request.getParameter("dsNomePaciente");
        Integer nrCodigo = Integer.parseInt(request.getParameter("nrCodigo"));
        Integer nrIdade = Integer.parseInt(request.getParameter("nrIdade"));
        String dsSexo = request.getParameter("dsSexo");
        Paciente newPaciente = new Paciente(nrCodigo, dsNomePaciente, nrIdade, dsSexo);
        pacienteDao.savePaciente(newPaciente);
        response.sendRedirect("paciente");
    }

    private void updatePaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String dsNomePaciente = request.getParameter("dsNomePaciente");
        Integer nrCodigo = Integer.parseInt(request.getParameter("nrCodigo"));
        Integer nrIdade = Integer.parseInt(request.getParameter("nrIdade"));
        String dsSexo = request.getParameter("dsSexo");

        Paciente paciente = new Paciente(id, nrCodigo, dsNomePaciente, nrIdade, dsSexo);
        pacienteDao.updatePaciente(paciente);
        response.sendRedirect("paciente");
    }

    
}