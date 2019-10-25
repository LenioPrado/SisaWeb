/*
* File:	     MsgConstants.java
* Creation date: 04/06/2015
* Author:        L�nio
*/
package br.edu.pcs.ifsulmg.sisa.web.constants;

/**
 * The Class MsgConstants.
 */
public class MsgConstants {
	
	/**
	 * Instantiates a new msg constants.
	 */
	private MsgConstants() {}
	
	/** The Constant KEY_VERSION. */
	public static final String KEY_VERSION = "app.version";	
	public static final String HOME_PAGE = "home?faces-redirect=true";
	public static final String SELECT_ROLE_PAGE = "selectRole?faces-redirect=true";
	public static final String EDIT_USER = "registerUser.xhtml";
	public static final String RECOVERY_PASSWORD_PAGE = "recoveryPassword.xhtml";
	public static final String MSG_SUBJECT = "Recupera��o de Senha";
	public static final String MSG_CONTENT = "Sua nova senha � '%s'.";
	public static final String MSG_EMAIL_EXIST = "O e-mail informado j� existe. ";
	public static final String MSG_SUCCESS_SEND_PWD = "Uma nova senha ser� enviada para seu e-mail.";
	public static final String MSG_DATE_CONVERSION_ERROR = "O valor fornecido como data � inv�lido";
	public static final String MSG_CURRENT_PASSWORD_NOT_MATCH = "Senha atual n�o confere";
	public static final String MSG_EDIT_USER_KEY = "lbl_edit_user";
	public static final String MSG_LOGOUT_KEY = "lbl_user_logout";
	public static final String UNKHNOWN_USER = "Usu�rio";
		
	/** The Constant LOGIN Messages */	
	public static final String MSG_ERROR_SEND_PASSWORD = "Erro ao enviar nova senha";	
	public static final String MSG_ERROR_LOAD_USERS_LIST = "Erro ao carregar lista de usu�rios";
	public static final String MSG_ERROR_LOAD_USER = "Erro ao carregar usu�rio";
	public static final String MSG_ERROR_LOGIN = "Email ou senha inv�lidos";
	public static final String MSG_ERRO_EMAIL_NOT_EXIST = "Email informando n�o est� cadastrado.";
	public static final String MSG_ERROR_USER_HAS_NO_ROLE = "Usu�rio n�o possui fun��o associada no sistema";
	public static final String MSG_INFO_LDAP_PASSWORDS = "O sistema utiliza a mesma senha do e-mail institucional. Caso n�o possua a senha, entre em contato com o suporte da Institui��o.";

	/** Default System Values Errors */
	public static final String MSG_ERRO_DEFAULT_USER_ROLE = "Esta fun��o de usu�rio � essencial ao sistema e n�o pode ser exclu�da.";
	
	/** Pdf Errors */
	public static final String MSG_ERROR_GENERATING_PDF_REPORT = "Erro ao gerar o relat�rio PDF.";
	public static final String MSG_ERROR_GENERATING_PDF_RESPONSE = "Erro ao gerar a resposta contendo o arquivo PDF.";
	public static final String MSG_ERROR_GETTING_PDF_FONT = "Erro ao recuperar a fonte para gera��o do arquivo PDF.";
	
