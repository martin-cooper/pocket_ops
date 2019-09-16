import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetTargetCommand extends Command {
	private Targetable tokenThatIsTargetting;
	private int positionOfToken;

	public GetTargetCommand(Targetable t, int pos) {
		tokenThatIsTargetting = t;
		positionOfToken = pos;
	}
	public void resolveCommand(PocketOpsGameState b, ObjectOutputStream s, PocketOpsUI ui) {
		ui.updateInstructions("Please choose a target for your " + tokenThatIsTargetting.getName());
		int selection = PocketOpsUtilities.getTarget(tokenThatIsTargetting, ui, b, positionOfToken);
		try {
			s.writeInt(selection);
			s.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ui.getPanel().allowConfirmation(false);
	}

}
