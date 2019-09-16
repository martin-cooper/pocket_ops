import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	private ArrayList<PocketOpsToken> tokens;
	private ArrayList<PocketOpsToken> specialistTokens;
	private String name;
	private boolean predictor;
	public Player(String name) {
		tokens = new ArrayList<PocketOpsToken>();
		tokens.add(new SpyToken(this));
		specialistTokens = new ArrayList<PocketOpsToken>();
		specialistTokens.add(new AssassinToken(this));
		specialistTokens.add(new SniperToken(this));
		specialistTokens.add(new NinjaToken(this));
		specialistTokens.add(new GrapplerToken(this));
		specialistTokens.add(new CourierToken(this));
		specialistTokens.add(new MoleToken(this));
		this.name = name;
		
	}
	public boolean isPredictor() {
		return predictor;
	}
	public void setPredictor(boolean pred) {
		predictor = pred;
	}
	public PocketOpsToken getSpyToken() {
		return tokens.get(0);
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return getName();
	}
	public PocketOpsToken getRandomSpecialist() {
		int index = (int)(Math.random()*(specialistTokens.size()));
		return specialistTokens.get(index);
	}
	public void removeToken(PocketOpsToken token) {
		specialistTokens.remove(token);
	}
	public boolean equals(Player other) {
		return name.equals(other.getName());
	}
	
}
