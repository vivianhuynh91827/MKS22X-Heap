import java.io.*;
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

  }

  public static void heapify(int[] data) {

  }

  public static void heapsort(int[] data) {

  }
}
