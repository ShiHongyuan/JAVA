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
     * ���ֲ���
     */
    public void binarySearch() {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,10};
        int key = 2;
        int index = this.binarySearch(a, key);
        System.out.println("���ֲ���---------------------");
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
     * ��������
     */
    public void quickSort() {
        int[] a = {10,2,3,9,4,8,5,7,6,1};
        this.quickSort(a, 0, a.length - 1);
        System.out.println("��������---------------------");
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
     * ��������
     */
    public void insertSort() {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.insertSort(a);
        System.out.println("��������---------------------");
        System.out.println(Arrays.toString(a));

    }

    private void insertSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        else {
            // JAVA����ֻ�ܴ���������͵ĵ����ݣ����ܴ�Ż�����������
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
     * ��ѡ������ ��ÿһ��ѡ��С�����ģ���һ�ֵ���󽻻���
     */
    public void selectSort () {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.selectSort(a);
        System.out.println("ѡ������---------------------");
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
     * ð������ �������Ƚϣ��ͽ�����
     */
    public void bubbleSort () {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.bubbleSort(a);
        System.out.println("ð������---------------------");
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
     * �鲢����
     */
    public void mergeSort() {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.mergeSort(a, 0, a.length - 1);
        System.out.println("�鲢����---------------------");
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
     * ������
     */
    public void heapSort() {
        int[] a = new int[]{10,2,3,9,4,8,5,7,6,1};
        this.heapSort(a);
        System.out.println("������---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void heapSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        // �ѣ������0��ʼ�����ڵ�Ϊk/2���ӽڵ���2k��2k+1
        // �ѣ������1��ʼ�����ڵ�Ϊ��k/2 - 1�����ӽڵ���2k+1��2k+2
        // �������ѣ������һ����Ҷ�ӽڵ㿪ʼ�������ϲ�ĸ��ڵ㣬ÿ���ڵ�ȥ�³�����Ӧ��λ�ã��ܹ���һ������
        for (int i = a.length/2 - 1; i >= 0; i--) {
            adjustHeap(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            // ���ѵĸ��ڵ������ģ����������һ��������λ�ã���δ����Χ�ڣ��³�������ĵ�һ��������ɺ���ڵ����������ˣ��ֽ��������³���n-1�κ���ź�����
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
     * ���������ȶ�����  �ʺϵķ�Χ�����㣬�ʺϴ���Ԫ��ֵ�ֲ�����������Ƚ�С�������������˷ѿռ�
     */
    public void countSort() {
        int[] a = new int[]{15,1,6,4,5,2,2,9,10,12};
        this.countSort(a);
        System.out.println("��������---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void countSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        // �ҳ������Сֵ
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
        // �������¼ÿ��ֵ���ֵĸ���
        int[] count = new int[max - min + 1];
        for (int i: a) {
            count[i - min]++;
        }
        // �������¼ÿ��ֵ���ֵ���ʼλ��
        int[] index = new int[max - min + 1];
        index[0] = 0;
        for (int i = 1; i < count.length; i++) {
            index[i] = index[i-1] + count[i-1];
        }
        // �����鰴˳��װֵ
        int[] tmp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            tmp[index[a[i] - min]] = a[i];
            index[a[i] - min]++;
        }
        // �����鸲��ԭ����
        for (int i = 0; i < tmp.length; i++) {
            a[i] = tmp[i];
        }
    }

    /**
     * Ͱ����
     * Ͱ������arr.length
     * ӳ�亯����bucketIndex = (value * arr.length) / (maxValue + 1)
     */
    public void bucketSort() {
        int[] a = new int[]{15,1,6,4,5,2,2,9,10,12};
        this.bucketSort(a);
        System.out.println("Ͱ����---------------------");
        System.out.println(Arrays.toString(a));
    }

    private void bucketSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        // �ҳ����ֵ
        int max = a[0];
        for (int i: a) {
            if (max < i) {
                max = i;
            }
        }
        // �Ѵ���Ԫ�طֲ�����ͬ��Ͱ��
        List<Integer>[] buckets = new ArrayList[a.length];
        for (int i: a) {
            int bucketIndex = (i * a.length) / (max + 1);
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<Integer>();
            }
            buckets[bucketIndex].add(i);
        }
        // ÿ��Ͱ�ڲ���������
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
        // ����ÿ��Ͱ������
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
     * ��������
     * �ַ����������ַ���ɣ�����������λ0~9�������
     * ÿһ�����������򷽷��������ȶ��ġ�������������ܵõ���ȷ�Ľ�������Ի����������ȶ�����
     * ������ʲô��˼������ʮ����������ÿһλ��ֻ������0~9�е�ĳһ�����ܹ�10�ֿ��ܡ���10�������Ļ���ͬ����������ֵĻ�Ϊ2�������ַ����������ʹ�õ���8λ����չASCII�ַ�������ô���Ļ�����256
     * ���ַ���ʹ�õ���8λ��ASCII��չ�ַ���������Ĵ�С��256�����ڻ���������ַ������򷽷���Ϊ��λ���ȵ��ַ�������
     * ��Ȼ֪��ÿһλ����ֵ��Χ��ʹ�ü��������Թؼ��ֶ�������������Ǹ����ǵ�ѡ��
     *      ��Ϊ���ݷ�Χȷ���Ҷ����󣨻��Ĵ�С������˲���ռ�ö��ٿռ䣻
     *      ���Ҽ��������ǻ��ڱȽϣ���ͨ���ıȽ����򷽷�Ч�ʸ��ߣ�
     *      �����������ȶ�������һ��������Ҫ��
     */


   // import java.util.Arrays;

    public void RadixSort() {
        int[] a = {244, 167, 1234, 321, 29, 98, 1444, 111, 99, 6};
        RadixSort(a);
        System.out.println("��������---------------------");
        System.out.println(Arrays.toString(a));
    }

    public void RadixSort(int[] a) {
        // ÿλ���ַ�Χ0~9����Ϊ10
        int R = 10;
        int N = a.length;
        int[] aux = new int[N];
        int[] count = new int[R + 1];
        // �Թؼ������������������λ���������־���������λ���ٵ������ڱȽϸ�λʱ���Զ���0���бȽ�
        // ������ת�����ַ������ַ����ĳ��Ⱦ������ֵ�λ�����ַ�������Ǹ�����Ҳӵ������λ��
        int W = Arrays.stream(a).map(s -> String.valueOf(s).length()).max().getAsInt();

        // ����Ҫd�ּ�������, ��d = 0��ʼ��˵���ǴӸ�λ��ʼ�Ƚϣ����ϴ��ҵ����˳��
        for (int d = 0; d < W; d++) {
            // 1. ����Ƶ�ʣ�����Ҫ�����鳤���϶����1
            for (int i = 0; i < N; i++) {
                // ʹ�ü�1������������ظ��ĸ�λ�þ�����
                count[digitAt(a[i], d) + 1]++;
            }
            // 2. Ƶ�� -> Ԫ�صĿ�ʼ����
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // 3. Ԫ�ذ��տ�ʼ�������࣬�õ�һ���ʹ�������һ������ʱ����������
            for (int i = 0; i < N; i++) {
                // ���һ�����ݺ��������Ա���ͬ�����ݿ������һ����λ
                aux[count[digitAt(a[i], d)]++] = a[i];
            }
            // 4. ���ݻ�д
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
            // ����count[]���Ա���һ��ͳ��ʹ��
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }

        }
    }
    // ����d����ȡĳ��ֵ�ĸ�λ��ʮλ����λ�ȣ�d = 0ȡ����λ��d = 1ȡ��ʮλ���Դ����ơ����ڲ����ڵĸ�λ����0��
    private int digitAt(int value, int d) {
        return (value / (int) Math.pow(10, d)) % 10;
    }



    /**
     * ϣ��shell����
     * �������Ԫ��������n��Ԫ�أ�����ȡһ������increment��С��n����Ϊ�����ȫ��Ԫ�ط�Ϊincrement�������У�
     * ���о���Ϊincrement��Ԫ�ط���ͬһ���������У���ÿһ���������зֱ�ʵ��ֱ�Ӳ�������Ȼ����С���increment��
     * �ظ����������л��ֺ���������ֱ�����ȡincrement=1��������Ԫ�ط���ͬһ��������������Ϊֹ��
     * ϣ��������һ�ֲ��ȶ��������㷨
     *
     * ���shell���ȡincrement=n/2����ȡ����increment=increment/2����ȡ����ֱ��increment=1��������ֱ�����һ����
     * ������λ�õ�Ԫ�زŻ���ż��λ�õ�Ԫ�ؽ��бȽϣ�����ʹ��������е�Ч�ʻ�ܵ͡�
     * ����Knuth���ȡincrement=n/3����ȡ��+1.�����������ȡ����Ϊ�ã�Ҳ�������increment����Ϊ��
     */
    public void shellSort() {
        int[] a = {244, 167, 1234, 321, 29, 98, 1444, 111, 99, 6};
        shellSort(a);
        System.out.println("ϣ������---------------------");
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
     * ��������⣨̰���㷨��
     */
    public void greedy() {
        int[] activity = {1, 2, 3, 4, 5, 6, 7};
        int[] sTime = {0, 1, 2, 4, 3, 6, 8};  // ���ŷֱ���1��2��3��4��5��6��7
        int[] fTime = {1, 3, 4, 7, 8, 8, 10}; // ���ŷֱ���1��2��3��4��5��6��7
        int start = 0;
        int finish = 0;
        List<Integer> activeList = greedy(activity, sTime, fTime, start, finish);
        System.out.println("����ã�̰���㷨��---------------------");
        for (int i = 0; i < activeList.size(); i++) {
            System.out.println(activeList.get(i));  // ��ȷ��Ӧ���� 1��2��4��7
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
     * ��Ϊ0����Ӵ�
     */
    public void sumZeroLongestSubstring () {
        int[] a = {1, 1, -1, -1, 1, 2, -2};
        int[] ret = sumZeroLongestSubstring(a);
        System.out.println("��Ϊ0����Ӵ�---------------------");
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
     * ����������У���̬�滮��
     * ��̬�滮����һ��Լ���������Ż�ĳ��ָ�꣬�ɷֽ�Ϊ��ɢ�������⣬��ͬ���������������һ������ͬ������������ͬ����������
     * ���η����ɷֽ�Ϊ��ɢ�����������⣬��ͬ��������������ⲻһ������ͬ�����ⲻ������ͬ����������
     * ��̬�滮�ͷ��η����õݹ�
     */
    public void longestSubsequence() {
        String s1 = "HELLO";
        String s2 = "UHOLSY";
        List<String> subString = longestSubsequence(s1, s2);
        System.out.println("�����������---------------------");
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
     * ������Ӵ�����̬�滮��
     */
    public void longestSubstring() {
        String s1 = "HELLO";
        String s2 = "UHOLSY";
        List<String> subString = longestSubstring(s1, s2);
        System.out.println("������Ӵ�---------------------");
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
     * �������BFS����һ����ά��������ʾ������Ȩͼ��1��ʾ��ָ��0��ʾû��ָ��������Ϊ0�㣬�յ����Ϊ6�㣬������0�㵽6�����̾��룩
     */
    public void breadFirstSearch() {
        int[][] graph = {{0,1,1,0,0,0,0},{0,0,0,0,1,0,0},{0,0,0,1,0,1,0},{0,0,0,0,1,0,0},{0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0},{0,0,0,0,0,0,0}};
        int start = 0;
        int end = 6;
        List<Integer> shortestPath = breadFirstSearch(graph, start, end);
        System.out.println("�������������������Ȩͼ��---------------------");
        System.out.println(shortestPath.toString()); // ����� 0 -> 1 -> 4 -> 6
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
     * �������DFS�����ݣ�
     * ������ȱ����ڱ����Ͽ���ʹ��ջ���ߵݹ�ʵ�֣���ʹ�õݹ�ʱ�ͽ������ݷ������˻ʺ�����-���ݷ�������������п��ܵĽ⣬
     * ���������һ�㲻����������н⣬���ǿ�Ӧ�������Ž����⣬���÷�֧�޽��˼�룬����һ��������Ż�����ʹ�ù�����ȣ�
     * �������Ҳ���ԡ���
     * �൱������ǰ�����
     *
     * ����һ����ά��������ʾ������Ȩͼ��1��ʾ��ָ��0��ʾû��ָ�򣬼�����ڵ�Ϊ0�㣬���������Ľڵ���6��������0�㵽6���·������������������6�ڵ㣩
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

        // ǰ�����
        String preOrder = firstOrderTraversal(G);
        System.out.println("DFS ǰ�����---------------------");
        System.out.println(preOrder);
        // �������
        String inOrder = inOrderTraversal(G);
        System.out.println("DFS �������---------------------");
        System.out.println(inOrder);
        // �������
        String afterOrder = afterOrderTraversal(G);
        System.out.println("DFS �������---------------------");
        System.out.println(afterOrder);



        // ��֪�����ǰ���������������
        String afterOrderResult = preAndInToAfter(preorder, inorder);
        System.out.println("DFS ��֪�����ǰ���������������---------------------");
        System.out.println(afterOrderResult);

        // ��֪����ͺ����������ǰ�����
        String preOrderResult = afterAndInToPre(afterorder, inorder);
        System.out.println("DFS ��֪����ͺ����������ǰ�����---------------------");
        System.out.println(preOrderResult);

        // ���ַ����������ַ����
        List<String> result = new ArrayList<>();
        allStringCombination("abc", result);
        System.out.println("DFS �ַ����������ַ����---------------------");
        System.out.println(result.toString());

        // ���������Ϊtarget��������ϣ�����Ԫ�ؿ����ظ���������ϲ�����ȫ��ͬ
        int[] candidates = {2,3,5};
        int target = 8;
        List<Integer> out = new ArrayList<>();
        List<List<Integer>> result2 = new ArrayList<List<Integer>>();
        combinationSum(candidates, target, 0, out, result2);
        System.out.println("DFS �������Ϊtarget���������---------------------");
        System.out.println(result2.toString());

    }
    // ǰ�����
    public String firstOrderTraversal(TreeNode root) {
        StringBuilder preorder = new StringBuilder();
        if (root != null) {
            preorder.append(root.getRootElem());
            preorder.append(firstOrderTraversal(root.getLeftNode()));
            preorder.append(firstOrderTraversal(root.getRightNode()));
        }
        return preorder.toString();

    }
    // �������
    public String inOrderTraversal(TreeNode root) {
        StringBuilder inorder = new StringBuilder();
        if (root != null) {
            inorder.append(inOrderTraversal(root.getLeftNode()));
            inorder.append(root.getRootElem());
            inorder.append(inOrderTraversal(root.getRightNode()));
        }
        return inorder.toString();

    }
    // �������
    public String afterOrderTraversal(TreeNode root) {
        StringBuilder afterOrder = new StringBuilder();
        if (root != null) {
            afterOrder.append(afterOrderTraversal(root.getLeftNode()));
            afterOrder.append(afterOrderTraversal(root.getRightNode()));
            afterOrder.append(root.getRootElem());
        }
        return afterOrder.toString();

    }
    // ��֪�����ǰ���������������
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
    // ��֪����ͺ����������ǰ�����
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
    // ���ַ����������ַ����
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

    // ���������Ϊtarget��������ϣ�����Ԫ�ؿ����ظ���������ϲ�����ȫ��ͬ
    public void combinationSum(int[] candidates, int target, int start, List<Integer> out, List<List<Integer>> result) {
        if (target < 0) {
           return;
        }
        if (target == 0) {
            // List�е���List�����ڸ�ֵ����List���ͷŵ�, ����list�൱��ָ����������ͷ�list����Ϣʱ��Llist�иոձ�add��ȥ��ֵҲ���ͷŵ��ˣ�����ÿ��Ҫnewһ������
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
//        System.out.println("�������������������Ȩͼ��---------------------");
//        System.out.println(key); // ��������������Ľڵ�
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
     * �Ͽ�˹���� �Ͻ�˹�����㷨����һ����ά��������ʾ�����Ȩͼ���Ǹ�Ȩ����n��ʾ��ָ����Ȩ����n��0��ʾû��ָ��������Ϊ0�㣬�յ����Ϊ6�㣬
     *           ������0�㵽6�����̼�Ȩ���룩
     */
    public void dijkstra() {
        int[][] graph = {{0,4,10,0,0,0,0},{0,0,0,0,21,0,0},{0,0,0,5,0,8,0},{0,0,0,0,5,0,0},{0,0,0,0,0,0,4},
                {0,0,0,0,12,0,0},{0,0,0,0,0,0,0}};
        int start = 0;
        int end = 6;
        List<Integer> shortestPath = new ArrayList<>();
        int shortestDist = dijkstra(graph, start, end, shortestPath);
        System.out.println("�Ͽ�˹�����㷨�������Ȩͼ���Ǹ�Ȩ��---------------------");
        System.out.println("��̾��룺" + shortestDist); // ����� 24
        System.out.println("���·����" + shortestPath.toString()); // ����� 0 -> 2 -> 3 -> 4 -> 6
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
            // ѡ����ǰû�����ʹ�����С�Ľڵ�
            int minDist = Integer.MAX_VALUE;
            int minDistIndex = -1;
            for (Integer key: costs.keySet()) {
                if (!visited.contains(key) && minDist > costs.get(key)) {
                    minDist = costs.get(key);
                    minDistIndex = key;
                }
            }
            // ������С�ڵ���ھӽڵ����С����͸��ڵ�
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
        // �����㵽�յ�·��
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
     * ���Լ��
     */
    public void gcd() {
        int a = 15;
        int b = 12;
        int c;
        // ŷ������㷨��շת�������
        c = gcd_divison(a, b);
        System.out.println("���Լ����շת�������---------------------");
        System.out.println(c);
        // ŷ������㷨���ݹ飩
        c = gcd_recursive(a, b);
        System.out.println("���Լ�����ݹ飩---------------------");
        System.out.println(c);
        // �����
        c = gcd_subtract(a, b);
        System.out.println("���Լ�����������---------------------");
        System.out.println(c);
        // ��ٷ�
        c = gcd_exhansition(a, b);
        System.out.println("���Լ������ٷ���---------------------");
        System.out.println(c);
    }
    // ŷ������㷨��շת�������
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
    // ŷ������㷨���ݹ飩
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
    // ŷ������㷨���������
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
    // ŷ������㷨����ٷ���
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
     * ��С������������С�������Ļ����������Լ������С������=��С��Լ�� * a/��С��Լ�� * b/��С��Լ��
     */
    public void lcm() {
        int a = 15;
        int b = 12;
        int c;
        // ŷ������㷨�������Լ����
        c = lcm_gcd(a, b);
        System.out.println("��С�����������Լ������---------------------");
        System.out.println(c);
        // ��ٷ�
        c = lcm_exhansition(a, b);
        System.out.println("��С����������ٷ���---------------------");
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
     * ����һ�ַ����Ե����ݽṹ
     * 1����û�л���ͼ�����л�����������ͼ������
     * 2��ɭ�֡�ǰ���ͺ�̡����Ķȣ�Ҷ�ӽڵ����0
     * 3���ڵ��˫�ף����ȣ����ӣ�����
     * 4�����Ĳ�Σ����ڵ��ǵ�һ�㣬���ĸ߶Ⱦ������Ĳ���
     */

    /**
     * ����������Ҷ�ӽڵ����ֻ���������ӽڵ㣬һ��һ�ң�������������Ҳ�Ƕ�����
     */

    /**
     * ��ȫ������:
     * 1��Ҷ�ӽڵ�ֻ����k����k-1�㣬Ҳ����˵��1��k-1����������ڵ���
     * 2��k����Բ��������ǽڵ㶼���������
     * 3���κ�һ���ڵ㲻��ֻ��������û��������
     * 4�����Ը��ڵ���ӽڵ���������й��ɵģ�0��1��2��3��k/2 2k+1 2k+2��1��2��3��4��k/2 2k 2k+1��
     */

    /**
     * ����������1��k��Ҷ�ӽڵ㶼��
     */

    /**
     * �����������������������Ƚڵ�С�����������Ƚڵ�󣬶������������������һ���Ǵ�С�����
     */

    /**
     * ƽ���������һ�����Ա���ƽ��Ķ�����������Ϊ�˱�֤����������������̫��б��������֤����ƽ��
     * 1��һ�ſ���
     * 2�����������ĸ߶�֮����� 1
     * 3������Ҳ������һ��ƽ�������
     */

    /**
     * ������������������̬�滮����ÿ���ڵ㶼��һ�������ʵĸ���ֵ������һ��������������С��ƽ�������ۣ��ö�̬�滮�滻ÿ���ڵ�Ϊ���ڵ����Ƚ�����
     */

    /**
     * ���������һ��ƽ��Ķ�������������ݽṹ�����ǲ��������ر𳤻��������ر𳤣�STL�е�map�����õĺ����
     * 1���ڵ��Ǻ�ɫ���ߺ�ɫ��
     * 2�����ڵ��Ǻ�ɫ
     * 3��Ҷ�ӽڵ�����Ǻ�ɫ�սڵ㣨null��
     * 4��ÿ����ɫ�ڵ�������ӽڵ�����Ǻ�ɫ�ģ�������˵��Ҷ�ӽڵ�һ���Ǻ�ɫ�ģ�Ҳ�����Ǻ�ɫ��
     * 5��������ڵ㵽ÿ��Ҷ�ӽڵ��·�������ĺ�ɫ�ڵ�������һ�����
     * �ڲ��롢ɾ���ڵ��ʱ���ƻ��˹������Ҫ�����������
     * 1����ɫ
     * 2���Ȱ��ܱ�ɫ���ȱ�ɫ������ɫ���в���ȥ�ˣ�����ת--����ת������ת
     */

    /**
     * B�������Ƕ���������
     * ��α���B�����ֲ����ȵ�ƽ���㷨��ƽ��������Ĺؼ���ƽ���㷨��һ����B���в����ɾ�����Ĳ��ԣ�
     * ����M/2�����ƣ��ڲ�����ʱ����������������Ҫ��������Ϊ������ռM/2�Ľ�㣻ɾ�����ʱ���轫��������M/2���ֵܽ��ϲ�
     *
     * ����B-�����������ǵȼ��ڶ��ֲ��ң���Mֵ�޹أ���Ҳ��û��B��ƽ�������
     */

    /**
     * B-����һ�ֶ�·�����������Ƕ���ģ�
     * 1�����������Ҷ�ӽ�����ֻ��M�����ӣ���M>2��
     * 2�������Ķ�����Ϊ[2, M]
     * 3�������������ķ�Ҷ�ӽ��Ķ�����Ϊ[M/2, M]
     * 4��ÿ�����������M/2-1��ȡ������������M-1���ؼ��֣�������2���ؼ��֣�
     * 5����Ҷ�ӽ��Ĺؼ��ָ���=ָ����ӵ�ָ����� - 1
     * 6���ؼ��ʣ�K[1], K[2], ��, K[M]����K[i] < K[i+1]
     *    ָ�룺P[1], P[2], ��, P[M]������P[1]ָ��ؼ���С��K[1]��������P[M]ָ��ؼ��ִ���K[M-1]������������P[i]ָ��ؼ�������(K[i-1], K[i])������
     * 7������Ҷ�ӽ��λ��ͬһ��
     * 8���ؼ��������нڵ��ϣ�Ҷ�ӽڵ�洢�ؼ��֣���Ҷ�ӽڵ�洢�ؼ��ֺ�ָ���ӽڵ��ָ�룬�ӽڵ����������ֻ��M�����ӵ�����������Ҷ�ӽڵ㣬������Ƿ�Ҷ�ӽڵ�
     *    ��Ҷ�ӽ���������
     *
     * B-���в����ɾ�����Ĳ��ԣ�
     * ����M/2�����ƣ��ڲ�����ʱ����������������Ҫ��������Ϊ������ռM/2�Ľ�㣻ɾ�����ʱ���轫��������M/2���ֵܽ��ϲ�
     *
     * B-���Ĳ������ܣ�
     * ����B-�����������ǵȼ��ڶ��ֲ��ң���Mֵ�޹أ���Ҳ��û��B��ƽ�������
     */

    /**
     * B+����һ�ֶ�·����������B-���ı���
     * 1�����������Ҷ�ӽ�����ֻ��M�����ӣ���M>2��
     * 2�������Ķ�����Ϊ[2, M]
     * 3�������������ķ�Ҷ�ӽ��Ķ�����Ϊ[M/2, M]
     * 4��ÿ�����������M/2-1��ȡ������������M-1���ؼ��֣�������2���ؼ��֣�
     * 5����Ҷ�ӽ��Ĺؼ��ָ���=ָ����ӵ�ָ�����
     * 6���ؼ��ʣ�K[1], K[2], ��, K[M-1]����K[i] < K[i+1]
     *    ָ�룺P[1], P[2], ��, P[M]������P[1]ָ��ؼ���С��[K[1],K[2])��������P[M]ָ��ؼ��ִ���[K[M],�����)������������P[i]ָ��ؼ�������[K[i], K[i+1])������
     * 7������Ҷ�ӽ��λ��ͬһ��
     * 8�����йؼ�����Ҷ�ӽڵ��ϣ���Ҷ�ӽڵ�洢��ֻ�ǹؼ��ֵķ�Χ��ָ���ӽڵ��ָ�룬ֻ��Ҷ�ӽڵ������
     * 9������Ҷ�ӽ�㶼��һ��ָ���ֵ�Ҷ�ӽڵ����ָ��
     * 10��ɾ���ڵ�ʱ���ڵ�ؼ��ָ���С��M/2����Ҫ���ϲ�����
     *
     * B+���Ĳ������ܣ�
     * B+�����������ǵȼ��ڶ��ֲ���
     *
     * B+�����ԣ�
     * 1�����йؼ��ֶ�������Ҷ�ӽ��������У��������������������еĹؼ���ǡ��������ģ��������ڷ�Ҷ�ӽ������
     * 2����Ҷ�ӽ���൱����Ҷ�ӽ���������ϡ����������Ҷ�ӽ���൱���Ǵ洢���ؼ��֣������ݲ㣬���ʺ��ļ�����ϵͳ
     */

    /**
     * B*����һ�ֶ�·����������B+���ı���
     * 1����Ҷ�ӽ��ؼ��ָ�������Ϊ(2/3)*M��B+������Ϊ(1/2)*M����B+�������һ���ڵ��������ʣ�
     *    ����B*�������½��ĸ��ʱ�B+��Ҫ�ͣ��ռ�ʹ���ʸ���
     * 2��ɾ���ڵ�ʱ���ڵ�ؼ��ָ���С��2/3*M����Ҫ���ϲ�����
     * 3����B+���ķǸ��ͷ�Ҷ�ӽ��������ָ���ֵܵ�ָ��
     */

    /**
     * B������������ÿ�����ֻ�洢һ���ؼ��֣����������У�С�������㣬�������ҽ�㣻
     * B-������·��������ÿ�����洢M/2��M���ؼ��֣���Ҷ�ӽ��洢ָ��ؼ��ַ�Χ���ӽ�㣻���йؼ������������г��֣���ֻ����һ�Σ���Ҷ�ӽ��������У�
     * B+������B-�������ϣ�ΪҶ�ӽ����������ָ�룬���йؼ��ֶ���Ҷ�ӽ���г��֣���Ҷ�ӽ����ΪҶ�ӽ���������B+�����ǵ�Ҷ�ӽ������У�
     * B*������B+�������ϣ�Ϊ��Ҷ�ӽ��Ҳ��������ָ�룬��������������ʴ�1/2��ߵ�2/3��
     */

    /**
     * ŷ������㷨����չŷ����á�ŷ����������С����
     */

    /**
     * ŷ������ŷ��������(n)��С�ڻ����n������������n���ʵ�������Ŀ
     * ŷ��������
     * 1�� ��n=1����(1)=1
     * 2�� ��nΪ��������(n)=n-1����Ϊ������С������ÿһ�����������ɻ��ʹ�ϵ
     * 3�� n = p^k (pΪ������kΪָ�����Ҵ��ڵ���1)��n��������k�η������(p^k) = p^k - p^(k-1) = p^k��1 - 1/p��= p^(k-1)(p-1��
     * 4�� n���������ʵ�����֮�����磺n = p1 * p1���� ��(n) = ��(p1p2) = ��(p1)��(p2)
     * 5�� ŷ������ͨ�ù�ʽ��n=(p1^a1)*(p2^a2)*����*(pk^ak), ��(n)=n*(1-1/p1)*(1-1/p2)*����*(1-1/pk)
     * ŷ������a^(��(m))ͬ��1(mod m) (a��m����)
     */

    /**
     * ���ر�Ҷ˹
     * 1�����������д��������ݣ�������������Ժ�ѵ����������ȡ�������ԣ����ֹ����໮�֣��γ�ѵ������
     * 2��������ѵ�����������������Ժ�ѵ������������Ƿ���������Ҫ�����Ǽ���ÿ�������ѵ�������еĳ���Ƶ�ʼ�ÿ���������Ի��ֶ�ÿ�������������ʹ���
     * 3��ʹ�÷������Դ���������з��࣬�����Ƿ������ʹ����������Ǵ�������������ӳ���ϵ
     */

    /**
     * RSA����һ�ַǶԳƼ����㷨
     * ѡȡһ�Ժܴ��������������p,q����n=pq������f(n)=(p-1)(q-1),��ԿKU=(e,n),e��(p-1)(q-1)���ʣ���1<e<(p-1)(q-1)
     * ˽ԿKR=(d,n),de��1 mod (p-1)(q-1) Ҳ�� d��e-1 mod (p-1)(q-1)�������ƽ�˽Կ�Ĺؼ���Ϊ��dҪ��p��q
     * ��Կ˽Կ�ĳ��ȣ�ģ���ȣ�Ҫ��1024λ����2048λ���ܱ�֤��ȫ����ˣ�p��q��e��ѡȡ����Կ˽Կ�����ɣ����ܽ���ģָ�����㶼��һ���ļ��������Ҫ���̼�����������
     * ��p��q��һ����������ʱ�򣬴����ǵĻ�pqȥ�ֽ�����p��q������һ�����ϵ���ѧ���⡣���統pq��1024λʱ������Ϊֹ��û�����ܹ������κμ��㹤��ȥ��ɷֽ����ӵ�����
     *
     * ���ܽ��ܹ�����ŷ������
     * �����Ϊ��ÿһ�����ű�ʾ������m�����ܺ����֡�m^e mod n�����Լ��ܺ����� = m^e mod n
     * �����Ϊ�����ܺ����֡�m^d mod n�����Խ��ܺ����� = m^d mod n���ٰѽ��ܺ����ֻ�ԭ�ɷ���
     */

    public void RSA() {
        String rawString = "shihongyuan";
        // ��Կ˽Կ
        int[] public_key = {3, 33};
        int[] secret_key = {7, 33};
        // �����
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

        System.out.println("RSA��Կ��Կ(���ܺ�) ---------------------");
        String encodeString = RSA_encode(rawString, codeMap, public_key);
        System.out.println(rawString + " -> " + encodeString);

        System.out.println("RSA��Կ��Կ(���ܺ�) ---------------------");
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
     * �ԳƼ��ܣ���Կ����˽Կ���öԳ�˽Կ���ܣ��Գ�˽Կ����
     * �ǶԳƼ��ܣ���Կ������˽Կ���öԷ��Ĺ�Կ���ܣ��Է����Լ���˽Կ����
     */

    /**
     * ����ǩ����Ҳ������ժҪ��������ָ�ƣ���Ϊ����֤��Ϣȷʵ�ǶԷ����͵ģ��м�û�б��������滻��۸�
     * 1���ȶ����½���hash���㣬hashֵh1����ժҪ������hash�㷨��MD2��MD4��MD5��HAVAL��SHA
     * 2�����Լ�˽Կ��ժҪ���ܣ����ɵĶ����С�����ǩ����
     * 3��������ǩ������Email���ĺ��棬һ���͸�B��Ϊ�˷�ֹ�ʼ�������������ü�����Կ���ܣ�
     * 4��B�յ��ʼ�������ù�Կ������ܣ�������Ҫ�����Լ�˽Կ���ܣ���A�Ĺ�Կ������ǩ�����ܣ��ɹ������Emailȷʵ����A��ʧ��˵������ð��
     * 5��B���ʼ�����ִ�й�ϣ����õ�hashֵh2���Ա�����ǩ����hashֵh1���Լ�����õ���h2
     */

    /**
     * ����֤�飺Ϊ�˵õ��Է��ɿ��Ĺ�Կ��Ϊ�˷�ֹ�Է��Ĺ�Կ�ٱ��ر��滻�������ں������֤������
     * 1���Է�ȥ֤��������������֤�飬��������ʱ�䣬��֤�������Է��Ĺ�Կ����Ϣ����֤��������˽Կ����Щ��Ϣ����
     * 2����ͬ��֤�����ĵĹ�Կ���и�֤�飩����������������ȥ��ȡ�������ױ��滻
     * 3���Է��������²�����������ǩ�����ټ�������֤��
     * 4��B�յ��ʼ�������֤�����ĵĸ�֤���������֤�飬�õ��Է��Ĺ�Կ����ȥ��������ǩ������������������֤�����ܱ�֤����Է��Ĺ�Կ����ȷ����
     *   ��֤��������֤֤�飬CA�����ǻ���������Ͽɺ��о���Ȩ���ĵ�������������һ�㱣֤�˸�֤��ľ��Կɿ��������֤�鶼��������ô����������ϵ��������
     */

    /**
     * https:http + ssl/tls
     * tls�������ǿͻ��˷�����������ͬʱ���Լ�֧�ֵļ��ܹ��򷢸����񷽣����ͻ�����֤���񷽷����ĸ�֤�飨�Լ�������ѡ��һ������㷨��hash�㷨����Ȼ��������������ԳƼ���˽Կ�����÷��񷽹�Կ���ܣ��������񷽣�
     * �����յ�˽Կ���ܳɹ�����ü���˽Կ������������Ϣhashֵ���ü���˽Կ����������Ϣ���ͻ��ˣ��ͻ����ü���˽Կ����������Ϣ������hashֵ�Ƿ�����񷽷�������һ�£����ü���˽Կ����������Ϣ����Ϣ��hashֵ�����񷽣�
     * �����յ�������Ϣ���ü���˽Կ���ܲ�����hashֵ�Աȣ���ʱ���ֹ��̽���
     * ssl����Ӧ�ò�֮�£������֮�ϣ����ľ��ǶԷ��񷽺Ϳͻ���֮�以������Ϣ�ü���˽Կ���м��ܺ��ͣ����պ��ü���˽Կ���н���
     *
     * https�г��õ�
     * �ǶԳƼ����㷨��RSA��DSA/DSS
     * �ԳƼ����㷨��AES��RC4��3DES
     * HASH�㷨��MD5��SHA1��SHA256
     *
     * ssl֤����������Ϊ�������еĹ�˾ת�ø�������˾�ˣ����֤�黹������Ч��������˾�Ϳ����������֤�����κ���Ϣ�������õ���ԭ��˾�����壬
     * ���԰䷢֤��ʱ��Ч��һ����2�꣬2����Զ����ڣ���������ȥ����
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

        // ʮ��������ת��Ϊ��������������2����ʮ�������������Եõ�һ���̺�����������2ȥ���̣��ֻ�õ�һ���̺���������˽��У�ֱ����Ϊ0ʱΪֹ��Ȼ����ȵõ���������Ϊ���������ĵ�λ��Чλ����õ���������Ϊ���������ĸ�λ��Чλ��������������
        int a = 10;
        String binary = "";
        while(a != 0) {
            int r = a%2;
            a/=2;
            binary += String.valueOf(r);
        }

        // ʮ����С��ת���ɶ�����С��:��2��ʮ����С�������Եõ�������������������ȡ��������2�����µ�С�����֣��ֵõ�һ�������ٽ�������������ȡ������˽��У�ֱ�����е�С������Ϊ�㣬��ʱ0��1Ϊ�����Ƶ����һλ�����ߴﵽ��Ҫ��ľ���Ϊֹ
        double b = 0.5;
        binary = "";
        while(b != 0) {
            int r = (int)(b * 2);
            b = b - r;
            binary += String.valueOf(r);
        }
    }

    // ci �� branchpipeline:���� + ��һЩ���ǰ�ı�֤������case��ӳ����case��Ӱ����������pft��bda�����׼��
    //       ������-> changepipeline:���룬��master hook -> masterpipeline:���룬��ȫ��master case��ÿ����Ŀ�����˵ù�ע�Լ���dev��master���̵�����������ȣ�
    //       ��RB -> RBpipeline:���룬������Ԥ����

}
