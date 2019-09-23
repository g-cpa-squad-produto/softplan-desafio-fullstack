package br.com.soft.resources.util.filter;

//package br.com.shopanimal.resources.util.filter;
//
//
//import java.io.IOException;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//public class CORSFilter implements ContainerResponseFilter {
//
//   @Override
//   public void filter(final ContainerRequestContext requestContext,
//                      final ContainerResponseContext cres) throws IOException {
//      cres.getHeaders().add("Access-Control-Allow-Origin", "*");
//      cres.getHeaders().add("Access-Control-Allow-Headers", "*");
//      cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
//      cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//      cres.getHeaders().add("Access-Control-Max-Age", "1209600");
//   }
//}