import java.util.Scanner;
import java.io.FileReader;
import java.io.InputStream;
import java.io.BufferedReader;

public class htmlTranslator {
	// Translate the contents of a file from the CMS notation
	// to html 1.0 format.

	private static void translateFile( String filename ) {
		BufferedReader userfile = null;
		String inputLine = "";

		// Iterate through the file, one line at a time.

		try {
			userfile = new BufferedReader( new FileReader( filename ) );

			while ( (inputLine = userfile.readLine()) != null) {
				System.out.println( inputLine );
			}
		} catch (Exception e) {
			System.out.println( e.getMessage() );
		} finally {
			// Close the file, if it was opened.

			try {
				userfile.close();
			} catch (Exception error) {
				System.out.println( error.getMessage() );
			}
		}
	}

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
			translateFile( filename );
		} else {
			System.out.println( "No filename provided." );
		}
	}
}

