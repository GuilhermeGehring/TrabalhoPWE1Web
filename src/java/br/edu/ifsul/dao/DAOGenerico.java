/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 20171pf.cc0178
 */
public class DAOGenerico<TipoGenerics> implements Serializable {

    private List<TipoGenerics> listaObjetos;
    private List<TipoGenerics> listaTodos;
    @PersistenceContext(unitName = "TrabalhoPWE1WebPU")
    protected EntityManager em;
   
    private final Class<TipoGenerics> classePersistente;
            
    public DAOGenerico(Class<TipoGenerics> clazz) {
       this.classePersistente = clazz;
    }

    public List<TipoGenerics> getListaObjetos() {
       
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }


    public List<TipoGenerics> getListaTodos() {
            String jpql = "from " + classePersistente.getSimpleName();
            
            return em.createQuery(jpql).getResultList();
    }

    public void persist(TipoGenerics obj) throws Exception {
            em.persist(obj);
    }

    public void merge(TipoGenerics obj) throws Exception {
            em.merge(obj);
    }

    public TipoGenerics getObjectById(Object id) throws Exception {
            return (TipoGenerics) em.find(classePersistente, id);
    }

    public void remover(TipoGenerics obj) throws Exception {
            obj = em.merge(obj);
            em.remove(obj);
    }

}