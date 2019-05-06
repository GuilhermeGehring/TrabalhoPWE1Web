/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

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
public class AlunoDAO extends DAOGenerico<Aluno> implements Serializable{

    public AlunoDAO() {
        super(Aluno.class);
    }
        
    @Override
    public Aluno getObjectById(Object id) throws Exception {
        List<Aluno> alunos = getListaObjetos();
        System.out.println("ID: " + id);
        for(Aluno aluno : alunos)
            if(Objects.equals(aluno.getId(), id))
                return aluno;
        return alunos.get(0);
        
    }   
    
}
