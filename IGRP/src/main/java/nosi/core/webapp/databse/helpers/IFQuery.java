package nosi.core.webapp.databse.helpers;

import java.util.List;

import javax.persistence.TypedQuery;

/**
 * Emanuel
 * 21 Dec 2017
 */
public interface IFQuery {

	public int execute();
	public List<?> getResultList();
	public Object getSigleResult();
	public TypedQuery<?> getSingleResult();
}
