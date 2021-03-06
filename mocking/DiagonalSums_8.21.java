StringBuilder 和 StringBuffer
LinkedList 和 ArrayList
Array 和 ArrayList

Consider lines of slope -1 passing between nodes (dotted lines in below diagram). 
Diagonal sum in a binary tree is sum of all node’s data lying through these lines. 
Given a Binary Tree, print all diagonal sums. For the following input tree, output should be 11, 14, 5.
思路很简单。，不过出了一个bug。。
来我们再写一遍。
Iterator<TreeNode, Integer> its = hm.entrySet().next();
The Map.entrySet method returns a collection-view of the map
http://docs.oracle.com/javase/7/docs/api/java/util/Map.Entry.html
其实hm.entrySet
..这里错啦，应该是Iterator<Entry<TreeNode, Integer>>
while(its.hasNext()) {
  EntrySet<TreeNode Integer> temp = its.next();
  int val = temp.getVal();
  int key = temp.getKey();
}
注意和种类Entry<>是单个，entrySet()是返回一个集合啦

Set<Map.Entry<K,V>>	entrySet()
就是说Set<Entry<K,V>> entrySet()
所以其实map里面的类型就是Entry<K, V>,可以通过map.entrySet()得到。
public class Solution {
	public ArrayList<Integer> dotted(TreeNode root) {
		// we can try the inorder travesal first
		// so we need a hashmap to store the count for each node
		HashMap<TreeNode, Integer> hm = new HashMap<TreeNode, Integer>();
		HashMap<Integer, Integer> cal = new HashMap<Integer, Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		inorder(root, hm, 0);
		Iterator<Entry<TreeNode, Integer>> its = hm.entrySet().iterator();
		while (its.hasNext()) {
			Entry<TreeNode, Integer> temp = its.next();
			int tempVal = temp.getKey().val;
			int tempKey = temp.getValue();
			if (!cal.containsKey(tempKey)) {
				cal.put(tempKey, tempVal);
			} else {
				cal.put(tempKey, cal.get(tempKey) + tempVal);
			}
		}
		ArrayList<Integer> index = new ArrayList<Integer>(cal.keySet());
		Collections.sort(index);
		for (int i = 0; i < index.size(); i++) {
			res.add(cal.get(index.get(i)));
		}
		return res;

	}

	void inorder(TreeNode root, HashMap<TreeNode, Integer> hm, int count) {
		if (root == null) {
			return;
		}
		inorder(root.left, hm, count + 1);
		hm.put(root, count);
		inorder(root.right, hm, count);
	}
	这样写简单很多了。。
public class Solution {
	public ArrayList<Integer> dotted(TreeNode root) {
	    // we can try the inorder travesal first
	    // so we need a hashmap to store the count for each node
	    HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    preorder(hm, 0, root);
	    ArrayList<Integer> index = new ArrayList<Integer>(hm.keySet());
	    Collections.sort(index);
	    for (int i = 0; i < index.size(); i++) {
	        res.add(hm.get(index.get(i)));
	    }
	    
	    return res;
	    
	}

	void preorder(HashMap<Integer, Integer> hm, int count, TreeNode root) {
	    if (root == null) {
	        return;
	    }
	    if (!hm.containsKey(count)) {
	        hm.put(count, root.val);
	    } else {
	        hm.put(count, hm.get(count) + root.val);
	    }
	    preorder(hm, count + 1, root.left);
	    preorder(hm, count, root.right);
	}
这里 
if (!hm.containsKey(count)) {
	        hm.put(count, root.val);
	    } else {
	        hm.put(count, hm.get(count) + root.val);
	    }
	    放在哪里其实无所谓的，因为说到底只是一个顺序的问题，改变count是因为count表示距离，
	    你要是想left的时候改。。你怎么改。。。
	    先处理哪个节点都无所谓。。
	    这里还是蛮神奇的，因为一个传下去hashmap的时候都是空，但是返回的过程中开始填
	    另一种是传下去的hashmap就开始填
	    但是对于每一root而言，其实状态是唯一的。
