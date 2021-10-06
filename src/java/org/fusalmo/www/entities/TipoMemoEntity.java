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
@Table(name = "tipomemo")
@NamedQueries({
    @NamedQuery(name = "TipoMemoEntity.findAll", query = "SELECT t FROM TipoMemoEntity t")
    , @NamedQuery(name = "TipoMemoEntity.findByIdTipo", query = "SELECT t FROM TipoMemoEntity t WHERE t.idTipo = :idTipo")
    , @NamedQuery(name = "TipoMemoEntity.findByNombre", query = "SELECT t FROM TipoMemoEntity t WHERE t.nombre = :nombre")})
public class TipoMemoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String idTipo;
    @Basic(optional = false)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private List<MemosEntity> memosEntityList;

    public TipoMemoEntity() {
    }

    public TipoMemoEntity(String idTipo) {
        this.idTipo = idTipo;
    }

    public TipoMemoEntity(String idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MemosEntity> getMemosEntityList() {
        return memosEntityList;
    }

    public void setMemosEntityList(List<MemosEntity> memosEntityList) {
        this.memosEntityList = memosEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMemoEntity)) {
            return false;
        }
        TipoMemoEntity other = (TipoMemoEntity) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.TipoMemoEntity[ idTipo=" + idTipo + " ]";
    }
    
}
