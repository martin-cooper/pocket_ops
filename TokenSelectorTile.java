import javax.swing.JButton;

public class TokenSelectorTile extends JButton{
	private PocketOpsToken token;
	
	public TokenSelectorTile(PocketOpsToken token) {
		super(token.getName());
		this.token = token;
	}
	public PocketOpsToken getToken() {
		return token;
	}
}
