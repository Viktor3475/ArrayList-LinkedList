import javax.xml.transform.stream.StreamSource;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class CustomDynList{
    private class Node{
        Object element;
        Node next;

        Node(Object element,Node prevNode){
            this.element=element;
            prevNode.next=this;
        }

        Node(Object element){
            this.element=element;
            next = null;
        }
    }

    private Node head,tail;
    private int count;

    public CustomDynList(){
        head = tail = null;
        count = 0;
    }

    public void add(Object item){
        if(head==null){
            head = new Node(item);
            tail = head;
        }
        else{
            Node newNode = new Node(item,tail);
            tail = newNode;
        }
        count++;
    }

    public int indexOf(Object item){
        Node currentNode = head;
        int currentIndex = 0;

        while(currentNode!=null){
            if((currentNode.element!=null&&currentNode.element.equals(item))
            ||(currentNode.element==null)&&(item==null)) return currentIndex;

            currentNode = currentNode.next;
            currentIndex++;
        }
        return -1;
    }

    public boolean contains(Object item){
        int index = indexOf(item);
        boolean found = (index!=-1);
        return found;
    }

    public Object elementAt(int index){
        if(index>=count||index<0){
            throw new IndexOutOfBoundsException("Invalid index: "+index);
        }
        Node current = head;

        for(int i =0;i<index;i++)
            current=current.next;

        return current.element;
    }

    public int getLength(){
        return count;
    }

    public Object remove(int index){
        if(index>=count||index<0){
            throw new IndexOutOfBoundsException("Invalid index: "+index);
        }
        Node currentNode=head;
        Node prevNode = null;
        int currentIndex = 0;
        while(currentIndex<index){
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }
        count--;
        if(count==0) head = tail = null;
        else if(prevNode == null) head = currentNode.next;
        else prevNode.next = currentNode.next;
        return currentNode.element;
    }

    public int remove(Object item){
        Node currentNode = head;
        Node prevNode = null;
        int currentIndex = 0;

        while (currentNode!=null){
            if((currentNode.element!=null&&currentNode.element.equals(item))
            ||(currentNode.element==null)&&(item==null)) break;

            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }

        if(currentNode!=null){
            count--;
            if(count==0) head = tail = null;
            else if(prevNode==null) head = currentNode.next;
            else prevNode.next = currentNode.next;
            return currentIndex;
        }
        else return -1;
    }

}


class CustomArrList{
    private Object[] arr;
    private int count;
    private static final int INITIAL_CAPACITY=10;

    public CustomArrList(){
        arr = new Object[INITIAL_CAPACITY];
        count = 0;
    }

    public void clear(){
        arr = new Object[0];
        count = 0;
    }

    public void add(Object item){

        add(item,count);

    }

    public void add(Object item,int index){
        if(index>count||index<0){
            throw new IndexOutOfBoundsException("Invalid index: "+index);

        }
        Object[] extendedArr = arr;
        if(count+1==arr.length){
            extendedArr = new Object[arr.length*2];
        }
        System.arraycopy(arr,0,extendedArr,0,index);
        count++;
        System.arraycopy(arr,index,extendedArr,index+1,count-index-1);
        extendedArr[index] = item;
        arr = extendedArr;
    }

    public Object elementAt(int index){
        if(index>=count||index<0){
            throw new IndexOutOfBoundsException("Invalid index: "+index);
        }
        return arr[index];
    }

    public int indexOf(Object item){
        if(item==null){
            for (int i = 0;i<arr.length;i++){
                if(arr[i] == null) return i;
            }
        }
        else {
            for (int i = 0;i<arr.length;i++){
                if(item.equals(arr[i])) return i;
            }
        }
        return -1;
    }

    public Object remove(int index){
        if(index>=count||index<0){
            throw new IndexOutOfBoundsException("Invalid index: "+index);
        }
        Object item = arr[index];
        System.arraycopy(arr,index+1,arr,index,count-index+1);
        arr[count-1]=null;
        count--;
        return item;
    }

    public int remove(Object item){
        int index = indexOf(item);
        if(index==-1) return index;
        System.arraycopy(arr,index+1,arr,index,count-index+1);
        count--;
        return index;

    }

    public boolean contains(Object item){
        int index = indexOf(item);
        boolean found = (index!=-1);
        return found;
    }

    public int getLength(){
        return count;
    }

}

class NodeTree{
    NodeTree left,right;
    int data;
    public NodeTree(int data){
        this.data = data;
    }

    public void insert(int value){
        if(value<data){
            if(left==null) left = new NodeTree(value);
            else left.insert(value);
        }
        else{
            if(right==null) right = new NodeTree(value);
            else right.insert(value);
        }
    }

    public void printPreOrder(){
        System.out.print(data + " ");
        if(left!=null) left.printPreOrder();
        if(right!=null) right.printPreOrder();
    }

    public void printInOrder(){
        if(left!=null) left.printInOrder();
        System.out.print(data + " ");
        if(right!=null) right.printInOrder();
    }
    public void printPostOrder(){
        if(left!=null) left.printPostOrder();
        if(right!=null) right.printPostOrder();
        System.out.print(data + " ");
    }
    public void printLevelOrder(){
        Queue<NodeTree> q = new LinkedList<>();
        q.add(this);
        while (q.size()>0){
            NodeTree head = q.remove();
            System.out.print(head.data + " ");
            if(head.left!=null) q.add(head.left);
            if(head.right!=null) q.add(head.right);
        }
    }
}

public class Main {

    static int N = 3;

    // Returns true if mat[][] is magic
    // square, else returns false.
    static boolean isMagicSquare(int mat[][]) {

       int sumD1 = 0,sumD2=0;
       for(int i = 0;i<N;i++){
           sumD1 +=mat[i][i];
           sumD2 +=mat[i][N-i-1];
       }

       if(sumD1!=sumD2) return false;
       for(int i  = 0;i<N;i++){
           int rowSum = 0,colSum=0;
           for(int j = 0;j<N;j++){
               rowSum+=mat[i][j];
               colSum+=mat[j][i];
           }
           if(rowSum!=colSum||colSum!=sumD1) return false;
       }
       return true;
    }

    // driver program to
    // test above function
    public static void main(String[] args) {
        int mat[][] = {{2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}};

        if (isMagicSquare(mat))
            System.out.println("Magic Square");
        else
            System.out.println("Not a magic" +
                    " Square");
    }
}


