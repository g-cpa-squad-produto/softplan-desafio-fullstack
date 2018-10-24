package com.agfgerador.compartilhado.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

public class UpperCase implements UserType {
    private static final int[] TYPES = {Types.VARCHAR};

	public int[] sqlTypes() {
	        return TYPES;
	}
	
	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
	        return String.class;
	}
	
	public boolean equals(Object x, Object y) throws HibernateException 
	{ boolean ret = false;
       if (x == y) 
    	   ret = true;
       else if ((x==null) || (y==null )) 
            ret =  false;
      
       ret = new EqualsBuilder().append(x, y).isEquals();
       return ret;
	}
	
	public int hashCode(Object o) throws HibernateException {
	        return new HashCodeBuilder().append(o).toHashCode();
	}
	
	public Object nullSafeGet(ResultSet resultSet, String[] strings,SessionImplementor session,Object object) throws HibernateException, SQLException {
		return StringUtils.upperCase((String) StringType.INSTANCE.nullSafeGet(resultSet, strings[0], session));
	}
	
	public void nullSafeSet(PreparedStatement preparedStatement, Object object, int i,SessionImplementor session) throws HibernateException, SQLException {
		String string = StringUtils.upperCase((String) object);
		StringType.INSTANCE.nullSafeSet(preparedStatement, string,i, session);
	}
	
	public Object deepCopy(Object o) throws HibernateException {
	      if (null == o) 
	            return null;
	      return new String(o.toString());
	}
	
	public boolean isMutable() {
	        return false;
	}
	
	public Serializable disassemble(Object o) throws HibernateException {
	        return (String) o;
	}
	
	public Object assemble(Serializable serializable, Object o) throws HibernateException {
	        return serializable;
	}
	
	public Object replace(Object o, Object arg1, Object arg2) throws HibernateException {
	        return o;
	}



	
}