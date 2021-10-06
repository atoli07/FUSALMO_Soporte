/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Soporte
 */
@Entity
@Table(name = "memos")
@NamedQueries({
    @NamedQuery(name = "MemosEntity.findAll", query = "SELECT m FROM MemosEntity m")
    , @NamedQuery(name = "MemosEntity.findById", query = "SELECT m FROM MemosEntity m WHERE m.id = :id")
    , @NamedQuery(name = "MemosEntity.findByFechaPrestamo", query = "SELECT m FROM MemosEntity m WHERE m.fechaPrestamo = :fechaPrestamo")
    , @NamedQuery(name = "MemosEntity.findByFechaEntrega", query = "SELECT m FROM MemosEntity m WHERE m.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "MemosEntity.findByCantidadRecursos", query = "SELECT m FROM MemosEntity m WHERE m.cantidadRecursos = :cantidadRecursos")})
public class MemosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    @Lob
    private String asunto;
    @Lob
    private String pdf;
    @Lob
    private String pDFFirmado;
    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    private Integer cantidadRecursos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMemo")
    private List<PrestamoRecursosEntity> prestamoRecursosEntityList;
    @JoinColumn(name = "IdEmpleado", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private EmpleadoEntity idEmpleado;
    @JoinColumn(name = "IdTipo", referencedColumnName = "IdTipo")
    @ManyToOne(optional = false)
    private TipoMemoEntity idTipo;
    @JoinColumn(name = "IdAgenteIT", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private UsuariosITEntity idAgenteIT;

    public MemosEntity() {
    }

    public MemosEntity(String id) {
        this.id = id;
    }

    public MemosEntity(String id, String asunto) {
        this.id = id;
        this.asunto = asunto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getPDFFirmado() {
        return pDFFirmado;
    }

    public void setPDFFirmado(String pDFFirmado) {
        this.pDFFirmado = pDFFirmado;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCantidadRecursos() {
        return cantidadRecursos;
    }

    public void setCantidadRecursos(Integer cantidadRecursos) {
        this.cantidadRecursos = cantidadRecursos;
    }

    public List<PrestamoRecursosEntity> getPrestamoRecursosEntityList() {
        return prestamoRecursosEntityList;
    }

    public void setPrestamoRecursosEntityList(List<PrestamoRecursosEntity> prestamoRecursosEntityList) {
        this.prestamoRecursosEntityList = prestamoRecursosEntityList;
    }

    public EmpleadoEntity getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(EmpleadoEntity idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TipoMemoEntity getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoMemoEntity idTipo) {
        this.idTipo = idTipo;
    }

    public UsuariosITEntity getIdAgenteIT() {
        return idAgenteIT;
    }

    public void setIdAgenteIT(UsuariosITEntity idAgenteIT) {
        this.idAgenteIT = idAgenteIT;
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
        if (!(object instanceof MemosEntity)) {
            return false;
        }
        MemosEntity other = (MemosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.MemosEntity[ id=" + id + " ]";
    }
    
}
