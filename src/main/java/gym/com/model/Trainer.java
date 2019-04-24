package gym.com.model;

public class Trainer {

	int trainerId;
	String name;

	public int getTrainerId() {
		return trainerId;
	}

	public String getName() {
		return name;
	}

	public Trainer(int trainerId, String name) {
		super();
		this.trainerId = trainerId;
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(trainerId)
		.append(",")
		.append(name);
		return sb.toString();

	}
}