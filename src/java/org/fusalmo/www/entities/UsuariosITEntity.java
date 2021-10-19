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
@Table(name = "usuariosit")
@NamedQueries({
    @NamedQuery(name = "UsuariosITEntity.findAll", query = "SELECT u FROM UsuariosITEntity u")
    , @NamedQuery(name = "UsuariosITEntity.findById", query = "SELECT u FROM UsuariosITEntity u WHERE u.id = :id")
    , @NamedQuery(name = "UsuariosITEntity.findByNombres", query = "SELECT u FROM UsuariosITEntity u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "UsuariosITEntity.findByApellidos", query = "SELECT u FROM UsuariosITEntity u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "UsuariosITEntity.findByFechaNacimiento", query = "SELECT u FROM UsuariosITEntity u WHERE u.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "UsuariosITEntity.findByGenero", query = "SELECT u FROM UsuariosITEntity u WHERE u.genero = :genero")
    , @NamedQuery(name = "UsuariosITEntity.findByDui", query = "SELECT u FROM UsuariosITEntity u WHERE u.dui = :dui")
    , @NamedQuery(name = "UsuariosITEntity.findByCorreo", query = "SELECT u FROM UsuariosITEntity u WHERE u.correo = :correo")
    , @NamedQuery(name = "UsuariosITEntity.findByContra", query = "SELECT u FROM UsuariosITEntity u WHERE u.contra = :contra")})
public class UsuariosITEntity implements Serializable {

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
    @Basic(optional = false)
    private Character genero;
    @Basic(optional = false)
    private String dui;
    @Basic(optional = false)
    private String correo;
    @Basic(optional = false)
    private String contra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgenteIT")
    private List<MemosEntity> memosEntityList;

    public UsuariosITEntity() {
    }

    public UsuariosITEntity(String id) {
        this.id = id;
    }

    public UsuariosITEntity(String id, String nombres, String apellidos, Date fechaNacimiento, Character genero, String dui, String correo, String contra) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.dui = dui;
        this.correo = correo;
        this.contra = contra;
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

    public List<MemosEntity> getMemosEntityList() {
        return memosEntityList;
    }

    public void setMemosEntityList(List<MemosEntity> memosEntityList) {
        this.memosEntityList = memosEntityList;
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
        if (!(object instanceof UsuariosITEntity)) {
            return false;
        }
        UsuariosITEntity other = (UsuariosITEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fusalmo.www.entities.UsuariosITEntity[ id=" + id + " ]";
    }
    
}
