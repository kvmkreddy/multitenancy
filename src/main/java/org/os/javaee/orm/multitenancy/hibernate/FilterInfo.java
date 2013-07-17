package org.os.javaee.orm.multitenancy.hibernate;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.type.Type;

/**
 * <p>Title: FilterInfo</p>
 * <p><b>Description:</b> FilterInfo</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class FilterInfo implements Serializable{
	
	private static final long serialVersionUID = -6028985448505407095L;

	private String name;
	private String defaultCondition;
	private String condition;
	private Map<String,Type> parametersType;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the defaultCondition
	 */
	public String getDefaultCondition() {
		return defaultCondition;
	}
	/**
	 * @param defaultCondition the defaultCondition to set
	 */
	public void setDefaultCondition(String defaultCondition) {
		this.defaultCondition = defaultCondition;
	}
	/**
	 * @return the parametersType
	 */
	public Map<String, Type> getParametersType() {
		return parametersType;
	}
	/**
	 * @param parametersType the parametersType to set
	 */
	public void setParametersType(Map<String, Type> parametersType) {
		this.parametersType = parametersType;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilterInfo other = (FilterInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}