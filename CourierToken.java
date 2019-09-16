
public class CourierToken extends SpecialistToken implements Targetable {

	public CourierToken(Player owner) {
		super(owner, "Courier");
	}
	@Override
	public boolean validTarget(PocketOpsGameState state, int position, int target) {
		if(state.tokenAt(target) != null) {
			return false;
		}
		else if(position + 3 <= 9 && target == position + 3
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

	@Override
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		//Checks if where the courier is placed is either empty or the token there has the same owner
		return state.tokenAt(pos) == null || state.tokenAt(pos).getOwner().getName().equals(super.getOwner().getName());
	}
	public void execute(Server server, PlaceAttempt place) {
		int targetPosition = server.getTarget(this, place.getPos());
		PocketOpsToken tokenAtCurrentLocation = server.getGameState().tokenAt(place.getPos());
		server.getGameState().putToken(new PlaceAttempt(tokenAtCurrentLocation, targetPosition));
		super.execute(server, place);
	}

}
