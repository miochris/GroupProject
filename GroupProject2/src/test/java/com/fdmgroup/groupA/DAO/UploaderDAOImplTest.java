package com.fdmgroup.groupA.DAO;

import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.fdmgroup.groupA.dao.UploaderDAOImpl;
import com.fdmgroup.groupA.model.UploadFile;

public class UploaderDAOImplTest {

	private EntityManager mockEM;
	private UploaderDAOImpl uploaderDAO;
	private UploadFile uFile;
	
	@Before
	public void setup(){
		mockEM = mock(EntityManager.class);
		uFile = mock(UploadFile.class);
		uploaderDAO = new UploaderDAOImpl();
		uploaderDAO.setEntityManager(mockEM);
	}
	
	@Test
	public void testSaveFile(){
		uploaderDAO.save(uFile);
		verify(mockEM).persist(uFile);
	}
}
