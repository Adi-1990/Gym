package com.gym;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

import gym.com.model.Equipment;
import gym.com.model.ExercisePlan;
import gym.com.model.Trainer;
import gym.dal.EquipmentsDAO;
import gym.dal.ExercisePlanDAO;
import gym.dal.TrainersDAO;

public class ExercisePlanDAOTest {

	@Test
	public void testLoadExercisePlan() {

		ExercisePlanDAO dao = new ExercisePlanDAO();
		TrainersDAO trainerDao = new TrainersDAO();
		EquipmentsDAO equipmetsDAO = new EquipmentsDAO();

		List<Trainer> listTrainers = trainerDao.loadTrainers("./trainers.csv");
		List<Equipment> listEquipments = equipmetsDAO.loadEquipments("./equipments.csv");

		List<ExercisePlan> loadedExercises = dao.loadExercisePlan("./exercisePlans.csv", listTrainers, listEquipments);
		assertEquals(2, loadedExercises.size());
	}

//	@Test
//	public void testSaveExercisePlans() {
//
//		ExercisePlan e1 = new ExercisePlan(planId, trainer, equipment, time)
//		
//		ExercisePlan e1 = new ExercisePlan(1, 1, 1, 5);
//		ExercisePlan e2 = new ExercisePlan(2, 2, 2, 5);
//
//		List<ExercisePlan> listOfExercisePlans = new ArrayList<ExercisePlan>();
//		listOfExercisePlans.add(e1);
//		listOfExercisePlans.add(e2);
//
//		ExercisePlanDAO dao = new ExercisePlanDAO();
//
//		dao.saveExercisePlan("./exercisePlansTest.csv", listOfExercisePlans);
//		List<ExercisePlan> loadExercisePlan = dao.loadExercisePlan("./exercisePlansTest.csv");
//
//		assertEquals(2, listOfExercisePlans.size());
//		assertEquals(1, listOfExercisePlans.get(0).getPlanId());
//		assertEquals(2, listOfExercisePlans.get(1).getPlanId());
//
//	}
}
