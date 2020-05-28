package br.com.opet.EzTicket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.swing.text.MaskFormatter;

public class Utils {

	@SuppressWarnings("deprecation")
	public static Date getDateFromString(String input) {
		try {
			if (input != null && input.equalsIgnoreCase("today")) {
				Date date = new Date();
				input = date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
			}
			return new SimpleDateFormat("dd/MM/yyyy").parse(input);
		} catch (Exception e) {}
		return null;
	}
	
	public static String capitalize(final String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}

		final int firstCodepoint = str.codePointAt(0);
		final int newCodePoint = Character.toTitleCase(firstCodepoint);
		if (firstCodepoint == newCodePoint) {
			// already capitalized
			return str;
		}

		final int newCodePoints[] = new int[strLen]; // cannot be longer than the char array
		int outOffset = 0;
		newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
		for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen;) {
			final int codepoint = str.codePointAt(inOffset);
			newCodePoints[outOffset++] = codepoint; // copy the remaining ones
			inOffset += Character.charCount(codepoint);
		}
		return new String(newCodePoints, 0, outOffset);
	}

	public static String formatter(String input, String pattern) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(input);
		} catch (ParseException e) {}
		return input;
	}
	
	public static void deleteSession() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		} catch (Exception e) {}
	}
	
}
