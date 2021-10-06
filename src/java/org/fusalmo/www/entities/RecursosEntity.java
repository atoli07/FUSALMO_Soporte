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
import javax.persistence.Lob;
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
@Table(name = "recursos")
@NamedQueries({
    @NamedQuery(name = "RecursosEntity.findAll", query = "SELECT r FROM RecursosEntity r")
    , @NamedQuery(name = "RecursosEntity.findById", query = "SELECT r FROM RecursosEntity r WHERE r.id = :id")
    , @NamedQuery(name = "RecursosEntity.findByNombre", query = "SELECT r FROM RecursosEntity r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RecursosEntity.findByMarca", query = "SELECT r FROM RecursosEntity r WHERE r.marca = :marca")
    , @NamedQuery(name = "RecursosEntity.findByModelo", query = "SELECT r FROM RecursosEntity r WHERE r.modelo = :modelo")
    , @NamedQuery(name = "RecursosEntity.findByNumSerie", query = "SELECT r FROM RecursosEntity r WHERE r.numSerie = :numSerie")
    , @NamedQuery(name = "RecursosEntity.findByDireccionIP", query = "SELECT r FROM RecursosEntity r WHERE r.direccionIP = :direccionIP")
    , @NamedQuery(name = "RecursosEntity.findByDireccionMAC", query = "SELECT r FROM RecursosEntity r WHERE r.direccionMAC = :direccionMAC")
    , @NamedQuery(name = "RecursosEntity.findByCargador", query = "SELECT r FROM RecursosEntity r WHERE r.cargador = :cargador")
    , @NamedQuery(name = "RecursosEntity.findByCodActivo", query = "SELECT r FROM RecursosEntity r WHERE r.codActivo = :codActivo")})
public class RecursosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    private String marca;
    @Basic(optional = false)
    private String modelo;
    @Basic(optional = false)
    private String numSerie;
    private String direccionIP;
    private String direccionMAC;
    @Basic(optional = false)
    private boolean cargador;
    @Basic(optional = false)
    private String codActivo;
    @Basic(optional = false)
    @Lob
    private String imagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecurso")
    private List<MantenimientosEntity> mantenimientosEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecurso")
    private List<EmpleadoEntity> empleadoEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecurso")
    private List<PrestamoRecursosEntity> prestamoRecursosEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seleccionRecurso")
    private List<TokensEntity> tokensEntityList;
    @JoinColumn(name = "IdAreaAsignada", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private AreaEntity idAreaAsignada;
    @JoinColumn(name = "IdTipoRecurso", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private TipoRecursoEntity idTipoRecurso;

    public RecursosEntity() {
    }

    public RecursosEntity(String id) {
        this.id = id;
    }

    public RecursosEntity(String id, String nombre, String marca, String modelo, String numSerie, boolean cargador, String codActivo, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.numSerie = numSerie;
        this.cargador = cargador;
        this.codActivo = codActivo;
        this.imagen = imagen;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getDireccionMAC() {
        return direccionMAC;
    }

    public void setDireccionMAC(String direccionMAC) {
        this.direccionMAC = direccionMAC;
    }

    public boolean getCargador() {
        return cargador;
    }

    public void setCargador(boolean cargador) {
        this.cargador = cargador;
    }

    public String getCodActivo() {
        return codActivo;
    }

    public void setCodActivo(String codActivo) {
        this.codActivo = codActivo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<MantenimientosEntity> getMantenimientosEntityList() {
        return mantenimientosEntityList;
    }

    public void setMantenimientosEntityList(List<MantenimientosEntity> mantenimientosEntityList) {
        this.mantenimientosEntityList = mantenimientosEntityList;
    }

    public List<EmpleadoEntity> getEmpleadoEntityList() {
        return empleadoEntityList;
    }

    public void setEmpleadoEntityList(List<EmpleadoEntity> empleadoEntityList) {
        this.empleadoEntityList = empleadoEntityList;
    }

    public List<PrestamoRecursosEntity> getPrestamoRecursosEntityList() {
        return prestamoRecursosEntityList;
    }

    public void setPrestamoRecursosEntityList(List<PrestamoRecursosEntity> prestamoRecursosEntityList) {
        this.prestamoRecursosEntityList = prestamoRecursosEntityList;
    }

    public List<TokensEntity> getTokensEntityList() {
        return tokensEntityList;
    }

    public void setTokensEntityList(List<TokensEntity> tokensEntityList) {
        this.tokensEntityList = tokensEntityList;
    }

    public AreaEntity getIdAreaAsignada() {
        return idAreaAsignada;
    }

    public void setIdAreaAsignada(AreaEntity idAreaAsignada) {
        this.idAreaAsignada = idAreaAsignada;
    }

    public TipoRecursoEntity getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(TipoRecursoEntity idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
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
        if (!(object instanceof RecursosEntity)) {
            return false;
        }
        RecursosEntity other = (RecursosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.RecursosEntity[ id=" + id + " ]";
    }
    
}
