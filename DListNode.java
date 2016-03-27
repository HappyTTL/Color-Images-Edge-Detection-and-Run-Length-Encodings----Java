/* DLNodes.java */

/**
* Construct a DoubleLinkedListNode holds a run object


*/
public class DListNode{
    public BasicRunItem runItem;
    public DListNode prev;
    public DListNode next;
    
    DListNode() {
        runItem = null;
        prev = null;
        next = null;
    }
    
    DListNode(int red, int green, int blue, int runLengths) {
        runItem = new BasicRunItem(red, green, blue, runLengths);
        prev = null;
        next = null;
    }

}