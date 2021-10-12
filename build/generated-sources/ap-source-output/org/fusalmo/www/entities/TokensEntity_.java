package org.fusalmo.www.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.EstadoTokenEntity;
import org.fusalmo.www.entities.RecursosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(TokensEntity.class)
public class TokensEntity_ { 

    public static volatile SingularAttribute<TokensEntity, String> descripcion;
    public static volatile SingularAttribute<TokensEntity, Date> fecha;
    public static volatile SingularAttribute<TokensEntity, RecursosEntity> seleccionRecurso;
    public static volatile SingularAttribute<TokensEntity, EstadoTokenEntity> idEstado;
    public static volatile SingularAttribute<TokensEntity, EmpleadoEntity> idEmpleado;
    public static volatile SingularAttribute<TokensEntity, String> id;
    public static volatile SingularAttribute<TokensEntity, String> prioridad;

}