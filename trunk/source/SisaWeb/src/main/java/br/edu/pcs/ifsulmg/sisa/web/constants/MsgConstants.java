/*
* File:	     MsgConstants.java
* Creation date: 04/06/2015
* Author:        Lênio
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
	public static final String MSG_SUBJECT = "Recuperação de Senha";
	public static final String MSG_CONTENT = "Sua nova senha é '%s'.";
	public static final String MSG_EMAIL_EXIST = "O e-mail informado já existe. ";
	public static final String MSG_SUCCESS_SEND_PWD = "Uma nova senha será enviada para seu e-mail.";
	public static final String MSG_DATE_CONVERSION_ERROR = "O valor fornecido como data é inválido";
	public static final String MSG_CURRENT_PASSWORD_NOT_MATCH = "Senha atual não confere";
	public static final String MSG_EDIT_USER_KEY = "lbl_edit_user";
	public static final String MSG_LOGOUT_KEY = "lbl_user_logout";
	public static final String UNKHNOWN_USER = "Usuário";
		
	/** The Constant LOGIN Messages */	
	public static final String MSG_ERROR_SEND_PASSWORD = "Erro ao enviar nova senha";	
	public static final String MSG_ERROR_LOAD_USERS_LIST = "Erro ao carregar lista de usuários";
	public static final String MSG_ERROR_LOAD_USER = "Erro ao carregar usuário";
	public static final String MSG_ERROR_LOGIN = "Email ou senha inválidos";
	public static final String MSG_ERRO_EMAIL_NOT_EXIST = "Email informando não está cadastrado.";
	public static final String MSG_ERROR_USER_HAS_NO_ROLE = "Usuário não possui função associada no sistema";
	public static final String MSG_INFO_LDAP_PASSWORDS = "O sistema utiliza a mesma senha do e-mail institucional. Caso não possua a senha, entre em contato com o suporte da Instituição.";

	/** Default System Values Errors */
	public static final String MSG_ERRO_DEFAULT_USER_ROLE = "Esta função de usuário é essencial ao sistema e não pode ser excluída.";
	
	/** Pdf Errors */
	public static final String MSG_ERROR_GENERATING_PDF_REPORT = "Erro ao gerar o relatório PDF.";
	public static final String MSG_ERROR_GENERATING_PDF_RESPONSE = "Erro ao gerar a resposta contendo o arquivo PDF.";
	public static final String MSG_ERROR_GETTING_PDF_FONT = "Erro ao recuperar a fonte para geração do arquivo PDF.";
	
	/** Include Sucess */
	public static final String MSG_SUCCESS_SAVE_ACADEMIC_PERIOD = "Período Acadêmico salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ACADEMIC_PERIOD_COURSE = "Curso x Período Acadêmico salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_AUTHOR = "Autor salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_BIBLIOGRAPHY= "Bibliografia salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_BIBLIOGRAPHY_AUTHOR= "Bibliografia do Autor salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_CAMPUS= "Campus salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_CAMPUS_COURSE= "Curso x Campus salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_CLASS_PROGRAMMING = "Programação da Aula salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_COURSE = "Curso salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_DISCIPLINE = "Disciplina salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_DISCIPLINE_BIBLIOGRAPHY = "Bibliografia da Disciplina salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_EVALUATION= "Avaliação salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PPC= "PPC salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PPC_DISCIPLINE= "Disciplina do PPC salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PROCEDURE_EVALUATION= "Processo de avaliação salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_RECTORY= "Reitoria salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_RESPONSIBLE= "Responsável salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ROLE= "Função salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ROLE_VERIFICATION = "Verificação da Função salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_TEACHING_PLAN = "Plano de Ensino salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_TRAINEESHIP = "Estágio salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_USER= "Usuário salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_USER_ROLE = "Função do Usuário salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_VERIFICATION = "Verificação salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PAGE = "Página salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_PAGE_ROLE = "Função da página salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_ACTION = "Ação salva com sucesso.";
	public static final String MSG_SUCCESS_SAVE_MENU = "Menu salvo com sucesso.";
	public static final String MSG_SUCCESS_SAVE_MENU_CHANGES = "Alterações no Menu salvas com sucesso.";
	public static final String MSG_SUCCESS_SAVE_SUBMIT_TEACHING_PLAN = "Submissão do Plano de Ensino para avaliação efetuada com sucesso.";

	/** Delete Sucess */
	public static final String MSG_SUCCESS_DELETE_ACADEMIC_PERIOD = "Período Acadêmico removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ACADEMIC_PERIOD_COURSE = "Associação do Período Acadêmico com o Curso removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_AUTHOR = "Autor removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_BIBLIOGRAPHY= "Bibliografia removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_BIBLIOGRAPHY_AUTHOR= "Associação da Bibliografia com o Autor removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_CAMPUS= "Campus removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_CAMPUS_COURSE= "Associação do Curso com o Campus removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_CLASS_PROGRAMMING = "Programação da Aula removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_COURSE = "Curso removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_DISCIPLINE = "Disciplina removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_DISCIPLINE_BIBLIOGRAPHY = "Bibliografia da Disciplina removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_EVALUATION= "Avaliação removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PPC= "PPC removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PPC_DISCIPLINE= "Associação da Disciplina com o PPC removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PROCEDURE_EVALUATION= "Processo de avaliação removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_RECTORY= "Reitoria removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_RESPONSIBLE= "Responsável removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ROLE= "Função removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ROLE_VERIFICATION = "Verificação da Função removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_TEACHING_PLAN = "Plano de Ensino removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_TRAINEESHIP = "Estágio removido com sucesso.";
	public static final String MSG_SUCCESS_DELETE_USER= "Usuário removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_USER_ROLE = "Função do Usuário removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_VERIFICATION = "Verificação removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PAGE = "Página removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_PAGE_ROLE = "Função da Página removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_ACTION = "Ação removida com sucesso.";
	public static final String MSG_SUCCESS_DELETE_MENU = "Menu removido com sucesso.";

	/** Save Errors */
	public static final String MSG_ERROR_SAVE_PPC = "Erro ao salvar o PPC.";
	public static final String MSG_ERROR_SAVE_COURSE = "Erro ao salvar o curso.";
	public static final String MSG_ERROR_SAVE_CAMPUS_COURSE = "Erro ao salvar o campus do curso.";
	public static final String MSG_ERROR_SAVE_TRAINEESHIP = "Erro ao salvar o estágio.";
	public static final String MSG_ERROR_SAVE_DISCIPLINE = "Erro ao salvar a disciplina.";
	public static final String MSG_ERROR_SAVE_CAMPUS = "Erro ao salvar o campus.";
	public static final String MSG_ERROR_SAVE_RECTORY = "Erro ao salvar a reitoria.";
	public static final String MSG_ERROR_SAVE_BIBLIOGRAPHY = "Erro ao salvar a bibliografia.";
	public static final String MSG_ERROR_SAVE_BIBLIOGRAPHY_AUTHOR = "Erro ao salvar a bibliografia e autor.";
	public static final String MSG_ERROR_SAVE_AUTHOR = "Erro ao salvar o autor.";
	public static final String MSG_ERROR_SAVE_TEACHING_PLAN = "Erro ao salvar o plano de ensino.";
	public static final String MSG_ERROR_SAVE_EVALUATION = "Erro ao salvar a Avaliação.";
	public static final String MSG_ERROR_SAVE_ROLE = "Erro ao salvar a função.";
	public static final String MSG_ERROR_SAVE_ACADEMIC_PERIOD_COURSE = "Erro ao salvar o período acadêmico do curso.";
	public static final String MSG_ERROR_SAVE_PROCEDURE_EVALUATION = "Erro ao salvar o procedimento avaliativo.";
	public static final String MSG_ERROR_SAVE_PPC_DISCIPLINE = "Erro ao salvar a disciplina do PPC.";
	public static final String MSG_ERROR_SAVE_VERIFICATION = "Erro ao salvar verificação.";
	public static final String MSG_ERROR_SAVE_ROLE_VERIFICATION = "Erro ao salvar verificação da função.";
	public static final String MSG_ERROR_SAVE_CLASS_PROGRAMMING = "Erro ao salvar programação de aula.";
	public static final String MSG_ERROR_SAVE_RESPONSIBLE = "Erro ao salvar o responsável.";
	public static final String MSG_ERROR_SAVE_USER_ROLE = "Erro ao salvar a função do usuário.";
	public static final String MSG_ERROR_SAVE_DISCIPLINE_BIBLIOGRAPHY = "Erro ao salvar bibliografia da disciplina";
	public static final String MSG_ERROR_SAVE_ACADEMIC_PERIOD = "Erro ao salvar período acadêmico";
	public static final String MSG_ERROR_SAVE_PAGE = "Erro ao salvar página";
	public static final String MSG_ERROR_SAVE_PAGE_ROLE = "Erro ao salvar função da página";
	public static final String MSG_ERROR_SAVE_ACTION = "Erro ao salvar a ação.";
	public static final String MSG_ERROR_SAVE_MENU = "Erro ao salvar o menu.";
	public static final String MSG_ERROR_SAVE_USER = "Erro ao salvar usuário";
	public static final String MSG_ERROR_SAVE_SUBMIT_TEACHING_PLAN = "Erro ao submeter Plano de Ensino para Avaliação.";
	public static final String MSG_ERROR_SAVE_MENU_CHANGES = "Não é possível adicionar um Menu a um Submenu.";

	/** Loading Errors */
	public static final String MSG_ERROR_LOADING_LIST_PPCS = "Erro ao listar os PPCs.";
	public static final String MSG_ERROR_LOADING_LIST_PPCS_BY_COURSE = "Erro ao listar os PPCs pelo Curso.";
	public static final String MSG_ERROR_LOADING_LIST_COURSES = "Erro ao listar os cursos.";
	public static final String MSG_ERROR_LOADING_LIST_TRAINEESHIPS = "Erro ao listar os estágios.";
	public static final String MSG_ERROR_LOADING_LIST_DISCIPLINES = "Erro ao listar as disciplinas.";
	public static final String MSG_ERROR_LOADING_LIST_DISCIPLINES_BY_PPC = "Erro ao listar as disciplinas pelo PPC.";
	public static final String MSG_ERROR_LOADING_LIST_RESPONSIBLES_BY_TEACHING_PLAN = "Erro ao listar os responsáveis pelo plano de ensino.";
	public static final String MSG_ERROR_LOADING_LIST_CAMPUS = "Erro ao listar os campus.";
	public static final String MSG_ERROR_LOADING_LIST_CAMPUS_COURSE = "Erro ao listar os campus do curso.";
	public static final String MSG_ERROR_LOADING_LIST_RECTORIES = "Erro ao listar as reitorias.";
	public static final String MSG_ERROR_LOADING_LIST_BIBLIOGRAPHIES = "Erro ao listar as bibliografias.";
	public static final String MSG_ERROR_LOADING_LIST_BIBLIOGRAPHIES_AUTHORS = "Erro ao listar as bibliografias e autores.";
	public static final String MSG_ERROR_LOADING_LIST_AUTHORS = "Erro ao listar os autores.";
	public static final String MSG_ERROR_LOADING_LIST_TEACHING_PLANS = "Erro ao listar os planos de ensino.";
	public static final String MSG_ERROR_LOADING_LIST_TEACHING_PLANS_BY_DISCIPLINE = "Erro ao listar os planos de ensino pela disciplina.";
	public static final String MSG_ERROR_LOADING_LIST_EVALUATIONS = "Erro ao listar as avaliações.";
	public static final String MSG_ERROR_LOADING_LIST_ROLES = "Erro ao listar as funções.";
	public static final String MSG_ERROR_LOADING_LIST_ACADEMIC_PERIOD_COURSE = "Erro ao listar os períodos acadêmicos dos cursos.";
	public static final String MSG_ERROR_LOADING_LIST_PROCEDURE_EVALUATION = "Erro ao listar os procedimentos avaliativos.";
	public static final String MSG_ERROR_LOADING_LIST_PPC_DISCIPLINE = "Erro ao listar as disciplinas do PPC.";
	public static final String MSG_ERROR_LOADING_LIST_VERIFICATION = "Erro ao listar as verificaçãos.";
	public static final String MSG_ERROR_LOADING_LIST_ROLE_VERIFICATION = "Erro ao listar as verificaçãos das funções.";
	public static final String MSG_ERROR_LOADING_LIST_CLASS_PROGRAMMING = "Erro ao listar as programações de aula.";
	public static final String MSG_ERROR_LOADING_LIST_RESPONSIBLES = "Erro ao listar os responsáveis.";
	public static final String MSG_ERROR_LOADING_LIST_USER_ROLE = "Erro ao listar as funções dos usuários.";
	public static final String MSG_ERROR_LOADING_LIST_USERS = "Erro ao listar os usuários.";
	public static final String MSG_ERROR_LOADING_LIST_DISCIPLINE_BIBLIOGRAPHY = "Erro ao listar as bibliografias das disciplinas.";
	public static final String MSG_ERROR_LOADING_LIST_PAGE = "Erro ao listar as páginas.";
	public static final String MSG_ERROR_LOADING_LIST_PAGE_ROLE = "Erro ao listar as funções das páginas.";
	public static final String MSG_ERROR_LOADING_LIST_ACTIONS = "Erro ao listar as ações.";
	public static final String MSG_ERROR_LOADING_LIST_MENUS = "Erro ao listar os menus.";
	public static final String MSG_ERROR_LOADING_COMPLETE_TEACHING_PLANS = "Erro ao listar o plano de ensino completo.";

	/** Delete Errors */
	public static final String MSG_ERROR_DELETE_PPC = "Erro ao excluir o PPC.";	
	public static final String MSG_ERROR_DELETE_COURSE = "Erro ao excluir o curso.";
	public static final String MSG_ERROR_DELETE_TRAINEESHIP = "Erro ao excluir o estágio.";
	public static final String MSG_ERROR_DELETE_DISCIPLINE = "Erro ao excluir a disciplina.";
	public static final String MSG_ERROR_DELETE_CAMPUS = "Erro ao excluir o campus.";
	public static final String MSG_ERROR_DELETE_CAMPUS_COURSE = "Erro ao excluir o campus do curso.";
	public static final String MSG_ERROR_DELETE_RECTORY = "Erro ao excluir a reitoria.";
	public static final String MSG_ERROR_DELETE_BIBLIOGRAPHY = "Erro ao excluir a bibliografia.";
	public static final String MSG_ERROR_DELETE_BIBLIOGRAPHY_AUTHOR = "Erro ao excluir a bibliografia e autores.";
	public static final String MSG_ERROR_DELETE_AUTHOR = "Erro ao excluir o autor.";
	public static final String MSG_ERROR_DELETE_TEACHING_PLAN = "Erro ao excluir o plano de ensino.";
	public static final String MSG_ERROR_DELETE_EVALUATION = "Erro ao excluir a avaliação.";
	public static final String MSG_ERROR_DELETE_ROLE = "Erro ao excluir a função.";
	public static final String MSG_ERROR_DELETE_ACADEMIC_PERIOD_COURSE = "Erro ao excluir o Período Acadêmico do Curso.";
	public static final String MSG_ERROR_DELETE_PROCEDURE_EVALUATION = "Erro ao excluir o procedimento avaliativo.";
	public static final String MSG_ERROR_DELETE_PPC_DISCIPLINE = "Erro ao excluir a disciplina do PPC.";
	public static final String MSG_ERROR_DELETE_VERIFICATION = "Erro ao excluir a verificação.";
	public static final String MSG_ERROR_DELETE_RESPONSIBLE = "Erro ao excluir o responsible.";
	public static final String MSG_ERROR_DELETE_ROLE_VERIFICATION = "Erro ao excluir a verificação da função.";
	public static final String MSG_ERROR_DELETE_CLASS_PROGRAMMING = "Erro ao excluir a programação de aula.";
	public static final String MSG_ERROR_DELETE_USER_ROLE = "Erro ao excluir a função do usuário.";
	public static final String MSG_ERROR_DELETE_DISCIPLINE_BIBLIOGRAPHY = "Erro ao excluir a bibliografia da disciplina.";
	public static final String MSG_ERROR_DELETE_ACADEMIC_PERIOD = "Erro ao excluir o Período Acadêmico.";	
	public static final String MSG_ERROR_DELETE_PAGE = "Erro ao excluir a página.";	
	public static final String MSG_ERROR_DELETE_PAGE_ROLE = "Erro ao excluir a função da página.";	
	public static final String MSG_ERROR_DELETE_MENU = "Erro ao excluir o menu.";
	public static final String MSG_ERROR_DELETE_USER = "Erro ao excluir usuário";
		
	/** Duplicate Entry */
	public static final String DUPLICATE_ENTRY = "duplicate entry";
	public static final String DUPLICATE_COURSE_NAME = "name";

	/** Insert Duplicate Entry Errors */
	public static final String MSG_ERROR_SAVE_DUPLICATE_COURSE_NAME = "Não é possível salvar os dados. Já existe um registro com o mesmo valor para o campo Nome.";
	
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
	public static final String MSG_ERROR_SAVE_CONSTRAINT_BIBLIOGRAPHY_AUTHOR = "Não é possível salvar os dados. Já existe uma associação da Bibliografia com o Autor selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY = "Não é possível salvar os dados. Já existe uma associação da Disciplina com a Bibliografia selecionadas.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_PPC_DISCIPLINE = "Não é possível salvar os dados. Já existe uma associação do PPC com a Disciplina selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_USER_ROLE = "Não é possível salvar os dados. Já existe uma associação do Usuário com a Função selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_PROCEDURE_EVALUATION = "Não é possível salvar os dados. Já existe uma associação do PPC com a Disciplina selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_ROLE_VERIFICATION = "Não é possível salvar os dados. Já existe uma Função com a Verificação selecionados.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_PAGE = "Não é possível salvar os dados. Já existe uma associação da página com a Função selecionada.";
	public static final String MSG_ERROR_SAVE_CONSTRAINT_VERIFICATION = "Não é possível salvar. Houve algum problema entre o relacionamento das entidades.";

	/** Delete Constraint Errors */	
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_TEACHING_PLAN = "Não é possivel excluir a Verificação pois está associado a um Plano de Ensino";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_ROLE = "Não é possivel excluir a Verificação pois está associado a uma Função";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_USER = "Não é possivel excluir a Verificação pois está associado a um Usuário";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_VERIFICATION = "Não é possivel excluir o Usuário pois está associado a uma Verificação";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_ROLE = "Não é possivel excluir o Usuário pois está associado a uma Função";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_ROLE_RESPONSIBLE = "Não é possivel excluir a Função do Usuário pois está associada a um Responsável";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_RESPONSIBLE = "Não é possivel excluir o Plano de Ensino pois está associado a um Responsável";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_VERIFICATION = "Não é possivel excluir o Plano de Ensino pois está associado a uma Verificação";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_DISCIPLINE = "Não é possivel excluir o Plano de Ensino pois está associado a uma Disciplina";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_PROCEDURE_EVALUATION = "Não é possivel excluir o Plano de Ensino pois está associado a um Procedimento de Avaliação";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PROCEDURE_EVALUATION_TEACHING_PLAN = "Não é possivel excluir o Procedimento de Avaliação pois está associado a um plano de ensino";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PROCEDURE_EVALUATION_EVALUATION = "Não é possivel excluir o Procedimento de Avaliação pois está associado a uma Avaliação";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_COURSE = "Não é possivel excluir o PPC pois está associado a um Curso";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_TRAINEESHIP = "Não é possivel excluir o PPC pois está associado a um Estágio";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TRAINEESHIP_PPC = "Não é possível excluir o Estágio pois está associado a um PPC.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE = "Não é possível excluir o PPC pois está associado a uma Disciplina.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN = "Não é possível excluir a associação do PPC com a Disciplina pois está associada com um Plano de Ensino.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_COURSE_ACADEMIC_PERIOD = "Não é possível excluir o Curso pois está associado a um Período Acadêmico.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_CLASS_PROGRAMMING = "Não é possível excluir o Plano de Ensino pois está associado a uma Programação de Aula.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_BIBLIOGRAPHY_AUTHOR = "Não é possível excluir a Bibliografia pois está associado a um Autor.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_BIBLIOGRAPHY_DISCIPLINE = "Não é possível excluir a Bibliografia pois está associado a uma Disciplina.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_ROLES = "Não é possível excluir a Verificação pois está associado a uma Função.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_EVALUATION_PROCEDURE_EVALUATION = "Não é possível excluir a Avaliação pois está associado a um Procedimento de Avaliação.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY = "Não é possível excluir a Disciplina pois está associado a uma Bilbliografia.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_CAMPUS_COURSE = "Não é possível excluir o Campus pois está associado a um Curso.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_RECTORY_CAMPUS = "Não é possível excluir a Reitoria pois está associado a um Campus.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_AUTHOR_BIBLIOGRAPHY = "Não é possível excluir o Autor pois está associado a uma Bibliografia.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ACADEMIC_PERIOD_COURSE = "Não é possível excluir o Período Acadêmico pois está associado a um Curso.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ROLE_VEdRIFICATION = "Não é possível excluir a Função pois está associado a uma Verificação.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PAGE_ROLE = "Não é possível excluir a Página pois está associada a uma Função.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ACTION_PAGE_ROLE = "Não é possível excluir a Ação pois está associado a uma Função de Página.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_PAGE_MENU = "Não é possível excluir a Página pois está associada a um Menu.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ROLE_PAGE_ROLE = "Não é possível excluir a Função pois está associada a uma Função de Página.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_ROLE_USER = "Não é possível excluir a Função pois está associada a um Usuário.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_USER_RESPONSIBLE = "Não é possível excluir o Usuário pois está associado a um Responsável.";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_COURSE_CAMPUS = "Não é possivel excluir o Curso pois está associado a um Campus";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_COURSE_PPC = "Não é possivel excluir o Curso pois está associado a um Ppc";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_PPC = "Não é possivel excluir a Disciplina pois está associado a um Ppc";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_TEACHING_PLAN = "Não é possivel excluir a Disciplina pois está associado a um Plano de Ensino";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_MENU_PARENT_MENU = "Não é possível excluir o Menu. Exclua todos os submenus e tente novamente";
	public static final String MSG_ERROR_DELETE_CONSTRAINT_RESPONSIBLE_VERIFICATION = "Não é possível excluir o Responsável pois está associado a uma Verificação.";
}