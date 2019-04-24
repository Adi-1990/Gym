package gym.com.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainerSchedule {

	Trainer trainer;
	LocalDate day;
	LocalTime startingHour;

	public TrainerSchedule(Trainer trainer, LocalDate day, LocalTime startingHour) {
		super();
		this.trainer = trainer;
		this.day = day;
		this.startingHour = startingHour;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(trainer.getTrainerId())
		.append(",")
		.append(day)
		.append(",")
		.append(startingHour);
		return sb.toString();
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public LocalDate getDay() {
		return day;
	}

	public LocalTime getStartingHour() {
		return startingHour;
	}

}