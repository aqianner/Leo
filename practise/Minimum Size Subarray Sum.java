public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int p = 0, q = 0, cur = 0, min = Integer.MAX_VALUE;
        while (p < len) {
            cur = nums[q];
            while (cur < s && q < len - 1) {
                q++;
                cur +=nums[q];
            }
            if (cur >= s)
                min = Math.min(q - p + 1, min);
            p++;
            q = p;
        }
        return min;
    }
}
别人的代码，比我的快太多。。
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int re = nums.length + 1;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= s) {
                if (right - left + 1 == 1)
                        return 1;
                re = Math.min(re, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }
        return re == nums.length + 1 ? 0 : re;
    }
} 
这个是nlogn的方法:
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i] + res[i - 1];
        }
        int mid = 0;
        int count = nums.length;
        for (int i = 0; i < nums.length; i++) {
        	int p = i, q = nums.length - 1;
		    while (p <= q) {
		        mid = p + (q - p) / 2;
		        if (res[mid] - res[i] + nums[i] >= s) {
		            q = mid - 1;
		            count = Math.min(mid - i + 1, count);
		        } else {
		            p = mid + 1;
		        }
		    }
        }
        return count == nums.length? 0: count;
    } 
}
因为已经提醒是nlogn了，所以其实第一眼想到的是归并排序之类的。。
但是重点是归并排序之所以能用是因为已经排好序了而且合并很简单。。
但是这题里面合并太头疼了。。虽然问题规模变小但是其实这样只会更麻烦吧。。
问题规模变小解决的原因是因为好拆好合吧。。
然后除了归并。。
然后因为是正数，正数有个性质，就是累加和是sorted的呀
sorted了的话就用二分查找呀！
nlogn就是对每一个二分查找了。
就像从一个sorted的序列里面找出第k大的。。
简直简单到爆。。

O(n)的原因是因为每个点只会被加入一次，被删除一次。所以其实是2n
我感觉这种算法复杂度计算起来有点不一样。
不过其实还是计算遍历的点的总个数啊。。
n方那种，很明显每次要遍历的是n,n-1, n-2个点...
也可以反过来看第一个点被遍历了1次，第二个被遍历了2次，第n个点被遍历了n次
但是这题每一个都是start遍历一次，end遍历一次，所以对所有的点就是2n了。。
可是我觉得这样感觉只有特殊做法才会这样。
其实sliding window是本质是deque吧。。
可以去研究一下deque的各种操作的复杂度。
