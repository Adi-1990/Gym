package gym.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gym.com.model.Customer;
import gym.com.model.ExercisePlan;
import gym.com.model.Subscription;
import gym.com.model.Trainer;

public class SubcscriptionDAO {

	public List<Subscription> loadSubscription(String path, LocalDate startDate, List<Customer> customer,
			List<Trainer> trainer, List<ExercisePlan> exercisePlan) {

		Path filePath = Paths.get(path);

		try {
			List<String> allLines = Files.readAllLines(filePath);
			List<Subscription> subscriptions = parseContent(allLines, startDate, customer, trainer, exercisePlan);
			return subscriptions;
		} catch (IOException e) {
			System.err.println("The files with customers could not be found!");
			return Collections.emptyList();
		}
	}

	private List<Subscription> parseContent(List<String> allLines, LocalDate startDate2, List<Customer> customer2,
			List<Trainer> trainer2, List<ExercisePlan> exercisePlan2) {

		List<Subscription> subscriptions = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");

			int subscriptionId = Integer.parseInt(values[0]);

			LocalDate startDate21 = LocalDate.parse(values[1], DateTimeFormatter.ISO_DATE);

			int customerId = Integer.parseInt(values[2]);
			Customer customer = searchCustomerById(customerId, customer2);

			int trainerId = Integer.parseInt(values[3]);
			Trainer trainer = searchTrainerById(trainerId, trainer2);

			int exercisePlanId = Integer.parseInt(values[4]);
			ExercisePlan exercisePlan = searchExercisePlanById(exercisePlanId, exercisePlan2);

			subscriptions.add(new Subscription(subscriptionId, startDate21, customer, trainer, exercisePlan));
		}
		return subscriptions;
	}

	private ExercisePlan searchExercisePlanById(int exercisePlanId, List<ExercisePlan> exercisePlan2) {
		for (ExercisePlan e : exercisePlan2) {
			if (e.getPlanId() == exercisePlanId)
				return e;
		}
		throw new RuntimeException("Could not find exercise plan with ID: " + exercisePlanId);
	}

	private Trainer searchTrainerById(int trainerId, List<Trainer> trainer2) {
		for (Trainer t : trainer2) {
			if (t.getTrainerId() == trainerId)
				return t;
		}
		throw new RuntimeException("Could not find trainer with ID: " + trainerId);
	}

	private Customer searchCustomerById(int customerId, List<Customer> customer2) {
		for (Customer c : customer2) {
			if (c.getCustomerId() == customerId)
				return c;
		}
		throw new RuntimeException("Could not find customer with ID: " + customerId);
	}

	public void saveSubscriptions(String path, List<Subscription> subscriptions) {

		Path filePath = Paths.get(path);

		List<String> content = new ArrayList<String>();

		for (Subscription s : subscriptions) {
			content.add(s.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of clients could not be saved on disk!");
		}
	}
}
