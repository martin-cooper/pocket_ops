import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PocketOpsBoardListener implements ActionListener {
	private PocketOpsBoard board;
	private JButton[] tilesToListenTo;
	public PocketOpsBoardListener(PocketOpsBoard b) {
		board = b;
		tilesToListenTo = board.getTiles();
		for(JButton tile : tilesToListenTo) {
			tile.addActionListener(this);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		TokenTile actionMaker = (TokenTile)e.getSource();
		board.setSelectedSquare(actionMaker.getPos());

	}

}
