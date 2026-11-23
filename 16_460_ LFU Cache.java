class LFUCache {
    private static class Node {
        int key;
        int value;
        int freq;

        Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyToNode;
    private final Map<Integer, LinkedHashSet<Node>> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToNode = new HashMap<>();
        this.freqToList = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyToNode.get(key);
        if (node == null) {
            return -1;
        }
        increaseFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;
            increaseFreq(node);
            return;
        }

        if (keyToNode.size() >= capacity) {
            evictLeastFrequent();
        }

        Node newNode = new Node(key, value, 1);
        keyToNode.put(key, newNode);
        freqToList.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(newNode);
        minFreq = 1;
    }

    private void increaseFreq(Node node) {
        int oldFreq = node.freq;
        LinkedHashSet<Node> oldList = freqToList.get(oldFreq);
        oldList.remove(node);

        if (oldList.isEmpty()) {
            freqToList.remove(oldFreq);
            if (minFreq == oldFreq) {
                minFreq++;
            }
        }

        node.freq++;
        freqToList.computeIfAbsent(node.freq, k -> new LinkedHashSet<>()).add(node);
    }

    private void evictLeastFrequent() {
        LinkedHashSet<Node> minList = freqToList.get(minFreq);
        Node oldestNode = minList.iterator().next();
        minList.remove(oldestNode);

        if (minList.isEmpty()) {
            freqToList.remove(minFreq);
        }

        keyToNode.remove(oldestNode.key);
    }
}
