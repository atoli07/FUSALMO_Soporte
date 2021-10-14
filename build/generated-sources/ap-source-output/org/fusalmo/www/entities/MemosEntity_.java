package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.AsignacionMemoEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.PrestamoExternoMemoEntity;
import org.fusalmo.www.entities.PrestamoInternoMemoEntity;
import org.fusalmo.www.entities.RemocionMemoEntity;
import org.fusalmo.www.entities.UsuariosITEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(MemosEntity.class)
public class MemosEntity_ { 

    public static volatile SingularAttribute<MemosEntity, UsuariosITEntity> idAgenteIT;
    public static volatile SingularAttribute<MemosEntity, String> tipo;
    public static volatile SingularAttribute<MemosEntity, String> pdf;
    public static volatile SingularAttribute<MemosEntity, EmpleadoEntity> idEmpleado;
    public static volatile SingularAttribute<MemosEntity, String> asunto;
    public static volatile SingularAttribute<MemosEntity, String> pDFFirmado;
    public static volatile SingularAttribute<MemosEntity, RemocionMemoEntity> idRemocionMemo;
    public static volatile SingularAttribute<MemosEntity, PrestamoInternoMemoEntity> idPrestamoInternoMemo;
    public static volatile SingularAttribute<MemosEntity, String> id;
    public static volatile SingularAttribute<MemosEntity, PrestamoExternoMemoEntity> idPrestamoExternoMemo;
    public static volatile SingularAttribute<MemosEntity, AsignacionMemoEntity> idAsignacionMemo;

}