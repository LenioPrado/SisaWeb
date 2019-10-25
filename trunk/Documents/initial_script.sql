create database sistemaacademico;
use sistemaacademico;

CREATE TABLE Cargo (
CódigoCargo integer PRIMARY KEY,
Nome varchar(80),
Observação varchar(200)
) engine = innodb;

CREATE TABLE Verificação (
CodigoVerificacao integer PRIMARY KEY,
data date,
Observação varchar(300),
status varchar(15),
CódigoPlanoEnsino Integer,
Código integer
)engine = innodb;

CREATE TABLE Autor (
CodigoAutor Integer PRIMARY KEY,
últimoSobrenome varchar(30),
DemaisNomes varchar(100)
)engine = innodb;

CREATE TABLE ProgramaçãoAulas (
CódigoProgramacao integer PRIMARY KEY,
Conteúdo varchar(150),
NumeroAulas Integer,
CódigoPlanoEnsino Integer
)engine = innodb;

CREATE TABLE Avaliação (
CódigoAvaliacao Integer PRIMARY KEY,
nome varchar(100),
descrição varchar(300)
)engine = innodb;

CREATE TABLE Usuário (
Código integer PRIMARY KEY,
Funcao varchar (50),
Nome varchar(80),
Senha char(8),
e_mail varchar(30)
)engine = innodb;

CREATE TABLE ProcedimentosAvaliação (
CodigoProcedimento Integer PRIMARY KEY,
data date,
Observação varchar(200),
CódigoPlanoEnsino Integer,
CódigoAvaliacao Integer,
FOREIGN KEY(CódigoAvaliacao) REFERENCES Avaliação (CódigoAvaliacao)
)engine = innodb;

CREATE TABLE PlanoEnsino (
CódigoPlanoEnsino Integer PRIMARY KEY,
Objetivo varchar(300),
ProcedimentoDeEnsino varchar(400),
Recuperção varchar(400),
Observação varchar(400),
CódigoDisciplina integer
)engine = innodb;

CREATE TABLE Curso (
Nome varchar(50),
Modalidade varchar(20),
CódigoCurso Integer PRIMARY KEY,
Turno varchar(20)
)engine = innodb;

CREATE TABLE Disciplina (
CódigoDisciplina integer PRIMARY KEY,
Período_Série Integer,
Ementa varchar(500),
Nome varchar(100),
CargaHorariaPratica float,
CargaHoráriaTeorica float,
AulasSemanais Integer,
Tipo varchar(30)
)engine = innodb;

CREATE TABLE Bibliografia (
CódigoBibliografia Integer PRIMARY KEY,
Título varchar(150),
Subtitulo varchar(150),
edicao integer,
Local_Cidade varchar(60),
Editora varchar(60),
Ano Integer,
Páginas Integer,
Volume Integer,
Serie varchar (100)
)engine = innodb;

CREATE TABLE PPC (
Código_PPC integer PRIMARY KEY,
Data  date,
Local varchar(50),
hora_aula integer,
NumeroSemanas integer,
CódigoCurso Integer,
CódigoEstágio Integer,
FOREIGN KEY(CódigoCurso) REFERENCES Curso (CódigoCurso)
)engine = innodb;

CREATE TABLE Estágio (
CódigoEstágio Integer PRIMARY KEY,
CargaHorária Integer,
Descrição varchar(1000)
)engine = innodb;

CREATE TABLE Campus (
CodigoCampus Integer PRIMARY KEY,
Nome varchar(200),
CNPJ varchar(30)
)engine = innodb;

CREATE TABLE Reitoria (
CNPJ varchar(30) PRIMARY KEY,
Atributo_1 varchar(10)
)engine = innodb;

CREATE TABLE Período_Letivo (
CódigoPeriodoLetivo Integer PRIMARY KEY,
Ano Integer,
Semestre Integer,
tipo varchar (10)
)engine = innodb;

