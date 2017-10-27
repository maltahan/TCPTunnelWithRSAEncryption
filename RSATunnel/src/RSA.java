import java.math.BigInteger;
import java.util.Random;

public class RSA {

	private BigInteger p;
	private BigInteger q;
	public static BigInteger N;
	public static BigInteger phi;
	public static BigInteger e;
	private BigInteger d;
	private int bitlength = 1024;
	private Random r;

	public RSA() {
		r = new Random();
		// bitLength of the returned BigInteger.
		// Source of random bits used to select candidates to be tested for primality.
		p = BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		// trying to guess e which is 1 < e < phi and coprime with phi and N (the gcd
		// for prime numbers is 1)
		e = BigInteger.probablePrime(bitlength / 2, r);
		while (e.gcd(phi).compareTo(BigInteger.ONE) > 0 && e.gcd(N).compareTo(BigInteger.ONE) > 0
				&& e.compareTo(phi) < 0) {
			e.add(BigInteger.ONE);
		}

	}

	public RSA(BigInteger e, BigInteger phi, BigInteger N) {
		RSA.e = e;
		RSA.phi = phi;
		RSA.N = N;
		d = e.modInverse(phi);
	}

	static String bytesToString(byte[] encrypted) {
		String test = "";
		for (byte b : encrypted) {
			test += Byte.toString(b);
		}
		return test;
	}

	// Encrypt message
	// B -> b power e mode N
	public byte[] encrypt(byte[] message) {
		return (new BigInteger(message)).modPow(e, N).toByteArray();
	}

	// Decrypt message
	public byte[] decrypt(byte[] message) {
		return (new BigInteger(message)).modPow(d, N).toByteArray();
	}

}
