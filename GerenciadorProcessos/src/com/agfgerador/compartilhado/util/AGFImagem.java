package com.agfgerador.compartilhado.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.zkoss.zul.Image;

public class AGFImagem {
	public static byte[] converterImageToByte(Image img) {
		byte[] Temp = img.getContent().getByteData();
		return Temp;
	}

	 public static BufferedImage converterByteToBufferedImage( byte[] object ) throws IOException
	 {  	ByteArrayInputStream is = new ByteArrayInputStream( object );
	     	BufferedImage bufferedImage = ImageIO.read( is );
	     	return bufferedImage;
	 }

	public static InputStream converterByteToInputStream(byte[] b)
	{   InputStream is = new ByteArrayInputStream(b); 
        return is;
	}

	public static BufferedImage base64StringToImg(String base64String) {
	    try {
	    	int start = base64String.indexOf(",");
	    	base64String = base64String.substring(start + 1);
	        return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
	    } catch (final IOException ioe) {
	        throw new UncheckedIOException(ioe);
	    }
	}
}
