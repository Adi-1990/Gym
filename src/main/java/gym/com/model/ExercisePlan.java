package gym.com.model;

public class ExercisePlan {

	int planId;
	Trainer trainer;
	Equipment equipment;
	int period;

	public ExercisePlan(int planId, Trainer trainer, Equipment equipment, int period) {
		super();
		this.planId = planId;
		this.trainer = trainer;
		this.equipment = equipment;
		this.period = period;
	}

	public int getPlanId() {
		return planId;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public int getTime() {
		return period;
	}

	@Override
	public String toString() {
		StringBuilder exercises = new StringBuilder();
		exercises.append(planId).append(",").append(trainer.getTrainerId()).append(",")
				.append(equipment.getEquipmentId()).append(",").append(period);
		return exercises.toString();
	}
}
