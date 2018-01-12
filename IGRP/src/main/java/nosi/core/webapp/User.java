package nosi.core.webapp;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONArray;

import nosi.core.webapp.helpers.Permission;
import nosi.core.webapp.helpers.Route;
import nosi.webapps.igrp.pages.login.LoginController;
/**
 * @author Marcel Iekiny
 * Apr 18, 2017
 */
public class User implements Component{
	
	private Identity identity;
	private int expire; // for authentication via cookie  
	
	public static final String loginUrl = "igrp/login/login";
	
	//private static Map<String, HttpSession> users = Collections.synchronizedMap(new HashMap<String, HttpSession>());
	
	public User(){}
	
	public boolean login(Identity identity, int expire){ // Make login and authenticate the user ... using session and cookies
		if(identity == null || identity.getAuthenticationKey() == null || identity.getAuthenticationKey().isEmpty())
			return false;
		try {
			this.identity = identity;
			this.expire = expire;
			Permission.changeOrgAndProfile("igrp");
			// Create the session context
			JSONArray json =  new JSONArray();
			json.put(this.identity.getIdentityId());
			json.put(this.identity.getAuthenticationKey() + "");
			Igrp.getInstance().getRequest().getSession(false).setAttribute("_identity-igrp", json.toString());
			this.sendCookie(Base64.getEncoder().encodeToString(json.toString().getBytes())); //  send a cookie to the end user 
		return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean checkSessionContext(){
		String aux = (String) Igrp.getInstance().getRequest().getSession(false).getAttribute("_identity-igrp");
		if(aux == null || aux.isEmpty()) return false;
		try {
			JSONArray json = new JSONArray(aux);
			int identityId = json.getInt(0);
			String authenticationKey = json.getString(1);
			nosi.webapps.igrp.dao.User user = new nosi.webapps.igrp.dao.User();
			user = user.findIdentityById(identityId);
			if(user!=null && user.getId()!=0 && authenticationKey.equals(user.getAuth_key())) {
				this.identity = (Identity) user;
			return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void checkCookieContext() {
		Cookie aux = null;
		Cookie []allCookies = Igrp.getInstance().getRequest().getCookies();
		if(allCookies != null)
			for(Cookie obj : allCookies)
				if(obj.getName().equals("_identity-igrp"))
					aux = obj;
		if(aux == null || aux.getValue().isEmpty()) return;
		String value = new String(Base64.getDecoder().decode(aux.getValue()));
		try {
			JSONArray json = new JSONArray(value);
			int identityId = json.getInt(0);
			String authenticationKey = json.getString(1);
			nosi.webapps.igrp.dao.User user = new nosi.webapps.igrp.dao.User();
			user = user.findIdentityById(identityId);
			if(user!=null && user.getId()!=0 && authenticationKey.equals(user.getAuth_key())) {
				// create the session context here
				Igrp.getInstance().getRequest().getSession(false).setAttribute("_identity-igrp", json.toString());
				this.identity = (Identity) user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendCookie(String value) {
		Cookie aux = new Cookie("_identity-igrp", value);
		aux.setMaxAge(this.expire);
		aux.setHttpOnly(true);
		Igrp.getInstance().getResponse().addCookie(aux);
	}
	
	public boolean isAuthenticated(){
		return this.identity != null;
	}
	
	public boolean logout(){ // Reset all login session/cookies information
		try {
			Igrp.getInstance().getRequest().getSession(false).invalidate(); // destroy the user session 
			// destroy the cookie ... make sure this.expire == 0 
			sendCookie("");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Identity getIdentity(){
		return this.identity;
	}

	@Override
	public void init() {
		boolean isLoginPage = false;
		String aux = Igrp.getInstance().getRequest().getParameter("r");
		/* test the login page (TOO_MANY_REQUEST purpose)*/
		if(aux != null){
			isLoginPage = aux.equals(User.loginUrl); // bug ... Perhaps
		}
		if(!this.checkSessionContext() && !isLoginPage){
			try {
				Route.remember(); // remember the url that was requested by the client ... 
				this.checkCookieContext();
				// Anyway, go to login page 
				LoginController controller = new LoginController();
				Response response = controller.actionGoToLogin();
				controller.setResponseWrapper(response);
				Igrp.getInstance().setCurrentController(controller);
				Igrp.getInstance().die();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
		// not set yet
	}
	
	public synchronized static String encryptToHash(String target, String algorithm/* MD5 or SHA1 */){
		String result = "";
		try {
			result = new BigInteger(1, MessageDigest.getInstance(algorithm).digest(target.getBytes())).toString(16/*Hexadecimal codification*/);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public synchronized static String generateAuthenticationKey() {
		return RandomStringUtils.randomAlphanumeric(32); // Deprecated !
	}
	
	public synchronized static String generateActivationKey() {
		return Base64.getUrlEncoder().encodeToString(((System.currentTimeMillis() + 1000*3600) + "").getBytes()); // 1h 
	}
	
}
