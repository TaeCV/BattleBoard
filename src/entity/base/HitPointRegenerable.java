package entity.base;

public interface HitPointRegenerable {
	// ability for ToughFighter and HealerFighter
	// increase their hit point slightly every end of a turn

	public void regenerateHitPoint(); // called every turn end
}
