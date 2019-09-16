
import java.awt.*;

import javax.swing.*;

public class PocketOpsUI extends JPanel{
	private PocketOpsBoard board;
	private PocketOpsSelectorPanel panel;
	private PocketOpsGameState gameState;
	private JPanel information;
	private JLabel position;
	private JLabel token;
	private JLabel instructions;
	private int selectedPosition = 1;
	private PocketOpsToken selectedToken;
	
	public PocketOpsUI(PocketOpsGameState state) {
		gameState = state;
		
		board = new PocketOpsBoard(gameState, this);
		panel = new PocketOpsSelectorPanel(gameState, this);
		instructions = new JLabel();
		Font f = new Font("Arial", 1, 20);
		instructions.setFont(f);
		information = new JPanel();
		information.add(position = new JLabel("Position selected: " + 0));
		position.setFont(f);
		information.add(token = new JLabel("Token selected: " + null));
		token.setFont(f);
		
		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		add(instructions, BorderLayout.NORTH);
		add(information, BorderLayout.SOUTH);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.repaint();
		panel.repaint();
		instructions.repaint();
		information.repaint();
	}
	public void updateTokens() {
		board.updateTokens();
	}
	public void updateInstructions(String text) {
		instructions.setText(text);
		repaint();
	}
	public void setSelectedSquare(int pos) {
		selectedPosition = pos;
		position.setText("Position selected: " + pos);
		repaint();
	}
	public void setSelectedToken(PocketOpsToken token) {
		selectedToken = token;
		this.token.setText("Token selected: " + selectedToken);
		repaint();
	}
	public int getSelectedPosition() {
		return selectedPosition;
	}
	public PocketOpsToken getSelectedToken() {
		return selectedToken;
	}
	public void confirmChoice() {
		gameState.confirmChoice(selectedPosition, selectedToken);
	}
	public void showWinner(Player winner) {
		if(winner.getName().equals(gameState.getPlayer().getName())) {
			JOptionPane.showMessageDialog(this, "You win! Starting new game");
		}
		else {
			JOptionPane.showMessageDialog(this, "You lose! Starting new game");
		}
	}
	public PocketOpsBoard getBoard() {
		return board;
	}
	public PocketOpsSelectorPanel getPanel() {
		return panel;
	}
}
