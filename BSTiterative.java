import java.util.*;
import java.io.*;

public class BSTiterative{

	/*
		Binary Search Tree (Iterative)
		Key properties: - left subtree is always lesser than the parent
						- right subtree is always greater than the parent
						- no duplicate nodes

		Iterative Insertion:    - 1. Compare the node you want to insert with root
								- 2. If it is greater than the root, go to the right subtree
								- 3. If it is lesser than the root, go to the left subtree
								- 4. Compare to the root of the subtree and repeat steps 2,3,1
								- 5. Insert



	*/


	//GROUNDWORK


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

	public BSTiterative(){ //constructor that is used to declare a Tree
		this.root = null; //when Tree is created, root will be automatically set to null
	}


	//FUNCTIONS

	public void insertIter(int item){ //this method inserts into the tree iteratively and takes in the integer as a parameter
		Node n = new Node(item); //integer is now inserted in node n 

		//Check if root is empty
		if(root==null){
			root = n; //left child and right child are automatically set to null
		}

		//If you reach here, that means root is not empty and tree has nodes inside of it
	}



	public static void main(String[] args){
		BSTiterative Tree = new BSTiterative();
		System.out.println("Test");
	}

	

}