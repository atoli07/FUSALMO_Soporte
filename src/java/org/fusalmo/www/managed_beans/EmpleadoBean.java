/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.model.EmpleadoModel;
import org.fusalmo.www.model.RecursosModel;
import org.fusalmo.www.utils.JsfUtil;
import org.primefaces.event.SelectEvent;


/**
 *
 * @author brand
 */
@ManagedBean
@RequestScoped
public class EmpleadoBean {

    EmpleadoModel modelo = new EmpleadoModel();
 private EmpleadoEntity empleado;
 private List<EmpleadoEntity> listaEmpleado;
    private String areaAsignada;
    private Integer tipoRecurso;
    private RecursosEntity recurso;
    private List<RecursosEntity>listaRecursos;
    private List<AreaEntity> listarAreas;
    private String f_seleccionada = "";
    private AreaEntity area;
 public EmpleadoBean() {
 empleado = new EmpleadoEntity();
 }
 public EmpleadoEntity getEmpleado() {
 return empleado;
 }
 public void setEmpleado(EmpleadoEntity empleado) {
 this.empleado = empleado;
 }
 public List<EmpleadoEntity> getListaEmpleado() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarEmpleado();
 }
  public List<EmpleadoEntity> getListarArea() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarEmpleado();
 }
  public List<EmpleadoEntity> getListarRecurso() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listaRecursos();
 }
 public String guardarEmpleado() {
     empleado.setIdAreaAsignada(modelo.obtenerArea(getAreaAsignada()));
     empleado.setId(modelo.crearID());
     empleado.setIsDeleted(false);
 if(modelo.nuevoEmpleado(getEmpleado()) != 1){
            System.out.println("Hubo un error inesperado al registrar el empleado");
            return null;
        } else {

            return "/adminIT/personal/Administracion?faces-redirect=true";
        }
    }

    public String eliminarEmpleados() {
        // Leyendo el parametro enviado desde la vista
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarEmpleados(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Jefe eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este Jefe");
        }
        return "/adminIT/personal/Eliminar/TablaEmpleado_1?faces-redirect=true";
    }
    
    public String borrarEmpleado() {

        String idEmpleado = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarEmp(idEmpleado) == 1) {

            return "TablaEmpleado_1?faces-redirect=true&result=1";

        } else {

            return "TablaEmpleado_1?faces-redirect=true&result=0";

        }

    }

    public String eliminarEmpleado() {
        // Leyendo el parametro enviado desde la vista
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarArea(id) > 0) {
            if (modelo.eliminarEmpleado(id) > 0) {
                JsfUtil.setFlashMessage("exito", "Empleado eliminado exitosamente");
            } else {
                JsfUtil.setErrorMessage(null, "No se pudo borrar a este Empleado");

            }
        }
        return "TablaEmpleado_1?faces-redirect=true";
    }
 
 
 public String ModificarEmpleado() {
      
     if(modelo.modificarEmpleado(empleado) >= 0){
         JsfUtil.setErrorMessage(null,"alavergA no funcionó");
         return null;
     } else{
         JsfUtil.setFlashMessage("exito", "Alumno modificado exitosamente");
     }
        return "TablaEmpleado?faces-redirect=true";
     
 }   


 public String getAreaAsignada() {
        return areaAsignada;
    }

    /**
     * @param areaAsignada the areaAsignada to set
     */
    public void setAreaAsignada(String areaAsignada) {
        this.areaAsignada = areaAsignada;
    }
    
    public void actualizar_fecha(SelectEvent event) {
        SimpleDateFormat fecha1 = new SimpleDateFormat("EEEEE dd MMMMM yyyy");
        StringBuilder cadena_fecha1_11 = new StringBuilder(fecha1.format(event.getObject()));
        f_seleccionada = cadena_fecha1_11.toString();
        
    }
    public String getF_seleccionada() {
        return f_seleccionada;
    }

    public void setF_seleccionada(String f_seleccionada) {
        this.f_seleccionada = f_seleccionada;
    }
    
    public void obtenerEmpleadoID(String empleados){
        
        setEmpleado(modelo.buscarEmpleadosId(empleados));
        
       
        
        
        
    }
    
    public String editarEmpleado(){
        
        //-----------System.out.println(recurso);-------------
        //System.out.println(recurso.getId());
        //System.out.println(recurso.getNombre());
        //System.out.println(recurso.getCodActivo());
        //System.out.println(recurso.getImagen());
        //System.out.println(areaAsignada);
        //System.out.println(recurso.getIdTipoRecurso());
        
        //System.out.println(JsfUtil.getRequest().getParameter("tipoRecurso"));
        //System.out.println(JsfUtil.getRequest().getParameter("idRecurso"));
        
        empleado.setId(JsfUtil.getRequest().getParameter("idEmpleado"));
        empleado.setIdAreaAsignada(modelo.obtenerArea(areaAsignada));
        
        
        if(modelo.modificarEmpleado(empleado) !=0){
            
            System.out.println("Se modificó correctamente el empleado");
            return "/adminIT/personal/Editar/EditarEmpleado?faces-redirect=true";
            
        }else{
            
            System.out.println("Hubo un error al modificar el recurso");
            return null;
            
        }
        
        
    }
    
    public String redirectEdit(){
        
        setEmpleado(
                modelo.buscarEmpleadosId(
                        JsfUtil.getRequest().getParameter("idEmpleado")
                )
        );
  
        
        return "/adminIT/personal/Editar/tipo/editando?faces-redirect=true&empleado=" + JsfUtil.getRequest().getParameter("idEmpleado") ;
        
    }
    
}
