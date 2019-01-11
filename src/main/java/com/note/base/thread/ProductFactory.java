package com.note.base.thread;

import java.util.Random;

public class ProductFactory {
    public static void main(String[] args) {
        ProductFactory pf = new ProductFactory();
        // 下单，交钱
        Future f = pf.createProduct("蛋糕");
        System.out.println("我去上班了，下了班我来取蛋糕...");
        // 拿着订单获取产品
        System.out.println("我拿着蛋糕回家." + f.get());
    }

    public Future createProduct(String name) {
        Future f = new Future(); // 创建一个订单
        System.out.println("下单成功，你可以去上班...");
        // 生产产品
        new Thread(() -> {
            Product p = new Product(new Random().nextInt(), name);
            f.setProduct(p);
        }).start();
        return f;
    }

}

class Product {
    private int id;
    private String name;

    public Product(int id, String name) {
        System.out.println("开始生产 " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }

        this.id = id;
        this.name = name;
        System.out.println(name + " 生产完毕");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

}

class Future {
    private Product product;
    private boolean down;

    public synchronized void setProduct(Product product) {
        if (down) {
            return;
        }

        this.product = product;
        this.down = true;
        notifyAll();
    }

    public synchronized Product get() {
        while (!down) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return product;
    }

}


