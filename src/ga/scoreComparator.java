package ga;

import java.util.Comparator;

public class scoreComparator implements Comparator<Chromosome> {

	@Override
	public int compare(Chromosome arg0, Chromosome arg1) {
		if(arg0.getScore() > arg1.getScore())
			return -1;
		else
			return 1;
	}

}
