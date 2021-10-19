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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Soporte
 */
@Entity
@Table(name = "tiporecurso")
@NamedQueries({
    @NamedQuery(name = "TipoRecursoEntity.findAll", query = "SELECT t FROM TipoRecursoEntity t")
    , @NamedQuery(name = "TipoRecursoEntity.findById", query = "SELECT t FROM TipoRecursoEntity t WHERE t.id = :id")
    , @NamedQuery(name = "TipoRecursoEntity.findByNombre", query = "SELECT t FROM TipoRecursoEntity t WHERE t.nombre = :nombre")})
public class TipoRecursoEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    @Lob
    private String descripcion;
    private String img;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRecurso")
    private List<RecursosEntity> recursosEntityList;

    public TipoRecursoEntity() {
    }
    
    public TipoRecursoEntity(Integer id) {
        this.id = id;
    }
    
    public TipoRecursoEntity(Integer id, String nombre, String descripcion) {
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
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        if (!(object instanceof TipoRecursoEntity)) {
            return false;
        }
        TipoRecursoEntity other = (TipoRecursoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.TipoRecursoEntity[ id=" + id + " ]";
    }

    public TipoRecursoEntity(Integer id, String nombre, String descripcion, String img) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img = img;
    }
}
