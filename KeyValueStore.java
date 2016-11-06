// 86. Key Value Store
/*
keyValueStore 有四个method，add(key, value), remove(key), get(key), lastestKey() implement keyValueStore
前三个就和hashmap一样，最后一个返回最近访问的key
*/
class KeyValueStore {
    private HashMap<Integer, DoubleLinkList> keyToNode;
    private DoubleLinkList head;
    private DoubleLinkList tail;
    public KeyValueStore() {
        this.keyToNode = new HashMap<>();
        this.head = new DoubleLinkList(0, 0);
        this.tail = new DoubleLinkList(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void add(int key, int value) {
        if (keyToNode.containsKey(key)) {
            DoubleLinkList node = keyToNode.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }
        else {
            DoubleLinkList node = new DoubleLinkList(key, value);
            keyToNode.put(key, node);
            addToHead(node);
        }
    }

    public boolean remove(int key) {
        if (!keyToNode.containsKey(key)) {
            return false;
        }
        DoubleLinkList node = keyToNode.get(key);
        keyToNode.remove(key);
        deleteNode(node);
        return true;
    }

    public Integer get(int key) {
        if (!keyToNode.containsKey(key)) {
            return null;
        }
        DoubleLinkList node = keyToNode.get(key);
        deleteNode(node);
        addToHead(node);
        return node.value;
    }

    public Integer latestKey() {
        if (head.next != tail) {
            return head.next.value;
        }
        return null;
    }

    private void addToHead(DoubleLinkList node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void deleteNode(DoubleLinkList node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }

    class DoubleLinkList {
        public int key;
        public int value;
        public DoubleLinkList next;
        public DoubleLinkList prev;
        public DoubleLinkList(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}