	/** Include Sucess */
	public static final String MSG_SUCCESS_SAVE_ACADEMIC_PERIOD = "Per�odo Acad�mico salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ACADEMIC_PERIOD_COURSE = "Curso x Per�odo Acad�mico salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_AUTHOR = "Autor salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_BIBLIOGRAPHY= "Bibliografia salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_BIBLIOGRAPHY_AUTHOR= "Bibliografia do Autor salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_CAMPUS= "Campus salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_CAMPUS_COURSE= "Curso x Campus salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_CLASS_PROGRAMMING = "Programa��o da Aula salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_COURSE = "Curso salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_DISCIPLINE = "Disciplina salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_DISCIPLINE_BIBLIOGRAPHY = "Bibliografia da Disciplina salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_EVALUATION= "Avalia��o salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PPC= "PPC salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PPC_DISCIPLINE= "Disciplina do PPC salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PROCEDURE_EVALUATION= "Processo de avalia��o salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_RECTORY= "Reitoria salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_RESPONSIBLE= "Respons�vel salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ROLE= "Fun��o salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ROLE_VERIFICATION = "Verifica��o da Fun��o salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_TEACHING_PLAN = "Plano de Ensino salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_TRAINEESHIP = "Est�gio salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_USER= "Usu�rio salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_USER_ROLE = "Fun��o do Usu�rio salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_VERIFICATION = "Verifica��o salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PAGE = "P�gina salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PAGE_ROLE = "Fun��o da p�gina salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ACTION = "A��o salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_MENU = "Menu salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_MENU_CHANGES = "Altera��es no Menu salvas com sucesso.";
	public static final String MSG_SUCCESS_SAVE_SUBMIT_TEACHING_PLAN = "Submiss�o do Plano de Ensino para avalia��o efetuada com sucesso.";

	/** Delete Sucess */
	public static final String MSG_SUCCESS_DELETE_ACADEMIC_PERIOD = "Per�odo Acad�mico removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ACADEMIC_PERIOD_COURSE = "Associa��o do Per�odo Acad�mico com o Curso removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_AUTHOR = "Autor removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_BIBLIOGRAPHY= "Bibliografia removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_BIBLIOGRAPHY_AUTHOR= "Associa��o da Bibliografia com o Autor removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_CAMPUS= "Campus removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_CAMPUS_COURSE= "Associa��o do Curso com o Campus removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_CLASS_PROGRAMMING = "Programa��o da Aula removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_COURSE = "Curso removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_DISCIPLINE = "Disciplina removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_DISCIPLINE_BIBLIOGRAPHY = "Bibliografia da Disciplina removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_EVALUATION= "Avalia��o removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PPC= "PPC removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PPC_DISCIPLINE= "Associa��o da Disciplina com o PPC removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PROCEDURE_EVALUATION= "Processo de avalia��o removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_RECTORY= "Reitoria removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_RESPONSIBLE= "Respons�vel removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ROLE= "Fun��o removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ROLE_VERIFICATION = "Verifica��o da Fun��o removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_TEACHING_PLAN = "Plano de Ensino removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_TRAINEESHIP = "Est�gio removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_USER= "Usu�rio removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_USER_ROLE = "Fun��o do Usu�rio removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_VERIFICATION = "Verifica��o removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PAGE = "P�gina removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PAGE_ROLE = "Fun��o da P�gina removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ACTION = "A��o removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_MENU = "Menu removido com sucesso.";

