import java.util.*;
import java.lang.*;
import java.io.*;

public class AVLiterative{

    class Node{                   
		Node leftChild;			  	
		Node rightChild;		  
        int item;
        int height;				  
  
		Node(int item){           
			this.leftChild=null;  
			this.rightChild=null; 
            this.item=item;
            this.height = 1;       
        }
    }

    
    public Node root; 

	public AVLiterative(){ 
		this.root = null; 
    }
    
    // AVL Tree Helper Functions
    public static int getHeight(Node node){ //get height of node to find the Balance Factor of Node
        if (node != null){ 
            int leftChildCounter = getHeight(node.leftChild); 
            int rightChildCounter = getHeight(node.rightChild); 
            if (leftChildCounter > rightChildCounter){ // go through node's left and right subtree heights and return highest value which is the height
                return (leftChildCounter+1);
            }else{
                return (rightChildCounter+1);
            }  
        } 

        return -1;
    } 

    public static int getBalanceFactor(Node node){ // Balance Factor (node) = height(LeftSubTree) - height(RightSubTree)
        return (getHeight(node.leftChild)-getHeight(node.rightChild)); 
    }

    public static boolean checkBalanced(Node node){ //Check if Tree is balanced
        if(node == null){
            return true; //getBalanceFactor(node) <= 1
        } else if (checkBalanced(node.rightChild) && checkBalanced(node.leftChild) && getBalanceFactor(node) <= 1) { //Balance factor of every node in tree has to be below 1
            return true;
        }else{
            return false;
        }
    }

    public void insertIter(int item){ //this method inserts into the tree iteratively and takes in the integer as a parameter
		Node n = new Node(item); //integer is now inserted in node n 
		boolean checkTree = true; //this flag checks the tree to see if its empty or not
		//Check if root is empty
		if(root==null){
			root = n; //left child and right child are automatically set to null
			checkTree = false; //function is done running
		}

		if(checkTree == true){ 
			//If you reach here, that means root is not empty and tree has nodes inside of it
			Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
			Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
			//we need the parentOfCurrentNode so that we can easily assign children

			while(currentNode != null){ //when currentNode does EVENTUALLY reach null, that means we know where to insert using the 2 pointers
				parentOfCurrentNode = currentNode; //we assign parent to current because we can keep track of Node before it before it reaches null
				//compare the integer we are trying to insert with the currentNode, if the integer is greater: go right, if the integer is smaller, go left
				if(item > currentNode.item){ 
					currentNode = currentNode.rightChild; //since the integer we are trying to insert is greater, we visit the right child and make that our current node
				}else{
					currentNode = currentNode.leftChild; //since the integer we are trying to insert is less, we visit the left child and make that our current node
				}
			}
			//now that the loop above is complete, currentNode is null and we have tracked the node before it with the parentOfCurrentNode node
			//now we have to insert into the tree
			if(item > parentOfCurrentNode.item){ //compare the integer to the node before the null
				parentOfCurrentNode.rightChild= n; //since the integer is greater than the node, we assign the integer as the left child
			}else{
				parentOfCurrentNode.leftChild = n; //since the integer is less than the parent node, we assign the integer as the left child
			}
        }
        

			
    }
    
    static void inOrderTraversal(Node node,ArrayList<Integer> arr){
		if(node != null){
			inOrderTraversal(node.leftChild, arr);
			arr.add(node.item);
			inOrderTraversal(node.rightChild, arr);
		}
	}


	//need to print out BST
	static ArrayList<Integer> printBST(AVLiterative tree){
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
    
    public static void Sort(AVLiterative tree){ //to sort a binary search tree (ascending), simply do a inOrder Traversal
		//luckily I created two helper functions that already sort from because of debugging the tree
		printBST(tree);
	}

    public static void main(String[] args) {
        AVLiterative Tree = new AVLiterative();
		Tree.insertIter(9);
        Tree.insertIter(5);
		Tree.insertIter(15);
		Tree.insertIter(3);
		Tree.insertIter(6);
		Tree.insertIter(11);
        Tree.insertIter(21);
        Tree.insertIter(2);
        Tree.insertIter(13);
        Tree.insertIter(18);
        Tree.insertIter(23);
        Tree.insertIter(16);
        Tree.insertIter(20);
        Tree.insertIter(2012);
        Tree.insertIter(20121);
        Tree.insertIter(201211);
        Tree.insertIter(20120);
        //Tree.insertIter(0);
        Sort(Tree);
        System.out.println();
        int BF1 = getBalanceFactor(Tree.root.leftChild);
        //int H1 = maxDepth(Tree.root.rightChild);
        System.out.println(BF1);
        //int BF2 = getBalanceFactor(Tree.root.rightChild);
        //System.out.println(BF2);
        //int BF3 = getBalanceFactor(Tree.root.leftChild);
        //System.out.println(BF3);
        
        if(checkBalanced(Tree.root)){
            System.out.println("Tree is balanced");
        }else{
            System.out.println("Tree is not balanced");
        }
        
    }
}