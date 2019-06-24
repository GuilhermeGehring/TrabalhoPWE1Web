package br.edu.ifsul.converters;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterCurso")
@RequestScoped
public class ConverterCurso implements Serializable, Converter {

    @EJB
    private CursoDAO dao;

    public ConverterCurso() {

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
        Curso obj = (Curso) o;
        return obj.getId().toString();
    }
}
