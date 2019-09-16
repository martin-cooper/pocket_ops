
public class SpyToken extends PocketOpsToken {
	public SpyToken(Player owner) {
		super(owner, "Spy");
	}
	public void execute(Server server, PlaceAttempt place) {
		server.putToken(place);
	}
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		return state.tokenAt(pos) == null;
	}
}
