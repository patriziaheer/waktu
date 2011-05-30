package ch.hsr.waktu.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Md5 {

	private Md5() {

	}

	public static String hash(final String input) {
		if (input != null) {
			MessageDigest digest = null;
			byte[] hash = null;

			try {
				digest = java.security.MessageDigest.getInstance("MD5");

				try {
					digest.update(input.getBytes("UTF-8"));
					hash = digest.digest();
					return byteArrayToString(hash);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String byteArrayToString(final byte[] binaryData) {

		final char[] hexadecimal = { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		if (binaryData.length != 16) {
			return null;
		}

		char[] buffer = new char[32];

		for (int i = 0; i < 16; i++) {
			int low = (int) (binaryData[i] & 0x0f);
			int high = (int) ((binaryData[i] & 0xf0) >> 4);
			buffer[i * 2] = hexadecimal[high];
			buffer[i * 2 + 1] = hexadecimal[low];
		}

		return new String(buffer);
	}
}
