
public class SniperToken extends SpecialistToken implements Targetable{

	public SniperToken(Player owner) {
		super(owner, "Sniper");
	}
	public void execute(Server server, PlaceAttempt place) {
		int positionToKill = server.getTarget(this, place.getPos());
		server.getGameState().removeToken(positionToKill);
		super.execute(server, place);
	}
	public boolean validTarget(PocketOpsGameState state, int sniperPosition, int target) {
		int sniperRow = (sniperPosition - 1) / 3;
		int sniperColumn = sniperPosition % 3;
		return ((target - 1) / 3 == sniperRow || target % 3 == sniperColumn) && !(state.tokenAt(target) instanceof SpecialistToken);
	}
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		return state.tokenAt(pos) == null;
	}
}
