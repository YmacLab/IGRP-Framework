package nosi.webapps.igrp.dao;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import nosi.base.ActiveRecord.BaseActiveRecord;
import nosi.core.webapp.helpers.IgrpHelper;
/**
 * Marcel Iekiny
 * Sep 16, 2017
 */
@Entity
@Table(name = "oauth_scopes")
public class OAuthScope extends BaseActiveRecord<OAuthScope> implements Serializable{
	
	@Id
	private String scope;
	private int is_default;
	
	public OAuthScope() {}
	
	public OAuthScope(String scope, int is_default) {
		super();
		this.scope = scope;
		this.is_default = is_default;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public int getIs_default() {
		return is_default;
	}
	public void setIs_default(int is_default) {
		this.is_default = is_default;
	}
	public Map<Object, Object> getListScope(){
		return IgrpHelper.toMap(this.findAll(), "scope", "scope", "");
	}
}
