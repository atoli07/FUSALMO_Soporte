package org.fusalmo.www.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TokensEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(EmpleadoEntity.class)
public class EmpleadoEntity_ { 

    public static volatile SingularAttribute<EmpleadoEntity, String> apellidos;
    public static volatile ListAttribute<EmpleadoEntity, TokensEntity> tokensEntityList;
    public static volatile SingularAttribute<EmpleadoEntity, Date> fechaNacimiento;
    public static volatile SingularAttribute<EmpleadoEntity, RecursosEntity> idRecurso;
    public static volatile SingularAttribute<EmpleadoEntity, AreaEntity> idAreaAsignada;
    public static volatile SingularAttribute<EmpleadoEntity, String> nombres;
    public static volatile SingularAttribute<EmpleadoEntity, String> telefonoFijo;
    public static volatile SingularAttribute<EmpleadoEntity, Character> genero;
    public static volatile SingularAttribute<EmpleadoEntity, String> correo;
    public static volatile ListAttribute<EmpleadoEntity, MemosEntity> memosEntityList;
    public static volatile SingularAttribute<EmpleadoEntity, String> dui;
    public static volatile SingularAttribute<EmpleadoEntity, String> id;
    public static volatile SingularAttribute<EmpleadoEntity, String> cargo;
    public static volatile SingularAttribute<EmpleadoEntity, String> contra;

}