	/** Save Errors */
	public static final String MSG_ERROR_SAVE_PPC = "Erro ao salvar o PPC.";
	public static final String MSG_ERROR_SAVE_COURSE = "Erro ao salvar o curso.";
	public static final String MSG_ERROR_SAVE_CAMPUS_COURSE = "Erro ao salvar o campus do curso.";
	public static final String MSG_ERROR_SAVE_TRAINEESHIP = "Erro ao salvar o est�gio.";
	public static final String MSG_ERROR_SAVE_DISCIPLINE = "Erro ao salvar a disciplina.";
	public static final String MSG_ERROR_SAVE_CAMPUS = "Erro ao salvar o campus.";
	public static final String MSG_ERROR_SAVE_RECTORY = "Erro ao salvar a reitoria.";
	public static final String MSG_ERROR_SAVE_BIBLIOGRAPHY = "Erro ao salvar a bibliografia.";
	public static final String MSG_ERROR_SAVE_BIBLIOGRAPHY_AUTHOR = "Erro ao salvar a bibliografia e autor.";
	public static final String MSG_ERROR_SAVE_AUTHOR = "Erro ao salvar o autor.";
	public static final String MSG_ERROR_SAVE_TEACHING_PLAN = "Erro ao salvar o plano de ensino.";
	public static final String MSG_ERROR_SAVE_EVALUATION = "Erro ao salvar a Avalia��o.";
	public static final String MSG_ERROR_SAVE_ROLE = "Erro ao salvar a fun��o.";
	public static final String MSG_ERROR_SAVE_ACADEMIC_PERIOD_COURSE = "Erro ao salvar o per�odo acad�mico do curso.";
	public static final String MSG_ERROR_SAVE_PROCEDURE_EVALUATION = "Erro ao salvar o procedimento avaliativo.";
	public static final String MSG_ERROR_SAVE_PPC_DISCIPLINE = "Erro ao salvar a disciplina do PPC.";
	public static final String MSG_ERROR_SAVE_VERIFICATION = "Erro ao salvar verifica��o.";
	public static final String MSG_ERROR_SAVE_ROLE_VERIFICATION = "Erro ao salvar verifica��o da fun��o.";
	public static final String MSG_ERROR_SAVE_CLASS_PROGRAMMING = "Erro ao salvar programa��o de aula.";
	public static final String MSG_ERROR_SAVE_RESPONSIBLE = "Erro ao salvar o respons�vel.";
	public static final String MSG_ERROR_SAVE_USER_ROLE = "Erro ao salvar a fun��o do usu�rio.";
	public static final String MSG_ERROR_SAVE_DISCIPLINE_BIBLIOGRAPHY = "Erro ao salvar bibliografia da disciplina";
	public static final String MSG_ERROR_SAVE_ACADEMIC_PERIOD = "Erro ao salvar per�odo acad�mico";
	public static final String MSG_ERROR_SAVE_PAGE = "Erro ao salvar p�gina";
	public static final String MSG_ERROR_SAVE_PAGE_ROLE = "Erro ao salvar fun��o da p�gina";
	public static final String MSG_ERROR_SAVE_ACTION = "Erro ao salvar a a��o.";
	public static final String MSG_ERROR_SAVE_MENU = "Erro ao salvar o menu.";
	public static final String MSG_ERROR_SAVE_USER = "Erro ao salvar usu�rio";
	public static final String MSG_ERROR_SAVE_SUBMIT_TEACHING_PLAN = "Erro ao submeter Plano de Ensino para Avalia��o.";
	public static final String MSG_ERROR_SAVE_MENU_CHANGES = "N�o � poss�vel adicionar um Menu a um Submenu.";

