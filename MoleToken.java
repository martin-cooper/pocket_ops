
public class MoleToken extends SpecialistToken implements Targetable {
	private boolean allySelected;
	
	public MoleToken(Player player) {
		super(player, "Mole");
	}

	@Override
	public boolean validTarget(PocketOpsGameState state, int position, int target) {
		//Checks for all adjacents
		int column = (position - 1) % 3;
		int row = (position - 1) / 3;
		int targetColumn = (target - 1) % 3;
		int targetRow = (target - 1) / 3;
		if(state.tokenAt(target) instanceof SpecialistToken) {
			return false;
		}
		//If the ally was already selected, you cannot choose another ally
		else if(allySelected && state.tokenAt(target) != null && state.tokenAt(target).getOwner().equals(state.getPlayer())) {
			return false;
		}
		//If an ally has not been selected, you cannot target an enemy
		else if(!allySelected && state.tokenAt(target) != null && !state.tokenAt(target).getOwner().equals(state.getPlayer())) {
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
	public boolean validPlaceLocation(PocketOpsGameState state, int pos) {
		return state.tokenAt(pos) == null;
	}
	public void execute(Server server, PlaceAttempt place) {
		int allyTarget = server.getTarget(this, place.getPos());
		allySelected = true;
		int enemyTarget = server.getTarget(this, place.getPos());
		PocketOpsToken temp = server.getGameState().tokenAt(allyTarget);
		server.getGameState().putToken(new PlaceAttempt(server.getGameState().tokenAt(enemyTarget), allyTarget));
		server.getGameState().putToken(new PlaceAttempt(temp, enemyTarget));
		super.execute(server, place);
	}

}
