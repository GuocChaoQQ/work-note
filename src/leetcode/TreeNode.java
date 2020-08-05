package leetcode;

/**
 * created by chao.guo on 2020/7/28
 **/
 class TreeNode {
    int val;
    TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

class Solution {

    public int maxDepth(TreeNode root) {
        int left_dept=0;
        int right_dept=0;
       if(root==null){
           return  0;
       }

       if(root.left!=null ){ // 根节点的左节点不为空
            left_dept=  maxDepth(root.left);
       }
        if(root.right!=null ){ // 根节点的左节点不为空
            right_dept= maxDepth(root.right);
        }
        return Math.max(left_dept,right_dept);

    }


}