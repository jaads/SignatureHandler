import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {

		SignatureHandler sigHandler = new SignatureHandler();

		String msg = "Text to be signed";
		byte[] signatureArr = sigHandler.getSignature(msg , "jan");
		boolean bool = sigHandler.verify(msg, signatureArr, "jan" );

		String signature = new BigInteger(1, signatureArr).toString(16);
		System.out.println("Message: " + msg);
		System.out.println("Signature: " + signature );
		System.out.println("Signature verification: " + bool );

	}

}

