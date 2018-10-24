package com.agfgerador.compartilhado.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import javax.servlet.ServletContext;
import org.zkoss.zk.ui.Sessions;

public class AGFFile {

	public static boolean salvaArquivo(String src,int tamArquivo,InputStream inputStream)
	{	boolean ret = false; 
		OutputStream outputStream = null;
		try 
		{	if((src!=null)&&(tamArquivo>0)){	
				outputStream = new FileOutputStream(new File(src));
				int read = 0;
				byte[] bytes = new byte[tamArquivo];
				while ((read = inputStream.read(bytes)) != -1) {	
					outputStream.write(bytes, 0, read);
				}
				File file = new File(src);
				try {
					BufferedReader leitor = new BufferedReader(new FileReader(file));
					leitor.close();
					} 
					catch (IOException e) {
						System.out.println("erro no buffer "+e);
						ret = false;
					}
				
				ret = true;
			}
			else{
				System.out.println("erro ou src null ou tamarquivo 0");
				System.out.println("src "+src);
				System.out.println("tamArquiv "+tamArquivo);
			}
	 
		} 
		catch (IOException e) {	
			ret = false;
			e.printStackTrace();
			System.out.println("erro no catch 2 "+e);
		} 
		finally 
		{	if (inputStream != null) {	
			    try {
					inputStream.close();
				} 
				catch (IOException e) {	
					ret = false;
					e.printStackTrace();
					System.out.println("erro no inputStream "+e);
				}
			}
			if (outputStream != null) {
				try {	
					outputStream.close();
				} 
				catch (IOException e) {	
					ret = false;
					e.printStackTrace();
					System.out.println("erro no outputStream "+e);
				}
			}
		}
		return ret;
	}

	 public static File salvaArquivo(String conteudo,String path)
	 {	File file = null;
		try {
			file = new File(path); 
	 		FileWriter f = new FileWriter (file, true);
			f.write(conteudo); 
			f.close();  
			file.createNewFile(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return file;
	 }

	public static String getSrcSistema(String src,String nomeProjeto,String nomeArquivo)
	{	
		String sistemaOperacional = System.getProperty("os.name");
		String  localizacao =null;
		ServletContext sc;
		sc = (ServletContext)Sessions.getCurrent().getWebApp().getNativeContext();
		if(!sistemaOperacional.equals("Linux"))
		{	localizacao= sc.getRealPath(src);
			localizacao = localizacao+"\\"+nomeArquivo;
		}
		
		else
		{	localizacao = sc.getRealPath("/"+nomeProjeto);
			localizacao = localizacao.replace(nomeProjeto+"/"+nomeProjeto, "");
			localizacao = localizacao+nomeProjeto+src+"/"+nomeArquivo;

		}
		return localizacao;
	}

	 public static void salvaArquivozip(String path,List<File> arquivos)  
	 {   byte[] buffer = new byte[18024];  
	     try{  
	    	ZipOutputStream out =   new ZipOutputStream(new FileOutputStream(path));  
		  	out.setLevel(Deflater.DEFAULT_COMPRESSION);  
		    for (int i = 0; i < arquivos.size(); i++){ 
		    	FileInputStream in = new FileInputStream(arquivos.get(i).getPath());  
		        out.putNextEntry(new ZipEntry(arquivos.get(i).getName()));  
		        int len;  
		        while ((len = in.read(buffer)) > 0){
		        	out.write(buffer, 0, len); 
		        }
		        out.closeEntry();  
		        in.close();  
		       }  
		   
	       out.close();  
	     }  
	     catch (IllegalArgumentException iae){  
	       iae.printStackTrace();  
	     }  
	     catch (FileNotFoundException fnfe){  
	       fnfe.printStackTrace();  
	     }  
	     catch (IOException ioe){  
	    	 ioe.printStackTrace();  
	     }  
	   }
}
