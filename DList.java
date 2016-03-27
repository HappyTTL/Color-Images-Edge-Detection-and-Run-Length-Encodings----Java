/* DList.java */

/**
 *  A DList1 is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */

public class DList {
    
    /**
     *  head references the first node.
     *  tail references the last node.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */
    
    protected DListNode head;
    protected DListNode tail;
    protected long size;
    
    /* DList1 invariants:
     *  1)  head.prev == null.
     *  2)  tail.next == null.
     *  3)  For any DListNode1 x in a DList, if x.next == y and x.next != null,
     *      then y.prev == x.
     *  4)  For any DListNode1 x in a DList, if x.prev == y and x.prev != null,
     *      then y.next == x.
     *  5)  The tail can be accessed from the head by a sequence of "next"
     *      references.
     *  6)  size is the number of DListNode1s that can be accessed from the
     *      head by a sequence of "next" references.
     */
    
    /**
     *  DList1() constructor for an empty DList1.
     */
    public DList() {
        head = new DListNode();
        tail = new DListNode();
        size = 0;
    }
    
    public void insertEnd (int red, int green, int blue, int runLengths) {
        if (head.next == null && tail.prev == null && size == 0) {
            DListNode temp = new DListNode(red, green, blue, runLengths);
            head.next = temp;
            temp.next = tail;
            tail.prev = temp;
            temp.prev = head;
            size = 1;
        } else {
            DListNode temp = new DListNode(red, green, blue, runLengths);
            tail.prev.next = temp;
            temp.prev = tail.prev;
            temp.next = tail;
            tail.prev = temp;
            size++;
        }

    }
    public static void main(String[] args){
        DList DL = new DList();
        DL.insertEnd(3,3,3,3);
        DL.insertEnd(4,4,4,4);
        System.out.println(DL.size);
        System.out.println(DL.head.next.runItem.red);
    }
}
