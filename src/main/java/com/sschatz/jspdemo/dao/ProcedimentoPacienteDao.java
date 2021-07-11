/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.dao;
import com.sschatz.jspdemo.domain.ProcedimentoPaciente;
import com.sschatz.jspdemo.util.HibernateUtil;
import java.math.BigInteger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.query.Query;


/**
 * CRUD database operations
 * @author Samuel Schatz
 *
 */
public class ProcedimentoPacienteDao {
    
    private static ProcedimentoPacienteDao instance;

    private ProcedimentoPacienteDao() {
        
    }
    
    
    public static ProcedimentoPacienteDao getInstance() {
         if (instance == null){
            instance = new ProcedimentoPacienteDao();
        }
        return instance;
    }
    

    /**
     * Save ProcedimentoPaciente
     * @param procedimentoPaciente
     */
    public void saveProcedimentoPaciente(ProcedimentoPaciente procedimentoPaciente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(procedimentoPaciente);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Update ProcedimentoPaciente
     * @param procedimentoPaciente
     */
    public void updateProcedimentoPaciente(ProcedimentoPaciente procedimentoPaciente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(procedimentoPaciente);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete ProcedimentoPaciente
     * @param id
     */
    public void deleteProcedimentoPaciente(Long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a procedimentoProcedimentoPaciente object

            Query query = session.createQuery("select m from ProcedimentoPaciente m where id = " + id.toString()); 
            query.setMaxResults(1);
            ProcedimentoPaciente procedimentoPaciente = (ProcedimentoPaciente) query.getSingleResult();
            
            
            if (procedimentoPaciente != null) {
                session.delete(procedimentoPaciente);
                System.out.println("procedimentoPaciente is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get ProcedimentoPaciente By ID
     * @param id
     * @return
     */
    public ProcedimentoPaciente getProcedimentoPaciente(Long id) {

        Transaction transaction = null;
        ProcedimentoPaciente procedimentoPaciente = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an procedimentoPaciente object
            procedimentoPaciente = session.get(ProcedimentoPaciente.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return procedimentoPaciente;
    }

    /**
     * Get all ProcedimentoPacientes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < ProcedimentoPaciente > getAllProcedimentoPaciente() {

        Transaction transaction = null;
        List < ProcedimentoPaciente > listOfProcedimentoPaciente = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an procedimentoPaciente object
            listOfProcedimentoPaciente = session.createQuery("from ProcedimentoPaciente").getResultList();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfProcedimentoPaciente;
    }
    
    
    
   public Long getNextKey()
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           
            //Query query = session.createNativeQuery("select max(id) + 1 from procedimento_paciente " );
            Query query = session.createQuery("select m from ProcedimentoPaciente m order by m.id desc"); 
            query.setMaxResults(1);
            ProcedimentoPaciente procedimentoPaciente = (ProcedimentoPaciente) query.getSingleResult();
            if (procedimentoPaciente != null){
                return procedimentoPaciente.getId() +1;
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        } 
        
        return 1L;
    }
}
