package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.model.AreasModel;

import org.fusalmo.www.utils.JsfUtil;

@ManagedBean
@RequestScoped
public class AreasBean {

    private String idRecurso;

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

    AreasModel modelo = new AreasModel();
    private AreaEntity mante;
    private List<AreaEntity> Listamante;

    public AreasBean() {
        mante = new AreaEntity();
        AreasModel modelo = new AreasModel();

    }

    public AreaEntity getMantenimientos() {
        return mante;
    }

    public void setMantenimientos(AreaEntity mante) {
        this.mante = mante;
    }

    public List<AreaEntity> getListaMantenimientos() {
        /* Notese que se llama al método listarMantemientos
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarMantenimientos();
    }

    public String eliminarMantenimientos() {
        // Leyendo el parametro enviado desde la vista
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarMantenimientos(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Area eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar esta Area");
        }
        return "eliminarMante?faces-redirect=true";
    }

    public List<AreaEntity> getListarJefeAsignado() {
        /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarJefeAsignado();
    }

    public String guardarAreas() {
        if (modelo.insertarMantenimientos(mante) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un usuario con este ID");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Usuario registrado exitosamente");
            //Forzando la redirección en el cliente
            return "TablaArea?faces-redirect=true";
        }
    }

    public String guardarMantenimientos() {
        System.out.println(getIdRecurso());
        //mante.setIdRecurso(modelo.buscarRecursoId(getIdRecurso()));
        if (modelo.insertarMantenimientos(mante) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un mantenimiento con este ID");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Mantenimiento registrado exitosamente");
            //Forzando la redirección en el cliente
            return "TablaArea?faces-redirect=true";
        }
    }
    
    
    
        public String ModificarMantenimientos() {

        if (modelo.modificarMantenimientos(mante) >= 1) {
            JsfUtil.setErrorMessage(null, " no funcionó");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Manteminiento modificado exitosamente");
        }
        return "TablaArea?faces-redirect=true";

    }

}
