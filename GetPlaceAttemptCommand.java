import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetPlaceAttemptCommand extends Command {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7923291886108702322L;

	public void resolveCommand(PocketOpsGameState board, ObjectOutputStream output, PocketOpsUI ui) {
		PlaceAttempt place = PocketOpsUtilities.getPlaceAttempt(ui, board);
		try {
			output.writeObject(place);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
