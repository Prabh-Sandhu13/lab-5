import java.util.Scanner;

public class htmlTranslator {
	public static void main( String[] args ) {
		String filename;
		Scanner userInput = new Scanner( System.in );

		// Get the filename from the user

		System.out.println( "filename? " );
		filename = userInput.next();
		userInput.close();

		// Ensure that the user provided some input.  Using a scanner,
		// the system should keep asking for input until it gets 
		// something.

		if (filename.length() > 0) {
			System.out.println( "filename retrieved: "+filename );
		} else {
			System.out.println( "No filename provided." );
		}
	}
}

