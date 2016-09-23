package base.bianli;

/**
 * Created by shanhaidaye on 2016/8/18.
 */
public class NodeTree {
    DataNode rootNode;
    DataNode tempNode;
    //int index_root;
    DataNode left_childDataNode;
    DataNode right_childDataNode;

    public DataNode initRootNode(int[] preArray){
        rootNode = new DataNode();
        rootNode.data = preArray[0];
        return rootNode;
    }

    public  void BuildTree(int[] preArray,int[] midArray,DataNode rootNode){
        int index_root = getIndex(midArray, rootNode.data);
        int lengthOfRightTree = preArray.length - index_root -1;

        int[] preArray_left;
        int[] preArray_right;
        int[] midArray_left;
        int[] midArray_right;

        if (index_root>0) {
            left_childDataNode = new DataNode();
            if (index_root==1) {
                left_childDataNode.data = midArray[0];
                rootNode.leftChild = left_childDataNode;
            }else {
                preArray_left = new int[index_root];
                midArray_left = new int[index_root];
                System.arraycopy(preArray, 1, preArray_left, 0, index_root);
                System.arraycopy(midArray, 0, midArray_left, 0, index_root);
                left_childDataNode.data = preArray_left[0];
                rootNode.leftChild = left_childDataNode;
                BuildTree(preArray_left, midArray_left, left_childDataNode);
            }
        }
        if (lengthOfRightTree>0) {
            right_childDataNode = new DataNode();
            if (lengthOfRightTree==1) {
                right_childDataNode.data = midArray[index_root+1];
                rootNode.rightChild = right_childDataNode;
                return;
            }else {
                preArray_right  = new int[lengthOfRightTree];
                midArray_right = new int[lengthOfRightTree];
                System.arraycopy(preArray, index_root+1, preArray_right, 0,lengthOfRightTree);
                System.arraycopy(midArray, index_root+1, midArray_right, 0, lengthOfRightTree);
                right_childDataNode.data = preArray_right[0];
                rootNode.rightChild = right_childDataNode;
                BuildTree(preArray_right, midArray_right,right_childDataNode);
            }
        }
    }

    public int getIndex(int[] array,int temp){
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==temp) {
                index = i;
                return index;
            }
        }
        return index;
    }
    //后序遍历
    public void postOrderTraverse(DataNode node){
        if (node==null) {
            return;
        }
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data+"-");
    }
    //前序遍历
    public void preOrderTraverse(DataNode node){
        if (node==null) {
            return;
        }
        System.out.print(node.data+"-");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }
    //中序遍历
    public void inOrderTraverse(DataNode node){
        if (node==null) {
            return;
        }
        inOrderTraverse(node.leftChild);
        System.out.print(node.data + "-");
        inOrderTraverse(node.rightChild);
    }

    public static void main(String args[]){
        int[] preArray = {11,1,33,44,55,8,77,10,99};
        int[] midArray = {33,8,1,55,44,11,99,10,77};
        NodeTree tree = new NodeTree();
        DataNode headNode = tree.initRootNode(preArray);
        tree.BuildTree(preArray, midArray, headNode);
        tree.postOrderTraverse(headNode);
    }

}
