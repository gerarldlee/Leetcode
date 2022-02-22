package com.leetcode.linkedlist;

public interface IFLinkList {

    void addAtHead(int val);

    void addAtTail(int val);

    void addAtIndex(int index, int val);

    void deleteAtIndex(int index);

    int get(int index);

}
