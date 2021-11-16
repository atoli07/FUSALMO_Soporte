package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.JefeAsignadoEntity;
import org.fusalmo.www.model.AREASSModel;
import org.fusalmo.www.utils.JPAUtil;
import org.fusalmo.www.utils.JsfUtil;
import org.fusalmo.www.utils.MessageUtil;

@ManagedBean
@RequestScoped
public class AREASSBean {
    
    private String idRecurso;
    private List<AreaEntity>listaManteActivos;

    public List<AreaEntity> getListaManteActivos() {
        return modelo.listarManteActivos();
    }

    public void setListaManteActivos(List<AreaEntity> listaManteActivos) {
        this.listaManteActivos = listaManteActivos;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }
    
    private AreaEntity mantenimiento;

    public AreaEntity getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(AreaEntity mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    AREASSModel modelo = new AREASSModel();
    private AreaEntity mante;
    private List<AreaEntity> Listamante;

    public AREASSBean() {
        mante = new AreaEntity();
        AREASSModel modelo = new AREASSModel();
        
    }

    public AreaEntity getMantenimientos() {
        return mante;
    }
    
    public void setMantenimientos(AreaEntity mante) {
        // public void setMantenimientos(MantenimientosEntity mante) {
        this.mante = mante;
    }

    public List<AreaEntity> getListaMantenimientos() {
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
        return "editor/editorArea?faces-redirect=true&idMantenimiento="+mante.getId();
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
            mensaje.addMessage("Confirmación", "El Area ha sido actualizado");
            return "/adminIT/FaREAS/editarA/editarArea?faces-redirect=true";
        }else{
             MessageUtil mensaje= new MessageUtil();
            mensaje.addMessage("Error", "El Area no pudo ser actualizado");
            return "/adminIT/FaREAS/editarA/editarArea?faces-redirect=true";
        }
    }

    
       public List<AreaEntity> getListarJefeAsignado() {
 /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
 return modelo.listarJefeAsignado();
 }
    
    
}
