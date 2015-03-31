package utils.os.test;

import utils.os.OSDiscerner;

/**
 * A simple test to see if your system is correctly recognised.
 * Just run and keep an eye on the console.
 * @author claudio
 *
 */
public class OSDiscernerValidation {

	public static void main(String[] args) {
		 
		System.out.println("The name of your O.S. is:\t"+OSDiscerner.getOS());
 
		if (OSDiscerner.isWindows()) {
			System.out.println("This is Windows");
		} else if (OSDiscerner.isMac()) {
			System.out.println("This is Mac");
		} else if (OSDiscerner.isUnix()) {
			System.out.println("This is Unix or Linux");
		} else if (OSDiscerner.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Unrecognised O.S., please let us know");
		}
	}

}
