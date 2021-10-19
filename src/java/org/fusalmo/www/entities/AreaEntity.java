/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Soporte
 */
@Entity
@Table(name = "area")
@NamedQueries({
    @NamedQuery(name = "AreaEntity.findAll", query = "SELECT a FROM AreaEntity a")
    , @NamedQuery(name = "AreaEntity.findById", query = "SELECT a FROM AreaEntity a WHERE a.id = :id")
    , @NamedQuery(name = "AreaEntity.findByNombre", query = "SELECT a FROM AreaEntity a WHERE a.nombre = :nombre")})
public class AreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    private String nombre;
    @JoinColumn(name = "IdJefeAsignado", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private JefeAsignadoEntity idJefeAsignado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaAsignada")
    private List<EmpleadoEntity> empleadoEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaAsignada")
    private List<RecursosEntity> recursosEntityList;

    public AreaEntity() {
    }

    public AreaEntity(String id) {
        this.id = id;
    }

    public AreaEntity(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public JefeAsignadoEntity getIdJefeAsignado() {
        return idJefeAsignado;
    }

    public void setIdJefeAsignado(JefeAsignadoEntity idJefeAsignado) {
        this.idJefeAsignado = idJefeAsignado;
    }

    public List<EmpleadoEntity> getEmpleadoEntityList() {
        return empleadoEntityList;
    }

    public void setEmpleadoEntityList(List<EmpleadoEntity> empleadoEntityList) {
        this.empleadoEntityList = empleadoEntityList;
    }

    public List<RecursosEntity> getRecursosEntityList() {
        return recursosEntityList;
    }

    public void setRecursosEntityList(List<RecursosEntity> recursosEntityList) {
        this.recursosEntityList = recursosEntityList;
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
        if (!(object instanceof AreaEntity)) {
            return false;
        }
        AreaEntity other = (AreaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.AreaEntity[ id=" + id + " ]";
    }
    
}
