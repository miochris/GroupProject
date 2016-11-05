package com.fdmgroup.groupA.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.groupA.dao.UploaderDAO;
import com.fdmgroup.groupA.model.UploadFile;

@Controller
public class FileUploadController {

	@Autowired
	private UploaderDAO uploadDAO;
	
	private static final Logger logger = Logger.getLogger(FundController.class);
	
	public void setUploadDAO(UploaderDAO uploadDAO) {
		this.uploadDAO = uploadDAO;
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String goToStudentUploader(Model model, @RequestParam int id){
		logger.debug("Debug starts");
		model.addAttribute("id", id);
		logger.info("Student id: " + id );
		logger.debug("Debug starts");
		return "protected/student/academy/assignment";
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String uploadStudentAssignment(@RequestParam("file") MultipartFile file, Model model, @RequestParam String username, @RequestParam int id) throws IOException{
		logger.debug("Debug starts");
		if (!file.isEmpty()) {
			createDirectory(file, model, username, id);
			logger.info("Uploaded Assignment : " + file + "by " + username );
			return "protected/student/academy/assignmentUploaded";
		} else {
			logger.info("Assignment " + file +" was not Uploaded");
			logger.debug("Debug ends");
			return "protected/student/academy/assignmentFailed";
		}
	}
	
	@RequestMapping(value="/uploadLesson", method = RequestMethod.GET)
	public String goToLessonUploader(Model model, @RequestParam int lessonId){
		logger.debug("Debug starts");
		model.addAttribute("id", lessonId);
		logger.info("Upload Lesson " +lessonId+ " link activated" );
		logger.debug("Debug ends");
		return "protected/tutor/lessons/material";
	}
	
	@RequestMapping(value="/uploadLesson", method = RequestMethod.POST)
	public String uploadLessonMaterial(@RequestParam("file") MultipartFile file, Model model, @RequestParam String username, @RequestParam int id) throws IOException{
		logger.debug("Debug starts");
		if (!file.isEmpty()) {
			logger.info("user: "+id);
			logger.info("user: "+username);
			createDirectory(file, model, username, id);
			logger.info(file + "was uploaded by " + id + username);
			logger.debug("Debug ends");
			return "protected/tutor/lessons/materialUploaded";
		} else {
			logger.info(file + "was not uploaded");
			logger.debug("Debug ends");
			return "protected/tutor/lesssons/materialFailed";
		}
	}
	
	@RequestMapping(value= "/uploadCV", method = RequestMethod.GET)
	public String goToCVUploader(Model model,HttpServletRequest request){
		logger.debug("Debug starts");
		logger.info("Return to upload cv page ");
		logger.debug("Debug ends");
		return "protected/tutor/account/uploadCV";
	}
	
	@RequestMapping(value="/uploadCV", method = RequestMethod.POST)
	public String uploadCV(@RequestParam("file") MultipartFile file, Model model,HttpServletRequest request,@RequestParam String username) throws IOException{
		logger.debug("Debug starts");
		if (!file.isEmpty()) {
			logger.info("Create directory of file: " +file);
			createDirectory(file, model, username, 0);
			logger.debug("Debug ends");
			return "protected/tutor/account/FileUploaded";
		} else {
			logger.debug("Debug ends");
			return "protected/tutor/account/FileUploaded";
		}
	}
	
	public void createDirectory(MultipartFile file, Model model, String username, int id) throws IOException{

		logger.debug("Debug starts");
		byte[] bytes = file.getBytes();

		// Creating the directory to store file
		String savePath = "C:/Temp";
		File dir = new File(savePath);
		

		if (!dir.exists()) {
			dir.getParentFile().mkdirs();
		}
		
		logger.info("create file on server " );
		String pathpath = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
		File serverFile = new File(pathpath);

			
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));	
		stream.write(bytes);
		stream.close();
		
		// Add to SQL Table
		UploadFile uFile = new UploadFile(username, id);
		uFile.setFileName(serverFile.getName());
		uFile.setFilePath(serverFile.getAbsolutePath());
		logger.info("setFileName/Path " +serverFile+ uFile);
		uploadDAO.save(uFile);
		logger.info("Saving: " +uFile + "to SQL Table");
		
		logger.info("Attribute of serverfile " +serverFile+ "saved");
		logger.debug("Debug ends");
		// Add attribute for next page
		model.addAttribute("filename", serverFile.getName());
		
	}
	
}
