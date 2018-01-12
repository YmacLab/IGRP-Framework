
package nosi.webapps.igrp.pages.env;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class EnvView extends View {
	
	
	public Field sectionheader_1_text;
	public Field name;
	public Field dad;
	public Field description;
	public Field status;
	public Field status_check;
	public Field personalizacoes;
	public Field img_src;
	public Field templates;
	public Field action_fk;
	public Field extras;
	public Field host;
	public Field flg_external;
	public Field flg_external_check;
	public Field link_menu;
	public Field link_center;
	public Field apache_dad;
	public Field flg_old;
	public Field flg_old_check;
	public Field gen_auto_code;
	public Field gen_auto_code_check;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_gravar;
	public EnvView(Env model){
		this.setPageTitle("Registar Aplicacao");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		
		sectionheader_1_text.setValue(gt("App builder - Novo"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		name = new TextField(model,"name");
		name.setLabel(gt("Nome"));
		
		name.propertie().add("name","p_name").add("type","text").add("maxlength","50").add("required","true").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		dad = new TextField(model,"dad");
		dad.setLabel(gt("C�digo"));
		
		dad.propertie().add("name","p_dad").add("type","text").add("maxlength","30").add("required","true").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		description = new TextAreaField(model,"description");
		description.setLabel(gt("Descri��o"));
		
		description.propertie().add("name","p_description").add("type","textarea").add("maxlength","500").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		status = new CheckBoxField(model,"status");
		status.setLabel(gt("Ativo?"));
		
		status.propertie().add("name","p_status").add("type","checkbox").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");
		personalizacoes = new SeparatorField(model,"personalizacoes");
		personalizacoes.setLabel(gt("Personaliza��es"));
		
		personalizacoes.propertie().add("name","p_personalizacoes").add("type","separator").add("maxlength","30").add("placeholder","").add("right","false");
		img_src = new TextField(model,"img_src");
		img_src.setLabel(gt("Logotipo"));
		
		img_src.propertie().add("name","p_img_src").add("type","text").add("maxlength","50").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		templates = new TextField(model,"templates");
		templates.setLabel(gt("Template (theme)"));
		
		templates.propertie().add("name","p_templates").add("type","text").add("maxlength","100").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		action_fk = new ListField(model,"action_fk");
		action_fk.setLabel(gt("Primeira P�gina"));
		
		action_fk.propertie().add("name","p_action_fk").add("type","select").add("multiple","false").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false").add("domain","").add("java-type","");
		extras = new SeparatorField(model,"extras");
		extras.setLabel(gt("Extras"));
		
		extras.propertie().add("name","p_extras").add("type","separator").add("maxlength","30").add("placeholder","").add("right","false");
		host = new TextField(model,"host");
		host.setLabel(gt("Host"));
		
		host.propertie().add("name","p_host").add("type","text").add("maxlength","255").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		flg_external = new CheckBoxField(model,"flg_external");
		flg_external.setLabel(gt("Externo?"));
		
		flg_external.propertie().add("name","p_flg_external").add("type","checkbox").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");
		link_menu = new TextField(model,"link_menu");
		link_menu.setLabel(gt("Link Menu (Antigo)"));
		
		link_menu.propertie().add("name","p_link_menu").add("type","text").add("maxlength","2000").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		link_center = new TextField(model,"link_center");
		link_center.setLabel(gt("Link Centro (Antigo)"));
		
		link_center.propertie().add("name","p_link_center").add("type","text").add("maxlength","2000").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		apache_dad = new TextField(model,"apache_dad");
		apache_dad.setLabel(gt("DAD"));
		
		apache_dad.propertie().add("name","p_apache_dad").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		flg_old = new CheckBoxField(model,"flg_old");
		flg_old.setLabel(gt("Antigo?"));
		
		flg_old.propertie().add("name","p_flg_old").add("type","checkbox").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");
		gen_auto_code = new CheckBoxField(model,"gen_auto_code");
		gen_auto_code.setLabel(gt("Generate Auto Code"));
		
		gen_auto_code.propertie().add("name","p_gen_auto_code").add("type","checkbox").add("maxlength","2").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");
		btn_gravar = new IGRPButton("Gravar","igrp","Env","gravar","submit","primary|fa-floppy-o","","");
		btn_gravar.propertie.add("type","specific").add("code","").add("rel","gravar");
		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);


		form_1.addField(name);
		form_1.addField(dad);
		form_1.addField(description);
		form_1.addField(status);
		form_1.addField(personalizacoes);
		form_1.addField(img_src);
		form_1.addField(templates);
		form_1.addField(action_fk);
		form_1.addField(extras);
		form_1.addField(host);
		form_1.addField(flg_external);
		form_1.addField(link_menu);
		form_1.addField(link_center);
		form_1.addField(apache_dad);
		form_1.addField(flg_old);
		form_1.addField(gen_auto_code);

		toolsbar_1.addButton(btn_gravar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}
}
