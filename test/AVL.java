import java.util.*;
import java.lang.*;
import java.io.*;

class AVL {

    //GROUNDWORK
    //---------------------------------------------------------------------------------------
    class Node{ //Nodes are part of the tree
        Node leftChild; //node can have a left child
        Node rightChild; //node can have a right child
        Node parent; //DEBUGGING PURPOSES
        int item; //every node needs an item
        int height; //"how high is it in the tree"
        int balanceFactor; //height(leftChild)-height(rightChild)

        Node(int item){ //constructor to create node
            this.leftChild=null; //left child is set to null
            this.rightChild=null; //right child is set to null
            this.item=item; //insert item into node
        }
    }
        
    public Node root; //root is declared and automatically empty
    public int traverseCount = 0;

    public AVL() { //constructor to declare a tree
        this.root = null;
    }
    //---------------------------------------------------------------------------------------

    public int getTraverseCount(){
        return traverseCount;
    }
    
    public Node insertIter(int item){ //good to return the root of the tree - Sresht
        /*
        OFFICE HOUR PSUEDOCODE

        parents = [parent1, parent2, parent3, etc]
        go through parents backwards
            update height
        go through parents backwards
            calculate BF
            if BF invalid
                this is Grandpa/GrandPivot
                do appropriate rotation

        NOTE: Two loops doesn't work for me, I found it easier to do it in one loop
        */

        Node n = new Node(item); //item is now inserted in node n
        boolean checkTree = true; //this flag checks and sees if the tree is empty or not
        if (root == null){
            root = n; //left child and right child are automatically set to null along with parent
            root.height = 0; //height is 0 because it no childnren
            checkTree = false; //function is done running 
        }

        if(checkTree){ 
            ////If you reach here, that means root is not empty and tree has nodes inside of it
            Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
            Node parentOfCurrentNode= null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
            //we need the parentOfCurrentNode so that we can easily assign children
            int amountOfParents = 0; //counter of parents
            ArrayList<Node> arrayOfParents = new ArrayList<Node>(); //array stores parents
            while (currentNode != null){ //when currentNode does EVENTUALLY reach null, that means we know where to insert using the 2 pointers
                traverseCount++; //added this for question 6
                amountOfParents++; //total parent counter
                parentOfCurrentNode = currentNode; //we assign parent to current because we can keep track of Node before it before it reaches null
                arrayOfParents.add(parentOfCurrentNode); //we store the parent in our parents array

                if(item>currentNode.item){
                    currentNode = currentNode.rightChild; //since the integer we are trying to insert is greater, we visit the right child and make that our current node
                }else{
                    currentNode = currentNode.leftChild; //since the integer we are trying to insert is less, we visit the left child and make that our current node
                }
            }
            n.parent = parentOfCurrentNode; //DEBUGGING: Parent should be updated regardless

            Node pass = null; //DEBUGGING: checking parent
            //now that the loop above is complete, currentNode is null and we have tracked the node before it with the parentOfCurrentNode node
			//now we have to insert into the tree
            if (parentOfCurrentNode.item > item){ //compare the integer to the node before the null
                parentOfCurrentNode.leftChild = n; //since the integer is greater than the node, we assign the integer as the left child
            }else{
                parentOfCurrentNode.rightChild = n; //since the integer is less than the parent node, we assign the integer as the left child
            }
            int sizeForHeights = amountOfParents; //DEBUGGING
            int sizeForBalanceFactor = amountOfParents; //work from the end of the parent list to the front
            int overall = amountOfParents; //just simply cloned the counter
            
            for(int j = 0; j<overall; j++){ //UPDATING HEIGHTS OF THE PARENTS OF THE NODE WE INSERTED
                Node nodes = arrayOfParents.get(sizeForBalanceFactor-1);
                if(nodes.leftChild == null && nodes.rightChild == null){
                    nodes.height=0;
                }else if(nodes.leftChild == null){
                    nodes.height=nodes.rightChild.height+1;
                }else if(nodes.rightChild == null){
                    nodes.height=nodes.leftChild.height+1;
                }else{
                    if(nodes.leftChild.height>nodes.rightChild.height){
                        nodes.height=nodes.leftChild.height+1;
                    }
                    else{
                        nodes.height=nodes.rightChild.height+1;
                    }
                }
                int BF; //UPDATING BALANCE FACTORS OF THE PARENTS OF THE NODE WE INSERTED
                if(nodes.leftChild == null && nodes.rightChild == null){
                    BF = 0; 
                    nodes.balanceFactor = 0;
                }else if(nodes.rightChild == null){
                    BF = nodes.leftChild.height + 1;
                    nodes.balanceFactor = BF;
                }else if(nodes.leftChild == null){
                    BF =  - 1 - nodes.rightChild.height;
                    nodes.balanceFactor = BF;
                }else{
                    BF = nodes.leftChild.height - nodes.rightChild.height;
                    nodes.balanceFactor = BF;
                }
                balance(BF, nodes, pass); //since it is BBST, we need to make balance the tree if the balance factors are 2 and -2 ( <= 1 is the goal), we balance by rotating
                sizeForBalanceFactor--; //continue to work backwards in the array
            }
            checkTree = false; //insert should be done by this point
        }
        return root; //returning root
    }

