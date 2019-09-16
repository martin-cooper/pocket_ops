import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Command implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8324860847127752934L;
	public abstract void resolveCommand(PocketOpsGameState b, ObjectOutputStream s, PocketOpsUI ui);
}
