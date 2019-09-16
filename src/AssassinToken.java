
public class AssassinToken extends SpecialistToken {
	private Player owner;

	public AssassinToken(Player player) {
		super(player, "Assassin");
		owner = player;
	}
	public void execute(Server server, PlaceAttempt place) {
		server.getGameState().removeToken(place.getPos());
		super.execute(server, place);
	}
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		return !(state.tokenAt(pos) instanceof SpecialistToken);
	}
}
