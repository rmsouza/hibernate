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
        
        Criteria criteria = session.createCriteria(Aluno.class);
        criteria.add(Restrictions.eq("nome", "Joana"));
        criteria.setProjection(Projections.rowCount());
        long countAluno = (Long) criteria.uniqueResult();
        
        Aluno a = new Aluno();
        a.setNome("Joana");
        a.setRa(21067005);
        a.setAtivo(true);
        a.setDataNascimento(new Date(86,1,31));
        
        Materia m = new Materia();
        m.setDescricao("PSW");
        
        Ra r = new Ra();
        r.setRa(21067003);
        r.setAtivo(true);
        
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            if (countAluno == 0) {
                session.save(a);
            } else {
                System.out.println("Aluno já existe!");
            }
            
            session.save(m);
            session.save(r);
            
            session.getTransaction().commit();
            
            List<Aluno> alunos = session.createCriteria(Aluno.class).list();
            for(Aluno aluno : alunos) {
                System.out.println(aluno.getNome());
            }
            
        }catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }                
    }
    
}
