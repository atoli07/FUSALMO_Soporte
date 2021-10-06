package org.fusalmo.www.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.PrestamoRecursosEntity;
import org.fusalmo.www.entities.TipoMemoEntity;
import org.fusalmo.www.entities.UsuariosITEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T15:59:20")
@StaticMetamodel(MemosEntity.class)
public class MemosEntity_ { 

    public static volatile SingularAttribute<MemosEntity, UsuariosITEntity> idAgenteIT;
    public static volatile SingularAttribute<MemosEntity, String> pdf;
    public static volatile SingularAttribute<MemosEntity, Date> fechaPrestamo;
    public static volatile SingularAttribute<MemosEntity, EmpleadoEntity> idEmpleado;
    public static volatile SingularAttribute<MemosEntity, String> asunto;
    public static volatile SingularAttribute<MemosEntity, String> pDFFirmado;
    public static volatile ListAttribute<MemosEntity, PrestamoRecursosEntity> prestamoRecursosEntityList;
    public static volatile SingularAttribute<MemosEntity, Date> fechaEntrega;
    public static volatile SingularAttribute<MemosEntity, Integer> cantidadRecursos;
    public static volatile SingularAttribute<MemosEntity, TipoMemoEntity> idTipo;
    public static volatile SingularAttribute<MemosEntity, String> id;

}