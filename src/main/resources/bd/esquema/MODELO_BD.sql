/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     28-04-2017 0:28:00                           */
/*==============================================================*/
CREATE SCHEMA PROVEEDOR
  AUTHORIZATION bennder;
  
drop table if exists PROVEEDOR.PLANTILLA_CORREO;

drop table if exists PROVEEDOR.PARAMETRO_SISTEMA;  

drop table if exists PROVEEDOR.ACCESO_USUARIO;

drop table if exists PROVEEDOR.BENEFICIO;

drop table if exists PROVEEDOR.BENEFICIO_DESCUENTO;

drop table if exists PROVEEDOR.BENEFICIO_GANCHO;

drop table if exists PROVEEDOR.BENEFICIO_IMAGEN;

drop table if exists PROVEEDOR.BENEFICIO_PRODUCTO;

drop table if exists PROVEEDOR.CATEGORIA;

drop table if exists PROVEEDOR.COMUNA;

drop table if exists PROVEEDOR.CONDICION_BENEFICIO;

drop table if exists PROVEEDOR.CONTACTO;

drop table if exists PROVEEDOR.DIRECCION;


drop table if exists PROVEEDOR.PAIS;

drop table if exists PROVEEDOR.PERFIL;

drop table if exists PROVEEDOR.PERFIL_USUARIO;

drop table if exists PROVEEDOR.PERMISO;

drop table if exists PROVEEDOR.PERMISO_PERFIL;

drop table if exists PROVEEDOR.PROVEEDOR;

drop table if exists PROVEEDOR.REGION;

drop table if exists PROVEEDOR.SUCURSAL_BENEFICIO;

drop table if exists PROVEEDOR.SUCURSAL_PROVEEDOR;

drop table if exists PROVEEDOR.TIPO_BENEFICIO;

drop table if exists PROVEEDOR.USUARIO;

drop table if exists PROVEEDOR.USUARIO_PROVEEDOR;



/*==============================================================*/
/* Table: ACCESO_USUARIO                                        */
/*==============================================================*/
create table PROVEEDOR.ACCESO_USUARIO (
   ID_USUARIO           INT4                 null,
   FECHA_ACCESO         DATE                 not null default CURRENT_TIMESTAMP
);

/*==============================================================*/
/* Table: BENEFICIO                                             */
/*==============================================================*/
create table PROVEEDOR.BENEFICIO (
   ID_BENEFICIO         SERIAL               not null,
   ID_TIPO_BENEFICIO    INT4                 null,
   ID_PROVEEDOR         INT4                 null,
   ID_CATEGORIA         INT4                 null,
   TITULO               VARCHAR(150)          not null,
   DESCRIPCION          VARCHAR(1000)        not null,
   FECHA_CREACION       date                 not null default CURRENT_TIMESTAMP,
   FECHA_EXPIRACION     date                 not null default CURRENT_TIMESTAMP,
   HABILITADO           BOOL                 not null,
   CALIFICACION         INT4                 not null,
   STOCK                INT4                 not null,
   LIMITE_STOCK         INT4                 null,
   VISITAS_GENERAL      INT4                 null,
   constraint PK_BENEFICIO primary key (ID_BENEFICIO)
);

/*==============================================================*/
/* Table: BENEFICIO_DESCUENTO                                   */
/*==============================================================*/
create table PROVEEDOR.BENEFICIO_DESCUENTO (
   ID_BENEFICIO         INT4                 not null,
   PORCENTAJE_DESCUENTO INT4                 null,
   constraint PK_BENEFICIO_DESCUENTO primary key (ID_BENEFICIO)
);

/*==============================================================*/
/* Table: BENEFICIO_GANCHO                                      */
/*==============================================================*/
create table PROVEEDOR.BENEFICIO_GANCHO (
   ID_BENEFICIO         INT4                 not null,
   GANCHO               VARCHAR(200)         null,
   constraint PK_BENEFICIO_GANCHO primary key (ID_BENEFICIO)
);

