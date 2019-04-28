/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle.testes.ejb;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 20171pf.cc0178
 */
@Named(value = "controleEspecialidade1")
@SessionScoped
public class ControleEspecialidade implements Serializable {
    
    @EJB
    private EspecialidadeDAO dao;

    private Especialidade objeto;

    public ControleEspecialidade() {
    }

    public String listar(){
        return "/privado/especialidade/crud?faces-redirect=true";
    }

    public void novo(){
        objeto = new Especialidade();
    }

    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " +                     
                    Util.getMensagemErro(e));
        } 
    }

    public void excluir(Object id){
        try {
                objeto = dao.getObjectById(id);
                dao.remover(objeto);
                Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
                Util.mensagemErro("Erro ao remover objeto: " + 
                                Util.getMensagemErro(e));
        }
    }

    public void salvar(){
        try {
                if (objeto.getId() == null){
                        dao.persist(objeto);
                } else {
                        dao.merge(objeto);
                }
                Util.mensagemInformacao("Objeto persistido com sucesso!");            
        } catch(Exception e){
                Util.mensagemErro("Erro ao persistir objeto: " + 
                                Util.getMensagemErro(e));
        }
    }

    public EspecialidadeDAO getDao() {
        return dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }
}
