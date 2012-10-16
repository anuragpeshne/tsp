package aStar;
import java.util.Comparator;


public class distComparator implements Comparator<spanTreeEdge> {
	@Override
	public int compare(spanTreeEdge n1, spanTreeEdge n2){
		if(n1.getCost() < n2.getCost())
			return -1;
		else if(n1.getCost() > n2.getCost())
			return 1;
		else
			return 1;
	}
}
