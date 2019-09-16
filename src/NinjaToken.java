
public class NinjaToken extends SpecialistToken implements Targetable {

	public NinjaToken(Player owner) {
		super(owner, "Ninja");
	}
	@Override
	public boolean validTarget(PocketOpsGameState state, int position, int target) {
		int column = (position - 1) % 3;
		int row = (position - 1) / 3;
		int targetColumn = (target - 1) % 3;
		int targetRow = (target - 1) / 3;
		if(state.tokenAt(target) instanceof SpecialistToken) {
			return false;
		}
		else if(position == 5 && target != 5) {
			return true;
		}
		
		//checks columns
		else if(position + 3 <= 9 && target == position + 3
				|| position - 3 >= 0 && target == position - 3) {
			return true;
		}
		//checks rows
		else if((position - 1) / 3 == (target - 1) / 3 &&( target + 1 == position 
				|| target - 1 == position) ) {
			return true;
		}
		//checks diagonals
		else if(Math.abs(column - targetColumn) == 1 && Math.abs(row - targetRow) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void execute(Server server, PlaceAttempt place) {
		int positionToKill = server.getTarget(this, place.getPos());
		server.getGameState().removeToken(positionToKill);
		super.execute(server, place);
	}
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		return state.tokenAt(pos) == null;
	}

}
