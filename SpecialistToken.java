
public abstract class SpecialistToken extends PocketOpsToken {

	public SpecialistToken(Player player, String name) {
		super(player, name);
	}
	public void execute(Server server, PlaceAttempt place) {
		server.putToken(place);
	}

}
