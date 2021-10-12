package org.fusalmo.www.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.entities.RecursosEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-07T20:33:21")
@StaticMetamodel(RemocionMemoEntity.class)
public class RemocionMemoEntity_ { 

    public static volatile SingularAttribute<RemocionMemoEntity, RecursosEntity> idRecurso;
    public static volatile ListAttribute<RemocionMemoEntity, MemosEntity> memosEntityList;
    public static volatile SingularAttribute<RemocionMemoEntity, String> id;

}