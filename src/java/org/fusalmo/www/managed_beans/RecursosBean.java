/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.model.RecursosModel;

/**
 *
 * @author Soporte
 */
@ManagedBean
@RequestScoped
public class RecursosBean {

    RecursosModel modelo= new RecursosModel();
    private RecursosEntity recurso;
    private List<RecursosEntity>listaRecursos;
    /**
     * Creates a new instance of RecursosBean
     */
    public RecursosBean() {
    }
    
    public List<RecursosEntity> getListaRecursos(){
        return modelo.listarRecursos();
    }
    
    public String ponerIP(RecursosEntity recurso){
        if (recurso.getDireccionIP()== null) {
            return "NO";
        }
        else{
            return recurso.getDireccionIP();
        }
    }
    
    public String ponerMAC(RecursosEntity recurso){
        if (recurso.getDireccionMAC()== null) {
            return "NO";
        }
        else{
            return recurso.getDireccionMAC();
        }
    }
    
}
