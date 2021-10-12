/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.model.AreaModel;
import org.fusalmo.www.utils.JsfUtil;
import org.fusalmo.www.utils.JPAUtil;



/**
 *
 * @author brand
 */
@ManagedBean
@RequestScoped
public class AreaBean {

    AreaModel modelo = new AreaModel();
 private AreaEntity area;
 private List<AreaEntity> listaArea;
 private AreaEntity idjefe;
 public AreaBean() {
 area = new AreaEntity();
 }
 public AreaEntity getArea() {
 return area;
 }
 public void setArea(AreaEntity area) {
 this.area = area;
 }
 public List<AreaEntity> getListaArea() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarArea();
 }
 public String guardarArea() {
 if (modelo.insertarArea(area) != 1) {
 JsfUtil.setErrorMessage(null, "Ya se registró un usuario con este ID");
 return null;//Regreso a la misma página
 } else {
 JsfUtil.setFlashMessage("exito", "Usuario registrado exitosamente");
 //Forzando la redirección en el cliente
 return "TablaArea?faces-redirect=true";
 }
 }
 public String eliminarArea() {
 // Leyendo el parametro enviado desde la vista
 String id= JsfUtil.getRequest().getParameter("id");

 if (modelo.eliminarArea(id) > 0) {
 JsfUtil.setFlashMessage("exito", "Usuario eliminado exitosamente");
 }
 else{
 JsfUtil.setErrorMessage(null, "No se pudo borrar a este usuario");
 }
 return "TablaArea?faces-redirect=true";
 }
 
 public String ModificarArea() {
      
     if(modelo.modificarArea(area) >= 1){
         JsfUtil.setErrorMessage(null,"alavergA no funcionó");
         return null;
     } else{
         JsfUtil.setFlashMessage("exito", "Alumno modificado exitosamente");
     }
        return "TablaArea?faces-redirect=true";
     
 }   
   public List<AreaEntity> getListarJefeAsignado() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarJefeAsignado();
 }

    
}