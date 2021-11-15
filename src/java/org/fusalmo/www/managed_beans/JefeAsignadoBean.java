/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.JefeAsignadoEntity;
import org.fusalmo.www.model.JefeAsignadoModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;


/**
 *
 * @author brand
 */
@ManagedBean
@RequestScoped
public class JefeAsignadoBean {

    JefeAsignadoModel modelo = new JefeAsignadoModel();
 private JefeAsignadoEntity jefe;
 private List<JefeAsignadoEntity> Listajefe;
 public JefeAsignadoBean() {
 jefe = new JefeAsignadoEntity();
 }
 public JefeAsignadoEntity getJefe() {
 return jefe;
 }
 public void setJefe(JefeAsignadoEntity jefe) {
 this.jefe = jefe;
 }
 public List<JefeAsignadoEntity> getListaJefe() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarJefe();
 }
 public String guardarJefe() {
     jefe.setId(modelo.crearID());
     jefe.setIsDeleted(false);
 if (modelo.insertarJefe(jefe) != 1) {
 if(modelo.nuevoJefe(getJefe()) != 1){
            System.out.println("Hubo un error inesperado al registrar el jefe");
            return null;//Regreso a la misma página
 } else {
 JsfUtil.setFlashMessage("exito", "Jefe registrado exitosamente");
 //Forzando la redirección en el cliente
 }
 
 
 }
        return "/adminIT/personal/Jefes/AdminJefes?faces-redirect=true";
 }
 public String eliminarJefe() {
 // Leyendo el parametro enviado desde la vista
 String id= JsfUtil.getRequest().getParameter("id");

 if (modelo.eliminarJefe(id) > 0) {
 JsfUtil.setFlashMessage("exito", "Jefe eliminado exitosamente");
 }
 else{
 JsfUtil.setErrorMessage(null, "No se pudo borrar a este Jefe");
 }
 return "/adminIT/personal/Jefes/EliminarJefe/EliminarJefe?faces-redirect=true";
 }
 
 public String ModificarJefe() {
      
     jefe.setId(modelo.crearID());
     jefe.setIsDeleted(false);
 if (modelo.modificarJefe(jefe) != 1) {
 if(modelo.nuevoJefe(getJefe()) != 1){
            System.out.println("Hubo un error inesperado al registrar el jefe");
            return null;//Regreso a la misma página
 } else {
 JsfUtil.setFlashMessage("exito", "Jefe registrado exitosamente");
 //Forzando la redirección en el cliente
 }
 
 
 }
        return "TablaJefeAsignado_1_1?faces-redirect=true";
     
 }   
 
 public String editarJefe(){
        
        //-----------System.out.println(recurso);-------------
        //System.out.println(recurso.getId());
        //System.out.println(recurso.getNombre());
        //System.out.println(recurso.getCodActivo());
        //System.out.println(recurso.getImagen());
        //System.out.println(areaAsignada);
        //System.out.println(recurso.getIdTipoRecurso());
        
        //System.out.println(JsfUtil.getRequest().getParameter("tipoRecurso"));
        //System.out.println(JsfUtil.getRequest().getParameter("idRecurso"));
        
        jefe.setId(JsfUtil.getRequest().getParameter("idJefe"));
        
        
        
        if(modelo.modificarJefe(jefe) != 0){
            
            System.out.println("Se modificó correctamente el jefe");
            return "TablaJefeAsignado_1_1";
            
        }else{
            
            System.out.println("Hubo un error al modificar el recurso");
            return null;
            
        }
        
    }
}
