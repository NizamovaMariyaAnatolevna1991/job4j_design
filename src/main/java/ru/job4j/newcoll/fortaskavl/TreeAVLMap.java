package ru.job4j.newcoll.fortaskavl;

import ru.job4j.collection.binarytree.AvlTree;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean insert(T key, V value) {
        if (key == null) {
            return false;
        }
        root = insert(root, key, value);
        return true;
    }

    private Node insert(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = insert(node.left, key, value);
            } else if (comparisonResult > 0) {
                node.right = insert(node.right, key, value);
            } else {
                node.value = value;
                return node;
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public V get(T key) {
        if (key == null) {
            return null;
        }
        Node node = find(root, key);
        return (node != null) ? node.value : null;
    }

    public boolean contains(T value) {
        if (value == null) {
            return false;
        }
        return find(root, value) != null;
    }

    private Node find(Node node, T key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    public Set<T> keySet() {
        Set<T> keys = new TreeSet<>();
        return collectKeys(root, keys);
    }

    private Set<T> collectKeys(Node localRoot, Set<T> keys) {
        if (localRoot != null) {
            collectKeys(localRoot.left, keys);
            keys.add(localRoot.key);
            collectKeys(localRoot.right, keys);
        }
        return keys;
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        return collectValues(root, values);
    }

    private List<V> collectValues(Node localRoot, List<V> values) {
        if (localRoot != null) {
            collectValues(localRoot.left, values);
            values.add(localRoot.value);
            collectValues(localRoot.right, values);
        }
        return values;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