    public static int findMinIter(AVL tree){ //in binary search tree, the left most node in any subtree is the lowest
		Node root = tree.root; //find the root of the tree
		if(tree.root == null){ //check if tree is empty
			return -1; //means tree is empty
		}
		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)

		while(currentNode != null){ //traverse while reach null
			parentOfCurrentNode = currentNode; //keeps track of the node before we reach null
			currentNode = currentNode.leftChild; //going down the tree to the leftmost node in the entire tree
		}
		return parentOfCurrentNode.item; //since current is null, we have track of the parent of the current and we return the integer inside the node
	}

	public static int findMaxIter(AVL tree){// in binary search tree, the right most node in any subtree is the highest
		Node root = tree.root; //find the root of the tree
		if(tree.root == null){ //check if tree is empty
			return -1; //means tree is empty
		}
		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)

		while(currentNode != null){ //traverse while reach null
			parentOfCurrentNode = currentNode; //keeps track of the node before we reach null
			currentNode = currentNode.rightChild; //going down the tree to the rightmost node in the entire tree
		}
		return parentOfCurrentNode.item; //since current is null, we have track of the parent of the current and we return the integer inside the node
	}

	public static int findNextIter(AVL tree, int number){ //find the next biggest element in the tree
		Node root = tree.root;
		if(root == null){ //check if tree is empty
			return -1; //means tree is empty
		}

		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
		boolean foundNode = false;
		while(number != currentNode.item){ //I need to assign my number paremeter to the correct node in the tree so these new few lines do that for me
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

	public static int findPrevIter(AVL tree, int number){ //find the previous compared to node
		Node root = tree.root;
		if(root == null){ //check if tree is empty
			return -1; //means tree is empty
		}

		Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
		Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
		boolean foundNode = false;
		while(number != currentNode.item){ //I need to assign my number paremeter to the correct node in the tree so these new few lines do that for me
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


    //---------------------------------------------------------------------------------------
    //AVL INSERT HELPER FUNCTIONS

    //I know Sresht told me this function was inefficient :( butttt its so useful lol 
    public Node Search(int number, int flag){ //can search for a node or the parent of the node in the tree
        if(root == null){
            return root; //check if there is even a node
        }
        Node currentNode = root; //this pointer starts at the root and will eventually keep changing while we traverse the tree
        Node parentOfCurrentNode = null; //this pointer keeps track of the PARENT of the currentNode (so basically the node behind it)
        if(currentNode.item==number){
            flag = 0;
        }
        while(number != currentNode.item && flag != 0){ //traverse through the BST by comparing till eventually we reach a match 
            parentOfCurrentNode=currentNode;
            if(number<currentNode.item){ 
                currentNode=currentNode.leftChild;
            }else if(number>currentNode.item){
                currentNode=currentNode.rightChild;
            }
        }
        if(flag == 0){
            return currentNode; //found the node with the item
        }else{
            return parentOfCurrentNode; //found the parent to the node with the item
        }
    }

    //since it is BBST, we need to make balance the tree if the balance factors are 2 and -2 ( <= 1 is the goal), we balance by rotating
    public void balance(int balanceFactor, Node node, Node pass){ //NOTE: pass parameter is used when debugging!!!
        Node pass1, pass2, pass3, pass4; //each pass is the parent of the node where we are going to rotate
        if(balanceFactor == -2){ //GO RIGHT
            if(node.rightChild.balanceFactor > 0){ //RIGHT LEFT INBALANCE
                pass1 = Search(node.rightChild.item,1);
                pass2 = Search(node.item,1);
                rightRotation(node.rightChild, pass1);
                leftRotation(node, pass2);
            }else{                                 //RIGHT RIGHT INBALANCE
                pass2 = Search(node.item,1);
                leftRotation(node, pass2);
            }
        }
        if(balanceFactor == 2){ //GO LEFT
            if(node.leftChild.balanceFactor < 0){ //LEFT RIGHT INBALANCE
                pass3 = Search(node.leftChild.item,1); 
                pass4 = Search(node.item,1);
                leftRotation(node.leftChild, pass3); 
                rightRotation(node, pass4);
            }else{                               //LEFT LEFT INBALANCE
                pass4 = Search(node.item,1); 
                rightRotation(node, pass4);
            }
        }
    }
    
    /*

    T1, T2 and T3 are subtrees of the tree 
    rooted with y (on the left side) or x (on 
    the right side)           
             y                               x
            / \     Right Rotation          /  \
           x   T3   - - - - - - - >        T1   y 
          / \       < - - - - - - -            / \
         T1  T2     Left Rotation            T2  T3
    Keys in both of the above trees follow the 
    following order 
    keys(T1) < key(x) < keys(T2) < key(y) < keys(T3)
    So BST property is not violated anywhere

    */

    
    public Node leftRotation(Node x, Node xParent) { //if there is an imbalance in the right child of subtree, you perform left rotation
        // NOTES FROM OFFICE HOURS
        // 1) pivot -> parent of pivot
        // 2) child of pivot -> pivot
        // 3) parent of pivot -> LEFT child of pivot
        // ROTATION COMPLETE

        //swapping
        Node originalRightChild = x.rightChild; //keeping track of leftChild 
        x.rightChild = originalRightChild.leftChild; //pivot -> parent of pivot
        originalRightChild.leftChild = x; //parent of pivot -> LEFT child of pivot

        //need to finish the rotation
        boolean reorganize = true; //boolean ends reorganization
        while(reorganize){
            if(x != xParent.rightChild && x != xParent){ //pivot isnt the parents right child OR the parent itself
                xParent.leftChild = originalRightChild; //parent of pivot left child now has the right child
                reorganize = false;
            }else if(x == xParent){ //parent of pivot is the root
                root = originalRightChild;
                reorganize = false;
            }else if(x == xParent.rightChild){ //since the pivot isnt parents right child or the parent
                xParent.rightChild = originalRightChild; 
                reorganize = false;
            }
        }
        
        //left rotation should be complete up to this point
        Node y = originalRightChild;//x, y consistency makes life easy

        stabalizeTree(x, y); //update the height, balance factor, and parents of pivot and parent of pivot in order to stabalize the tree
        return x; //return pivot
    }

    public Node rightRotation(Node x, Node xParent) {  //if there is an imbalance in the left child of subtree, you perform right rotation
        // NOTES FROM OFFICE HOURS
        // 1) pivot -> parent of pivot
        // 2) child of pivot -> pivot
        // 3) parent of pivot -> RIGHT child of pivot
        // ROTATION COMPLETE

        //swapping
        Node originalLeftChild = x.leftChild; //keeping track of leftChild 
        x.leftChild = originalLeftChild.rightChild; //pivot -> parent of pivot
        originalLeftChild.rightChild = x; //parent of pivot -> RIGHT child of pivot

        //need to finish the rotation
        boolean reorganize = true; //boolean ends reorganization
        while(reorganize){
            if(x != xParent.rightChild && x != xParent){ //pivot isnt the parents right child OR the parent itself
                xParent.leftChild = originalLeftChild; //parent of pivot left child now has the left child
                reorganize = false;
            }else if(x == xParent){ //parent of pivot is the root
                root = originalLeftChild;
                reorganize = false;
            }else if(x == xParent.rightChild){ //since the pivot isnt parents right child or the parent
                xParent.rightChild = originalLeftChild; 
                reorganize = false;
            }
        }
        Node y = originalLeftChild; //x, y consistency makes life easy

        //right rotation should be complete up to this point
        stabalizeTree(x, y); //update the height, balance factor, and parents of pivot and parent of pivot in order to stabalize the tree
        return x; //return pivot
    }



    public void balanceFactorAfterRotation(Node pivot, Node beforePivot){ //after rotation, updates the balance factors of the pivot and the parent of pivot{
        boolean pivotBool = true;
        boolean beforePivotBool = true;
        while(pivotBool == true){
            if(pivot.leftChild == null && pivot.rightChild == null){
                pivot.balanceFactor = 0;
            }else if(pivot.rightChild == null){
                pivot.balanceFactor = pivot.leftChild.height + 1; //BF = height(leftchild) - (-1);
            }else if(pivot.leftChild == null){
                pivot.balanceFactor = - 1 - pivot.rightChild.height; //BF = height(rightchild) - (1);
            }else{
                pivot.balanceFactor = pivot.leftChild.height-pivot.rightChild.height; //BF = height(leftchild)-height(rightchild)
            }
            pivotBool = false;
        }
        while(beforePivotBool == true){
            if(beforePivot.leftChild == null && beforePivot.rightChild == null){ 
                beforePivot.balanceFactor = 0;
            }else if(beforePivot.rightChild == null){
                beforePivot.balanceFactor = beforePivot.leftChild.height + 1; //BF = height(leftchild) - (-1);
            }else if(beforePivot.leftChild == null){
                beforePivot.balanceFactor = beforePivot.rightChild.height -1; //BF = height(rightchild) - (1);
            }else{
                beforePivot.balanceFactor = beforePivot.leftChild.height-beforePivot.rightChild.height; //BF = height(leftchild)-height(rightchild)
            }
            beforePivotBool = false;
        }
    }



    public void heightAfterRotation(Node parents){ //function is from insert function and performs and it updates the height of the node we insert
        boolean parentsBool = true;
        while(parentsBool == true){
            if(parents.leftChild == null && parents.rightChild == null){
                parents.height=0;
            }else if(parents.leftChild == null){
                parents.height=parents.rightChild.height+1;
            }else if(parents.rightChild == null){
                parents.height=parents.leftChild.height+1;
            }else if(parents.leftChild == null){
                parents.height=parents.rightChild.height+1;
            }else{
                if(parents.leftChild.height>parents.rightChild.height){
                    parents.height=parents.leftChild.height+1;
                }
                else{
                    parents.height=parents.rightChild.height+1;
                }
            }
            parentsBool = false;
        }


    }

    public void stabalizeTree(Node pivot, Node beforePivot){ //update the height, balance factor, and parents of pivot and parent of pivot in order to stabalize the tree
        heightAfterRotation(pivot); //update height of pivot
        heightAfterRotation(beforePivot); //update height of pivots parent
        balanceFactorAfterRotation(pivot, beforePivot); //now heights are updated, update the balance factor
    }

  
    //---------------------------------------------------------------------------------------
    // Recursive Debuggers I found useful -- Ignore! 
    static void inOrderTraversal(Node node, ArrayList < Integer > arr) {
        if (node != null) {
            inOrderTraversal(node.leftChild, arr);
            arr.add(node.item);
            inOrderTraversal(node.rightChild, arr);
        }
    }

    public static void Sort(AVL tree) { //to sort a binary search tree (ascending), simply do a inOrder Traversal
        //luckily I created two helper functions that already sort from because of debugging the tree
        printBST(tree);
    }

    static ArrayList <Integer> printBST(AVL tree) {
        ArrayList < Integer > arr = new ArrayList < Integer > ();
        System.out.println("The root of this BST is: " + tree.root.item);
        inOrderTraversal(tree.root, arr);
        System.out.print("InOrder Traversal:( ");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + ",");
        }
        System.out.print(" )");
        return arr;
    }

    public static int getHeight(Node node){ //get height of node to find the Balance Factor of Node
        if(node != null){ 
            int leftChildCounter = getHeight(node.leftChild); 
            int rightChildCounter = getHeight(node.rightChild); 
            if (leftChildCounter > rightChildCounter){ // go through node's left and right subtree heights and return highest value which is the height
                return (leftChildCounter+1);
            }else{
                return (rightChildCounter+1);
            }  
        }else{
            return 0;
        }

    }

    public static int getBalanceFactor(Node node){ // Balance Factor (node) = height(LeftSubTree) - height(RightSubTree)
        //return (getHeight(node.leftChild)-getHeight(node.rightChild));
        int leftHeight = getHeight(node.leftChild);
        int rightHeight = getHeight(node.rightChild);
        return Math.abs(leftHeight-rightHeight);
    }


    public static boolean checkBalanced(Node node){ //Check if Tree is balanced
        if(node == null){
            return true; 
        } else if (checkBalanced(node.rightChild) && checkBalanced(node.leftChild) && getBalanceFactor(node) <= 1) { //Balance factor of every node in tree has to be below 1
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isBalanced(Node node) { 
        if (node == null) {
            return true;
        }else if (getBalanceFactor(node) < 2 && isBalanced(node.leftChild) && isBalanced(node.rightChild)) {
            return true;
        }else{
            return false;
        }
    }





    //---------------------------------------------------------------------------------------
    public static void main(String[] args) {
        AVL Tree = new AVL();
        
        /*
        Tree.insertIter(10);
        Tree.insertIter(11);
        Tree.insertIter(9);
        Tree.insertIter(7);
        Tree.insertIter(6);
        Tree.insertIter(8);
        Tree.insertIter(4);
        Tree.insertIter(3);
        */
        
        
        Tree.insertIter(15);
        Tree.insertIter(17);
        Tree.insertIter(16);
        Tree.insertIter(13);
        Tree.insertIter(22);
        Tree.insertIter(14);
        Tree.insertIter(20);
        Tree.insertIter(40);
        Tree.insertIter(37);
        Tree.insertIter(10);
        Tree.insertIter(11);
        

        /*
        Tree.insertIter(9);
        Tree.insertIter(7);
        Tree.insertIter(8);
        Tree.insertIter(5);
        Tree.insertIter(6);
        Tree.insertIter(3);
        Tree.insertIter(1);
        Tree.insertIter(2);
        Tree.insertIter(0);
        */

        /*
        Tree.insertIter(50);
        Tree.insertIter(100);
        Tree.insertIter(0);
        Tree.insertIter(1);
        Tree.insertIter(2);
        Tree.insertIter(3);
        Tree.insertIter(47);
        Tree.insertIter(48);
        Tree.insertIter(49);
        Tree.insertIter(99);
        */

        Sort(Tree);
        System.out.println("\nRoot: " + Tree.root.item);
        System.out.println("Root - Right Child: " + Tree.root.rightChild.item);
        System.out.println("Root - Left  Child: " + Tree.root.leftChild.item);
        System.out.println("Root - Left  Child  -Left  Child: " + Tree.root.leftChild.leftChild.item);
    }
}
