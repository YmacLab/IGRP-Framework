/*-------------------------*/

/*Create Controller*/

package nosi.webapps.igrp.pages.dominio;
/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/

import nosi.core.webapp.Controller;
import nosi.core.webapp.Response;
import java.io.IOException;

/*----#END-PRESERVED-AREA----*/

public class DominioController extends Controller {		

	public Response actionIndex() throws IOException{
		Dominio model = new Dominio();
		DominioView view = new DominioView(model);
		return this.renderView(view);
	}

	public Response actionPesquisar() throws IOException{
	
		return this.redirect("RED","Teste","Action");
	}
	
	public Response actionNovo() throws IOException{
		
		return this.redirect("RED","Teste","Action");
	}
	
}
