/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.model.Memos_Model;
import org.fusalmo.www.utils.JsfUtil;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

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
    private List<EmpleadoEntity> listaEmpleados;
    private List<EmpleadoEntity> filtrarListaEmpleados;
    private List<RecursosEntity> recursosSeleccionados;
    private List<RecursosEntity> listarRecursos;
    private List<RecursosEntity> filtrarListaRecursos;
    private EmpleadoEntity empleadoSeleccionado;
    private Date date;
    private String areaSeleccionada;
    private String dateSelected;
    private String datosEmpleado;
    private String memoSelected;
    private StreamedContent PDFFirmado;
    private UploadedFile filePDF;

    /**
     * Creates a new instance of Memos_Bean
     */
    public Memos_Bean() {
        memos = new MemosEntity();
        empleadoSeleccionado = null;
        recursosSeleccionados = null;
    }
    
    public List<AreaEntity> getListarAreas(){
        
        return getModelo().listaAreas();
        
    }
    
    public List<EmpleadoEntity> getListaEmpleados(){
        
        return modelo.listaEmpleados();
        
    }
    
    public void onChanged(){
        
        if(getAreaSeleccionada() != ""){
            
            AreaEntity area = new AreaEntity();
            
            System.out.println(getAreaSeleccionada());
            area = getModelo().obtenerArea(getAreaSeleccionada());
            getMemos().setPara(area.getIdJefeAsignado().getNombres() + " " + area.getIdJefeAsignado().getApellidos() + " (" + area.getNombre() + ")");
            
        }else{
            System.out.println("No hay área seleccionada!");
        }
        
    }
    
    public void onRowSelectedArea(){
        System.out.println(getEmpleadoSeleccionado().getNombres() + " " + getEmpleadoSeleccionado().getApellidos()); 
        System.out.println(getEmpleadoSeleccionado().getId());
        setDatosEmpleado(getEmpleadoSeleccionado().getNombres() + " " + getEmpleadoSeleccionado().getApellidos());
    }
    
    public void onRowSelectedResources(){
        System.out.println(recursosSeleccionados);
        System.out.println("----------------------");
    }
    
    public void convertirFecha(){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        System.out.println(dateFormat.format(getDate()));
        setDateSelected(dateFormat.format(getDate()));
        
    }
    
    public void agregarMemo(){
        
        if(getEmpleadoSeleccionado() != null && getRecursosSeleccionados().isEmpty() != true) {

            String datosAdmin = "";

            AreaEntity area = getModelo().obtenerArea(getAreaSeleccionada());

            getMemos().setPara(
                    area.getIdJefeAsignado().getNombres()
                    + " "
                    + area.getIdJefeAsignado().getApellidos()
                    + " ("
                    + area.getNombre()
                    + ")"
            );

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
            setDateSelected(dateFormat.format(getDate()));

            setDatosEmpleado(
                    getEmpleadoSeleccionado().getNombres()
                    + " "
                    + getEmpleadoSeleccionado().getApellidos()
                    + " ("
                    + getEmpleadoSeleccionado().getCargo()
                    + ")"
            );

            datosAdmin = JsfUtil.getRequest().getParameter(
                    "nombreAdminIT") + 
                    " (Administrador IT)";

            getModelo().crearPDF(
                    getMemos().getPara(),
                    datosAdmin,
                    getMemos().getAsunto(),
                    getDateSelected(),
                    getDatosEmpleado(),
                    getRecursosSeleccionados()
            );

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Debe seleccionar un empleado y los recursos a asignar.",
                            "Algun campo malo")
            );

        }
        
    }
    
    public StreamedContent getPDFFirmado(){
        this.PDFFirmado= modelo.obtenerPDFFirmadoById(JsfUtil.getRequest().getParameter("idMemo"));
        return this.PDFFirmado;
    }
    
    public void updateEmpSelect(String args){
        setMemoSelected(args);
    }
    
    public void uploadPDF(){
        
        System.out.println(getFilePDF());
        if (getFilePDF() != null) {
            
            System.out.println();
            
            MemosEntity obtenerMemo = getModelo().buscarMemoById(JsfUtil.getRequest().getParameter("args"));
            
            System.out.println(obtenerMemo);
            
            //System.out.println(getFilePDF().getFileName() + " Filenames");
            //System.out.println(getFilePDF().getInputStream() + " InputStream");
            //System.out.println(getFilePDF().getContent());
            //getMemos().setPDFFirmado(getFilePDF().getContent());
            
            obtenerMemo.setPDFFirmado(getFilePDF().getContent());
            
            if(getModelo().modificarMemo(obtenerMemo) != 0){
                
                System.out.println("Se agregó el PDF firmado con éxito");
                
            }else{
                System.out.println("No se agregó el archivo PDF firmado");
            }
            
        }else{
            System.out.println("No seleccionó un archivo");
        }
        
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
     * @return the filtrarListaEmpleados
     */
    public List<EmpleadoEntity> getFiltrarListaEmpleados() {
        return filtrarListaEmpleados;
    }

    /**
     * @param filtrarListaEmpleados the filtrarListaEmpleados to set
     */
    public void setFiltrarListaEmpleados(List<EmpleadoEntity> filtrarListaEmpleados) {
        this.filtrarListaEmpleados = filtrarListaEmpleados;
    }

    /**
     * @param listarAreas the listarAreas to set
     */
    public void setListarAreas(List<AreaEntity> listarAreas) {
        this.listarAreas = listarAreas;
    }

    /**
     * @param listaEmpleados the listaEmpleados to set
     */
    public void setListaEmpleados(List<EmpleadoEntity> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    /**
     * @return the empleadoSeleccionado
     */
    public EmpleadoEntity getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    /**
     * @param empleadoSeleccionado the empleadoSeleccionado to set
     */
    public void setEmpleadoSeleccionado(EmpleadoEntity empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }
    
    /**
     * @return the datosEmpleado
     */
    public String getDatosEmpleado() {
        return datosEmpleado;
    }

    /**
     * @param datosEmpleado the datosEmpleado to set
     */
    public void setDatosEmpleado(String datosEmpleado) {
        this.datosEmpleado = datosEmpleado;
    }

    /**
     * @return the recursosSeleccionados
     */
    public List<RecursosEntity> getRecursosSeleccionados() {
        return recursosSeleccionados;
    }

    /**
     * @param recursosSeleccionados the recursosSeleccionados to set
     */
    public void setRecursosSeleccionados(List<RecursosEntity> recursosSeleccionados) {
        this.recursosSeleccionados = recursosSeleccionados;
    }

    /**
     * @return the listarRecursos
     */
    public List<RecursosEntity> getListarRecursos() {
        return getModelo().listaRecursos();
    }

    /**
     * @param listarRecursos the listarRecursos to set
     */
    public void setListarRecursos(List<RecursosEntity> listarRecursos) {
        this.listarRecursos = listarRecursos;
    }

    /**
     * @return the filtrarListaRecursos
     */
    public List<RecursosEntity> getFiltrarListaRecursos() {
        return filtrarListaRecursos;
    }

    /**
     * @param filtrarListaRecursos the filtrarListaRecursos to set
     */
    public void setFiltrarListaRecursos(List<RecursosEntity> filtrarListaRecursos) {
        this.filtrarListaRecursos = filtrarListaRecursos;
    }

    /**
     * @return the filePDF
     */
    public UploadedFile getFilePDF() {
        return filePDF;
    }

    /**
     * @param filePDF the filePDF to set
     */
    public void setFilePDF(UploadedFile filePDF) {
        this.filePDF = filePDF;
    }

    /**
     * @return the memoSelected
     */
    public String getMemoSelected() {
        return memoSelected;
    }

    /**
     * @param memoSelected the memoSelected to set
     */
    public void setMemoSelected(String memoSelected) {
        this.memoSelected = memoSelected;
    }
    
}
