package interview.cake;

import java.util.Stack;

public class ValidBinaryTree {
	
	public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }
	
	public static class NodeBoundPair {
		private int lowerBound;
		private int upperBound;
		BinaryTreeNode node;
		
		public NodeBoundPair(BinaryTreeNode node, int lowerBound, int upperBound) {
			this.node = node;
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
		}
		
	}
	
	public static boolean isBalanced(BinaryTreeNode root) {
		if(root == null) {
			return true;
		}
		
		Stack<NodeBoundPair> nodeBoundStack = new Stack<>();
		if(root.left != null && root.left.value < root.value) {
			nodeBoundStack.push(new NodeBoundPair(root.left, Integer.MIN_VALUE, root.value));
		}
		
		if(root.right != null && root.right.value > root.value) {
			nodeBoundStack.push(new NodeBoundPair(root.right, root.value, Integer.MAX_VALUE));
		}
		
		while(!nodeBoundStack.isEmpty()) {
			NodeBoundPair currentPair = nodeBoundStack.pop();
			int lowerBound = currentPair.lowerBound;
			int upperBound = currentPair.upperBound;
			BinaryTreeNode node = currentPair.node;
			
			if(node.value > lowerBound && node.value < upperBound) {
				if(node.left != null) {
					nodeBoundStack.push(new NodeBoundPair(node.left, lowerBound, node.value));
				}
				if(node.right != null) {
					nodeBoundStack.push(new NodeBoundPair(node.right, node.value, upperBound));
				}
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        
        System.out.println(isBalanced(root));
	}
}
