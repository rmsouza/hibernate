/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ra21067003
 */

@Entity(name="TB_CONTA")
public class Conta {
    @Id
    @GeneratedValue
    private int id;
    private int numero;
    
    @OneToMany( mappedBy="conta" )
    private List<Movimentacao> lancamentos = new ArrayList<>();
    
    public void adicionaLancamento( Movimentacao m ) {
        lancamentos.add(m);
        m.setConta(this);
    }
    
    public double getSaldo() {
        double saldo = 0;
        
        for (Movimentacao m : lancamentos) {
            if (m.getTipo().equals("D"))
                saldo -= m.getValor();
            else
                saldo += m.getValor();
        }
        
        return saldo;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the lancamentos
     */
    public List<Movimentacao> getLancamentos() {
        return lancamentos;
    }

    /**
     * @param lancamentos the lancamentos to set
     */
    public void setLancamentos(List<Movimentacao> lancamentos) {
        this.lancamentos = lancamentos;
    }

}
