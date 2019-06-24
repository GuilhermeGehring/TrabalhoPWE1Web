/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Professor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author guilherme
 */
@Stateful
public class ProfessorDAO extends DAOGenerico<Professor> implements Serializable {

    public ProfessorDAO() {
        super(Professor.class);

        // inicializar as ordenações possiveis
        listaOrdem.add(new Ordem("nomeUsuario", "Nome de Usuário", "like"));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

}
