package com.core.blore.crypto;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.salt.RandomSaltGenerator;

/**
 * Password encryptor
 * 
 * <p>
 * Creates a one way hash of provided string using SHA-256 algorithm.
 * </p>
 * 
 * @author Vijay Pawar
 */
public final class PasswordEncryptor {

	/** The inst. */
	private static volatile PasswordEncryptor inst;

	/** The digester. */
	private static StandardStringDigester digester = new StandardStringDigester();

	/** The generator. */
	private static RandomSaltGenerator generator = new RandomSaltGenerator();

	/** The Constant ITERATIONS. */
	private static final int ITERATIONS = 50000;

	/**
	 * Instantiates a new password encryptor.
	 */
	private PasswordEncryptor() {
		digester.setAlgorithm("SHA-256");
		digester.setIterations(ITERATIONS);
		digester.setSaltGenerator(generator);
	}

	/**
	 * Gets the single instance of PasswordEncryptor.
	 * 
	 * @return single instance of PasswordEncryptor
	 */
	public static PasswordEncryptor getInstance() {
		if (inst == null) {
			inst = new PasswordEncryptor();
		}
		return inst;
	}

	/**
	 * Encrypt.
	 * 
	 * @param passwordToBeEncrypted
	 *            the password to be encrypted
	 * @return the string
	 */
	public synchronized String encrypt(final String passwordToBeEncrypted) {
		if (passwordToBeEncrypted != null) {
			return digester.digest(passwordToBeEncrypted);
		}
		return null;
	}

	/**
	 * Check password.
	 * 
	 * @param enteredPassword
	 *            the entered password
	 * @param databasePassword
	 *            the database password
	 * @return true, if successful
	 */
	public synchronized boolean checkPassword(final String enteredPassword, final String databasePassword) {
		if (enteredPassword != null && databasePassword != null) {
			return digester.matches(enteredPassword, databasePassword);
		}
		return false;
	}

}
