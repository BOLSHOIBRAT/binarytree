package org.itstep;

public class BinaryTree {
    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        boolean isRed;
        Node parent; // предыдущий

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
            this.isRed = true;
        }

        @Override
        public String toString() {
            return "(" +
                    "Число: " + key +
                    ", value=" + value +
                    ", Лево: " + left +
                    ", Право: " + right +
                    ", Цвет: " + isRed +
                    ')';
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    private Node getGrandfather(Node n) {
        if (n.parent != null) {
            return n.parent.parent;
        } else {
            return null;
        }
    }

    private Node getUncle(Node n) {
        Node g = n.parent.parent;
        if (g.left == n.parent) {
            return g.right;
        } else {
            return g.left;
        }
    }

    public void add(int key, Object value) {
        // первый элемент?
        Node n;
        if (root == null) {
            root = new Node(key, value);
            n = root;
        } else { // это не первый элемент и нужно найти место вставки
            Node found = findNode(root, key);
            n = new Node(key, value);
            if (found == null) return;
            if (key > found.key) {
                found.right = n;
                n.parent = found;
            } else if (key < found.key) {
                found.left = n;
                n.parent = found;
            } else {
                found.value = value;
                n = found;
            }
        }
        case1(n);
    }

    private void case1(Node n) {
        if (n == root) //n.parent==null
        {
            n.isRed = false;
        } else {
            case2(n);
        }
    }

    private void case2(Node n) {
        if (n.parent != null && n.parent.isRed) {
            case3(n);
        }
    }

    private void case3(Node n) {
        Node u = getUncle(n);
        if (u != null && u.isRed) {
            u.parent.isRed = true;
            u.isRed = false;
            n.parent.isRed = false;
            case1(u.parent);
        } else {
            case4(n);
        }
    }

    private void case4(Node n) {
        Node g = getGrandfather(n);
        if (g.left == n.parent && n.parent.right == n) { //А
            Node seve = n.left;
            Node p = n.parent;

            g.left = n;
            n.parent = g;

            n.left = p;
            p.parent = n;

            p.right = seve;
            if (seve != null) {
                seve.parent = p;
            }
            n = n.left;
        }
        if (g.right == n.parent && n.parent.left == n) {//B
            Node seve = n.right;
            Node p = n.parent;

            g.right = n;
            n.parent = g;

            n.right = p;
            p.parent = n;

            p.left = seve;
            if (seve != null) {
                seve.parent = p;
            }
            n = n.right;
        }
        case5(n);
    }

    private void case5(Node n) {
        Node g = getGrandfather(n);
        if (n.parent.left == n && g.left == n.parent) {//А
            Node gg = g.parent;
            Node p = n.parent;
            n.parent.isRed = false;
            g.isRed = true;

            g.left = p.right;
            if (p.right != null) {
                p.right.parent = g;
            }
            if (gg != null) {
                gg.left = p;
                p.parent = gg;
            } else {
                root = p;
            }
            p.right=g;
            g.parent=p;
        }
        if (n.parent.right == n && g.right == n.parent) {//B
            Node gg = g.parent;
            Node p = n.parent;
            n.parent.isRed = false;
            g.isRed = true;

            g.right = p.left;
            if (p.left != null) {
                p.left.parent = g;
            }
            if (gg != null) {
                gg.right = p;
                p.parent = gg;
            } else {
                root = p;
            }
            p.left=g;
            g.parent=p;
        }
    }

    private Node findNode(Node cur, int key) {
        if (key > cur.key) { // добавляем в лево
            if (cur.right == null) {
                return cur;
            } else {
                return findNode(cur.right, key);
            }
        } else if (key < cur.key) {
            if (cur.left == null) {
                return cur;
            } else {
                return findNode(cur.left, key);
            }
        } else {
            return cur;
        }
    }

}
