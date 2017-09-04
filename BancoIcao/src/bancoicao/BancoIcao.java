/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoicao;

import model.Conta;
import model.Cliente;
import model.Movimentacao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ra21067003
 */
public class BancoIcao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
                
        Conta conta = new Conta();
        conta.setNumero(123);
        conta.adicionaLancamento( new Movimentacao("C", "Crédito inicial", 1000) );
        conta.adicionaLancamento( new Movimentacao("D", "Débito", 300) );
        
        Cliente cliente = new Cliente();
        cliente.setCpf( 12345678901L );
        cliente.setNome("Marcelo");
        cliente.setConta(conta);
        
        
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            for(Movimentacao l: conta.getLancamentos()) {
                session.save(l);
            }
            session.save(cliente);
            session.save(conta);
            
            // System.out.print(conta.getSaldo());
            
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
