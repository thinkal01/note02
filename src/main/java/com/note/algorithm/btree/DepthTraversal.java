package com.note.algorithm.btree;

import org.junit.Test;

import java.util.*;

class TreeNode<T> {
    T val;
    TreeNode<T> left = null;
    TreeNode<T> right = null;

    public TreeNode(T val) {
        this.val = val;
    }
}

public class DepthTraversal {
    /**
     * 广度优先遍历
     * 对每一层节点依次访问，访问完一层进入下一层，而且每个节点只能访问一次。
     *
     * @param root
     * @return
     */
    public <T> ArrayList<T> PrintFromTopToBottom(TreeNode<T> root) {
        ArrayList<T> lists = new ArrayList<>();
        if (root == null) return lists;
        // 先进先出
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> tree = queue.poll();
            // 先往队列中插左节点，再插右节点，这样出队就是先左节点后右节点了。
            if (tree.left != null) queue.offer(tree.left);
            if (tree.right != null) queue.offer(tree.right);
            lists.add(tree.val);
        }
        return lists;
    }

    /**
     * 深度优先遍历,从头部向底部遍历
     *
     * @param root
     * @return
     */
    public <T> ArrayList<T> PrintFromTopToBottom2(TreeNode<T> root) {
        ArrayList<T> lists = new ArrayList<>();
        if (root == null) return lists;
        // Stack<TreeNode<T>> stack = new Stack<>();
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<T> tree = stack.pop();
            //先往栈中压入右节点，再压左节点，这样出栈就是先左节点后右节点了。
            if (tree.right != null) stack.push(tree.right);
            if (tree.left != null) stack.push(tree.left);
            lists.add(tree.val);
        }
        return lists;
    }

    /**
     * 深度遍历递归实现
     *
     * @param root
     */
    public void depthOrderTraversalWithRecursive(TreeNode root) {
        depthTraversal2(root);
    }

    private void depthTraversal2(TreeNode tn) {
        if (tn != null) {
            System.out.print(tn.val + "  ");
            depthTraversal2(tn.left);
            depthTraversal2(tn.right);
        }
    }

    @Test
    public void test01() {
        /**
         *      a
         *    b   c
         *   d e f g
         *  h i
         */
        TreeNode root = new TreeNode("A");
        TreeNode<String> b = new TreeNode<>("B");
        TreeNode<String> c = new TreeNode<>("C");
        TreeNode<String> d = new TreeNode<>("D");
        TreeNode<String> e = new TreeNode<>("E");
        TreeNode<String> f = new TreeNode<>("F");
        TreeNode<String> g = new TreeNode<>("G");
        TreeNode<String> h = new TreeNode<>("H");
        TreeNode<String> i = new TreeNode<>("I");

        root.left = b;
        root.right = c;

        b.left = d;
        b.right = e;

        c.left = f;
        c.right = g;

        d.left = h;
        d.right = i;

        ArrayList arrayList = PrintFromTopToBottom(root);
        System.out.println(arrayList); // [A, B, C, D, E, F, G, H, I]

        arrayList = PrintFromTopToBottom2(root);
        System.out.println(arrayList); // [A, B, D, H, I, E, C, F, G]
    }
}