package pa3.cs535.cs.iastate.edu;

import java.util.ArrayList;
import java.util.Comparator;

public class Vertex implements Comparable<Vertex>{
	
	private int id;
	private String name;
	private int inDegree;
	private int outDegree;
	
	private float rank;
	
	private ArrayList<Vertex> nextVertices;
	
	public Vertex(){
		
	}
	public Vertex(String name){
		this.name = name;
		nextVertices = new ArrayList<Vertex>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	
	public float getRank() {
		return rank;
	}
	public void setRank(float rank) {
		this.rank = rank;
	}
	
	public ArrayList<Vertex> getNextVertices() {
		return nextVertices;
	}
	public void setNextVertices(ArrayList<Vertex> nextVertices) {
		this.nextVertices = nextVertices;
	}
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	@Override
	public String toString(){
		return this.name+":"+"InDegree:"+this.inDegree+";OutDegree:"+this.outDegree;
	}
	
	@Override
	public int compareTo(Vertex v){
		if(this.getRank()==v.getRank()){
			return 0;
		}else{
			return this.getRank() > v.getRank() ? 1 : -1;
		}
	}
	
	public static Comparator<Vertex> inDegreeComparator 
    = new Comparator<Vertex>() {

		public int compare(Vertex v1, Vertex v2) {

			if (v1.getInDegree() == v2.getInDegree()){
				return 0;
			}
			else{
				return v1.getInDegree() > v2.getInDegree() ? 1 : -1;
			}

		}
	};
}
