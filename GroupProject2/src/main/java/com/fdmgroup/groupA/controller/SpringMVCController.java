package com.fdmgroup.groupA.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.groupA.model.Grade;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class SpringMVCController {

	private static final Logger logger = Logger.getLogger(FundController.class);
	
	@RequestMapping(value = "/downloadPDF")
	public void downloadPDF(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		final ServletContext servletContext = request.getSession()
				.getServletContext();
		final File tempDirectory = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		final String temperotyFilePath = tempDirectory.getAbsolutePath();

		List<Grade> gradeList = (List<Grade>) request.getSession()
				.getAttribute("gradeList");
		String fileName = "Grade for " + gradeList.get(0).getLesson().getName()
				+ ".pdf";
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);
		try {

			createPDF(temperotyFilePath + "\\" + fileName, gradeList);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = convertPDFToByteArrayOutputStream(temperotyFilePath + "\\"
					+ fileName);
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(
			String fileName) {

		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}

	public static Document createPDF(String file, List<Grade> gradeList) {

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document, gradeList);

			addTitlePage(document, gradeList);

			createTable(document, gradeList);

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;

	}

	private static void addMetaData(Document document, List<Grade> gradeList) {
		document.addTitle("Grade report for "
				+ gradeList.get(0).getLesson().getName());
		document.addSubject("Grade report for "
				+ gradeList.get(0).getLesson().getName());
		document.addAuthor("Bean");
		document.addCreator("Bean");
	}

	private static void addTitlePage(Document document, List<Grade> gradeList)
			throws DocumentException {
		Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				Font.BOLD);
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("Grade Report for Lesson "
				+ gradeList.get(0).getLesson().getName()+ " in Course " +gradeList.get(0).getLesson().getTutor().getCourse().getCourseName(), TIME_ROMAN));

		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on "
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);

	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void createTable(Document document, List<Grade> gradeList)
			throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(4);

		PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Last Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Mark"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Average Mark" ));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		table.setHeaderRows(1);

		for (Grade grade : gradeList) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(grade.getStudent().getFirstname());
			table.addCell(grade.getStudent().getLastname());
			table.addCell(String.valueOf(grade.getMark()));
			table.addCell(String.valueOf(grade.getStudent().getMark()));
		}

		document.add(table);
	}

}
