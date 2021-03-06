package com.stephenramthun.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a single-linked list data structure.
 *
 * @author Stephen Ramthun
 */

public class LinkedList<T extends Comparable> implements Iterable<T> {

    Node head;
    Node tail;
    int size;

    /**
     * Initializes the LinkedList with no items.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Initializes the LinkedList with one item.
     *
     * @param item  Item to insert into the new list.
     */
    public LinkedList(T item) {
        head = new Node(item);
        tail = head;
        size = 1;
    }

    /**
     * Returns the number of items in the list.
     *
     * @return  The number of items in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     * @return  True if the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an item to the end of the list.
     *
     * @param item  Item to add to the end of the list.
     */
    public void add(T item) {
        if (size == 0) {
            head = new Node(item);
            tail = head;
        } else {
            tail.next = new Node(item);
            tail = tail.next;
        }

        size++;
    }

    /**
     * Adds an item to the beginning of the list.
     *
     * @param item  Item to add to the beginning of the list.
     */
    public void addFirst(T item) {
        if (size == 0) {
            head = new Node(item);
            tail = head;
        } else {
            Node node = new Node(item);
            node.next = head;
            head = node;
        }

        size++;
    }

    /**
     * Adds an item to the end of the list.
     *
     * @param item  Item to add to the end of the list.
     */
    public void addLast(T item) {
        add(item);
    }

    /**
     * Returns the item at the head of the list.
     *
     * @return  The item at the head of the list.
     */
    public T getFirst() {
        if (head == null) {
            return null;
        }

        return head.value;
    }

    /**
     * Removes and returns the item at the head of the list.
     *
     * @return  The item removed from the head of the list.
     */
    public T removeFirst() {
        if (head == null) {
            return null;
        }

        T value = head.value;
        head = head.next;
        size--;

        if (size == 0) {
            head = null;
        }

        return value;
    }

    /**
     * Returns the item at the tail of the list.
     *
     * @return  The item at the tail of the list.
     */
    public T getLast() {
        if (tail == null) {
            return null;
        }

        return tail.value;
    }

    /**
     * Removes and returns the item at the tail of the list.
     *
     * @return  The item removed from the tail of the list.
     */
    public T removeLast() {
        if (tail == null || head == null) {
            return null;
        }

        if (size == 1) {
            tail = null;
            return removeFirst();
        }

        T value = tail.value;
        Node current = head;

        while (current.next != tail) {
            current = current.next;
        }

        tail = current;
        size--;

        if (size <= 1) {
            head = tail;
        }

        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    class ListIterator implements Iterator<T> {
        Node cursor;

        public ListIterator() {
            this.cursor = LinkedList.this.head;
        }

        public boolean hasNext() {
            return this.cursor != null;
        }

        public T next() {
            if (cursor != null) {
                T value = cursor.value;
                cursor = cursor.next;
                return value;
            }
            throw new NoSuchElementException();
        }
    }

    class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            next = null;
        }
    }
}
