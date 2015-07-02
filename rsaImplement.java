package RSA;
import java.math.BigInteger;
import java.util.Random;


public class rsaImplement {
	
	public static final int EBITS=4;
	
	public void keygen(BigInteger p, BigInteger q){
		
		Random rnd= new Random();
		BigInteger n= p.multiply(q);
		System.out.println("n is: "+n);
		BigInteger phiN= p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println("phiN is: "+phiN);
		BigInteger e = new BigInteger(EBITS, rnd);
		BigInteger d;
		
		while(!phiN.gcd(e).equals(BigInteger.ONE) || e.equals(BigInteger.ONE)){
			e = new BigInteger(EBITS, rnd);
		}
		d= e.modInverse(phiN);
		System.out.println("private key is: ("+n+", "+e+")");
		System.out.println("public key is: ("+n+", "+d+")");
		
	}
	
	//RSA encrypt, use e to encrypt plaintext m
	public BigInteger encrypt(BigInteger n, BigInteger e, BigInteger m){
		
		BigInteger c;
		c= m.modPow(e, n);
		return c;
	}
	
	//RSA decrypt, use d to decrypt cyphertext c
	public BigInteger decrypt(BigInteger n, BigInteger d, BigInteger c){
		
		BigInteger m;
		m= c.modPow(d, n);
		return m;
	}
}

/*
public static void main(String[] args) {
	// TODO Auto-generated method stub
	Random rnd= new Random();
	rsaImplement rsa= new rsaImplement();
	
	BigInteger p = new BigInteger("2932415249");
	BigInteger q = new BigInteger("3808178233");
	BigInteger n = new BigInteger("36244487427475665509");
	BigInteger e = new BigInteger("55");
	BigInteger d = new BigInteger("8194405850446038307");
	BigInteger m = new BigInteger(8,rnd);
	BigInteger c = new BigInteger("1347473421000108332");
	//rsa.keygen(p, q);
	
	//p = rsa.encrypt(n,e,m);
	//System.out.println("m is: "+m);
	//System.out.println("encrypt c is: "+p);
	q = rsa.decrypt(n, d, c);
	System.out.println("decrypt m is: "+q);
}
*/