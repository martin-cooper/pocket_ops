import java.awt.*;

import javax.swing.*;

public class PocketOpsBoard extends JPanel{
	private JButton[][] tiles;
	private PocketOpsGameState state;
	private PocketOpsUI ui;
	
	public PocketOpsBoard(PocketOpsGameState s, PocketOpsUI u) {
		tiles = new JButton[3][3];
		state = s;
		ui = u;
		setLayout(new GridLayout(3,3));
		int count = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				tiles[i][j] = new TokenTile(count);
				add(tiles[i][j]);
				count++;
			}
		}
		PocketOpsBoardListener listener = new PocketOpsBoardListener(this);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[0].length; j++) {
				TokenTile tile = (TokenTile)tiles[i][j];
				if(tile.getOwner() != null && tile.getOwner().equals(state.getPlayer())) {
					tile.setOpaque(true);
					tile.setBackground(Color.BLUE);
					tile.setText(tile.getToken().getName());
				}
				else if(tile.getOwner() != null && !tile.getOwner().equals(state.getPlayer())) {
					tile.setOpaque(true);
					tile.setBackground(Color.RED);
					tile.setText(tile.getToken().getName());
				}
				else {
					
					tile.setBackground(null);
				}
				tile.repaint();
			}
		}
	}
	public void setSelectedSquare(int square) {
		ui.setSelectedSquare(square);
	}
	public JButton[] getTiles() {
		JButton[] newArray = new JButton[9];
		int count = 0;
		for(JButton[] arr: tiles) {
			for(JButton tile: arr) {
				newArray[count] = tile;
				count++;
			}
		}
		return newArray;
	}
	public void updateTokens() {
		PocketOpsToken[][] tokens = state.getTokens();
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[0].length; j++) {
				((TokenTile)tiles[i][j]).updateTile(tokens[i][j]);
			}
		}
	}
	public String toString() {
		String s = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				s += ((TokenTile)tiles[i][j]).getOwner() + " ";
			}
		}
		return s;
	}
}
