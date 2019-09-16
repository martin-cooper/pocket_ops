import java.io.Serializable;

public class PlaceAttempt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1223498569518146296L;
	private PocketOpsToken token;
	private int pos;
	private boolean fail;
	public PlaceAttempt(PocketOpsToken token, int pos) {
		this.token = token;
		this.pos = pos;
	}

	public PocketOpsToken getToken() {
		return token;
	}
	public void setToken(PocketOpsToken token) {
		this.token = token;
	}
	public void fail() {
		fail = true;
	}
	public boolean getFail() {
		return fail;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void execute(Server server) {
		if(!fail) {
			token.execute(server, this);
			//server.putToken(this);
		}
	}
	public boolean isValid(PocketOpsGameState state) {
		if(token != null) {
			return token.validPlaceLocation(state, pos);
		}
		else {
			return false;
		}
	}
}
