
public class GrapplerToken extends SpecialistToken implements Targetable {

	public GrapplerToken(Player player) {
		super(player, "Grappler");
	}
	public boolean validTarget(PocketOpsGameState state, int position, int target) {
		if(state.tokenAt(target) instanceof SpecialistToken) {
			return false;
		}
		if(position + 3 <= 9 && target == position + 3
				|| position - 3 >= 0 && target == position - 3) {
			return true;
		}
		else if((position - 1) / 3 == (target - 1) / 3 &&( target + 1 == position 
				|| target - 1 == position) ) {
			return true;
		}
		else {
			return false;
		}
	}
	public void execute(Server server, PlaceAttempt place) {
		int swapPosition = server.getTarget(this, place.getPos());
		int originalPosition = place.getPos();
		PocketOpsToken tokenBeingSwapped = server.getGameState().tokenAt(swapPosition);
		place.setPos(swapPosition);
		server.putToken(place);
		server.putToken(new PlaceAttempt(tokenBeingSwapped, originalPosition));

	}
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		return state.tokenAt(pos) == null;
	}

}
