package gym;

import java.time.LocalDate;
import java.util.List;

import gym.business.SubscriptionService;
import gym.com.model.Trainer;

public class Main {

	public static void main(String[] args) {
		
		SubscriptionService freeTrainersAtDate = new SubscriptionService();
		LocalDate dateToCheck = LocalDate.now();

		List<Trainer> availableTrainers = freeTrainersAtDate.freeTrainersAtDate(dateToCheck);

		System.out.print("Trainers with ID's : ");
		for (Trainer r : availableTrainers) {
			System.out.print(r.getTrainerId() + " ");
		}
		System.out.print("are free for date : " + dateToCheck);
	}

	
	
	
}