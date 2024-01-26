package org.zzx.nk.nk01;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author: zhouzhixiang
 * @create: 2023-07-27 11:21
 */
public class TaskHandler {

//    public static void main(String[] args) {

//        int[] nums1 = new int[]{1,-1,3,0,5,-4,2,6,-7,8};
//        int[] nums2 = new int[]{1,-1,8};
//        int[] intersection = intersection(nums1, nums2);
//        System.out.println(intersection);
//    }


    /**
     * @param str
     * @return
     */
    public static int maxLength(String str) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, temp = 0;
        for (int j = 0; j < str.length(); j++) {
            int i = dic.getOrDefault(str.charAt(j), -1);
            dic.put(str.charAt(j), j);
            temp = temp < j - i ? temp + 1 : j -i;
            res = Math.max(temp, res);
        }
        return res;
    }


    public static int[] getArray(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return new int[]{};

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }


    public static String convertMetersToKilometers(String metersString, String defaultValue) {
        try {
            double meters = Double.parseDouble(metersString);
            double kilometers = meters / 1000;

            DecimalFormat df = new DecimalFormat("#.##");
            String kilometersFormatted = df.format(kilometers);

            if (kilometersFormatted.endsWith(".0")) {
                kilometersFormatted = kilometersFormatted.substring(0, kilometersFormatted.length() - 2);
            }
            return kilometersFormatted;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static List<List<Integer>> threeNums(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            int third = n - 1;
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[first]);
                    tempList.add(nums[second]);
                    tempList.add(nums[third]);
                    result.add(tempList);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> fourNums(int[] nums, int target) {
        if (nums == null || nums.length < 4) return new ArrayList<>();
        int length = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int first = 0; first < length - 3; first++) {
            if (first > 0 && nums[first] == nums[first-1]) continue;
            if ((long) nums[first] + nums[first+1] + nums[first+2] + nums[first+3] > target) break;
            if ((long) nums[first] + nums[length - 1] + nums[length - 2] + nums[length-3] < target) continue;
            for (int second = first+1; second < length - 2; second++) {
                if (second > first + 1 && nums[second] == nums[second-1]) continue;
                if ((long) nums[first] + nums[second] + nums[second+1] + nums[second+2] > target) break;
                if ((long) nums[first] + nums[second] + nums[length - 1] + nums[length-2] < target) continue;
                int third = second + 1;
                int four = length - 1;
                while (third < four) {
                    long sum = nums[first] + nums[second] + nums[third] + nums[four];
                    if (sum == target) {
                        List<Integer> tempList = Arrays.asList(nums[first], nums[second], nums[third], nums[four]);
                        result.add(tempList);
                        while (third < four && nums[third] == nums[third + 1]) third++;
                        third++;
                        while (third < four && nums[four] == nums[four - 1]) four--;
                        four--;
                    } else if (sum > target) {
                        four--;
                    } else {
                        third++;
                    }
                }
            }
        }
        return result;
    }

public static class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 != null) return l2;
        if (l1 != null && l2 == null) return l1;
        if (l1 == null && l2 == null) return null;
        String l1Str = String.valueOf(l1.val);
        String l2Str = String.valueOf(l2.val);
        while (l1.next != null) {
            ListNode tempNode = l1.next;
            l1Str = tempNode.val + l1Str;
            l1 = l1.next;
        }
        while (l2.next != null) {
            ListNode tempNode = l2.next;
            l2Str = tempNode.val + l2Str;
            l2 = l2.next;
        }
        BigDecimal l1Long = new BigDecimal(l1Str);
        BigDecimal l2Long = new BigDecimal(l2Str);
        BigDecimal l3Long = l1Long.add(l2Long);
        String l3Str = String.valueOf(l3Long);
        char[] charArray = l3Str.toCharArray();
        int l3Int = charArray[charArray.length - 1] - '0';
        ListNode l3 = new ListNode(l3Int);
        ListNode head = l3;
        for (int i = charArray.length - 2; i >= 0; i--) {
            l3.next = new ListNode(charArray[i] - '0');
            l3 = l3.next;
        }
        return head;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static int removeElement(int[] nums, int val) {
        PrimitiveIterator.OfInt iterator = Arrays.stream(nums).iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.intValue() != val) {
                iterator.remove();
            }
        }
        return nums.length;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        if (list1 == null && list2 != null) return list2;
        if (list1 != null && list2 == null) return list1;
        ListNode head;
        if (list1.val > list2.val) {
            head = list2;
            list2 = list2.next;
        } else {
            head = list1;
            list1 = list1.next;
        }
        ListNode resultHead = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        while (list1 != null) {
            head.next = list1;
            list1 = list1.next;
            head = head.next;
        }
        while (list2 != null) {
            head.next = list2;
            list2 = list2.next;
            head = head.next;
        }
        return resultHead;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    public int maxSubArray2(int[] nums) {
        // dp[n] = dp[n-1] + nums[i], dp[n-1] >= 0;
        // dp[n] = nums[i], dp[n-1] < 0;
        // dp[n] = Max(dp[n-1] + nums[i], nums[i]);

        int len = nums.length;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        int res = dp[0];
        for (int i = 1; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSubArray3(int[] nums) {
        // dp[i] = dp[i-1] + nums[i]; dp[i-1] >= 0;
        // dp[i] = nums[i]; dp[i-1] < 0
        // dp[i] = Max(dp[i-1] + nums[i], nums[i]);
        int len = nums.length;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i-1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode pos = head;
        Set<ListNode> posSet = new HashSet<>();
        while (pos != null) {
            if (posSet.contains(pos)) {
                return pos;
            } else {
                posSet.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length <= 0) return -1;
        if (target <= nums[0]) return 0;
        if (target > nums[nums.length - 1]) return nums.length;
        if (target == nums[nums.length - 1]) return nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (nums[i] > target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int searchInsert3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p+1] = nums[q];
                p++;
            }
            q++;
        }
        return p++;
    }

    public boolean canJump(int[] nums) {
        int length = nums.length;
        int maxRight = 0;
        for (int i = 0; i < length; i++) {
            if (i > maxRight) break;
            maxRight = Math.max(maxRight, i + nums[i]);
            if (maxRight >= length - 1) {
                return true;
            }
        }
        return false;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                ansList.add(interval);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] result = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            result[i] = ansList.get(i);
        }
        return result;
    }

    public void reverseString(char[] s) {
        int length = s.length;
        int left = 0, right = length - 1;
        while (left < right) {
            char tep = s[left];
            s[left] = s[right];
            s[right] = tep;
            left++;
            right--;
        }
    }

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int length = arr.length;
        int left = 0, right = length - 1;

        while (left < right) {
            while (left < length && !isVowels(arr[left])) {
                ++left;
            }
            while (right > 0 && !isVowels(arr[right])) {
                --right;
            }
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                ++left;
                --right;
            }
        }
        return new String(arr);
    }

    private boolean isVowels(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }


    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        return interSectionProcess(set1, set2);
    }

    private int[] interSectionProcess(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return interSectionProcess(set2, set1);
        }
        Set<Integer> interSectionSet = new HashSet<>();
        for (Integer s1 : set1) {
            if (set2.contains(s1)) {
                interSectionSet.add(s1);
            }
        }
        int[] res = new int[interSectionSet.size()];
        int index = 0;
        for (Integer s : interSectionSet) {
            res[index++] = s;
        }
        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            int key = entry.getKey();
            int val = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < val) {
                    queue.poll();
                    queue.offer(new int[]{key, val});
                }
            } else {
                queue.offer(new int[]{key, val});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        Set<Integer> resList = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    resList.add(nums1[i]);
                }
            }
        }
        int[] res = new int[resList.size()];
        int index = 0;
        for (Integer set : resList) {
            res[index++] = set;
        }
        return res;
    }

    public int countNumbersWithUniqueDigits(int n) {
        // n = 0, [0,1) -> 1
        // n = 1, [0,9] -> 10
        // n = 2, [0,100) -> 十位：[1,9]-》9，个位：[0, 9]（要去除和十位相同的） -> 9，再加上只有一位数的情况: [0,9] -> 10 ：9x9 + 10 = 91
        // n = 3, [0,1000) -> 百位：[1,9] -> 9, 十位：[0,9]（要去除和百位相同的） -> 9, 个位：[0,9](要去除和百位、十位相同的) -> 8， 再加上只有两位数 和 只有一位数的情况

        if (n == 0)
            return 1;
        if (n == 1)
            return 10;

        int cur = 9;
        int res = 10;

        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }

        return res;
    }

    public int countNumbersWithUniqueDigits2(int n) {
        // n = 0, [0,1) -> 1
        // n = 1, [0,9] -> 10
        // n = 2, [0,100) -> 十位：[1,9] -> 9, 个位：[0,9]（去除和十位相同的）-> 9, 再加上只有一位数的情况：[0,9] -> 10 ————》 9x9+10 = 91
        // n = 3, [0,1000) -> 百位：[1,9] -> 9, 十位：[0,9]（去除和百位相同的）-> 9, 个位：[0,9] (要去除和百位、十位相同的) -> 8，再加上只有两位数和只有一位数的情况 ——》 9x9x8 + 9x9+10 + 10
        // n = 4, [0,10000) -> 千位：[1,9] -> 9, 百位：[0,9]（去除和千位相同的）-> 9, 十位：[0,9]（去除和千位、百位相同的）-> 8, 个位：[0,9]（去除和千位、百位、十位相同的）-> 7, 再加上只有三位数、两位数、一位数的情况 ——》 9x9x8x7 + 9x9x8+9x9+10 + 10
        if (n == 0)
            return 1;
        if (n == 1)
            return 10;
        int cur = 9, res = 10;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }

    /**
     * 使用 TreeSet 来寻找最接近 k 的区域和：
     * 在这一步中，TreeSet 被用来存储历史累加和。为了找到最大的区域和，我们需要找到一个最小的 x，使得 s - x <= k，
     * 其中 s 是当前的累加和。这可以通过 TreeSet 的 ceiling 方法实现，它可以找到集合中大于或等于给定元素的最小元素。
     *
     * 这里的 Integer ceil = sumSet.ceiling(s - k); 这行代码的作用是：在 sumSet 中找到一个最小的数 ceil，使得 ceil >= s - k。
     * 如果这样的 ceil 存在，那么 s - ceil 就是一个候选的区域和，它是小于或等于 k 的。我们需要检查这个候选值是否比当前的最大值 ans 更大，如果是，就更新 ans。
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }

    public int maxSumSubMatrix2(int[][] matrix, int k) {
        int result = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                for (int c = 0; c < n; c++) {
                    sum[c] += matrix[j][c];
                }
                TreeSet<Integer> sumSet = new TreeSet<>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceiling = sumSet.ceiling(s - k);
                    if (ceiling != null) {
                        result = Integer.max(result, s - ceiling);
                    }
                    sumSet.add(s);
                }
            }
        }
        return result;
    }

    public static boolean wordPattern(String pattern, String s) {
        if (pattern == null || "".equals(pattern) || s == null || "".equals(s)) {
            return false;
        }
        String[] split = s.split("\\s");
        if (pattern.length() != split.length) {
            return false;
        }
        Map<Character, Set<Integer>> map = new HashMap<>();
        char[] charArray = pattern.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            Set<Integer> integers = map.get(charArray[i]);
            if (integers == null || integers.size() == 0) {
                integers = new HashSet<>();
            }
            integers.add(i);
            map.put(charArray[i], integers);
        }

        Collection<Set<Integer>> values = map.values();

        for (Set<Integer> sets : values) {
            String temp = null;
            for (Integer si : sets) {

                if (temp == null) {
                    temp = split[si];
                } else {
                    if (!temp.equals(split[si])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean wordPattern2(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }

    public boolean wordPattern3(String pattern, String str) {

        Map<String, Character> str2ChMap = new HashMap<>();
        Map<Character, String> ch2StrMap = new HashMap<>();
        int m = str.length();
        int left = 0;
        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern.charAt(i);
            if (left >= m) {
                return false;
            }
            int right = left;
            while (right < m && str.charAt(right) != ' ') {
                right++;
            }
            String word = str.substring(left, right);
            if (str2ChMap.containsKey(word) && ch != str2ChMap.get(word)) {
                return false;
            }
            if (ch2StrMap.containsKey(ch) && !word.equals(ch2StrMap.get(ch))) {
                return false;
            }
            str2ChMap.put(word, ch);
            ch2StrMap.put(ch, word);
            left = right + 1;
        }
        return left >= m;
    }

    class Solution1 {
        public boolean wordPatternMatch(String pattern, String s) {
            return isMatch(s, 0, pattern, 0, new HashMap<>(), new HashMap<>());
        }

        private boolean isMatch(String s, int i, String p, int j, Map<Character, String> c2s, Map<String, Character> s2c) {
            // 如果pattern和s都匹配完了
            if (i == s.length() && j == p.length()) return true;
            // 如果任意一个匹配完了，但另一个没有
            if (i == s.length() || j == p.length()) return false;

            char c = p.charAt(j);
            // 如果pattern的当前字符已经有映射
            if (c2s.containsKey(c)) {
                String str = c2s.get(c);
                // 检查s的剩余部分是否以这个映射开始
                if (!s.startsWith(str, i)) return false;
                // 如果是，继续检查剩下的部分
                return isMatch(s, i + str.length(), p, j + 1, c2s, s2c);
            }

            // 尝试所有可能的映射
            for (int k = i; k < s.length(); k++) {
                String part = s.substring(i, k + 1);
                // 如果这个子串已经被映射了，跳过
                if (s2c.containsKey(part)) continue;

                // 创建新映射
                c2s.put(c, part);
                s2c.put(part, c);

                // 递归检查剩下的部分
                if (isMatch(s, k + 1, p, j + 1, c2s, s2c)) return true;

                // 如果这个映射不行，撤销它
                c2s.remove(c);
                s2c.remove(part);
            }

            return false;
        }
    }

    public boolean wordPatternMatch(String pattern, String s) {
        return isMathch(s, 0, pattern, 0, new HashMap<Character, String>(), new HashMap<String, Character>());
    }

    private boolean isMathch(String s, int i, String pattern, int j, HashMap<Character, String> c2s, HashMap<String, Character> s2c) {
        if (i == s.length() && j == pattern.length()) return true;
        if (i == s.length() || j == pattern.length()) return false;
        char ch = pattern.charAt(j);
        if (c2s.containsKey(ch)) {
            String str = c2s.get(ch);
            if (!s.startsWith(str, i)) {
                return false;
            }
            return isMathch(s, i + str.length(), pattern, j + 1, c2s, s2c);
        }
        for (int p = i; p < s.length(); ++p) {
            String sbStr = s.substring(i, p + 1);
            if (s2c.containsKey(sbStr)) continue;
            c2s.put(ch, sbStr);
            s2c.put(sbStr, ch);

            if (isMathch(s, p + 1, pattern, j + 1, c2s, s2c)) {
                return true;
            }
            c2s.remove(ch);
            s2c.remove(sbStr);
        }

        return false;
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    /**
     * 这个问题要求找出所有可能的下一步状态，其中每个状态是通过将当前字符串中连续的两个 "++" 反转为 "--" 生成的。可以通过遍历字符串，检查每一对相邻的字符是否为 "++"，然后进行替换来实现。
     *
     * 在Java中，这可以通过以下步骤实现：
     *
     * 创建一个列表（比如 ArrayList）来存储所有可能的下一步状态。
     * 遍历给定的字符串 currentState，检查每一对相邻的字符。
     * 如果找到 "++"，则将它替换为 "--" 并将结果添加到列表中。
     * 最后返回这个列表。
     */
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < currentState.length() - 1; i++) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                String nextState = currentState.substring(0, i) + "--" + currentState.substring(i + 2);
                result.add(nextState);
            }
        }
        return result;
    }

    public List<String> generatePossibleNextMoves2(String currentState) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < currentState.length() - 1; i++) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                String nextState = currentState.substring(0, i) + "--" + currentState.substring(i + 2);
                result.add(nextState);
            }
        }
        return result;
    }

    /**
     * 这个问题是关于决策树和博弈论的经典问题。它可以通过递归和记忆化搜索（动态规划）来解决。基本思路是检查每一种可能的移动，然后对每一种情况递归地检查对手是否有输的情况。
     * 如果在某一步中，起始玩家的任何一种移动都会导致对手最终输掉游戏，那么起始玩家就存在必胜方案。
     *
     * 为了提高效率，可以使用记忆化搜索来存储已经计算过的中间结果，避免重复计算。这样可以大大减少计算量。
     *
     * 时间复杂度分析
     * 时间复杂度分析主要考虑以下两个因素：
     *
     * 递归深度：最坏情况下，每次只翻转一个 "++"，因此递归的深度大约是字符串中 "++" 对的数量。
     * 状态数量：字符串长度为 n 时，可能的不同状态数量是 2^n（每个位置可以是 '+' 或 '-'）。
     * 然而，由于使用了记忆化，每个状态只计算一次。因此，实际的时间复杂度大致是 O(n * 2^n)，这里 n 是因为对于每个状态，需要遍历整个字符串来寻找 "++" 并尝试翻转。
     *
     * 这个时间复杂度表明算法对于小字符串是有效的，但对于长度非常大的字符串，计算将变得不切实际。
     */
    public class Solution2 {
        Map<String, Boolean> memo = new HashMap<>();

        public boolean canWin(String currentState) {
            if (memo.containsKey(currentState)) {
                return memo.get(currentState);
            }
            for (int i = 0; i < currentState.length() - 1; i++) {
                if (currentState.startsWith("++", i)) {
                    String nextState = currentState.substring(0, i) + "--" + currentState.substring(i + 2);
                    // 如果对手在这种情况下输了，那么当前玩家赢
                    if (!canWin(nextState)) {
                        memo.put(currentState, true);
                        return true;
                    }
                }
            }
            memo.put(currentState, false);
            return false;
        }
    }

    public class Solution3 {

        private Map<String, Boolean> map = new HashMap<>();
        public boolean canWin(String currentState) {
            if (map.containsKey(currentState)) {
                return map.get(currentState);
            }
            for (int i = 0; i < currentState.length() - 1; i++) {
                if (currentState.startsWith("++", i)) {
                    String nextState = currentState.substring(0, i) + "--" + currentState.substring(i + 2);
                    if (!canWin(nextState)) {
                        map.put(currentState, true);
                        return true;
                    }
                }
            }
            map.put(currentState, false);
            return false;
        }
    }

    class MedianFinder {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder() {
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

    class MedianFinder2 {

        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder2() {
            queMin = new PriorityQueue<>((a, b) -> (b - a));
            queMax = new PriorityQueue<>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || queMin.peek() >= num) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMin.size() < queMax.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

    /**
     * 方法一 广度优先搜索[超过时间限制]
     * 一种暴力的方法是评估网格中的所有可能的碰头地点。我们可以从每个点开始应用广度优先搜索。
     * 在将一个点插入队列时，我们需要记录该点到碰头地点的距离。此外，我们还需要一个额外的 visited 表来记录哪个点已经被访问过，以避免再次被插入队列。
     *
     */
    class Solution4 {
        public int minTotalDistance(int[][] grid) {
            int minDistance = Integer.MAX_VALUE;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    int distance = search(grid, row, col);
                    minDistance = Math.min(distance, minDistance);
                }
            }
            return minDistance;
        }

        private int search(int[][] grid, int row, int col) {
            Queue<Point> q = new LinkedList<>();
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            q.add(new Point(row, col, 0));
            int totalDistance = 0;
            while (!q.isEmpty()) {
                Point point = q.poll();
                int r = point.row;
                int c = point.col;
                int d = point.distance;
                if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) {
                    continue;
                }
                if (grid[r][c] == 1) {
                    totalDistance += d;
                }
                visited[r][c] = true;
                q.add(new Point(r + 1, c, d + 1));
                q.add(new Point(r - 1, c, d + 1));
                q.add(new Point(r, c + 1, d + 1));
                q.add(new Point(r, c - 1, d + 1));
            }
            return totalDistance;
        }

        public class Point {
            int row;
            int col;
            int distance;
            public Point(int row, int col, int distance) {
                this.row = row;
                this.col = col;
                this.distance = distance;
            }
        }
    }

    /**
     * 我们可以使用选择算法在 O(mn)O(mn)O(mn) 时间内选择中位数，但有一个更简单的方法 。注意，我们可以按排序的顺序收集行和列坐标。
     */
    class Solution5 {
        public int minTotalDistance(int[][] grid) {
            List<Integer> rows = collectRows(grid);
            List<Integer> cols = collectCols(grid);
            int row = rows.get(rows.size() / 2);
            int col = cols.get(cols.size() / 2);
            return minDistance1D(rows, row) + minDistance1D(cols, col);
        }

        private int minDistance1D(List<Integer> points, int origin) {
            int distance = 0;
            for (int point : points) {
                distance += Math.abs(point - origin);
            }
            return distance;
        }

        private List<Integer> collectRows(int[][] grid) {
            List<Integer> rows = new ArrayList<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col] == 1) {
                        rows.add(row);
                    }
                }
            }
            return rows;
        }

        private List<Integer> collectCols(int[][] grid) {
            List<Integer> cols = new ArrayList<>();
            for (int col = 0; col < grid[0].length; col++) {
                for (int row = 0; row < grid.length; row++) {
                    if (grid[row][col] == 1) {
                        cols.add(col);
                    }
                }
            }
            return cols;
        }
    }

    class Solution6 {
        public int minTotalDistance(int[][] grid) {
            List<Integer> rows = findRows(grid);
            List<Integer> cols = findCols(grid);
            int row = rows.get(rows.size() / 2);
            int col = cols.get(cols.size() / 2);
            return minDistance(rows, row) + minDistance(cols, col);
        }

        private int minDistance(List<Integer> points, int origin) {
            int distance = 0;
            for (Integer point : points) {
                distance += Math.abs(point - origin);
            }
            return distance;
        }

        private List<Integer> findCols(int[][] grid) {
            List<Integer> cols = new ArrayList<>();
            for (int i = 0; i < grid[0].length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[j][i] == 1) {
                        cols.add(i);
                    }
                }
            }
            return cols;
        }

        private List<Integer> findRows(int[][] grid) {
            List<Integer> rows = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                    }
                }
            }
            return rows;
        }
    }

    class Solution7 {

        public int minTotalDistance(int[][] grid) {
            List<Integer> rows = findRows(grid);
            List<Integer> cols = findCols(grid);
            int row = rows.get(rows.size() / 2);
            int col = cols.get(cols.size() / 2);
            return minDistance(rows, row) + minDistance(cols, col);
        }

        private int minDistance(List<Integer> points, int origin) {
            int distance = 0;
            for (Integer point : points) {
                distance += Math.abs(point - origin);
            }
            return distance;
        }

        private List<Integer> findCols(int[][] grid) {
            List<Integer> cols = new ArrayList<>();
            for (int col = 0; col < grid[0].length; col++) {
                for (int row = 0; row < grid.length; row++) {
                    if (grid[row][col] == 1) {
                        cols.add(col);
                    }
                }
            }
            return cols;
        }

        private List<Integer> findRows(int[][] grid) {
            List<Integer> rows = new ArrayList<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col] == 1) {
                        rows.add(row);
                    }
                }
            }
            return rows;
        }
    }

    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     *
     * 方法一：深度优先搜索
     * 思路和算法
     *
     * 二叉树的序列化本质上是对其值进行编码，更重要的是对其结构进行编码。可以遍历树来完成上述任务。众所周知，我们一般有两个策略：广度优先搜索和深度优先搜索。
     *
     * 广度优先搜索可以按照层次的顺序从上到下遍历所有的节点
     * 深度优先搜索可以从一个根开始，一直延伸到某个叶，然后回到根，到达另一个分支。根据根节点、左节点和右节点之间的相对顺序，可以进一步将深度优先搜索策略区分为：
     * 先序遍历
     * 中序遍历
     * 后序遍历
     */
    public class Codec {
        public class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode(int x) { val = x; }
          }
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode deserialize(String data) {
            String[] dataArray = data.split(",");
            List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public TreeNode rdeserialize(List<String> dataList) {
            if (dataList.get(0).equals("None")) {
                dataList.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);

            return root;
        }
    }

    public class Codec2 {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            public TreeNode(int val) {
                this.val = val;
            }
        }

        public String serialize(TreeNode root) {
            return reserialize(root, "");
        }

        public String reserialize(TreeNode root, String data) {
            if (root == null) {
                return data + "None,";
            }
            data += String.valueOf(root.val) + ",";
            data = reserialize(root.left, data);
            data = reserialize(root.right, data);
            return data;
        }

        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            List<String> list = new LinkedList<>(Arrays.asList(split));
            return redeserialize(list);
        }

        private TreeNode redeserialize(List<String> list) {
            if (list.get(0).equals("None")) {
                list.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
            root.left = redeserialize(list);
            root.right = redeserialize(list);
            return root;
        }
    }

    public class Codec3 {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            public TreeNode(int val) {
                this.val = val;
            }
        }
        public String serialize(TreeNode root) {
            if (root == null) {
                return "X";
            }
            String left = "(" + serialize(root.left) + ")";
            String right = "(" + serialize(root.right) + ")";
            return left + root.val + right;
        }

        public TreeNode deserialize(String data) {
            int[] ptr = {0};
            return parse(data, ptr);
        }

        public TreeNode parse(String data, int[] ptr) {
            if (data.charAt(ptr[0]) == 'X') {
                ++ptr[0];
                return null;
            }
            TreeNode cur = new TreeNode(0);
            cur.left = parseSubtree(data, ptr);
            cur.val = parseInt(data, ptr);
            cur.right = parseSubtree(data, ptr);
            return cur;
        }

        public TreeNode parseSubtree(String data, int[] ptr) {
            ++ptr[0]; // 跳过左括号
            TreeNode subtree = parse(data, ptr);
            ++ptr[0]; // 跳过右括号
            return subtree;
        }

        public int parseInt(String data, int[] ptr) {
            int x = 0, sgn = 1;
            if (!Character.isDigit(data.charAt(ptr[0]))) {
                sgn = -1;
                ++ptr[0];
            }
            while (Character.isDigit(data.charAt(ptr[0]))) {
                x = x * 10 + data.charAt(ptr[0]++) - '0';
            }
            return x * sgn;
        }
    }

}
