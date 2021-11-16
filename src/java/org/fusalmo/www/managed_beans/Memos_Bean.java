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
    private EmpleadoEntity empSelected;
    private Date date;
    private Date dateReturn;
    private List<LocalDate> listDaysUse;
    private String areaSeleccionada;
    private String dateSelected;
    private String dateSelectedReturn;
    private String datosEmpleado;
    private String memoSelected;
    private String empleadoSelected;
    private String descripcionDeUso;
    private String horarioAUtilizar;
    private StreamedContent PDFFirmado;
    private UploadedFile filePDF;
    
    //Variables que son utilizadas en el apartado de memos, en la vista previa 
    //al crear un memo de prestamo
    private String mesEnero = "";
    private String diasEnero = "";
    
    private String mesFebrero = "";
    private String diasFebrero = "";
    
    private String mesMarzo = "";
    private String diasMarzo = "";
    
    private String mesAbril = "";
    private String diasAbril = "";
    
    private String mesMayo = "";
    private String diasMayo = "";
    
    private String mesJunio = "";
    private String diasJunio = "";
    
    private String mesJulio = "";
    private String diasJulio = "";
    
    private String mesAgosto = "";
    private String diasAgosto = "";
    
    private String mesSeptiembre = "";
    private String diasSeptiembre = "";
    
    private String mesOctubre = "";
    private String diasOctubre = "";
    
    private String mesNoviembre = "";
    private String diasNoviembre = "";
    
    private String mesDiciembre = "";
    private String diasDiciembre = "";

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
                        if(recursosSeleccionado.getNombre().equals("OFFICE 365")){
                            
                            recursosSeleccionado.setIsDeleted(false);
                            
                        }else{
                            
                            recursosSeleccionado.setIsDeleted(true);
                        
                        }
                        
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
                setDateSelectedReturn(dateFormat.format(getDateReturn()));

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
                        + "informático en calidad de "
                        + "PRÉSTAMO para "
                        + getDescripcionDeUso()
                        + " el cual queda encargado el empleado "
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
                getModelo().crearPDFPrestamo(
                        addMemo.getPara(),
                        datosAdmin,
                        addMemo.getAsunto(),
                        getDateSelected(),
                        getDatosEmpleado(),
                        getRecursosSeleccionados(),
                        addMemo.getId(),
                        getDescripcionDeUso(),
                        getMesEnero(),
                        getDiasEnero(),
                        getMesFebrero(),
                        getDiasFebrero(),
                        getMesMarzo(),
                        getDiasMarzo(),
                        getMesAbril(),
                        getDiasAbril(),
                        getMesMayo(),
                        getDiasMayo(),
                        getMesJunio(),
                        getDiasJunio(),
                        getMesJulio(),
                        getDiasJulio(),
                        getMesAgosto(),
                        getDiasAgosto(),
                        getMesSeptiembre(),
                        getDiasSeptiembre(),
                        getMesOctubre(),
                        getDiasOctubre(),
                        getMesNoviembre(),
                        getDiasNoviembre(),
                        getMesDiciembre(),
                        getDiasDiciembre(),
                        getHorarioAUtilizar()
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
                
            }else{
                
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Debe seleccionar un empleado y los recursos que se prestarán.",
                                "Algun campo malo")
                );
                
                return null;
                
            }
            
        }
        
        return null;
        
    }
    
    public void multiDateSelected(){
        
        List<LocalDate> 
                enero = new ArrayList<>(), 
                febrero = new ArrayList<>(),
                marzo = new ArrayList<>(),
                abril = new ArrayList<>(),
                mayo = new ArrayList<>(),
                junio = new ArrayList<>(),
                julio = new ArrayList<>(),
                agosto = new ArrayList<>(),
                septiembre = new ArrayList<>(),
                octubre = new ArrayList<>(),
                noviembre = new ArrayList<>(),
                diciembre = new ArrayList<>()
                ;
        
        String 
                lunesEnero = "",
                martesEnero = "",
                miercolesEnero = "",
                juevesEnero = "",
                viernesEnero = "",
                sabadoEnero = "",
                domingoEnero = ""
                ;
        
        String 
                lunesFebrero = "",
                martesFebrero = "",
                miercolesFebrero = "",
                juevesFebrero = "",
                viernesFebrero = "",
                sabadoFebrero = "",
                domingoFebrero = ""
                ;
        
        String 
                lunesMarzo = "",
                martesMarzo = "",
                miercolesMarzo = "",
                juevesMarzo = "",
                viernesMarzo = "",
                sabadoMarzo = "",
                domingoMarzo = ""
                ;
        
        String 
                lunesAbril = "",
                martesAbril = "",
                miercolesAbril = "",
                juevesAbril = "",
                viernesAbril = "",
                sabadoAbril = "",
                domingoAbril = ""
                ;
        
        String 
                lunesMayo = "",
                martesMayo = "",
                miercolesMayo = "",
                juevesMayo = "",
                viernesMayo = "",
                sabadoMayo = "",
                domingoMayo = ""
                ;
        
        String 
                lunesJunio = "",
                martesJunio = "",
                miercolesJunio = "",
                juevesJunio = "",
                viernesJunio = "",
                sabadoJunio = "",
                domingoJunio = ""
                ;
        
        String 
                lunesJulio = "",
                martesJulio = "",
                miercolesJulio = "",
                juevesJulio = "",
                viernesJulio = "",
                sabadoJulio = "",
                domingoJulio = ""
                ;
        
        String 
                lunesAgosto = "",
                martesAgosto = "",
                miercolesAgosto = "",
                juevesAgosto = "",
                viernesAgosto = "",
                sabadoAgosto = "",
                domingoAgosto = ""
                ;
        
        String 
                lunesSeptiembre = "",
                martesSeptiembre = "",
                miercolesSeptiembre = "",
                juevesSeptiembre = "",
                viernesSeptiembre = "",
                sabadoSeptiembre = "",
                domingoSeptiembre = ""
                ;
        
        String 
                lunesOctubre = "",
                martesOctubre = "",
                miercolesOctubre = "",
                juevesOctubre = "",
                viernesOctubre = "",
                sabadoOctubre = "",
                domingoOctubre = ""
                ;
        
        String 
                lunesNoviembre = "",
                martesNoviembre = "",
                miercolesNoviembre = "",
                juevesNoviembre = "",
                viernesNoviembre = "",
                sabadoNoviembre = "",
                domingoNoviembre = ""
                ;
        
        String 
                lunesDiciembre = "",
                martesDiciembre = "",
                miercolesDiciembre = "",
                juevesDiciembre = "",
                viernesDiciembre = "",
                sabadoDiciembre = "",
                domingoDiciembre = ""
                ;
        
        for (LocalDate localDate : listDaysUse) {
            
            /*
            System.out.println(localDate);
            System.out.println(localDate.getDayOfWeek().name());
            System.out.println(localDate.getDayOfMonth());
            System.out.println(localDate.getMonth().name().equals("DECEMBER"));
            System.out.println(localDate.getYear());
            System.out.println("-----------------------------");
            */
            
            if(localDate.getMonth().name().equals("JANUARY")){
                
                enero.add(localDate);
                setMesEnero("Mes de Enero");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesEnero += "Lunes " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesEnero += "Martes " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesEnero += "Miércoles " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesEnero += "Jueves " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesEnero += "Viernes " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoEnero += "Sábado " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoEnero += "Domingo " + localDate.getDayOfMonth() + " de Enero,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("FEBRUARY")){
                
                febrero.add(localDate);
                setMesFebrero("Mes de Febrero");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesFebrero += "Lunes " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesFebrero += "Martes " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesFebrero += "Miércoles " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesFebrero += "Jueves " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesFebrero += "Viernes " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoFebrero += "Sábado " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoFebrero += "Domingo " + localDate.getDayOfMonth() + " de Febrero,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("MARCH")){
                
                marzo.add(localDate);
                setMesMarzo("Mes de Marzo");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesMarzo += "Lunes " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesMarzo += "Martes " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesMarzo += "Miércoles " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesMarzo += "Jueves " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesMarzo += "Viernes " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoMarzo += "Sábado " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoMarzo += "Domingo " + localDate.getDayOfMonth() + " de Marzo,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("APRIL")){
                
                abril.add(localDate);
                setMesAbril("Mes de Abril");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesAbril += "Lunes " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesAbril += "Martes " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesAbril += "Miércoles " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesAbril += "Jueves " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesAbril += "Viernes " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoAbril += "Sábado " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoAbril += "Domingo " + localDate.getDayOfMonth() + " de Abril,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("MAY")){
                
                mayo.add(localDate);
                setMesMayo("Mes de Mayo");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesMayo += "Lunes " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesMayo += "Martes " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesMayo += "Miércoles " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesMayo += "Jueves " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesMayo += "Viernes " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoMayo += "Sábado " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoMayo += "Domingo " + localDate.getDayOfMonth() + " de Mayo,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("JUNE")){
                
                junio.add(localDate);
                setMesJunio("Mes de Junio");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesJunio += "Lunes " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesJunio += "Martes " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesJunio += "Miércoles " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesJunio += "Jueves " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesJunio += "Viernes " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoJunio += "Sábado " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoJunio += "Domingo " + localDate.getDayOfMonth() + " de Junio,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("JULY")){
                
                julio.add(localDate);
                setMesJulio("Mes de Julio");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesJulio += "Lunes " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesJulio += "Martes " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesJulio += "Miércoles " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesJulio += "Jueves " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesJulio += "Viernes " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoJulio += "Sábado " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoJulio += "Domingo " + localDate.getDayOfMonth() + " de Julio,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("AUGUST")){
                
                agosto.add(localDate);
                setMesAgosto("Mes de Agosto");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesAgosto += "Lunes " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesAgosto += "Martes " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesAgosto += "Miércoles " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesAgosto += "Jueves " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesAgosto += "Viernes " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoAgosto += "Sábado " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoAgosto += "Domingo " + localDate.getDayOfMonth() + " de Agosto,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("SEPTEMBER")){
                
                septiembre.add(localDate);
                setMesSeptiembre("Mes de Septiembre");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesSeptiembre += "Lunes " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesSeptiembre += "Martes " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesSeptiembre += "Miércoles " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesSeptiembre += "Jueves " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesSeptiembre += "Viernes " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoSeptiembre += "Sábado " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoSeptiembre += "Domingo " + localDate.getDayOfMonth() + " de Septiembre,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("OCTOBER")){
                
                octubre.add(localDate);
                setMesOctubre("Mes de Octubre");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesOctubre += "Lunes " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesOctubre += "Martes " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesOctubre += "Miércoles " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesOctubre += "Jueves " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesOctubre += "Viernes " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoOctubre += "Sábado " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoOctubre += "Domingo " + localDate.getDayOfMonth() + " de Octubre,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("NOVEMBER")){
                
                noviembre.add(localDate);
                setMesNoviembre("Mes de Noviembre");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesNoviembre += "Lunes " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesNoviembre += "Martes " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesNoviembre += "Miércoles " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesNoviembre += "Jueves " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesNoviembre += "Viernes " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoNoviembre += "Sábado " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoNoviembre += "Domingo " + localDate.getDayOfMonth() + " de Noviembre,\n";
                }
                
            }
            if(localDate.getMonth().name().equals("DECEMBER")){
                
                diciembre.add(localDate);
                setMesDiciembre("Mes de Diciembre");
                
                if(localDate.getDayOfWeek().name().equals("MONDAY")){
                    lunesDiciembre += "Lunes " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("TUESDAY")){
                    martesDiciembre += "Martes " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("WEDNESDAY")){
                    miercolesDiciembre += "Miércoles " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("THURSDAY")){
                    juevesDiciembre += "Jueves " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("FRIDAY")){
                    viernesDiciembre += "Viernes " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SATURDAY")){
                    sabadoDiciembre += "Sábado " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                if(localDate.getDayOfWeek().name().equals("SUNDAY")){
                    domingoDiciembre += "Domingo " + localDate.getDayOfMonth() + " de Diciembre,\n";
                }
                
            }
            
        }
        /*
        System.out.println(enero);
        System.out.println(febrero);
        System.out.println(marzo);
        System.out.println(abril);
        System.out.println(mayo);
        System.out.println(junio);
        System.out.println(julio);
        System.out.println(agosto);
        System.out.println(septiembre);
        System.out.println(octubre);
        System.out.println(noviembre);
        System.out.println(diciembre);
        
        getMesEnero();
        getMesFebrero();
        getMesMarzo();
        getMesAbril();
        getMesMayo();
        getMesJunio();
        getMesJulio();
        getMesAgosto();
        getMesSeptiembre();
        getMesOctubre();
        getMesNoviembre();
        getMesDiciembre();
        
        System.out.println("ENERO");
        System.out.println(lunesEnero);
        System.out.println(martesEnero);
        System.out.println(miercolesEnero);
        System.out.println(juevesEnero);
        System.out.println(viernesEnero);
        System.out.println(sabadoEnero);
        System.out.println(domingoEnero);
        */
        if(lunesEnero.isEmpty() != true){
            this.diasEnero += lunesEnero + "\n";
        }
        if(martesEnero.isEmpty() != true){
            this.diasEnero += martesEnero + "\n";
        }
        if(miercolesEnero.isEmpty() != true){
            this.diasEnero += miercolesEnero + "\n";
        }
        if(juevesEnero.isEmpty() != true){
            this.diasEnero += juevesEnero + "\n";
        }
        if(viernesEnero.isEmpty() != true){
            this.diasEnero += viernesEnero + "\n";
        }
        if(sabadoEnero.isEmpty() != true){
            this.diasEnero += sabadoEnero + "\n";
        }
        if(domingoEnero.isEmpty() != true){
            this.diasEnero += domingoEnero + "\n";
        }
        /*
        System.out.println("Febrero");
        System.out.println(lunesFebrero);
        System.out.println(martesFebrero);
        System.out.println(miercolesFebrero);
        System.out.println(juevesFebrero);
        System.out.println(viernesFebrero);
        System.out.println(sabadoFebrero);
        System.out.println(domingoFebrero);
        */
        if(lunesFebrero.isEmpty() != true){
            this.diasFebrero += lunesFebrero + "\n";
        }
        if(martesFebrero.isEmpty() != true){
            this.diasFebrero += martesFebrero + "\n";
        }
        if(miercolesFebrero.isEmpty() != true){
            this.diasFebrero += miercolesFebrero + "\n";
        }
        if(juevesFebrero.isEmpty() != true){
            this.diasFebrero += juevesFebrero + "\n";
        }
        if(viernesFebrero.isEmpty() != true){
            this.diasFebrero += viernesFebrero + "\n";
        }
        if(sabadoFebrero.isEmpty() != true){
            this.diasFebrero += sabadoFebrero + "\n";
        }
        if(domingoFebrero.isEmpty() != true){
            this.diasFebrero += domingoFebrero + "\n";
        }
        /*
        System.out.println("MARZO");
        System.out.println(lunesMarzo);
        System.out.println(martesMarzo);
        System.out.println(miercolesMarzo);
        System.out.println(juevesMarzo);
        System.out.println(viernesMarzo);
        System.out.println(sabadoMarzo);
        System.out.println(domingoMarzo);
        */
        if(lunesMarzo.isEmpty() != true){
            this.diasMarzo += lunesMarzo + "\n";
        }
        if(martesMarzo.isEmpty() != true){
            this.diasMarzo += martesMarzo + "\n";
        }
        if(miercolesMarzo.isEmpty() != true){
            this.diasMarzo += miercolesMarzo + "\n";
        }
        if(juevesMarzo.isEmpty() != true){
            this.diasMarzo += juevesMarzo + "\n";
        }
        if(viernesMarzo.isEmpty() != true){
            this.diasMarzo += viernesMarzo + "\n";
        }
        if(sabadoMarzo.isEmpty() != true){
            this.diasMarzo += sabadoMarzo + "\n";
        }
        if(domingoMarzo.isEmpty() != true){
            this.diasMarzo += domingoMarzo + "\n";
        }
        /*
        System.out.println("ABRIL");
        System.out.println(lunesAbril);
        System.out.println(martesAbril);
        System.out.println(miercolesAbril);
        System.out.println(juevesAbril);
        System.out.println(viernesAbril);
        System.out.println(sabadoAbril);
        System.out.println(domingoAbril);
        */
        if(lunesAbril.isEmpty() != true){
            this.diasAbril += lunesAbril + "\n";
        }
        if(martesAbril.isEmpty() != true){
            this.diasAbril += martesAbril + "\n";
        }
        if(miercolesAbril.isEmpty() != true){
            this.diasAbril += miercolesAbril + "\n";
        }
        if(juevesAbril.isEmpty() != true){
            this.diasAbril += juevesAbril + "\n";
        }
        if(viernesAbril.isEmpty() != true){
            this.diasAbril += viernesAbril + "\n";
        }
        if(sabadoAbril.isEmpty() != true){
            this.diasAbril += sabadoAbril + "\n";
        }
        if(domingoAbril.isEmpty() != true){
            this.diasAbril += domingoAbril + "\n";
        }
        /*
        System.out.println("MAYO");
        System.out.println(lunesMayo);
        System.out.println(martesMayo);
        System.out.println(miercolesMayo);
        System.out.println(juevesMayo);
        System.out.println(viernesMayo);
        System.out.println(sabadoMayo);
        System.out.println(domingoMayo);
        */
        if(lunesMayo.isEmpty() != true){
            this.diasMayo += lunesMayo + "\n";
        }
        if(martesMayo.isEmpty() != true){
            this.diasMayo += martesMayo + "\n";
        }
        if(miercolesMayo.isEmpty() != true){
            this.diasMayo += miercolesMayo + "\n";
        }
        if(juevesMayo.isEmpty() != true){
            this.diasMayo += juevesMayo + "\n";
        }
        if(viernesMayo.isEmpty() != true){
            this.diasMayo += viernesMayo + "\n";
        }
        if(sabadoMayo.isEmpty() != true){
            this.diasMayo += sabadoMayo + "\n";
        }
        if(domingoMayo.isEmpty() != true){
            this.diasMayo += domingoMayo + "\n";
        }
        /*
        System.out.println("JUNIO");
        System.out.println(lunesJunio);
        System.out.println(martesJunio);
        System.out.println(miercolesJunio);
        System.out.println(juevesJunio);
        System.out.println(viernesJunio);
        System.out.println(sabadoJunio);
        System.out.println(domingoJunio);
        */
        if(lunesJunio.isEmpty() != true){
            this.diasJunio += lunesJunio + "\n";
        }
        if(martesJunio.isEmpty() != true){
            this.diasJunio += martesJunio + "\n";
        }
        if(miercolesJunio.isEmpty() != true){
            this.diasJunio += miercolesJunio + "\n";
        }
        if(juevesJunio.isEmpty() != true){
            this.diasJunio += juevesJunio + "\n";
        }
        if(viernesJunio.isEmpty() != true){
            this.diasJunio += viernesJunio + "\n";
        }
        if(sabadoJunio.isEmpty() != true){
            this.diasJunio += sabadoJunio + "\n";
        }
        if(domingoJunio.isEmpty() != true){
            this.diasJunio += domingoJunio + "\n";
        }
        /*
        System.out.println("JULIO");
        System.out.println(lunesJulio);
        System.out.println(martesJulio);
        System.out.println(miercolesJulio);
        System.out.println(juevesJulio);
        System.out.println(viernesJulio);
        System.out.println(sabadoJulio);
        System.out.println(domingoJulio);
        */
        if(lunesJulio.isEmpty() != true){
            this.diasJulio += lunesJulio + "\n";
        }
        if(martesJulio.isEmpty() != true){
            this.diasJulio += martesJulio + "\n";
        }
        if(miercolesJulio.isEmpty() != true){
            this.diasJulio += miercolesJulio + "\n";
        }
        if(juevesJulio.isEmpty() != true){
            this.diasJulio += juevesJulio + "\n";
        }
        if(viernesJulio.isEmpty() != true){
            this.diasJulio += viernesJulio + "\n";
        }
        if(sabadoJulio.isEmpty() != true){
            this.diasJulio += sabadoJulio + "\n";
        }
        if(domingoJulio.isEmpty() != true){
            this.diasJulio += domingoJulio + "\n";
        }
        /*
        System.out.println("AGOSTO");
        System.out.println(lunesAgosto);
        System.out.println(martesAgosto);
        System.out.println(miercolesAgosto);
        System.out.println(juevesAgosto);
        System.out.println(viernesAgosto);
        System.out.println(sabadoAgosto);
        System.out.println(domingoAgosto);
        */
        if(lunesAgosto.isEmpty() != true){
            this.diasAgosto += lunesAgosto + "\n";
        }
        if(martesAgosto.isEmpty() != true){
            this.diasAgosto += martesAgosto + "\n";
        }
        if(miercolesAgosto.isEmpty() != true){
            this.diasAgosto += miercolesAgosto + "\n";
        }
        if(juevesAgosto.isEmpty() != true){
            this.diasAgosto += juevesAgosto + "\n";
        }
        if(viernesAgosto.isEmpty() != true){
            this.diasAgosto += viernesAgosto + "\n";
        }
        if(sabadoAgosto.isEmpty() != true){
            this.diasAgosto += sabadoAgosto + "\n";
        }
        if(domingoAgosto.isEmpty() != true){
            this.diasAgosto += domingoAgosto + "\n";
        }
        /*
        System.out.println("SEPTIEMBRE");
        System.out.println(lunesSeptiembre);
        System.out.println(martesSeptiembre);
        System.out.println(miercolesSeptiembre);
        System.out.println(juevesSeptiembre);
        System.out.println(viernesSeptiembre);
        System.out.println(sabadoSeptiembre);
        System.out.println(domingoSeptiembre);
        */
        if(lunesSeptiembre.isEmpty() != true){
            this.diasSeptiembre += lunesSeptiembre + "\n";
        }
        if(martesSeptiembre.isEmpty() != true){
            this.diasSeptiembre += martesSeptiembre + "\n";
        }
        if(miercolesSeptiembre.isEmpty() != true){
            this.diasSeptiembre += miercolesSeptiembre + "\n";
        }
        if(juevesSeptiembre.isEmpty() != true){
            this.diasSeptiembre += juevesSeptiembre + "\n";
        }
        if(viernesSeptiembre.isEmpty() != true){
            this.diasSeptiembre += viernesSeptiembre + "\n";
        }
        if(sabadoSeptiembre.isEmpty() != true){
            this.diasSeptiembre += sabadoSeptiembre + "\n";
        }
        if(domingoSeptiembre.isEmpty() != true){
            this.diasSeptiembre += domingoSeptiembre + "\n";
        }
        /*
        System.out.println("OCTUBRE");
        System.out.println(lunesOctubre);
        System.out.println(martesOctubre);
        System.out.println(miercolesOctubre);
        System.out.println(juevesOctubre);
        System.out.println(viernesOctubre);
        System.out.println(sabadoOctubre);
        System.out.println(domingoOctubre);
        */
        if(lunesOctubre.isEmpty() != true){
            this.diasOctubre += lunesOctubre + "\n";
        }
        if(martesOctubre.isEmpty() != true){
            this.diasOctubre += martesOctubre + "\n";
        }
        if(miercolesOctubre.isEmpty() != true){
            this.diasOctubre += miercolesOctubre + "\n";
        }
        if(juevesOctubre.isEmpty() != true){
            this.diasOctubre += juevesOctubre + "\n";
        }
        if(viernesOctubre.isEmpty() != true){
            this.diasOctubre += viernesOctubre + "\n";
        }
        if(sabadoOctubre.isEmpty() != true){
            this.diasOctubre += sabadoOctubre + "\n";
        }
        if(domingoOctubre.isEmpty() != true){
            this.diasOctubre += domingoOctubre + "\n";
        }
        /*
        System.out.println("NOVIEMBRE");
        System.out.println(lunesNoviembre);
        System.out.println(martesNoviembre);
        System.out.println(miercolesNoviembre);
        System.out.println(juevesNoviembre);
        System.out.println(viernesNoviembre);
        System.out.println(sabadoNoviembre);
        System.out.println(domingoNoviembre);
        */
        if(lunesNoviembre.isEmpty() != true){
            this.diasNoviembre += lunesNoviembre + "\n";
        }
        if(martesNoviembre.isEmpty() != true){
            this.diasNoviembre += martesNoviembre + "\n";
        }
        if(miercolesNoviembre.isEmpty() != true){
            this.diasNoviembre += miercolesNoviembre + "\n";
        }
        if(juevesNoviembre.isEmpty() != true){
            this.diasNoviembre += juevesNoviembre + "\n";
        }
        if(viernesNoviembre.isEmpty() != true){
            this.diasNoviembre += viernesNoviembre + "\n";
        }
        if(sabadoNoviembre.isEmpty() != true){
            this.diasNoviembre += sabadoNoviembre + "\n";
        }
        if(domingoNoviembre.isEmpty() != true){
            this.diasNoviembre += domingoNoviembre + "\n";
        }
        /*
        System.out.println("DICIEMBRE");
        System.out.println(lunesDiciembre);
        System.out.println(martesDiciembre);
        System.out.println(miercolesDiciembre);
        System.out.println(juevesDiciembre);
        System.out.println(viernesDiciembre);
        System.out.println(sabadoDiciembre);
        System.out.println(domingoDiciembre);
        */
        if(lunesDiciembre.isEmpty() != true){
            this.diasDiciembre += lunesDiciembre + "\n";
        }
        if(martesDiciembre.isEmpty() != true){
            this.diasDiciembre += martesDiciembre + "\n";
        }
        if(miercolesDiciembre.isEmpty() != true){
            this.diasDiciembre += miercolesDiciembre + "\n";
        }
        if(juevesDiciembre.isEmpty() != true){
            this.diasDiciembre += juevesDiciembre + "\n";
        }
        if(viernesDiciembre.isEmpty() != true){
            this.diasDiciembre += viernesDiciembre + "\n";
        }
        if(sabadoDiciembre.isEmpty() != true){
            this.diasDiciembre += sabadoDiciembre + "\n";
        }
        if(domingoDiciembre.isEmpty() != true){
            this.diasDiciembre += domingoDiciembre + "\n";
        }
        
        System.out.println(getMesEnero());
        System.out.println(getDiasEnero());
        
        System.out.println(getMesFebrero());
        System.out.println(getDiasFebrero());
        
        System.out.println(getMesMarzo());
        System.out.println(getDiasMarzo());
        
        System.out.println(getMesAbril());
        System.out.println(getDiasAbril());
        
        System.out.println(getMesMayo());
        System.out.println(getDiasMayo());
        
        System.out.println(getMesJunio());
        System.out.println(getDiasJunio());
        
        System.out.println(getMesJulio());
        System.out.println(getDiasJulio());
        
        System.out.println(getMesAgosto());
        System.out.println(getDiasAgosto());
        
        System.out.println(getMesSeptiembre());
        System.out.println(getDiasSeptiembre());
        
        System.out.println(getMesOctubre());
        System.out.println(getDiasOctubre());
        
        System.out.println(getMesNoviembre());
        System.out.println(getDiasNoviembre());
        
        System.out.println(getMesDiciembre());
        System.out.println(getDiasDiciembre());
        
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
    
    public void onchangeEmpleado(){
        
        System.out.println(getModelo().buscarEmpleadoByID(getEmpleadoSelected()));
        setEmpSelected(getModelo().buscarEmpleadoByID(getEmpleadoSelected()));
        setEmpleadoSelected("Cualquier cosa");
        
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

    /**
     * @return the listDaysUse
     */
    public List<LocalDate> getListDaysUse() {
        return listDaysUse;
    }

    /**
     * @param listDaysUse the listDaysUse to set
     */
    public void setListDaysUse(List<LocalDate> listDaysUse) {
        this.listDaysUse = listDaysUse;
    }

    /**
     * @return the descripcionDeUso
     */
    public String getDescripcionDeUso() {
        return descripcionDeUso;
    }

    /**
     * @param descripcionDeUso the descripcionDeUso to set
     */
    public void setDescripcionDeUso(String descripcionDeUso) {
        this.descripcionDeUso = descripcionDeUso;
    }

    /**
     * @return the horarioAUtilizar
     */
    public String getHorarioAUtilizar() {
        return horarioAUtilizar;
    }

    /**
     * @param horarioAUtilizar the horarioAUtilizar to set
     */
    public void setHorarioAUtilizar(String horarioAUtilizar) {
        this.horarioAUtilizar = horarioAUtilizar;
    }

    /**
     * @return the empSelected
     */
    public EmpleadoEntity getEmpSelected() {
        return empSelected;
    }

    /**
     * @param empSelected the empSelected to set
     */
    public void setEmpSelected(EmpleadoEntity empSelected) {
        this.empSelected = empSelected;
    }

    /**
     * @return the mesEnero
     */
    public String getMesEnero() {
        return mesEnero;
    }

    /**
     * @param mesEnero the mesEnero to set
     */
    public void setMesEnero(String mesEnero) {
        this.mesEnero = mesEnero;
    }

    /**
     * @return the diasEnero
     */
    public String getDiasEnero() {
        return diasEnero;
    }

    /**
     * @param diasEnero the diasEnero to set
     */
    public void setDiasEnero(String diasEnero) {
        this.diasEnero = diasEnero;
    }

    /**
     * @return the mesFebrero
     */
    public String getMesFebrero() {
        return mesFebrero;
    }

    /**
     * @param mesFebrero the mesFebrero to set
     */
    public void setMesFebrero(String mesFebrero) {
        this.mesFebrero = mesFebrero;
    }

    /**
     * @return the diasFebrero
     */
    public String getDiasFebrero() {
        return diasFebrero;
    }

    /**
     * @param diasFebrero the diasFebrero to set
     */
    public void setDiasFebrero(String diasFebrero) {
        this.diasFebrero = diasFebrero;
    }

    /**
     * @return the mesMarzo
     */
    public String getMesMarzo() {
        return mesMarzo;
    }

    /**
     * @param mesMarzo the mesMarzo to set
     */
    public void setMesMarzo(String mesMarzo) {
        this.mesMarzo = mesMarzo;
    }

    /**
     * @return the diasMarzo
     */
    public String getDiasMarzo() {
        return diasMarzo;
    }

    /**
     * @param diasMarzo the diasMarzo to set
     */
    public void setDiasMarzo(String diasMarzo) {
        this.diasMarzo = diasMarzo;
    }

    /**
     * @return the mesAbril
     */
    public String getMesAbril() {
        return mesAbril;
    }

    /**
     * @param mesAbril the mesAbril to set
     */
    public void setMesAbril(String mesAbril) {
        this.mesAbril = mesAbril;
    }

    /**
     * @return the diasAbril
     */
    public String getDiasAbril() {
        return diasAbril;
    }

    /**
     * @param diasAbril the diasAbril to set
     */
    public void setDiasAbril(String diasAbril) {
        this.diasAbril = diasAbril;
    }

    /**
     * @return the mesMayo
     */
    public String getMesMayo() {
        return mesMayo;
    }

    /**
     * @param mesMayo the mesMayo to set
     */
    public void setMesMayo(String mesMayo) {
        this.mesMayo = mesMayo;
    }

    /**
     * @return the diasMayo
     */
    public String getDiasMayo() {
        return diasMayo;
    }

    /**
     * @param diasMayo the diasMayo to set
     */
    public void setDiasMayo(String diasMayo) {
        this.diasMayo = diasMayo;
    }

    /**
     * @return the mesJunio
     */
    public String getMesJunio() {
        return mesJunio;
    }

    /**
     * @param mesJunio the mesJunio to set
     */
    public void setMesJunio(String mesJunio) {
        this.mesJunio = mesJunio;
    }

    /**
     * @return the diasJunio
     */
    public String getDiasJunio() {
        return diasJunio;
    }

    /**
     * @param diasJunio the diasJunio to set
     */
    public void setDiasJunio(String diasJunio) {
        this.diasJunio = diasJunio;
    }

    /**
     * @return the mesJulio
     */
    public String getMesJulio() {
        return mesJulio;
    }

    /**
     * @param mesJulio the mesJulio to set
     */
    public void setMesJulio(String mesJulio) {
        this.mesJulio = mesJulio;
    }

    /**
     * @return the diasJulio
     */
    public String getDiasJulio() {
        return diasJulio;
    }

    /**
     * @param diasJulio the diasJulio to set
     */
    public void setDiasJulio(String diasJulio) {
        this.diasJulio = diasJulio;
    }

    /**
     * @return the mesAgosto
     */
    public String getMesAgosto() {
        return mesAgosto;
    }

    /**
     * @param mesAgosto the mesAgosto to set
     */
    public void setMesAgosto(String mesAgosto) {
        this.mesAgosto = mesAgosto;
    }

    /**
     * @return the diasAgosto
     */
    public String getDiasAgosto() {
        return diasAgosto;
    }

    /**
     * @param diasAgosto the diasAgosto to set
     */
    public void setDiasAgosto(String diasAgosto) {
        this.diasAgosto = diasAgosto;
    }

    /**
     * @return the mesSeptiembre
     */
    public String getMesSeptiembre() {
        return mesSeptiembre;
    }

    /**
     * @param mesSeptiembre the mesSeptiembre to set
     */
    public void setMesSeptiembre(String mesSeptiembre) {
        this.mesSeptiembre = mesSeptiembre;
    }

    /**
     * @return the diasSeptiembre
     */
    public String getDiasSeptiembre() {
        return diasSeptiembre;
    }

    /**
     * @param diasSeptiembre the diasSeptiembre to set
     */
    public void setDiasSeptiembre(String diasSeptiembre) {
        this.diasSeptiembre = diasSeptiembre;
    }

    /**
     * @return the mesOctubre
     */
    public String getMesOctubre() {
        return mesOctubre;
    }

    /**
     * @param mesOctubre the mesOctubre to set
     */
    public void setMesOctubre(String mesOctubre) {
        this.mesOctubre = mesOctubre;
    }

    /**
     * @return the diasOctubre
     */
    public String getDiasOctubre() {
        return diasOctubre;
    }

    /**
     * @param diasOctubre the diasOctubre to set
     */
    public void setDiasOctubre(String diasOctubre) {
        this.diasOctubre = diasOctubre;
    }

    /**
     * @return the mesNoviembre
     */
    public String getMesNoviembre() {
        return mesNoviembre;
    }

    /**
     * @param mesNoviembre the mesNoviembre to set
     */
    public void setMesNoviembre(String mesNoviembre) {
        this.mesNoviembre = mesNoviembre;
    }

    /**
     * @return the diasNoviembre
     */
    public String getDiasNoviembre() {
        return diasNoviembre;
    }

    /**
     * @param diasNoviembre the diasNoviembre to set
     */
    public void setDiasNoviembre(String diasNoviembre) {
        this.diasNoviembre = diasNoviembre;
    }

    /**
     * @return the mesDiciembre
     */
    public String getMesDiciembre() {
        return mesDiciembre;
    }

    /**
     * @param mesDiciembre the mesDiciembre to set
     */
    public void setMesDiciembre(String mesDiciembre) {
        this.mesDiciembre = mesDiciembre;
    }

    /**
     * @return the diasDiciembre
     */
    public String getDiasDiciembre() {
        return diasDiciembre;
    }

    /**
     * @param diasDiciembre the diasDiciembre to set
     */
    public void setDiasDiciembre(String diasDiciembre) {
        this.diasDiciembre = diasDiciembre;
    }
    
}
