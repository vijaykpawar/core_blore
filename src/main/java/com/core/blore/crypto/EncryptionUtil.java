package com.core.blore.crypto;

import com.core.blore.utils.CoreUtils;


/**
 * Provides utility methods for handling encryption.
 * 
 * @author Vijay Pawar
 */
public final class EncryptionUtil {

	/** The key. */
	private static String key;

	/**
	 * Instantiates a new encryption util.
	 * 
	 * @param key
	 *            the key
	 */
	public EncryptionUtil(String key) {
		super();
	}

	static {
		if (key == null || key.trim().isEmpty()) {
			key = CoreUtils.DEFAULT_ENCRYPTION_KEY;
		}
	}

	/**
	 * Encrypts any string using key given in configuration.
	 * 
	 * @param msg
	 *            the msg
	 * @return the string
	 */
	public static String encrypt(final String msg) {
		FWEEWCrypto crypt = new FWEEWCrypto(key);
		String data = crypt.encryptAsBase64(msg.getBytes());
		// logger.info("Message : " + data) ;
		return data;
	}

	/**
	 * Decrypts any string using key given in configuration.
	 * 
	 * @param msg
	 *            the msg
	 * @return the string
	 */
	public static String decrypt(final String msg) {
		FWEEWCrypto crypt = new FWEEWCrypto(key);
		String data = crypt.decryptBase64(msg);
		// logger.info("Message : " + data) ;
		return data;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
	/*	System.out.println(encrypt("{" + "\"req\": { " + "  \"appd\": { " + "    \"appid\": \"\", " + "    \"appname\": \"\", "
				+ "    \"org\": \"\", " + "    \"version\": \"\"" + "  }, " + "  \"devd\": { " + "    \"imei\": \"000000000000000\", "
				+ "   \"make\": \"generic\", " + "  \"mod\": \"generic\", " + "   \"os\": \"iphone\", " + "   \"scrh\": \"480\", "
				+ "   \"scrw\": \"320\" " + " }, " + " \"uo\": [ " + "  { " + "     \"k\": \"gps\", " + "     \"v\": \"182|184\" " + "   } " + " ], "
				+ " \"imei\": \"000000000000000\", " + " \"ip\": [ " + "   { " + "     \"k\": \"start\", " + "    \"v\": \"0\" " + "  } " + " ], "
				+ " \"serv\": \"login\"," + " \"sid\": \"\", " + " \"stats\": [\"test|test|5\"] " + "}}"));

		System.out
				.println("Decrypted: "
						+ decrypt("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"));
*/
		String str=encrypt("hello world");
		System.out.println("  str enc is ::"+str);
		
		
		System.out.println("");
		System.out.println("decripted is ::"+decrypt(str));
	}
}
