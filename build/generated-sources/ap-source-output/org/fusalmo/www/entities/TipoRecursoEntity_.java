package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.RecursosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(TipoRecursoEntity.class)
public class TipoRecursoEntity_ { 

    public static volatile SingularAttribute<TipoRecursoEntity, String> descripcion;
    public static volatile SingularAttribute<TipoRecursoEntity, Integer> id;
    public static volatile ListAttribute<TipoRecursoEntity, RecursosEntity> recursosEntityList;
    public static volatile SingularAttribute<TipoRecursoEntity, String> nombre;

}