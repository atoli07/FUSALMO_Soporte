/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    
    public void saveResources(){
        
        /*
        System.out.println(recurso.getId());
        System.out.println(recurso.getNombre());
        System.out.println(recurso.getMarca());
        System.out.println(recurso.getModelo());
        System.out.println(recurso.getNumSerie());
        System.out.println(recurso.getDireccionIP());
        System.out.println(recurso.getDireccionMAC());
        System.out.println(recurso.getCodActivo());
        System.out.println(recurso.getImagen());
        System.out.println(getAreaAsignada() + " Area asignada");
        System.out.println(recurso.getCargador());
        System.out.println(getTipoRecurso() + " Tipo de recurso");
        
        System.out.println(modelo.obtenerRecurso(getTipoRecurso()).getId());
        System.out.println(modelo.obtenerRecurso(getTipoRecurso()).getNombre());
        
        System.out.println(modelo.obtenerArea(getAreaAsignada()).getId());
        System.out.println(modelo.obtenerArea(getAreaAsignada()).getNombre());
        */
        
        //modelo.contarRecursos();
        
        //System.out.println(modelo.crearID() + " ID retornado para guardar en la BD");
        
        System.out.println(recurso.getNombre());
        System.out.println(recurso.getCodActivo());
        System.out.println(getAreaAsignada());
        System.out.println(JsfUtil.getRequest().getParameter("tipoRecurso"));
        System.out.println(recurso.getImagen());
        
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
    
    public List<AreaEntity> getListarAreas(){
        
        return modelo.listaAreas();
        
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
