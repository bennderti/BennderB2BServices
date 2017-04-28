/*==============================================================*/
/* POBLAMIENTO DE PERFILES/ROLES USUARIOS(SPRING SECURITY)      */
/*==============================================================*/

INSERT INTO PROVEEDOR.PERFIL(ID_PERFIL,NOMBRE,HABILITADO) VALUES(1,'ADMINISTRADOR',true);
INSERT INTO PROVEEDOR.PERFIL(ID_PERFIL,NOMBRE,HABILITADO) VALUES(2,'USUARIO',true);


/*==============================================================*/
/*Inserción de planilla de correo  */
/*==============================================================*/


INSERT INTO PROVEEDOR.plantilla_correo(id_plantilla,nombre,asunto,descripcion)
values(1,'recuperacion-password.vm','Recuperación Contraseña','Plantilla utilizada para recuperación contraseña usuario');


/*==============================================================*/
/*Parámetros para correo de salida  */
/*==============================================================*/

INSERT INTO PROVEEDOR.PARAMETRO_SISTEMA(TIPO_PARAMETRO,CLAVE,VALOR_A,VALOR_B)
VALUES('CORREO_SOPORTE','CREDENCIALES','soporte@bennder.cl','soportebennder2017');

/*==============================================================*/
/*Parámetros para correo de salida  */
/*==============================================================*/

INSERT INTO PROVEEDOR.PARAMETRO_SISTEMA(TIPO_PARAMETRO,CLAVE,VALOR_A,VALOR_B)
VALUES('BENNDER_USUARIO','URL_PLATAFORMA','http://ec2-54-245-30-229.us-west-2.compute.amazonaws.com:8080/BennderWeb/index.html','');

/*==============================================================*/
/* PAIS    */
/*==============================================================*/

INSERT INTO PROVEEDOR.PAIS(ID_PAIS,NOMBRE) VALUES(1,'Chile');

/*==============================================================*/
/* REGIONES    */
/*==============================================================*/


INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(15,1,'Arica y Parinacota');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(01,1,'Tarapacá');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(02,1,'Antofagasta');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(03,1,'Atacama');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(04,1,'Coquimbo');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(05,1,'Valparaíso');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(06,1,'Libertador Gral. Bernardo O’Higgins');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(07,1,'Maule');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(08,1,'Biobío');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(09,1,'Araucanía');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(14,1,'Los Ríos');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(10,1,'Los Lagos');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(11,1,'Aysén del Gral. Carlos Ibáñez del Campo');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(12,1,'Magallanes y de la Antártica Chilena');
INSERT INTO PROVEEDOR.REGION(ID_REGION,ID_PAIS,NOMBRE) VALUES(13,1,'Metropolitana de Santiago');

/*==============================================================*/
/* COMUNAS    */
/*==============================================================*/


INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01101,1,'Iquique');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01107,1,'Alto Hospicio');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01401,1,'Pozo Almonte');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01402,1,'Camiña');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01403,1,'Colchane');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01404,1,'Huara');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(01405,1,'Pica');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02101,2,'Antofagasta');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02102,2,'Mejillones');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02103,2,'Sierra Gorda');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02104,2,'Taltal');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02201,2,'Calama');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02202,2,'Ollague');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02203,2,'San Pedro de Atacama');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02301,2,'Tocopilla');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(02302,2,'María Elena');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03101,3,'Copiapó');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03102,3,'Caldera');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03103,3,'Tierra Amarilla');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03201,3,'Chañaral');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03202,3,'Diego de Almagro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03301,3,'Vallenar');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03302,3,'Alto del Carmen');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03303,3,'Freirina');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(03304,3,'Huasco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04101,4,'La Serena');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04102,4,'Coquimbo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04103,4,'Andacollo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04104,4,'La Higuera');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04105,4,'Paihuano');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04106,4,'Vicuña');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04201,4,'Illapel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04202,4,'Canela');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04203,4,'Los Vilos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04204,4,'Salamanca');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04301,4,'Ovalle');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04302,4,'Combarbalá');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04303,4,'Monte Patria');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04304,4,'Punitaqui');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(04305,4,'Río Hurtado');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05101,5,'Valparaíso');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05102,5,'Casablanca');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05103,5,'Concón');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05104,5,'Juan Fernández');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05105,5,'Puchuncaví');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05106,5,'Quilpué');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05107,5,'Quintero');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05108,5,'Villa Alemana');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05109,5,'Viña del Mar');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05201,5,'Isla de Pascua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05301,5,'Los Andes');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05302,5,'Calle Larga');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05303,5,'Rinconada');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05304,5,'San Esteban');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05401,5,'La Ligua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05402,5,'Cabildo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05403,5,'Papudo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05404,5,'Petorca');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05405,5,'Zapallar');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05501,5,'Quillota');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05502,5,'Calera');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05503,5,'Hijuelas');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05504,5,'La Cruz');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05505,5,'Limache');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05506,5,'Nogales');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05507,5,'Olmué');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05601,5,'San Antonio');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05602,5,'Algarrobo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05603,5,'Cartagena');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05604,5,'El Quisco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05605,5,'El Tabo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05606,5,'Santo Domingo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05701,5,'San Felipe');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05702,5,'Catemu');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05703,5,'Llay Llay');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05704,5,'Panquehue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05705,5,'Putaendo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(05706,5,'Santa María');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06101,6,'Rancagua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06102,6,'Codegua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06103,6,'Coinco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06104,6,'Coltauco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06105,6,'Doñihue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06106,6,'Graneros');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06107,6,'Las Cabras');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06108,6,'Machalí');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06109,6,'Malloa');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06110,6,'Mostazal');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06111,6,'Olivar');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06112,6,'Peumo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06113,6,'Pichidegua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06114,6,'Quinta de Tilcoco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06115,6,'Rengo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06116,6,'Requinoa');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06117,6,'San Vicente');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06201,6,'Pichilemu');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06202,6,'La Estrella');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06203,6,'Litueche');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06204,6,'Marchihue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06205,6,'Navidad');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06206,6,'Paredones');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06301,6,'San Fernando');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06302,6,'Chépica');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06303,6,'Chimbarongo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06304,6,'Lolol');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06305,6,'Nancagua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06306,6,'Palmilla');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06307,6,'Peralillo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06308,6,'Placilla');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06309,6,'Pumanque');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(06310,6,'Santa Cruz');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07101,7,'Talca');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07102,7,'Constitución');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07103,7,'Curepto');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07104,7,'Empedrado');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07105,7,'Maule');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07106,7,'Pelarco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07107,7,'Pencahue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07108,7,'Río Claro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07109,7,'San Clemente');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07110,7,'San Rafael');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07201,7,'Cauquenes');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07202,7,'Chanco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07203,7,'Pelluhue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07301,7,'Curicó');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07302,7,'Hualañé');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07303,7,'Licantén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07304,7,'Molina');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07305,7,'Rauco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07306,7,'Romeral');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07307,7,'Sagrada Familia');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07308,7,'Teno');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07309,7,'Vichuquén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07401,7,'Linares');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07402,7,'Colbún');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07403,7,'Longaví');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07404,7,'Parral');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07405,7,'Retiro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07406,7,'San Javier');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07407,7,'Villa Alegre');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(07408,7,'Yerbas Buenas');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08101,8,'Concepción');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08102,8,'Coronel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08103,8,'Chiguayante');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08104,8,'Florida');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08105,8,'Hualqui');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08106,8,'Lota');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08107,8,'Penco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08108,8,'San Pedro De La Paz');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08109,8,'Santa Juana');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08110,8,'Talcahuano');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08111,8,'Tomé');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08112,8,'Hualpén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08201,8,'Lebu');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08202,8,'Arauco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08203,8,'Cañete');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08204,8,'Contulmo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08205,8,'Curanilahue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08206,8,'Los Alamos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08207,8,'Tirua');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08301,8,'Los Angeles');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08302,8,'Antuco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08303,8,'Cabrero');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08304,8,'Laja');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08305,8,'Mulchén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08306,8,'Nacimiento');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08307,8,'Negrete');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08308,8,'Quilaco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08309,8,'Quilleco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08310,8,'San Rosendo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08311,8,'Santa Bárbara');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08312,8,'Tucapel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08313,8,'Yumbel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08314,8,'Alto Biobío');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08401,8,'Chillán');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08402,8,'Bulnes');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08403,8,'Cobquecura');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08404,8,'Coelemu');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08405,8,'Coihueco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08406,8,'Chillán Viejo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08407,8,'El Carmen');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08408,8,'Ninhue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08409,8,'Ñiquén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08410,8,'Pemuco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08411,8,'Pinto');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08412,8,'Portezuelo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08413,8,'Quillón');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08414,8,'Quirihue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08415,8,'Ranquil');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08416,8,'San Carlos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08417,8,'San Fabián');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08418,8,'San Ignacio');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08419,8,'San Nicolás');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08420,8,'Trehuaco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(08421,8,'Yungay');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09101,9,'Temuco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09102,9,'Carahue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09103,9,'Cunco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09104,9,'Curarrehue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09105,9,'Freire');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09106,9,'Galvarino');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09107,9,'Gorbea');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09108,9,'Lautaro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09109,9,'Loncoche');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09110,9,'Melipeuco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09111,9,'Nueva Imperial');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09112,9,'Padre Las Casas');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09113,9,'Perquenco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09114,9,'Pitrufquén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09115,9,'Pucón');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09116,9,'Saavedra');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09117,9,'Teodoro Schmidt');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09118,9,'Toltén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09119,9,'Vilcún');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09120,9,'Villarrica');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09121,9,'Cholchol');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09201,9,'Angol');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09202,9,'Collipulli');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09203,9,'Curacautín');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09204,9,'Ercilla');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09205,9,'Lonquimay');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09206,9,'Los Sauces');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09207,9,'Lumaco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09208,9,'Purén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09209,9,'Renaico');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09210,9,'Traiguén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(09211,9,'Victoria');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10101,10,'Puerto Montt');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10102,10,'Calbuco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10103,10,'Cochamó');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10104,10,'Fresia');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10105,10,'Frutillar');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10106,10,'Los Muermos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10107,10,'Llanquihue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10108,10,'Maullín');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10109,10,'Puerto Varas');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10201,10,'Castro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10202,10,'Ancud');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10203,10,'Chonchi');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10204,10,'Curaco de Vélez');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10205,10,'Dalcahue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10206,10,'Puqueldón');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10207,10,'Queilén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10208,10,'Quellón');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10209,10,'Quemchi');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10210,10,'Quinchao');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10301,10,'Osorno');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10302,10,'Puerto Octay');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10303,10,'Purranque');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10304,10,'Puyehue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10305,10,'Río Negro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10306,10,'San Juan de la Costa');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10307,10,'San Pablo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10401,10,'Chaitén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10402,10,'Futaleufú');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10403,10,'Hualaihue');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(10404,10,'Palena');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11101,11,'Coihaique');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11102,11,'Lago Verde');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11201,11,'Aisén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11202,11,'Cisnes');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11203,11,'Guaitecas');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11301,11,'Cochrane');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11302,11,'Ohiggins');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11303,11,'Tortel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11401,11,'Chile Chico');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(11402,11,'Río Ibáñez');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12101,12,'Punta Arenas');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12102,12,'Laguna Blanca');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12103,12,'Río Verde');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12104,12,'San Gregorio');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12201,12,'Cabo de Hornos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12301,12,'Porvenir');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12302,12,'Primavera');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12303,12,'Timaukel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12401,12,'Natales');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(12402,12,'Torres del Paine');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13101,13,'Santiago');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13102,13,'Cerrillos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13103,13,'Cerro Navia');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13104,13,'Conchalí');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13105,13,'El Bosque');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13106,13,'Estación Central');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13107,13,'Huechuraba');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13108,13,'Independencia');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13109,13,'La Cisterna');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13110,13,'La Florida');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13111,13,'La Granja');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13112,13,'La Pintana');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13113,13,'La Reina');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13114,13,'Las Condes');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13115,13,'Lo Barnechea');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13116,13,'Lo Espejo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13117,13,'Lo Prado');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13118,13,'Macul');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13119,13,'Maipú');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13120,13,'Ñuñoa');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13121,13,'Pedro Aguirre Cerda');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13122,13,'Peñalolén');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13123,13,'Providencia');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13124,13,'Pudahuel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13125,13,'Quilicura');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13126,13,'Quinta Normal');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13127,13,'Recoleta');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13128,13,'Renca');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13129,13,'San Joaquín');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13130,13,'San Miguel');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13131,13,'San Ramón');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13132,13,'Vitacura');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13201,13,'Puente Alto');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13202,13,'Pirque');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13203,13,'San José de Maipo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13301,13,'Colina');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13302,13,'Lampa');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13303,13,'Til til');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13401,13,'San Bernardo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13402,13,'Buin');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13403,13,'Calera de Tango');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13404,13,'Paine');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13501,13,'Melipilla');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13502,13,'Alhué');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13503,13,'Curacaví');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13504,13,'María Pinto');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13505,13,'San Pedro');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13601,13,'Talagante');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13602,13,'El Monte');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13603,13,'Isla de Maipo');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13604,13,'Padre Hurtado');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(13605,13,'Peñaflor');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14101,14,'Valdivia');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14102,14,'Corral');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14103,14,'Lanco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14104,14,'Los Lagos');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14105,14,'Máfil');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14106,14,'Mariquina');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14107,14,'Paillaco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14108,14,'Panguipulli');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14201,14,'La Unión');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14202,14,'Futrono');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14203,14,'Lago Ranco');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(14204,14,'Río Bueno');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(15101,15,'Arica');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(15102,15,'Camarones');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(15201,15,'Putre');
INSERT INTO PROVEEDOR.COMUNA(ID_COMUNA,ID_REGION,NOMBRE) VALUES(15202,15,'General Lagos');


