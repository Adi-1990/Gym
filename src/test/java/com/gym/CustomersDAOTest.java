package com.gym;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gym.com.model.Customer;
import gym.dal.CustomersDAO;
import junit.framework.TestCase;

public class CustomersDAOTest extends TestCase {

	@Test
	public void testLoadCustomers() {

		CustomersDAO dao = new CustomersDAO();
		List<Customer> customers = dao.loadCustomers("./customers.csv");
		assertEquals(3, customers.size());

	}

	@Test
	public void testSaveCustomers() {

		Customer customer1 = new Customer(1, "gicu", "1111");
		Customer customer2 = new Customer(1, "gicu", "1111");
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);

		CustomersDAO dao = new CustomersDAO();
		dao.saveCustomer("./customers-test.csv", customers);
		List<Customer> loadedCustomers = dao.loadCustomers("./customers-test.csv");

		assertEquals(2, loadedCustomers.size());
		assertEquals(customer1.getCustomerId(), loadedCustomers.get(0).getCustomerId());

	}
}