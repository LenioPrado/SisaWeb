use SISA;

/* Inserts Default */

## Roles ##
INSERT INTO roles (role_id,name,observation) VALUES (1, 'Administrador', 'Função Padrão do Sistema');

## Users ##
INSERT INTO users (user_id, name, password, email) values (1,'Administrador', '1VsR4a3oE9aV9rpHFeIoyxp2Kdc=', 'sisa@ifsuldeminas.edu.br');

## User Role ##
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (1,1,1,'2016-01-01','2030-12-31');

## Pages ##
INSERT INTO pages (page_id,name,url) VALUES (1,'--DefaultMenu--','');
INSERT INTO pages (page_id,name,url) VALUES (2,'AcademicPeriod','listAcademicPeriod.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (3,'AcademicPeriodCourse','listAcademicPeriodCourse.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (4,'Author','listAuthor.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (5,'Bibliography','listBibliography.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (6,'BibliographyAuthor','listBibliographyAuthor.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (7,'Campus','listCampus.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (8,'CampusCourse','listCampusCourse.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (9,'ClassProgramming','listClassProgramming.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (10,'Course','listCourse.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (11,'Discipline','listDiscipline.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (12,'DisciplineBibliography','listDisciplineBibliography.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (13,'Evaluation','listEvaluation.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (14,'Page','listPage.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (15,'Ppc','listPpc.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (16,'PpcDiscipline','listPpcDiscipline.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (17,'ProcedureEvaluation','listProcedureEvaluation.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (18,'Rectory','listRectory.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (19,'Responsible','listResponsible.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (20,'Role','listRole.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (21,'TeachingPlan','listTeachingPlan.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (22,'Traineeship','listTraineeship.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (23,'UserRole','listUserRole.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (24,'Users','listUsers.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (25,'Verification','listVerification.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (26,'Action','listAction.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (27,'Menu','listMenu.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (28,'PageRole','listPageRole.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (29,'Home','home.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (30,'CompleteTeachingPlan','listCompleteTeachingPlan.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (31,'TeachingPlanVerification','listTeachingPlanVerification.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (32,'TeachingPlansToEvaluate','teachingPlansToEvaluate.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (33,'FillTeachingPlan','listFillTeachingPlan.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (34,'EvaluateTeachingPlan','listEvaluateTeachingPlan.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (35,'ClassProgrammingByTeachingPlan','listClassProgrammingByTeachingPlan.xhtml');
INSERT INTO pages (page_id,name,url) VALUES (36,'ProcedureEvaluationByTeachingPlan','listProcedureEvaluationByTeachingPlan.xhtml');


## Action ##
INSERT INTO actions (action_id,description) VALUES (1,'Listar');
INSERT INTO actions (action_id,description) VALUES (2,'Incluir');
INSERT INTO actions (action_id,description) VALUES (3,'Editar');
INSERT INTO actions (action_id,description) VALUES (4,'Excluir');

## Menu ##
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (1,NULL,'Pedagógico',4,1);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (2,NULL,'Instituição',2,1);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (4,NULL,'Período Acadêmico',9,1);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (6,NULL,'Funções',13,1);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (7,NULL,'Sistema',12,1);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (38,NULL,'Bibliografias',5,1);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (39,NULL,'Plano de Ensino',7,1);

INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (9,1,'PPC´s',3,15);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (10,1,'Disciplinas',4,11);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (11,1,'Disciplinas dos PPC´s',5,16);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (12,1,'Cursos',2,10);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (13,1,'Estágios',8,22);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (14,39,'Planos de Ensino',1,21);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (15,39,'Programação das Aulas',2,9);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (16,2,'Reitorias',1,18);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (17,2,'Campus',2,7);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (18,2,'Cursos do Campus',3,8);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (19,38,'Autores',2,4);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (20,38,'Autores das Bibliografias',4,6);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (21,38,'Bibliografias',3,5);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (22,1,'Bibliografias das Disciplinas',6,12);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (23,4,'Responsáveis',3,19);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (24,39,'Verificações',7,25);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (25,4,'Períodos Acadêmicos',1,2);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (26,4,'Períodos Acadêmicos dos Cursos',2,3);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (27,39,'Avaliações',3,13);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (28,39,'Procedimentos de Avaliação',4,17);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (29,6,'Funções',1,20);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (30,6,'Funções do Usuário',2,23);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (31,7,'Páginas',1,14);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (32,7,'Funções das Páginas',2,28);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (33,7,'Ações',3,26);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (34,7,'Menus',4,27);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (35,7,'Usuários',5,24);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (36,39,'Plano de Ensino Completo',5,30);
INSERT INTO menus (menu_id, parent_id, description, menu_order, page_id) VALUES (37,39,'Verificações do Plano de Ensino',6,31);

## Pages Roles ##
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (1,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (1,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (1,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (1,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (2,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (2,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (2,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (2,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (3,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (3,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (3,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (3,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (4,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (4,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (4,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (4,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (5,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (5,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (5,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (5,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (6,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (6,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (6,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (6,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (7,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (7,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (7,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (7,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (8,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (8,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (8,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (8,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (9,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (9,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (9,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (9,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (10,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (10,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (10,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (10,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (11,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (11,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (11,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (11,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (12,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (12,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (12,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (12,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (13,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (13,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (13,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (13,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (14,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (14,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (14,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (14,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (15,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (15,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (15,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (15,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (16,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (16,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (16,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (16,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (17,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (17,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (17,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (17,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (18,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (18,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (18,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (18,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (19,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (19,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (19,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (19,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (20,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (20,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (20,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (20,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (21,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (21,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (21,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (21,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (22,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (22,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (22,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (22,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (23,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (23,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (23,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (23,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (24,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (24,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (24,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (24,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (25,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (25,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (25,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (25,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (26,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (26,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (26,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (26,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (27,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (27,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (27,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (27,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (28,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (28,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (28,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (28,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (29,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (29,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (29,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (29,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (30,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (30,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (30,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (30,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (31,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (31,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (31,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (31,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (32,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (32,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (32,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (32,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (33,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (33,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (33,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (33,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (34,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (34,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (34,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (34,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (35,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (35,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (35,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (35,1,4);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (36,1,1);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (36,1,2);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (36,1,3);
INSERT INTO pages_roles (page_id,role_id,action_id) VALUES (36,1,4);

/* Fim */

/*
SET innodb_lock_wait_timeout = 200;

INSERT INTO mysql.user (Host, User, Password, Select_priv, max_connections, max_user_connections, ssl_cipher, x509_issuer, x509_subject,
Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv, Reload_priv, Shutdown_priv, Process_priv, File_priv, Grant_priv, 
References_priv, Index_priv, Alter_priv, Show_db_priv, Super_priv, Create_tmp_table_priv, Lock_tables_priv, Execute_priv, 
Repl_slave_priv, Repl_client_priv, Create_view_priv, Show_view_priv, Create_routine_priv, Alter_routine_priv, Create_user_priv) 
VALUES ('%', 'root', '', 'Y', 200, 200, '','','',
'Y', 'Y', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N',
'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 
'N', 'N', 'N', 'N', 'N', 'N', 'N');

GRANT ALL ON *.* TO 'root'@'%';

flush privileges ;
*/