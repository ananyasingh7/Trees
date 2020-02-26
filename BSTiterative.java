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

	public BSTiterative(){ //constructor that is used to declare a Tree
		this.root = null; //when Tree is created, root will be automatically set to null
	}

	public static void main(String[] args){
		BSTiterative Tree = new BSTiterative();
		System.out.println("Test");
	}

	

}