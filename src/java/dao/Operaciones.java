/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.Direccion;
import pojo.Persona;

/**
 *
 * @author Jose
 */
public class Operaciones {

    public void insertarPersona(Persona p1, SessionFactory MiSesion) {

        Session session = MiSesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(p1);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            //session.close(); 
        }
    }
    public void insertarEmails(Direccion emails, SessionFactory MiSesion) {

        Session session = MiSesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(emails);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            //session.close(); 
        }
    }

    
    public List<Persona> listarPersonas(SessionFactory SessionBuilder) {
        Session session = SessionBuilder.openSession();
        List listaPersona = session.createQuery("from Persona").list();

        return listaPersona;
    }

    public boolean borrarPersona(Persona votante, SessionFactory MiSesion) throws Exception, IndexOutOfBoundsException {

        boolean resul = false;

        Session session = MiSesion.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.delete(votante);

            tx.commit();
            resul = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            //session.close();
        }
        return resul;
    }
    
    public Persona buscarPersonaNif(String nif, SessionFactory MiSesion) {
        Session session = MiSesion.openSession();

        String hql = "FROM Persona where nif=:minif";
        Query query = session.createQuery(hql);
        query.setParameter("minif", nif);
        query.setMaxResults(1);
        Persona result = (Persona) query.uniqueResult();
        return result;
    } 
    
    
 
}
