package gym.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gym.com.model.Customer;
import gym.com.model.Equipment;
import gym.com.model.ExercisePlan;
import gym.com.model.Subscription;
import gym.com.model.Trainer;
import gym.dal.CustomersDAO;
import gym.dal.EquipmentsDAO;
import gym.dal.ExercisePlanDAO;
import gym.dal.SubcscriptionDAO;
import gym.dal.TrainersDAO;

public class SubscriptionService {

	public List<Trainer> freeTrainersAtDate(LocalDate day) {

		CustomersDAO customersDao = new CustomersDAO();
		TrainersDAO trainerDao = new TrainersDAO();
		EquipmentsDAO equipmentsDao = new EquipmentsDAO();
		ExercisePlanDAO exercisePlanDao = new ExercisePlanDAO();
		SubcscriptionDAO subscriptionDao = new SubcscriptionDAO();

		// 1. Load all trainers, subscriptions
		List<Customer> allCustomers = customersDao.loadCustomers("./customers.csv");
		List<Trainer> allTrainers = trainerDao.loadTrainers("./trainers.csv");
		List<Equipment> allEquipments = equipmentsDao.loadEquipments("./equipments.csv");
		List<ExercisePlan> allLoadedExercises = exercisePlanDao.loadExercisePlan("./exercisePlans.csv", allTrainers,
				allEquipments);
		List<Subscription> allSubscriptions = subscriptionDao.loadSubscription("./subscriptions.txt", day, allCustomers,
				allTrainers, allLoadedExercises);

		// 2. Find the trainers available for a specific date

		List<Trainer> freeTrainersAtDate = isTheTrainerFree(day, allTrainers, allSubscriptions);

		return freeTrainersAtDate;
	}

	private List<Trainer> isTheTrainerFree(LocalDate day, List<Trainer> allTrainers,
			List<Subscription> allSubscriptions) {

		List<Trainer> freeTrainersAtDate = new ArrayList<>();
		for (Trainer t : allTrainers) {
			boolean isUsed = false;

			for (Subscription s : allSubscriptions)
				if (t.getTrainerId() == s.getTrainer().getTrainerId()) {
					if (s.getStartDate().isEqual(day)) {
						isUsed = true;
					}
				}
			if (!isUsed) {
				freeTrainersAtDate.add(t);
			}
		}
		return freeTrainersAtDate;
	}
}