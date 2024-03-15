//import java.util.ArrayList;
//import java.util.List;
//
//class Person {
//    String name;
//    int studentNumber;
//
//    Person(String name, int studentNumber) {
//        this.name = name;
//        this.studentNumber = studentNumber;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", studentNumber=" + studentNumber +
//                '}';
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        int m = 10; // 总人数
//        int k = 3; // 报数的数字
//        List<Person> people = new ArrayList<>();
//        for (int i = 1; i <= m; i++) {
//            people.add(new Person("Name" + i, 100 + i)); // 假设学号从101开始
//        }
//        Person lastMan = josephus2(people, k);
//        System.out.println("最后存活的人是: " + lastMan);
//    }
//
//    public static Person josephus2(List<Person> people, int k) {
//        int index = 0; // 当前位置索引
//        int count = 0; // 报数计数器
//        while (people.size() > 1) { // 当人数大于1时继续循环
//            if (++count == k) { // 报数到k
//                count = 0; // 重置计数器
//                Person out = people.remove(index); // 移除出列的人
//                System.out.println("第" + out.studentNumber + "号" + out.name + "出列"); // 输出出列的人
//            }
//            index = (index + 1) % people.size(); // 更新当前位置索引，模拟环形结构
//        }
//        // 返回最后存活的人
//        return people.get(0);
//    }
//}

//import java.util.LinkedList;
//
//class Person {
//    String name;
//    int position; // 原始位置编号
//
//    Person(String name, int position) {
//        this.name = name;
//        this.position = position;
//    }
//
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", position=" + position +
//                '}';
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        int m = 10; // 总人数
//        int k = 3; // 报数的数字
//
//        // 创建链表并初始化
//        LinkedList<Person> people = new LinkedList<>();
//        for (int i = 1; i <= m; i++) {
//            people.add(new Person("Name" + i, i));
//        }
//
//        // 将链表首尾相连形成环
//        people.addLast(people.getFirst()); // 添加第一个元素到链表末尾，形成环
//
//        // 找到出列的位置并移除节点，直到链表中只剩下一个节点
//        while (people.size() > 1) {
//            // 报数，跳过k-1个节点
//            for (int i = 0; i < k - 1; i++) {
//                people.addLast(people.removeFirst()); // 将头节点移动到尾部
//            }
//            // 移除头节点（即第k个节点）
//            Person out = people.removeFirst();
//            System.out.println("第" + out.position + "号" + out.name + "出列");
//        }
//
//        // 链表中剩下的最后一个节点是最后存活的人
//        Person lastMan = people.getFirst();
//        System.out.println("最后存活的人是: " + lastMan);
//    }
//}
//
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    // 嵌套类Node
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }
    }

    // 插入方法
    public void insert(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = insertRec(root, data);
    }

    // 递归插入方法
    private Node<T> insertRec(Node<T> current, T data) {
        if (current == null) {
            return new Node<>(data);
        }

        if (data.compareTo(current.data) < 0) {
            current.left = insertRec(current.left, data);
        } else if (data.compareTo(current.data) > 0) {
            current.right = insertRec(current.right, data);
        } else {
            // 数据已存在，不插入
            return current;
        }

        return current;
    }


    // 中序遍历方法
    public void visit() {
        inOrderTraversal(root);
    }

    // 递归中序遍历方法
    private void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }



    // 搜索方法
    public boolean search(T data) {
        return searchRec(root, data);
    }

    // 递归搜索方法
    private boolean searchRec(Node<T> current, T data) {
        if (current == null) {
            return false;
        }

        if (data.compareTo(current.data) == 0) {
            return true;
        } else if (data.compareTo(current.data) < 0) {
            return searchRec(current.left, data);
        } else {
            return searchRec(current.right, data);
        }
    }




    // 主方法，用于测试
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // 插入数据
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // 遍历树
        System.out.println("Inorder traversal:");
        tree.visit();

        // 搜索数据
        System.out.println("\nSearching for 6: " + tree.search(6));
        System.out.println("Searching for 9: " + tree.search(9));
    }
}