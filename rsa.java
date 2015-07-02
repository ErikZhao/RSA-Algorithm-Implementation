package RSA;
import java.math.BigInteger;
import java.util.*;

public class rsa {

	private static Scanner s;

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		primeNumber prn = new primeNumber();
		rsaImplement rsi = new rsaImplement();
		
		/*prime check, input candidate number to check if it is prime*/
		System.out.println("=======================prime check=========================");
		System.out.println("please input the number you want to check if it is a prime:");
		s = new Scanner(System.in);
		BigInteger num= s.nextBigInteger();
		if(prn.primeCheck(num))
			System.out.println("this number is prime");
		else
			System.out.println("this number is not prime");
		
		
		/*prime generate, input number bits*/
		System.out.println("=======================prime generate=========================");
		System.out.println("please input number bits of prime number you want to generate:");		
		int numBit;
		numBit = s.nextInt();
		prn.primeGen(numBit);
		
		
		/*private key and public key generate, input p and q*/ 
		System.out.println("========================keys generate=========================");
		System.out.println("please input p: ");
		BigInteger p=s.nextBigInteger();
		System.out.println("please input q: ");
		BigInteger q=s.nextBigInteger();
		rsi.keygen(p, q);
		
		
		/*encrypt, input n, e, m*/
		System.out.println("========================encrypt=========================");
		System.out.println("please input n: ");
		BigInteger n=s.nextBigInteger();
		System.out.println("please input e: ");
		BigInteger e=s.nextBigInteger();
		System.out.println("please input m: ");
		BigInteger m=s.nextBigInteger();
		BigInteger c;
		c = rsi.encrypt(n, e, m);
		System.out.println("encypte c is: "+c);		
		
		
		/*decrypt, input n, d, c*/
		System.out.println("========================decrypt=========================");
		System.out.println("please input n: ");
		BigInteger n1=s.nextBigInteger();
		System.out.println("please input d: ");
		BigInteger d=s.nextBigInteger();
		System.out.println("please input c: ");
		BigInteger c1=s.nextBigInteger();
		BigInteger m1;
		m1 = rsi.decrypt(n1, d, c1);
		System.out.println("decrypt m is: "+m1);		
	}
	


}
