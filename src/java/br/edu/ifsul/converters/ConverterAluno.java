package br.edu.ifsul.converters;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterAluno")
@RequestScoped
public class ConverterAluno implements Serializable, Converter {

    @EJB
    private AlunoDAO dao;

    public ConverterAluno() {

    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        if (string == null || string.equals("Selecione") || string.equals("Selecione um registro")) {
            return null;
        }

        Aluno o = null;
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
        Aluno obj = (Aluno) o;
        return obj.getNomeUsuario();
    }
}
