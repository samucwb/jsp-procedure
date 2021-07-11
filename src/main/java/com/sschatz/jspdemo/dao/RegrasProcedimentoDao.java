/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.dao;
import com.sschatz.jspdemo.domain.RegrasProcedimento;
import com.sschatz.jspdemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 * CRUD database operations
 * @author Samuel Schatz
 *
 */
public class RegrasProcedimentoDao {
    
    private static RegrasProcedimentoDao instance;

    private RegrasProcedimentoDao() {
        
    }
    
    
    public static RegrasProcedimentoDao getInstance() {
         if (instance == null){
            instance = new RegrasProcedimentoDao();
        }
        return instance;
    }
    
    
  

    /**
     * Save RegrasProcedimento
     * @param regrasProcedimento
     */
    public void saveRegrasProcedimento(RegrasProcedimento regrasProcedimento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(regrasProcedimento);
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
     * Update RegrasProcedimento
     * @param regrasProcedimento
     */
    public void updateRegrasProcedimento(RegrasProcedimento regrasProcedimento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(regrasProcedimento);
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
     * Delete RegrasProcedimento
     * @param id
     */
    public void deleteRegrasProcedimento(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a regrasProcedimento object
            RegrasProcedimento regrasProcedimento = session.get(RegrasProcedimento.class, id);
            if (regrasProcedimento != null) {
                session.delete(regrasProcedimento);
                System.out.println("regrasProcedimento is deleted");
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
     * Get RegrasProcedimento By ID
     * @param id
     * @return
     */
    public RegrasProcedimento getRegrasProcedimento(Long id) {

        Transaction transaction = null;
        RegrasProcedimento regrasProcedimento = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an regrasProcedimento object
            regrasProcedimento = session.get(RegrasProcedimento.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return regrasProcedimento;
    }

    /**
     * Get all RegrasProcedimentos
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < RegrasProcedimento > getAllRegrasProcedimento() {

        Transaction transaction = null;
        List < RegrasProcedimento > listOfRegrasProcedimento = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an regrasProcedimento object

            listOfRegrasProcedimento = session.createQuery("from RegrasProcedimento").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfRegrasProcedimento;
    }
}
