/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Soporte
 */
@Entity
@Table(name = "prestamorecursos")
@NamedQueries({
    @NamedQuery(name = "PrestamoRecursosEntity.findAll", query = "SELECT p FROM PrestamoRecursosEntity p")
    , @NamedQuery(name = "PrestamoRecursosEntity.findByIdPrestamo", query = "SELECT p FROM PrestamoRecursosEntity p WHERE p.idPrestamo = :idPrestamo")})
public class PrestamoRecursosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idPrestamo;
    @JoinColumn(name = "IdEmpleado", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private EmpleadoEntity idEmpleado;
    @JoinColumn(name = "IdMemo", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private MemosEntity idMemo;
    @JoinColumn(name = "IdRecurso", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private RecursosEntity idRecurso;

    public PrestamoRecursosEntity() {
    }

    public PrestamoRecursosEntity(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public EmpleadoEntity getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(EmpleadoEntity idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public MemosEntity getIdMemo() {
        return idMemo;
    }

    public void setIdMemo(MemosEntity idMemo) {
        this.idMemo = idMemo;
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
        hash += (idPrestamo != null ? idPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoRecursosEntity)) {
            return false;
        }
        PrestamoRecursosEntity other = (PrestamoRecursosEntity) object;
        if ((this.idPrestamo == null && other.idPrestamo != null) || (this.idPrestamo != null && !this.idPrestamo.equals(other.idPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.PrestamoRecursosEntity[ idPrestamo=" + idPrestamo + " ]";
    }
    
}
