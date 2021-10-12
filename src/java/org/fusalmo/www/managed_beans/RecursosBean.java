/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TipoRecursoEntity;
import org.fusalmo.www.model.RecursosModel;
import org.fusalmo.www.utils.JsfUtil;

/**
 *
 * @author Soporte
 */
@ManagedBean
@RequestScoped
public class RecursosBean {

    RecursosModel modelo = new RecursosModel();
    private String areaAsignada;
    private Integer tipoRecurso;
    private RecursosEntity recurso;
    private List<RecursosEntity>listaRecursos;
    private List<AreaEntity> listarAreas;
    /**
     * Creates a new instance of RecursosBean
     */
    public RecursosBean() {
        
        recurso = new RecursosEntity();
        
    }
    
    public List<RecursosEntity> getListaRecursos(){
        return modelo.listarRecursos();
    }
    
    public List<TipoRecursoEntity> getListaTipoRecurso(){
        return modelo.listarTipoRecursos();
    }
    
    public String ponerIP(RecursosEntity recurso){
        if (recurso.getDireccionIP()== null) {
            return "NO";
        }
        else{
            return recurso.getDireccionIP();
        }
    }
    
    public String ponerMAC(RecursosEntity recurso){
        if (recurso.getDireccionMAC()== null) {
            return "NO";
        }
        else{
            return recurso.getDireccionMAC();
        }
    }
    
    public String opcAgregar(int opcAdd){
        
        System.out.println(opcAdd);
        switch (opcAdd) {
            case 1://Agregar laptop
                return "agregarLaptop?faces-redirect=true&type-resource=" + opcAdd;
            case 2://Agregar laptop
                return "agregarMicrosoftENT?faces-redirect=true&type-resource=" + opcAdd;
            default:
                throw new AssertionError();
        }
        
    }
    
    public String guardarRecurso(){
        
        recurso.setId(modelo.crearID());
        recurso.setIdAreaAsignada(modelo.obtenerArea(getAreaAsignada()));
        recurso.setIdTipoRecurso(
                modelo.obtenerRecurso(
                        Integer.parseInt(
                                JsfUtil.getRequest().getParameter("tipoRecurso")
                        )
                )
        );
        
        if(modelo.nuevoRecurso(getRecurso()) != 1){
            System.out.println("Hubo un error inesperado al registrar la laptop");
            return null;
        }else{
            return "seleccionDeRecurso?faces-redirect=true";
        }
        
    }
    
    public String modificarRecurso(){
        System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        if(modelo.modificarRecurso(recurso) != 0){
            
            System.out.println("Recurso modificado con éxito!");
            
            return "editarRecursos?faces-redirect=true";
            
        }else{
            System.out.println("Error inesperado al modificar recurso!");
            return "editarRecursos?faces-redirect=true";
        }
        
    }
    
    public List<AreaEntity> getListarAreas(){
        
        return modelo.listaAreas();
        
    }
    
    public String borrarRecurso(){
    
        String idRecurso = JsfUtil.getRequest().getParameter("idRecurso");
        
        if(modelo.eliminarRecurso(idRecurso) > 0){
        
            return "eliminarRecurso?faces-redirect=true&result=1";
            
        }else{
        
            return "eliminarRecurso?faces-redirect=true&result=0";
            
        }
        
    }
    
    public void obtenerRecursoID(String resource){
        
        setRecurso(modelo.buscarRecursoId(resource));
        
    }
    
    public String redirectEdit(){
        
        setRecurso(
                modelo.buscarRecursoId(
                        JsfUtil.getRequest().getParameter("idRecurso")
                )
        );
        
        if(getRecurso().getIdTipoRecurso().getId() == 1){
            return "editarRecurso?faces-redirect=true&idRecurso=" + getRecurso().getId() + "&tipo-Recurso=" + getRecurso().getIdTipoRecurso().getId();
        }
        if(getRecurso().getIdTipoRecurso().getId() == 2){
            return "editarRecursoMicrosoft?faces-redirect=true&idRecurso=" + getRecurso().getId();
        }
        
        return null;
        
    }
    
    public void prueba(){
        
        System.out.println("ENTROOO");
        System.out.println(recurso.getId());
        System.out.println(recurso.getNombre());
        
    }
    
    /**
     * @return the recurso
     */
    public RecursosEntity getRecurso() {
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(RecursosEntity recurso) {
        this.recurso = recurso;
    }
    
    /**
     * @return the areaAsignada
     */
    public String getAreaAsignada() {
        return areaAsignada;
    }

    /**
     * @param areaAsignada the areaAsignada to set
     */
    public void setAreaAsignada(String areaAsignada) {
        this.areaAsignada = areaAsignada;
    }

    /**
     * @return the tipoRecurso
     */
    public Integer getTipoRecurso() {
        return tipoRecurso;
    }

    /**
     * @param tipoRecurso the tipoRecurso to set
     */
    public void setTipoRecurso(Integer tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
    
}
