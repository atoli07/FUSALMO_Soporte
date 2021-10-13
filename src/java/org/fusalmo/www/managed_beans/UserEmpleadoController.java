/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.model.UserEmpleadoModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

/**
 *
 * @author Brymolina
 */
@ManagedBean(name="userEmpleado")
@RequestScoped
public class UserEmpleadoController {
    
    private String Id;
    private String correo;
    private String Password;
    
    public String validaLogin(){
        
        try{
        UserEmpleadoModel USER=new UserEmpleadoModel();
        EmpleadoEntity u = USER.validarUsuario(correo, Password);
        
        if(u!=null){
            Id= u.getId();
            return "bienvenidoEmpleado";
        }
        else{
            return "indexEmpleado";         
        }
        }catch(NoResultException e) { 
     return "indexEmpleado"; 
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