/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import com.sschatz.jspdemo.dao.PacienteDao;
import com.sschatz.jspdemo.dao.ProcedimentoDao;
import com.sschatz.jspdemo.dao.ProcedimentoPacienteDao;
import com.sschatz.jspdemo.domain.Paciente;
import com.sschatz.jspdemo.domain.Procedimento;
import com.sschatz.jspdemo.domain.ProcedimentoPaciente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sschatz.jspdemo.util.ServletTools;
import java.util.List;



/**
 *
 * @author sschatz
 */
public class ProcedimentoPacienteServletForm extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private ProcedimentoPacienteDao procedimentoPacienteDao;
    

    public void init() {
        procedimentoPacienteDao = procedimentoPacienteDao.getInstance();
    }
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            
            if( ServletTools.checkId(request) )
            {
               updateProcedimentoPaciente(request, response); 
            }
            else 
            {
               insertProcedimentoPaciente(request, response); 
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    
    private void insertProcedimentoPaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String dsObservacao = request.getParameter("dsObservacao");
        Long idProcedimento = Long.parseLong(request.getParameter("procedimento"));
        Long idPaciente = Long.parseLong(request.getParameter("paciente"));
        
        ProcedimentoDao procedimentoDao = ProcedimentoDao.getInstance();
        PacienteDao pacienteDao = PacienteDao.getInstance();
        
        Paciente paciente = pacienteDao.getPaciente(idPaciente);
        Procedimento procedimento = procedimentoDao.getProcedimento(idProcedimento);
        
        
        ProcedimentoPaciente newProcedimentoPaciente = new ProcedimentoPaciente();
        newProcedimentoPaciente.setDsObservacao(dsObservacao.trim());
        newProcedimentoPaciente.setPaciente(paciente);
        newProcedimentoPaciente.setProcedimento(procedimento);
        newProcedimentoPaciente.setId(procedimentoPacienteDao.getNextKey());
        
        System.out.println(newProcedimentoPaciente.toString());
        procedimentoPacienteDao.saveProcedimentoPaciente(newProcedimentoPaciente);
        
       
        response.sendRedirect("procedimento_paciente");
        
        
       
    }

    private void updateProcedimentoPaciente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String dsObservacao = request.getParameter("dsObservacao");
        
        Long idProcedimento = Long.parseLong(request.getParameter("procedimento"));
        Long idPaciente = Long.parseLong(request.getParameter("paciente"));
        
       
        PacienteDao pacienteDao = PacienteDao.getInstance();
        
        Paciente paciente = pacienteDao.getPaciente(idPaciente);
        Procedimento procedimento = getSelectedProcedimentoById(idProcedimento);
        

        ProcedimentoPaciente newProcedimentoPaciente = new ProcedimentoPaciente(id, dsObservacao, procedimento, paciente);
        procedimentoPacienteDao.updateProcedimentoPaciente(newProcedimentoPaciente);
        response.sendRedirect("procedimento_paciente");
       
    }
    
    

    private ProcedimentoPaciente getSelectedRecordById(Long id){
        if (id>0){
            procedimentoPacienteDao = procedimentoPacienteDao.getInstance();
            List < ProcedimentoPaciente > listProcedimentoPaciente = procedimentoPacienteDao.getAllProcedimentoPaciente();
            
            for (ProcedimentoPaciente item : listProcedimentoPaciente){
                if (item.getId().equals(id)) {
                    return item;
                }
            }
            
        } 
        return new ProcedimentoPaciente();
        
    }
    
    private Procedimento getSelectedProcedimentoById(Long id){
        if (id>0){
            ProcedimentoDao procedimentoPacienteDao = ProcedimentoDao.getInstance();
            List < Procedimento > lista = procedimentoPacienteDao.getAllProcedimento();
            
            for (Procedimento item : lista){
                if (item.getId().equals(id)) {
                    return item;
                }
            }
            
        } 
        return new Procedimento();
        
    }
}