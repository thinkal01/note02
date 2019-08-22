package com.note.algorithm.btree;

import org.junit.Test;

import java.util.*;

public class FindAllRoute {

    /*
           A
        B     C
       D E  F H G
         I
     */
    @Test
    public void test01() {
        // 创建一棵树
        TreeNode root = new TreeNode("A");
        // 第二层
        root.children.add(new TreeNode("B"));
        root.children.add(new TreeNode("C"));
        // 第三层
        root.children.get(0).children.add(new TreeNode("D"));
        root.children.get(0).children.add(new TreeNode("E"));

        root.children.get(1).children.add(new TreeNode("F"));
        root.children.get(1).children.add(new TreeNode("H"));
        root.children.get(1).children.add(new TreeNode("G"));
        // 第四层
        root.children.get(0).children.get(1).children.add(new TreeNode("I"));

        // recurTree(root);
        System.out.println(dfsTree(root));
    }

    /**
     * 深度优先遍历（递归方式）-- 树（Tree）
     */
    public void recurTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(root.value);
        // 查找“E”的分支
        findPath(result, root, path, "E");
        System.out.println(result);
    }

    private void findPath(List<List<String>> result, TreeNode node, List<String> path, String target) {
        /*if (target.equals(node.value)) {
            result.add(path);
            return;
        }*/
        if (node.children == null || node.children.size() <= 0) {
            result.add(path); // 查找所有
            return;
        }

        for (int i = 0; i < node.children.size(); i++) {
            TreeNode child = node.children.get(i);
            List<String> cPath = new ArrayList<>();
            cPath.addAll(path);
            cPath.add(child.value);
            // if (result.size() > 0) break;
            findPath(result, child, cPath, target);
        }
    }

    /**
     * 深度优先遍历（非递归方式）-- 查找树的所有叶子路径
     *
     * @param root 根节点
     * @return 叶子路径的集合
     */
    public List<List<TreeNode>> dfsTree(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<List<TreeNode>> pathStack = new Stack<>();
        List<List<TreeNode>> result = new ArrayList<>();
        nodeStack.push(root);

        ArrayList<TreeNode> arrayList = new ArrayList<>();
        // arrayList.add(root);
        pathStack.push(arrayList);

        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            List<TreeNode> curPath = pathStack.pop();
            // 也可以在此处添加
            curPath.add(curNode);

            if (curNode.children == null || curNode.children.size() <= 0) {
                result.add(curPath);
            } else {
                int childSize = curNode.children.size();
                for (int i = childSize - 1; i >= 0; i--) {
                    TreeNode node = curNode.children.get(i);
                    nodeStack.push(node);
                    List<TreeNode> list = new ArrayList<>(curPath);
                    // list.add(node);
                    pathStack.push(list);
                }
            }
        }

        return result;
    }

    /**
     * 广度优先遍历 ---- 查找树的所有叶子路径
     *
     * @param root 根节点
     * @return 叶子路径的集合
     */
    public List<List<TreeNode>> bfsTree(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<TreeNode>> qstr = new LinkedList<>();
        List<List<TreeNode>> result = new ArrayList<>();
        nodeQueue.add(root);
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        qstr.add(arrayList);

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.remove();
            List<TreeNode> curList = qstr.remove();

            if (curNode.children == null || curNode.children.size() <= 0) {
                curList.add(curNode);
                result.add(curList);
            } else {
                for (int i = 0; i < curNode.children.size(); i++) {
                    TreeNode treeNode = curNode.children.get(i);
                    nodeQueue.add(treeNode);
                    List<TreeNode> list = new ArrayList<>(curList);
                    list.add(curNode);
                    qstr.add(list);
                }
            }
        }

        return result;
    }

    class TreeNode {
        String value;
        List<TreeNode> children;

        public TreeNode() {
            children = new ArrayList<>();
        }

        public TreeNode(String value) {
            this.value = value;
            children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
