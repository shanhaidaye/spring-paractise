package base.bianli;

/**
 * Created by shanhaidaye on 2016/8/18.
 */

    /**
     * Copyright 2014 by Ruiqin Sun
     * All right reserved
     * created  on 2014-9-9 下午2:34:15
     **/
    public class BinTreeInt {
        private Node root;

        /**
         * 创建内部节点类
         **/
        private class Node{
            //      左节点
            private Node leftChild;
            //      右节点
            private Node rightChild;
            //      节点对应的值
            private int data;

            public Node(int data){
                this.leftChild = null;
                this.rightChild = null;
                this.data = data;
            }
        }// class Node

        public BinTreeInt(){
            root = null;
        }

        /*
         *递归的创建二叉树
         * */
        public void buildTree(Node node ,int data){
            if (root == null){// 如果根节点为空，创建根节点
                root = new Node(data);
            }else{
                if(data <node.data){//插入到左子树
                    if(node.leftChild == null){//左节点为空，直接创建值为data的左节点
                        node.leftChild = new Node(data);
                    }else{//左节点不为空，调用buildTree函数插到左子树中
                        buildTree(node.leftChild,data);
                    }
                }else{
                    if(node.rightChild == null){
                        node.rightChild = new Node(data);
                    }else{
                        buildTree(node.rightChild,data);
                    }
                }
            }
        }//end buildTree
        /*
         *前序遍历二叉树
         * */
        public void preOrder(Node node){
            if(node != null){
                System.out.print(node.data+"\t");
                preOrder(node.leftChild);
                preOrder(node.rightChild);
            }
        }
        /*
         *中序遍历二叉树
         * */
        public void inOrder(Node node){
            if(node != null){
                inOrder(node.leftChild);
                System.out.print(node.data+"\t");
                inOrder(node.rightChild);
            }
        }
        /*
         *后序遍历二叉树
         * */
        public void postOrder(Node node){
            if(node != null){
                postOrder(node.leftChild);
                postOrder(node.rightChild);
                System.out.print(node.data+"\t");
            }
        }

        public static void main(String ars[]){
            int[] a={2,4,12,45,21,6,111};

            BinTreeInt binTree = new BinTreeInt();
            for(int i = 0; i<a.length; i++){
                binTree.buildTree(binTree.root, a[i]);
            }
            System.out.print("前序遍历");
            binTree.preOrder(binTree.root);
            System.out.println("");
            System.out.print("中序遍历");
            binTree.inOrder(binTree.root);
            System.out.println("");
            System.out.print("后序遍历");
            binTree.postOrder(binTree.root);
        }
    }
