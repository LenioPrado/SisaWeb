/*
* File:	     PdfUtil.java
* Creation date: 14/10/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;

public class PdfUtil {

	/**
	 * Gets the pdf font.
	 *
	 * @return the pdf font
	 */
	public static BaseFont getPdfFont(){
		BaseFont helvetica = null;
		try {
	        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
	    } catch (Exception e) {
	    	BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_GETTING_PDF_FONT, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
	    }
		return helvetica;
	}
	
	/**
	 * Write pdf.
	 *
	 * @param document the document
	 * @param fileName the file name
	 * @param baos the baos
	 */
	public static void writePdf(Document document, String fileName, ByteArrayOutputStream baos){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.responseReset();
	        externalContext.setResponseContentType("application/pdf");
	        externalContext.setResponseHeader("Expires", "0");
	        externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	        externalContext.setResponseHeader("Pragma", "public");
	        externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
	        externalContext.setResponseContentLength(baos.size());
	        OutputStream out = externalContext.getResponseOutputStream();
	        baos.writeTo(out);
	        externalContext.responseFlushBuffer();
	        context.responseComplete();
		} catch (Exception e) {
	    	BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_GENERATING_PDF_RESPONSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
	    }
	}
}