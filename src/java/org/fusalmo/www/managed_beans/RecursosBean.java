/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TipoRecursoEntity;
import org.fusalmo.www.model.RecursosModel;
import org.fusalmo.www.utils.JsfUtil;
import org.fusalmo.www.utils.Validate_codAct;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Soporte
 */
@ManagedBean
@RequestScoped
public class RecursosBean {

    RecursosModel modelo = new RecursosModel();
    Validate_codAct validar = new Validate_codAct();
    private String areaAsignada;
    private Integer tipoRecurso;
    private RecursosEntity recurso;
    String idRecurso;
    private int tipoRecursoModificar;
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
                return "tipo/agregarLaptop?faces-redirect=true&type-resource=" + opcAdd;
            case 2://Agregar laptop
                return "tipo/agregarMicrosoftENT?faces-redirect=true&type-resource=" + opcAdd;
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
            return "/adminIT/recursos/agregar/seleccionDeRecurso?faces-redirect=true";
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
    
    public String redirectEdit(){
        
        setRecurso(
                modelo.buscarRecursoId(
                        JsfUtil.getRequest().getParameter("idRecurso")
                )
        );
        
        if(getRecurso().getIdTipoRecurso().getId() == 1){
            return "tipo/laptop?faces-redirect=true&idRecurso=" + getRecurso().getId() + "&tipo-Recurso=" + getRecurso().getIdTipoRecurso().getId();
        }
        if(getRecurso().getIdTipoRecurso().getId() == 2){
            return "tipo/microsoft?faces-redirect=true&idRecurso=" + getRecurso().getId() + "&tipo-Recurso=" + getRecurso().getIdTipoRecurso().getId();
        }
        
        return null;
        
    }
    
    public void obtenerRecursoID(String resource){
        
        setRecurso(modelo.buscarRecursoId(resource));
        
        setTipoRecursoModificar((int) recurso.getIdTipoRecurso().getId());
        
    }
    
    public String editarRecurso(){
        
        //-----------System.out.println(recurso);-------------
        //System.out.println(recurso.getId());
        //System.out.println(recurso.getNombre());
        //System.out.println(recurso.getCodActivo());
        //System.out.println(recurso.getImagen());
        //System.out.println(areaAsignada);
        //System.out.println(recurso.getIdTipoRecurso());
        
        //System.out.println(JsfUtil.getRequest().getParameter("tipoRecurso"));
        //System.out.println(JsfUtil.getRequest().getParameter("idRecurso"));
        
        recurso.setId(JsfUtil.getRequest().getParameter("idRecurso"));
        recurso.setIdTipoRecurso(modelo.obtenerRecurso(Integer.parseInt(JsfUtil.getRequest().getParameter("tipoRecurso"))));
        recurso.setIdAreaAsignada(modelo.obtenerArea(areaAsignada));
        
        if(modelo.modificarRecurso(recurso) != 0){
            
            System.out.println("Se modificó correctamente el recurso");
            return "/adminIT/recursos/editar/verEditar?faces-redirect=true";
            
        }else{
            
            System.out.println("Hubo un error al modificar el recurso");
            return null;
            
        }
        
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

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }
    
    /**
     * @return the tipoRecursoModificar
     */
    public int getTipoRecursoModificar() {
        return tipoRecursoModificar;
    }

    /**
     * @param tipoRecursoModificar the tipoRecursoModificar to set
     */
    public void setTipoRecursoModificar(int tipoRecursoModificar) {
        this.tipoRecursoModificar = tipoRecursoModificar;
    }
    
}
