import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client extends JFrame{
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Player player;
	private PocketOpsGameState state;
	private PocketOpsUI ui;
	private String IP;

	public static void main(String[] args) {
		Client client = new Client("192.168.1.65");
		client.setContentPane(client.getUi());
		client.setVisible(true);
		client.setSize(500, 500);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.connect();
		client.playGame();
	}
	public Client(String IP) {
		super("Client");
		player = new Player("Test client");
		player.setPredictor(false);
		state = new PocketOpsGameState(player);
		ui = new PocketOpsUI(state);
		this.IP = IP;
	}
	public PocketOpsUI getUi() {
		return ui;
	}
	public void connect() {
		ui.updateInstructions("Waiting for connection");
		while(true) {
			try {
				connection = new Socket(InetAddress.getByName(IP), 45235);
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());
				output.flush();
				break;

			} catch (IOException e) {
			}
		}
	}
	public void playGame() {
		//Loop through each game
		while(true) {
			try {
				//Loop for every round played
				while(true) {
					Command c;
					try {
						c = (Command)input.readObject();
						c.resolveCommand(state, output, ui);
						if(c instanceof SendWinnerCommand) {
							break;
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}catch(IOException e) {
				JOptionPane.showMessageDialog(ui, "Connection ended");
			}
			state = new PocketOpsGameState(player);
			ui = new PocketOpsUI(state);
			setContentPane(ui);
		}
	}
}
