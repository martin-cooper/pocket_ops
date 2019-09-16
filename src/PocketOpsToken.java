import java.io.Serializable;

public abstract class PocketOpsToken implements Serializable{
	private Player owner;
	private String name;

	public PocketOpsToken(Player player, String name) {
		owner = player;
		this.name = name;
		
	}
	public Player getOwner() {
		return owner;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return getName();
	}
	public abstract void execute(Server server, PlaceAttempt place);
	public abstract boolean validPlaceLocation(PocketOpsGameState state, int pos);
}
