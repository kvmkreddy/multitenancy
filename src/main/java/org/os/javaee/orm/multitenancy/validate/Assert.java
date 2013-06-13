package org.os.javaee.orm.multitenancy.validate;

/**
 * <p>Title: Assert</p>
 * <p><b>Description:</b> Assert</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class Assert {

	public static boolean isNotNull(Object...objects){
		if(objects != null && objects.length>0){
			for(Object object:objects){
				if(object == null){
					return false;
				}
			}
		}
		return true;
	}
}