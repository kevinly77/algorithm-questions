package interview.cake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BalancedBinaryTree {
	public static int minDepth = 0;
	public static int maxDepth = 0;
	public static int treeSize = 0;
	
	public static class BinaryTreeNode {
		public int value;
		public BinaryTreeNode left;
		public BinaryTreeNode right;
		public int size;
		
		public BinaryTreeNode(int value) {
			this.value = value;
			treeSize++;
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
	
	public static void dfs(BinaryTreeNode root, int target) {
		Stack<BinaryTreeNode> stack = new Stack<>();
		boolean[] isVisited = new boolean[treeSize];
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode current = stack.pop();
			//isVisited[current.value] = true;
			if(current.value == target) {
				System.out.println("target found: " + current.value);
			}
			if(current.left != null) {
				stack.push(current.left);
			}
			if(current.right != null) {
				stack.push(current.right);
			}
			
		}
		
	}
	public static boolean isBalanced(BinaryTreeNode treeRoot) {
		
		return false;
	}
	
	public static void printInOrder(BinaryTreeNode root) {
		if(root == null) {
			return;
		}
		printInOrder(root.left);
		System.out.println("Depth: " + minDepth);
		System.out.println(root.value);
		
		printInOrder(root.right);
		minDepth++;
	}
	
	public static boolean bfs(BinaryTreeNode root, int target) {
		if(root == null) {
			return false;
		}
		
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		boolean[] visited = new boolean[treeSize];
		visited[root.value] = true;
		queue.add(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode current = queue.poll();
			if(current.value == target) {
				System.out.println("Found target" + current.value);
				return true;
			}
			
			if(current.left != null) {
				queue.add(current.left);
			}
			if(current.right != null) {
				queue.add(current.right);
			}
			
		}
		return false;
		
	}
	
	public static void test() {
		BinaryTreeNode root = new BinaryTreeNode(5);
        BinaryTreeNode a = root.insertLeft(8);
        BinaryTreeNode b = root.insertRight(6);
        a.insertLeft(1);
        a.insertRight(2);
        b.insertLeft(3);
        b.insertRight(4);
        dfs(root, 4);
        System.out.println("-------------");
        System.out.println(treeSize);
	}
	
	public static void test2() {
		final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7).insertRight(8);
        printInOrder(root);
	}
	public static void main(String args[]) {
		test();
        
        
        
       
        
        
        
        
        
	}
}
