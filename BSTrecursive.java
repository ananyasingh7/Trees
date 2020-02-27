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

	public void delete(int item){ //its 2:30 in the morning and I don't think I have enough brain cells left to do this
		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
		boolean foundNode = false;
		while(item != currentNode.item){        //I need to assign my number paremeter to the correct node in the tree so these new few lines do that for me
			parentOfCurrentNode = currentNode;
			if(item == currentNode.item){
				foundNode = true;
			}
			if(item > currentNode.item){
				currentNode = currentNode.rightChild;
			}
			if(item < currentNode.item){
				currentNode = currentNode.leftChild;
			}
		}

		boolean checkTree = true; //this flag checks the tree to see if its empty or not
		//Check if root is empty
		if(root==null){
			checkTree = false; //function is done running
		}

		if(checkTree == true){
			deleteRec(root, currentNode); //delete the node by recursively checking, starting from the top
		}


	}

	public Node deleteRec(Node first, Node node){ //this is the part where I can say I have no more brain cells to do this... goodnight!
		if(root.leftChild == null){
			return first.rightChild;
		}else if (root.rightChild == null){
			return first.leftChild;
		}
	
		return first;
	}

	public static int findMaxRec(Node node){// in binary search tree, the right most node in any subtree is the highest
		if(node.rightChild == null){
			return node.item; //since right child is null, we return the node before the right child.. this is our basecase
		}else{
			return findMaxRec(node.rightChild); //keep going till the right child hits null
		}
	}

	public static int findMinRec(Node node){// in binary search tree, the left most node in any subtree is the lowest
		if(node.leftChild == null){
			return node.item; //since left child is null, we return the node before the left child.. this is our basecase
		}else{
			return findMinRec(node.leftChild); //keep going till the left child hits null
		}
	}

	public static int findNextRec(BSTrecursive tree, int number){ //find the next biggest element in the tree
		Node root = tree.root;
		if(root == null){ //check if tree is empty
			return -1; //means tree is empty
		}

		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
		boolean foundNode = false;
		while(number != currentNode.item){        //I need to assign my number paremeter to the correct node in the tree so these new few lines do that for me
			parentOfCurrentNode = currentNode;
			if(number == currentNode.item){
				foundNode = true;
			}
			if(number > currentNode.item){
				currentNode = currentNode.rightChild;
			}
			if(number < currentNode.item){
				currentNode = currentNode.leftChild;
			}
		}

		ArrayList<Integer> arr = new ArrayList<Integer>();
		inOrderTraversal(tree.root,arr);
		for(int i = 0;i<arr.size(); i++){
			if(currentNode.item == arr.get(i)){ //compare the node item with the ArrayList 
				return arr.get(i+1); //FindNext finds the prev in an ordered list
			}
		}

		return -1;
	}

	public static int findPrevRec(BSTrecursive tree, int number){ //find the next biggest element in the tree
		Node root = tree.root;
		if(root == null){ //check if tree is empty
			return -1; //means tree is empty
		}

		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
		boolean foundNode = false;
		while(number != currentNode.item){        //I need to assign my number paremeter to the correct node in the tree so these new few lines do that for me
			parentOfCurrentNode = currentNode;
			if(number == currentNode.item){
				foundNode = true;
			}
			if(number > currentNode.item){ 
				currentNode = currentNode.rightChild;
			}
			if(number < currentNode.item){
				currentNode = currentNode.leftChild;
			}
		}

		ArrayList<Integer> arr = new ArrayList<Integer>();
		inOrderTraversal(tree.root,arr);
		for(int i = 0;i<arr.size(); i++){
			if(currentNode.item == arr.get(i)){ //compare the node item with the ArrayList 
				return arr.get(i-1); //FindPrev finds the prev in an ordered list
			}
		}

		return -1;
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
		int max = findMaxRec(Tree.root);
		System.out.println();
		System.out.println("Maximum Node: " + max);
		int min = findMinRec(Tree.root);
		System.out.println("Minimum Node: " + min);
		int findNext = findNextRec(Tree, 22);
		System.out.println(findNext);
		int findPrev = findPrevRec(Tree, 22);
		System.out.println(findPrev);

		//System.out.println();
		//Tree.delete(40);   
		//Tree.delete(22);
		//Sort(Tree);
	}

	

}