/*==============================================================*/
/* Table: BENEFICIO_IMAGEN                                      */
/*==============================================================*/
create table PROVEEDOR.BENEFICIO_IMAGEN (
   ID_IMAGEN            SERIAL               not null,
   ID_BENEFICIO         INT4                 null,
   NOMBRE               VARCHAR(50)          not null,
   PATH                 VARCHAR(200)         not null,
   ORDEN                INT4                 not null,
   constraint PK_BENEFICIO_IMAGEN primary key (ID_IMAGEN)
);

/*==============================================================*/
/* Table: BENEFICIO_PRODUCTO                                    */
/*==============================================================*/
create table PROVEEDOR.BENEFICIO_PRODUCTO (
   ID_BENEFICIO         INT4                 not null,
   PRECIO_NORMAL        INT4                 null,
   PRECIO_OFERTA        INT4                 null,
   constraint PK_BENEFICIO_PRODUCTO primary key (ID_BENEFICIO)
);

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table PROVEEDOR.CATEGORIA (
   ID_CATEGORIA         INT4                 not null,
   ID_CATEGORIA_PADRE   INT4                 not null default -1,
   NOMBRE               VARCHAR(50)          not null,
   DESCRIPCION          VARCHAR(500)         not null,
   constraint PK_CATEGORIA primary key (ID_CATEGORIA)
);

/*==============================================================*/
/* Table: COMUNA                                                */
/*==============================================================*/
create table PROVEEDOR.COMUNA (
   ID_COMUNA            INT4                 not null,
   ID_REGION            INT4                 null,
   NOMBRE               VARCHAR(50)          not null,
   constraint PK_COMUNA primary key (ID_COMUNA)
);

/*==============================================================*/
/* Table: CONDICION_BENEFICIO                                   */
/*==============================================================*/
create table PROVEEDOR.CONDICION_BENEFICIO (
   ID_BENEFICIO         INT4                 null,
   CONDICION            VARCHAR(100)         null
);

/*==============================================================*/
/* Table: CONTACTO                                              */
/*==============================================================*/
create table PROVEEDOR.CONTACTO (
   ID_CONTACTO          SERIAL               not null,
   CELULAR              INT4                 null,
   TELEFONO_FIJO        INT4                 null,
   CORREO               VARCHAR(30)          null,
   constraint PK_CONTACTO primary key (ID_CONTACTO)
);

