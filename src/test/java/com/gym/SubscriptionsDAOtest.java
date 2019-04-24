package com.gym;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

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

public class SubscriptionsDAOtest {

	@Test
	public void testLoadSubscription() {

		CustomersDAO custDAO = new CustomersDAO();
		TrainersDAO trainerDao = new TrainersDAO();
		ExercisePlanDAO exPlanDao = new ExercisePlanDAO();
		EquipmentsDAO eqDao = new EquipmentsDAO();

		List<Customer> listCustomers = custDAO.loadCustomers("./customers.csv");
		List<Trainer> listTrainers = trainerDao.loadTrainers("./trainers.csv");
		List<Equipment> eqList = eqDao.loadEquipments("./equipments.csv");
		List<ExercisePlan> exPLan = exPlanDao.loadExercisePlan("./exercisePlans.csv", listTrainers, eqList);

		SubcscriptionDAO subDAO = new SubcscriptionDAO();

		LocalDate startDate = LocalDate.now();
		List<Subscription> loadedSubscription = subDAO.loadSubscription("./subscriptions.csv", startDate, listCustomers,
				listTrainers, exPLan);

		assertEquals(3, loadedSubscription.size());
	}

	@Test
	public void testSaveSubscriptions() {

	}

}
