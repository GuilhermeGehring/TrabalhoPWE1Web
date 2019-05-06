/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author guilherme
 */
@Named(value = "controleProfessor")
@ViewScoped
public class ControleProfessor implements Serializable {
    
    @EJB
    private ProfessorDAO dao;

    private Professor objeto;

    public ControleProfessor() {
    }

    public String listar(){
        return "/privado/professor/crud?faces-redirect=true";
    }

    public void novo(){
        objeto = new Professor();
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

    public ProfessorDAO getDao() {
        return dao;
    }

    public Professor getObjeto() {
        return objeto;
    }
}
