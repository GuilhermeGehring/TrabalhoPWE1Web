/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author guilherme
 */
@Named(value = "converterInstituicao")
@RequestScoped
public class ConverterInstituicao implements Serializable, Converter {

    @EJB
    private InstituicaoDAO dao;

    public ConverterInstituicao() {

    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (string == null || string.equals("Selecione") || string.equals("Selecione um registro")) {
            return null;
        }

        return dao.find(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Instituicao obj = (Instituicao) o;
        return obj.getId().toString();
    }
}
