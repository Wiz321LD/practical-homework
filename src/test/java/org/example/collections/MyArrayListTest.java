package org.example.collections;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MyArrayListTest {

    private static final MyArrayList<Integer> myArrayList = new MyArrayList<>();

    @BeforeEach
    public void listSetUp() {
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
    }

    @Test
    public void addElementsAndCheckSize(){
        assertEquals(3, myArrayList.size());
    }

    @Test
    public void addAllElements(){
        MyArrayList<Integer> list = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>();
        list.add(7);
        list.add(7);
        list2.add(3);
        list.addAll(list2);
        assertEquals(3, list.size());
    }

    @Test
    public void clearElements(){
        myArrayList.clear();
        assertEquals(0, myArrayList.size());
    }

    @Test
    public void getElement(){
        assertEquals(3, myArrayList.get(2));
    }

    @Test
    public void checkThatEmpty(){
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    public void removeElement(){
        myArrayList.remove(0);
        assertEquals(2, myArrayList.size());
    }

    @Test
    public void sortList(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.sort(Integer::compareTo);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, list.toArray());
    }

    @AfterEach
    public void clearList(){
        myArrayList.clear();
    }

}