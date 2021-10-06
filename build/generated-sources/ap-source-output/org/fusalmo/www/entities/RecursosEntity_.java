package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MantenimientosEntity;
import org.fusalmo.www.entities.PrestamoRecursosEntity;
import org.fusalmo.www.entities.TipoRecursoEntity;
import org.fusalmo.www.entities.TokensEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T15:59:20")
@StaticMetamodel(RecursosEntity.class)
public class RecursosEntity_ { 

    public static volatile SingularAttribute<RecursosEntity, String> direccionMAC;
    public static volatile ListAttribute<RecursosEntity, TokensEntity> tokensEntityList;
    public static volatile ListAttribute<RecursosEntity, MantenimientosEntity> mantenimientosEntityList;
    public static volatile ListAttribute<RecursosEntity, PrestamoRecursosEntity> prestamoRecursosEntityList;
    public static volatile SingularAttribute<RecursosEntity, String> imagen;
    public static volatile SingularAttribute<RecursosEntity, Boolean> cargador;
    public static volatile ListAttribute<RecursosEntity, EmpleadoEntity> empleadoEntityList;
    public static volatile SingularAttribute<RecursosEntity, String> nombre;
    public static volatile SingularAttribute<RecursosEntity, String> modelo;
    public static volatile SingularAttribute<RecursosEntity, String> numSerie;
    public static volatile SingularAttribute<RecursosEntity, AreaEntity> idAreaAsignada;
    public static volatile SingularAttribute<RecursosEntity, String> direccionIP;
    public static volatile SingularAttribute<RecursosEntity, String> marca;
    public static volatile SingularAttribute<RecursosEntity, String> codActivo;
    public static volatile SingularAttribute<RecursosEntity, String> id;
    public static volatile SingularAttribute<RecursosEntity, TipoRecursoEntity> idTipoRecurso;

}