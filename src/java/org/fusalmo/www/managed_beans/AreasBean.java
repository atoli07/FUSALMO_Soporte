package org.fusalmo.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.JefeAsignadoEntity;
import org.fusalmo.www.model.AreasModel;

import org.fusalmo.www.utils.JsfUtil;
import org.fusalmo.www.utils.MessageUtil;

@ManagedBean
@RequestScoped
public class AreasBean {

    private String idRecurso;
    private String jefeSeleccionado;

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
    private AreaEntity area;
    private List<AreaEntity> Listamante;

    public AreasBean() {
        area = new AreaEntity();
        AreasModel modelo = new AreasModel();

    }

    public AreaEntity getMantenimientos() {
        return area;
    }

    public void setMantenimientos(AreaEntity mante) {
        this.area = mante;
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

    public List<JefeAsignadoEntity> getListarJefeAsignado() {
        /* Notese que se llama al método listarArea
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarJefeAsignado();
    }

    public String guardarAreas() {
        if (modelo.insertarMantenimientos(area) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un usuario con este ID");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Usuario registrado exitosamente");
            //Forzando la redirección en el cliente
            return "TablaArea?faces-redirect=true";
        }
    }

    public String guardarArea() {
        System.out.println(getIdRecurso());
        area.setId(modelo.crearID());
        area.setIdJefeAsignado(modelo.buscarJefeID(jefeSeleccionado));
        area.setIsDeleted(false);
        //mante.setIdRecurso(modelo.buscarRecursoId(getIdRecurso()));
        if (modelo.insertarMantenimientos(area) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un mantenimiento con este ID");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Mantenimiento registrado exitosamente");
            //Forzando la redirección en el cliente
            return "/adminIT/areas/administrarAreas?faces-redirect=true";
        }
    }
    
    public String borrarMante() {

        String idRecurso = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarMante(idRecurso) == 1) {

            return "/adminIT/areas/eliminar/eliminarArea?faces-redirect=true&result=1";

        } else {

            return "/adminIT/areas/eliminar/eliminarArea?faces-redirect=true&result=0";

        }

    }
    
    
    /*
        public String ModificarMantenimientos() {
        area = modelo.obtenerMantenimientos(JsfUtil.getRequest().getParameter("idMantenimiento"));
        return "editor/editorArea?faces-redirect=true&idMantenimiento=" + area.getId();
    }
    

    public void obtenerMantenimiento(String mantenimiento) {
        setMantenimientos(modelo.obtenerMantenimientos(mantenimiento));
        setIdRecurso(area.getIdRecurso().getId());
    }

    public String editarMantenimiento() {
        area.setId(JsfUtil.getRequest().getParameter("idMantenimiento"));
        area.setIdRecurso(modelo.buscarRecursoId(getIdRecurso()));
        if (modelo.modificarMantenimientos(area) > 1) {
            MessageUtil mensaje = new MessageUtil();
            mensaje.addMessage("Confirmación", "El Area ha sido actualizado");
            return "/adminIT/areas/editarA/editarArea?faces-redirect=true";
        } else {
            MessageUtil mensaje = new MessageUtil();
            mensaje.addMessage("Error", "El Area no pudo ser actualizado");
            return "/adminIT/areas/editarA/editarArea?faces-redirect=true";
        }
    }
    
     public String ModificarMantenimientos() {

        if (modelo.modificarMantenimientos(area) >= 1) {
            JsfUtil.setErrorMessage(null, " no funcionó");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Manteminiento modificado exitosamente");
        }
        return "TablaArea?faces-redirect=true";

    }
    */

    /**
     * @return the jefeSeleccionado
     */
    public String getJefeSeleccionado() {
        return jefeSeleccionado;
    }

    /**
     * @param jefeSeleccionado the jefeSeleccionado to set
     */
    public void setJefeSeleccionado(String jefeSeleccionado) {
        this.jefeSeleccionado = jefeSeleccionado;
    }

}
