package com.fdmgroup.groupA.DAO;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fdmgroup.groupA.dao.RequestDAOImpl;
import com.fdmgroup.groupA.model.Request;

public class RequestDAOImplTest {

	private EntityManager mockEM;
	private RequestDAOImpl RequestDAOImpl;
	private Request mockRequest;
	private TypedQuery<Request> mockQuery;

	@Before
	public void setup() {
		mockEM = Mockito.mock(EntityManager.class);
		mockRequest = Mockito.mock(Request.class);
		mockQuery = Mockito.mock(TypedQuery.class);
		RequestDAOImpl = new RequestDAOImpl();
		RequestDAOImpl.setEntityManager(mockEM);
	}

	@Test
	public void testAddRequest() {
		RequestDAOImpl.addRequest(mockRequest);
		verify(mockEM).persist(mockRequest);
	}

	@Test
	public void testUpdateRequest() {
		RequestDAOImpl.updateRequest(mockRequest);
		verify(mockEM).merge(mockRequest);
	}

	@Test
	public void testdeleteRequest() {
		when(mockEM.find(Request.class, eq(anyInt()))).thenReturn(mockRequest);
		RequestDAOImpl.deleteRequest(eq(anyInt()));
		verify(mockEM).remove(mockRequest);
	}

	@Test
	public void testGetRequestByUserName() {
		List<Request> mockList = mock(List.class);
		when(mockEM.createQuery("SELECT r FROM Request r WHERE r.username = :username", Request.class))
				.thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		String string = "";
		RequestDAOImpl.getRequestByUserName(string);
		Assert.assertEquals(mockList, RequestDAOImpl.getRequestByUserName(string));
	}

	@Test
	public void testGetAllRequest() {
		List<Request> mockList = mock(List.class);
		when(mockEM.createQuery("Select r from Request r", Request.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		RequestDAOImpl.getAllRequest();
		Assert.assertEquals(mockList, RequestDAOImpl.getAllRequest());
	}

	@Test
	public void testGetRequestById() {
		Request request = new Request("u", "S", "");
		when(mockEM.find(Request.class, request.getId())).thenReturn(request);
		Request result = RequestDAOImpl.getRequestById(request.getId());
		Assert.assertEquals(request, result);
	}

	@Test
	public void testGetRequestByType() {
		List<Request> list = mock(List.class);
		Request request = new Request("u", "S", "");
		when(mockEM.createQuery("SELECT r FROM Request r WHERE r.type = :type", Request.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(list);
		List<Request> result = RequestDAOImpl.getRequestByType("S");
		Assert.assertEquals(list, result);
	}
}
