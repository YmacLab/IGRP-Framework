
package nosi.webapps.igrp.pages.pesquisarmenu;
/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import nosi.core.gui.components.IGRPTopMenu;
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.Response;
import nosi.core.webapp.helpers.Permission;
import nosi.core.xml.XMLWritter;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Menu;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.ProfileType;

import static nosi.core.i18n.Translator.gt;
/*----#END-PRESERVED-AREA----*/

public class PesquisarMenuController extends Controller {		


	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(INDEX)----*/
		PesquisarMenu model = new PesquisarMenu();
		Menu menu = new Menu();
		int idApp = 0;
		int idOrg = 0;
		int idMen = 0;
		model.load();
		if (Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST")) {

			idApp = (model.getAplicacao() != null && !model.getAplicacao().equals(""))
					? Integer.parseInt(model.getAplicacao())
					: 0;
			idOrg = (model.getOrganica() != null && !model.getOrganica().equals(""))
					? Integer.parseInt(model.getOrganica())
					: 0;
			idMen = (model.getMenu_principal() != null && !model.getMenu_principal().equals(""))
					? Integer.parseInt(model.getMenu_principal())
					: 0;

			menu.setApplication(idApp != 0 ? new Application().findOne(idApp) : null);
			menu.setOrganization(idOrg != 0 ? new Organization().findOne(idOrg) : null);
			menu.setMenu(idMen != 0 ? new Menu().findOne(idMen) : null);
		}
		List<Menu> menus = null;

		if (idOrg == 0) {
			String dad = Permission.getCurrentEnv();
			if ("igrp".equalsIgnoreCase(dad)) {
				menus = menu.find().andWhere("application", "=", idApp != 0 ? idApp : null)
						.andWhere("menu", "=", idMen != 0 ? idMen : null).all();
			} else {
				menus = menu.find()
						.andWhere("application", "=", new Application().find().andWhere("dad", "=", dad).one().getId())
						.andWhere("application", "<>", 1)// Oculta IGRP Core
						.andWhere("application", "<>", 2)// Oculta IGRP Tutorial
						.andWhere("application", "<>", 3)// Oculta IGRP Studio
						.andWhere("menu", "=", idMen != 0 ? idMen : null).all();
			}
		} else {
			menus = menu.searchMen();
		}
		ArrayList<PesquisarMenu.Table_1> lista = new ArrayList<>();
		// Preenchendo a tabela
		for (Menu menu_db1 : menus) {
			PesquisarMenu.Table_1 table1 = new PesquisarMenu.Table_1();
			if (menu_db1.getMenu() != null) {
				table1.setT1_menu_principal(menu_db1.getMenu().getDescr());
			} else {
				table1.setT1_menu_principal(menu_db1.getDescr());
			}
			if (menu_db1.getAction() != null) {
				table1.setPagina(menu_db1.getAction().getPage());
				table1.setTable_titulo(menu_db1.getDescr());
			}
			table1.setAtivo(menu_db1.getStatus() == 1 ? "Ativo" : "Inativo");
			table1.setCheckbox(menu_db1.getId());
			table1.setP_id("" + menu_db1.getId());
			if (menu_db1.getFlg_base() == 1) {
				table1.setCheckbox_check(menu_db1.getId());
			}
			lista.add(table1);
		}
   
   		   model.setNovo("igrp", "NovoMenu", "index&target=_blank&app=" + model.getAplicacao());
		PesquisarMenuView view = new PesquisarMenuView(model);
		// Alimentando o selectorOption (Aplicacao, organica, e menuPrincipal)
		view.aplicacao.setValue(new Application().getListApps());
		HashMap<String, String> organizations = (!new String(model.getAplicacao() + "").isEmpty()
				? new Organization().getListOrganizations(idApp)
				: null);
		view.organica.setValue(organizations);
		HashMap<Integer, String> menu_principal = (!new String(model.getAplicacao() + "").isEmpty()
				&& !new String(model.getAplicacao() + "").isEmpty() ? new Menu().getListPrincipalMenus(idApp) : null);
		view.menu_principal.setValue(menu_principal);

