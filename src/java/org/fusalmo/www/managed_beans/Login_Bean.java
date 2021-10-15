/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.model.Login_model;

/**
 *
 * @author Nemy
 */
@ManagedBean
@SessionScoped
public class Login_Bean {

    Login_model modelo = new Login_model();
    private String mail;
    private String password;
    private String id;
    private String name;
    private String ape;
    
    /**
     * Creates a new instance of login_temporal
     */
    public Login_Bean() {
        
    }
    
    public String verifiedData(){
        System.out.println(mail + password);
        List<EmpleadoEntity> empleado = modelo.buscarEmpleado(mail, password);
        List<UsuariosITEntity> admin = modelo.buscarAdmin(mail, password);
        
        if(empleado.isEmpty() != true && empleado.size() != 0){
            
            for (EmpleadoEntity empleadoEntity : empleado) {

                setId(empleadoEntity.getId());
                setName(empleadoEntity.getNombres());
                setApe(empleadoEntity.getApellidos());

            }
            
            return "redirectEmpleado?faces-redirect=trueid-emp=" + id + "&name=" + name + "&ape=" + ape;
            
        }
        if(admin.isEmpty() != true && admin.size() != 0){
            
            for (UsuariosITEntity usuariosITEntity : admin) {
                
                setId(usuariosITEntity.getId());
                setName(usuariosITEntity.getNombres());
                setApe(usuariosITEntity.getApellidos());
                
            }
            
            return "redirectAdmin?faces-redirect=trueid-emp=" + id + "&name=" + name + "&ape=" + ape;
            
        }else{
            
            return "index?faces-redirect=true";
            
        }
        
    }
    
    public void prueba(){
        
        System.out.println(getMail());
        System.out.println(getPassword());
        
    }
    
    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the ape
     */
    public String getApe() {
        return ape;
    }

    /**
     * @param ape the ape to set
     */
    public void setApe(String ape) {
        this.ape = ape;
    }
    
}
