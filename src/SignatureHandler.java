import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Scanner;

public class SignatureHandler {

	private final Signature EcDsaSign;
	private final String keyStorePath = "/home/.../keystore.jks";
	private final KeyStore store;

	protected SignatureHandler() {
		Security.addProvider(new BouncyCastleProvider());
		this.EcDsaSign = getSigInstance();
		this.store = loadKeyStore();
	}

	private Signature getSigInstance() {
		try {
			return Signature.getInstance("SHA256withECDSA", "BC");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private KeyStore loadKeyStore () {
		Scanner sc = new Scanner(System.in);
		try {
			KeyStore store = KeyStore.getInstance("BKS", "BC");
			System.out.println("Enter password for Keystore");
			String pwStore = sc.nextLine();
			store.load(new FileInputStream(keyStorePath), pwStore.toCharArray());
			return store;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private PrivateKey getPrivateKey(final String keyAlias) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter private key password to use the key from keystore");
			String secKeyPw = sc.nextLine();
			return (PrivateKey) store.getKey(keyAlias, secKeyPw.toCharArray());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private PublicKey getPublicKey(String keyAlias) {
		try {
			Certificate cert = store.getCertificate(keyAlias);
			return cert.getPublicKey();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	protected byte[] getSignature(String msg, final String keyAlias) {
		PrivateKey privKey = getPrivateKey(keyAlias);
		try {
			EcDsaSign.initSign(privKey);
			byte[] msgToSign = msg.getBytes("UTF-8");
			EcDsaSign.update(msgToSign);
			byte[] sigArr = EcDsaSign.sign();
			return sigArr;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	protected boolean verify(String msg, byte[] signature, String keyAlias) {
		PublicKey pubKey = getPublicKey(keyAlias);
		try {
			EcDsaSign.initVerify(pubKey);
			byte[] msgArr = msg.getBytes("UTF-8");
			EcDsaSign.update(msgArr);
			return EcDsaSign.verify(signature);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
