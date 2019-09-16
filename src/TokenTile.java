import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class TokenTile extends JButton {
	private final int pos;
	private Player owner;
	private PocketOpsToken token;

	public TokenTile(int pos) {
		super(String.valueOf(pos));
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}
	public Player getOwner() {
		return owner;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//		if(token != null && owner.getName().equals("Test server")) {
//			super.setBackground(Color.RED);
//			setText(token.getName());
//			//System.out.println("Background set to red");
//		}
//		else if(token != null && owner.getName().equals("Test client")) {
//			super.setBackground(Color.BLUE);
//			setText(token.getName());
//			//System.out.println("Background set to blue at position of " + pos);
//		}
//		else if(token == null){
//			super.setBackground(null);
//			setText(String.valueOf(pos));
//		}
		setText(String.valueOf(pos));

	}
	public void updateTile(PocketOpsToken token) {
		this.token = token;
		if(token != null) {
			owner = token.getOwner();
		}
		else {
			owner = null;
		}
	}
	public PocketOpsToken getToken() {
		return token;
	}


}
