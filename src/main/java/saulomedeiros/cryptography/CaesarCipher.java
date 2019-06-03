package saulomedeiros.cryptography;

public class CaesarCipher {
	
	private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	public String decoder(String cipherText, int key){
		cipherText = cipherText.toLowerCase();
		String decodedText = "";
		char character;
		for (int i = 0; i < cipherText.length(); i++) {
			character = cipherText.charAt(i);
			character = replaceLetter(character, key);
			decodedText += character;
		}
		
		System.out.println("CaesarCipher - Decoded Text = " + decodedText);
		
		return decodedText;
	}
		
	private char replaceLetter(char previousCharacter, int key) {

		if (ALPHABET.contains(String.valueOf(previousCharacter))) {
			int indexNewCharacter = (ALPHABET.indexOf(previousCharacter) - key);
			int rest = indexNewCharacter < 0 ? indexNewCharacter % 26 + 26 : indexNewCharacter % 26;
			return ALPHABET.charAt(rest);
		}

		return previousCharacter;
	}
}