/*==============================================================*/
/* Table: DIRECCION                                             */
/*==============================================================*/
create table PROVEEDOR.DIRECCION (
   ID_DIRECCION         SERIAL               not null,
   ID_COMUNA            INT4                 null,
   CALLE                VARCHAR(20)          not null,
   NUMERO               VARCHAR(10)          not null,
   DEPARTAMENTO         VARCHAR(10)          null,
   VILLA                VARCHAR(20)          null,
   constraint PK_DIRECCION primary key (ID_DIRECCION)
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PROVEEDOR.PAIS (
   ID_PAIS              INT4                 not null,
   NOMBRE               VARCHAR(20)          not null,
   constraint PK_PAIS primary key (ID_PAIS)
);

/*==============================================================*/
/* Table: PERFIL                                                */
/*==============================================================*/
create table PROVEEDOR.PERFIL (
   ID_PERFIL            INT4                 not null,
   NOMBRE               VARCHAR(20)          not null,
   HABILITADO           BOOL                 not null,
   constraint PK_PERFIL primary key (ID_PERFIL)
);


/*==============================================================*/
/* Table: PERFIL_USUARIO                                        */
/*==============================================================*/
create table PROVEEDOR.PERFIL_USUARIO (
   ID_PERFIL            INT4                 not null,
   ID_USUARIO           INT4                 not null,
   constraint PK_PERFIL_USUARIO primary key (ID_PERFIL, ID_USUARIO)
);


/*==============================================================*/
/* Table: PERMISO                                               */
/*==============================================================*/
create table PROVEEDOR.PERMISO (
   ID_PERMISO           INT4                 not null,
   NOMBRE               VARCHAR(20)          not null,
   HABILITADO           BOOL                 not null,
   constraint PK_PERMISO primary key (ID_PERMISO)
);


/*==============================================================*/
/* Table: PERMISO_PERFIL                                        */
/*==============================================================*/
create table PROVEEDOR.PERMISO_PERFIL (
   ID_PERMISO           INT4                 not null,
   ID_PERFIL            INT4                 not null,
   constraint PK_PERMISO_PERFIL primary key (ID_PERMISO, ID_PERFIL)
);


/*==============================================================*/
/* Table: PROVEEDOR                                             */
/*==============================================================*/
create table PROVEEDOR.PROVEEDOR (
   ID_PROVEEDOR         SERIAL               not null,
   NOMBRE               VARCHAR(50)          not null,
   RUT                  INT4                 not null,
   FECHA_INGRESO        DATE                 not null,
   FECHA_SALIDA         DATE                 null,
   HABILITADO           BOOL                 not null,
   PATH_LOGO            VARCHAR(200)         null,
   constraint PK_PROVEEDOR primary key (ID_PROVEEDOR)
);


/*==============================================================*/
/* Table: REGION                                                */
/*==============================================================*/
create table PROVEEDOR.REGION (
   ID_REGION            INT4                 not null,
   ID_PAIS              INT4                 null,
   NOMBRE               VARCHAR(50)          not null,
   constraint PK_REGION primary key (ID_REGION)
);


/*==============================================================*/
/* Table: SUCURSAL_BENEFICIO                                    */
/*==============================================================*/
create table PROVEEDOR.SUCURSAL_BENEFICIO (
   ID_SUCURSAL          INT4                 not null,
   ID_BENEFICIO         INT4                 not null,
   constraint PK_SUCURSAL_BENEFICIO primary key (ID_SUCURSAL, ID_BENEFICIO)
);

/*==============================================================*/
/* Table: SUCURSAL_PROVEEDOR                                    */
/*==============================================================*/
create table PROVEEDOR.SUCURSAL_PROVEEDOR (
   ID_SUCURSAL          SERIAL               not null,
   ID_PROVEEDOR         INT4                 null,
   ID_DIRECCION         INT4                 null,
   NOMBRE               VARCHAR(50)          null,
   HORARIO_ATENCION     VARCHAR(100)         null,
   HABILITADO           BOOL                 null,
   PASSWORD_POS         VARCHAR(50)          null,
   OFICINA              VARCHAR(30)          null,
   constraint PK_SUCURSAL_PROVEEDOR primary key (ID_SUCURSAL)
);


/*==============================================================*/
/* Table: TIPO_BENEFICIO                                        */
/*==============================================================*/
create table PROVEEDOR.TIPO_BENEFICIO (
   ID_TIPO_BENEFICIO    INT4                 not null,
   NOMBRE               VARCHAR(15)          null,
   constraint PK_TIPO_BENEFICIO primary key (ID_TIPO_BENEFICIO)
);


/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table PROVEEDOR.USUARIO (
   ID_USUARIO           INT4                 not null,
   ID_CONTACTO          INT4                 null,
   ID_DIRECCION         INT4                 null,
   USUARIO              VARCHAR(50)          not null,
   PASSWORD             VARCHAR(50)          not null,
   NOMBRES              VARCHAR(50)          not null,
   APELLIDO_P           VARCHAR(50)          not null,
   APELLIDO_M           VARCHAR(50)          not null,
   FECHA_NACIMIENTO     DATE                 not null,
   HABILITADO           BOOL                 null default FALSE,
   constraint PK_USUARIO primary key (ID_USUARIO)
);


/*==============================================================*/
/* Table: USUARIO_PROVEEDOR                                     */
/*==============================================================*/
create table PROVEEDOR.USUARIO_PROVEEDOR (
   ID_PROVEEDOR         INT4                 null,
   ID_USUARIO           INT4                 null,
   CORREO               VARCHAR(50)          not null
);

/*==============================================================*/
/* Table: PARAMETRO_SISTEMA                                     */
/*==============================================================*/

CREATE TABLE PROVEEDOR.PARAMETRO_SISTEMA
(
  ID_PARAMETRO_SISTEMA serial NOT NULL,
  TIPO_PARAMETRO VARCHAR(30) NOT NULL,
  CLAVE VARCHAR(30) NOT NULL,
  VALOR_A VARCHAR(100) NOT NULL,
  VALOR_B VARCHAR(100) NOT NULL
);

/*==============================================================*/
/* Table: PLANTILLA_CORREO                                     */
/*==============================================================*/
CREATE TABLE PROVEEDOR.PLANTILLA_CORREO
(
  ID_PLANTILLA integer NOT NULL,
  NOMBRE VARCHAR(100) NOT NULL,
  ASUNTO VARCHAR(100) NOT NULL,
  DESCRIPCION VARCHAR(100) NOT NULL,
  CONSTRAINT pk_plantilla_correo PRIMARY KEY (id_plantilla)
);

alter table PROVEEDOR.ACCESO_USUARIO
   add constraint FK_ACCESO_U_REFERENCE_USUARIO foreign key (ID_USUARIO)
      references PROVEEDOR.USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO
   add constraint FK_BENEFICI_REFERENCE_CATEGORI foreign key (ID_CATEGORIA)
      references PROVEEDOR.CATEGORIA (ID_CATEGORIA)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO
   add constraint FK_BENEFICI_REFERENCE_TIPO_BEN foreign key (ID_TIPO_BENEFICIO)
      references PROVEEDOR.TIPO_BENEFICIO (ID_TIPO_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO
   add constraint FK_BENEFICI_REFERENCE_PROVEEDO foreign key (ID_PROVEEDOR)
      references PROVEEDOR.PROVEEDOR (ID_PROVEEDOR)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO_DESCUENTO
   add constraint FK_BENEFICI_REFERENCE_BENEFICI1 foreign key (ID_BENEFICIO)
      references PROVEEDOR.BENEFICIO (ID_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO_GANCHO
   add constraint FK_BENEFICI_REFERENCE_BENEFICI2 foreign key (ID_BENEFICIO)
      references PROVEEDOR.BENEFICIO (ID_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO_IMAGEN
   add constraint FK_BENEFICI_REFERENCE_BENEF foreign key (ID_BENEFICIO)
      references PROVEEDOR.BENEFICIO (ID_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.BENEFICIO_PRODUCTO
   add constraint FK_BENEFICI_REFERENCE_BENEFICI4 foreign key (ID_BENEFICIO)
      references PROVEEDOR.BENEFICIO (ID_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.COMUNA
   add constraint FK_COMUNA_REFERENCE_REGION foreign key (ID_REGION)
      references PROVEEDOR.REGION (ID_REGION)
      on delete restrict on update restrict;

alter table PROVEEDOR.CONDICION_BENEFICIO
   add constraint FK_CONDICIO_REFERENCE_BENEFICI foreign key (ID_BENEFICIO)
      references PROVEEDOR.BENEFICIO (ID_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.DIRECCION
   add constraint FK_DIRECCIO_REFERENCE_COMUNA foreign key (ID_COMUNA)
      references PROVEEDOR.COMUNA (ID_COMUNA)
      on delete restrict on update restrict;

alter table PROVEEDOR.PERFIL_USUARIO
   add constraint FK_PERFIL_U_REFERENCE_PERFIL foreign key (ID_PERFIL)
      references PROVEEDOR.PERFIL (ID_PERFIL)
      on delete restrict on update restrict;

alter table PROVEEDOR.PERFIL_USUARIO
   add constraint FK_PERFIL_U_REFERENCE_USUARIO foreign key (ID_USUARIO)
      references PROVEEDOR.USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.PERMISO_PERFIL
   add constraint FK_PERMISO__REFERENCE_PERMISO foreign key (ID_PERMISO)
      references PROVEEDOR.PERMISO (ID_PERMISO)
      on delete restrict on update restrict;

alter table PROVEEDOR.PERMISO_PERFIL
   add constraint FK_PERMISO__REFERENCE_PERFIL foreign key (ID_PERFIL)
      references PROVEEDOR.PERFIL (ID_PERFIL)
      on delete restrict on update restrict;

alter table PROVEEDOR.REGION
   add constraint FK_REGION_REFERENCE_PAIS foreign key (ID_PAIS)
      references PROVEEDOR.PAIS (ID_PAIS)
      on delete restrict on update restrict;

alter table PROVEEDOR.SUCURSAL_BENEFICIO
   add constraint FK_SUCURSAL_REFERENCE_SUCURSAL foreign key (ID_SUCURSAL)
      references PROVEEDOR.SUCURSAL_PROVEEDOR (ID_SUCURSAL)
      on delete restrict on update restrict;

alter table PROVEEDOR.SUCURSAL_BENEFICIO
   add constraint FK_SUCURSAL_REFERENCE_BENEFICI foreign key (ID_BENEFICIO)
      references PROVEEDOR.BENEFICIO (ID_BENEFICIO)
      on delete restrict on update restrict;

alter table PROVEEDOR.SUCURSAL_PROVEEDOR
   add constraint FK_SUCURSAL_REFERENCE_DIRECCIO foreign key (ID_DIRECCION)
      references PROVEEDOR.DIRECCION (ID_DIRECCION)
      on delete restrict on update restrict;

alter table PROVEEDOR.SUCURSAL_PROVEEDOR
   add constraint FK_SUCURSAL_REFERENCE_PROVEEDO foreign key (ID_PROVEEDOR)
      references PROVEEDOR.PROVEEDOR (ID_PROVEEDOR)
      on delete restrict on update restrict;

alter table PROVEEDOR.USUARIO
   add constraint FK_USUARIO_REFERENCE_CONTACTO foreign key (ID_CONTACTO)
      references PROVEEDOR.CONTACTO (ID_CONTACTO)
      on delete restrict on update restrict;

alter table PROVEEDOR.USUARIO
   add constraint FK_USUARIO_REFERENCE_DIRECCIO foreign key (ID_DIRECCION)
      references PROVEEDOR.DIRECCION (ID_DIRECCION)
      on delete restrict on update restrict;


alter table PROVEEDOR.USUARIO_PROVEEDOR
   add constraint FK_USUARIO__REFERENCE_PROVEEDO foreign key (ID_PROVEEDOR)
      references PROVEEDOR.PROVEEDOR (ID_PROVEEDOR)
      on delete restrict on update restrict;

alter table PROVEEDOR.USUARIO_PROVEEDOR
   add constraint FK_USUARIO__REFERENCE_USUARIO foreign key (ID_USUARIO)
      references PROVEEDOR.USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

CREATE TABLE log_beneficio
(
    "ID_BENEFICIO" INT4 NOT NULL,
    "ID_USUARIO" INT4 NOT NULL,
    "FECHA" DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "ACCION" VARCHAR(20) NOT NULL,
    CONSTRAINT "LOG_BENEFICIO_FK1" FOREIGN KEY ("ID_BENEFICIO")
        REFERENCES beneficio (id_beneficio) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "LOG_BENEFICIO_FK2" FOREIGN KEY ("ID_USUARIO")
        REFERENCES usuario (id_usuario) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
	  



	  
	  
	  

