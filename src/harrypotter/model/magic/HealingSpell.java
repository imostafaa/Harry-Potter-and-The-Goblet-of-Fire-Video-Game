package harrypotter.model.magic;

public class HealingSpell extends Spell {

	private int healingAmount;

	public HealingSpell(String name, int cost, int coolDown, int healingAmount) {

		super(name, cost, coolDown);
		this.healingAmount = healingAmount;

	}

	public int getHealingAmount() {
		return healingAmount;
	}

	@Override
	public Spell Duplicate() {
		return new HealingSpell(this.getName(), this.getCost(), this.getCoolDown(), this.healingAmount);
	}

}
