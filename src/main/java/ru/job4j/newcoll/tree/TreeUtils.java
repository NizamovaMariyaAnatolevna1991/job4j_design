package ru.job4j.newcoll.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        if (findByKey(root, child).isPresent()) {
            return false;
        }

        Optional<Node<T>> parentOpt = findByKey(root, parent);
        if (parentOpt.isEmpty()) {
            return false;
        }

        Node<T> newChild = new Node<>(child);
        parentOpt.get().getChildren().add(newChild);
        return true;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            if (current.getValue().equals(key)) {
                return Optional.of(current);
            }

            List<Node<T>> children = current.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }

        SimpleStack<Node<T>> nodeStack = new SimpleStack<>();
        SimpleStack<Node<T>> parentStack = new SimpleStack<>();

        for (Node<T> child : root.getChildren()) {
            nodeStack.push(child);
            parentStack.push(root);
        }

        while (!nodeStack.isEmpty()) {
            Node<T> current = nodeStack.pop();
            Node<T> parent = parentStack.pop();

            if (current.getValue().equals(key)) {
                parent.getChildren().remove(current);
                return Optional.of(current);
            }


            for (Node<T> child : current.getChildren()) {
                nodeStack.push(child);
                parentStack.push(current);
            }
        }

        return Optional.empty();
    }
}
