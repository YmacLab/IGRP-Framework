package nosi.core.webapp;
/**
 * @author Marcel Iekiny
 * Apr 14, 2017
 */
import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import nosi.core.config.Config;
import nosi.core.i18n.I18nManager;
import nosi.core.servlet.IgrpServlet;

public final class Igrp{ // Not extends 
	
	private static IgrpFactory<Igrp> appInstance = null;

	private HttpServlet servlet; // Refer to HttpServlet 
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Controller controller; // Current controller ... 
	
	private String currentAppName;
	private String currentPageName;
	private String currentActionName;
	
	private String homeUrl;
	private String basePath;
	private String baseRoute;
	
	private boolean die;
	
	//Flash Message
	private FlashMessage flashMessage;
	
	// User component (Identity)
	private User user;
	
	// For i18n
	private I18nManager i18nManager;
	
	// For Igrp log toolbar 
	private IgrpLog log;
	
	private Igrp(){} // Private and empty default constructor ... allow Singleton class 
	/**
		Ensure a single instance of Igrp.class per thread for each request 
		That is a Threadlocal Singleton :-) 
	 **/
	public static Igrp getInstance() {
        if (appInstance == null) {
            appInstance = new ThreadLocalIgrpFactory<>(
            		new IgrpFactory<Igrp>() {
						@Override
						public Igrp create() {
							return new Igrp();
						}
				});
        }
	    return appInstance.create();
	 }
	 
	// Inicialize the web app components
	public Igrp init(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response){
		this.servlet = servlet;
		this.request = request;
		this.response = response;
		this.basePath = this.request.getContextPath();
		this.baseRoute = this.request.getServletPath();
		this.homeUrl = "igrp/home/index";
		
		// init. of others configuration 
		this.flashMessage = new FlashMessage(); // Flash Message instance
		
		// Config. of RDBMS or others DS ...
		// PersistenceUtils.init();
		
		// User component (Identity)
		this.user = new User();
		this.user.init();
		
		// For internacionalization purpose 
		this.i18nManager = new I18nManager();
		this.i18nManager.init();
		
		// For Igrp log toolbar 
		this.log = new IgrpLog();
		this.log.init();
		
		return this;
	}
	
	public void run() throws IOException{ // run the web app 
		Config.configurationApp();
		if(!this.die){
			this.log.func1();
			this.runController();
		}
		this.exit();
	}
	
	// Exit and Send the response ... 
	private void exit(){
		Controller.sendResponse();
		this.die = false;
	}
	
	public IgrpFactory<Igrp> currentThreadLocal() {
		return appInstance;
	}
	
	private void runController() throws IOException{
		Controller.initControllerNRunAction();
	}
	
	public HttpServlet getServlet() {
		return servlet;
	}

	public void setServlet(IgrpServlet servlet) {
		this.servlet = servlet;
	}
	
	public Controller getCurrentController(){
		return this.controller;
	}
	
	public void setCurrentController(Controller controller){
		this.controller = controller;
	}
	
	public HttpServletResponse getResponse(){
		return this.response;
	}
	
	public void setResponse(HttpServletResponse response){
		this.response = response;
	}
	
	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	public String getBaseRoute(){
		return this.baseRoute;
	}
	
	public String getBasePath(){
		return this.basePath;
	}
	
	public String getHomeUrl(){
		return this.homeUrl;
	}
	
	public FlashMessage getFlashMessage(){
		return this.flashMessage;
	}

	
	public String getCurrentAppName() {
		return Core.isNotNull(currentAppName)?currentAppName:"igrp";
	}

	public void setCurrentAppName(String currentAppName) {
		this.currentAppName = currentAppName;
	}

	public String getCurrentPageName() {
		return Core.isNotNull(currentPageName)?currentPageName:"home";
	}

	public void setCurrentPageName(String currentPageName) {
		this.currentPageName = currentPageName;
	}

	public String getCurrentActionName() {
		return Core.isNotNull(currentActionName)?currentActionName:"index";
	}

	public void setCurrentActionName(String currentActionName) {
		this.currentActionName = currentActionName;
	}
	
	public User getUser(){
		return this.user;
	}

	public void die(){
		this.die = true;
	}
	
	public I18nManager getI18nManager() {
		return i18nManager;
	}

	public static String getMethod() {
		return Igrp.getInstance().getRequest().getMethod();
	}
	
	public IgrpLog getLog() {
		return log;
	}
	
	public String generateCsrfToken() {
		byte[] buffer = new byte[50];
		new SecureRandom().nextBytes(buffer);
	    String result = DatatypeConverter.printHexBinary(buffer);
	    String cookieName = this.request.getParameter("r").replaceAll("/", "-");
	    Cookie cookie = new Cookie(cookieName, result);
	    this.response.addCookie(cookie);
		return result;
	}
}
