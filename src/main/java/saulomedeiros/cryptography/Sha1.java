package saulomedeiros.cryptography;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {

	public String coder(String input) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] messageDigest = md.digest(input.getBytes());

		BigInteger no = new BigInteger(1, messageDigest);

		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		
		System.out.println("SHA1 - coded Text = " + hashtext);
		
		return hashtext;
	}

}
