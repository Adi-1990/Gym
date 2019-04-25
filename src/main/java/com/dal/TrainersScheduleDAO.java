package gym.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gym.com.model.Trainer;
import gym.com.model.TrainerSchedule;

public class TrainersScheduleDAO {

	public List<TrainerSchedule> loadTrainerSchedule(String path, List<Trainer> trainer) {

		Path filePath = Paths.get(path);

		try {
			List<String> allLines = Files.readAllLines(filePath);
			List<TrainerSchedule> trainersSchedule = parseContent(allLines, trainer);
			return trainersSchedule;

		} catch (IOException e) {
			System.err.println("The file trainers could not be found on the disk");
			return Collections.emptyList();
		}
	}

	private List<TrainerSchedule> parseContent(List<String> allLines, List<Trainer> trainer2) {

		List<TrainerSchedule> trainerSchedule = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");
			int trainerId = Integer.parseInt(values[0]);
			Trainer trainer = searchTrainerById(trainerId, trainer2);

			LocalDate date = LocalDate.parse(values[1], DateTimeFormatter.ISO_DATE);
			LocalTime StartingHour = LocalTime.parse(values[2], DateTimeFormatter.ISO_LOCAL_TIME);

			trainerSchedule.add(new TrainerSchedule(trainer, date, StartingHour));
		}
		return trainerSchedule;
	}

	private Trainer searchTrainerById(int trainerId, List<Trainer> trainer2) {
		for (Trainer t : trainer2) {
			if (t.getTrainerId() == trainerId)
				return t;
		}
		throw new RuntimeException("Could not find trainer with ID: " + trainerId);
	}

	public void saveTrainerSchedule(String path, List<TrainerSchedule> trainerSchedule) {

		Path filePath = Paths.get(path);
		List<String> content = new ArrayList<String>();
		for (TrainerSchedule t : trainerSchedule) {
			content.add(t.toString());
		}

		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of trainers schedule could not be saved on disk!");
		}
	}
}