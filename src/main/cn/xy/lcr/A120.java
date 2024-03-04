package cn.xy.lcr;

/**
 * @author xiangyu
 * @date 2024-03-04 0:20
 * 设备中存有 n 个文件，文件 id 记于数组 documents。若文件 id 相同，则定义为该文件存在副本。请返回任一存在副本的文件 id。
 * <p>
 * 0 ≤ documents[i] ≤ n-1
 * 2 <= n <= 100000
 */
public class A120 {
    /**
     * hash 略过不写，看到了一种原地hash的思路可以写一哈
     *
     * @param documents
     * @return
     */
    public int findRepeatDocument(int[] documents) {
        int i = 0;
        while(i < documents.length) {
            if(documents[i] == i) {
                i++;
                continue;
            }
            if(documents[documents[i]] == documents[i]) {
                return documents[i];
            }
            int tmp = documents[i];
            documents[i] = documents[tmp];
            documents[tmp] = tmp;
        }
        return -1;
    }


    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
