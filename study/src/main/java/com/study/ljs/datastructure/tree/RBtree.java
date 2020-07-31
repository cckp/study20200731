package com.study.ljs.datastructure.tree;

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

public class RBtree {


}


class Node<T>{
    public T value;
    public Node<T> parent;
    public boolean isRed;
    public Node<T> left;
    public Node<T> right;
}