package gym.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gym.com.model.Trainer;

public class TrainersDAO {

	public List<Trainer> loadTrainers(String path) {

		Path filePath = Paths.get(path);

		try {
			List<String> allLines = Files.readAllLines(filePath);
			List<Trainer> trainers = parseContent(allLines);
			return trainers;

		} catch (IOException e) {
			System.err.println("The file trainers could not be found on the disk");
			return Collections.emptyList();
		}
	}

	private List<Trainer> parseContent(List<String> allLines) {

		List<Trainer> trainer = new ArrayList<>();

		for (String line : allLines) {
			String[] values = line.split(",");
			int trainerId = Integer.parseInt(values[0]);
			String name = values[1];
			trainer.add(new Trainer(trainerId, name));
		}
		return trainer;
	}

	public void saveTrainers(String path, List<Trainer> trainers) {

		Path filePath = Paths.get(path);
		List<String> content = new ArrayList<String>();
		for (Trainer t : trainers) {
			content.add(t.toString());
		}

		try {
			Files.write(filePath, content);
		} catch (IOException e) {
			System.err.println("The list of clients could not be saved on disk!");
		}

	}
}