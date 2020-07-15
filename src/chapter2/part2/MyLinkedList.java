package chapter2.part2;


import org.w3c.dom.Node;

/**
 * Created by Tang on 2020/7/15
 */
public class MyLinkedList {
    //头结点指针
    private Node head;
    //尾节点指针
    private Node last;
    //链表的实际长度
    private int size;

    public void insert(int data, int index) throws Exception {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围!");
        }
        //Node insertedNode = new Node(data);
        if (size == 0) {
            //空链表
        } else if (index == 0) {
            //插入头部
        } else if (size == index) {
            //插入尾部
        } else {
            //插入中间
        }
    }
}
