/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import javax.faces.application.FacesMessage;
import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.model.UserModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.model.UserEmpleadoModel;
import org.fusalmo.www.utils.MailUtil;

/**
 *
 * @author Brymolina
 */
@ManagedBean(name = "userIT")
@RequestScoped
public class UserItController {

    private String Id;
    private String login;
    private String password;
    private String res;

    public String validaLogin() throws Exception {

        UserModel USER = new UserModel();
        setRes(" ");
        try {
            UsuariosITEntity u = USER.validarUsuario(login, password);
            Id = u.getId();
            return "principal";

        } catch (NoResultException e) {
            try {
                EmpleadoEntity emp = USER.validarEmpleado(login, password);
                Id = emp.getId();
                return "principalEmpleado";
            } catch (NoResultException e2) {
                setRes("LAS CREDENCIALES NO CONCUERDAN CON NINGUN USUARIO, Intente nuevamente!!");
                return "index";
            }

        }
    }

    public String recuperarContra() throws Exception {
        UserModel USER = new UserModel();

        try {
            UsuariosITEntity u = USER.recuperarUsuario(login);
            System.out.println("CORREO: "+login);
            password = u.getContra();
            String mensaje = "Buen día " + u.getNombres() + " " + u.getApellidos() + ", tu contraseña es: " + password;
            MailUtil.sendMail(u.getCorreo(), mensaje);
            return "reestablecerOk";

        } catch (NoResultException e) {
            try {
                EmpleadoEntity emp = USER.recuperarEmpleado(login);
                password = emp.getContra();
                String mensaje = "Buen día " + emp.getNombres() + " " + emp.getApellidos() + ", tu contraseña es: " + password;
                MailUtil.sendMail(emp.getCorreo(), mensaje);
                return "reestablecerOk";
            } catch (NoResultException e2) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El correo no existe, por favor ingrese un correo válido"));
                //setRes("El CORREO NO EXISTE PORVAFOR INGRESE UN CORREO VALIDO!!");
                return "recuperar";
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

    public String getres() {
        return getRes();
    }

    public void setres(String res) {
        this.setRes(res);
    }

    /**
     * @return the res
     */
    public String getRes() {
        return res;
    }

    /**
     * @param res the res to set
     */
    public void setRes(String res) {
        this.res = res;
    }

}
