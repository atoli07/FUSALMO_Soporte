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
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "EmpleadoEntity.findAll", query = "SELECT e FROM EmpleadoEntity e")
    , @NamedQuery(name = "EmpleadoEntity.findById", query = "SELECT e FROM EmpleadoEntity e WHERE e.id = :id")
    , @NamedQuery(name = "EmpleadoEntity.findByNombres", query = "SELECT e FROM EmpleadoEntity e WHERE e.nombres = :nombres")
    , @NamedQuery(name = "EmpleadoEntity.findByApellidos", query = "SELECT e FROM EmpleadoEntity e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "EmpleadoEntity.findByFechaNacimiento", query = "SELECT e FROM EmpleadoEntity e WHERE e.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "EmpleadoEntity.findByTelefonoFijo", query = "SELECT e FROM EmpleadoEntity e WHERE e.telefonoFijo = :telefonoFijo")
    , @NamedQuery(name = "EmpleadoEntity.findByGenero", query = "SELECT e FROM EmpleadoEntity e WHERE e.genero = :genero")
    , @NamedQuery(name = "EmpleadoEntity.findByDui", query = "SELECT e FROM EmpleadoEntity e WHERE e.dui = :dui")
    , @NamedQuery(name = "EmpleadoEntity.findByCargo", query = "SELECT e FROM EmpleadoEntity e WHERE e.cargo = :cargo")
    , @NamedQuery(name = "EmpleadoEntity.findByCorreo", query = "SELECT e FROM EmpleadoEntity e WHERE e.correo = :correo")
    , @NamedQuery(name = "EmpleadoEntity.findByContra", query = "SELECT e FROM EmpleadoEntity e WHERE e.contra = :contra")
    , @NamedQuery(name = "EmpleadoEntity.findByMailPass", query = "SELECT e FROM EmpleadoEntity e WHERE e.correo = :correo AND e.contra = :contra")
    , @NamedQuery(name = "EmpleadoEntity.findByAreaAsignada", query = "SELECT e FROM EmpleadoEntity e WHERE e.idAreaAsignada.id = :idAreaAsignada")
    , @NamedQuery(name = "RecursosEntity.countAll", query = "SELECT COUNT(r.id) FROM RecursosEntity r")
    , @NamedQuery(name = "EmpleadoEntity.countAll", query = "SELECT COUNT(r.id) FROM EmpleadoEntity r")})
public class EmpleadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String id;
    @Basic(optional = false)
    private String nombres;
    @Basic(optional = false)
    private String apellidos;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String telefonoFijo;
    @Basic(optional = false)
    private Character genero;
    @Basic(optional = false)
    private String dui;
    @Basic(optional = false)
    private String cargo;
    @Basic(optional = false)
    private String correo;
    @Basic(optional = false)
    private String contra;
    @Basic(optional = false)
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<PrestamoRecursosEntity> prestamoRecursosEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<RecursosDeEmpleadosEntity> recursosDeEmpleadosEntityList;
    @JoinColumn(name = "IdAreaAsignada", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private AreaEntity idAreaAsignada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<MemosEntity> memosEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<TokensEntity> tokensEntityList;
    

    public EmpleadoEntity() {
    }

    public EmpleadoEntity(String id) {
        this.id = id;
    }

    public EmpleadoEntity(String id, String nombres, String apellidos, Date fechaNacimiento, Character genero, String dui, String cargo, String correo, String contra, boolean isDeleted) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.dui = dui;
        this.cargo = cargo;
        this.correo = correo;
        this.contra = contra;
        this.isDeleted=isDeleted;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<PrestamoRecursosEntity> getPrestamoRecursosEntityList() {
        return prestamoRecursosEntityList;
    }

    public void setPrestamoRecursosEntityList(List<PrestamoRecursosEntity> prestamoRecursosEntityList) {
        this.prestamoRecursosEntityList = prestamoRecursosEntityList;
    }

    public List<RecursosDeEmpleadosEntity> getRecursosDeEmpleadosEntityList() {
        return recursosDeEmpleadosEntityList;
    }

    public void setRecursosDeEmpleadosEntityList(List<RecursosDeEmpleadosEntity> recursosDeEmpleadosEntityList) {
        this.recursosDeEmpleadosEntityList = recursosDeEmpleadosEntityList;
    }

    public AreaEntity getIdAreaAsignada() {
        return idAreaAsignada;
    }

    public void setIdAreaAsignada(AreaEntity idAreaAsignada) {
        this.idAreaAsignada = idAreaAsignada;
    }

    public List<MemosEntity> getMemosEntityList() {
        return memosEntityList;
    }

    public void setMemosEntityList(List<MemosEntity> memosEntityList) {
        this.memosEntityList = memosEntityList;
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
        if (!(object instanceof EmpleadoEntity)) {
            return false;
        }
        EmpleadoEntity other = (EmpleadoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.EmpleadoEntity[ id=" + id + " ]";
    }

   /** public TipoRecursoEntity getIdTipoEmpleado() {
       
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(TipoRecursoEntity idTipoRecurso) {
        this.idTipoEmpleado = idTipoRecurso;
    } */
    
}
