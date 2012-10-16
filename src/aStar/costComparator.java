package aStar;
import java.util.Comparator;


public class costComparator implements Comparator<searchNode> {

	@Override
	public int compare(searchNode arg0, searchNode arg1) {
		if(arg0.cost > arg1.cost)
			return 1;
		else
			return -1;
	}

}
