/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author guilherme
 */
@Named(value = "controleCurso")
@SessionScoped
public class ControleCurso implements Serializable {

    @EJB
    private CursoDAO dao;

    @EJB
    private DisciplinaDAO daoDisciplina;

    private Curso objeto;

    public ControleCurso() {
    }

    public void imprimirCursos() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("Cursos", parametros, dao.getListaTodos());
    }

    public String listar() {
        return "/privado/curso/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Curso();
        objeto.setDisciplinas(new ArrayList());
    }

    public void alterar(Object id) {
        try {
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
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public CursoDAO getDao() {
        return dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    private Disciplina disciplina;
    private Boolean novaDisciplina;

    public void novaDisciplina() {
        disciplina = new Disciplina();
        novaDisciplina = true;
    }

    public void alterarDisciplina(int index) {
        disciplina = objeto.getDisciplinas().get(index);
        novaDisciplina = false;
    }

    public void salvarDisciplina() {
        if (novaDisciplina) {
            objeto.adicionarDisciplina(disciplina);
        }
        Util.mensagemInformacao("Serviço adicionado com sucesso");
    }

    public void removerDisciplina(int index) {
        objeto.removerDisciplina(index);
        Util.mensagemInformacao("Disciplina removida com sucesso");
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Boolean getNovaDisciplina() {
        return novaDisciplina;
    }

    public void setNovaDisciplina(Boolean novaDisciplina) {
        this.novaDisciplina = novaDisciplina;
    }

    public DisciplinaDAO getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }

}
