import java.io.ObjectOutputStream;


public class SendWinnerCommand extends Command {
	private Player winner;
	
	public SendWinnerCommand(Player player) {
		winner = player;
	}
	
	@Override
	public void resolveCommand(PocketOpsGameState b, ObjectOutputStream s, PocketOpsUI ui) {
		ui.showWinner(winner);

	}

}
