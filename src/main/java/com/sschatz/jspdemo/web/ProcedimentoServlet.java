/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import com.sschatz.jspdemo.dao.ProcedimentoDao;
import com.sschatz.jspdemo.domain.Procedimento;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author sschatz
 */
public class ProcedimentoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private ProcedimentoDao pacienteDao;

    public void init() {
        pacienteDao = pacienteDao.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            listProcedimento(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProcedimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listProcedimento(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Procedimento > listProcedimento = pacienteDao.getAllProcedimento();
        request.setAttribute("listProcedimento", listProcedimento);
        RequestDispatcher dispatcher = request.getRequestDispatcher("procedimento.jsp");
        dispatcher.forward(request, response);
    }
}