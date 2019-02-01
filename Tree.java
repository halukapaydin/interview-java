package com.scholar.osint.mp.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tree {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Node {
        int h;
        int data;
        Node left;
        Node right;

        @Override
        public String toString() {
            return data + "(" + h + ")";
        }
    }

    private Node root;

    public void add(int data){
        if(root == null){
            root = new Node(1, data, null,  null);
        }else{
            add(2, root, data);
        }
    }

    private void add(int h, Node node, int data){
        if(node.data > data){
            if(node.left == null){
                node.left = new Node(h, data, null, null);
            }else{
                add(++h, node.left, data);
            }
        }else if(node.data < data){
            if(node.right == null){
                node.right = new Node(h, data, null, null);
            }else{
                add(++h, node.right, data);
            }
        }
    }

    public Node find(int data){
        Node node = find(root, data);
        return node;
    }

    private Node find(Node node, int data){
        if(node == null){
            return null;
        }

        if(node.data == data){
            return node;
        }else{
            Node n = find(node.left, data);
            if(n != null){
                return n;
            }

            n = find(node.right, data);
            if(n != null){
                return n;
            }


        }
        return null;
    }


    public void delete(int value){

    }

    private void delete(Node node){
        if(node.right == null && node.left == null){

        }


    }





    public List<Node> traverseRNL(){
        List<Node> list = new ArrayList<>();
        traverseRNL(list, root);
        return list;
    }

    private void traverseRNL(List<Node> list, Node node){
        if(node != null){
            traverseRNL(list, node.right);
            list.add(node);
            traverseRNL(list, node.left);
        }
    }

    public List<Node> traverseLNR(){
        List<Node> list = new ArrayList<>();
        traverseLNR(list, root);
        return list;
    }

    private void traverseLNR(List<Node> list, Node node){
        if(node != null){
            traverseLNR(list, node.left);
            list.add(node);
            traverseLNR(list, node.right);
        }
    }

    public Node min(){
        Node node = root;
        while (node != null){
            if(node.left != null){
                node = node.left;
            }else{
                return node;
            }

        }
        return node;
    }

    public Node max(){
        Node node = root;
        while (node != null){
            if(node.right != null){
                node = node.right;
            }else{
                return node;
            }

        }
        return node;
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.add(12);
        tree.add(232);
        tree.add(230);
        tree.add(43430);
        tree.add(504);
        tree.add(6540);
        tree.add(7);
        tree.add(40);
        tree.add(86);

        System.err.println(tree.traverseLNR().stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.err.println(tree.traverseRNL().stream().map(String::valueOf).collect(Collectors.joining(",")));

        Node node = tree.find(504);
        System.err.println("N : " + node.data +  " L : " + node.left + " R : " + node.right);
        System.err.println("min : " + tree.min() +  " max : " + tree.max());

    }


}
