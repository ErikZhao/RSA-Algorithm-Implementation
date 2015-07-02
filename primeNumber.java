package RSA;
import java.math.BigInteger;
import java.util.Random;




public class primeNumber {
	

	
	public static final int NUMBERBITS=1024;
	static BigInteger consTwo= new BigInteger("2");
	public primeNumber(){
		
	}


	
	
	public void primeGen(int numBits){
		
		primeNumber s= new primeNumber();
		Random rnd= new Random();
		BigInteger num= new BigInteger(numBits,rnd);
		
		while(true){
			num=new BigInteger(numBits,rnd);
			int n=numBits-num.bitLength();
			//shift left to combine a big number of 1024bits and add one to produce odd number
			if(n!=0 || num.remainder(consTwo).equals(BigInteger.ZERO)){
				num=num.shiftLeft(n);
				num=num.add(BigInteger.ONE);
			}
			
			if(s.primeCheck(num)){
				break;
			}
			
		}
		System.out.println("the prime number is: "+num);
	}
	
	
	//check if this number is prime
	public boolean primeCheck(BigInteger rnd){
		
		BigInteger [] h = {new BigInteger("3"), new BigInteger("5"), new BigInteger("7"), new BigInteger("11"), 
				new BigInteger("13"), new BigInteger("17"), new BigInteger("19"), new BigInteger("23"), 
				new BigInteger("29"), new BigInteger("31"), new BigInteger("37"), new BigInteger("41"), 
				new BigInteger("43"), new BigInteger("47"), new BigInteger("53"), new BigInteger("59"), 
				new BigInteger("61"), new BigInteger("67"), new BigInteger("71"), new BigInteger("73"), 
				new BigInteger("79"), new BigInteger("83"), new BigInteger("89"), new BigInteger("97"), 
				new BigInteger("101"), new BigInteger("103"), new BigInteger("107"), new BigInteger("109"), 
				new BigInteger("113"), new BigInteger("127"), new BigInteger("131"), new BigInteger("137"), 
				new BigInteger("139"), new BigInteger("149"), new BigInteger("151"), new BigInteger("157"), 
				new BigInteger("163"), new BigInteger("167"), new BigInteger("173"), new BigInteger("179"), 
				new BigInteger("181"), new BigInteger("191"), new BigInteger("193"), new BigInteger("197"), 
				new BigInteger("199"), new BigInteger("211"), new BigInteger("223"), new BigInteger("227"), 
				new BigInteger("229"), new BigInteger("233"), new BigInteger("239"), new BigInteger("241"), 
				new BigInteger("251"), new BigInteger("257"), new BigInteger("263"), new BigInteger("269"), 
				new BigInteger("271"), new BigInteger("277"), new BigInteger("281"), new BigInteger("283"), 
				new BigInteger("293"), new BigInteger("307"), new BigInteger("311"), new BigInteger("313"), 
				new BigInteger("317"), new BigInteger("331"), new BigInteger("337"), new BigInteger("347"), 
				new BigInteger("349"), new BigInteger("353"), new BigInteger("359"), new BigInteger("367"), 
				new BigInteger("373"), new BigInteger("379"), new BigInteger("383"), new BigInteger("389"),
				new BigInteger("397"), new BigInteger("401"), new BigInteger("409"), new BigInteger("419"), 
				new BigInteger("421"), new BigInteger("431"), new BigInteger("433"), new BigInteger("439"), 
				new BigInteger("443"), new BigInteger("449"), new BigInteger("457"), new BigInteger("461"), 
				new BigInteger("463"), new BigInteger("467"), new BigInteger("479"), new BigInteger("487"), 
				new BigInteger("491"), new BigInteger("499"), new BigInteger("503"), new BigInteger("509"), 
				new BigInteger("521"), new BigInteger("523"), new BigInteger("541"), new BigInteger("547"), 
				new BigInteger("557"), new BigInteger("563"), new BigInteger("569"), new BigInteger("571"), 
				new BigInteger("577"), new BigInteger("587"), new BigInteger("593"), new BigInteger("599"), 
				new BigInteger("601"), new BigInteger("607"), new BigInteger("613"), new BigInteger("617"), 
				new BigInteger("619"), new BigInteger("631"), new BigInteger("641"), new BigInteger("643"), 
				new BigInteger("647"), new BigInteger("653"), new BigInteger("659"), new BigInteger("661"), 
				new BigInteger("673"), new BigInteger("677"), new BigInteger("683"), new BigInteger("691"), 
				new BigInteger("701"), new BigInteger("709"), new BigInteger("719"), new BigInteger("727"), 
				new BigInteger("733"), new BigInteger("739"), new BigInteger("743"), new BigInteger("751"), 				
				new BigInteger("757"), new BigInteger("761"), new BigInteger("769"), new BigInteger("773"), 
				new BigInteger("787"), new BigInteger("797"), new BigInteger("809"), new BigInteger("811"),
				new BigInteger("821"), new BigInteger("823"), new BigInteger("827"), new BigInteger("829"), 
				new BigInteger("839"), new BigInteger("853"), new BigInteger("857"), new BigInteger("859"), 
				new BigInteger("863"), new BigInteger("877"), new BigInteger("881"), new BigInteger("883"), 
				new BigInteger("887"), new BigInteger("907"), new BigInteger("911"), new BigInteger("919"), 
				new BigInteger("929"), new BigInteger("937"), new BigInteger("941"), new BigInteger("947"), 
				new BigInteger("953"), new BigInteger("967"), new BigInteger("971"), new BigInteger("977"), 
				new BigInteger("983"), new BigInteger("991"), new BigInteger("997"), };
		
		//try devide fisrt, devide small prime, if finding factor, return false;
		BigInteger smallNum= new BigInteger("1000");
		if(rnd.max(smallNum).equals(rnd)){
			for (int i=0; i<=h.length-1; i++){
				if(rnd.remainder(h[i]).equals(BigInteger.ZERO)){
					return false;
				}
			}
		}
		
		
		
		int numBits=rnd.bitLength();
		BigInteger rndMinOne=rnd.subtract(BigInteger.ONE);
		int s=0;//s
		BigInteger d;//d
		int k;
		int successfulCount=0;
		
		
		BigInteger remainder=rndMinOne.remainder(consTwo);

		//computer s and d
		while(remainder.equals(BigInteger.ZERO)){
			rndMinOne=rndMinOne.divide(consTwo);
			remainder=rndMinOne.remainder(consTwo);
			s++;
			
		}
		d=rndMinOne;

		
		//witness loop Miller-Rabin
		for(k=0;k<30;k++){

			Random random=new Random();
			//pick a random base number a range is [2,rnd-2]
			BigInteger a= new BigInteger(numBits, random);
			while (a.equals(rnd) ||a.equals(rnd.subtract(BigInteger.ONE)) || a.equals(BigInteger.ONE) || a.equals(BigInteger.ZERO) || a.max(rnd).equals(a)){

				a=new BigInteger(numBits,random);
			}

			BigInteger x= a.modPow(d, rnd); //to check if x equal to rnd-1 or 1

			//if x==1 or rnd-1, return successful count
			if (x.equals(BigInteger.ONE)){
				successfulCount++;
				continue;
			}
			if (x.equals(rnd.subtract(BigInteger.ONE))){
				successfulCount++;
				continue;
			}
				
			for(int sCount=0; sCount<=s-1;sCount++){
				x=x.modPow(consTwo, rnd);
				if(x.equals(BigInteger.ONE))
					return false;
				if(x.equals(rnd.subtract(BigInteger.ONE))){
					successfulCount++;
					break;
				}
				//return false;
			}
			
		}
		//every witness loop is right, successful count is equal to k times check, return true
		if(successfulCount==30)
			return true;
		else
			return false;
				
	}
		
}
