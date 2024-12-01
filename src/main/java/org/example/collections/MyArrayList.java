package org.example.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<E> implements Collection<E> {

    //==================================== Fields ===========================================

    private static final int INITIAL_CAPACITY = 10;

    private static final Object[] DEFAULT_EMPTY_ELEMENT_ARRAY = {};

    private Object[] elementArray;

    private int size;


    //==================================== Constructors ===========================================

    public MyArrayList() {
        this.elementArray = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int initialCapacity) {
        this.elementArray = new Object[initialCapacity];
        this.size = 0;
    }


    //==================================== Methods ===========================================

    /**
     * This method add one element to this collection.
     *
     * @param e element The element that needed to add.
     * @return True if addition was successfully.
     */
    @Override
    public boolean add(E e) {
        checkRangeOfArraySize();
        this.elementArray[size] = e;
        this.size++;
        return true;
    }

    /**
     * This method add element to this collection by the index.
     *
     * @param index The index of this collection.
     * @param element The element that needed to add.
     * @return True if addition was successfully.
     */
    public boolean add(int index, E element){
        checkRangeOfIndex(index);
        checkRangeOfArraySize();
        this.elementArray[index] = element;
        this.size++;
        return true;
    }

    private void checkRangeOfIndex(int index) {
        if (index < 0 || index >= this.elementArray.length){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkRangeOfArraySize() {
        if (this.size >= this.elementArray.length) {
            this.elementArray = ensureArrayCapacity(this.elementArray.length * 2);
        }
    }

    private Object[] ensureArrayCapacity(int newCapacity) {
        return this.elementArray = Arrays.copyOf(this.elementArray, newCapacity + 1);
    }

    /**
     * This method adds all elements from collection to its own elementArray.
     *
     * @param collection Collection containing elements to be added to this collection
     * @return True if addition was successfully.
     */
    public boolean addAll(Collection<? extends E> collection){
        Object[] collectionArray = collection.toArray();
        int s1 = this.size, s2 = collection.size();
        if (collectionArray.length > 0){
            if ((collectionArray.length + this.elementArray.length) > this.elementArray.length){
                ensureArrayCapacity(this.elementArray.length + collectionArray.length + 1);
            }
            int indexOfStarting = this.size;
            for (int i = 0; i < collectionArray.length; i++){
                this.elementArray[indexOfStarting++] = collectionArray[i];
            }
            this.size = s1 + s2;

        }
        return true;
    }

    private void mergeCollections(Object[] collectionArray, int lastOccupiedIndex) {
        int indexOfStarting = lastOccupiedIndex;
        for (int i = 0; i < collectionArray.length; i++){
            this.elementArray[indexOfStarting++] = collectionArray[i];
        }
    }

    /**
     * This method clears all elements of this collection.
     */
    public void clear(){
        if (this.size > 0){
            for (int i = 0; i < this.size; i++){
                this.elementArray[i] = null;
            }
            this.size = 0;
        }
    }

    /**
     * This method returns an element from this collection by its index.
     *
     * @param index The index where the element lies.
     * @return E its element which returns by the index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index){return (E)this.elementArray[index];}

    /**
     * This method checks size of created collection.
     *
     * @return If there are no elements than it returns true.
     */
    public boolean isEmpty(){return this.size == 0;}

    /**
     * This method remove one element from this collection by the index.
     *
     * @param index The index where the element lies.
     * @return True if removing was successfully.
     */
    public boolean remove(int index){
        if (this.size > 0){
            checkRangeOfIndex(index);
            this.elementArray[index] = null;
            this.size--;
            rebuildArray();
        }
        return true;
    }

    private void rebuildArray() {
        if (this.size > 0){
            Object[] newArray = new Object[this.elementArray.length];
            int newArrCount = 0;
            for (int i = 0; i < this.elementArray.length; i++){
                if (this.elementArray[i] != null){
                    newArray[newArrCount] = this.elementArray[i];
                    newArrCount++;
                }
            }
            this.elementArray = newArray;
            trimToSize();
        }
    }

    private void trimToSize() {
        if (this.size < this.elementArray.length){
            this.elementArray = (this.size == 0)
                    ? DEFAULT_EMPTY_ELEMENT_ARRAY
                    : Arrays.copyOf(this.elementArray, this.size);
        }
    }

    /**
     * This method remove one element from this collection.
     *
     * @param object Element to be removed from this collection, if present
     * @return True if removing was successfully.
     */
    public boolean remove(Object object){
        if (this.size > 0 && object != null){
            for (int i = 0; i < this.size; i++){
                if (this.elementArray[i].equals(object)){
                    this.elementArray[i] = null;
                    this.size--;
                    rebuildArray();
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Sort collection by Merge sort.
     *
     * @param comparator Comparator that specifies how to sort this collection.
     */
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> comparator){
        mergeSort((E[]) this.elementArray, comparator);
    }

    @SuppressWarnings("unchecked")
    private <E> void mergeSort(E[] array, Comparator<? super E> comparator){

        if (array == null){
            throw new IllegalArgumentException("Value is null");
        }

        if (array.length < 2){
            return;
        }

        int median = array.length/2;
        int leftSubArraySize = median;
        int rightSubArraySize = array.length - median;

        E[] leftSubArray = (E[])new Object[leftSubArraySize];
        E[] rightSubArray = (E[])new Object[rightSubArraySize];

        for (int i = 0; i < leftSubArraySize; i++){
            leftSubArray[i] = array[i];
        }

        for (int i = 0; i < rightSubArraySize; i++){
            rightSubArray[i] = array[median + i];
        }

        mergeSort(leftSubArray, comparator);
        mergeSort(rightSubArray, comparator);

        merge(array, leftSubArray, rightSubArray, comparator);

    }

    private <E> void merge(E[] array, E[] leftSubArray, E[] rightSubArray, Comparator<? super E> comparator){

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int sortedArrayIndex = 0;

        while (leftArrayIndex < leftSubArray.length && rightArrayIndex < rightSubArray.length){
            int comparisonInt = comparator.compare(leftSubArray[leftArrayIndex], rightSubArray[rightArrayIndex]);

            if (comparisonInt <= 0){
                array[sortedArrayIndex] = leftSubArray[leftArrayIndex];
                leftArrayIndex++;
            } else {
                array[sortedArrayIndex] = rightSubArray[rightArrayIndex];
                rightArrayIndex++;
            }
            sortedArrayIndex++;
        }

        while (leftArrayIndex < leftSubArray.length){
            array[sortedArrayIndex] = leftSubArray[leftArrayIndex];
            leftArrayIndex++;
            sortedArrayIndex++;
        }

        while (rightArrayIndex < rightSubArray.length){
            array[sortedArrayIndex] = rightSubArray[rightArrayIndex];
            rightArrayIndex++;
            sortedArrayIndex++;
        }

    }

    /**
     * This method return size of this collection.
     *
     * @return size The size of the collection (the number of elements it contains).
     */
    public int size(){return this.size;}

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elementArray=" + Arrays.toString(elementArray) +
                '}';
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        return (T[]) Arrays.copyOf(this.elementArray, this.size, array.getClass());
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementArray, this.size);
    }


    ///////////////////////////////////////////////////////////////////////////////////

    //TODO: LATER
    @Override
    public boolean contains(Object o) {
        return false;
    }

    //TODO: LATER
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    //TODO: LATER
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    //TODO: LATER
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    //TODO: LATER
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

}
