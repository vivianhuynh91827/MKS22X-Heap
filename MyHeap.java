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
    if (right >= size && left > size) return;
    //if current only has one child
    if (right >= size) {
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

  }

  public static void heapsort(int[] data) {

  }

  public static void main(String[] args) {
    int[] test = {3, 5, 9, 6, 7, 7, 5};
    pushUp(test, 4);
    // pushDown(test, 7, 0);
    System.out.println(Arrays.toString(test));
  }
}
