package lsy;


import java.util.HashMap;
import java.util.Map;

/**
 * @author lsy
 */
public class LRU {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }

    private int capacity;
    private Map<Integer,Node> map;
    private Node first;
    private Node last;

    public LRU(int capacity) {
        this.capacity=capacity;
        map= new HashMap<Integer, Node>();
    }

    public int get(int key){
        /**
         * 若没有返回-1
         */
        if(null == map.get(key))    return -1;
        /**
         * 将获取的节点移动到头节点
         */
        moveToHead(map.get(key));
        return map.get(key).val;
    }

    public void moveToHead(Node node){
        if(node==first){
            return;
        }else if(node==last){
            node.prev.next=null;
            last=node.prev;
        }else {
            if(first==null && last==null) {
                first = node;
                last = node;
                return;
            }
        }
        node.next = first;
        first.prev = node;
        first = node;
        /*if(node.prev==null){
            first = node;
            last = node;
            node.prev = null;
            node.next = null;
        }else if(node.next==null) {
            node.next = first;
            node.prev.next = null;
            first.prev = node;
        }else {
            node.prev.next = node.next.prev;
            node.next = first;
            node.prev = null;
        }*/
    }

    public void add(int key,int value){
        Node node = map.get(key);
        if(node==null){
            if(map.size()==capacity) removeLast();
            node = new Node();
            node.val=value;
            node.key=key;
            /**
             * 判断node数量是否超过最大值 capacity
             */
            map.put(key,node);
            /**
             * 将新添加的节点放到头节点
             */
            moveToHead(node);
        }else {
            node.val=value;
            moveToHead(node);
        }
    }

    /**
     * 删除最后一个node
     */
    public void removeLast(){
        map.remove(last.key);
        Node prev = last.prev;
        if(prev!=null){
            prev.next=null;
            last=prev;
        }
    }


    public static void main(String[] args) {
        LRU lru = new LRU(10);
        for (int i = 0; i < 14; i++) {
            lru.add(i,i);
        }
        System.out.println(lru.first.val);
        System.out.println(lru.last.val);
    }
}
