/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Nemy
 */

@FacesValidator("validate.DATE")
public class Validate_DATE implements Validator{
    
    private static final String CUSTOM_PATTERN = "^(?:0?[1-9]|1[1-2])([\\-/.])(3[01]|[12][0-9]|0?[1-9])\\1\\d{4}$";
    private Pattern pattern;
    private Matcher matcher;
    
    public Validate_DATE(){
        
        pattern = Pattern.compile(CUSTOM_PATTERN);
        
    }
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException{
        
        matcher = pattern.matcher(o.toString());
        
        if(!matcher.matches()){
            
            FacesMessage msg = new FacesMessage("Ingrese una fecha válida.", "Formato incorrecto.");
            
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(msg);
            
        }
        
    }
    
}
