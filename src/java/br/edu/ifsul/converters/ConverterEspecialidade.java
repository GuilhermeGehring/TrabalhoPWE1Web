package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "converterEspecialidade") 
@RequestScoped
public class ConverterEspecialidade implements Serializable, Converter {
    
    @PersistenceContext(unitName = "TrabalhoPWE1WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione uma Especialidade")){
            return null;
        }
        return em.find(Especialidade.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Especialidade obj = (Especialidade) o;
        return obj.getId().toString();
    }

}
