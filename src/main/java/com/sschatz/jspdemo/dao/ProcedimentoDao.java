/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.dao;
import com.sschatz.jspdemo.domain.Procedimento;
import com.sschatz.jspdemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 * CRUD database operations
 * @author Samuel Schatz
 *
 */
public class ProcedimentoDao {
    
    private static ProcedimentoDao instance;

    private ProcedimentoDao() {
        
    }
    
    
    public static ProcedimentoDao getInstance() {
         if (instance == null){
            instance = new ProcedimentoDao();
        }
        return instance;
    }
    
    
  

    /**
     * Save Procedimento
     * @param procedimento
     */
    public void saveProcedimento(Procedimento procedimento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(procedimento);
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
     * Update Procedimento
     * @param procedimento
     */
    public void updateProcedimento(Procedimento procedimento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(procedimento);
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
     * Delete Procedimento
     * @param id
     */
    public void deleteProcedimento(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a procedimento object
            Procedimento procedimento = session.get(Procedimento.class, id);
            if (procedimento != null) {
                session.delete(procedimento);
                System.out.println("procedimento is deleted");
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
     * Get Procedimento By ID
     * @param id
     * @return
     */
    public Procedimento getProcedimento(Long id) {

        Transaction transaction = null;
        Procedimento procedimento = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an procedimento object
            procedimento = session.get(Procedimento.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return procedimento;
    }

    /**
     * Get all Procedimentos
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < Procedimento > getAllProcedimento() {

        Transaction transaction = null;
        List < Procedimento > listOfProcedimento = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an procedimento object

            listOfProcedimento = session.createQuery("from Procedimento").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfProcedimento;
    }
}
