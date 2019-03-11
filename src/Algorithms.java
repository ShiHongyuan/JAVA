import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.ir.IdentNode;

import java.security.Key;
import java.time.Instant;
import java.util.*;

public class Algorithms {

    /**
     * jsonDeep
     */
    public class JsonDeep {
        private JSONObject json;

        public JSONObject getJson() {
            return json;
        }
        public void setJson(JSONObject json) {
            this.json = json;
        }

        public String getJsonString(){
            System.out.println(json.toString());
            System.out.println(json.toJSONString());
//        JsonArray jsonArray = new JsonArray();
            return json.toString();
        }

        public JsonDeep(String jsonString) {
            super();
            jsonString = "{'1':1,{'2':2},'22':22}}";
            json = (JSONObject)JSONObject.parse(jsonString);
        }
    }


    public void jsonDeep() {
//        JsonDeep jsonDeep = new JsonDeep("{'1':1,'11':11,{'2':2,'22':[22,22]},{'222':222,{'3':3,{'4':4},'33':33}}}");
//        JsonDeep jsonDeep = new JsonDeep("{'1':1,{'2':2,{{'4':4},'3':3},'22':22}}");
//        StringBuilder jsonString = new StringBuilder(jsonDeep.getJsonString());
        String s1 = "{'1':1,{'2':2,{{'4':4},'3':3},'22':22}}";
        String s2 = "{'1':1,{'2':2},{'22':22}}";
        String s3 = "{'1':1,'11':11,{'2':2,'22':[22,22]},{'222':222,{'3':3,{'4':4},'33':33}}}";
        StringBuilder jsonString = new StringBuilder(s3);
        int len = jsonString.length();
        if (len > 0) {
            int num = jsonDeep(0, len, jsonString);
            System.out.println(num);
        }

    }
    private int jsonDeep(int start, int len, StringBuilder jsonString) {
        if (start >= len) {
            return 0;
        }

        if (start == len - 1) {
            return 1;
        }

        char key = jsonString.charAt(start);
        if (key == '}') {
            int max = 0;
            int index = jsonString.substring(start).indexOf("{");
            if (index != -1) {
                int point = start;
                while (point != (index + start)) {
                    if (jsonString.charAt(point) == '}') {
                        max++;
                    }
                    point++;
                }
                while (index != -1) {
                    Stack<String> stack = new Stack<String>();
                    stack.push("{");
                    index = index + start;
                    int end = index + 1;
                    while (!stack.empty()) {
                        if (jsonString.charAt(end) == '{') {
                            stack.push("{");
                        } else if (jsonString.charAt(end) == '}') {
                            stack.pop();
                        }
                        end++;
                    }
                    int b = jsonDeep(index, end, jsonString);
                    if (max < b) {
                        max = b;
                    }
                    index = jsonString.substring(end).indexOf("{");
                    start = end;
                }
                return max + jsonDeep(start, len, jsonString);
            }
            else {
                return 1 + jsonDeep(start + 1, len, jsonString);
            }
        }
        return jsonDeep(start + 1, len, jsonString);
    }

