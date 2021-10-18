/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.model.UserModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.model.UserEmpleadoModel;

/**
 *
 * @author Brymolina
 */
@ManagedBean(name="userIT")
@RequestScoped
public class UserItController {
   private String Id;
   private String login;
   private String password;
   
   public String validaLogin()throws Exception {
       UserModel USER= new UserModel();
       
    try{
    UsuariosITEntity u = USER.validarUsuario(login, password);
        Id=u.getId();
        return "principal";
        
    }catch(NoResultException e) { 
        try{
            EmpleadoEntity emp = USER.validarEmpleado(login, password);
            Id=emp.getId();
            return "principalEmpleado";
        }catch(NoResultException e2) {
            return "index";
        }
        
    } 
}

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
