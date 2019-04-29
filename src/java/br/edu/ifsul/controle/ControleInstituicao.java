/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 20171pf.cc0178
 */
@Named(value = "controleInstituicao")
@ViewScoped
public class ControleInstituicao implements Serializable {
    
    @EJB
    private InstituicaoDAO dao;

    private Instituicao objeto;

    public ControleInstituicao() {
    }

    public String listar(){
        return "/privado/instituicao/crud?faces-redirect=true";
    }

    public void novo(){
        objeto = new Instituicao();
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

    public InstituicaoDAO getDao() {
        return dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }
}
