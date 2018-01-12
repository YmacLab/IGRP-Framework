/*-------------------------*/

/*Create View*/

package nosi.webapps.igrp.pages.oauthclientlist;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;
public class OAuthClientListView extends View {
	
	
	public Field sectionheader_1_text;
	public Field uris_;
	public Field clientid_pesquisa;
	public Field client_id;
	public Field client_secret;
	public Field uris;
	public Field scope;
	public Field p_id;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_novo;
	public IGRPButton btn_pesquisar;
	public IGRPButton btn_button_1;
	public IGRPButton btn_apagar;
	public IGRPButton btn_refresh;
	public OAuthClientListView(OAuthClientList model){
		this.setPageTitle(gt("Pesquisar Dados dos clientes"));
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		table_1 = new IGRPTable("table_1","");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel("");
		
		sectionheader_1_text.setValue(gt("Dados dos Clientes"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		uris_ = new TextField(model,"uris_");
		uris_.setLabel(gt("URIs "));
		
		uris_.propertie().add("name","p_uris_").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		clientid_pesquisa = new TextField(model,"clientid_pesquisa");
		clientid_pesquisa.setLabel("ClientID");
		
		clientid_pesquisa.propertie().add("name","p_clientid_pesquisa").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		client_id = new TextField(model,"client_id");
		client_id.setLabel("Client ID");
		
		client_id.propertie().add("name","p_client_id").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		client_secret = new TextField(model,"client_secret");
		client_secret.setLabel("Client Secret");
		
		client_secret.propertie().add("name","p_client_secret").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		uris = new TextField(model,"uris");
		uris.setLabel("URIs");
		
		uris.propertie().add("name","p_uris").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		scope = new TextField(model,"scope");
		scope.setLabel("Scope");
		
		scope.propertie().add("name","p_scope").add("type","text").add("maxlength","30").add("align","left").add("lookup_parser","false").add("iskey","false");
		p_id = new HiddenField(model,"p_id");
		p_id.setLabel("");
		
		p_id.propertie().add("name","p_id").add("type","hidden").add("maxlength","30").add("iskey","false").add("tag","id");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");
		btn_novo = new IGRPButton(gt("Novo"),"igrp","OAuthClient","index","_self","success|fa-plus-square","","");
		btn_novo.propertie.add("type","specific").add("code","").add("rel","novo");
		btn_pesquisar = new IGRPButton(gt("Pesquisar"),"igrp","OAuthClientList","pesquisar","submit","default|fa-search","","");
		btn_pesquisar.propertie.add("type","form").add("code","").add("class","default").add("rel","pesquisar");
		btn_button_1 = new IGRPButton("Button","igrp","OAuthClientList","button_1","submit","warning|fa-edit","","");
		btn_button_1.propertie.add("type","specific").add("code","").add("class","warning").add("rel","button_1");
		btn_apagar = new IGRPButton(gt("Apagar"),"igrp","OAuthClientList","apagar","alert_submit","danger|fa-remove","","");
		btn_apagar.propertie.add("type","specific").add("code","").add("class","danger").add("rel","apagar");
		btn_refresh = new IGRPButton("Refresh","igrp","OAuthClientList","refresh","alert_submit","primary|fa-refresh","","");
		btn_refresh.propertie.add("type","specific").add("code","").add("class","primary").add("rel","refresh");
		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);

		form_1.addField(uris_);
		form_1.addField(clientid_pesquisa);

		table_1.addField(client_id);
		table_1.addField(client_secret);
		table_1.addField(uris);
		table_1.addField(scope);
		table_1.addField(p_id);


		toolsbar_1.addButton(btn_novo);
		form_1.addButton(btn_pesquisar);
		table_1.addButton(btn_button_1);
		table_1.addButton(btn_apagar);
		table_1.addButton(btn_refresh);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(table_1);
		this.addToPage(toolsbar_1);
	}
}
/*-------------------------*/