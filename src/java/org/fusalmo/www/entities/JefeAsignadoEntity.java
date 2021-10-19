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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Soporte
 */
@Entity
@Table(name = "jefeasignado")
@NamedQueries({
    @NamedQuery(name = "JefeAsignadoEntity.findAll", query = "SELECT j FROM JefeAsignadoEntity j")
    , @NamedQuery(name = "JefeAsignadoEntity.findById", query = "SELECT j FROM JefeAsignadoEntity j WHERE j.id = :id")
    , @NamedQuery(name = "JefeAsignadoEntity.findByNombres", query = "SELECT j FROM JefeAsignadoEntity j WHERE j.nombres = :nombres")
    , @NamedQuery(name = "JefeAsignadoEntity.findByApellidos", query = "SELECT j FROM JefeAsignadoEntity j WHERE j.apellidos = :apellidos")})
public class JefeAsignadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    private String nombres;
    @Basic(optional = false)
    private String apellidos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJefeAsignado")
    private List<AreaEntity> areaEntityList;

    public JefeAsignadoEntity() {
    }

    public JefeAsignadoEntity(String id) {
        this.id = id;
    }

    public JefeAsignadoEntity(String id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<AreaEntity> getAreaEntityList() {
        return areaEntityList;
    }

    public void setAreaEntityList(List<AreaEntity> areaEntityList) {
        this.areaEntityList = areaEntityList;
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
        if (!(object instanceof JefeAsignadoEntity)) {
            return false;
        }
        JefeAsignadoEntity other = (JefeAsignadoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.JefeAsignadoEntity[ id=" + id + " ]";
    }
    
}
