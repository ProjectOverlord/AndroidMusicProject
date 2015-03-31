package utils.os;

/**
 * This class provides useful tools for the detection of the O.S. of the terminal in use.
 * This is a class designed to act as a Singleton, since I can't see why one would need
 * to use two or more instances.
 * The methods are static, so you don't need to call an object. The class is enough.
 * 
 * @author claudio
 *
 */
public class OSDiscerner {
	
	/* Questa stringa contiene il nomed del sistema operativo */
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	private OSDiscerner() {
		// Do nothing, string OS already filled with correct content
	}
 
	public static String getOS() {
		return OS;
	}

	public static boolean isWindows() {
 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OS.indexOf("sunos") >= 0);
 
	}
 
}