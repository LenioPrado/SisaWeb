/*
* File:	     CreatePdfUtil.java
* Creation date: 05/10/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

import javax.faces.application.FacesMessage;

import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.enums.EnumHelper;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CreatePdfUtil {

	public static void createPDF(TeachingPlan teachingPlan) {
	    try {
	    	int columns = 6;
			Color color = Color.lightGray;
			PdfPCell blankLineCell = new PdfPCell(new Paragraph(Chunk.NEWLINE));
			blankLineCell.setBorder(Rectangle.NO_BORDER);
			blankLineCell.setColspan(columns);
			
	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
	        
	        BaseFont helvetica = PdfUtil.getPdfFont();
	        Font normalFont = new Font(helvetica, 8, Font.NORMAL);
	        Font boldFont = new Font(helvetica, 8, Font.BOLD);

	        if (!document.isOpen()) {
	            document.open();
	        }
	        
	        PdfPTable pdfHeaderTable = new PdfPTable(2);
	        pdfHeaderTable.setWidthPercentage(100);
			pdfHeaderTable.setWidths(new float[] { 30f, 70f });
	        
			Image image = getImage();
			Paragraph headerImageParagraphy = new Paragraph();
			if(image != null){				
		        headerImageParagraphy.add(new Chunk(image, 0, 0));				
			}
			PdfPCell imageHeaderCell = new PdfPCell(headerImageParagraphy);
			imageHeaderCell.setBorder(Rectangle.NO_BORDER);
			pdfHeaderTable.addCell(imageHeaderCell);
			
			Paragraph headerTextParagraphy = new Paragraph();
			
			headerTextParagraphy.add(MessagesUtil.getValue("lbl_header_text")+"\n");
			headerTextParagraphy.add(MessagesUtil.getValue("lbl_sub_header_text"));			
			
			PdfPCell headerTextCell = new PdfPCell(headerTextParagraphy);
			headerTextCell.setBorder(Rectangle.NO_BORDER);
			headerTextCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfHeaderTable.addCell(headerTextCell);    	        

			PdfPCell reportTitleCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_teaching_plan_report")));
			reportTitleCell.setBorder(Rectangle.NO_BORDER);
			reportTitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			reportTitleCell.setColspan(2);
			pdfHeaderTable.addCell(reportTitleCell);
			
	        document.add(pdfHeaderTable);
	        
			if (teachingPlan != null) {				
				PdfPTable pdfTable = new PdfPTable(columns);
				pdfTable.setWidthPercentage(100);
				float[] widths = new float[] { 20f, 10f, 20f, 10f, 20f, 20f };
				pdfTable.setWidths(widths);
				
				PdfPCell identificationCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_identification_teaching_plan"), boldFont));
				identificationCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				identificationCell.setColspan(columns);
				identificationCell.setBackgroundColor(color);
				pdfTable.addCell(identificationCell);
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_course_teaching_plan"), normalFont));
				PdfPCell courseNameCell = new PdfPCell(new Paragraph(teachingPlan.getPpcDiscipline().getPpc().getCourse().getName(), normalFont));
				courseNameCell.setColspan(3);
				pdfTable.addCell(courseNameCell);				
						
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_modality_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(new EnumHelper().getModalityLabel(teachingPlan.getPpcDiscipline().getPpc().getCourse().getModality()), normalFont));

				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_period_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getPpcDiscipline().getDiscipline().getPeriodSeries()), normalFont));

				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_year_semester_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getAcademicPeriod().getSemester()), normalFont));

				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_shift_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(new EnumHelper().getShiftLabel(teachingPlan.getPpcDiscipline().getPpc().getCourse().getShift()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_curricular_component_teaching_plan"), normalFont));
								
				PdfPCell disciplineNameCell = new PdfPCell(new Paragraph(teachingPlan.getPpcDiscipline().getDiscipline().getName(), normalFont));
				disciplineNameCell.setColspan(5);
				pdfTable.addCell(disciplineNameCell);						
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_class_number_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getPpcDiscipline().getClassNumber()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_complete_hour_load_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getTotalLoadTimeHours()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_hour_class_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getPpcDiscipline().getPpc().getClassHour()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_theoretical_class_number_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getTheoreticalLoadTimeHoursClass()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_theoretical_class_number_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getTheoreticalLoadTimeHoursClass()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_theoretical_hour_load_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getTheoreticalLoadTimeHours()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_practice_class_number_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getPracticeLoadTimeHoursClass()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_practice_hour_load_teaching_plan"), normalFont));
				pdfTable.addCell(new Paragraph(String.valueOf(teachingPlan.getPracticeLoadTimeHours()), normalFont));
				
				pdfTable.addCell(new Paragraph(MessagesUtil.getValue("lbl_teacher_teaching_plan"), normalFont));
				PdfPCell teacherNameCell = new PdfPCell(new Paragraph(teachingPlan.getTeacher().getName(), normalFont));
				teacherNameCell.setColspan(5);
				pdfTable.addCell(teacherNameCell);
				
				PdfPCell menuHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_menu_teaching_plan"), boldFont));
				menuHeaderCell.setColspan(columns);
				menuHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				menuHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(menuHeaderCell);
				
				PdfPCell menuCell = new PdfPCell(new Paragraph(teachingPlan.getPpcDiscipline().getDiscipline().getMenu(), normalFont));
				menuCell.setColspan(columns);
				pdfTable.addCell(menuCell);				
				
				PdfPCell objectiveHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_objectives_teaching_plan"), boldFont));
				objectiveHeaderCell.setColspan(columns);
				objectiveHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				objectiveHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(objectiveHeaderCell);
				
				PdfPCell objectiveCell = new PdfPCell(new Paragraph(teachingPlan.getObjective(), normalFont));
				objectiveCell.setColspan(columns);
				pdfTable.addCell(objectiveCell);				

				pdfTable.addCell(blankLineCell);
				
				PdfPCell programContentHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_program_content_teaching_plan"), boldFont));
				programContentHeaderCell.setColspan(columns);
				programContentHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				programContentHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(programContentHeaderCell);
				
				PdfPCell developedContentHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_developed_content_teaching_plan"), normalFont));
				developedContentHeaderCell.setColspan(3);
				developedContentHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTable.addCell(developedContentHeaderCell);
				
				PdfPCell expectedClassNumberHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_expected_class_number_teaching_plan"), normalFont));
				expectedClassNumberHeaderCell.setColspan(3);
				expectedClassNumberHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTable.addCell(expectedClassNumberHeaderCell);				
				
				for (ClassProgramming item : teachingPlan.getClassesProgramming()) {
					
					PdfPCell classProgrammingContentCell = new PdfPCell(new Paragraph(item.getContent(), normalFont));
					classProgrammingContentCell.setColspan(3);
					pdfTable.addCell(classProgrammingContentCell);
					
					PdfPCell classProgrammingClassesQuantityCell = new PdfPCell(new Paragraph(String.valueOf(item.getClassesQuantity()), normalFont));
					classProgrammingClassesQuantityCell.setColspan(3);
					pdfTable.addCell(classProgrammingClassesQuantityCell);
				}
				
				pdfTable.addCell(blankLineCell);
				
				PdfPCell teachingProcedureHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_teaching_procedure_teaching_plan"), boldFont));
				teachingProcedureHeaderCell.setColspan(columns);
				teachingProcedureHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				teachingProcedureHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(teachingProcedureHeaderCell);
				
				PdfPCell teachingProcedureCell = new PdfPCell(new Paragraph(teachingPlan.getTeachingProcedure(), normalFont));
				teachingProcedureCell.setColspan(columns);
				pdfTable.addCell(teachingProcedureCell);				
				
				pdfTable.addCell(blankLineCell);
				
				PdfPCell procedureEvaluationHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_procedure_evaluation_teaching_plan"), boldFont));
				procedureEvaluationHeaderCell.setColspan(columns);
				procedureEvaluationHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				procedureEvaluationHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(procedureEvaluationHeaderCell);
				
				for (ProcedureEvaluation item : teachingPlan.getProceduresEvaluations()) {
					PdfPCell procedureEvaluationObservationCell = new PdfPCell(new Paragraph(item.getObservation(), normalFont));
					procedureEvaluationObservationCell.setColspan(columns);
					pdfTable.addCell(procedureEvaluationObservationCell);
				}				
				
				pdfTable.addCell(blankLineCell);
				
				PdfPCell recoveryHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_recuperation_actitivity_teaching_plan"), boldFont));
				recoveryHeaderCell.setColspan(columns);
				recoveryHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				recoveryHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(recoveryHeaderCell);
				
				PdfPCell recoveryCell = new PdfPCell(new Paragraph(teachingPlan.getRecovery(), normalFont));
				recoveryCell.setColspan(columns);
				pdfTable.addCell(recoveryCell);			
				
				pdfTable.addCell(blankLineCell);
				
				PdfPCell referencesHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_references_teaching_plan"), boldFont));
				referencesHeaderCell.setColspan(columns);
				referencesHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				referencesHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(referencesHeaderCell);
				
				PdfPCell basicReferencesHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_basic_reference_teaching_plan"), boldFont));
				basicReferencesHeaderCell.setColspan(columns);
				basicReferencesHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				basicReferencesHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(basicReferencesHeaderCell);
				
				for (Bibliography item : teachingPlan.getPpcDiscipline().getDiscipline().getBasicBibliographies()) {
					PdfPCell basicBibliographyCell = new PdfPCell(new Paragraph(item.getFormattedText(), normalFont));
					basicBibliographyCell.setColspan(columns);
					pdfTable.addCell(basicBibliographyCell);
				}
				
				PdfPCell complementaryReferencesHeaderCell = new PdfPCell(new Paragraph(MessagesUtil.getValue("lbl_complementary_reference_teaching_plan"), boldFont));
				complementaryReferencesHeaderCell.setColspan(columns);
				complementaryReferencesHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				complementaryReferencesHeaderCell.setBackgroundColor(color);
				pdfTable.addCell(complementaryReferencesHeaderCell);
				
				for (Bibliography item : teachingPlan.getPpcDiscipline().getDiscipline().getComplementaryBibliographies()) {
					PdfPCell complementaryBibliographyCell = new PdfPCell(new Paragraph(item.getFormattedText(), normalFont));
					complementaryBibliographyCell.setColspan(columns);
					pdfTable.addCell(complementaryBibliographyCell);
				}
				
				pdfTable.addCell(blankLineCell);
			
				Paragraph signTeacherParagraph = getDateToSign(MessagesUtil.getValue("lbl_sign_teacher_teaching_plan"), normalFont);
				PdfPCell signTeacherCell = new PdfPCell(signTeacherParagraph);
				signTeacherCell.setColspan(2);
				signTeacherCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTable.addCell(signTeacherCell);			

				Paragraph signCourseCoordinatorParagraph = getDateToSign(MessagesUtil.getValue("lbl_sign_course_coordinator_teaching_plan"), normalFont);
				PdfPCell signCourseCoordinatorCell = new PdfPCell(signCourseCoordinatorParagraph);
				signCourseCoordinatorCell.setColspan(2);
				signCourseCoordinatorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTable.addCell(signCourseCoordinatorCell);
				
				Paragraph signEducationCoordinatorParagraph = getDateToSign(MessagesUtil.getValue("lbl_sign_education_coordinator_teaching_plan"), normalFont);
				PdfPCell signEducationCoordinatorCell = new PdfPCell(signEducationCoordinatorParagraph);
				signEducationCoordinatorCell.setColspan(2);
				signEducationCoordinatorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTable.addCell(signEducationCoordinatorCell);
				
				pdfTable.addCell(blankLineCell);
				
				Paragraph signObservation = new Paragraph(MessagesUtil.getValue("lbl_sign_observations_teaching_plan"), normalFont);
				signObservation.add("\n");
				signObservation.add("\n");
				signObservation.add("\n");
				signObservation.add("\n");
				signObservation.add("\n");
				signObservation.add("\n");
				signObservation.add("\n");
				
				PdfPCell signObservationsTeacherCell = new PdfPCell(signObservation);
				signObservationsTeacherCell.setColspan(columns);
				pdfTable.addCell(signObservationsTeacherCell);
				
				document.add(pdfTable);
			}
			document.close();
	        PdfUtil.writePdf(document, MessagesUtil.getValue("file_complete_teaching_plan"), baos);
	    } catch (DocumentException e) {
	    	BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_GENERATING_PDF_REPORT, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
	    }	    
	}
	
	private static Image getImage(){
		String imgPath = BeanUtil.getImageUrl("logo.jpg");;
        Image img = null;        
        try {
        	img = Image.getInstance(imgPath);
        	img.scaleToFit(150,150);
		} catch (Exception e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
	    }       
        return img;
	}
	
	private static Paragraph getDateToSign(String lastLineMessage, Font font){
		Paragraph signDate = new Paragraph(MessagesUtil.getValue("lbl_sign_date_teaching_plan"), font);
		signDate.add("\n");
		signDate.add("\n");
		signDate.add("\n");
		signDate.add("\n");
		signDate.add("\n");
		signDate.add(lastLineMessage);
		return signDate;
	}
}