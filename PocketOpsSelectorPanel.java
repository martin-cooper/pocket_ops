import java.awt.*;

import javax.swing.*;

public class PocketOpsSelectorPanel extends JPanel {
	private PocketOpsUI ui;
	private JLabel tokenSelector;
	private JPanel selectableTokens;
	private TokenSelectorTile spyToken;
	private TokenSelectorTile specialist;
	private JButton confirmChoiceButton;
	private PocketOpsGameState state;
	
	public PocketOpsSelectorPanel(PocketOpsGameState state, PocketOpsUI ui) {
		this.state = state;
		this.ui = ui;
		
		tokenSelector = new JLabel("Token Selector");
		selectableTokens = new JPanel();
		confirmChoiceButton = new JButton("Confirm choice");
		confirmChoiceButton.setEnabled(false);
		
		setLayout(new BorderLayout());
		
		Player player = state.getPlayer();
		PocketOpsToken token = player.getSpyToken();
		
		spyToken = new TokenSelectorTile(token);
		specialist = new TokenSelectorTile(state.getPlayer().getRandomSpecialist());
		
		selectableTokens.add(spyToken);
		selectableTokens.add(specialist);
		
		add(tokenSelector, BorderLayout.NORTH);
		add(selectableTokens, BorderLayout.CENTER);
		add(confirmChoiceButton, BorderLayout.SOUTH);
		PocketOpsSelectorPanelListener listener = new PocketOpsSelectorPanelListener(state, this);

		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		tokenSelector.repaint();
		selectableTokens.repaint();

	}
	public JButton[] getButtons() {
		JButton[] arr = new JButton[] {spyToken, specialist, confirmChoiceButton};
		return arr;
	}
	public void setSelectedToken(PocketOpsToken p) {
		ui.setSelectedToken(p);
		ui.repaint();
	}
	public void confirmChoice() {
		ui.confirmChoice();
	}
	public void allowConfirmation(boolean confirmed) {
		confirmChoiceButton.setEnabled(confirmed);
	}
	public void disableSpecialist() {
		specialist.setEnabled(false);
		ui.setSelectedToken(null);
	}
	
}
