/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.model.Memos_Model;

/**
 *
 * @author Nemy
 */
@ManagedBean
@RequestScoped
public class Memos_Bean {

    private Memos_Model modelo = new Memos_Model();
    private MemosEntity memos;
    private List<AreaEntity> listarAreas;
    private List<EmpleadoEntity> listaEMPArea;
    private Date date;
    private String areaSeleccionada;
    private String dateSelected;
    private String empleadoSeleccionado;

    /**
     * Creates a new instance of Memos_Bean
     */
    public Memos_Bean() {
        memos = new MemosEntity();
    }
    
    public List<AreaEntity> getListarAreas(){
        
        return getModelo().listaAreas();
        
    }
    
    public List<EmpleadoEntity> getListaEMPArea(){
        
        return listaEMPArea;
        
    }
    
    public void obtenerArea(){
        
        AreaEntity area = modelo.obtenerArea(getAreaSeleccionada());
        
        if(area != null){
            
            getMemos().setPara(area.getIdJefeAsignado().getNombres() + " " + area.getIdJefeAsignado().getApellidos() + " (" + area.getNombre() + ")");
            //System.out.println(area.getId());
            listaEMPArea = modelo.listarEMPArea(area.getId());
            //getListaEMPArea();
            //System.out.println(getListaEMPArea() + " Resultado de la consulta");
            
        }else{
            
            System.out.println("No hay área seleccionada");
            
        }
        
    }
    
    public void onChanged(){
        
        if(getAreaSeleccionada() != ""){
         
            System.out.println(getAreaSeleccionada());
            AreaEntity area = modelo.obtenerArea(getAreaSeleccionada());
            getMemos().setPara(area.getIdJefeAsignado().getNombres() + " " + area.getIdJefeAsignado().getApellidos() + " (" + area.getNombre() + ")");
            System.out.println("------------------------");
            System.out.println(areaSeleccionada);
            listaEMPArea = modelo.listarEMPArea(areaSeleccionada);
            
        }else{
            System.out.println("No hay área seleccionada");
        }
        
    }
    
    public void prueba(){   
        System.out.println(getEmpleadoSeleccionado());  
    }
    
    public void convertirFecha(){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        System.out.println(dateFormat.format(getDate()));
        setDateSelected(dateFormat.format(getDate()));
        
    }
    
    public void agregarMemo(){
        
        System.out.println("Memo agregado");
        System.out.println(getEmpleadoSeleccionado());
        
    }
    
    /**
     * @return the memos
     */
    public MemosEntity getMemos() {
        return memos;
    }

    /**
     * @param memos the memos to set
     */
    public void setMemos(MemosEntity memos) {
        this.memos = memos;
    }
    
    /**
     * @return the modelo
     */
    public Memos_Model getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Memos_Model modelo) {
        this.modelo = modelo;
    }
    
    /**
     * @return the areaSeleccionada
     */
    public String getAreaSeleccionada() {
        return areaSeleccionada;
    }

    /**
     * @param areaSeleccionada the areaSeleccionada to set
     */
    public void setAreaSeleccionada(String areaSeleccionada) {
        this.areaSeleccionada = areaSeleccionada;
    }
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * @return the dateSelected
     */
    public String getDateSelected() {
        return dateSelected;
    }

    /**
     * @param dateSelected the dateSelected to set
     */
    public void setDateSelected(String dateSelected) {
        this.dateSelected = dateSelected;
    }
    
    /**
     * @return the empleadoSeleccionado
     */
    public String getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    /**
     * @param empleadoSeleccionado the empleadoSeleccionado to set
     */
    public void setEmpleadoSeleccionado(String empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }
    
}