	/** Loading Errors */
	public static final String MSG_ERROR_LOADING_LIST_PPCS = "Erro ao listar os PPCs.";
	public static final String MSG_ERROR_LOADING_LIST_PPCS_BY_COURSE = "Erro ao listar os PPCs pelo Curso.";
	public static final String MSG_ERROR_LOADING_LIST_COURSES = "Erro ao listar os cursos.";
	public static final String MSG_ERROR_LOADING_LIST_TRAINEESHIPS = "Erro ao listar os est�gios.";
	public static final String MSG_ERROR_LOADING_LIST_DISCIPLINES = "Erro ao listar as disciplinas.";
	public static final String MSG_ERROR_LOADING_LIST_DISCIPLINES_BY_PPC = "Erro ao listar as disciplinas pelo PPC.";
	public static final String MSG_ERROR_LOADING_LIST_RESPONSIBLES_BY_TEACHING_PLAN = "Erro ao listar os respons�veis pelo plano de ensino.";
	public static final String MSG_ERROR_LOADING_LIST_CAMPUS = "Erro ao listar os campus.";
	public static final String MSG_ERROR_LOADING_LIST_CAMPUS_COURSE = "Erro ao listar os campus do curso.";
	public static final String MSG_ERROR_LOADING_LIST_RECTORIES = "Erro ao listar as reitorias.";
	public static final String MSG_ERROR_LOADING_LIST_BIBLIOGRAPHIES = "Erro ao listar as bibliografias.";
	public static final String MSG_ERROR_LOADING_LIST_BIBLIOGRAPHIES_AUTHORS = "Erro ao listar as bibliografias e autores.";
	public static final String MSG_ERROR_LOADING_LIST_AUTHORS = "Erro ao listar os autores.";
	public static final String MSG_ERROR_LOADING_LIST_TEACHING_PLANS = "Erro ao listar os planos de ensino.";
	public static final String MSG_ERROR_LOADING_LIST_TEACHING_PLANS_BY_DISCIPLINE = "Erro ao listar os planos de ensino pela disciplina.";
	public static final String MSG_ERROR_LOADING_LIST_EVALUATIONS = "Erro ao listar as avalia��es.";
	public static final String MSG_ERROR_LOADING_LIST_ROLES = "Erro ao listar as fun��es.";
	public static final String MSG_ERROR_LOADING_LIST_ACADEMIC_PERIOD_COURSE = "Erro ao listar os per�odos acad�micos dos cursos.";
	public static final String MSG_ERROR_LOADING_LIST_PROCEDURE_EVALUATION = "Erro ao listar os procedimentos avaliativos.";
	public static final String MSG_ERROR_LOADING_LIST_PPC_DISCIPLINE = "Erro ao listar as disciplinas do PPC.";
	public static final String MSG_ERROR_LOADING_LIST_VERIFICATION = "Erro ao listar as verifica��os.";
	public static final String MSG_ERROR_LOADING_LIST_ROLE_VERIFICATION = "Erro ao listar as verifica��os das fun��es.";
	public static final String MSG_ERROR_LOADING_LIST_CLASS_PROGRAMMING = "Erro ao listar as programa��es de aula.";
	public static final String MSG_ERROR_LOADING_LIST_RESPONSIBLES = "Erro ao listar os respons�veis.";
	public static final String MSG_ERROR_LOADING_LIST_USER_ROLE = "Erro ao listar as fun��es dos usu�rios.";
	public static final String MSG_ERROR_LOADING_LIST_USERS = "Erro ao listar os usu�rios.";
	public static final String MSG_ERROR_LOADING_LIST_DISCIPLINE_BIBLIOGRAPHY = "Erro ao listar as bibliografias das disciplinas.";
	public static final String MSG_ERROR_LOADING_LIST_PAGE = "Erro ao listar as p�ginas.";
	public static final String MSG_ERROR_LOADING_LIST_PAGE_ROLE = "Erro ao listar as fun��es das p�ginas.";
	public static final String MSG_ERROR_LOADING_LIST_ACTIONS = "Erro ao listar as a��es.";
	public static final String MSG_ERROR_LOADING_LIST_MENUS = "Erro ao listar os menus.";
	public static final String MSG_ERROR_LOADING_COMPLETE_TEACHING_PLANS = "Erro ao listar o plano de ensino completo.";

