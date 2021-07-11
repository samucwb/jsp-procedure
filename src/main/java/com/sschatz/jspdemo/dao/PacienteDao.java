/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.dao;
import com.sschatz.jspdemo.domain.Paciente;
import com.sschatz.jspdemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 * CRUD database operations
 * @author Samuel Schatz
 *
 */
public class PacienteDao {
    
    private static PacienteDao instance;

    private PacienteDao() {
        
    }
    
    
    public static PacienteDao getInstance() {
         if (instance == null){
            instance = new PacienteDao();
        }
         
        
        return instance;
    }
    
    
  

    /**
     * Save Paciente
     * @param paciente
     */
    public void savePaciente(Paciente paciente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(paciente);
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
     * Update Paciente
     * @param paciente
     */
    public void updatePaciente(Paciente paciente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(paciente);
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
     * Delete Paciente
     * @param id
     */
    public void deletePaciente(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a paciente object
            Paciente paciente = session.get(Paciente.class, id);
            if (paciente != null) {
                session.delete(paciente);
                System.out.println("paciente is deleted");
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
     * Get Paciente By ID
     * @param id
     * @return
     */
    public Paciente getPaciente(Long id) {

        Transaction transaction = null;
        Paciente paciente = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an paciente object
            paciente = session.get(Paciente.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return paciente;
    }

    /**
     * Get all Pacientes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < Paciente > getAllPaciente() {

        Transaction transaction = null;
        List < Paciente > listOfPaciente = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an paciente object

            listOfPaciente = session.createQuery("from Paciente").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPaciente;
    }
}
