import java.util.*;

class AllOne {
    private class Node {
        int count;
        Set<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Map<String, Node> keyCountMap;
    private Node head, tail;

    public AllOne() {
        keyCountMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (keyCountMap.containsKey(key)) {
            Node currNode = keyCountMap.get(key);
            int nextCount = currNode.count + 1;
            Node nextNode = currNode.next;

            if (nextNode == tail || nextNode.count != nextCount) {
                nextNode = insertNodeAfter(currNode, nextCount);
            }

            nextNode.keys.add(key);
            keyCountMap.put(key, nextNode);

            currNode.keys.remove(key);
            if (currNode.keys.isEmpty()) {
                removeNode(currNode);
            }
        } else {
            Node firstNode = head.next;
            if (firstNode == tail || firstNode.count != 1) {
                firstNode = insertNodeAfter(head, 1);
            }
            firstNode.keys.add(key);
            keyCountMap.put(key, firstNode);
        }
    }

    public void dec(String key) {
        if (!keyCountMap.containsKey(key)) {
            return;
        }

        Node currNode = keyCountMap.get(key);
        int prevCount = currNode.count - 1;

        currNode.keys.remove(key);

        if (prevCount == 0) {
            keyCountMap.remove(key);
        } else {
            Node prevNode = currNode.prev;
            if (prevNode == head || prevNode.count != prevCount) {
                prevNode = insertNodeBefore(currNode, prevCount);
            }
            prevNode.keys.add(key);
            keyCountMap.put(key, prevNode);
        }

        if (currNode.keys.isEmpty()) {
            removeNode(currNode);
        }
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    private Node insertNodeAfter(Node target, int count) {
        Node newNode = new Node(count);
        newNode.next = target.next;
        newNode.prev = target;
        target.next.prev = newNode;
        target.next = newNode;
        return newNode;
    }

    private Node insertNodeBefore(Node target, int count) {
        Node newNode = new Node(count);
        newNode.next = target;
        newNode.prev = target.prev;
        target.prev.next = newNode;
        target.prev = newNode;
        return newNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
