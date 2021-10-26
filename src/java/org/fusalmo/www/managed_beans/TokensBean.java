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
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TokensEntity;
import org.fusalmo.www.model.TokensModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;
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
    private EmpleadoEntity empleadoModel;
    private String idEmpleado;
    private TokensEntity token;
    private TokensEntity selectedToken;
    private List<TokensEntity> listaTokens;

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
    
    public void borrarDatos(){
        token.setId("");
        token.setDescripcion("");
        token.setFecha(null);
        token.setIdEmpleado(null);
        token.setIdEstado(null);
        token.setPrioridad("");
        token.setSeleccionRecurso(null);
    }
    
    public void verBitacora(){
        Map<String, Object> options= new HashMap<>();
        options.put("resizable", false);
        PrimeFaces.current().dialog().openDynamic("detalleBitacoraToken",options,null);
    }
    
    public void mostrarDetalle(String id){
        
        System.out.println(id);
        this.selectedToken = new TokensEntity();
        selectedToken = modelo.obtenerToken(id);
        System.out.println(selectedToken.getIdEstado().getDescripcion());
        
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
    
}
