import java.util.*;
import java.lang.*;
import java.io.*;


public class questionSix{

    public static void main(String[] args) {
        

        //Question 6 B
        BSTiterative iterative = new BSTiterative();
        AVL avl = new AVL();
        //ArrayList<Integer> tenThousandElements = ArrayOfIntegers.getRandomArray(10000);

        for(int i = 0; i<10000; i++){
            //iterative.insertIter(tenThousandElements.get(i));
            //avl.insertIter(tenThousandElements.get(i));
        }

        //System.out.println(iterative.getTraverseCount());
        //System.out.println(avl.getTraverseCount());



        //Question 6 C
        ArrayList<Integer> tenThousandElements = ArrayOfIntegers.getSortedArray(10000);
        for(int i = 0; i<10000; i++){
            iterative.insertIter(tenThousandElements.get(i));
            avl.insertIter(tenThousandElements.get(i));
        }

        System.out.println(iterative.getTraverseCount());
        System.out.println(avl.getTraverseCount());
    }
}