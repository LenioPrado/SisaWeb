DROP DATABASE IF EXISTS sisa;

create database sisa;
use sisa;

CREATE TABLE pages (
page_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar (50) NOT NULL,
url varchar (50) NOT NULL
);

CREATE TABLE actions (
action_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
description varchar (50) NOT NULL
);

CREATE TABLE authors (
author_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
last_name varchar(30) NOT NULL,
other_names varchar(100) NOT NULL,
UNIQUE `unique_author` (last_name, other_names)
);

CREATE TABLE roles (
role_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(80) NOT NULL UNIQUE,
observation varchar(200)NOT NULL
);

CREATE TABLE users (
user_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(80) NOT NULL UNIQUE,
password varchar (50) NOT NULL,
email varchar(50) NOT NULL UNIQUE
);

CREATE TABLE evaluations (
evaluation_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(100) NOT NULL,
description varchar(300)NOT NULL
);

CREATE TABLE traineeships (
traineeship_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
hour_load Integer NOT NULL,
description varchar(1000)NOT NULL,
short_description varchar(50)NOT NULL
);

CREATE TABLE disciplines (
discipline_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
period_series Integer NOT NULL,
menu varchar(2000) NOT NULL,
name varchar(100) NOT NULL,
week_classes Integer NOT NULL,
type varchar(30) NOT NULL
);

CREATE TABLE menus (
menu_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
parent_id Integer,
description varchar(50) NOT NULL,
menu_order Integer NOT NULL,
page_id Integer NOT NULL,
FOREIGN KEY(parent_id) REFERENCES menus (menu_id),
FOREIGN KEY(page_id) REFERENCES pages (page_id)
);

CREATE TABLE courses (
course_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(50) NOT NULL UNIQUE,
short_name varchar(30) NOT NULL,
modality Integer NOT NULL,
shift Integer NOT NULL
);

CREATE TABLE academics_periods (
academic_period_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
year Integer NOT NULL,
semester Integer NOT NULL,
type varchar (10)NOT NULL
);

CREATE TABLE rectories (
rectory_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
cnpj varchar(30) NOT NULL UNIQUE,
name varchar(100)NOT NULL UNIQUE
);

CREATE TABLE bibliographies (
bibliography_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
bibliography_type Integer NOT NULL,
title varchar(300) NOT NULL,
subtitle varchar(150),
edition integer,
city_place varchar(60),
publisher varchar(60),
year Integer,
pages Integer,
volume Integer,
series varchar (100),
isbn varchar (20)
);

CREATE TABLE ppcs (
ppc_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
date Date NOT NULL ,
place varchar(50) NOT NULL,
class_hour Integer NOT NULL,
weeks_number Integer NOT NULL,
course_id Integer NOT NULL,
traineeship_id Integer NOT NULL,
FOREIGN KEY(course_id) REFERENCES courses (course_id),
FOREIGN KEY(traineeship_id) REFERENCES traineeships (traineeship_id)
);

CREATE TABLE ppcs_disciplines (
ppc_discipline_id Integer AUTO_INCREMENT NOT NULL,
discipline_id Integer NOT NULL,
ppc_id Integer NOT NULL,
PRIMARY KEY (ppc_discipline_id,discipline_id,ppc_id),
FOREIGN KEY(discipline_id) REFERENCES disciplines (discipline_id),
FOREIGN KEY(ppc_id) REFERENCES ppcs (ppc_id)
);

CREATE TABLE teaching_plans (
teaching_plan_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
ppc_discipline_id Integer NOT NULL,
academic_period_id Integer NOT NULL,
teacher_id Integer NOT NULL,
objective varchar(1000) NOT NULL,
teaching_procedure varchar(800) NOT NULL,
recovery varchar(2000),
observation varchar(800)NOT NULL,
FOREIGN KEY(teacher_id) REFERENCES users (user_id),
FOREIGN KEY(ppc_discipline_id) REFERENCES ppcs_disciplines (ppc_discipline_id),
FOREIGN KEY(academic_period_id) REFERENCES academics_periods (academic_period_id)
);

