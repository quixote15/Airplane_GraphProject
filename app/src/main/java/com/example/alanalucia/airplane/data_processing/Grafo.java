/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alanalucia.airplane.data_processing;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author CreuzaFDR
 */
public class Grafo {
  
    private LinkedList<Pair<Integer,Integer>>[]adj;
    private int v;
    private int[] distancia;
    private LinkedList<Integer> previous;
    public Grafo(int v){
        distancia = new int[v];
        previous = new LinkedList();
        this.v = v;
        adj = (LinkedList<Pair<Integer,Integer>>[]) new LinkedList[v];
        for(int i = 0; i < v; i++){
            adj[i] = new LinkedList();
        }
        //adicionar arestas
        iniciarRotas();
    }
    
    /**
     * retorna a lista de arestas com extremo no vertice inicial
     * @param startvertice
     * @return 
     */

    public LinkedList<Pair<Integer,Integer>> getAdjacencyList(int startvertice) {
        LinkedList<Pair<Integer,Integer>> tmp =  (LinkedList<Pair<Integer,Integer>>) new LinkedList<>(adj[startvertice]);
        return tmp;
     }

    public void iniciarRotas(){
        addEdge(0,1,20); //AJU-BSB
        addEdge(0,2,50);//AJU-GRU
        addEdge(0,3,70);//aju-CGH
        addEdge(0,4,75);//aju-GIG
        addEdge(1,2,15);//bsb-gru
        addEdge(1,3,65);//bsb-cgh
        addEdge(1,4,85);//bsb-gig
        addEdge(3,4,20);//CGH-GIG
        addEdge(3,5,22); //CGH-SDU
        addEdge(3,15,66); //CGH-XAP
        addEdge(2,4,23);//GRU-GIG
        addEdge(2,5,34);//gru-sdu
        addEdge(2,9,45);//gru-rec
        addEdge(2,10,55);//gru-gyn
        addEdge(2,6,38);//gru-cwb
        addEdge(4,9,29);//gig-rec
        addEdge(4,14,51);//gig-jpo
        addEdge(5,13,44);//sdu-mcz
        addEdge(5,11,76);//sdu-gyn
        addEdge(6,13,88);//cwb-mcz


    }

      // Appends a new Edge to the linked list
    public void addEdge(int startVertex, int endVertex, int weight) {
        adj[startVertex].add(new Pair<>(endVertex, weight));
        adj[endVertex].add(new Pair<>(startVertex,weight));
    }
    
          // Returns number of outward edges from a vertex
    public int getNumberOfEdgesFromVertex(int startVertex) {
        return adj[startVertex].size();
    }
    
    public int size(){
        return v;
    }
       
     // Prints the Adjaency List
    public void printAdjacencyList() {
        int i = 0;
          
        for (LinkedList< Pair<Integer, Integer> > list : adj) {
            System.out.print("adjacencyList[" + i + "] -> ");
              
            for (Pair<Integer, Integer> edge : list) {
                System.out.print(edge.getFirst() + "(" + edge.getSecond() + ")");
            }
              
            ++i;
            System.out.println();
        }
    }

    public LinkedList<Pair<Integer, Integer>>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Pair<Integer, Integer>>[] adj) {
        this.adj = adj;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int[] getDistancia() {
        return distancia;
    }

    public void setDistancia(int[] distancia) {
        this.distancia = distancia;
    }

    public LinkedList<Integer> getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedList<Integer> previous) {
        this.previous = previous;
    }
    
    public int[] getPath(int target){
        Stack stack = new Stack();

        int u = target;
        while ( u > 0 && previous.get(u)!=null){
            stack.push(u);
        }
        stack.push(u);

        int[] lista = new int[stack.size()+3];
        int i = 0;

       while(!stack.empty()){
           lista[i] = (int)stack.pop();
           i++;
       }
        stack = null;
        System.gc(); //garbage colector
        return lista ;
    }
    
}