	/** Delete Errors */
	public static final String MSG_ERROR_DELETE_PPC = "Erro ao excluir o PPC.";	
	public static final String MSG_ERROR_DELETE_COURSE = "Erro ao excluir o curso.";
	public static final String MSG_ERROR_DELETE_TRAINEESHIP = "Erro ao excluir o est�gio.";
	public static final String MSG_ERROR_DELETE_DISCIPLINE = "Erro ao excluir a disciplina.";
	public static final String MSG_ERROR_DELETE_CAMPUS = "Erro ao excluir o campus.";
	public static final String MSG_ERROR_DELETE_CAMPUS_COURSE = "Erro ao excluir o campus do curso.";
	public static final String MSG_ERROR_DELETE_RECTORY = "Erro ao excluir a reitoria.";
	public static final String MSG_ERROR_DELETE_BIBLIOGRAPHY = "Erro ao excluir a bibliografia.";
	public static final String MSG_ERROR_DELETE_BIBLIOGRAPHY_AUTHOR = "Erro ao excluir a bibliografia e autores.";
	public static final String MSG_ERROR_DELETE_AUTHOR = "Erro ao excluir o autor.";
	public static final String MSG_ERROR_DELETE_TEACHING_PLAN = "Erro ao excluir o plano de ensino.";
	public static final String MSG_ERROR_DELETE_EVALUATION = "Erro ao excluir a avalia��o.";
	public static final String MSG_ERROR_DELETE_ROLE = "Erro ao excluir a fun��o.";
	public static final String MSG_ERROR_DELETE_ACADEMIC_PERIOD_COURSE = "Erro ao excluir o Per�odo Acad�mico do Curso.";
	public static final String MSG_ERROR_DELETE_PROCEDURE_EVALUATION = "Erro ao excluir o procedimento avaliativo.";
	public static final String MSG_ERROR_DELETE_PPC_DISCIPLINE = "Erro ao excluir a disciplina do PPC.";
	public static final String MSG_ERROR_DELETE_VERIFICATION = "Erro ao excluir a verifica��o.";
	public static final String MSG_ERROR_DELETE_RESPONSIBLE = "Erro ao excluir o responsible.";
	public static final String MSG_ERROR_DELETE_ROLE_VERIFICATION = "Erro ao excluir a verifica��o da fun��o.";
	public static final String MSG_ERROR_DELETE_CLASS_PROGRAMMING = "Erro ao excluir a programa��o de aula.";
	public static final String MSG_ERROR_DELETE_USER_ROLE = "Erro ao excluir a fun��o do usu�rio.";
	public static final String MSG_ERROR_DELETE_DISCIPLINE_BIBLIOGRAPHY = "Erro ao excluir a bibliografia da disciplina.";
	public static final String MSG_ERROR_DELETE_ACADEMIC_PERIOD = "Erro ao excluir o Per�odo Acad�mico.";	
	public static final String MSG_ERROR_DELETE_PAGE = "Erro ao excluir a p�gina.";	
	public static final String MSG_ERROR_DELETE_PAGE_ROLE = "Erro ao excluir a fun��o da p�gina.";	
	public static final String MSG_ERROR_DELETE_MENU = "Erro ao excluir o menu.";
	public static final String MSG_ERROR_DELETE_USER = "Erro ao excluir usu�rio";
		
	/** Duplicate Entry */
	public static final String DUPLICATE_ENTRY = "duplicate entry";
	public static final String DUPLICATE_COURSE_NAME = "name";

	/** Insert Duplicate Entry Errors */
	public static final String MSG_ERROR_SAVE_DUPLICATE_COURSE_NAME = "N�o � poss�vel salvar os dados. J� existe um registro com o mesmo valor para o campo Nome.";
	
