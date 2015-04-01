package pa3.cs535.cs.iastate.edu;

import java.util.Iterator;
import java.util.TreeSet;


public class PageRank {
	
	private Graph graph;
	
	public PageRank(Graph graph){
		this.graph = graph;
	}
	
	public float pageRankOf(String vertex){
		Vertex v = this.graph.retrieveVertex(vertex);
		return v.getRank();
	}
	
	public void topKPageRank(int k){
		TreeSet<Vertex> tsVertices = new TreeSet<Vertex>();
		for(Integer i:this.graph.getGraphMap().keySet()){
			tsVertices.add(this.graph.getGraphMap().get(i));
		}
		Iterator<Vertex> itr=tsVertices.iterator();
		int count=0;
		Vertex v;
		while(count<k){
			v = itr.next();
			System.out.println(v.getName()+":"+v.getRank());
			count++;
		}
	}
	
	public void pageRank(float beta,float sigma,Graph graph){
		boolean converge = false;
		Vertex v;
		float initRank = (float) (1.0/graph.getGraphSize());
		for(Integer i:graph.getGraphMap().keySet()){
			v = graph.getGraphMap().get(i);
			v.setRank(initRank);
		}
		float currentRank[] = new float[graph.getGraphSize()];
		float nextRank[] = new float[graph.getGraphSize()];
		int count = 0;
		while(!converge){
			currentRank = graph.getVertexRankVector(currentRank);
			singleRound(graph,beta);
			nextRank = graph.getVertexRankVector(nextRank);
			if(diffVertexVectors(currentRank,nextRank,sigma)){
				converge = true;
			}
			count++;
			System.out.println(count);
		}
		for(int i=0;i<nextRank.length;i++){
//			System.out.println(nextRank[i]);
		}
	}
	
	private void singleRound(Graph graph,float beta){
		
		Vertex v;
		Vertex w;
		float tempRank;
		int numOfVertice = graph.getGraphSize();
		float baseRank = 1-beta;
		for(Integer i:graph.getGraphMap().keySet()){
			v = graph.getGraphMap().get(i);
			if(v.getOutDegree()==0){
				tempRank = beta*v.getRank()/numOfVertice;
				for(Integer j:graph.getGraphMap().keySet()){	
					w = graph.getGraphMap().get(j);
					w.setRank(baseRank+tempRank);
				}
			}else{
				tempRank = beta*v.getRank()/v.getNextVertices().size();
				for(Vertex x:v.getNextVertices()){	
					x.setRank(baseRank+tempRank);
				}
			}
		}
	} 
	
	private boolean diffVertexVectors(float[] v1,float[] v2,float sigma){
		boolean result = true;
		if(v1.length!=v2.length){
			System.err.println("Vectors have different length!!!");
			result = false;
		}else{
			for(int i=0;i<v1.length;i++){
				if(Math.abs(v1[i]-v2[i])>sigma){
					result = false;
					break;
				}
			}
		}
		return result;
	}

}
