import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PocketOpsSelectorPanelListener implements ActionListener {
	private PocketOpsSelectorPanel panel;
	private PocketOpsGameState state;
	private JButton[] buttonsToListenTo;
	
	public PocketOpsSelectorPanelListener(PocketOpsGameState s, PocketOpsSelectorPanel p) {
		panel = p;
		state = s;
		buttonsToListenTo = p.getButtons();
		for(JButton b: buttonsToListenTo) {
			b.addActionListener(this);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof TokenSelectorTile){
			panel.setSelectedToken(((TokenSelectorTile)e.getSource()).getToken());
		}
		else {
			panel.confirmChoice();
		}
	}

}
