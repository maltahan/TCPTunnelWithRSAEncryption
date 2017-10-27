import java.io.Serializable;
import java.math.BigInteger;

public class ClientServerInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public byte[] encrypted;
	public BigInteger e;
	public BigInteger N;
	public BigInteger phi;
	
	public ClientServerInformation( byte[] encrypted,BigInteger e, BigInteger N,BigInteger phi ) {
		 
		this.encrypted = encrypted;
		this.e = e;
		this.N = N;
		this.phi = phi;
		 
	 }
	
}


 