	/** Constraint Names */
	public static final String CONSTRAINT_PAGE_MENU = "menu";
	public static final String CONSTRAINT_PAGE_ROLE = "pages_roles";
	public static final String CONSTRAINT_ROLE_PAGE_ROLE = "pages_roles";
	public static final String CONSTRAINT_USER_ROLE = "users_roles";
	public static final String CONSTRAINT_USER_RESPONSIBLE = "responsibles";
	public static final String CONSTRAINT_TEACHING_PLAN_CLASS_PROGRAMMING = "classes_programming";
	public static final String CONSTRAINT_COURSE_ACADEMIC_PERIOD = "academics_periods_course";
	public static final String CONSTRAINT_COURSE_CAMPUS = "campus_courses";
	public static final String CONSTRAINT_COURSE_PPC = "ppcs";
	public static final String CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY = "disciplines_bibliographies";
	public static final String CONSTRAINT_DISCIPLINE_PPC = "ppcs_disciplines";
	public static final String CONSTRAINT_DISCIPLINE_TEACHING_PLAN = "teaching_plans";
	public static final String CONSTRAINT_EVALUATION_PROCEDURE_EVALUATION = "procedures_evaluations";
	public static final String CONSTRAINT_PPC_DISCIPLINE = "ppcs_disciplines"; 
	public static final String CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN = "teaching_plans";
	public static final String CONSTRAINT_PPC_COURSE = "courses";
	public static final String CONSTRAINT_PPC_TRAINEESHIP = "traineeships";
	public static final String CONSTRAINT_PROCEDURE_EVALUATION_TEACHING_PLAN = "teaching_plans";
	public static final String CONSTRAINT_PROCEDURE_EVALUATION_EVALUATION = "evaluations";
	public static final String CONSTRAINT_RECTORY_CAMPUS = "campus";
	public static final String CONSTRAINT_TEACHING_PLAN_RESPONSIBLE = "responsibles";
	public static final String CONSTRAINT_TEACHING_PLAN_VERIFICATION = "verifications";
	public static final String CONSTRAINT_TEACHING_PLAN_DISCIPLINE = "disciplines";
	public static final String CONSTRAINT_TEACHING_PLAN_PROCEDURE_EVALUATION = "evaluations";
	public static final String CONSTRAINT_TRAINEESHIP_PPC = "traineeships";
	public static final String CONSTRAINT_USER_VERIFICATION = "verifications";
	public static final String CONSTRAINT_VERIFICATION_TEACHING_PLAN = "teaching_plans";
	public static final String CONSTRAINT_VERIFICATION_ROLE = "roles";
	public static final String CONSTRAINT_VERIFICATION_USER = "users";
	public static final String CONSTRAINT_BIBLIOGRAPHY_AUTHOR = "bibliographies_authors";
	public static final String CONSTRAINT_MENU_PARENT_MENU = "menu";
	public static final String CONSTRAINT_RESPONSIBLE_VERIFICATION = "verifications";
	
	/** Insert Constraint Errors */	
	public static final String MSG_ERROR_SAVE_CONSTRAINT_BIBLIOGRAPHY_AUTHOR = "N�o � poss�vel salvar os dados. J� existe uma associa��o da Bibliografia com o Autor selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY = "N�o � poss�vel salvar os dados. J� existe uma associa��o da Disciplina com a Bibliografia selecionadas.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_PPC_DISCIPLINE = "N�o � poss�vel salvar os dados. J� existe uma associa��o do PPC com a Disciplina selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_USER_ROLE = "N�o � poss�vel salvar os dados. J� existe uma associa��o do Usu�rio com a Fun��o selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_PROCEDURE_EVALUATION = "N�o � poss�vel salvar os dados. J� existe uma associa��o do PPC com a Disciplina selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_ROLE_VERIFICATION = "N�o � poss�vel salvar os dados. J� existe uma Fun��o com a Verifica��o selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_PAGE = "N�o � poss�vel salvar os dados. J� existe uma associa��o da p�gina com a Fun��o selecionada.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_VERIFICATION = "N�o � poss�vel salvar. Houve algum problema entre o relacionamento das entidades.";

