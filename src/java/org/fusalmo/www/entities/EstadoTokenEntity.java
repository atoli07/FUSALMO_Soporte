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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "estadotoken")
@NamedQueries({
    @NamedQuery(name = "EstadoTokenEntity.findAll", query = "SELECT e FROM EstadoTokenEntity e")
    , @NamedQuery(name = "EstadoTokenEntity.findById", query = "SELECT e FROM EstadoTokenEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EstadoTokenEntity.findByNombre", query = "SELECT e FROM EstadoTokenEntity e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EstadoTokenEntity.findByDescripcion", query = "SELECT e FROM EstadoTokenEntity e WHERE e.descripcion = :descripcion")})
public class EstadoTokenEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private List<TokensEntity> tokensEntityList;

    public EstadoTokenEntity() {
    }

    public EstadoTokenEntity(Integer id) {
        this.id = id;
    }

    public EstadoTokenEntity(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<TokensEntity> getTokensEntityList() {
        return tokensEntityList;
    }

    public void setTokensEntityList(List<TokensEntity> tokensEntityList) {
        this.tokensEntityList = tokensEntityList;
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
        if (!(object instanceof EstadoTokenEntity)) {
            return false;
        }
        EstadoTokenEntity other = (EstadoTokenEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.EstadoTokenEntity[ id=" + id + " ]";
    }
    
}
