package general;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * Found here:
 * http://www.rgagnon.com/javadetails/java-0198.html
 */

public class JTextFieldLimit extends PlainDocument {
	private int limit;
	// optional uppercase conversion
	private boolean toUppercase = false;

	JTextFieldLimit(int l) {
		super();
		limit = l;
	}

	JTextFieldLimit(int l, boolean upper) {
		super();
		limit = l;
		toUppercase = upper;
	}

	public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}
		if ((getLength() + str.length()) <= limit) {
			if (toUppercase) str = str.toUpperCase();
			super.insertString(offset, str, attr);
		}
	}
}