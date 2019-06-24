/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateful;

/**
 *
 * @author 20171pf.cc0178
 */
@Stateful
public class AlunoDAO extends DAOGenerico<Aluno> implements Serializable {

    public AlunoDAO() {
        super(Aluno.class);

        // inicializar as ordenações possiveis
        listaOrdem.add(new Ordem("nomeUsuario", "Nome de Usuário", "like"));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

    @Override
    public Aluno getObjectById(Object id) throws Exception {
        List<Aluno> alunos = getListaObjetos();
        System.out.println("Nome do Usuário: " + id);
        for (Aluno aluno : alunos) {
            if (Objects.equals(aluno.getNomeUsuario(), id)) {
                return aluno;
            }
        }
        return alunos.get(0);

    }

}
