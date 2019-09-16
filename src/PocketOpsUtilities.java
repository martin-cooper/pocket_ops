
public class PocketOpsUtilities {
	public static int getPrediction(PocketOpsUI ui, PocketOpsGameState state) {
		ui.updateInstructions("Please predict where you think the opponent will place their token");
		ui.getPanel().allowConfirmation(true);
		int confirmedSquare;
		do {
			confirmedSquare = state.getConfirmedSquare();
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}while(confirmedSquare == 0);
		ui.getPanel().allowConfirmation(false);
		ui.updateInstructions("Prediction confirmed");
		state.resetConfirmation();
		return confirmedSquare;
	}
	public static PlaceAttempt getPlaceAttempt(PocketOpsUI ui, PocketOpsGameState state) {
		ui.updateInstructions("Please select a position and token");
		PlaceAttempt place;
		int confirmedPosition;
		PocketOpsToken confirmedToken;
		do {
			int selectedPosition = ui.getSelectedPosition();
			PocketOpsToken selectedToken = ui.getSelectedToken();
			if(selectedToken != null && selectedToken.validPlaceLocation(state, selectedPosition)) {
				ui.getPanel().allowConfirmation(true);
			}
			else {
				ui.getPanel().allowConfirmation(false);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			confirmedPosition = state.getConfirmedSquare();
			confirmedToken = state.getConfirmedToken();
		}while(confirmedPosition == 0 && confirmedToken == null);
		place = new PlaceAttempt(confirmedToken, confirmedPosition);
		
		if(place.getToken() instanceof SpecialistToken) {
			ui.getPanel().disableSpecialist();
		}
		state.resetConfirmation();
		ui.getPanel().allowConfirmation(false);
		ui.updateInstructions("Place attempt confirmed");
		return place;
	}
	public static int getTarget(Targetable t, PocketOpsUI ui, PocketOpsGameState state, int positionTokenIsIn) {
		ui.updateInstructions("Please choose a target for your " + t.getName());
		int target;
		do {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int selectedTarget = ui.getSelectedPosition();
			if(t.validTarget(state, positionTokenIsIn, selectedTarget)) {
				ui.getPanel().allowConfirmation(true);
			}
			else {
				ui.getPanel().allowConfirmation(false);
			}
			target = state.getConfirmedSquare();
		}while(target == 0);
		return target;
	}
}
