package gym.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gym.com.model.Equipment;
import gym.com.model.ExercisePlan;
import gym.com.model.Trainer;

public class ExercisePlanDAO {

	public List<ExercisePlan> loadExercisePlan(String path, List<Trainer> trainer, List<Equipment> equipment) {

		Path filePath = Paths.get(path);

		try {
			List<String> content = Files.readAllLines(filePath);
			List<ExercisePlan> exercisePlans = parseContent(content, trainer, equipment);
			return exercisePlans;
		} catch (IOException e) {
			System.err.println("The file with exercise plans could not be found !");
			return Collections.emptyList();
		}
	}

	private List<ExercisePlan> parseContent(List<String> content, List<Trainer> trainer, List<Equipment> equipment) {

		List<ExercisePlan> exercisePlans = new ArrayList<ExercisePlan>();

		for (String line : content) {
			String[] values = line.split(",");

			int planId = Integer.parseInt(values[0]);

			int trainerId = Integer.parseInt(values[1]);
			Trainer trainer1 = searchTrainerById(trainerId, trainer);

			int equipmentId = Integer.parseInt(values[2]);
			Equipment equipment1 = searchEquipmentById(equipmentId, equipment);

			int minutes = Integer.parseInt(values[3]);

			exercisePlans.add(new ExercisePlan(planId, trainer1, equipment1, minutes));
		}
		return exercisePlans;
	}

	private Equipment searchEquipmentById(int equipmentId, List<Equipment> equipment) {
		for (Equipment e : equipment) {
			if (equipmentId == e.getEquipmentId())
				return e;
		}
		throw new RuntimeException("Could not find the equipment with ID: " + equipmentId);
	}

	private Trainer searchTrainerById(int trainerId, List<Trainer> trainer) {

		for (Trainer t : trainer) {
			if (trainerId == t.getTrainerId())
				return t;
		}
		throw new RuntimeException("Could not find the trainer with ID: " + trainerId);
	}

	public void saveExercisePlan(String path, List<ExercisePlan> exercisePLans) {

		Path filePath = Paths.get(path);

		List<String> content = new ArrayList<String>();

		for (ExercisePlan e : exercisePLans) {
			content.add(e.toString());
		}
		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of clients could not be saved on disk!");
		}
	}
}
