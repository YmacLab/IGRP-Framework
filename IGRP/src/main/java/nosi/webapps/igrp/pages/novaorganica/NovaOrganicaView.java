
package nosi.webapps.igrp.pages.novaorganica;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class NovaOrganicaView extends View {
	
	
	public Field sectionheader_1_text;
	public Field nome;
	public Field codigo;
	public Field ativo;
	public Field ativo_check;
	public Field nada;
	public Field aplicacao;
	public Field organizacao_pai;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_gravar;
	public NovaOrganicaView(NovaOrganica model){
		this.setPageTitle("Registar Organica");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		
		sectionheader_1_text.setValue(gt("Gestao de Organica - Novo"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		nome = new TextField(model,"nome");
		nome.setLabel(gt("Nome"));
		
		nome.propertie().add("name","p_nome").add("type","text").add("maxlength","60").add("required","true").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		codigo = new TextField(model,"codigo");
		codigo.setLabel(gt("C�digo"));
		
		codigo.propertie().add("name","p_codigo").add("type","text").add("maxlength","30").add("required","true").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		ativo = new CheckBoxField(model,"ativo");
		ativo.setLabel(gt("Ativo"));
		
		ativo.propertie().add("name","p_ativo").add("type","checkbox").add("maxlength","50").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");
		nada = new SeparatorField(model,"nada");
		nada.setLabel(gt(" "));
		
		nada.propertie().add("name","p_nada").add("type","separator").add("maxlength","30").add("placeholder","").add("right","false");
		aplicacao = new ListField(model,"aplicacao");
		aplicacao.setLabel(gt("Aplica��o"));
		
		aplicacao.propertie().add("name","p_aplicacao").add("type","select").add("multiple","false").add("maxlength","100").add("required","true").add("change","true").add("disabled","false").add("right","false").add("domain","").add("java-type","");
		organizacao_pai = new ListField(model,"organizacao_pai");
		organizacao_pai.setLabel(gt("Organiza��o pai"));
		
		organizacao_pai.propertie().add("name","p_organizacao_pai").add("type","select").add("multiple","false").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false").add("domain","").add("java-type","");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");
		btn_gravar = new IGRPButton("Gravar","igrp","NovaOrganica","gravar","submit","primary|fa-save","","");
		btn_gravar.propertie.add("type","specific").add("code","").add("rel","gravar");
		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);


		form_1.addField(nome);
		form_1.addField(codigo);
		form_1.addField(ativo);
		form_1.addField(nada);
		form_1.addField(aplicacao);
		form_1.addField(organizacao_pai);

		toolsbar_1.addButton(btn_gravar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}
}
