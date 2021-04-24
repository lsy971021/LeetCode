package lsy;


import com.sun.tools.hat.internal.parser.ReadBuffer;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.*;

/**
 * @author 刘思远
 */
public class LeetCode {

    @Test
    public void letCodeTest() {
        System.out.println(Arrays.toString(exchange(new int[]{21, 2, 4, 6, 2, 3, 4, 5, 6, 7, 89, 11, 12, 14, 15, 16})));
        System.out.println(majorityElement(new int[]{1, 2, 3, 4, 3, 5, 3, 3, 3, 2, 4, 6, 7, 8, 3, 5, 5, 5, 3, 3}));
        System.out.println(Arrays.toString(constructArr(new int[]{1, 2, 3})));
        System.out.println(lengthOfLongestSubstring("abcdeaddg"));
        Map test = distinct(new int[]{1, 2, 4, 11, 12, 13}, new int[]{4, 6, 8, 9, 15});
        for (Object o : test.keySet()) {
            System.out.println(o);
        }
        System.out.println("dfs:" + movingCount(5, 5, 5));
        System.out.println("青蛙跳台问题:"+numWays(8));
    }


    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int lift = 0;
        int right = nums.length - 1;
        int ex;
        while (lift < right) {
            while (lift < right) {
                if (nums[lift] % 2 == 0) {
                    break;
                }
                lift++;
            }
            while (lift < right) {
                if (nums[right] % 2 != 0) {
                    break;
                }
                right--;
            }
            ex = nums[lift];
            nums[lift] = nums[right];
            nums[right] = ex;
        }
        return nums;
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int index = 0, times = 0;
        for (int i = 0; i < nums.length; i++) {
            if (times == 0) {
                index = nums[i];
                times++;
                continue;
            }
            if (index != nums[i]) {
                times--;
                continue;
            }
            times++;
        }
        return index;
    }

    /**
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素
     * B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     * 示例:
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int[] result = new int[a.length];
        int lift = 1;
        for (int i = 0; i < a.length; i++) {
            result[i] = lift;
            lift *= a[i];
        }
        int right = 1;
        for (int length = a.length - 1; length >= 0; length--) {
            result[length] *= right;
            right *= a[length];
        }
        return result;
    }

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * 示例：
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是、 "abc"，所以其长度为 3
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int num = 0;
        Map<Character, Integer> map = new HashMap();
        for (int end = 0, start = 0; end < length; end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)) + 1);
            }
            num = Math.max(num, end - start + 1);
            map.put(s.charAt(end), end);
        }
        return num;
    }
    /*public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }*/


    /**
     * 在a数组中去除含有b数组中任意一个数
     * 示例：
     * 输入：a=[1,2,3,4,5] b=[2,5,6,7]
     * 输出：[1,3,4]
     *
     * @param a
     * @param b
     * @return
     */
    public Map distinct(int[] a, int[] b) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>(a.length);
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], true);
        }
        for (int i = 0; i < b.length; i++) {
            if (map.get(b[i]) != null) {
                map.remove(b[i]);
            }
        }
        int[] n = new int[map.size()];
        int num = 0;
        for (Integer integer : map.keySet()) {
            n[num++] = integer;
        }
        return map;
    }

    /**
     * DFS(Depth first search)
     * 地上有一个 m 行 n 列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人
     * 从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动
     * 到方格外），也不能进入行坐标和列坐标的数位之和大于 k 的格子。例如，当 k 为
     * 18 时，机器人能够进入方格 [35, 37] ，因为 3+5+3+7=18。但它不能进入方格
     * [35, 38]，因为 3+5+3+8=19。请问该机器人能够到达多少个格子？
     * 示例 1:
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     */

    int count = 0;

    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        boolean[][] marked = new boolean[m][n];
        return dfs(marked, 0, 0, k);
    }

    public int dfs(boolean[][] marked, int a, int b, int k) {
        if (a >= marked.length || b >= marked[0].length || a < 0 || b < 0|| marked[a][b]) {
            return 0;
        }
        if (a < 10 && b < 10 && a + b >= k) {
            return 0;
        } else {
            int aa = a > 10 ? a / 10 + a % 10 : a;
            int bb = b > 10 ? b / 10 + b % 10 : b;
            if (aa + bb >= k) {
                return 0;
            }
        }
        marked[a][b] = true;
        dfs(marked, a + 1, b, k);
        dfs(marked, a, b + 1, k);
        dfs(marked, a - 1, b, k);
        dfs(marked, a, b - 1, k);
        return ++count;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直
     * 线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * @return
     */
    public void maxArea() {

    }


    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = s.length();
        int maxNum = length > numRows ? length / numRows - 1 : 1;
        int minNum = length > numRows ? (length - numRows) / 4 + 1 : 0;
        for (int i = 0; i < maxNum; i++) {
            stringBuffer.append(s.charAt(2 * (numRows - 1) * i));
        }
        return stringBuffer.toString();
    }


    /**
     * @斐波那契数列
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 思路：
     * 标签：动态规划
     * 整体思路：本质是斐波那契数列和循环求余法
     * 令 F(n) 表示为上第 n 层台阶时所需要的跳法
     * 因为一次可以跳上 1 级台阶，也可以跳上 2 级台阶，所以 F(n) 可以从 F(n−1) 跳上来，也可以从 F(n−2) 跳上来
     * 推导出 F(n) = F(n-1) + F(n-2)
     * 由于第 n 层的跳法数量只取决于 n-1 和 n−2 层，所以存储时，可以仅存储这三个值，优化空间复杂度
     * 时间复杂度：O(n)，空间复杂度：O(1)
     */
    public int numWays(int n) {
        int n1 = 1,n2 = 1,sum=1;
        for (int i = 0; i < n; i++) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        System.out.println("青蛙跳台问题解1："+n1);
        n1 = 1;n2 = 1;sum=1;
        for (int i = 1; i < n; i++) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return sum;
    }

}
