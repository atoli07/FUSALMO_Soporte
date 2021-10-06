package org.fusalmo.www.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.MemosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T15:59:20")
@StaticMetamodel(UsuariosITEntity.class)
public class UsuariosITEntity_ { 

    public static volatile SingularAttribute<UsuariosITEntity, String> apellidos;
    public static volatile SingularAttribute<UsuariosITEntity, Date> fechaNacimiento;
    public static volatile SingularAttribute<UsuariosITEntity, Character> genero;
    public static volatile SingularAttribute<UsuariosITEntity, String> correo;
    public static volatile ListAttribute<UsuariosITEntity, MemosEntity> memosEntityList;
    public static volatile SingularAttribute<UsuariosITEntity, String> dui;
    public static volatile SingularAttribute<UsuariosITEntity, String> id;
    public static volatile SingularAttribute<UsuariosITEntity, String> contra;
    public static volatile SingularAttribute<UsuariosITEntity, String> nombres;

}