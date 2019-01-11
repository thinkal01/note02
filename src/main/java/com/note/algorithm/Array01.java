package com.note.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Array01 {
    public void test03() {
        //定义一个二维数组
        int[][] arr = {{1, 2, 3}, {4, 5}, {6}};
        printArray2(arr);
    }

    public void test02() {
        //定义数组
        int[][] arr = new int[3][];

        System.out.println(arr);    //[[I@175078b
        System.out.println(arr[0]); //null
        System.out.println(arr[1]); //null
        System.out.println(arr[2]); //null

        //动态的为每一个一维数组分配空间
        arr[0] = new int[2];
        arr[1] = new int[3];
        arr[2] = new int[1];
    }

    @Test
    public void test04() {
        //定义一个数据
        int number = 123456;
        //定义一个数组
        int[] arr = new int[8];

        //通过观察这个代码，我们发现应该是可以通过循环改进的
        int index = 0;
        while (number > 0) {
            arr[index] = number % 10;
            index++;
            number /= 10;
        }

        //输出数据
        for (int x = 0; x < index; x++) {
            System.out.print(arr[x]);
        }
    }

    public void test07() {
        // 不要同时动态和静态进行
        // int[] arr = new int[3]{1,2,3};
    }

    @Test
    public void testArrayCopy() {
        int[] arr = {11, 22, 33, 44, 55};
        int[] arr2 = {6, 7, 8, 9, 10};
        System.arraycopy(arr, 1, arr2, 2, 2);

        // 转集合
        String[] strArray = {"hello", "world", "java"};
        List<String> list = Arrays.asList(strArray);
        list = Arrays.asList("hello", "world", "java");
    }

    @Test
    public void test01() {
        int[] arr = {8, 54, 41, 21, 9, 15};
        selectSort(arr);
        int i = halfSearch_2(arr, 21);
    }

    public void testArrays() {
        int[] arr = {24, 69, 80, 57, 13};

        // 把数组转成字符串
        System.out.println("排序前：" + Arrays.toString(arr));

        // 对数组进行排序
        Arrays.sort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));

        // 二分查找
        System.out.println("binarySearch:" + Arrays.binarySearch(arr, 57));
    }

    // 二分查找
    public static int getIndex(int[] arr, int value) {
        //定义最大索引，最小索引
        int min = 0;
        int max = arr.length - 1;
        int mid = (max + min) / 2;

        while (arr[mid] != value) {
            if (arr[mid] > value) {
                max = mid - 1;
            } else if (arr[mid] < value) {
                min = mid + 1;
            }

            if (min > max) {
                return -1;
            }

            mid = (max + min) / 2;
        }

        return mid;
    }

    // 二分查找
    public static int halfSearch_2(int[] arr, int key) {
        int min, max, mid;
        min = 0;
        max = arr.length - 1;
        while (min <= max) {
            mid = (max + min) >> 1;
            if (key > arr[mid])
                min = mid + 1;
            else if (key < arr[mid])
                max = mid - 1;
            else
                return mid;
        }
        return -min - 1;
    }

    // 选择排序
    public static void selectSort(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            for (int y = x + 1; y < arr.length; y++) {
                if (arr[y] < arr[x]) {
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                }
            }
        }
    }

    public static void selectSort2(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            int index = x;
            int y = x + 1;
            for (; y < arr.length; y++) {
                if (arr[index] > arr[y]) {
                    index = y;
                }
            }
            if (index != x) {
                arr[x] = arr[x] ^ arr[y];
                arr[y] = arr[x] ^ arr[y];
                arr[x] = arr[x] ^ arr[y];
            }
        }
    }

    /*
        需求：数组逆序
    */
    public static void reverse(int[] arr) {
        //用循环改进
        for (int x = 0; x < arr.length / 2; x++) {
            int temp = arr[x];
            arr[x] = arr[arr.length - 1 - x];
            arr[arr.length - 1 - x] = temp;
        }
    }

    public static void reverse2(int[] arr) {
        for (int start = 0, end = arr.length - 1; start <= end; start++, end--) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }

    // 数组最大值
    public static int getMax(int[] arr) {
        int maxIndex = 0;
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > arr[maxIndex])
                maxIndex = x;
        }
        return arr[maxIndex];
    }

    /*
        需求：打印杨辉三角形(行数可以键盘录入)
        1
        1 1
        1 2 1
        1 3 3 1
        1 4 6 4 1
        1 5 10 10 5 1
        分析：看这种图像的规律
            A:任何一行的第一列和最后一列都是1
            B:从第三行开始，每一个数据是它上一行的前一列和它上一行的本列之和。
    */
    @Test
    public void pascalTriangle() {
        // public static void main(String args[]) {
        // 创建键盘录入对象
        Scanner sc = new Scanner(System.in);

        // 这个n的数据来自于键盘录入。
        System.out.println("请输入一个数据：");
        int n = sc.nextInt();

        // 定义二维数组
        int[][] arr = new int[n][n];

        // 给这个二维数组任何一行的第一列和最后一列赋值为1
        for (int x = 0; x < arr.length; x++) {
            arr[x][0] = 1; //任何一行第1列
            arr[x][x] = 1; //任何一行的最后1列
        }

        // 从第三行开始，每一个数据是它上一行的前一列和它上一行的本列之和。
        for (int x = 2; x < arr.length; x++) {
            // 最后一列为1,这里要减去1
            // 第一列也是有值了,y从1开始
            for (int y = 1; y <= x - 1; y++) {
                // 每一个数据是它上一行的前一列和它上一行的本列之和。
                arr[x][y] = arr[x - 1][y - 1] + arr[x - 1][y];
            }
        }

        //遍历这个二维数组。
        for (int x = 0; x < arr.length; x++) {
            // for(int y=0; y<arr[x].length; y++) {
            for (int y = 0; y <= x; y++) {
                System.out.print(arr[x][y] + "\t");
            }
            System.out.println();
        }
    }

    //遍历数组
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            if (x == arr.length - 1) { //这是最后一个元素
                System.out.println(arr[x] + "]");
            } else {
                System.out.print(arr[x] + ", ");
            }
        }
    }

    // 数组toString
    public static String arrToString(int[] a) {
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {//中间省略条件判断，提高了效率。
            b.append(a[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    /*
        需求：遍历二维数组
    */
    public static void printArray2(int[][] arr) {
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                System.out.print(arr[x][y] + " ");
            }
            System.out.println();
        }
    }


    // 三维数组
    @Test
    public void testArray() {
        // 静态初始化完成，每行的数组元素个数不一样
        int score[][][] = {{{5, 1}, {6, 7}}, {{9, 4}, {8, 3}}};

        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++) {
                for (int k = 0; k < score[i][j].length; k++) {
                    System.out.println("score[" + i + "][" + j + "][" + k + "] = " + score[i][j][k] + "\t");
                }
            }
        }
    }

    @Test
    public void arraysFunction() {
        int temp[] = {3, 4, 5, 7, 9, 1, 2, 6, 8};
        // 排序
        Arrays.sort(temp);
        System.out.print("排序后的数组：");
        System.out.println(Arrays.toString(temp));
        // 如果要想使用二分法查询的话，则必须是排序之后的数组
        int point = Arrays.binarySearch(temp, 3);
        System.out.println("元素‘3’的位置在：" + point);
        // 填充数组
        Arrays.fill(temp, 3);
        System.out.print("数组填充：");
        System.out.println(Arrays.toString(temp));
    }
}
