package sk.jazzman.brmi.server.ws;

/**
 * Server state
 * 
 * @author jano
 * 
 */
public interface ServerActionHandlerStateInf {
	public static final int NOT_INITIALIZED = 0;
	public static final int INITIALIZED = 1;
	public static final int INITIALIZATION_IN_PROGRESS = 2;

	public static final int CONNECTED = 2;
	public static final int DISCONNECTED = 3;

	/**
	 * Return state
	 * 
	 * @return
	 */
	public int getConnectionState();

	/**
	 * Setter connection state state
	 * 
	 * @param state
	 */
	public void setConnectionState(int connectionState);

	/**
	 * Return state
	 * 
	 * @return
	 */
	public int getInitializationState();

	/**
	 * Setter initialization state
	 * 
	 * @param initState
	 */
	public void setInitializationState(int initState);
}
