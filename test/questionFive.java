import java.util.*;
import java.lang.*;
import java.io.*;

public class questionFive{

    public static void main(String[] args) {
        

        //Question 5 A
        BSTrecursive recursive = new BSTrecursive();
        AVL avl = new AVL();
        //ArrayList<Integer> tenThousandElements = ArrayOfIntegers.getRandomArray(10000);
        for(int i = 0; i<10000; i++){
            //recursive.insert(tenThousandElements.get(i));
            //avl.insertIter(tenThousandElements.get(i));
        }

        //System.out.println(recursive.root.item);
        //System.out.println(avl.root.item);



        //Question 5 B
        ArrayList<Integer> tenElements = ArrayOfIntegers.getRandomArray(10);
        for(int i = 0; i<10; i++){
            //recursive.insert(tenThousandElements.get(i));
            //avl.insertIter(tenThousandElements.get(i));
        }

        //System.out.println(recursive.root.item);
        //System.out.println(avl.root.item);



        //Question 5 C
        BSTiterative iterative = new BSTiterative();
        ArrayList<Integer> tenThousandElements = ArrayOfIntegers.getRandomArray(10000);
        for(int i = 0; i<10000; i++){
            iterative.insertIter(tenThousandElements.get(i));
            avl.insertIter(tenThousandElements.get(i));
        }

        System.out.println(iterative.root.item);
        System.out.println(avl.root.item);

    }
}