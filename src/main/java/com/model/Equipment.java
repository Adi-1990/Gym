package gym.com.model;

public class Equipment {

	int equipmentId;
	String name;

	public int getEquipmentId() {
		return equipmentId;
	}

	public String getName() {
		return name;
	}

	public Equipment(int equipmentId, String name) {
		super();
		this.equipmentId = equipmentId;
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder equipment = new StringBuilder();
		equipment.append(equipmentId)
		.append(",")
		.append(name);

		return equipment.toString();
	}
}