	/** Delete Constraint Errors */	
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_TEACHING_PLAN = "N�o � possivel excluir a Verifica��o pois est� associado a um Plano de Ensino";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_ROLE = "N�o � possivel excluir a Verifica��o pois est� associado a uma Fun��o";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_USER = "N�o � possivel excluir a Verifica��o pois est� associado a um Usu�rio";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_VERIFICATION = "N�o � possivel excluir o Usu�rio pois est� associado a uma Verifica��o";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_ROLE = "N�o � possivel excluir o Usu�rio pois est� associado a uma Fun��o";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_ROLE_RESPONSIBLE = "N�o � possivel excluir a Fun��o do Usu�rio pois est� associada a um Respons�vel";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_RESPONSIBLE = "N�o � possivel excluir o Plano de Ensino pois est� associado a um Respons�vel";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_VERIFICATION = "N�o � possivel excluir o Plano de Ensino pois est� associado a uma Verifica��o";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_DISCIPLINE = "N�o � possivel excluir o Plano de Ensino pois est� associado a uma Disciplina";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_PROCEDURE_EVALUATION = "N�o � possivel excluir o Plano de Ensino pois est� associado a um Procedimento de Avalia��o";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PROCEDURE_EVALUATION_TEACHING_PLAN = "N�o � possivel excluir o Procedimento de Avalia��o pois est� associado a um plano de ensino";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PROCEDURE_EVALUATION_EVALUATION = "N�o � possivel excluir o Procedimento de Avalia��o pois est� associado a uma Avalia��o";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_COURSE = "N�o � possivel excluir o PPC pois est� associado a um Curso";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_TRAINEESHIP = "N�o � possivel excluir o PPC pois est� associado a um Est�gio";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TRAINEESHIP_PPC = "N�o � poss�vel excluir o Est�gio pois est� associado a um PPC.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE = "N�o � poss�vel excluir o PPC pois est� associado a uma Disciplina.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN = "N�o � poss�vel excluir a associa��o do PPC com a Disciplina pois est� associada com um Plano de Ensino.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_COURSE_ACADEMIC_PERIOD = "N�o � poss�vel excluir o Curso pois est� associado a um Per�odo Acad�mico.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_CLASS_PROGRAMMING = "N�o � poss�vel excluir o Plano de Ensino pois est� associado a uma Programa��o de Aula.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_BIBLIOGRAPHY_AUTHOR = "N�o � poss�vel excluir a Bibliografia pois est� associado a um Autor.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_BIBLIOGRAPHY_DISCIPLINE = "N�o � poss�vel excluir a Bibliografia pois est� associado a uma Disciplina.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_ROLES = "N�o � poss�vel excluir a Verifica��o pois est� associado a uma Fun��o.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_EVALUATION_PROCEDURE_EVALUATION = "N�o � poss�vel excluir a Avalia��o pois est� associado a um Procedimento de Avalia��o.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY = "N�o � poss�vel excluir a Disciplina pois est� associado a uma Bilbliografia.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_CAMPUS_COURSE = "N�o � poss�vel excluir o Campus pois est� associado a um Curso.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_RECTORY_CAMPUS = "N�o � poss�vel excluir a Reitoria pois est� associado a um Campus.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_AUTHOR_BIBLIOGRAPHY = "N�o � poss�vel excluir o Autor pois est� associado a uma Bibliografia.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ACADEMIC_PERIOD_COURSE = "N�o � poss�vel excluir o Per�odo Acad�mico pois est� associado a um Curso.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ROLE_VEdRIFICATION = "N�o � poss�vel excluir a Fun��o pois est� associado a uma Verifica��o.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PAGE_ROLE = "N�o � poss�vel excluir a P�gina pois est� associada a uma Fun��o.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ACTION_PAGE_ROLE = "N�o � poss�vel excluir a A��o pois est� associado a uma Fun��o de P�gina.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PAGE_MENU = "N�o � poss�vel excluir a P�gina pois est� associada a um Menu.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ROLE_PAGE_ROLE = "N�o � poss�vel excluir a Fun��o pois est� associada a uma Fun��o de P�gina.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ROLE_USER = "N�o � poss�vel excluir a Fun��o pois est� associada a um Usu�rio.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_RESPONSIBLE = "N�o � poss�vel excluir o Usu�rio pois est� associado a um Respons�vel.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_COURSE_CAMPUS = "N�o � possivel excluir o Curso pois est� associado a um Campus";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_COURSE_PPC = "N�o � possivel excluir o Curso pois est� associado a um Ppc";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_PPC = "N�o � possivel excluir a Disciplina pois est� associado a um Ppc";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_TEACHING_PLAN = "N�o � possivel excluir a Disciplina pois est� associado a um Plano de Ensino";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_MENU_PARENT_MENU = "N�o � poss�vel excluir o Menu. Exclua todos os submenus e tente novamente";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_RESPONSIBLE_VERIFICATION = "N�o � poss�vel excluir o Respons�vel pois est� associado a uma Verifica��o.";
}