package gym.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gym.com.model.Customer;

public class CustomersDAO {

	public List<Customer> loadCustomers(String path) {

		Path filePath = Paths.get(path);

		try {
			List<String> content = Files.readAllLines(filePath);
			List<Customer> customers = parseContent(content);
			return customers;

		} catch (IOException e) {
			System.err.println("The files with customers could not be found!");
			return Collections.emptyList();
		}
	}

	private List<Customer> parseContent(List<String> content) {
		
		List<Customer> customers = new ArrayList<>();
	
		for (String line : content) {

			String[] values = line.split(",");

			int customerId = Integer.parseInt(values[0]);
			String name = values[1];
			String phoneNumber = values[2];
			customers.add(new Customer(customerId, name, phoneNumber));
		}
		return customers;
	}

	public void saveCustomer(String path, List<Customer> customer) {
		Path filePath = Paths.get(path);
		List<String> content = new ArrayList<String>();
		for (Customer c : customer) {
			content.add(c.toString());

			try {
				Files.write(filePath, content);
			} catch (IOException e) {
				System.err.println("The list of customers could not be found ok disk!");
			}
		}
	}
}