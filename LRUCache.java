package lru;

import java.util.HashMap;
/**
 * Follows Doubly Linked List with Hashing Mechanism to design LRU
 * */
public class LRUCache {
    private int capacity;
    private HashMap<Integer,Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key){
        if(!cache.containsKey(key)){
            return -1;
        }

        Node node = cache.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    /**put data in cache*/
    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
        }
        Node node = new Node(key,value);
        cache.put(key,node);
        add(node);
        if(capacity<cache.size()) {
            Node lastNode=tail.prev;
            remove(lastNode);
            cache.remove(lastNode.key);
        }
    }

    /**Adding Node to DoublyLinkList - follow Insertion at middle approach as LRU Head and tail is a default nod and does not contain any value*/
    private void add(Node node) {
        Node nextNode=head.next;

        node.next=nextNode;
        node.prev=head;
        nextNode.prev=node;
        head.next=node;
    }

    /**Adding Node to DoublyLinkList - follow Deletion at middle approach as LRU Head and tail is a default nod and does not contain any value*/
    private void remove(Node node) {
        Node nextNode=node.next;
        Node prevNode=node.prev;
        prevNode.next=nextNode;
        nextNode.prev=prevNode;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(4));
        cache.put(4,4);
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.cache);
    }
}