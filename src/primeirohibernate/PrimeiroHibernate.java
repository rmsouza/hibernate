/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeirohibernate;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ra21067003
 */
public class PrimeiroHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        Ra ra = new Ra( 232420 );
        ra.setAtivo(true);
        
        Materia m = new Materia();
        m.setDescricao("PSW 2");
        
        Aluno a = new Aluno();
        a.setNome("Margarete");
        a.setAtivo(true);
        a.setDataNascimento(new Date(86,1,31));
        a.setRa(ra);
        a.setMateria(m);
               
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            session.save(ra);
            session.save(a);
            session.save(m);
            
            session.getTransaction().commit();
            
        }catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }                
    }
    
}
