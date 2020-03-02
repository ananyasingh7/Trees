public class AVLiterative{

    class Node{                   
		Node leftChild;			  	
		Node rightChild;		  
		int item;				  
  
		Node(int item){           
			this.leftChild=null;  
			this.rightChild=null; 
			this.item=item;       
		}

    }
    
    public Node root; 

	public AVLiterative(){ 
		this.root = null; 
	}


    public static void main(String[] args) {
        
    }
}