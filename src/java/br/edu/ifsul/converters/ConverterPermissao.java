package br.edu.ifsul.converters;

import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.modelo.Permissao;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterPermissao")
@RequestScoped
public class ConverterPermissao implements Serializable, Converter {

    @EJB
    private PermissaoDAO dao;

    public ConverterPermissao() {

    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (string == null || string.equals("Selecione") || string.equals("Selecione um registro")) {
            return null;
        }

        Permissao o = null;
        try {
            o = dao.getObjectById(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return o;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Permissao obj = (Permissao) o;
        return obj.getNome();
    }
}
