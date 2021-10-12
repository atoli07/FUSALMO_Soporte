package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.AsignacionMemoEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.MantenimientosEntity;
import org.fusalmo.www.entities.PrestamoExternoMemoEntity;
import org.fusalmo.www.entities.PrestamoInternoMemoEntity;
import org.fusalmo.www.entities.RemocionMemoEntity;
import org.fusalmo.www.entities.TipoRecursoEntity;
import org.fusalmo.www.entities.TokensEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(RecursosEntity.class)
public class RecursosEntity_ { 

    public static volatile SingularAttribute<RecursosEntity, String> direccionMAC;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList7;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList8;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList5;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList6;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList3;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList4;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList1;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList2;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList19;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList18;
    public static volatile SingularAttribute<RecursosEntity, AreaEntity> idAreaAsignada;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList;
    public static volatile SingularAttribute<RecursosEntity, String> marca;
    public static volatile SingularAttribute<RecursosEntity, String> id;
    public static volatile ListAttribute<RecursosEntity, RemocionMemoEntity> remocionMemoEntityList;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList1;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList2;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList3;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList4;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList5;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList6;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList7;
    public static volatile ListAttribute<RecursosEntity, EmpleadoEntity> empleadoEntityList;
    public static volatile SingularAttribute<RecursosEntity, String> modelo;
    public static volatile SingularAttribute<RecursosEntity, String> numSerie;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList8;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList9;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList11;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList10;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList13;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList12;
    public static volatile SingularAttribute<RecursosEntity, String> codActivo;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList15;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList14;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList17;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList16;
    public static volatile ListAttribute<RecursosEntity, PrestamoInternoMemoEntity> prestamoInternoMemoEntityList9;
    public static volatile ListAttribute<RecursosEntity, TokensEntity> tokensEntityList;
    public static volatile ListAttribute<RecursosEntity, MantenimientosEntity> mantenimientosEntityList;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList16;
    public static volatile SingularAttribute<RecursosEntity, String> nombre;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList17;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList18;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList19;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList12;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList13;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList14;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList15;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList10;
    public static volatile ListAttribute<RecursosEntity, PrestamoExternoMemoEntity> prestamoExternoMemoEntityList11;
    public static volatile SingularAttribute<RecursosEntity, Boolean> cargador;
    public static volatile SingularAttribute<RecursosEntity, String> direccionIP;
    public static volatile ListAttribute<RecursosEntity, AsignacionMemoEntity> asignacionMemoEntityList;
    public static volatile SingularAttribute<RecursosEntity, TipoRecursoEntity> idTipoRecurso;

}