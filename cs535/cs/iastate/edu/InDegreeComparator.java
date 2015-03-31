package pa3.cs535.cs.iastate.edu;

import java.util.Comparator;

public class InDegreeComparator implements Comparator<Vertex>{

	@Override
	public int compare(Vertex v1, Vertex v2) {

		if (v1.getInDegree() == v2.getInDegree()){
			return 0;
		}
		else{
			return v1.getInDegree() > v2.getInDegree() ? 1 : -1;
		}

	}


}
