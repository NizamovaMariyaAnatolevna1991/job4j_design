package ru.job4j.newcoll.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        int count = 0;

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            count++;

            for (Node<T> child : current.getChildren()) {
                queue.push(child);
            }
        }

        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        List<T> result = new ArrayList<>();

        Queue<Node<T>> queue = new SimpleQueue<>();

        queue.push(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.getValue());

            for (Node<T> child : current.getChildren()) {
                queue.push(child);
            }
        }

        return result;
    }
}