    /**
     * 二分查找
     */
    public void binarySearch() {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10};
        int key = 2;
        int index = this.binarySearch(a, key);
        System.out.println("二分查找---------------------");
        System.out.println(index);
    }

    private int binarySearch(int[] a, int key) {
        int index = -1;
        int left = 0;
        int right = a.length;
        while (left <= right) {
            int pivot = (left + right) / 2;
            if (a[pivot] == key) {
                index = pivot;
                break;
            }
            else if (a[pivot] < key){
                left = pivot + 1;
            }
            else {
                right = pivot - 1;
            }
        }
        return index;
    }

    /**
     * 快速排序
     */
    public void quickSort() {
        int[] a = {10,2,3,9,4,8,5,7,6,1};
        this.quickSort(a, 0, a.length - 1);
        System.out.println("快速排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = (left + right) / 2;
        int key = a[pivot];
        a[pivot] = a[left];
        a[left] = key;

        int i = left + 1;
        int j = right;
        while ( i <= j) {
            while (i <= right && a[i] <= key) {
                i++;
            }
            while (j >= left + 1 && a[j] >= key) {
                j--;
            }
            if (i > j) {
                break;
            }
            else {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        int tmp = a[j];
        a[j] = a[left];
        a[left] = tmp;

        quickSort(a, left, j - 1);
        quickSort(a, j + 1, right);

//        int index = start;
//        for (int i = start + 1; i <= end; i++) {
//            if (a[i] < key) {
//                int tmp = a[index + 1];
//                a[index + 1] = a[i];
//                a[i] = tmp;
//                tmp = a[index + 1];
//                a[index + 1] = a[index];
//                a[index] = tmp;
//                index++;
//            }
//        }
//        quickSort(a, start, index - 1);
//        quickSort(a, index + 1, end);
    }


    /**
     * 插入排序
     */
    public void insertSort() {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.insertSort(a);
        System.out.println("插入排序---------------------");
        System.out.println(Arrays.toString(a));

    }

    private void insertSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        else {
            // JAVA集合只能存放引用类型的的数据，不能存放基本数据类型
            List<Integer> b = new ArrayList<Integer>();
            for (int key : a) {
                int size = b.size();
                if (size == 0) {
                    b.add(key);
                    continue;
                }
                int i = 0;
                while (i < size) {
                    if (key <= b.get(i)) {
                        b.add(i, key);
                        break;
                    }
                    i++;
                }
                if (i == size) {
                    b.add(key);
                }
            }
            for (int i = 0; i < b.size(); i++) {
                a[i] = b.get(i);
            }
        }
    }


    /**
     * 简单选择排序 （每一轮选最小或最大的，在一轮的最后交换）
     */
    public void selectSort () {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.selectSort(a);
        System.out.println("选择排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void selectSort (int[] a) {
        if (a.length <= 1) {
            return;
        }
        int minIndex;
        for (int i = 0; i < a.length; i++) {
            minIndex = i;
            for (int j = i; j < a.length; j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }

    /**
     * 冒泡排序 （两两比较，就交换）
     */
    public void bubbleSort () {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.bubbleSort(a);
        System.out.println("冒泡排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void bubbleSort (int[] a) {
        if (a.length <= 1) {
            return;
        }
        int j = 1;
        while (j <= a.length - 1) {
            for (int i = 0; i < a.length - j; i++) {
                if (a[i] > a[i+1]) {
                    int tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;
                }
            }
            j++;
        }
    }

    /**
     * 归并排序
     */
    public void mergeSort() {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.mergeSort(a, 0, a.length - 1);
        System.out.println("归并排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void mergeSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = (left + right) / 2;
        mergeSort(a, left, pivot);
        mergeSort(a, pivot + 1, right);
        int[] b = merge(a, pivot, left, right);
        copy(a, b, left, right);
    }

    private int[] merge(int[] a, int pivort, int left, int right) {
        int[] b = new int[right - left + 1];
        int i = left;
        int j = pivort + 1;
        int index = 0;
        while (i <= pivort && j <= right) {
            if (a[i] < a[j]) {
                b[index++] = a[i];
                i++;
            }
            else {
                b[index++] = a[j];
                j++;
            }
        }
        if (i <= pivort) {
            while (i <= pivort) {
                b[index++] = a[i];
                i++;
            }
        }
        else if (j <= right) {
            while (j <= right) {
                b[index++] = a[j];
                j++;
            }
        }
        return b;
    }

    private void copy(int[] a, int[] b, int left, int right) {
        int j = 0;
        for (int i = left; i <= right; i++) {
            a[i] = b[j++];
        }
    }


    /**
     * 堆排序
     */
    public void heapSort() {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.heapSort(a);
        System.out.println("堆排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void heapSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        // 堆，数组从0开始，父节点为k/2，子节点是2k，2k+1
        // 堆，数组从1开始，父节点为（k/2 - 1），子节点是2k+1，2k+2
        // 构建最大堆，从最后一个非叶子节点开始，到最上层的根节点，每个节点去下沉到对应的位置，能构建一个最大堆
        for (int i = a.length/2 - 1; i >= 0; i--) {
            adjustHeap(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            // 最大堆的根节点是最大的，和数组最后一个数交换位置，在未排序范围内，下沉交换后的第一个数，完成后根节点又是最大的了，又交换，又下沉，n-1次后就排好序了
            int tmp = a[i];
            a[i] = a[0];
            a[0] = tmp;
            adjustHeap(a, 0, i);
        }

    }

    private void adjustHeap(int[] a, int key, int len) {
        int tmp = a[key];
        for (int i = 2 * key + 1; i < len; i = 2 * key + 1) {
            if (i + 1 < len && a[i] < a[i+1]) {
                i++;
            }
            if (tmp < a[i]) {
                a[key] = a[i];
                a[i] = tmp;
                key = i;
            }
            else {
                break;
            }
        }

    }

    /**
     * 计数排序（稳定排序）  适合的范围并不广，适合待排元素值分布较连续，跨度较小的情况，否则很浪费空间
     */
    public void countSort() {
        int[] a = new int[]{15,1,6,4,5,2,2,9,10,12};
        this.countSort(a);
        System.out.println("计数排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void countSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        // 找出最大最小值
        int max = a[0];
        int min = a[0];
        for (int i: a) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }
        // 新数组记录每个值出现的个数
        int[] count = new int[max - min + 1];
        for (int i: a) {
            count[i - min]++;
        }
        // 新数组记录每个值出现的起始位置
        int[] index = new int[max - min + 1];
        index[0] = 0;
        for (int i = 1; i < count.length; i++) {
            index[i] = index[i-1] + count[i-1];
        }
        // 新数组按顺序装值
        int[] tmp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            tmp[index[a[i] - min]] = a[i];
            index[a[i] - min]++;
        }
        // 新数组覆盖原数组
        for (int i = 0; i < tmp.length; i++) {
            a[i] = tmp[i];
        }
    }

    /**
     * 桶排序
     * 桶个数：arr.length
     * 映射函数：bucketIndex = (value * arr.length) / (maxValue + 1)
     */
    public void bucketSort() {
        int[] a = new int[]{15,1,6,4,5,2,2,9,10,12};
        this.bucketSort(a);
        System.out.println("桶排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void bucketSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        // 找出最大值
        int max = a[0];
        for (int i: a) {
            if (max < i) {
                max = i;
            }
        }
        // 把待排元素分布到不同的桶里
        List<Integer>[] buckets = new ArrayList[a.length];
        for (int i: a) {
            int bucketIndex = (i * a.length) / (max + 1);
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<Integer>();
            }
            buckets[bucketIndex].add(i);
        }
        // 每个桶内部进行排序
        for (List<Integer> bucket: buckets) {
            if (bucket != null) {
                bucket.sort(null);
            }
//            int[] tmp = new int[bucket.size()];
//            for (int i = 0; i < bucket.size(); i++) {
//                tmp[i] = bucket.get(i);
//            }
//            quickSort(tmp, 0, tmp.length - 1);
        }
        // 连接每个桶的数据
        int cnt = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    a[cnt++] = buckets[i].get(j);
                }
            }
        }
    }


    /**
     * 基数排序
     * 字符串由若干字符组成，整数由若干位0~9数字组成
     * 每一轮排序中排序方法必须是稳定的。否则基数排序不能得到正确的结果，所以基数排序是稳定排序。
     * 基数是什么意思，对于十进制整数，每一位都只可能是0~9中的某一个，总共10种可能。那10就是它的基，同理二进制数字的基为2；对于字符串，如果它使用的是8位的扩展ASCII字符集，那么它的基就是256
     * 若字符串使用的是8位的ASCII扩展字符集，则基的大小是256，基于基数排序的字符串排序方法称为低位优先的字符串排序
     * 既然知道每一位的数值范围，使用计数排序以关键字对数组进行排序是个明智的选择：
     *      因为数据范围确定且都不大（基的大小），因此不会占用多少空间；
     *      而且计数排序不是基于比较，比通常的比较排序方法效率更高；
     *      计数排序是稳定排序，这一点至关重要。
     */


   // import java.util.Arrays;

    public void RadixSort() {
        int[] a = {244, 167, 1234, 321, 29, 98, 1444, 111, 99, 6};
        RadixSort(a);
        System.out.println("基数排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    public void RadixSort(int[] a) {
        // 每位数字范围0~9，基为10
        int R = 10;
        int N = a.length;
        int[] aux = new int[N];
        int[] count = new int[R + 1];
        // 以关键字来排序的轮数，由位数最多的数字决定，其余位数少的数字在比较高位时，自动用0进行比较
        // 将数字转换成字符串，字符串的长度就是数字的位数，字符串最长的那个数字也拥有最多的位数
        int W = Arrays.stream(a).map(s -> String.valueOf(s).length()).max().getAsInt();

        // 共需要d轮计数排序, 从d = 0开始，说明是从个位开始比较，符合从右到左的顺序
        for (int d = 0; d < W; d++) {
            // 1. 计算频率，在需要的数组长度上额外加1
            for (int i = 0; i < N; i++) {
                // 使用加1后的索引，有重复的该位置就自增
                count[digitAt(a[i], d) + 1]++;
            }
            // 2. 频率 -> 元素的开始索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // 3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
            for (int i = 0; i < N; i++) {
                // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
                aux[count[digitAt(a[i], d)]++] = a[i];
            }
            // 4. 数据回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
            // 重置count[]，以便下一轮统计使用
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }

        }
    }
    // 根据d，获取某个值的个位、十位、百位等，d = 0取出个位，d = 1取出十位，以此类推。对于不存在的高位，用0补
    private int digitAt(int value, int d) {
        return (value / (int) Math.pow(10, d)) % 10;
    }



    /**
     * 希尔shell排序
     * 设待排序元素序列有n个元素，首先取一个整数increment（小于n）作为间隔将全部元素分为increment个子序列，
     * 所有距离为increment的元素放在同一个子序列中，在每一个子序列中分别实行直接插入排序。然后缩小间隔increment，
     * 重复上述子序列划分和排序工作。直到最后取increment=1，将所有元素放在同一个子序列中排序为止。
     * 希尔排序是一种不稳定的排序算法
     *
     * 最初shell提出取increment=n/2向下取整，increment=increment/2向下取整，直到increment=1。但由于直到最后一步，
     * 在奇数位置的元素才会与偶数位置的元素进行比较，这样使用这个序列的效率会很低。
     * 后来Knuth提出取increment=n/3向下取整+1.还有人提出都取奇数为好，也有人提出increment互质为好
     */
    public void shellSort() {
        int[] a = {244, 167, 1234, 321, 29, 98, 1444, 111, 99, 6};
        shellSort(a);
        System.out.println("希尔排序---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void shellSort(int[] a) {
        if (a.length <=1) {
            return;
        }
        int increment = a.length;
        do {
            increment = increment / 3 + 1;
            for (int i = increment; i < a.length; i++) {
                if (a[i] < a[i - increment]) {
                    int tmp = a[i];
                    a[i] = a[i - increment];
                    int j = i - increment;
                    while (j >= 0 && a[j] > tmp) {
                        a[j + increment] = a[j];
                        j -= increment;
                    }
                    a[j + increment] = tmp;

                }
            }

        } while (increment > 1);
    }



    /**
     * 活动调用问题（贪心算法）
     */
    public void greedy() {
        int[] activity = {1, 2, 3, 4, 5, 6, 7};
        int[] sTime = {0, 1, 2, 4, 3, 6, 8};  // 活动编号分别是1，2，3，4，5，6，7
        int[] fTime = {1, 3, 4, 7, 8, 8, 10}; // 活动编号分别是1，2，3，4，5，6，7
        int start = 0;
        int finish = 0;
        List<Integer> activeList = greedy(activity, sTime, fTime, start, finish);
        System.out.println("活动调用（贪心算法）---------------------");
        for (int i = 0; i < activeList.size(); i++) {
            System.out.println(activeList.get(i));  // 正确答案应该是 1，2，4，7
        }
    }
    private  List<Integer> greedy(int[] activity, int[] sTime, int[] fTime, int start, int finish) {
        List<Integer> activeList = new ArrayList<>();
        this.greedyQuickSort(fTime, 0, fTime.length - 1, sTime, activity);
        int finishIndex = start;
        System.out.println("ftime---------------");
        System.out.println(Arrays.toString(fTime));
        System.out.println("stime---------------");
        System.out.println(Arrays.toString(sTime));
        System.out.println("activity---------------");
        System.out.println(Arrays.toString(activity));
        for (int i = 0; i < fTime.length; i++) {
            if (sTime[i] >= finishIndex) {
                activeList.add(activity[i]);
                finishIndex = fTime[i];
            }
        }
        return activeList;
    }

    private void greedyQuickSort(int[] fTime, int left, int right, int[] sTime, int[] activity) {
        if (left >= right) {
            return;
        }
        int pivot = (left + right) / 2;
        int key = fTime[pivot];
        fTime[pivot] = fTime[left];
        fTime[left] = key;
        int key1 = sTime[pivot];
        sTime[pivot] = sTime[left];
        sTime[left] = key1;
        int key2 = activity[pivot];
        activity[pivot] = activity[left];
        activity[left] = key2;

        int i = left + 1;
        int j = right;
        while ( i <= j) {
            while (i <= right && fTime[i] <= key) {
                i++;
            }
            while (j >= left + 1 && fTime[j] >= key) {
                j--;
            }
            if (i > j) {
                break;
            }
            else {
                int tmp = fTime[i];
                fTime[i] = fTime[j];
                fTime[j] = tmp;
                int tmp1 = sTime[i];
                sTime[i] = sTime[j];
                sTime[j] = tmp1;
                int tmp2 = activity[i];
                activity[i] = activity[j];
                activity[j] = tmp2;

            }
        }
        int tmp = fTime[j];
        fTime[j] = fTime[left];
        fTime[left] = tmp;
        int tmp1 = sTime[j];
        sTime[j] = sTime[left];
        sTime[left] = tmp1;
        int tmp2 = activity[j];
        activity[j] = activity[left];
        activity[left] = tmp2;

        greedyQuickSort(fTime, left, j - 1, sTime, activity);
        greedyQuickSort(fTime, j + 1, right, sTime, activity);


    }

    /**
     * 和为0的最长子串
     */
    public void sumZeroLongestSubstring () {
        int[] a = {1, 1, -1, -1, 1, 2, -2};
        int[] ret = sumZeroLongestSubstring(a);
        System.out.println("和为0的最长子串---------------------");
        System.out.println(ret[0]);
        System.out.println(ret[1]);
        System.out.println(ret[1] - ret[0]);
    }

    private int[] sumZeroLongestSubstring (int[] a) {
        if (a.length <= 1) {
            return new int[] {0, 0};
        }
        int[] b = new int[a.length];
        int maxValue = 0;
        for (int i = 0; i < a.length; i++) {
            if(i == 0) {
                b[i] = a[i];
                maxValue = Math.abs(a[i]);
            }
            else {
                b[i] = b[i-1] + a[i];
                if (maxValue < Math.abs(b[i])) {
                    maxValue = Math.abs(b[i]);
                }
            }
        }
        System.out.println(maxValue);
        int[] start = new int[2 * maxValue + 1];
        int[] end = new int[2 * maxValue + 1];

        for (int i = 0; i < b.length; i++) {
            start[i] = -1;
            end[i] = -1;
        }

        int dest = 0;
        int[] ret = new int[2];
        for (int i = 0; i < b.length; i++) {
            end[maxValue + b[i]] = i;
            if (start[maxValue + b[i]] == -1) {
                start[maxValue + b[i]] = i;
            }
            if (dest < end[maxValue + b[i]] - start[maxValue + b[i]]) {
                dest = end[maxValue + b[i]] - start[maxValue + b[i]];
                ret[0] = start[maxValue + b[i]];
                ret[1] = end[maxValue + b[i]];
            }
        }
        return ret;
    }

    /**
     * 最长公共子序列（动态规划）
     * 动态规划：在一定约束条件下优化某种指标，可分解为离散的子问题，不同子问题的子子问题一样，不同子问题依赖相同的子子问题
     * 分治法：可分解为离散独立的子问题，不同子问题的子子问题不一样，不同子问题不依赖相同的子子问题
     * 动态规划和分治法都用递归
     */
    public void longestSubsequence() {
        String s1 = "HELLO";
        String s2 = "UHOLSY";
        List<String> subString = longestSubsequence(s1, s2);
        System.out.println("最长公共子序列---------------------");
        System.out.println(subString.toString());
        System.out.println(Arrays.toString(subString.toArray()));
    }

    public List<String> longestSubsequence(String s1, String s2) {
        List<String> subString = new ArrayList<>();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] middleRet = new int[c1.length + 1][c2.length + 1];
        int max = 0;
        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                if (c1[i] == c2[j]) {
                    middleRet[i + 1][j + 1] = middleRet[i][j] + 1;
                    if (max < middleRet[i + 1][j + 1]) {
                        max = middleRet[i + 1][j + 1];
                        subString.add(String.valueOf(c1[i]));
                    }
                }
                else if (middleRet[i][j + 1] > middleRet[i + 1][j]) {
                    middleRet[i + 1][j + 1] = middleRet[i][j + 1];
                }
                else {
                    middleRet[i + 1][j + 1] = middleRet[i + 1][j];
                }
            }
        }
        return subString;
    }


    /**
     * 最长公共子串（动态规划）
     */
    public void longestSubstring() {
        String s1 = "HELLO";
        String s2 = "UHOLSY";
        List<String> subString = longestSubstring(s1, s2);
        System.out.println("最长公共子串---------------------");
        System.out.println(subString.toString());
        System.out.println(Arrays.toString(subString.toArray()));
    }

    public List<String> longestSubstring(String s1, String s2) {
        List<String> subString = new ArrayList<>();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] middleRet = new int[c1.length + 1][c2.length + 1];
        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                if (c1[i] == c2[j]) {
                    middleRet[i + 1][j + 1] = middleRet[i][j] + 1;
                    if (subString.size() == 0) {
                        subString.add(String.valueOf(c1[i]));
                    }
                    else if (c1[i - 1] == c2[j - 1]) {
                        subString.add(String.valueOf(c1[i]));
                    }
                }
                else {
                    middleRet[i + 1][j + 1] = 0;
                }
            }
        }
        return subString;
    }

    /**
     * 广度优先BFS（用一个二维数组来表示有向无权图，1表示有指向，0表示没有指向，起点假设为0点，终点假设为6点，搜索从0点到6点的最短距离）
     */
    public void breadFirstSearch() {
        int[][] graph = {{0,1,1,0,0,0,0},{0,0,0,0,1,0,0},{0,0,0,1,0,1,0},{0,0,0,0,1,0,0},{0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0},{0,0,0,0,0,0,0}};
        int start = 0;
        int end = 6;
        List<Integer> shortestPath = breadFirstSearch(graph, start, end);
        System.out.println("广度优先搜索（有向无权图）---------------------");
        System.out.println(shortestPath.toString()); // 结果是 0 -> 1 -> 4 -> 6
    }

    private List<Integer> breadFirstSearch(int[][] graph, int start, int end) {
        List<Integer> shortestPath = new ArrayList<>();
        if (graph.length <= 1) {
            return shortestPath;
        }
        int[] parents = new int[graph.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visited = new ArrayList<>();
        queue.offer(start);
        while (queue.size() != 0) {
            int visit = queue.poll();
            if (!visited.contains(visit)) {
                if (visit == end) {
                    break;
                }
                else {
                    for (int i = 0; i < graph[visit].length; i++) {
                        if (graph[visit][i] == 1) {
                            queue.offer(i);
                            if (parents[i] == -1)
                                parents[i] = visit;
                        }
                    }
                    visited.add(visit);
                }
            }
        }

        int point = end;
        shortestPath.add(0, end);
        while (point != start) {
            point = parents[point];
            shortestPath.add(0, point);
        }
        return shortestPath;
    }

    /**
     * 深度优先DFS（回溯）
     * 深度优先遍历在编码上可以使用栈或者递归实现，当使用递归时就叫做回溯法，，八皇后问题-回溯法，可以求解所有可能的解，
     * 而广度优先一般不可以求得所有解，但是可应用于最优解问题，利用分支限界的思想，所以一般求解最优化问题使用广度优先，
     * 深度优先也可以。。
     * 相当于树的前序遍历
     *
     * （用一个二维数组来表示有向无权图，1表示有指向，0表示没有指向，假设根节点为0点，满足条件的节点是6，搜索从0点到6点的路径，返回满足条件的6节点）
     */
    public class TreeNode {
        private TreeNode leftNode;
        private TreeNode rightNode;
        private char rootElem;

        public TreeNode(char rootElem, TreeNode leftNode, TreeNode rightNode) {
            this.rootElem = rootElem;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public char getRootElem() {
            return this.rootElem;
        }
        public TreeNode getLeftNode() {
            return this.leftNode;
        }
        public TreeNode getRightNode() {
            return this.rightNode;
        }
        public void setRootElem(char rootElem) {
            this.rootElem = rootElem;
        }
        public void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }
        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }
    }

    public void DFS_BuildBinaryTree() {
        TreeNode E = new TreeNode('E', null, null);
        TreeNode F = new TreeNode('F', E, null);
        TreeNode A = new TreeNode('A', null, null);
        TreeNode D = new TreeNode('D', A, F);
        TreeNode H = new TreeNode('H', null, null);
        TreeNode Z = new TreeNode('Z', null, null);
        TreeNode M = new TreeNode('M', H, Z);
        TreeNode G = new TreeNode('G', D, M);

//        TreeNode[] preorder = {G, D, A, F, E, M, H, Z};
//        TreeNode[] inorder = {A, D, E, F, G, H, M, Z};
//        TreeNode[] afterorder = {A, E, F, D, H, Z, M, G};
        String preorder = "GDAFEMHZ";
        String inorder = "ADEFGHMZ";
        String afterorder = "AEFDHZMG";

        // 前序遍历
        String preOrder = firstOrderTraversal(G);
        System.out.println("DFS 前序遍历---------------------");
        System.out.println(preOrder);
        // 中序遍历
        String inOrder = inOrderTraversal(G);
        System.out.println("DFS 中序遍历---------------------");
        System.out.println(inOrder);
        // 后序遍历
        String afterOrder = afterOrderTraversal(G);
        System.out.println("DFS 后序遍历---------------------");
        System.out.println(afterOrder);



        // 已知中序和前序遍历，求后序遍历
        String afterOrderResult = preAndInToAfter(preorder, inorder);
        System.out.println("DFS 已知中序和前序遍历，求后序遍历---------------------");
        System.out.println(afterOrderResult);

        // 已知中序和后序遍历，求前序遍历
        String preOrderResult = afterAndInToPre(afterorder, inorder);
        System.out.println("DFS 已知中序和后序遍历，求前序遍历---------------------");
        System.out.println(preOrderResult);

        // 求字符串的所有字符组合
        List<String> result = new ArrayList<>();
        allStringCombination("abc", result);
        System.out.println("DFS 字符串的所有字符组合---------------------");
        System.out.println(result.toString());

        // 求数组里和为target的所有组合，数组元素可以重复，但是组合不能完全相同
        int[] candidates = {2,3,5};
        int target = 8;
        List<Integer> out = new ArrayList<>();
        List<List<Integer>> result2 = new ArrayList<List<Integer>>();
        combinationSum(candidates, target, 0, out, result2);
        System.out.println("DFS 数组里和为target的所有组合---------------------");
        System.out.println(result2.toString());

    }
    // 前序遍历
    public String firstOrderTraversal(TreeNode root) {
        StringBuilder preorder = new StringBuilder();
        if (root != null) {
            preorder.append(root.getRootElem());
            preorder.append(firstOrderTraversal(root.getLeftNode()));
            preorder.append(firstOrderTraversal(root.getRightNode()));
        }
        return preorder.toString();

    }
    // 中序遍历
    public String inOrderTraversal(TreeNode root) {
        StringBuilder inorder = new StringBuilder();
        if (root != null) {
            inorder.append(inOrderTraversal(root.getLeftNode()));
            inorder.append(root.getRootElem());
            inorder.append(inOrderTraversal(root.getRightNode()));
        }
        return inorder.toString();

    }
    // 后序遍历
    public String afterOrderTraversal(TreeNode root) {
        StringBuilder afterOrder = new StringBuilder();
        if (root != null) {
            afterOrder.append(afterOrderTraversal(root.getLeftNode()));
            afterOrder.append(afterOrderTraversal(root.getRightNode()));
            afterOrder.append(root.getRootElem());
        }
        return afterOrder.toString();

    }
    // 已知中序和前序遍历，求后序遍历
    public String preAndInToAfter(String preorder, String inorder) {
        if (inorder.length() <= 1) {
            return inorder;
        }
        StringBuilder afterorder = new StringBuilder();
        if (preorder != null) {
            char root = preorder.charAt(0);
            int index = inorder.indexOf(String.valueOf(root));
            if (index != -1) {
                afterorder.append(preAndInToAfter(preorder.substring(1), inorder.substring(0, index)));
                if (index + 1 <= inorder.length() - 1) {
                    afterorder.append(preAndInToAfter(preorder.substring(index + 1), inorder.substring(index + 1, inorder.length())));
                }
            }
            afterorder.append(preorder.charAt(0));
        }
        return afterorder.toString();

    }
    // 已知中序和后序遍历，求前序遍历
    public String afterAndInToPre(String afterorder, String inorder) {
        if (inorder.length() <= 1) {
            return inorder;
        }
        StringBuilder preorder = new StringBuilder();
        if (afterorder != null) {
            char root = afterorder.charAt(afterorder.length() - 1);
            preorder.append(root);
            int index = inorder.indexOf(String.valueOf(root));
            if (index != -1) {
                preorder.append(afterAndInToPre(afterorder.substring(0, index), inorder.substring(0, index)));
                if (index + 1 <= inorder.length() - 1) {
                    preorder.append(afterAndInToPre(afterorder.substring(0, afterorder.length() - 1), inorder.substring(index + 1, inorder.length())));
                }
            }
        }
        return preorder.toString();
    }
    public void allStringCombination(String str, List<String> result) {
        StringBuilder s = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            s.setCharAt(i, s.charAt(0));
            s.setCharAt(0, tmp);
            for (int h = 0; h < str.length(); h++) {
                StringBuilder ss = new StringBuilder(s.substring(h));
                for (int j = 0; j < ss.length(); j++) {
                    tmp = ss.charAt(j);
                    ss.setCharAt(j, ss.charAt(0));
                    ss.setCharAt(0, tmp);

                    allSubstring(ss.toString(), 0, result);

                    tmp = ss.charAt(j);
                    ss.setCharAt(j, ss.charAt(0));
                    ss.setCharAt(0, tmp);
                }
            }
            tmp = s.charAt(i);
            s.setCharAt(i, s.charAt(0));
            s.setCharAt(0, tmp);
        }
    }
    // 求字符串的所有字符组合
    public void allSubstring(String str, int start, List<String> result) {
        if (start >= str.length() - 1) {
            result.add(str);
            return;
        }
        StringBuilder s = new StringBuilder(str);
        for (int i = start; i < s.length(); i++) {
            char tmp = s.charAt(i);
            s.setCharAt(i, s.charAt(start));
            s.setCharAt(start, tmp);

            allSubstring(s.toString(), start + 1, result);

//            StringBuilder ret = new StringBuilder();
//            ret.append(s.charAt(start));
//            ret.append(allSubstring(s.toString(), start + 1, result));
//            result.add(ret.toString());
//            result.add(s.substring(0, start) + ret.toString());

            tmp = s.charAt(i);
            s.setCharAt(i, s.charAt(start));
            s.setCharAt(start, tmp);
        }
    }

    // 求数组里和为target的所有组合，数组元素可以重复，但是组合不能完全相同
    public void combinationSum(int[] candidates, int target, int start, List<Integer> out, List<List<Integer>> result) {
        if (target < 0) {
           return;
        }
        if (target == 0) {
            // List中的子List不能在赋值到父List后释放掉, 由于list相当于指针变量，当释放list中信息时，Llist中刚刚被add进去的值也被释放掉了，所以每次要new一个对象
            List<Integer> out1 = new ArrayList<>(out);
            result.add(out1);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            out.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], i, out, result);
            out.remove(out.size() - 1);
        }
    }



