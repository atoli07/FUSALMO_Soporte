/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nemy
 */
@ManagedBean
@RequestScoped
public class MessageUtil {

    /**
     * Creates a new instance of MessageUtil
     */
    public MessageUtil() {
    }
    
    //Mensaje de confirmación en la vista
    
    public void confirm() {
        addMessage("Confirmación", "Recurso eliminado");
    }

    public void error() {
        addMessage("Error", "El recurso está siendo ocupado");
    }
    
    public void confirmAddMemo(){
        
        addMessage("Confimación", "¡El memo se ha creado con éxito!");
        
    }

    public void confirmDeleteToken(){
        addMessage("Confirmación", "El token ha sido eliminado");
    }
    
    public void errorDeleteToken(){
        addMessage("Error", "El token está siendo ocupado");
    }
    
    public void confirmDeleteMemo(){
        addMessage("Confirmación", "El memo ha sido eliminado");
    }
    
    public void errorDeleteMemo(){
        addMessage("Error", "El memo está siendo ocupado");
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessageError(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void delete() {
        addMessage("Confirmed", "Record deleted");
    }
    
}
