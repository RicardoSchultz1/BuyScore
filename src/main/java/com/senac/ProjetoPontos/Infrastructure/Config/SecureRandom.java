package com.senac.ProjetoPontos.Infrastructure.Config;

public final class SecureRandom {
    
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final java.security.SecureRandom RNG = new java.security.SecureRandom();

	private SecureRandom() {}

	public static String generateCode(int length) {
		if (length <= 0) throw new IllegalArgumentException("length must be > 0");
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int idx = RNG.nextInt(ALPHABET.length());
			sb.append(ALPHABET.charAt(idx));
		}
		return sb.toString();
	}

	public static String generate6LetterCode() {
		return generateCode(6);
	}
}
