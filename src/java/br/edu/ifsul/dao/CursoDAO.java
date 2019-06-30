/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author guilherme
 */
@Stateful
public class CursoDAO extends DAOGenerico<Curso> implements Serializable {

    public CursoDAO() {
        super(Curso.class);
        // inicializar as ordenações possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

    @Override
    public Curso getObjectById(Object id) throws Exception {
        Curso obj = em.find(Curso.class, id);
        // Deve-se inicializar as coleções para não gerar erro de LazyInicializationException na lista de permissao
        obj.getDisciplinas().size();
        return obj;
    }

    @Override
    public List<Curso> getListaTodos() {
        String jpql = "from Curso order by " + ordemAtual.getAtributo();
        List<Curso> cursos = em.createQuery(jpql).getResultList();
        for (Curso c : cursos) {
            c.getDisciplinas().size();
        }
        return cursos;
    }
}
