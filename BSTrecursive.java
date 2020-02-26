import java.util.*;
import java.io.*;

public class BSTrecursive{

	/*
		Binary Search Tree (Iterative)
		Key properties: - left subtree is always lesser than the parent
						- right subtree is always greater than the parent
						- no duplicate nodes

		Recursive Insertion: 


	*/

	class Node{                   //Nodes are part of tree
		Node leftChild;			  //node can have a left child	
		Node rightChild;		  //node can have a right child	
		int item;				  //node needs item	
  
		Node(int item){           //constructor to create node
			this.leftChild=null;  //left child is set to null
			this.rightChild=null; //right child is set to null
			this.item=item;       //insert item into node
		}

	}

	public Node root; //root is declared and automatically empty

	public BSTrecursive(){ //constructor that is used to declare a Tree
		this.root = null; //when Tree is created, root will be automatically set to null
	}

	public static void main(String[] args){
		BSTrecursive Tree = new BSTrecursive();
		System.out.println("Test");
	}

	

}