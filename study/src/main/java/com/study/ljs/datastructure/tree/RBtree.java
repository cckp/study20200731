package com.study.ljs.datastructure.tree;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 参考：https://zhuanlan.zhihu.com/p/24367771
 *
 * @author lijiasheng
 * date : 2020/7/31 8:07
 *
 * 红黑树定义：   任何一个节点都有颜色，黑色或者红色
 *              根节点是黑色的
 *              父子节点之间不能出现两个连续的红节点
 *              任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等
 *              空节点被认为是黑色的
 * */

public class RBtree<T> {
    /**
     * 左旋、右旋、变色
     * */
    private Node<T> root;

    public RBtree(Node<T> root) {
        this.root = root;
    }

    /**
     * 左旋操作
     * */
    public void leftRotate(Node target){
        if (target==root||null==target){return;}
        Node target_parent = target.getParent();
        Node target_grandparent = target_parent.getParent();

        boolean isRoot = false;
        if (null == target_grandparent){
            target_grandparent = this.root;
            isRoot = true;
        }

        Node target_left = target.getLeft();
        boolean isLeft = target_grandparent.getLeft()==target_parent?true:false;

        target_parent.setRight(target_left);
        target_left.setParent(target_parent);

        target.setLeft(target_parent);
        target_parent.setParent(target);

        if (isLeft){
            target_grandparent.setLeft(target);
        }else {
            target_grandparent.setRight(target);
        }
        if (isRoot){
            this.root = target;
            this.root.setParent(null);
        }
    }
    public void rightRotate(Node target){
        if (target==root||null==target){return;}
        Node target_parent = target.getParent();
        Node target_grandparent = target_parent.getParent();


        boolean isRoot = false;
        if (null == target_grandparent){
            target_grandparent = this.root;
            isRoot = true;
        }

        Node target_right = target.getRight();
        boolean isLeft = target_grandparent.getLeft()==target_parent?true:false;

        target_parent.setLeft(target_right);
        target_right.setParent(target_parent);

        target.setRight(target_parent);
        target_parent.setParent(target);

        if (isLeft){
            target_grandparent.setLeft(target);
        }else{
            target_grandparent.setRight(target);
        }
        if (isRoot){
            this.root = target;
            this.root.setParent(null);
        }
    }


    public static void main(String[] args) {

        Node<Integer> root = new Node<Integer>().setValue(100);

        Node<Integer> root_left = new Node<Integer>().setValue(50);
        Node<Integer> root_right = new Node<Integer>().setValue(200);

        Node<Integer> root_left_right = new Node<Integer>().setValue(60);
        Node<Integer> root_left_left = new Node<Integer>().setValue(40);

        Node<Integer> root_right_right = new Node<Integer>().setValue(300);
        Node<Integer> root_right_left = new Node<Integer>().setValue(150);

        root.setLeft(root_left);
        root_left.setParent(root);

        root.setRight(root_right);
        root_right.setParent(root);


        root_left.setLeft(root_left_left);
        root_left_left.setParent(root_left);

        root_left.setRight(root_left_right);
        root_left_right.setParent(root_left);


        root_right.setLeft(root_right_left);
        root_right_left.setParent(root_right);

        root_right.setRight(root_right_right);
        root_right_right.setParent(root_right);



        RBtree<Integer> rBtree = new RBtree<Integer>(root);


        System.out.println("左旋");

        rBtree.leftRotate(root.getRight());

        System.out.println("右旋");


    }
}



@Data
@Accessors(chain = true)
class Node<T>{
    private T value;
    private Node<T> parent;
    private boolean isRed;
    private Node<T> left;
    private Node<T> right;


    @Override
    public String toString() {
        return "value=" + value;
    }
}