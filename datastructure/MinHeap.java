package datastructure;

import java.util.Arrays;

public class MinHeap {
    private int capacity = 10;
    private int size = 0;
    private int[] items = new int[capacity];

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return items[0];
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int item = items[0];

        items[0] = items[size - 1];
        size--;
        heapifyDown();

        return item;
    }

    public void offer(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && getParent(index) > items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) {
                break;
            } else {
                swap(smallerChildIndex, index);
                index = smallerChildIndex;
            }
        }
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity = capacity * 2;
        }
    }

    private void swap(int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    private int getLeftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int getRightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private int getParent(int index) {
        return items[getParentIndex(index)];
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getParentIndex(int index) {
        return index <= 0 ? -1 : (index - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }


    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.offer(10);
        minHeap.offer(5);
        minHeap.offer(20);
        minHeap.offer(2);
        minHeap.offer(3);
        minHeap.offer(6);

        System.out.println("Min value = " + minHeap.peek());
        System.out.println("Remove min value = " + minHeap.poll());
        System.out.println("Min value = " + minHeap.peek());
    }
}
