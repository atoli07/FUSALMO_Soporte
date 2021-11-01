/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.EstadoTokenEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TokensEntity;
import org.fusalmo.www.model.EstadoTokenModel;
import org.fusalmo.www.model.TokensModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;
import org.fusalmo.www.utils.MessageUtil;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Soporte
 */
@ManagedBean
@RequestScoped
public class TokensBean {

    TokensModel modelo= new TokensModel();
    private String idRecurso;
    private String idToken;
    private Integer idEstadoToken;
    private EmpleadoEntity empleadoModel;
    private String idEmpleado;
    private TokensEntity token;
    private TokensEntity selectedToken;
    private List<TokensEntity> listaTokens;
    private List<EstadoTokenEntity> listaEstado;

    /**
     * Creates a new instance of TokensBean
     */
    public TokensBean() {
        token= new TokensEntity();
    }
    
    public List<TokensEntity> getListaTokens(){
        return modelo.listarTokens();
    }
    
    public List<TokensEntity> getListaTokensByIdEmpleado(String idemp){
        listaTokens =modelo.listarTokensByIdEmpleado(idemp.substring(22,28));
        /*EmpleadoEntity  emp=modelo.obtenerEmpleado(idemp);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                System.out.println(emp.getId());
                System.out.println(emp.getNombres());
                System.out.println(emp.getApellidos());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++");*/
        return  listaTokens;
    }
    
    public List<EstadoTokenEntity> getListaEstadoTokens(){
        EstadoTokenModel estadoModel= new EstadoTokenModel();
        listaEstado = estadoModel.listarEstadoTokens();
        return listaEstado;
    }
    
    public String crearToken(){
        EntityManager em= JPAUtil.getEntityManager();
        token.setId(modelo.crearID());
        if (idEmpleado.length()>6) {
            System.out.println("ENTRA AL IF DE >6");
            token.setIdEmpleado(modelo.obtenerEmpleado(idEmpleado.substring(22,28)));
        }
        else{
            token.setIdEmpleado(modelo.obtenerEmpleado(idEmpleado));
        }
        System.out.println("-----------------------");
        System.out.println(getIdEmpleado());
        token.setIdEstado(modelo.obtenerEstadoToken(1));
        java.util.Date fecha = new Date();
        System.out.println(fecha);
        token.setFecha(fecha);
        token.setSeleccionRecurso(em.find(RecursosEntity.class, idRecurso));
        
        if (idEmpleado.length()>6) {
            if(modelo.crearToken(getToken()) != 1){
            System.out.println("Hubo un error inesperado al crear el token");
            return null;
            }else{
            return "listadoTokens?faces-redirect=true";
            } 
        } 
        else{
            if(modelo.crearToken(getToken()) != 1){
            System.out.println("Hubo un error inesperado al crear el token");
            return null;
            }else{
            return "administracionTokens?faces-redirect=true";
            } 
        }
    }
    
    public String borrarToken(){
        String id = JsfUtil.getRequest().getParameter("idToken");
        if (modelo.eliminarToken(id)!= 1) {
            return "administracionTokens?faces-redirect=true&result=0";
        }else{
            return "administracionTokens?faces-redirect=true&result=1";
        }
    }
    
    public void borrarDatos(){
        token.setId("");
        token.setDescripcion("");
        token.setFecha(null);
        token.setIdEmpleado(null);
        token.setIdEstado(null);
        token.setPrioridad("");
        token.setSeleccionRecurso(null);
    }
    
    public void mostrarDetalle(){
        String id = JsfUtil.getRequest().getParameter("idToken");
        System.out.println(id);
        this.selectedToken = new TokensEntity();
        selectedToken = modelo.obtenerToken(id);
        System.out.println(selectedToken.getIdEstado().getDescripcion());
        
    }
    
    public void cambiarEstadoToken(Integer idEst){
        String id = JsfUtil.getRequest().getParameter("idToken");
        System.out.println(idEst);
        EstadoTokenEntity newId=modelo.obtenerEstadoToken(idEst);
        System.out.println(newId.getNombre());
        this.selectedToken= modelo.obtenerToken(id);
        selectedToken.setIdEstado(newId);
        
        if (modelo.actualizarEstadoToken(getSelectedToken())!=1) {
            MessageUtil mensaje= new MessageUtil();
            mensaje.addMessage("Error", "El estado del token no puede ser actualizado");
        }
        else{
            MessageUtil mensaje= new MessageUtil();
            mensaje.addMessage("Confirmación", "El estado del token ha sido actualizado");
        }
        
    }
    
    
    /*
    public String crearToken(){
        
    }*/

    public TokensEntity getSelectedToken() {
        return selectedToken;
    }

    public void setSelectedToken(TokensEntity selectedToken) {
        this.selectedToken = selectedToken;
    }
    
    /**
     * @return the idToken
     */
    public String getIdToken() {
        return idToken;
    }

    /**
     * @param idToken the idToken to set
     */
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public TokensEntity getToken() {
        return token;
    }

    public void setToken(TokensEntity token) {
        this.token = token;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEstadoToken() {
        return idEstadoToken;
    }

    public void setIdEstadoToken(Integer idEstadoToken) {
        this.idEstadoToken = idEstadoToken;
    }
    
    
}
