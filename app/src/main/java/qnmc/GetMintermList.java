package qnmc;

import java.util.Set;
import java.util.TreeSet;

public class GetMintermList {
	private static Set<String> set = new TreeSet<>();

    public static Set<String> getSet() {
        return set;
    }
	
	public void setMinList(String x){
		set.add(x);
	}

	public static Set<String> getMin(){
		return set;
	}
	
}





