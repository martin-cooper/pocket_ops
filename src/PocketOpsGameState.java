

public class PocketOpsGameState{
	private PocketOpsToken[][] tokens;
	private Player playerOfThisGame;
	private int confirmedSquare;
	private PocketOpsToken confirmedToken;

	public PocketOpsGameState(Player player){
		playerOfThisGame = player;
		tokens = new PocketOpsToken[3][3];
	}
	public void putToken(PlaceAttempt p) {
		if(!p.getFail()) {
			int position = p.getPos();
			tokens[(position - 1) / 3][(position - 1) % 3] = p.getToken();
		}
	}
	public void removeToken(int position) {
		tokens[(position - 1) / 3][(position - 1) % 3] = null;
		System.out.println("Token removed " + this);
	}
	public Player getPlayer() {
		return playerOfThisGame;
	}
	public void confirmChoice(int position, PocketOpsToken token) {
		confirmedSquare = position;
		confirmedToken = token;
	}
	public int getConfirmedSquare() {
		int temp = confirmedSquare;
		confirmedSquare = 0;
		return temp;
	}
	public PocketOpsToken getConfirmedToken() {
		PocketOpsToken temp = confirmedToken;
		confirmedToken = null;
		return temp;
	}
	public PocketOpsToken[][] getTokens() {
		return tokens;
	}
	public void updateState(PocketOpsToken[][] state) {
		tokens = state;
	}
	public String toString() {
		String s = "Game state: ";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(tokens[i][j] == null) {
					s += "null ";
				}
				else {
					s += tokens[i][j].getOwner() + " ";
				}
			}
		}
		return s;
	}
	public PocketOpsToken tokenAt(int position) {
		return tokens[(position - 1) / 3][(position - 1) % 3];
	}
	public void resetConfirmation() {
		confirmedToken = null;
		confirmedSquare = 0;
	}
}
