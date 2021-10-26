/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Soporte
 */
@Entity
@Table(name = "mantenimientos")
@NamedQueries({
    @NamedQuery(name = "MantenimientosEntity.findAll", query = "SELECT m FROM MantenimientosEntity m")
    , @NamedQuery(name = "MantenimientosEntity.findById", query = "SELECT m FROM MantenimientosEntity m WHERE m.id = :id")
    , @NamedQuery(name = "MantenimientosEntity.findByFechaRealizacion", query = "SELECT m FROM MantenimientosEntity m WHERE m.fechaRealizacion = :fechaRealizacion")
    , @NamedQuery(name = "MantenimientosEntity.findByIsDeleted", query = "SELECT m FROM MantenimientosEntity m WHERE m.isDeleted = :isDeleted")})
public class MantenimientosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Lob
    private String descripcion;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRealizacion;
    @Basic(optional = false)
    private boolean isDeleted;
    @JoinColumn(name = "IdRecurso", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private RecursosEntity idRecurso;

    public MantenimientosEntity() {
    }

    public MantenimientosEntity(String id) {
        this.id = id;
    }

    public MantenimientosEntity(String id, Date fechaRealizacion, boolean isDeleted) {
        this.id = id;
        this.fechaRealizacion = fechaRealizacion;
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public RecursosEntity getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(RecursosEntity idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MantenimientosEntity)) {
            return false;
        }
        MantenimientosEntity other = (MantenimientosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.MantenimientosEntity[ id=" + id + " ]";
    }
    
}
