import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetPredictionCommand extends Command {

	@Override
	public void resolveCommand(PocketOpsGameState b, ObjectOutputStream s, PocketOpsUI ui) {
		
		int prediction = PocketOpsUtilities.getPrediction(ui, b);
		try {
			s.writeInt(prediction);
			s.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
