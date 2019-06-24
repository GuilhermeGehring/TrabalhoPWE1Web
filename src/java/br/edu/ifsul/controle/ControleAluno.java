/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 20171pf.cc0178
 */
@Named(value = "controleAluno")
@ViewScoped
public class ControleAluno implements Serializable {

    @EJB
    private AlunoDAO dao;

    private Aluno objeto;

    private boolean isEdit = false;

    public ControleAluno() {
    }

    public String listar() {
        return "/privado/aluno/crud?faces-redirect=true";
    }

    public void novo() {
        objeto = new Aluno();
        isEdit = false;
    }

    public void alterar(Object id) {
        try {
            isEdit = true;
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            //id nesse caso não é gerado pelo BD
            if (!getIsEdit()) {
                dao.persist(getObjeto());
                Util.mensagemInformacao("Objeto persistido com sucesso!");
            } else {
                dao.merge(getObjeto());
                Util.mensagemInformacao("Objeto alterado com sucesso!");
            }
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public AlunoDAO getDao() {
        return dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }
}
