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
@Table(name = "tokens")
@NamedQueries({
    @NamedQuery(name = "TokensEntity.findAll", query = "SELECT t FROM TokensEntity t")
    , @NamedQuery(name = "TokensEntity.findById", query = "SELECT t FROM TokensEntity t WHERE t.id = :id")
    , @NamedQuery(name = "TokensEntity.findByFecha", query = "SELECT t FROM TokensEntity t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TokensEntity.findByPrioridad", query = "SELECT t FROM TokensEntity t WHERE t.prioridad = :prioridad")})
public class TokensEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    @Lob
    private String descripcion;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    private String prioridad;
    @JoinColumn(name = "IdEmpleado", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private EmpleadoEntity idEmpleado;
    @JoinColumn(name = "IdEstado", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private EstadoTokenEntity idEstado;
    @JoinColumn(name = "SeleccionRecurso", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private RecursosEntity seleccionRecurso;

    public TokensEntity() {
    }

    public TokensEntity(String id) {
        this.id = id;
    }

    public TokensEntity(String id, String descripcion, Date fecha, String prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public EmpleadoEntity getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(EmpleadoEntity idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public EstadoTokenEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoTokenEntity idEstado) {
        this.idEstado = idEstado;
    }

    public RecursosEntity getSeleccionRecurso() {
        return seleccionRecurso;
    }

    public void setSeleccionRecurso(RecursosEntity seleccionRecurso) {
        this.seleccionRecurso = seleccionRecurso;
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
        if (!(object instanceof TokensEntity)) {
            return false;
        }
        TokensEntity other = (TokensEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.TokensEntity[ id=" + id + " ]";
    }
    
}
