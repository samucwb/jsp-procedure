/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import com.google.gson.Gson;
import com.sschatz.jspdemo.dao.PacienteDao;
import com.sschatz.jspdemo.dao.RegrasProcedimentoDao;
import com.sschatz.jspdemo.domain.Paciente;
import com.sschatz.jspdemo.domain.RegrasProcedimento;
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
public class CheckRuleServelet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private RegrasProcedimentoDao regrasProcedimentoDao;

    public void init() {
        regrasProcedimentoDao = RegrasProcedimentoDao.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            listRegrasProcedimento(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegrasProcedimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    private void listRegrasProcedimento(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List < RegrasProcedimento > listRegrasProcedimento = regrasProcedimentoDao.getAllRegrasProcedimento();
        Long cdPaciente =  0L;
        Long cdProcedimento = 0L;
        ResultPayload resultPayload = new ResultPayload();
        
        if (request.getParameterMap().containsKey("cdPaciente") && request.getParameterMap().containsKey("cdProcedimento") )
        {
       
            cdPaciente = Long.parseLong(request.getParameter("cdPaciente"));
            cdProcedimento = Long.parseLong(request.getParameter("cdProcedimento"));

            PacienteDao pacienteDao = PacienteDao.getInstance();
            Paciente paciente = pacienteDao.getPaciente(cdPaciente);

            if ( paciente.getId() > 0 && listRegrasProcedimento.size() > 0 ) {


                boolean permite = false;
                for (RegrasProcedimento regrasProcedimento: listRegrasProcedimento) {
                    if (regrasProcedimento.getDsSexo().equals(paciente.getDsSexo())
                        && regrasProcedimento.getProcedimento().getId().equals(cdProcedimento)){
                        permite = regrasProcedimento.getIePermite().equals("S") ? true: false;
                    }
                }


                if (permite){
                 
                  resultPayload.setStatus(permite);
                  String json = new Gson().toJson(resultPayload);
                  response.getWriter().write(json);
                  return;
                } 
            }
        }
        
        resultPayload.setStatus(false);
        String json = new Gson().toJson(resultPayload);
        response.getWriter().write(json);
    }

   
}