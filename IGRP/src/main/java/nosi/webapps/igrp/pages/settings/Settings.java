package nosi.webapps.igrp.pages.settings;
import nosi.core.config.Config;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.SeparatorList;

public class Settings extends Model{		
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;
	@RParam(rParamName = "p_nome")
	private String nome;
	@RParam(rParamName = "p_email")
	private String email;
	@RParam(rParamName = "p_username")
	private String username;
	@RParam(rParamName = "p_telefone")
	private String telefone;
	@RParam(rParamName = "p_telemovel")
	private String telemovel;
	@RParam(rParamName = "p_ultimo_acesso_igrp")
	private String ultimo_acesso_igrp;
	@RParam(rParamName = "p_ultimo_acesso_rede_estado")
	private String ultimo_acesso_rede_estado;
	@RParam(rParamName = "p_password_expira_em")
	private String password_expira_em;
	@RParam(rParamName = "p_view_1_img")
	private String view_1_img;
	@RParam(rParamName = "p_organica")
	private String organica;
	@RParam(rParamName = "p_perfil")
	private String perfil;
	@RParam(rParamName = "p_janela_inicil")
	private String janela_inicil;
	@RParam(rParamName = "p_idioma")
	private String idioma;
	@RParam(rParamName = "p_auto_complete")
	private int auto_complete;
	@RParam(rParamName = "p_auto_complete_check")
	private int auto_complete_check;
	@RParam(rParamName = "p_notificacao")
	private String notificacao;
	@RParam(rParamName = "p_receber_notificacao")
	private int receber_notificacao;
	@RParam(rParamName = "p_receber_notificacao_check")
	private int receber_notificacao_check;
	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}
	public String getTelefone(){
		return this.telefone;
	}
	
	public void setTelemovel(String telemovel){
		this.telemovel = telemovel;
	}
	public String getTelemovel(){
		return this.telemovel;
	}
	
	public void setUltimo_acesso_igrp(String ultimo_acesso_igrp){
		this.ultimo_acesso_igrp = ultimo_acesso_igrp;
	}
	public String getUltimo_acesso_igrp(){
		return this.ultimo_acesso_igrp;
	}
	
	public void setUltimo_acesso_rede_estado(String ultimo_acesso_rede_estado){
		this.ultimo_acesso_rede_estado = ultimo_acesso_rede_estado;
	}
	public String getUltimo_acesso_rede_estado(){
		return this.ultimo_acesso_rede_estado;
	}
	
	public void setPassword_expira_em(String password_expira_em){
		this.password_expira_em = password_expira_em;
	}
	public String getPassword_expira_em(){
		return this.password_expira_em;
	}
	
	public void setView_1_img(String view_1_img){
		this.view_1_img = view_1_img;
	}
	public String getView_1_img(){
		return this.view_1_img;
	}
	
	public void setOrganica(String organica){
		this.organica = organica;
	}
	public String getOrganica(){
		return this.organica;
	}
	
	public void setPerfil(String perfil){
		this.perfil = perfil;
	}
	public String getPerfil(){
		return this.perfil;
	}
	
	public void setJanela_inicil(String janela_inicil){
		this.janela_inicil = janela_inicil;
	}
	public String getJanela_inicil(){
		return this.janela_inicil;
	}
	
	public void setIdioma(String idioma){
		this.idioma = idioma;
	}
	public String getIdioma(){
		return this.idioma;
	}
	
	public void setAuto_complete(int auto_complete){
		this.auto_complete = auto_complete;
	}
	public int getAuto_complete(){
		return this.auto_complete;
	}
	public void setAuto_complete_check(int auto_complete_check){
		this.auto_complete_check = auto_complete_check;
	}
	public int getAuto_complete_check(){
		return this.auto_complete_check;
	}
	
	public void setNotificacao(String notificacao){
		this.notificacao = notificacao;
	}
	public String getNotificacao(){
		return this.notificacao;
	}
	
	public void setReceber_notificacao(int receber_notificacao){
		this.receber_notificacao = receber_notificacao;
	}
	public int getReceber_notificacao(){
		return this.receber_notificacao;
	}
	public void setReceber_notificacao_check(int receber_notificacao_check){
		this.receber_notificacao_check = receber_notificacao_check;
	}
	public int getReceber_notificacao_check(){
		return this.receber_notificacao_check;
	}


}
