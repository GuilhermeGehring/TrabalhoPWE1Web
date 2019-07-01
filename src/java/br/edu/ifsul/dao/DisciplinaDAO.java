package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class DisciplinaDAO extends DAOGenerico<Disciplina> implements Serializable {

    public DisciplinaDAO() {
        super(Disciplina.class);

        // inicializar as ordenações possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("curso", "Curso", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

    @Override
    public Disciplina getObjectById(Object id) throws Exception {
        Disciplina obj = em.find(Disciplina.class, id);
        // Deve-se inicializar as coleções para não gerar erro de LazyInicializationException na lista de permissao
        System.out.println("Aluno daqui: ");
        for (Aluno a : obj.getAlunos()) {
            System.out.println("Aluno daqui: " + a.getNomeUsuario());
        }
        obj.getAlunos().size();
        return obj;
    }

}
