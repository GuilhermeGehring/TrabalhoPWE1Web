package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.jboss.weld.util.collections.ArraySet;

@Named(value = "controleDisciplina")
@SessionScoped
public class ControleDisciplina implements Serializable {

    @EJB
    private DisciplinaDAO dao;
    private Disciplina objeto;
    private Boolean novoObjeto;

    @EJB
    private AlunoDAO daoAluno;
    private Aluno aluno;

    public ControleDisciplina() {
    }

    public void adicionarAluno() {
        System.out.println("Aluno: " + aluno);
        if (aluno != null) {
            if (!objeto.getAlunos().contains(aluno)) {
                objeto.getAlunos().add(aluno);
                Util.mensagemInformacao("Aluno adicionado com sucesso!");
            } else {
                Util.mensagemErro("Este aluno já existe na sua lista!");
            }
        }
    }

    public void salvarAluno() {
        if (!objeto.getAlunos().contains(aluno)) {
            objeto.getAlunos().add(aluno);
            Util.mensagemInformacao("Aluno adicionado com sucesso!");
        } else {
            Util.mensagemErro("Este aluno já foi adicionado a lista!");
        }

    }

    public void excluirAluno(Aluno obj) {
        objeto.getAlunos().remove(obj);
        Util.mensagemInformacao("Aluno removido com sucesso!");
    }

    public void removerAluno(Aluno al) {
        objeto.getAlunos().remove(al);
        Util.mensagemInformacao("Aluno removido com sucesso!");
    }

    public String listar() {
        return "/privado/disciplina/listar?faces-redirect=true";
    }

    public void novo() {
        novoObjeto = true;
        objeto = new Disciplina();
        objeto.setAlunos(new ArraySet<>());
    }

    public void alterar(Object id) {
        try {
            novoObjeto = false;
            objeto = dao.getObjectById(id);
            System.out.println("Aluno do curso: ");
            for (Aluno a : objeto.getAlunos()) {
                System.out.println("Aluno do curso: " + a.getNomeUsuario());
            }
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
            if (novoObjeto) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            System.out.println("Disciplina erro: " + objeto.getId());
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public DisciplinaDAO getDao() {
        return dao;
    }

    public Disciplina getObjeto() {
        return objeto;
    }

    public Boolean getNovoObjeto() {
        return novoObjeto;
    }

    public void setNovoObjeto(Boolean novoObjeto) {
        this.novoObjeto = novoObjeto;
    }

    public AlunoDAO getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO daoAluno) {
        this.daoAluno = daoAluno;
    }

}
