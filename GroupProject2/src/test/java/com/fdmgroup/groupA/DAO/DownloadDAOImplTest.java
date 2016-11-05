package com.fdmgroup.groupA.DAO;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.groupA.dao.DownloadDAOImpl;
import com.fdmgroup.groupA.model.UploadFile;

public class DownloadDAOImplTest {

	private EntityManager mockEM;
	private DownloadDAOImpl downloadDao;
	private UploadFile uFile;
	private TypedQuery<UploadFile> q;
	private TypedQuery<UploadFile> q2;
	private TypedQuery<UploadFile> q3;
	private TypedQuery<UploadFile> q4;

	@Before
	public void setup(){
		mockEM = mock(EntityManager.class);
		uFile = mock(UploadFile.class);
		downloadDao = new DownloadDAOImpl();
		downloadDao.setEntityManager(mockEM);
		q = mock(TypedQuery.class);
		q2 = mock(TypedQuery.class);
		q3 = mock(TypedQuery.class);
		q4 = mock(TypedQuery.class);
	}
	
	@Test
	public void testGetFilePathByUsernameAndType(){
		String filePath = "a";
		when(mockEM.createQuery("SELECT f FROM UploadFile f WHERE  f.username = :username AND f.type = :type", UploadFile.class)).thenReturn(q);
		when(q.setParameter("username", uFile.getFileName())).thenReturn(q2);
		when(q2.setParameter("type", uFile.getId())).thenReturn(q3);
		when(q3.getSingleResult()).thenReturn(uFile);
		when(uFile.getFilePath()).thenReturn(filePath);
		String view = downloadDao.getFilePathByUserNameAndType(uFile.getFileName(), uFile.getId());
		Assert.assertEquals(filePath, view);
	}
	
	@Test
	public void testGetFilePathByUsernameAndTypeNULL() { 
		when(mockEM.createQuery("SELECT f FROM UploadFile f WHERE  f.username = :username AND f.type = :type", UploadFile.class)).thenReturn(q);
		when(q.setParameter("username", uFile.getFileName())).thenReturn(q2);
		when(q2.setParameter("type", uFile.getId())).thenReturn(q3);
		when(q3.getSingleResult()).thenReturn(uFile);
		doThrow(Exception.class).when(uFile).getFilePath();
		downloadDao.getFilePathByUserNameAndType(null, 0);
	}
}
