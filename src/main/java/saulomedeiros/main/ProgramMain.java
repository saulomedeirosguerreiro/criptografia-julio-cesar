package saulomedeiros.main;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import saulomedeiros.cryptography.CaesarCipher;
import saulomedeiros.cryptography.Sha1;
import saulomedeiros.domain.Answer;
import saulomedeiros.helper.FileHelper;
import saulomedeiros.service.HttpService;

public class ProgramMain {
	
	public static void main(String[] args) throws Exception {
		
		String filename =  "answer.json";
		String token = "09b55d779ff57cfb3bb2f2a4001d07c94d8e17eb";
		String urlGenerateData = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
		String urlSubmitSolution = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";
		
		//Passo 1
		String jsonString = HttpService.get(urlGenerateData , token);
		
		//Passo 2
		FileHelper.saveToFile(jsonString, filename);
		
		//Passo 3
		Answer answer = Answer.get(jsonString);
		updateDecifrado(answer);
		
		//Passo 4
		
		updateResumoCriptografico(answer);
		
		//Passo 5
		jsonString = Answer.getJson(answer);
		FileHelper.saveToFile(jsonString, filename);
		
		//Passo 6       
		
		HttpService.sendFile(urlSubmitSolution,token, "answer", new File(filename), filename);
		
	}

	private static void updateDecifrado(Answer answer) {
		CaesarCipher caesarCipher = new CaesarCipher();
		String decodedCaesarCipherText = caesarCipher.decoder(answer.getCifrado(), answer.getNumero_casas());
		answer.setDecifrado(decodedCaesarCipherText);
	}

	private static void updateResumoCriptografico(Answer answer) throws NoSuchAlgorithmException  {
		Sha1 sha1 = new Sha1();
		String codedSha1Text = sha1.coder(answer.getDecifrado());
		answer.setResumo_criptografico(codedSha1Text);
	}
	
}
