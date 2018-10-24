package com.agfgerador.compartilhado.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class ImageHtmlEmail extends HtmlEmail {
	private final static Logger log = Logger.getLogger(ImageHtmlEmail.class
			.getName());
	protected Pattern pattern = Pattern.compile("(<[Ii][Mm][Gg]\\s*.*?\\s+[Ss][Rr][Cc]\\s*=\\s*\")([^\"]*)(\")");

	@Override
	public HtmlEmail setHtmlMsg(String aHtml) throws EmailException {
		return setHtmlMsg(aHtml, null);
	}

	public  HtmlEmail setHtmlMsg(String aHtml, File baseDir)
			throws EmailException {
		if(aHtml == null || aHtml.isEmpty()) {
			return super.setHtmlMsg(aHtml);
		}
		Matcher matcher = pattern.matcher(aHtml);

		StringBuffer myStringBuffer = new StringBuffer();
		while (matcher.find()) {
			String image = matcher.group(2);
			log.fine("Embedding: " + image);

			String cid = null;
			boolean embedded = false;
			if (baseDir != null && new File(baseDir, image).isFile()
					&& new File(baseDir, image).canRead()) {
				cid = embed(new File(baseDir, image));
				embedded = true;
			} else if (new File(image).isFile() && new File(image).canRead()) {
				cid = embed(new File(image));
				embedded = true;
			} else {
				try {
					cid = embed(new URL(image), image);
					embedded = true;
				} catch (IOException e) {
					log.fine("Could not download URL: " + image
							+ " Exception: " + e.getMessage());
				} catch (EmailException e) {
					log.fine("Could not download URL: " + image
							+ " Exception: " + e.getMessage());
				}
			}

			if (embedded) {
				matcher.appendReplacement(myStringBuffer, matcher.group(1)
						+ "cid:" + cid + matcher.group(3));
			}
		}
		matcher.appendTail(myStringBuffer);
		
		return super.setHtmlMsg(myStringBuffer.toString());
	}
}
