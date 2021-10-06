package org.fusalmo.www.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.RecursosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T15:59:20")
@StaticMetamodel(MantenimientosEntity.class)
public class MantenimientosEntity_ { 

    public static volatile SingularAttribute<MantenimientosEntity, String> descripcion;
    public static volatile SingularAttribute<MantenimientosEntity, Date> fechaRealizacion;
    public static volatile SingularAttribute<MantenimientosEntity, RecursosEntity> idRecurso;
    public static volatile SingularAttribute<MantenimientosEntity, String> id;

}