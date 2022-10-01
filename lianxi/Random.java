package lianxi;

import java.util.HashSet;
import java.util.Set;

public class Random {

	public static void main(String[] args) {
		Set set = new HashSet<>();
//		Random random = new Random();
		while(true) {
//			int n = random.nextInt(30)+1;
//			int random = (int)(Math.random()*(MAX-MIX+1))+MIN;
			int n = (int)(Math.random()*(68))+1;
			set.add(n);
			if(set.size()>=10) 
				break;
		}
		set.forEach((o)->{System.out.print(o+" ");});
//		set.stream().forEach((n)->{System.out.print(n+" ");});
//		set.iterator().forEachRemaining(System.out::println);
	}
}