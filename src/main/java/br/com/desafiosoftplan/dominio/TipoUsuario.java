package br.com.desafiosoftplan.dominio;

import br.com.desafiosoftplan.componente.conversor.enums.DominioEnum;

/**
 * Dominio para os perfil de usuário
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
public enum TipoUsuario implements DominioEnum<Integer>
{
   ADMINISTRADOR(1),
   USUARIO_TRIADOR(2),
   USUARIO_FINALIZADOR(3);

   private Integer valor;

   TipoUsuario(Integer valor){
      this.valor = valor;
   }

   @Override
   public Integer getValor()
   {
      return this.valor;
   }

   public static  TipoUsuario get(Integer valor){
      for (TipoUsuario tipo : values())
      {
         if (tipo.getValor() == valor){
            return tipo;
         }

      }
      return null;
   }
}
