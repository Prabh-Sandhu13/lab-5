import java.util.Scanner;
import java.io.FileReader;
import java.io.InputStream;
import java.io.BufferedReader;

public class htmlTranslator {
	private static final String titleMarker = "title: ";

	// Determine if a given input line matches the format for a title
	// for the document;

	private static Boolean isTitleLine( String line ) {
		Boolean titleLine = false;

		// Start with the simple requirement that the title
		// keyword must start the line.

		if ((line.length() >= titleMarker.length()) && (titleMarker.equals(line.substring(0, titleMarker.length())))) {
			titleLine = true;
		}

		return titleLine;
	}

	// Take as a precondition that we have determined this line to
	// be a proper start to the title header line.
	// The CMS only allows a single header line for the file, so
	// we can take a simple approach to printing the content.
	// We are also told that there are no formatting codes in the title
	// line, so we can take the line text as-is as the title.

	private static void translateTitleLine( String line ) {
		System.out.print("<head>\n<title>");
		System.out.print(line.substring(titleMarker.length(), line.length()));
		System.out.println("</title>\n</head>");
	}

	// Translate the contents of a file from the CMS notation
	// to html 1.0 format.

	private static void translateFile( String filename ) {
		BufferedReader userfile = null;
		String inputLine = "";

		try {
			userfile = new BufferedReader( new FileReader( filename ) );

			// The file opened, so include an opening HTML tag

			System.out.println("<html>");

			// Ignore any leading blank lines

			while ( ((inputLine = userfile.readLine()) != null) &&
			        (inputLine.length() == 0)) {
				// Do nothing but consume the line
			}

			// We have a line in memory.  Check for a title line.
			// Process it and then bring in the next line of
			// the file so the upcoming while loop always has
			// a line already loaded.

			if (isTitleLine( inputLine )) {
				translateTitleLine( inputLine );
				inputLine = userfile.readLine();
			}

			// Iterate through the body of the file, one line at a time.
			// Recall that we already have one line in memory.

			while ( inputLine != null) {
				System.out.println( inputLine );
				inputLine = userfile.readLine();
			}

			// Close the HTML tag

			System.out.println("</html>");
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

