package gym.com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Subscription {

	int subscriptionId;
	LocalDate startDate;
	Customer customer;
	Trainer trainer;
	ExercisePlan exercisePlan;

	public Subscription(int subscriptionId, LocalDate startDate, Customer customer, Trainer trainer, ExercisePlan exercisePlan) {
		super();
		this.subscriptionId = subscriptionId;
		this.startDate = startDate;
		this.customer = customer;
		this.trainer = trainer;
		this.exercisePlan = exercisePlan;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public ExercisePlan getExercisePlan() {
		return exercisePlan;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(subscriptionId)
		.append(",")
		.append(startDate.format(DateTimeFormatter.ISO_DATE))
		.append(",")
		.append(customer.getCustomerId())
		.append(",")
		.append(trainer.getTrainerId())
		.append(",")
		.append(exercisePlan.getPlanId());
		return sb.toString();
	}

}