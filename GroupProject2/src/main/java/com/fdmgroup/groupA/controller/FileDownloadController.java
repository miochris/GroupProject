package com.fdmgroup.groupA.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fdmgroup.groupA.dao.DownloadDAO;

@Controller
public class FileDownloadController {

	@Autowired
	private DownloadDAO dDao;
	@Autowired
	private ServletContext sc;
	
	public void setdDao(DownloadDAO dDao) {
		this.dDao = dDao;
	}

	public void setSc(ServletContext sc) {
		this.sc = sc;
	}

	public FileDownloadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = Logger.getLogger(FileDownloadController.class);
	
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public @ResponseBody void downloadFile(HttpServletResponse res, @RequestParam String username, @RequestParam int type) throws IOException {
    	logger.debug("Debug starts");
    	String filePath = dDao.getFilePathByUserNameAndType(username,type);
    	File file = getFile(filePath);
        InputStream in = new FileInputStream(file);
        res.setContentType(sc.getMimeType(filePath));
        res.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        res.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, res.getOutputStream());
        logger.info("File has been downloaded");
        logger.debug("Debug ends");
    }
    
    File getFile(String FILE_PATH) throws FileNotFoundException {
    	logger.debug("Debug starts");
        File file = new File(FILE_PATH);
        if (!file.exists()){
        	logger.info("File Does not exist!");
            throw new FileNotFoundException(" File not found with path: " + FILE_PATH + ".");
            
        }
        logger.debug("Debug ends");
        return file;
    }
	
}
