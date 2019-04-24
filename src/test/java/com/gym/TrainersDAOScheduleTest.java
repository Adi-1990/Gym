package com.gym;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import gym.com.model.Trainer;
import gym.com.model.TrainerSchedule;
import gym.dal.TrainersDAO;
import gym.dal.TrainersScheduleDAO;

public class TrainersDAOScheduleTest {

	@Test
	public void trainersScheduleLoadTest() {

		TrainersDAO trainerDao = new TrainersDAO();
		List<Trainer> listTrainers = trainerDao.loadTrainers("./trainers.csv");

		TrainersScheduleDAO dao = new TrainersScheduleDAO();
		List<TrainerSchedule> loadedTrainersSchedule = dao.loadTrainerSchedule("./trainerSchedule.txt", listTrainers);

		LocalTime ora = LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime ora2 = LocalTime.parse(("11:00"), DateTimeFormatter.ISO_LOCAL_TIME);

		assertEquals(2, loadedTrainersSchedule.size());
		assertEquals(ora, loadedTrainersSchedule.get(0).getStartingHour());
		assertEquals(ora2, loadedTrainersSchedule.get(1).getStartingHour());
	}

	@Test
	public void trainersScheduleSaveTest() {

		TrainersDAO trainerDao = new TrainersDAO();
		List<Trainer> listTrainers = trainerDao.loadTrainers("./trainers.csv");

		TrainerSchedule trainerSchedule = new TrainerSchedule(listTrainers.get(0),(LocalDate.parse(("2019-05-02"), DateTimeFormatter.ISO_DATE)),(LocalTime.parse(("10:00"), DateTimeFormatter.ISO_LOCAL_TIME)));
		TrainerSchedule trainerSchedule2 = new TrainerSchedule(listTrainers.get(1),(LocalDate.parse(("2019-05-05"), DateTimeFormatter.ISO_DATE)),(LocalTime.parse(("11:00"), DateTimeFormatter.ISO_LOCAL_TIME)));
		List<TrainerSchedule> listOfTrainersSchedule = new ArrayList<TrainerSchedule>();

		listOfTrainersSchedule.add(trainerSchedule);
		listOfTrainersSchedule.add(trainerSchedule2);

		TrainersScheduleDAO scheduleDao = new TrainersScheduleDAO();

		scheduleDao.saveTrainerSchedule("./trainers-schedule-test.txt", listOfTrainersSchedule);

		List<TrainerSchedule> trainerSchedule21 = scheduleDao.loadTrainerSchedule("./trainers-schedule-test.txt", listTrainers);

		assertEquals(listOfTrainersSchedule.size(), trainerSchedule21.size());
	}
}