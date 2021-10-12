/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.model.EmpleadoModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;


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
 if (modelo.insertarEmpleado(empleado) != 1) {
 JsfUtil.setErrorMessage(null, "Ya se registró un empleado con este ID");
 return null;//Regreso a la misma página
 } else {
 JsfUtil.setFlashMessage("exito", "Empleado registrado exitosamente");
 //Forzando la redirección en el cliente
 return "TablaEmpleado?faces-redirect=true";
 }
 }
 public String eliminarEmpleado() {
 // Leyendo el parametro enviado desde la vista
 String id= JsfUtil.getRequest().getParameter("id");

 if (modelo.eliminarEmpleado(id) > 0) {
 JsfUtil.setFlashMessage("exito", "Empleado eliminado exitosamente");
 }
 else{
 JsfUtil.setErrorMessage(null, "No se pudo borrar a este Empleado");
 }
 return "TablaEmpleado?faces-redirect=true";
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
}
