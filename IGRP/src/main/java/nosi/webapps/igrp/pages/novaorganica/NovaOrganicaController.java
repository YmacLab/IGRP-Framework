
package nosi.webapps.igrp.pages.novaorganica;
/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.FlashMessage;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.RParam;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.User;
import java.io.IOException;
import static nosi.core.i18n.Translator.gt;
/*----#END-PRESERVED-AREA----*/

public class NovaOrganicaController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(INDEX)----*/
		NovaOrganica model = new NovaOrganica();
		
		if(Igrp.getMethod().equalsIgnoreCase("post")){
			model.load();
		}
		if(Igrp.getInstance().getRequest().getParameter("id_app")!=null){
			model.setAplicacao(Igrp.getInstance().getRequest().getParameter("id_app"));
		}
		model.setAtivo(1);
		NovaOrganicaView view = new NovaOrganicaView(model);		
//		Organization organization = new Organization();
		view.aplicacao.setValue(new Application().getListApps());		
		view.organizacao_pai.setVisible(false);
//		view.organica_pai.setValue(model.getAplicacao() != 0 ? organization.getListOrganizations(model.getAplicacao()) : null);		
		return this.renderView(view);
		/*----#END-PRESERVED-AREA----*/
	}


	public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(GRAVAR)----*/
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")){
			NovaOrganica model = new NovaOrganica();		
			Organization organization = new Organization();
			model.load();			
			organization.setCode(model.getCodigo());
			organization.setApplication(new Application().findOne(model.getAplicacao()));
			/*if(model.getOrganica_pai()!=0){
				organization.setOrganization(new Organization().findOne(model.getOrganica_pai()));
			}*/
			User u = (User) Igrp.getInstance().getUser().getIdentity();
			organization.setUser(u);
			organization.setStatus(model.getAtivo());
			organization.setName(model.getNome());
			organization = organization.insert();
			if(organization!=null){
				Core.setMessageSuccess(gt("Org�nica registada com sucesso"));
				Core.setMessageInfoLink(gt("Atribuir menu para Org�nica: "+organization.getName()),"igrp", "MenuOrganica", "index&target=_blank&id="+organization.getId()+"&type=org");
			}else
				Core.setMessageError(gt("Ocorreu um erro."));			
		return this.redirect("igrp", "nova-organica", "index");
		}
		Core.setMessageError(gt("Invalid request ..."));			
		return this.redirect("igrp", "nova-organica", "index");
		/*----#END-PRESERVED-AREA----*/
	}
	
	/*----#START-PRESERVED-AREA(CUSTOM_ACTIONS)----*/
	public Response actionEditar(@RParam(rParamName = "p_id") String idOrganica) throws IOException, IllegalArgumentException, IllegalAccessException{
		NovaOrganica model = new NovaOrganica();
		
		Organization organization = new Organization().findOne(Integer.parseInt(idOrganica));		
		model.setCodigo(organization.getCode());
		model.setNome(organization.getName());
		model.setAplicacao(""+organization.getApplication().getId());
		
		/*if(organization.getOrganization()!=null){
			model.setOrganica_pai(organization.getOrganization().getId());
		}*/
		model.setAtivo(organization.getStatus());		
			
		NovaOrganicaView view = new NovaOrganicaView(model);		
		view.aplicacao.setValue(new Application().getListApps());		
		//view.organica_pai.setValue(model.getAplicacao() != 0 ? organization.getListOrganizations() : null);		
		view.sectionheader_1_text.setValue(gt("Gest�o de Org�nica - Atualizar"));	
		view.organizacao_pai.setVisible(false);
		view.btn_gravar.setLink("editar_&p_id=" + idOrganica);	
		return this.renderView(view);
	}
	
	public Response actionEditar_(@RParam(rParamName = "p_id") String idOrganica) throws IOException, IllegalArgumentException, IllegalAccessException{
		
		if(Igrp.getInstance().getRequest().getMethod().equals("POST")){
			
			NovaOrganica model = new NovaOrganica();
			Organization organization = new Organization().findOne(Integer.parseInt(idOrganica));		
			model.setCodigo(organization.getCode());
			model.setNome(organization.getName());
			model.setAplicacao(""+organization.getApplication().getId());
			/*if(organization.getOrganization()!=null){
				model.setOrganica_pai(organization.getOrganization().getId());
			}*/
			model.setAtivo(organization.getStatus());	
			
			model.load();
			organization.setCode(model.getCodigo());
			organization.setName(model.getNome());
			organization.setApplication(new Application().findOne(model.getAplicacao()));
			/*if(model.getOrganica_pai()!=0){
				organization.setOrganization(new Organization().findOne(model.getOrganica_pai()));
			}*/
			organization.setStatus(model.getAtivo());
			organization = organization.update();
			if(organization!=null){
				Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.SUCCESS, gt("Org�nica actualizada com sucesso."));
			}else
				Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.ERROR, gt("Erro ao atualizar."));
			return this.redirect("igrp", "nova-organica", "editar", new String[]{"p_id"}, new String[]{organization.getId() + ""});
		}
		Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.SUCCESS, gt("Invalid request ..."));
		return this.redirect("igrp", "nova-organica", "editar", new String[]{"p_id"}, new String[]{idOrganica + ""});
	}
	
	/*----#END-PRESERVED-AREA----*/
}
