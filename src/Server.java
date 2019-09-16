
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server extends JFrame{
	private Socket connection;
	private PocketOpsGameState state;
	private ServerSocket server;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Player player;
	private PocketOpsUI ui;
	
	public static void main(String[] args) {
		Server server = new Server();
		server.setContentPane(server.getPocketOpsUI());
		server.setVisible(true);
		server.setSize(500, 500);
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.setResizable(true);
		server.connect();
		server.playGame();
	}

	public Server(){
		super("Server");
		player = new Player("Test server");
		setupGame();

	}
	public void connect() {
		try {
			ui.updateInstructions("Waiting for connection");
			if(server != null) {
				server.close();
			}
			server = new ServerSocket(45235);
			connection = server.accept();
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void playGame() {
		while(true) {
			Player winner;
			while(true) {
				try {
					playRound();
					player.setPredictor(!player.isPredictor());
				}
				//connection ended case
				catch(IOException e) {
					JOptionPane.showMessageDialog(ui, "Connection ended");
					System.out.println("Reconnecting");
					connect();
					break;
				}
				if((winner = getWinner()) != null) {
					try {
						output.writeObject(new SendWinnerCommand(winner));
						ui.showWinner(winner);
						break;
					} catch (IOException e) {
						JOptionPane.showMessageDialog(ui, "Connection ended");
						connect();
						break;
					}
				}
			}
			setupGame();
		}

	}
	private void setupGame() {
		player.setPredictor(true);
		state = new PocketOpsGameState(player);
		ui = new PocketOpsUI(state);
		setContentPane(ui);
		ui.repaint();
	}
	private void playRound() throws IOException{
		PlaceAttempt place = null;
		int prediction = 0;
		if(player.isPredictor()) {
			output.writeObject(new GetPlaceAttemptCommand());
			output.flush();
			prediction =  PocketOpsUtilities.getPrediction(ui, state);
			//This try catch block sends a command to the opponent for them to send where they would like to place their token
			try {

				place = (PlaceAttempt)input.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			output.writeObject(new GetPredictionCommand());
			output.flush();

			place = PocketOpsUtilities.getPlaceAttempt(ui, state);
			prediction = input.readInt();

		}
		comparePrediction(prediction, place);
		resolve(place);
		output.writeObject(new UpdateState(state.getTokens()));
		output.flush();
		output.reset();

	}
	private void comparePrediction(int pred, PlaceAttempt place) {
		if(pred == place.getPos()) {
			place.fail();
		}

	}
	private void resolve(PlaceAttempt p) {
		Player player = p.getToken().getOwner();
		String fail;
		if(p.getFail()) {
			fail = "failed";
		}
		else {
			fail = "succeeded";
		}
		ui.updateInstructions(player.getName() + "'s place attempt " + fail);
		p.execute(this);
		ui.updateTokens();
		ui.repaint();

	}
	private Player getWinner() {
		//Checks rows and columns and diagonals for a winner
		PocketOpsToken[][] tokens = state.getTokens();
		for(PocketOpsToken[] arr: tokens) {
			if((arr[0] != null && arr[1] != null && arr[2] != null) && 
					(arr[0].getOwner().getName().equals(arr[1].getOwner().getName()) 
							&& arr[0].getOwner().getName().equals(arr[2].getOwner().getName()))){
				return arr[0].getOwner();
			}
		}
		for(int i = 0; i < tokens[0].length; i++) {
			if((tokens[0][i] != null && tokens[1][i] != null && tokens[2][i] != null) && 
					(tokens[0][i].getOwner().getName().equals(tokens[1][i].getOwner().getName())
							&& tokens[0][i].getOwner().getName().equals(tokens[2][i].getOwner().getName()))) {
				return tokens[0][i].getOwner();
			}
		}
		if((tokens[0][0] != null && tokens[1][1] != null && tokens[2][2] != null)
				&& tokens[0][0].getOwner().getName().equals(tokens[1][1].getOwner().getName())
				&& tokens[0][0].getOwner().getName().equals(tokens[2][2].getOwner().getName())){
			return tokens[0][0].getOwner();
		}
		if((tokens[0][2] != null && tokens[1][1] != null && tokens[2][0] != null)
				&& tokens[0][2].getOwner().getName().equals(tokens[1][1].getOwner().getName())
				&& tokens[0][2].getOwner().getName().equals(tokens[2][0].getOwner().getName())){
			return tokens[0][2].getOwner();
		}
		return null;
	}

	public PocketOpsUI getPocketOpsUI() {
		return ui;
	}
	public void putToken(PlaceAttempt p) {
		state.putToken(p);
	}
	public PocketOpsGameState getGameState() {
		return state;
	}
	public int getTarget(Targetable token, int positionTokenIsIn){
		int target = 0;
		//Tells the server to get the target
		if(!player.isPredictor()) {
			target = PocketOpsUtilities.getTarget(token, ui, state, positionTokenIsIn);
		}
		//Tells the opponent to give a target
		else {
			try {
				output.writeObject(new GetTargetCommand(token, positionTokenIsIn));
				output.flush();
				output.reset();
				target = input.readInt();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(ui, "Connection reset");
			}

		}
		return target;
	}
}