		// Para pegar os parametros que queremos enviar para poder editar o menu no view
		view.p_id.setParam(true);
		view.table_1.addData(lista);	

		return this.renderView(view);
		/*----#END-PRESERVED-AREA----*/
	}


	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(EDITAR)----*/
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		if (id != null && !id.equals("")) {
			return this.redirect("igrp", "NovoMenu", "index&target=_blank&p_id=" + id);
		}

		return this.redirect("igrp", "PesquisarMenu", "index");
		/*----#END-PRESERVED-AREA----*/
	}
	

	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#START-PRESERVED-AREA(ELIMINAR)----*/
		String id = Igrp.getInstance().getRequest().getParameter("p_id");
		Menu menu_db = new Menu();
		if (menu_db.delete(Integer.parseInt(id)))
			Igrp.getInstance().getFlashMessage().addMessage("success", gt("Opera��o efetuada com sucesso"));
		else
			Igrp.getInstance().getFlashMessage().addMessage("error", gt("Falha ao tentar efetuar esta opera��o"));

		return this.redirect("igrp", "PesquisarMenu", "index");
		/*----#END-PRESERVED-AREA----*/
	}
	
	/*----#START-PRESERVED-AREA(CUSTOM_ACTIONS)----*/

	// Menu list I have access to
	public Response actionMyMenu() throws IOException {
		XMLWritter xml_menu = new XMLWritter();
		xml_menu.startElement("menus");
		if (Igrp.getInstance().getUser().isAuthenticated()) {
			Menu x = new Menu();
			HashMap<String, List<Menu>> menu = x.getMyMenu();
			if (menu != null) {
				for (Entry<String, List<Menu>> m : menu.entrySet()) {
					xml_menu.startElement("menu");
					xml_menu.setElement("title", gt(m.getKey()));
					// If the Parent menu id is equal to the submenu id, so merge the 2 and use link
					Menu link1Menu = (Menu) m.getValue().get(0);

					if (link1Menu.getMenu() != null) {

						if (link1Menu.getId() == link1Menu.getMenu().getId()) {
							xml_menu.setElement("link", "webapps?r=" + link1Menu.getMenu().getLink());
							xml_menu.setElement("order", "" + link1Menu.getMenu().getOrderby());
						} else
							xml_menu.setElement("order", "" + x.getOrderby());
					} else
						xml_menu.setElement("order", "" + x.getOrderby());

					for (Menu main : m.getValue()) {
						if (main.getMenu() != null) {
							xml_menu.startElement("submenu");
							xml_menu.writeAttribute("title", gt(main.getMenu().getDescr()));
							xml_menu.writeAttribute("id", "" + main.getMenu().getId());
							xml_menu.setElement("link", "webapps?r=" + main.getMenu().getLink());
							xml_menu.setElement("title", gt(main.getMenu().getDescr()));
							xml_menu.setElement("target", main.getMenu().getTarget());
							xml_menu.setElement("id", "" + main.getMenu().getId());
							xml_menu.setElement("status", "" + main.getMenu().getStatus());
							xml_menu.setElement("order", "" + main.getMenu().getOrderby());
							xml_menu.endElement();
						}
					}
					xml_menu.endElement();
				}
			}
		}
		xml_menu.endElement();
		return this.renderView(xml_menu + "");
	}

	// Get Top Menu
	public Response actionTopMenu() throws IOException {
		IGRPTopMenu topMenu = new IGRPTopMenu("top_menu");
		topMenu.addItem("Home", "webapps?r=" + Permission.getCurrentEnv(), "DefaultPage", "index", "_self", "home.png");
		topMenu.addItem("Settings", "webapps?r=igrp", "Settings", "index", "_self", "settings.png");
		topMenu.addItem("Mapa Processos", "webapps?r=igrp", "MapaProcesso", "index", "_self", "process.png");
		topMenu.addItem("Tarefas", "webapps?r=igrp", "ExecucaoTarefas", "index", "_self", "tasks.png");
		return this.renderView(topMenu.toString());
	}
	/*----#END-PRESERVED-AREA----*/
}
