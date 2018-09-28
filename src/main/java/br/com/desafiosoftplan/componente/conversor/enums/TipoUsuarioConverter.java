package br.com.desafiosoftplan.componente.conversor.enums;

import br.com.desafiosoftplan.dominio.TipoUsuario;

/**
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
public class TipoUsuarioConverter extends EnumIntegerConverter<TipoUsuario>
{
   @Override
   public Class<TipoUsuario> returnedClass()
   {
      return TipoUsuario.class;
   }
}
