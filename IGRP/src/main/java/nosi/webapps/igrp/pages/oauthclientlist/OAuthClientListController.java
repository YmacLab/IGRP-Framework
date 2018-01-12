/*-------------------------*/

/*Create Controller*/

package nosi.webapps.igrp.pages.oauthclientlist;

/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.OAuthClient;
import static nosi.core.i18n.Translator.gt;
/*----#END-PRESERVED-AREA----*/

public class OAuthClientListController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(INDEX)----*/						
		OAuthClientList model = new OAuthClientList();
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")){
			model.load();
		}
		
		List<OAuthClientList.Table_1> data = new ArrayList<>();
		
		OAuthClient db_cli = new OAuthClient();
		List<OAuthClient> lista = db_cli.find().andWhere("client_id", "like", model.getClientid_pesquisa()).andWhere("redirect_uri", "like", model.getUris_()).all();
		
		for(OAuthClient cliente: lista) {
			OAuthClientList.Table_1 tabela = new OAuthClientList.Table_1();
			tabela.setClient_id(cliente.getClient_id());
			tabela.setClient_secret(cliente.getClient_secret());
			tabela.setUris(cliente.getRedirect_uri());
			tabela.setScope(cliente.getScope());
			tabela.setP_id(""+cliente.getId());
			data.add(tabela);
		}
		
		OAuthClientListView view = new OAuthClientListView(model);
		view.table_1.addData(data);
		view.btn_pesquisar.setLink("index");
		view.p_id.setParam(true);
		return this.renderView(view);
		/*----#END-PRESERVED-AREA----*/
	}

	public Response actionPesquisar() throws IOException{
		/*----#START-PRESERVED-AREA(PESQUISAR)----*/						
		return this.redirect("igrp","oauthclientlist","index");
		/*----#END-PRESERVED-AREA----*/
	}
	

	public Response actionButton_1() throws IOException{
		/*----#START-PRESERVED-AREA(BUTTON_1)----*/				
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		return this.redirect("igrp","OAuthClient","index&p_id="+id);
		/*----#END-PRESERVED-AREA----*/
	}
	

	public Response actionApagar() throws IOException{
		/*----#START-PRESERVED-AREA(APAGAR)----*/						
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id != null && !id.equals("")) {
			OAuthClient cliente = new OAuthClient();
			cliente = cliente.findOne(id);
			boolean status = cliente.delete();
			if(status) 
				Igrp.getInstance().getFlashMessage().addMessage("success", gt("Operacao efetuada com sucesso"));
			else 
				Igrp.getInstance().getFlashMessage().addMessage("error", gt("Operacao falhada"));
		}
		return this.redirect("igrp","OAuthClientList","index");
		/*----#END-PRESERVED-AREA----*/
	}
	

	public Response actionRefresh() throws IOException{
		/*----#START-PRESERVED-AREA(REFRESH)----*/				
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		OAuthClient cliente = new OAuthClient();
		
		cliente = cliente.findOne(id);
		cliente.setClient_id(java.util.UUID.randomUUID().toString().replaceAll("-", ""));
		cliente.setClient_secret(RandomStringUtils.randomAlphabetic(40));
		cliente = cliente.update();
		
		if(cliente != null) 
			Igrp.getInstance().getFlashMessage().addMessage("success", gt("Operacao efetuada com sucesso"));
		else 
			Igrp.getInstance().getFlashMessage().addMessage("error", gt("Operacao falhada"));
		
		return this.redirect("igrp","OAuthClientList","index");
		/*----#END-PRESERVED-AREA----*/
	}
	
	/*----#START-PRESERVED-AREA(CUSTOM_ACTIONS)----*/
	
	/*----#END-PRESERVED-AREA----*/
}
