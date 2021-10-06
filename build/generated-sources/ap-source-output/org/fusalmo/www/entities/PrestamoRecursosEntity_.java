package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.entities.RecursosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T15:59:20")
@StaticMetamodel(PrestamoRecursosEntity.class)
public class PrestamoRecursosEntity_ { 

    public static volatile SingularAttribute<PrestamoRecursosEntity, EmpleadoEntity> idEmpleado;
    public static volatile SingularAttribute<PrestamoRecursosEntity, MemosEntity> idMemo;
    public static volatile SingularAttribute<PrestamoRecursosEntity, RecursosEntity> idRecurso;
    public static volatile SingularAttribute<PrestamoRecursosEntity, Integer> idPrestamo;

}