CREATE TABLE users_roles (
user_role_id Integer AUTO_INCREMENT NOT NULL,
user_id Integer NOT NULL,
role_id Integer NOT NULL,
start_date date NOT NULL,
end_date date NOT NULL,
PRIMARY KEY (user_role_id,user_id,role_id,start_date,end_date),
FOREIGN KEY(user_id) REFERENCES users (user_id),
FOREIGN KEY(role_id) REFERENCES roles (role_id)
);

CREATE TABLE responsibles (
responsible_id Integer AUTO_INCREMENT NOT NULL,
user_role_id Integer NOT NULL,
teaching_plan_id Integer NOT NULL,
PRIMARY KEY(responsible_id,user_role_id,teaching_plan_id),
FOREIGN KEY(user_role_id) REFERENCES users_roles (user_role_id),
FOREIGN KEY(teaching_plan_id) REFERENCES teaching_plans (teaching_plan_id)
);

CREATE TABLE verifications (
verification_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
creation_date timestamp NOT NULL,
evaluation_date timestamp,
observation varchar(300) NOT NULL,
status Integer NOT NULL,
responsible_id Integer NOT NULL,
FOREIGN KEY(responsible_id) REFERENCES responsibles (responsible_id)
);

CREATE TABLE campus (
campus_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(200) NOT NULL UNIQUE,
cnpj varchar(30) NOT NULL UNIQUE,
rectory_id Integer NOT NULL,
FOREIGN KEY(rectory_id) REFERENCES rectories (rectory_id)
);

CREATE TABLE classes_programming (
programming_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
content varchar(300),
classes_quantity Integer NOT NULL,
class_type Integer NOT NULL,
teaching_plan_id Integer NOT NULL,
FOREIGN KEY(teaching_plan_id) REFERENCES teaching_plans (teaching_plan_id)
);

CREATE TABLE procedures_evaluations (
procedure_id Integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
date date NOT NULL,
observation varchar(200) NOT NULL,
teaching_plan_id Integer NOT NULL,
evaluation_id Integer NOT NULL,
FOREIGN KEY(teaching_plan_id) REFERENCES teaching_plans (teaching_plan_id),
FOREIGN KEY(evaluation_id) REFERENCES evaluations (evaluation_id)
);

CREATE TABLE academics_periods_courses (
academic_period_id Integer NOT NULL,
course_id Integer NOT NULL,
PRIMARY KEY (academic_period_id,course_id),
FOREIGN KEY(academic_period_id) REFERENCES academics_periods (academic_period_id),
FOREIGN KEY(course_id) REFERENCES courses (course_id)
);

CREATE TABLE bibliographies_authors (
author_id Integer NOT NULL,
bibliography_id Integer NOT NULL,
PRIMARY KEY (author_id,bibliography_id),
FOREIGN KEY(author_id) REFERENCES authors (author_id),
FOREIGN KEY(bibliography_id) REFERENCES bibliographies (bibliography_id)
);

CREATE TABLE disciplines_bibliographies (
bibliography_id Integer NOT NULL,
discipline_id Integer NOT NULL,
is_basic boolean NOT NULL,
PRIMARY KEY(bibliography_id,discipline_id),
FOREIGN KEY(bibliography_id) REFERENCES bibliographies (bibliography_id),
FOREIGN KEY(discipline_id) REFERENCES disciplines (discipline_id)
);

CREATE TABLE campus_courses (
course_id Integer NOT NULL,
campus_id Integer NOT NULL,
PRIMARY KEY(course_id,campus_id),
FOREIGN KEY(course_id) REFERENCES courses (course_id),
FOREIGN KEY(campus_id) REFERENCES campus (campus_id)
);

CREATE TABLE pages_roles (
page_id Integer,
role_id Integer,
action_id Integer,
PRIMARY KEY(page_id,role_id,action_id),
FOREIGN KEY(page_id) REFERENCES pages (page_id),
FOREIGN KEY(role_id) REFERENCES roles (role_id),
FOREIGN KEY(action_id) REFERENCES actions (action_id)
);