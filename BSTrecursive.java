import java.util.*;
import java.io.*;

public class BSTrecursive{

	/*
		Binary Search Tree (Iterative)
		Key properties: - left subtree is always lesser than the parent
						- right subtree is always greater than the parent
						- no duplicate nodes

		Recursive Insertion: 1) compare data item with current node
							 2) if smaller go left else go right
							 3) repeat step 2 till you hit null
							 4) insert! 


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

	/*
		PSUEDO CODE FOR INSERT:
		Insert(tree,item)
			check empty, insert at root
		else
			insertItem(tree,item)
	*/

	public void insert(int item){
		Node n = new Node(item); //add item into node

		boolean checkTree = true; //this flag checks the tree to see if its empty or not
		//Check if root is empty
		if(root==null){
			root = n; //left child and right child are automatically set to null
			checkTree = false; //function is done running
		}

		if(checkTree == true){
			//If you reach here, that means root is not empty and tree has nodes inside of it
			insertRec(root, n); //insert recursively! -- root is being passed in as a parameter because you always want to compare with root first!
		}

	}

	public void insertRec(Node first, Node node){ //first will be the node we will always compare with, node will be the node we want to insert
		if(first.item < node.item){ //the node we want to insert belongs in the right subtree
			if(first.rightChild != null){ //right child node is not null and we need to keep going if node is greater
				insertRec(first.rightChild, node);
			}else{ //right child is null and we can set it here! base case 
				first.rightChild = node; 
			}
		}

		if(first.item > node.item){ //the node we want to insert belongs in the left subtree
			if(first.rightChild != null){ //left child node is not null and we need to keep going if node is greater
				insertRec(first.leftChild, node);
			}else{ //left child is null and we can set it here! base case 
				first.leftChild = node; 
			}
		}

	}

	public static void Sort(BSTrecursive tree){ //to sort a binary search tree (ascending), simply do a inOrder Traversal
		//luckily I created two helper functions that already sort from because of debugging the tree
		printBST(tree);
	}

	static void inOrderTraversal(Node node,ArrayList<Integer> arr){
		if(node != null){
			inOrderTraversal(node.leftChild, arr);
			arr.add(node.item);
			inOrderTraversal(node.rightChild, arr);
		}
	}


	//need to print out BST
	static ArrayList<Integer> printBST(BSTrecursive tree){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		System.out.println("The root of this BST is: " + tree.root.item);
		inOrderTraversal(tree.root,arr);
		System.out.print("InOrder Traversal:( ");
		for(int i = 0; i<arr.size(); i++){
			System.out.print(arr.get(i) + ",");
		}
		System.out.print(" )");
		return arr;
	}	

	public static void main(String[] args){
		BSTrecursive Tree = new BSTrecursive();
		Tree.insert(30);
		Tree.insert(20);
		Tree.insert(40);
		Tree.insert(10);
		Tree.insert(25);
		Tree.insert(22);
		Tree.insert(50);
		Sort(Tree);
	}

	

}