这是错的。。
public class Solution {
	ArrayList<String> result = new ArrayList<String>();

	List<String> brackets(int l, int m, int n) { // l : (), m: [], n: {}
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(0);
		temp.add(0);
		temp.add(0);
		temp.add(0);
		temp.add(0);
		temp.add(0);
		helper(2 * l, 2 * m, 2 * n, temp,
				new StringBuffer());
		return result;
	}

	void helper(int l, int m, int n, ArrayList<Integer> temp, StringBuffer sb) {
		// temp = {lleft,lright,mleft,mright,nleft, nright};
		int lleft = temp.get(0);
		int lright = temp.get(1);
		int mleft = temp.get(2);
		int mright = temp.get(3);
		int nleft = temp.get(4);
		int nright = temp.get(5);
		if (lleft < lright || mleft < mright || nleft < nright || l < 0
				|| m < 0 || n < 0) {
			return;
		}
		if (lleft == lright && mleft == mright && nleft == nright && l == 0
				&& m == 0 && n == 0) {
			result.add(sb.toString());
		}
		sb.append('(');
		temp.set(0, temp.get(0) + 1);
		helper(l - 1, m, n, temp, sb);
		sb.deleteCharAt(sb.length() - 1);
		temp.set(0, temp.get(0) - 1);
		sb.append(')');
		temp.set(1, temp.get(1) + 1);
		helper(l - 1, m, n, temp, sb);
		sb.deleteCharAt(sb.length() - 1);
		temp.set(1, temp.get(1) - 1);
		sb.append('[');
		temp.set(2, temp.get(2) + 1);
		helper(l, m - 1, n, temp, sb);
		sb.deleteCharAt(sb.length() - 1);
		temp.set(2, temp.get(2) - 1);
		sb.append(']');
		temp.set(3, temp.get(3) + 1);
		helper(l, m - 1, n, temp, sb);
		sb.deleteCharAt(sb.length() - 1);
		temp.set(3, temp.get(3) - 1);
		sb.append('{');
		temp.set(4, temp.get(4) + 1);
		helper(l, m, n - 1, temp, sb);
		sb.deleteCharAt(sb.length() - 1);
		temp.set(4, temp.get(4) - 1);
		sb.append('}');
		temp.set(5, temp.get(5) + 1);
		helper(l, m, n - 1, temp, sb);
		sb.deleteCharAt(sb.length() - 1);
		temp.set(5, temp.get(5) - 1);

	}
