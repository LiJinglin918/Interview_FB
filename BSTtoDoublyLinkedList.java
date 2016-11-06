// 47. BST to Doubly LinkedList
// Time complexity O(nlgn) 
public static TreeNode Tree2LinkedList(TreeNode root) {
	if (root == null) {
		return root;
	}
	Connect(root);
	TreeNode head = root;
	TreeNode tail = root;
	while (head.left != null) {
		head = head.left;
	}
	while (tail.right != null) {
		tail = tail.right;
	}
	head.left = tail;
	tail.right = head;
	return head;
}
private static void Connect(TreeNode root) {
	if (root == null) {
		return;
	}
	if (root.left != null) {
		TreeNode left = root.left;
		Connect(left);
		while (left.right != null) {
			left = left.right;
		}
		left.right = root;
		root.left = left;
	}
	if (root.right != null) {
		TreeNode right =root.right;
		Connect(right);
		while (right.left != null) {
			right = right.left;
		}
		right.left = root;
		root.right = right;
	}
}



// 71. Tree to Doubly LinkedList

     3
   /
1         
   \ 
    2
一棵树，返回一个list的头结点：
1《——》2《——》3（一三相连）
意思是返回一个递增list，1的right是2，left是3， 2的left是1，right是3,3的left是2，right是 1.
我用inorder获得递增序列然后设置left与right的node。返回1这个结点。. from: 1point3acres.com/bbs 
follow up如何不先获得序列，直接在traverse的时候获得结果。follow up没回答出来。

Tree To Double linked list:

class TreeToDoubleLinkedList {
    public DoubleLinkedList toDoubleLinkedList(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> inorder = new Stack<>();
        DoubleLinkedList fakeHead = new DoubleLinkedList(0);
        DoubleLinkedList previous = fakeHead;
        toLeft(inorder, root);
        while (!inorder.isEmpty()) {
            TreeNode node = inorder.pop();
            DoubleLinkedList curNode = new DoubleLinkedList(node.val);
            curNode.pre = previous;
            previous.next = curNode;
            previous = curNode;
            if (node.right != null) {
                toLeft(inorder, node.right);
            }
        }
        DoubleLinkedList head = fakeHead.next;
        head.pre = previous;
        previous.next = head;
        return head;
    }

    private void toLeft(Stack<TreeNode> inorder, TreeNode root) {
        while (root != null) {
            inorder.push(root);
            root = root.left;
        }
    }
}

class DoubleLinkedList {
    DoubleLinkedList pre;
    DoubleLinkedList next;
    int val;
    public DoubleLinkedList(int val) {
        this.val = val;
    }
}
