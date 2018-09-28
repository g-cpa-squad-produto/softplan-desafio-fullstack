package br.com.desafiosoftplan.componente.conversor.enums;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Conversor-base para enums que representam domínios de entidades de domínio.
 *
 * @param <T> Tipo do enum (precisa ser instância de {@link DominioEnum}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
public abstract class GenericEnumConverter<T extends DominioEnum<?>> implements UserType, Serializable
{

   private static final long serialVersionUID = 1L;

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#equals(java.lang.Object, java.lang.Object)
    */
   @Override
   public boolean equals(Object primeiroObjeto, Object segundoObjeto) throws HibernateException
   {
      if (primeiroObjeto == null && segundoObjeto == null)
      {
         return true;
      }

      if (primeiroObjeto == null && segundoObjeto != null)
      {
         return false;
      }

      return primeiroObjeto.equals(segundoObjeto);
   }

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
    */
   @Override
   public int hashCode(Object objeto) throws HibernateException
   {
      return objeto.hashCode();
   }

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
    */
   @Override
   public Object deepCopy(Object value) throws HibernateException
   {
      return value;
   }

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#isMutable()
    */
   @Override
   public boolean isMutable()
   {
      return false;
   }

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
    */
   @Override
   public Serializable disassemble(Object value) throws HibernateException
   {
      return (Serializable) value;
   }

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
    */
   @Override
   public Object assemble(Serializable cached, Object owner) throws HibernateException
   {
      return cached;
   }

   /**
    * (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public Object replace(Object original, Object target, Object owner) throws HibernateException
   {
      return original;
   }

   /**
    * Requisita a subclasses que informem a a ser utilizada para conversões (Ver Javadoc da super classe)
    *
    * @see org.hibernate.usertype.UserType#returnedClass()
    */
   @Override
   public abstract Class<T> returnedClass();

}