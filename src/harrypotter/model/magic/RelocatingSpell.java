package harrypotter.model.magic;

public class RelocatingSpell extends Spell {

	private int range;

	public RelocatingSpell(String name, int cost, int coolDown, int range) {

		super(name, cost, coolDown);
		this.range = range;

	}
	public Spell Duplicate(){
		return new RelocatingSpell(this.getName(), this.getCost(), this.getCoolDown(), this.getRange());
	}

	public int getRange() {
		return range;
	}
}
