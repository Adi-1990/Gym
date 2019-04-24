package com.gym;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gym.com.model.Trainer;
import gym.dal.TrainersDAO;

public class TrainersDAOTest {

	@Test
	public void trainersLoadTest() {

		TrainersDAO dao = new TrainersDAO();
		List<Trainer> content = dao.loadTrainers("./trainers.csv");

		assertEquals(2, content.size());
		assertEquals(1, content.get(0).getTrainerId());
	}

	@Test
	public void trainersSaveTest() {

		Trainer trainer1 = new Trainer(1, "ilie");
		Trainer trainer2 = new Trainer(2, "gheo");

		List<Trainer> listOfTrainers = new ArrayList<Trainer>();
		listOfTrainers.add(trainer1);
		listOfTrainers.add(trainer2);

		TrainersDAO dao = new TrainersDAO();
		dao.saveTrainers("./trainers-test.csv", listOfTrainers);

		List<Trainer> trainers = dao.loadTrainers("./trainers-test.csv");

		assertEquals(trainers.size(), listOfTrainers.size());
		assertEquals(trainers.get(0).getName(), listOfTrainers.get(0).getName());

	}
}