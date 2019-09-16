import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UpdateState extends Command implements Serializable{
	
	private PocketOpsToken[][] stateArray;

	public UpdateState(PocketOpsToken[][] stateArray) {
		this.stateArray = stateArray;
	}
	public void resolveCommand(PocketOpsGameState b, ObjectOutputStream s, PocketOpsUI ui) {
		b.updateState(stateArray);
		ui.updateTokens();
		ui.repaint();
	}

}
