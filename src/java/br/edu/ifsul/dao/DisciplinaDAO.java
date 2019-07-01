package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
        listaOrdem.add(new Ordem("curso.nome", "Curso", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

    @Override
    public Disciplina getObjectById(Object id) throws Exception {
        Disciplina obj = em.find(Disciplina.class, id);
        // Deve-se inicializar as coleções para não gerar erro de LazyInicializationException na lista de permissao
        obj.getAlunos().size();
        return obj;
    }

}
