import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;
    Node(int d) {
        data = d;
        left = null;
        right = null;
    }
}

class Binarytreeh {
    Node root;

    Binarytreeh() {
        Scanner sc = new Scanner(System.in);
        root = createTree(sc);
    }

    Node createTree(Scanner sc) {
        int data = sc.nextInt();
        // base case
        if (data == -1) {
            return null;
        }
        // recursive case
        Node n = new Node(data);
        n.left = createTree(sc);
        n.right = createTree(sc);
        return n;
    }

    void display() {
        preorderPrint(root);
    }

    void preorderPrint(Node root) {
        // base case
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preorderPrint(root.left);
        preorderPrint(root.right);
    }

    boolean search(int key) {
        return searchNode(root, key);
    }

    boolean searchNode(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        return searchNode(root.left, key) || searchNode(root.right, key);
    }

    void delete(int key) {
        root = deleteNode(root, key);
    }

    Node deleteNode(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (root.data == key) {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteNode(root.right, root.data);
        } else {
            root.left = deleteNode(root.left, key);
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
}

class Binarytree {
    public static void main(String[] args) {
        Binarytreeh t = new Binarytreeh();
        t.display();
        // Test search method
        System.out.println("Search for 3: " + t.search(3));
        System.out.println("Search for 10: " + t.search(10));
        // Test delete method
        t.delete(3);
        t.display();
    }
}
