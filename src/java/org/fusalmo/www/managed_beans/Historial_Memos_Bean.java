/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.model.Historial_Memos_Model;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Nemy
 */
@ManagedBean
@RequestScoped
public class Historial_Memos_Bean {
    
    private StreamedContent file;
    private List<MemosEntity> listarMemos;
    private List<MemosEntity> filtrarMemos;
    private Historial_Memos_Model model;

    /**
     * Creates a new instance of Historial_Memos_Bean
     */
    public Historial_Memos_Bean() {
        this.model = new Historial_Memos_Model();
        file = DefaultStreamedContent.builder()
                .name("memo.pdf")
                .contentType("application/pdf")
                .stream(
                        () -> FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getResourceAsStream("/resources/demo/prueba.pdf")
                )
                .build();
    }
    
    public List<MemosEntity> getListarMemos(){
        return getModel().listaMemos();
    }

    /**
     * @return the model
     */
    public Historial_Memos_Model getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Historial_Memos_Model model) {
        this.model = model;
    }

    /**
     * @return the filtrarMemos
     */
    public List<MemosEntity> getFiltrarMemos() {
        return filtrarMemos;
    }

    /**
     * @param filtrarMemos the filtrarMemos to set
     */
    public void setFiltrarMemos(List<MemosEntity> filtrarMemos) {
        this.filtrarMemos = filtrarMemos;
    }

    /**
     * @return the file
     */
    public StreamedContent getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
}
