/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.fusalmo.www.entities.PrestamoRecursosEntity;
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
    private List<EmpleadoEntity> empleadosConRecursos;
    private List<RecursosEntity> recursosSeleccionados;
    private List<RecursosEntity> listarRecursos;
    private List<RecursosEntity> filtrarListaRecursos;
    private List<PrestamoRecursosEntity> recursosDeEmpleado;
    private List<MemosEntity> listaMemosActivos;
    private EmpleadoEntity empleadoSeleccionado;
    private Date date;
    private Date dateReturn;
    private String areaSeleccionada;
    private String dateSelected;
    private String dateSelectedReturn;
    private String datosEmpleado;
    private String memoSelected;
    private String empleadoSelected;
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
    
    public List<EmpleadoEntity> getEmpleadosConRecursos(){
        System.out.println("Entrooo");
        return getModelo().obtenerEmpleadosConRecursos(getListaEmpleados());
        
    }
    
    public List<MemosEntity> getListaMemosActivos(){
        return modelo.listaMemosActivos();
    }
    
    public String borrarMemo(){
        String idMemo = JsfUtil.getRequest().getParameter("idMemo");
        
        if(modelo.eliminarMemo(idMemo)> 0){
        
            return "eliminarMemo?faces-redirect=true&result=1";
            
        }else{
        
            return "eliminarMemo?faces-redirect=true&result=0";
            
        }
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
    
    public void onChangedEmpleado(){
        
        System.out.println(getEmpleadoSelected() + " Empleado seleccionado");
        setRecursosDeEmpleado(getModelo().listarRecursosPrestadosByID(getEmpleadoSelected()));
        System.out.println(getRecursosDeEmpleado());
        
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
    
    public void convertirFechaDos(){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        System.out.println(dateFormat.format(getDateReturn()));
        setDateSelectedReturn(dateFormat.format(getDateReturn()));
        
    }
    
    public String agregarMemo() throws ParseException{
        
        //De Asignación
        if (JsfUtil.getRequest().getParameter("tipoMemo").equals("ASI")) {

            if (getEmpleadoSeleccionado() != null && getRecursosSeleccionados().isEmpty() != true) {

                //Creación de instancia de tipo MemoEntity
                MemosEntity addMemo = new MemosEntity();

                //Nombre, apellido y cargo
                String datosAdmin = "";

                //Creación de instancia de tipo AreaEntity para agregarlo a la bd
                AreaEntity area = getModelo().obtenerArea(getAreaSeleccionada());

                //Formato de fecha para el documento PDF
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

                //Se setea la fecha final que irá en el documento PDF
                setDateSelected(dateFormat.format(getDate()));

                //Seteo de campos para la bd
                addMemo.setAsunto(getMemos().getAsunto());
                addMemo.setId(getModelo().crearID());
                addMemo.setIdAgenteIT(getModelo().obtenerAdminIT(JsfUtil.getRequest().getParameter("IdEmp")));
                addMemo.setIdEmpleado(getEmpleadoSeleccionado());
                addMemo.setIdTipo(getModelo().obtenerTipoMemo(JsfUtil.getRequest().getParameter("tipoMemo")));
                addMemo.setFechaEntrega(getDate());
                addMemo.setCantidadRecursos(getRecursosSeleccionados().size());
                addMemo.setPara(
                        area.getIdJefeAsignado().getNombres()
                        + " "
                        + area.getIdJefeAsignado().getApellidos()
                        + " ("
                        + area.getNombre()
                        + ")"
                );
                addMemo.setDe(
                        addMemo.getIdAgenteIT().getNombres()
                        + addMemo.getIdAgenteIT().getApellidos()
                        + " (Administrador IT)"
                );
                addMemo.setDescripcion(
                        "Por este medio, se efectúa la asignación de equipo "
                        + "informático en calidad de "
                        + "ASIGNACIÓN para el usuario "
                        + addMemo.getIdEmpleado().getNombres()
                        + " "
                        + addMemo.getIdEmpleado().getApellidos()
                        + " ("
                        + addMemo.getIdEmpleado().getCargo()
                        + ")"
                );
                addMemo.setIsDeleted(false);
                //Fin de seteo de campos

                //Se setea los datos del empleado con los datos antes seteados
                //del addMemo de tipo MemoEntity para poder llenar el PDF
                setDatosEmpleado(
                        addMemo.getIdEmpleado().getNombres()
                        + " "
                        + addMemo.getIdEmpleado().getApellidos()
                        + " ("
                        + addMemo.getIdEmpleado().getCargo()
                        + ")"
                );

                //Datos del administrador o empleado IT del que ha hecho el memo
                datosAdmin = addMemo.getIdAgenteIT().getNombres()
                        + " "
                        + addMemo.getIdAgenteIT().getApellidos()
                        + " (Administrador IT)";

                //Se crea el PDF
                getModelo().crearPDFAsignacion(
                        addMemo.getPara(),
                        datosAdmin,
                        addMemo.getAsunto(),
                        getDateSelected(),
                        getDatosEmpleado(),
                        getRecursosSeleccionados(),
                        addMemo.getId()
                );

                //Se convierte el PDF a un arreglo de byte para agregarlo a la BD
                addMemo.setPdf(getModelo().convertirPDF(addMemo.getId()));

                getModelo().borrarPDF(addMemo.getId());

                if (getModelo().crearMemo(addMemo) != 0) {

                    System.out.println("Se agregó el memo con éxito");

                    //Agregar datos a prestamo recursos
                    for (RecursosEntity recursosSeleccionado : recursosSeleccionados) {

                        PrestamoRecursosEntity prestamo = new PrestamoRecursosEntity();

                        prestamo.setIdEmpleado(addMemo.getIdEmpleado());
                        prestamo.setIdMemo(getModelo().buscarMemoById(addMemo.getId()));
                        prestamo.setIdRecurso(recursosSeleccionado);

                        //Modifica el valor de isDelete para que el recurso no se
                        //vuelva a reutilizar porque ya se asignó
                        recursosSeleccionado.setIsDeleted(true);
                        getModelo().modificarRecursoEntity(recursosSeleccionado);

                        if (getModelo().crearPrestamoRecurso(prestamo) != 0) {

                            System.out.println("el prestamo se agregó correctamente");

                        } else {

                            System.out.println("Hubo un error al agregar el prestamo");

                        }

                    }

                    return "/adminIT/memos/crear/index-crear?faces-redirect=true&result=1";

                } else {

                    System.out.println("No se pudo agregar el memo");

                    return "memo-asignacion?faces-redirect=true&result=0";

                }

            } else {

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Debe seleccionar un empleado y los recursos a asignar.",
                                "Algun campo malo")
                );

                return null;

            }

        }
        
        //Sustitución
        if(JsfUtil.getRequest().getParameter("tipoMemo").equals("SUS")){
            
            if(getEmpleadoSelected().equals("null") != true && getRecursosSeleccionados().isEmpty() != true){
                
                //Creación de instancia de tipo MemoEntity
                MemosEntity addMemo = new MemosEntity();

                //Nombre, apellido y cargo
                String datosAdmin = "";

                //Creación de instancia de tipo AreaEntity para agregarlo a la bd
                AreaEntity area = getModelo().obtenerArea(getAreaSeleccionada());

                //Formato de fecha para el documento PDF
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

                //Se setea la fecha final que irá en el documento PDF
                setDateSelected(dateFormat.format(getDate()));

                //Seteo de campos para la bd
                addMemo.setAsunto(getMemos().getAsunto());
                addMemo.setId(getModelo().crearID());
                addMemo.setIdAgenteIT(getModelo().obtenerAdminIT(JsfUtil.getRequest().getParameter("IdEmp")));
                addMemo.setIdEmpleado(getModelo().buscarEmpleadoByID(getEmpleadoSelected()));
                addMemo.setIdTipo(getModelo().obtenerTipoMemo(JsfUtil.getRequest().getParameter("tipoMemo")));
                addMemo.setFechaEntrega(getDate());
                addMemo.setCantidadRecursos(getRecursosSeleccionados().size());
                addMemo.setPara(
                        area.getIdJefeAsignado().getNombres()
                        + " "
                        + area.getIdJefeAsignado().getApellidos()
                        + " ("
                        + area.getNombre()
                        + ")"
                );
                addMemo.setDe(
                        addMemo.getIdAgenteIT().getNombres()
                        + addMemo.getIdAgenteIT().getApellidos()
                        + " (Administrador IT)"
                );
                addMemo.setDescripcion(
                        "Por este medio, se efectúa la asignación de equipo "
                        + "informático en calidad de SUSTITUCIÓN del usuario "
                        + addMemo.getIdEmpleado().getNombres()
                        + " "
                        + addMemo.getIdEmpleado().getApellidos()
                        + " ("
                        + addMemo.getIdEmpleado().getCargo()
                        + ")"
                );
                addMemo.setIsDeleted(false);
                //Fin de seteo de campos

                //Se setea los datos del empleado con los datos antes seteados
                //del addMemo de tipo MemoEntity para poder llenar el PDF
                setDatosEmpleado(
                        addMemo.getIdEmpleado().getNombres()
                        + " "
                        + addMemo.getIdEmpleado().getApellidos()
                        + " ("
                        + addMemo.getIdEmpleado().getCargo()
                        + ")"
                );

                //Datos del administrador o empleado IT del que ha hecho el memo
                datosAdmin = addMemo.getIdAgenteIT().getNombres()
                        + " "
                        + addMemo.getIdAgenteIT().getApellidos()
                        + " (Administrador IT)";
                
                //Lista de todos los recursos prestados
                List<PrestamoRecursosEntity> prestamos = getModelo().listarRecursosPrestadosByID(getEmpleadoSelected());

                //Se crea el PDF
                getModelo().crearPDFSustitucion(
                        addMemo.getPara(),
                        datosAdmin,
                        addMemo.getAsunto(),
                        getDateSelected(),
                        getDatosEmpleado(),
                        getRecursosSeleccionados(),
                        addMemo.getId(),
                        prestamos
                );

                //Se convierte el PDF a un arreglo de byte para agregarlo a la BD
                addMemo.setPdf(getModelo().convertirPDF(addMemo.getId()));

                getModelo().borrarPDF(addMemo.getId());
                
                System.out.println(getModelo().crearID());
                
                if (getModelo().crearMemo(addMemo) != 0) {
                    
                    for (PrestamoRecursosEntity prestamo : prestamos) {
                        
                        prestamo.getIdRecurso().setIsDeleted(false);
                        
                        getModelo().modificarRecursoEntity(prestamo.getIdRecurso());
                        
                        System.out.println(prestamo);
                        System.out.println(prestamo.getIdPrestamo());
                        
                        if(getModelo().eliminarPrestamo(prestamo.getIdPrestamo()) != 0){
                            
                            System.out.println("Se eliminó el prestamo con exito");
                            
                        }else{
                            
                            System.out.println("No se eliminó el prestamo");
                            
                        }
                        
                    }

                    System.out.println("Se agregó el memo con éxito");

                    //Agregar datos a prestamo recursos
                    for (RecursosEntity recursosSeleccionado : recursosSeleccionados) {
                        System.out.println(recursosSeleccionado + " Recursos seleccionados");
                        PrestamoRecursosEntity prestamo = new PrestamoRecursosEntity();

                        prestamo.setIdEmpleado(addMemo.getIdEmpleado());
                        prestamo.setIdMemo(getModelo().buscarMemoById(addMemo.getId()));
                        prestamo.setIdRecurso(recursosSeleccionado);

                        //Modifica el valor de isDelete para que el recurso no se
                        //vuelva a reutilizar porque ya se asignó
                        recursosSeleccionado.setIsDeleted(true);
                        getModelo().modificarRecursoEntity(recursosSeleccionado);

                        if (getModelo().crearPrestamoRecurso(prestamo) != 0) {

                            System.out.println("el prestamo se agregó correctamente");

                        } else {

                            System.out.println("Hubo un error al agregar el prestamo");

                        }

                    }
                    
                    return "/adminIT/memos/crear/index-crear?faces-redirect=true&result=1";

                } else {

                    System.out.println("No se pudo agregar el memo");

                    return "memo-asignacion?faces-redirect=true&result=0";

                }
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Debe seleccionar un empleado y los recursos a sustituir.",
                                "Algun campo malo")
                );
                
                return null;
                
            }
            
        }        
        
        //Prestamo
        if(JsfUtil.getRequest().getParameter("tipoMemo").equals("EXT")){
            
            System.out.println("Memo de prestamo externo");
            return null;
            
        }
        
        return null;
        
    }
    
    public void prueba(){
        
        System.out.println("ENTROOOo");
        System.out.println(getEmpleadoSelected());
        System.out.println(getRecursosDeEmpleado());
        
    }
    
    public StreamedContent obtenerPDFFirmado(String args){
        this.PDFFirmado= modelo.obtenerPDFFirmadoById(args);
        return this.PDFFirmado;
    }
    
    public StreamedContent obtenerPDF(String args){
        this.PDFFirmado= modelo.obtenerPDFById(args);
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
    
    public String convertDateFromDatabase(Object date){
        
        System.out.println(date);
        String returnDate = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        
        returnDate = dateFormat.format(date);
        
        return returnDate;
        
    }
    
    public String convertBoolean(Boolean args){
        
        if(args == true){
            
            return "Sí";
            
        }else{
            
            return "No";
            
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

    /**
     * @return the empleadoSelected
     */
    public String getEmpleadoSelected() {
        return empleadoSelected;
    }

    /**
     * @param empleadoSelected the empleadoSelected to set
     */
    public void setEmpleadoSelected(String empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
    }

    /**
     * @return the recursosDeEmpleado
     */
    public List<PrestamoRecursosEntity> getRecursosDeEmpleado() {
        return recursosDeEmpleado;
    }

    /**
     * @param recursosDeEmpleado the recursosDeEmpleado to set
     */
    public void setRecursosDeEmpleado(List<PrestamoRecursosEntity> recursosDeEmpleado) {
        this.recursosDeEmpleado = recursosDeEmpleado;
    }

    /**
     * @return the dateReturn
     */
    public Date getDateReturn() {
        return dateReturn;
    }

    /**
     * @param dateReturn the dateReturn to set
     */
    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    /**
     * @return the dateSelectedReturn
     */
    public String getDateSelectedReturn() {
        return dateSelectedReturn;
    }

    /**
     * @param dateSelectedReturn the dateSelectedReturn to set
     */
    public void setDateSelectedReturn(String dateSelectedReturn) {
        this.dateSelectedReturn = dateSelectedReturn;
    }
    
}
