package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.JefeAsignadoEntity;
import org.fusalmo.www.entities.RecursosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(AreaEntity.class)
public class AreaEntity_ { 

    public static volatile SingularAttribute<AreaEntity, JefeAsignadoEntity> idJefeAsignado;
    public static volatile SingularAttribute<AreaEntity, String> id;
    public static volatile ListAttribute<AreaEntity, EmpleadoEntity> empleadoEntityList;
    public static volatile ListAttribute<AreaEntity, RecursosEntity> recursosEntityList;
    public static volatile SingularAttribute<AreaEntity, String> nombre;

}