CREATE TABLE Bibliografia_Autor (
CodigoAutor Integer,
CódigoBibliografia Integer,
primary key(CodigoAutor,CódigoBibliografia),
FOREIGN KEY(CodigoAutor) REFERENCES Autor (CodigoAutor),
FOREIGN KEY(CódigoBibliografia) REFERENCES Bibliografia (CódigoBibliografia)
)engine = innodb;

CREATE TABLE Usuário_Cargo (
DataTérmino date,
DataInício date,
CódigoCargo integer,
Código integer,
PRIMARY KEY(CódigoCargo,Código),
FOREIGN KEY(CódigoCargo) REFERENCES Cargo (CódigoCargo),
FOREIGN KEY(Código) REFERENCES Usuário (Código)
)engine = innodb;

CREATE TABLE Curso_PeríodoLetivo (
CódigoPeriodoLetivo Integer,
CódigoCurso Integer,
Primary key (CódigoPeriodoLetivo,CódigoCurso),
FOREIGN KEY(CódigoPeriodoLetivo) REFERENCES Período_Letivo (CódigoPeriodoLetivo),
FOREIGN KEY(CódigoCurso) REFERENCES Curso (CódigoCurso)
)engine = innodb;

CREATE TABLE Campus_Curso (
CódigoCurso Integer,
CodigoCampus Integer,
primary key (CódigoCurso,CodigoCampus),
FOREIGN KEY(CódigoCurso) REFERENCES Curso (CódigoCurso),
FOREIGN KEY(CodigoCampus) REFERENCES Campus (CodigoCampus)
)engine = innodb;

CREATE TABLE Cargo_Verificação (
CódigoCargo integer ,
CodigoVerificacao integer ,
Data date,
Primary key (CódigoCargo, CodigoVerificacao),
FOREIGN KEY(CódigoCargo) REFERENCES Cargo (CódigoCargo),
FOREIGN KEY(CodigoVerificacao) REFERENCES Verificação (CodigoVerificacao)
)engine = innodb;

CREATE TABLE Responsável (
Código integer,
CódigoPlanoEnsino Integer,
Primary key (Código, CódigoPlanoEnsino),
FOREIGN KEY(Código) REFERENCES Usuário (Código),
FOREIGN KEY(CódigoPlanoEnsino) REFERENCES PlanoEnsino (CódigoPlanoEnsino)
)engine = innodb;

CREATE TABLE PPC_Disciplina (
CódigoDisciplina integer,
Código_PPC integer,
primary key (CódigoDisciplina, Código_PPC),
FOREIGN KEY(CódigoDisciplina) REFERENCES Disciplina (CódigoDisciplina),
FOREIGN KEY(Código_PPC) REFERENCES PPC (Código_PPC)
)engine = innodb;

CREATE TABLE Disciplina_Bibliografia (
CódigoBibliografia Integer,
CódigoDisciplina integer,
primary key (CódigoBibliografia, CódigoDisciplina),
FOREIGN KEY(CódigoBibliografia) REFERENCES Bibliografia (CódigoBibliografia),
FOREIGN KEY(CódigoDisciplina) REFERENCES Disciplina (CódigoDisciplina)
)engine = innodb;

ALTER TABLE Verificação ADD FOREIGN KEY(CódigoPlanoEnsino) REFERENCES PlanoEnsino (CódigoPlanoEnsino);
ALTER TABLE Verificação ADD FOREIGN KEY(Código) REFERENCES Usuário (Código);
ALTER TABLE ProgramaçãoAulas ADD FOREIGN KEY(CódigoPlanoEnsino) REFERENCES PlanoEnsino (CódigoPlanoEnsino);
ALTER TABLE ProcedimentosAvaliação ADD FOREIGN KEY(CódigoPlanoEnsino) REFERENCES PlanoEnsino (CódigoPlanoEnsino);
ALTER TABLE PlanoEnsino ADD FOREIGN KEY(CódigoDisciplina) REFERENCES Disciplina (CódigoDisciplina);
ALTER TABLE PPC ADD FOREIGN KEY(CódigoEstágio) REFERENCES Estágio (CódigoEstágio);
ALTER TABLE Campus ADD FOREIGN KEY(CNPJ) REFERENCES Reitoria (CNPJ);
