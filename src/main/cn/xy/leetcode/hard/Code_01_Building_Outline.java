package cn.xy.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 给定一个N行3列二维数组， 每一行表示有一座大楼， 一共有N座
 * 大楼。 所有大楼的底部都坐落在X轴上， 每一行的三个值
 * (a,b,c)代表每座大楼的从(a,0)点开始， 到 (b,0)点结束， 高
 * 度为c。 输入的数据可以保证a<b,且a， b， c均为正数。 大楼之
 * 间可以有重合。 请输出整体的轮廓线。
 * 例子： 给定一个二维数组 [ [1, 3, 3], [2, 4, 4], [5, 6,
 * 1] ]
 * 输出为轮廓线 [ [1, 2, 3], [2, 4, 4], [5, 6, 1] ]
 */
public class Code_01_Building_Outline {
	/**
	 * 建立一个数据结构，位置，高度，上下
	 */
	public static class Node {
		public boolean isUp;
		public int posi;
		public int h;

		public Node(boolean bORe, int position, int height) {
			isUp = bORe;
			posi = position;
			h = height;
		}
	}

	public static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.posi != o2.posi) {				
				return o1.posi - o2.posi;
			}
			if (o1.isUp != o2.isUp) {
				return o1.isUp ? -1 : 1;
			}
			return 0;
		}
	}

	public static List<List<Integer>> buildingOutline(int[][] buildings) {
		Node[] nodes = new Node[buildings.length * 2];
		//生成大楼信息
		for (int i = 0; i < buildings.length; i++) {
			nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
			nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
		}
		//按照位置排序
		Arrays.sort(nodes, new NodeComparator());
		//高度，高度出现的次数
		TreeMap<Integer, Integer> htMap = new TreeMap<>();
		//当前位置的最大高度
		TreeMap<Integer, Integer> pmMap = new TreeMap<>();
		//
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isUp) {
				if (!htMap.containsKey(nodes[i].h)) {
					htMap.put(nodes[i].h, 1);
				} else {
					htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
				}
			} else {

				if (htMap.containsKey(nodes[i].h)) {
					if (htMap.get(nodes[i].h) == 1) {
						htMap.remove(nodes[i].h);
					} else {
						htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
					}
				}
			}

			if (htMap.isEmpty()) {
				pmMap.put(nodes[i].posi, 0);
			} else {
				pmMap.put(nodes[i].posi, htMap.lastKey());
			}
		}
		List<List<Integer>> res = new ArrayList<>();
		int start = 0;
		int height = 0;
		for (Entry<Integer, Integer> entry : pmMap.entrySet()) {
			//当前位置
			int curPosition = entry.getKey();
			//当前高度
			int curMaxHeight = entry.getValue();
			if (height != curMaxHeight) {
				if (height != 0) {
					List<Integer> newRecord = new ArrayList<Integer>();
					newRecord.add(start);
					newRecord.add(curPosition);
					newRecord.add(height);
					res.add(newRecord);
				}
				start = curPosition;
				height = curMaxHeight;
			}
		}
		return res;
	}

}
