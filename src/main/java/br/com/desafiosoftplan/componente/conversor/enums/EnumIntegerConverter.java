package br.com.desafiosoftplan.componente.conversor.enums;

/**
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

/**
 * Conversor-base para enums que representam domínios de entidades de domínio numéricos.
 *
 * @param <T> Tipo do enum (precisa ser instância de {@link DominioEnum}
 *
 * @author Outubro/2018: Jessica Etiene Marques Almeida<DD>
 */
public abstract class EnumIntegerConverter<T extends DominioEnum<Integer>> extends GenericEnumConverter<T>{

   private static final long serialVersionUID = 1L;
   private static final int FIRST_POSITION = 0;

   /**
    *
    * (Ver Javadoc da super classe)
    * @see org.hibernate.usertype.UserType#sqlTypes()
    */
   @Override
   public int[] sqlTypes()
   {
      return new int[]{Types.INTEGER};
   }

   /**
    *
    * (Ver Javadoc da super classe)
    * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
    */
   @Override
   public Object nullSafeGet(ResultSet resultSet, String[] names,
                             SessionImplementor session, Object owner) throws HibernateException,
      SQLException
   {
      int identificador = resultSet.getInt(names[FIRST_POSITION]);

      for(DominioEnum<Integer> value : returnedClass().getEnumConstants())
      {
         if(identificador == value.getValor().intValue())
         {
            return value;
         }
      }
      return null;
   }

   /**
    * (Ver Javadoc da super classe)
    * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
    *    java.lang.Object, int, org.hibernate.engine.spi.SessionImplementor)
    */
   @SuppressWarnings("unchecked")
   @Override
   public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session)
      throws HibernateException, SQLException
   {
      if (value == null)
      {
         statement.setNull(index, Types.INTEGER);
      }
      else
      {
         statement.setInt(index, ((DominioEnum<Integer>)value).getValor());
      }
   }

}
