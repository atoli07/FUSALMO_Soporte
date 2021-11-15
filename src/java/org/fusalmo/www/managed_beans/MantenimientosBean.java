package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.MantenimientosEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.model.MantenimientosModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;
import org.fusalmo.www.utils.MessageUtil;

@ManagedBean
@RequestScoped
public class MantenimientosBean {
    
    private String idRecurso;
    private List<MantenimientosEntity>listaManteActivos;

    public List<MantenimientosEntity> getListaManteActivos() {
        return modelo.listarManteActivos();
    }

    public void setListaManteActivos(List<MantenimientosEntity> listaManteActivos) {
        this.listaManteActivos = listaManteActivos;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }
    
    private MantenimientosEntity mantenimiento;

    public MantenimientosEntity getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(MantenimientosEntity mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    MantenimientosModel modelo = new MantenimientosModel();
    private MantenimientosEntity mante;
    private List<MantenimientosEntity> Listamante;

    public MantenimientosBean() {
        mante = new MantenimientosEntity();
        MantenimientosModel modelo = new MantenimientosModel();
        
    }

    public MantenimientosEntity getMantenimientos() {
        return mante;
    }
    
    public void setMantenimientos(MantenimientosEntity mante) {
        // public void setMantenimientos(MantenimientosEntity mante) {
        this.mante = mante;
    }

    public List<MantenimientosEntity> getListaMantenimientos() {
        /* Notese que se llama al método listarMantemientos
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarMantenimientos();
    }

    public String guardarMantenimientos() {
        //mante.setId(modelo.crearID());
        System.out.println(getIdRecurso());
        mante.setIdRecurso(modelo.buscarRecursoId(getIdRecurso()));
        if (modelo.insertarMantenimientos(mante) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un mantenimiento con este ID");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Mantenimiento registrado exitosamente");
            //Forzando la redirección en el cliente
            return "/adminIT/mantenimiento/listar/listar?faces-redirect=true&result=1";
        }
    }

    public String borrarMante() {

        String idRecurso = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarMante(idRecurso) == 1) {

            return "eliminarMante?faces-redirect=true&result=1";

        } else {

            return "eliminarMante?faces-redirect=true&result=0";

        }

    }

    public String ModificarMantenimientos() {
        mante= modelo.obtenerMantenimientos(JsfUtil.getRequest().getParameter("idMantenimiento"));
        return "editor/editarMantenimiento?faces-redirect=true&idMantenimiento="+mante.getId();
    }
    
    public void obtenerMantenimiento(String mantenimiento){
        setMantenimientos(modelo.obtenerMantenimientos(mantenimiento));
        setIdRecurso(mante.getIdRecurso().getId());
    }
    
    public String editarMantenimiento(){
        mante.setId(JsfUtil.getRequest().getParameter("idMantenimiento"));
        mante.setIdRecurso(modelo.buscarRecursoId(getIdRecurso()));
        if (modelo.modificarMantenimientos(mante)>1) {
            MessageUtil mensaje= new MessageUtil();
            mensaje.addMessage("Confirmación", "El mantenimiento ha sido actualizado");
            return "/adminIT/mantenimiento/editarM/editarMante?faces-redirect=true";
        }else{
             MessageUtil mensaje= new MessageUtil();
            mensaje.addMessage("Error", "El mantenimiento no pudo ser actualizado");
            return "/adminIT/mantenimiento/editarM/editarMante?faces-redirect=true";
        }
    }

}
