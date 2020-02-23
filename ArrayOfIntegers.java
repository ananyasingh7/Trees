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
		return arr;
	}

	public static ArrayList<Integer> getSortedArray(int n){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int length = n;
		while(length != -1){
			arr.add(length);
			length--;
		}
		return arr;
	}

	public static void main(String[] args){
		System.out.println(getSortedArray(60));
	}
}