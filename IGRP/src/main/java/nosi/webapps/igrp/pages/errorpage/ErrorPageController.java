/*-------------------------*/

/*Create Controller*/

package nosi.webapps.igrp.pages.errorpage;

/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.Response;
import java.io.IOException;

import javax.servlet.RequestDispatcher;

import static nosi.core.i18n.Translator.gt;
/*----#END-PRESERVED-AREA----*/

public class ErrorPageController extends Controller {		

	/*----#START-PRESERVED-AREA(CUSTOM_ACTIONS)----*/

	/*public Response actionNotFound() throws IOException{
		if(Igrp.getInstance().getUser().isAuthenticated()){
			Igrp.getInstance().getFlashMessage().addMessage("error", "Falha na execu��o da opera��o");
			ErrorPage model = new ErrorPage();
			ErrorPageView view = new ErrorPageView(model);
			return this.renderView(view);
		}
		else
			return this.redirect("igrp", "login", "login");
	}
	*/
	public Response actionException() throws IOException{
		if(Igrp.getInstance().getUser().isAuthenticated()){
			Core.query("", "").getResultList().size();
			System.out.println(RequestDispatcher.ERROR_EXCEPTION);
			Exception e = (Exception)Igrp.getInstance().getRequest().getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			e.printStackTrace();
			
			Igrp.getInstance().getFlashMessage().addMessage("error", ""+Igrp.getInstance().getRequest().getAttribute("javax.servlet.error.message"));
			Igrp.getInstance().getFlashMessage().addMessage("info", gt("Por favor contactar o servi�o de HELPDESK para mais informa��es.(helpdesk@nosi.cv - Tel:2607973)"));
			ErrorPage model = new ErrorPage();
			ErrorPageView view = new ErrorPageView(model);
			return this.renderView(view);
		}
		else
			return this.redirect("igrp", "login", "login");
	}
	
	/*public Response actionPermission() throws IOException{
		if(Igrp.getInstance().getUser().isAuthenticated()){
			Igrp.getInstance().getFlashMessage().addMessage("error", "Falha na execu��o da opera��o");
			ErrorPage model = new ErrorPage();
			ErrorPageView view = new ErrorPageView(model);
			return this.renderView(view);
		}
		else
			return this.redirect("igrp", "login", "login");
	}*/	
	/*----#END-PRESERVED-AREA----*/
}
