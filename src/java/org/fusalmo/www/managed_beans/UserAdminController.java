/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.model.UserAdminModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

/**
 *
 * @author Brymolina
 */
@ManagedBean(name="userAdmin")
@RequestScoped
public class UserAdminController {
    
    private String Id;
    private String correo;
    private String Password;
    
    public String validaLogin(){
        
        try{
        UserAdminModel USER=new UserAdminModel();
        EmpleadoEntity u = USER.validarUsuario(correo, Password);
        
        if(u!=null){
            Id= u.getId();
            return "principalAdmin";
        }
        else{
            return "index";         
        }
        }catch(NoResultException e) { 
     return "indexAdmin"; 
    } 
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
}
