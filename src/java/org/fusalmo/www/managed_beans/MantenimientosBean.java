package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.MantenimientosEntity;
import org.fusalmo.www.model.MantenimientosModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;


@ManagedBean
@RequestScoped
public class MantenimientosBean {

  MantenimientosModel modelo = new MantenimientosModel();
 private MantenimientosEntity mante;
 private List<MantenimientosEntity> Listamante;
 
 public MantenimientosBean() {
 mante = new MantenimientosEntity();
 }
 public MantenimientosEntity getMantenimientos() {
 return mante;
 }
 public void setMantenimientos(MantenimientosEntity mante) {
 this.mante = mante;
 }
 public List<MantenimientosEntity> getListaMantenimientos() {
 /* Notese que se llama al método listarMantemientos
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarMantenimientos();
 }
 public String guardarMantenimientos() {
 if (modelo.insertarMantenimientos(mante) != 1) {
 JsfUtil.setErrorMessage(null, "Ya se registró un mantenimiento con este ID");
 return null;//Regreso a la misma página
 } else {
 JsfUtil.setFlashMessage("exito", "Mantenimiento registrado exitosamente");
 //Forzando la redirección en el cliente
 return "TablaMantenimientos?faces-redirect=true";
 }
 }
 public String eliminarMantenimientos() {
 // Leyendo el parametro enviado desde la vista
 String id= JsfUtil.getRequest().getParameter("id");

 if (modelo.eliminarMantenimientos(id) > 0) {
 JsfUtil.setFlashMessage("exito", "Mantenimiento eliminado exitosamente");
 }
 else{
 JsfUtil.setErrorMessage(null, "No se pudo eliminar este Mantenimiento");
 }
 return "TablaMantenimientos?faces-redirect=true";
 }
 
 public String ModificarMantenimientos() {
      
     if(modelo.modificarMantenimientos(mante) >= 1){
         JsfUtil.setErrorMessage(null," no funcionó");
         return null;
     } else{
         JsfUtil.setFlashMessage("exito", "Manteminiento modificado exitosamente");
     }
        return "TablaMantenimientos?faces-redirect=true";
     
 }   
    
    
}
