
package nosi.webapps.igrp.pages.novomenu;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class NovoMenuView extends View {
	
	
	public Field sectionheader_1_text;
	public Field env_fk;
	public Field action_fk;
	public Field detalhes;
	public Field descr;
	public Field orderby;
	public Field self_id;
	public Field status;
	public Field status_check;
	public Field extra;
	public Field target;
	public Field link;
	public Field flg_base;
	public Field flg_base_check;
	public Field p_area;
	public Field p_img_src;
	public Field p_id;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_gravar;
	public NovoMenuView(NovoMenu model){
		this.setPageTitle("Registar Menu");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		
		sectionheader_1_text.setValue(gt("Gest�o Menu - Novo"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		env_fk = new ListField(model,"env_fk");
		env_fk.setLabel(gt("Aplica��o"));
		
		env_fk.propertie().add("name","p_env_fk").add("type","select").add("multiple","false").add("maxlength","30").add("required","true").add("change","true").add("disabled","false").add("right","false").add("domain","").add("java-type","int");
		action_fk = new ListField(model,"action_fk");
		action_fk.setLabel(gt("P�gina"));
		
		action_fk.propertie().add("name","p_action_fk").add("type","select").add("multiple","false").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false").add("domain","").add("java-type","int");
		detalhes = new SeparatorField(model,"detalhes");
		detalhes.setLabel(gt("Detalhes"));
		
		detalhes.propertie().add("name","p_detalhes").add("type","separator").add("maxlength","30").add("placeholder","").add("right","false");
		descr = new TextField(model,"descr");
		descr.setLabel(gt("T�tulo"));
		
		descr.propertie().add("name","p_descr").add("type","text").add("maxlength","80").add("required","true").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		orderby = new NumberField(model,"orderby");
		orderby.setLabel(gt("Ordem"));
		
		orderby.propertie().add("name","p_orderby").add("type","number").add("min","").add("max","").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false").add("java-type","int");
		self_id = new ListField(model,"self_id");
		self_id.setLabel(gt("Menu pai"));
		
		self_id.propertie().add("name","p_self_id").add("type","select").add("multiple","false").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false").add("domain","").add("java-type","int");
		status = new CheckBoxField(model,"status");
		status.setLabel(gt("Ativo?"));
		
		status.propertie().add("name","p_status").add("type","checkbox").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");
		extra = new SeparatorField(model,"extra");
		extra.setLabel(gt("Extra"));
		
		extra.propertie().add("name","p_extra").add("type","separator").add("maxlength","30").add("placeholder","").add("right","false");
		target = new ListField(model,"target");
		target.setLabel(gt("Target"));
		
		target.propertie().add("name","p_target").add("type","select").add("multiple","false").add("maxlength","30").add("required","false").add("change","false").add("disabled","false").add("right","false").add("domain","").add("java-type","");
		link = new TextField(model,"link");
		link.setLabel(gt("Link (Procedure)"));
		
		link.propertie().add("name","p_link").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		flg_base = new CheckBoxField(model,"flg_base");
		flg_base.setLabel(gt("P�blico?"));
		
		flg_base.propertie().add("name","p_flg_base").add("type","checkbox").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("right","false").add("check","true");
		p_area = new HiddenField(model,"p_area");
		p_area.setLabel(gt(""));
		
		p_area.propertie().add("name","p_area").add("type","hidden").add("maxlength","30").add("java-type","").add("tag","area");
		p_img_src = new HiddenField(model,"p_img_src");
		p_img_src.setLabel(gt(""));
		
		p_img_src.propertie().add("name","p_img_src").add("type","hidden").add("maxlength","30").add("java-type","").add("tag","img_src");
		p_id = new HiddenField(model,"p_id");
		p_id.setLabel(gt(""));
		
		p_id.propertie().add("name","p_id").add("type","hidden").add("maxlength","30").add("java-type","int").add("tag","id");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");
		btn_gravar = new IGRPButton("Gravar","igrp","NovoMenu","gravar","submit","primary|fa-save","","");
		btn_gravar.propertie.add("type","specific").add("code","").add("rel","gravar");
		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);


		form_1.addField(env_fk);
		form_1.addField(action_fk);
		form_1.addField(detalhes);
		form_1.addField(descr);
		form_1.addField(orderby);
		form_1.addField(self_id);
		form_1.addField(status);
		form_1.addField(extra);
		form_1.addField(target);
		form_1.addField(link);
		form_1.addField(flg_base);
		form_1.addField(p_area);
		form_1.addField(p_img_src);
		form_1.addField(p_id);

		toolsbar_1.addButton(btn_gravar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}
}
