/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.model.UserItModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

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
    try{
        
    
    UserItModel USER= new UserItModel();
    UsuariosITEntity u = USER.validarUsuario(login, password);
    if(u!=null){
        Id=u.getId();
        return "principal";
    }else{
        return "index";
    }
    }catch(NoResultException e) { 
     return "index"; 
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
