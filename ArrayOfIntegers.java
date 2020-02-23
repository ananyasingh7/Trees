import java.util.*;
import java.lang.Math;

public class ArrayOfIntegers{


	public static ArrayList<Integer> getRandomArray(int n){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Random random = new Random();
		int counter = 0;
		while(n > counter){
			boolean inArray = false;
			int number = random.nextInt();
			for(int i = 0; i<arr.size(); i++){
				if(arr.get(i) == number){
					inArray = true;
				}
			}
			if(!inArray){
				arr.add(number);
				counter++;
			}
		}
		for(int i = 0; i<arr.size(); i++){
			System.out.println(arr.get(i));
		}
		return arr;
	}

	//public ArrayList<Integer> getSortedArray(int n){
	//	
	//}

	public static void main(String[] args){
		System.out.println(getRandomArray(10));
	}
}