import java.io.*;
import java.util.*;
public class MyHeap {
  private static void pushDown(int[] data, int size, int index) {
    //left child index
    int left = (2*index) + 1;
     //right child index
    int right = (2*index) + 2;
    //current node value
    int current = data[index];
    //if current index is a leaf
    if (right >= size && left >= size - 1) return;
    //if current only has one child
    if (right == size && left == size - 1) {
      //if the child is less than current, end
      if (data[left] < current) return;
      // else, swap values and end
      else {
        data[index] = data[left];
        data[left] = current;
        return;
      }
    }
    //final possibility: node has 2 children
    //left child
    int l = data[left];
    //right child
    int r = data[right];
    //if both children are smaller than node, end
    if (current > l && current > r) return;
    //greater of the 2 children
    int max = Math.max(l, r);
    //node to be swapped
    int newIndex;
    if (max == l) newIndex = left;
    else newIndex = right;
    //swap value with larger child
    data[index] = data[newIndex];
    data[newIndex] = current;
    pushDown(data, size, newIndex);
  }

  private static void pushUp(int[] data, int index) {
    //if index is the root of the tree, end
    if (index == 0) return;
    //parent index
    int parent = (index-1)/2;
    int parentVal = data[parent];
    //current value
    int current = data[index];
    //is parent is greater than current node, end
    if (parent > current) return;
    //if parent is less than current, swap values
    data[index] = parentVal;
    data[parent] = current;
    pushUp(data, parent);
  }

  public static void heapify(int[] data) {
    if (data.length == 1) return;
    int rows = countRows(data);
    //the last element in the second to last row
    int currentNode = (int)Math.pow(2,rows) -2;
    // System.out.println(currentNode);
    while (currentNode >= 0) {
      pushDown(data, data.length, currentNode);
      currentNode--;
    }
  }

  //returns the total number of rows
  private static int countRows(int[] data) {
    int exp = 0;
    int nodes = 0;
    while (nodes < data.length) {
      nodes += Math.pow(2, exp);
      exp++;
    }
    return exp-1;
  }

  public static void heapsort(int[] data) {
    heapify(data);
    int indexLast = data.length-1;
    int size = data.length;
    while (size!=0) {
      pushDown(data, size, 0);
      int temp = data[0];
      data[0] = data[indexLast];
      data[indexLast] = temp;
      size--;
      indexLast--;
    }
    if (data[0] > data[1]) {
      int temp = data[0];
      data[0] = data[1];
      data[1] = temp;
    }
  }

  public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 1000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        MyHeap.heapsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
}
  // public static void main(String[] args) {
  //   int[] test = {20,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
  //   heapsort(test);
  //   System.out.println(Arrays.toString(test));
  // }
}
