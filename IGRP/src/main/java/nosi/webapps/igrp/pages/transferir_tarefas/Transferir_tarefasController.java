/*-------------------------*/

/*Create Controller*/

package nosi.webapps.igrp.pages.transferir_tarefas;

/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/
import nosi.core.config.Config;
import nosi.core.webapp.Controller;
import nosi.core.webapp.FlashMessage;
import nosi.core.webapp.Igrp;
import java.io.IOException;
import nosi.core.webapp.Response;
import nosi.core.webapp.activit.rest.TaskService;
import nosi.webapps.igrp.dao.User;
import static nosi.core.i18n.Translator.gt;
/*----#END-PRESERVED-AREA----*/

public class Transferir_tarefasController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(INDEX)----*/				
		Transferir_tarefas model = new Transferir_tarefas();
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if(id!=null && !id.equals("")){
			TaskService task = new TaskService().getTask(id);
			if(task!=null){
				model.setAtribuido_por(task.getOwner());
				model.setP_id(id);
				model.setCodigo_processo(task.getTaskDefinitionKey());
				model.setCriado_por_(task.getOwner());
				model.setNumero_processo(task.getProcessDefinitionId());
				model.setUtilizador_actual(task.getAssignee());
				model.setTipo_processo(task.getCategory());
//				model.setData_criacao(task.getCreateTime().toString());
//				model.setData_fim(data_fim);
//				model.setData_fim(data_fim);
			}
		}
		
		Transferir_tarefasView view = new Transferir_tarefasView(model);
		view.btn_gravar.setLink("index");

		view.novo_utilizador.setLookup("r=igrp/LookupListUser/index&amp;dad=igrp");
		view.novo_utilizador.addParam("p_prm_target","_blank");
		view.novo_utilizador.addParam("p_id_utilizador", "p_id");
		view.novo_utilizador.addParam("p_novo_utilizador", "nome_1");
		
		if(Igrp.getInstance().getRequest().getMethod().equalsIgnoreCase("post")){
			model.load();
			if(model.getP_id_utilizador()!=null && !model.getP_id_utilizador().equals("")){
				User user = new User().findOne(Integer.parseInt(model.getP_id_utilizador()));
				if(user!=null && new TaskService().delegateTask(id, user.getUser_name())){
					Igrp.getInstance().getFlashMessage().addMessage("success",gt("Tarefa transferida para ")+user.getName()+gt(" com sucesso"));
				}else{
					Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.ERROR,FlashMessage.MESSAGE_ERROR);				
				}
			}else{
				Igrp.getInstance().getFlashMessage().addMessage(FlashMessage.ERROR,FlashMessage.MESSAGE_ERROR);				
			}
		}
		Config.target = "_blank";
		return this.renderView(view);
		/*----#END-PRESERVED-AREA----*/
	}


	public Response actionGravar() throws IOException{
		/*----#START-PRESERVED-AREA(GRAVAR)----*/				
		return this.redirect("igrp","Transferir_tarefas","index");
		/*----#END-PRESERVED-AREA----*/
	}
	

	
	/*----#START-PRESERVED-AREA(CUSTOM_ACTIONS)----*/
	
	/*----#END-PRESERVED-AREA----*/
}