//    public void deepFirstSearch() {
//        int[][] graph = {{0,1,1,0,0,0,0},{0,0,0,0,1,0,0},{0,0,0,1,0,1,0},{0,0,0,0,1,0,0},{0,0,0,0,0,0,1},
//                {0,0,0,0,1,0,0},{0,0,0,0,0,0,0}};
//        int start = 0;
//        int end = 6;
//        int key = deepFirstSearch(graph, start, end);
//        System.out.println("深度优先搜索（有向无权图）---------------------");
//        System.out.println(key); // 结果是满足条件的节点
//    }

//    private int deepFirstSearch(int[][] graph, int start, int end) {
//        int key = start;
//        if (graph.length <= 1) {
//            return key;
//        }
//        Queue<Integer> queue = new LinkedList<>();
//        List<Integer> visited = new ArrayList<>();
//        queue.offer(start);
//        shortestPath.add(start);
//        while (queue.size() != 0) {
//            int visit = queue.poll();
//            if (!visited.contains(visit)) {
//                shortestPath.add(visit);
//                if (visit == end) {
//                    shortestPath.add(end);
//                    return shortestPath;
//                }
//                else {
//                    for (int i = 0; i < graph[visit].length; i++) {
//                        if (graph[visit][i] == 1) {
//                            queue.offer(i);
//                        }
//                    }
//                    visited.add(visit);
//                }
//            }
//        }
//        return shortestPath;
//    }

    /**
     * 迪克斯特拉 迪杰斯特拉算法（用一个二维数组来表示有向加权图（非负权），n表示有指向且权重是n，0表示没有指向，起点假设为0点，终点假设为6点，
     *           搜索从0点到6点的最短加权距离）
     */
    public void dijkstra() {
        int[][] graph = {{0,4,10,0,0,0,0},{0,0,0,0,21,0,0},{0,0,0,5,0,8,0},{0,0,0,0,5,0,0},{0,0,0,0,0,0,4},
                {0,0,0,0,12,0,0},{0,0,0,0,0,0,0}};
        int start = 0;
        int end = 6;
        List<Integer> shortestPath = new ArrayList<>();
        int shortestDist = dijkstra(graph, start, end, shortestPath);
        System.out.println("迪克斯特拉算法（有向加权图，非负权）---------------------");
        System.out.println("最短距离：" + shortestDist); // 结果是 24
        System.out.println("最短路径：" + shortestPath.toString()); // 结果是 0 -> 2 -> 3 -> 4 -> 6
    }

    private int dijkstra(int[][] graph, int start, int end, List<Integer> shortestPath) {
        int shortestDist = Integer.MAX_VALUE;
        if (graph.length <= 1) {
            return shortestDist;
        }
        List<Integer> visited = new ArrayList<>();
        Map<Integer, Integer> costs = new HashMap<>();
        Map<Integer, Integer> parents = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            costs.put(i, Integer.MAX_VALUE);
            parents.put(i, Integer.MAX_VALUE);
        }
        for (int i = 0; i < graph.length; i++) {
            if (graph[start][i] != 0) {
                costs.put(i, graph[start][i]);
                parents.put(i, start);
            }
        }
        visited.add(start);
        while (visited.size() != graph.length) {
            // 选出当前没被访问过的最小的节点
            int minDist = Integer.MAX_VALUE;
            int minDistIndex = -1;
            for (Integer key: costs.keySet()) {
                if (!visited.contains(key) && minDist > costs.get(key)) {
                    minDist = costs.get(key);
                    minDistIndex = key;
                }
            }
            // 更新最小节点的邻居节点的最小距离和父节点
            if (minDistIndex == -1) {
                break;
            }
            for (int i = 0; i < graph[minDistIndex].length; i++) {
                if (graph[minDistIndex][i] !=0 ) {
                    if (costs.get(minDistIndex) + graph[minDistIndex][i] < costs.get(i)) {
                        costs.put(i, costs.get(minDistIndex) + graph[minDistIndex][i]);
                        parents.put(i, minDistIndex);
                    }
                }
            }
            visited.add(minDistIndex);
        }
        // 输出起点到终点路径
        shortestDist = costs.get(end);
        int point = end;
        shortestPath.add(0, end);
        while (point != start) {
            point = parents.get(point);
            shortestPath.add(0, point);
        }
        return shortestDist;
    }


    /**
     * 最大公约数
     */
    public void gcd() {
        int a = 15;
        int b = 12;
        int c;
        // 欧几里得算法（辗转相除法）
        c = gcd_divison(a, b);
        System.out.println("最大公约数（辗转相除法）---------------------");
        System.out.println(c);
        // 欧几里得算法（递归）
        c = gcd_recursive(a, b);
        System.out.println("最大公约数（递归）---------------------");
        System.out.println(c);
        // 相减法
        c = gcd_subtract(a, b);
        System.out.println("最大公约数（相减法）---------------------");
        System.out.println(c);
        // 穷举法
        c = gcd_exhansition(a, b);
        System.out.println("最大公约数（穷举法）---------------------");
        System.out.println(c);
    }
    // 欧几里得算法（辗转相除法）
    private int gcd_divison(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == b || b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        int r;
        while (b > 0) {
            r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
    // 欧几里得算法（递归）
    private int gcd_recursive(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == b || b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        return b > 0 ? gcd_recursive(b, a%b) : a;
    }
    // 欧几里得算法（相减法）
    private int gcd_subtract(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }
    // 欧几里得算法（穷举法）
    private int gcd_exhansition(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0 && a == 0) {
            return 0;
        }
        int t = a;
        if (a > b)
            t = b;
        int i;
        for (i = t; i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                break;
            }
        }
        return i;
    }

    /**
     * 最小公倍数，求最小公倍数的基础是求最大公约数，最小公倍数=最小公约数 * a/最小公约数 * b/最小公约数
     */
    public void lcm() {
        int a = 15;
        int b = 12;
        int c;
        // 欧几里得算法（用最大公约数求）
        c = lcm_gcd(a, b);
        System.out.println("最小公倍数（最大公约数法）---------------------");
        System.out.println(c);
        // 穷举法
        c = lcm_exhansition(a, b);
        System.out.println("最小公倍数（穷举法）---------------------");
        System.out.println(c);
    }

    private int lcm_gcd (int a, int b) {
        return a/gcd_divison(a, b) * b;
    }
    private int lcm_exhansition (int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0 || a == 0) {
            return 0;
        }
        int t = a;
        if (a < b)
            t = b;
        int i;
        for (i = t; ; i++) {
            if (i % a == 0 && i % b == 0) {
                break;
            }
        }
        return i;
    }


    /**
     * 树：一种非线性的数据结构
     * 1、树没有环，图可以有环，这是树和图的区别
     * 2、森林、前驱和后继、树的度，叶子节点度是0
     * 3、节点的双亲，祖先，孩子，子孙
     * 4、树的层次，根节点是第一层，树的高度就是树的层数
     */

    /**
     * 二叉树：非叶子节点最多只有两个孩子节点，一左一右，二叉树的子树也是二叉树
     */

    /**
     * 完全二叉树:
     * 1、叶子节点只能在k或者k-1层，也就是说，1到k-1层必须是最大节点数
     * 2、k层可以不满，但是节点都必须在左边
     * 3、任何一个节点不能只有左子树没有右子树
     * 4、所以父节点和子节点的索引是有规律的：0，1，2，3（k/2 2k+1 2k+2）1，2，3，4（k/2 2k 2k+1）
     */

    /**
     * 满二叉树：1到k层叶子节点都有
     */

    /**
     * 二叉树搜索树：左子树都比节点小，右子树都比节点大，二叉排序树的中序遍历一定是从小到大的
     */

    /**
     * 平衡二叉树：一个可以保持平衡的二叉搜索树，为了保证二叉搜索树不至于太倾斜，尽量保证两边平衡
     * 1、一颗空树
     * 2、左右子树的高度之差不大于 1
     * 3、子树也必须是一颗平衡二叉树
     */

    /**
     * 最优搜索二叉树（动态规划）：每个节点都有一个被访问的概率值，构造一个二叉树具有最小的平搜索代价，用动态规划替换每个节点为根节点来比较来解
     */

    /**
     * 红黑树：是一种平衡的二叉查找树的数据结构，就是不会左腿特别长或者右腿特别长，STL中的map就是用的红黑树
     * 1、节点是红色或者黑色的
     * 2、根节点是黑色
     * 3、叶子节点必须是黑色空节点（null）
     * 4、每个红色节点的两个子节点必须是黑色的，但不是说非叶子节点一定是红色的，也可以是黑色的
     * 5、从任意节点到每个叶子节点的路径包含的黑色节点数都是一样多的
     * 在插入、删除节点的时候，破坏了规则就需要调整红黑树：
     * 1、变色
     * 2、先把能变色的先变色，当变色进行不下去了，就旋转--左旋转、右旋转
     */

    /**
     * B树：就是二叉搜索树
     * 如何保持B树结点分布均匀的平衡算法是平衡二叉树的关键；平衡算法是一种在B树中插入和删除结点的策略：
     * 由于M/2的限制，在插入结点时，如果结点已满，需要将结点分裂为两个各占M/2的结点；删除结点时，需将两个不足M/2的兄弟结点合并
     *
     * 所以B-树的性能总是等价于二分查找（与M值无关），也就没有B树平衡的问题
     */

    /**
     * B-树：一种多路搜索树（不是二叉的）
     * 1、定义任意非叶子结点最多只有M个儿子；且M>2；
     * 2、根结点的儿子数为[2, M]
     * 3、除根结点以外的非叶子结点的儿子数为[M/2, M]
     * 4、每个结点存放至少M/2-1（取上整）和至多M-1个关键字；（至少2个关键字）
     * 5、非叶子结点的关键字个数=指向儿子的指针个数 - 1
     * 6、关键词：K[1], K[2], …, K[M]；且K[i] < K[i+1]
     *    指针：P[1], P[2], …, P[M]；其中P[1]指向关键字小于K[1]的子树，P[M]指向关键字大于K[M-1]的子树，其它P[i]指向关键字属于(K[i-1], K[i])的子树
     * 7、所有叶子结点位于同一层
     * 8、关键字在所有节点上，叶子节点存储关键字，非叶子节点存储关键字和指向子节点的指针，子节点能满足最多只有M个孩子的条件，就是叶子节点，否则就是非叶子节点
     *    非叶子结点可以命中
     *
     * B-树中插入和删除结点的策略：
     * 由于M/2的限制，在插入结点时，如果结点已满，需要将结点分裂为两个各占M/2的结点；删除结点时，需将两个不足M/2的兄弟结点合并
     *
     * B-树的查找性能：
     * 所以B-树的性能总是等价于二分查找（与M值无关），也就没有B树平衡的问题
     */

    /**
     * B+树：一种多路搜索树，是B-树的变体
     * 1、定义任意非叶子结点最多只有M个儿子；且M>2；
     * 2、根结点的儿子数为[2, M]
     * 3、除根结点以外的非叶子结点的儿子数为[M/2, M]
     * 4、每个结点存放至少M/2-1（取上整）和至多M-1个关键字；（至少2个关键字）
     * 5、非叶子结点的关键字个数=指向儿子的指针个数
     * 6、关键词：K[1], K[2], …, K[M-1]；且K[i] < K[i+1]
     *    指针：P[1], P[2], …, P[M]；其中P[1]指向关键字小于[K[1],K[2])的子树，P[M]指向关键字大于[K[M],无穷大)的子树，其它P[i]指向关键字属于[K[i], K[i+1])的子树
     * 7、所有叶子结点位于同一层
     * 8、所有关键字在叶子节点上，非叶子节点存储的只是关键字的范围和指向子节点的指针，只在叶子节点才命中
     * 9、所有叶子结点都有一个指向兄弟叶子节点的链指针
     * 10、删除节点时，节点关键字个数小于M/2，就要做合并操作
     *
     * B+树的查找性能：
     * B+树的性能总是等价于二分查找
     *
     * B+树特性：
     * 1、所有关键字都出现在叶子结点的链表中（稠密索引），且链表中的关键字恰好是有序的，不可能在非叶子结点命中
     * 2、非叶子结点相当于是叶子结点的索引（稀疏索引），叶子结点相当于是存储（关键字）的数据层，更适合文件索引系统
     */

    /**
     * B*树：一种多路搜索树，是B+树的变体
     * 1、非叶子结点关键字个数至少为(2/3)*M，B+树是少为(1/2)*M，比B+树提高了一个节点块的利用率，
     *    所以B*树分配新结点的概率比B+树要低，空间使用率更高
     * 2、删除节点时，节点关键字个数小于2/3*M，就要做合并操作
     * 3、在B+树的非根和非叶子结点再增加指向兄弟的指针
     */

    /**
     * B树：二叉树，每个结点只存储一个关键字，等于则命中，小于走左结点，大于走右结点；
     * B-树：多路搜索树，每个结点存储M/2到M个关键字，非叶子结点存储指向关键字范围的子结点；所有关键字在整颗树中出现，且只出现一次，非叶子结点可以命中；
     * B+树：在B-树基础上，为叶子结点增加链表指针，所有关键字都在叶子结点中出现，非叶子结点作为叶子结点的索引；B+树总是到叶子结点才命中；
     * B*树：在B+树基础上，为非叶子结点也增加链表指针，将结点的最低利用率从1/2提高到2/3；
     */

    /**
     * 欧几里得算法（扩展欧几里得、欧拉定理、费马小定理）
     */

    /**
     * 欧拉定理：欧拉函数φ(n)是小于或等于n的正整数中与n互质的数的数目
     * 欧拉函数：
     * 1、 当n=1，φ(1)=1
     * 2、 当n为质数，φ(n)=n-1，因为质数与小于它的每一个数，都构成互质关系
     * 3、 n = p^k (p为质数，k为指数，且大于等于1)，n是质数的k次方，则φ(p^k) = p^k - p^(k-1) = p^k（1 - 1/p）= p^(k-1)(p-1）
     * 4、 n是两个互质的整数之积，如：n = p1 * p1，则 φ(n) = φ(p1p2) = φ(p1)φ(p2)
     * 5、 欧拉函数通用公式：n=(p1^a1)*(p2^a2)*……*(pk^ak), φ(n)=n*(1-1/p1)*(1-1/p2)*……*(1-1/pk)
     * 欧拉定理：a^(φ(m))同余1(mod m) (a与m互质)
     */

    /**
     * 朴素贝叶斯
     * 1、输入是所有待分类数据，输出是特征属性和训练样本，提取特征属性，和手工分类划分，形成训练样本
     * 2、分类器训练，输入是特征属性和训练样本，输出是分类器，主要工作是计算每个类别在训练样本中的出现频率及每个特征属性划分对每个类别的条件概率估计
     * 3、使用分类器对待分类项进行分类，输入是分类器和待分类项，输出是待分类项与类别的映射关系
     */

    /**
     * RSA，是一种非对称加密算法
     * 选取一对很大的素数（质数）p,q，让n=pq，计算f(n)=(p-1)(q-1),公钥KU=(e,n),e与(p-1)(q-1)互质，且1<e<(p-1)(q-1)
     * 私钥KR=(d,n),de≡1 mod (p-1)(q-1) 也是 d≡e-1 mod (p-1)(q-1)，所以破解私钥的关键是为求d要求p和q
     * 公钥私钥的长度（模长度）要到1024位甚至2048位才能保证安全，因此，p、q、e的选取、公钥私钥的生成，加密解密模指数运算都有一定的计算程序，需要仰仗计算机高速完成
     * 当p和q是一个大素数的时候，从它们的积pq去分解因子p和q，这是一个公认的数学难题。比如当pq大到1024位时，迄今为止还没有人能够利用任何计算工具去完成分解因子的任务
     *
     * 加密解密过程用欧拉定理，
     * 则加密为：每一个符号表示成数字m，加密后数字≡m^e mod n，所以加密后数字 = m^e mod n
     * 则解密为：解密后数字≡m^d mod n，所以解密后数字 = m^d mod n，再把解密后数字还原成符号
     */

    public void RSA() {
        String rawString = "shihongyuan";
        // 公钥私钥
        int[] public_key = {3, 33};
        int[] secret_key = {7, 33};
        // 编码表
        Map<String, Integer> codeMap= new HashMap<>();
        codeMap.put("a", 1);
        codeMap.put("b", 2);
        codeMap.put("c", 3);
        codeMap.put("d", 4);
        codeMap.put("e", 5);
        codeMap.put("f", 6);
        codeMap.put("g", 7);
        codeMap.put("h", 8);
        codeMap.put("i", 9);
        codeMap.put("j", 10);
        codeMap.put("k", 11);
        codeMap.put("l", 12);
        codeMap.put("m", 13);
        codeMap.put("n", 14);
        codeMap.put("o", 15);
        codeMap.put("p", 16);
        codeMap.put("q", 17);
        codeMap.put("r", 18);
        codeMap.put("s", 19);
        codeMap.put("t", 20);
        codeMap.put("u", 21);
        codeMap.put("v", 22);
        codeMap.put("w", 23);
        codeMap.put("x", 24);
        codeMap.put("y", 25);
        codeMap.put("z", 26);

        System.out.println("RSA公钥秘钥(加密后) ---------------------");
        String encodeString = RSA_encode(rawString, codeMap, public_key);
        System.out.println(rawString + " -> " + encodeString);

        System.out.println("RSA公钥秘钥(解密后) ---------------------");
        String decodeString = RSA_decode(encodeString, codeMap, secret_key);
        System.out.println(encodeString + " -> " + decodeString);
    }
    private String RSA_encode(String rawString, Map<String, Integer> codeMap, int[] public_key) {
        String encodeString = "";
        int i = rawString.length() - 1;
        while (i >= 0) {
            if (codeMap.get(rawString.substring(i, i + 1)) != null) {
                int key = codeMap.get(rawString.substring(i, i + 1));
                int code = (int)Math.pow(Double.valueOf(key), public_key[0]) % public_key[1];
                System.out.println(rawString.substring(i, i + 1) + "  " + key + "   " + code);
                if (code < 10) {
                    encodeString += "0";
                }
                encodeString += String.valueOf(code);
            }
            i--;
        }
        return encodeString;
    }
    private String RSA_decode(String encodeString, Map<String, Integer> codeMap, int[] secret_key) {
        String decodeString = "";
        int i = encodeString.length() - 2;
        while (i >= 0) {
            int code = (int)((long)Math.pow(Double.valueOf(Integer.valueOf(encodeString.substring(i, i + 2))), secret_key[0]) % secret_key[1]);
            long d = (long)Math.pow(Double.valueOf(Integer.valueOf(encodeString.substring(i, i + 2))), secret_key[0]);
            System.out.println(encodeString.substring(i, i + 2) + "  " + d + "  " + code);
            for (String key: codeMap.keySet()) {
                if (codeMap.get(key).equals(code)) {
                    decodeString += key;
                    break;
                }
            }
            i -= 2;
        }
        return decodeString;

    }

    /**
     * 对称加密：公钥等于私钥，用对称私钥加密，对称私钥解密
     * 非对称加密：公钥不等于私钥，用对方的公钥加密，对方用自己的私钥解密
     */

    /**
     * 数字签名（也叫数字摘要，或数字指纹）：为了验证信息确实是对方发送的，中间没有被其他人替换或篡改
     * 1、先对文章进行hash运算，hash值h1叫做摘要，常见hash算法：MD2、MD4、MD5、HAVAL、SHA
     * 2、用自己私钥对摘要加密，生成的东西叫“数字签名”
     * 3、把数字签名加在Email正文后面，一起发送给B（为了防止邮件被窃听你可以用继续公钥加密）
     * 4、B收到邮件后（如果用公钥整体加密，这里需要先用自己私钥解密）用A的公钥对数字签名解密，成功则代表Email确实来自A，失败说明有人冒充
     * 5、B对邮件正文执行哈希运算得到hash值h2，对比数字签名的hash值h1和自己运算得到的h2
     */

    /**
     * 数字证书：为了得到对方可靠的公钥，为了防止对方的公钥再本地被替换，以至于后面的验证无意义
     * 1、对方去证书中心生成数字证书，包含过期时间，颁证机构，对方的公钥等信息，由证书中心用私钥将这些信息加密
     * 2、不同的证书中心的公钥（叫根证书）都会存在浏览器，会去获取，不容易被替换
     * 3、对方发送文章不仅加上数字签名，再加上数字证书
     * 4、B收到邮件后，先用证书中心的根证书解密数字证书，拿到对方的公钥，再去解密数字签名，再做接下来的验证，就能保证这个对方的公钥是正确的了
     *   根证书是自验证证书，CA机构是获得社会绝对认可和有绝对权威的第三方机构，这一点保证了根证书的绝对可靠。如果根证书都有问题那么整个加密体系毫无意义
     */

    /**
     * https:http + ssl/tls
     * tls：做的是客户端发起连接请求（同时把自己支持的加密规则发给服务方），客户端验证服务方发来的根证书（以及服务方挑选的一组加密算法和hash算法），然后生成随机数（对称加密私钥），用服务方公钥加密，发给服务方，
     * 服务方收到私钥解密成功，获得加密私钥，计算握手消息hash值，用加密私钥加密握手消息给客户端，客户端用加密私钥解密握手消息，计算hash值是否与服务方发送来的一致，再用加密私钥加密握手消息和消息的hash值给服务方，
     * 服务方收到握手消息，用加密私钥解密并计算hash值对比，此时握手过程结束
     * ssl：在应用层之下，传输层之上，做的就是对服务方和客户端之间互传的消息用加密私钥进行加密后发送，接收后用加密私钥进行解密
     *
     * https中常用的
     * 非对称加密算法：RSA，DSA/DSS
     * 对称加密算法：AES，RC4，3DES
     * HASH算法：MD5，SHA1，SHA256
     *
     * ssl证书会过期是因为：比如有的公司转让给其他公司了，如果证书还保留有效，其他公司就可以利用这个证书做任何消息传播，用的是原公司的名义，
     * 所以颁发证书时有效期一般是2年，2年后自动过期，必须重新去申请
     */


    public static void main(String[] args) {
        Algorithms algorithms = new Algorithms();

        algorithms.jsonDeep();

        algorithms.binarySearch();

        algorithms.quickSort();

        algorithms.insertSort();

        algorithms.selectSort();

        algorithms.bubbleSort();

        algorithms.mergeSort();

        algorithms.heapSort();

        algorithms.countSort();

        algorithms.bucketSort();

        algorithms.RadixSort();

        algorithms.shellSort();

        algorithms.greedy();

        algorithms.sumZeroLongestSubstring();

        algorithms.longestSubsequence();

        algorithms.longestSubstring();

        algorithms.breadFirstSearch();

        algorithms.dijkstra();

        algorithms.gcd();

        algorithms.lcm();

        algorithms.DFS_BuildBinaryTree();

        algorithms.RSA();

        // 十进制整数转换为二进制整数：用2整除十进制整数，可以得到一个商和余数；再用2去除商，又会得到一个商和余数，如此进行，直到商为0时为止，然后把先得到的余数作为二进制数的低位有效位，后得到的余数作为二进制数的高位有效位，依次排列起来
        int a = 10;
        String binary = "";
        while(a != 0) {
            int r = a%2;
            a/=2;
            binary += String.valueOf(r);
        }

        // 十进制小数转换成二进制小数:用2乘十进制小数，可以得到积，将积的整数部分取出，再用2乘余下的小数部分，又得到一个积，再将积的整数部分取出，如此进行，直到积中的小数部分为零，此时0或1为二进制的最后一位。或者达到所要求的精度为止
        double b = 0.5;
        binary = "";
        while(b != 0) {
            int r = (int)(b * 2);
            b = b - r;
            binary += String.valueOf(r);
        }
    }

    // ci ： branchpipeline:编译 + 做一些提测前的保证，跑新case，映射新case，影响面评估，pft，bda，提测准入
    //       合主干-> changepipeline:编译，合master hook -> masterpipeline:编译，跑全量master case，每个项目负责人得关注自己的dev合master的绿灯情况（报警等）
    //       拉RB -> RBpipeline:编译，发布，预上线

}
