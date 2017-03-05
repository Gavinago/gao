package com.wssys.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class testsz {
	public static void main(String[] args) {
		Integer[] i = new Integer[] { 1, 2, 3 };
		Integer[] k = new Integer[] { 2, 4, 5 };

		// 测试insect
		Integer[] result_insect = intersect(i, k);
		System.out.println("求交集的结果如下：");
		for (Integer str : result_insect) {
			System.out.println(str);
		}

		List s = new ArrayList();
		// List s=Arrays.asList(i);
		for (int ii = 0; ii < i.length; ii++) {
			s.add(i[ii]);
		}
		for (int ii = 0; ii < s.size(); ii++) {
			for (Integer str : result_insect) {
				// System.out.println(str);
				Integer v = (Integer) s.get(ii);
				if (v.compareTo(str)==0) {
					s.remove(ii);
				}
			}
		}

		Integer[] endresult = (Integer[]) s.toArray(new Integer[s.size()]);
		System.out.println("最终返回：" + Arrays.toString(endresult));
		// Integer[] result_minus = minus(i, k);
		// System.out.println("求差集的结果如下：");
		// for (Integer str : result_minus) {
		// System.out.println(str);
		// }
	}

	// 求两个数组的交集
	public static Integer[] intersect(Integer[] arr1, Integer[] arr2) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str, Boolean.FALSE);
			}
		}
		for (Integer str : arr2) {
			if (map.containsKey(str)) {
				map.put(str, Boolean.TRUE);
			}
		}

		for (Entry<Integer, Boolean> e : map.entrySet()) {
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}

		Integer[] result = {};
		return list.toArray(result);
	}

	// 求两个数组的差集
	public static Integer[] minus(int[] arr1, int[] arr2) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> history = new LinkedList<Integer>();
		int[] longerArr = arr1;
		int[] shorterArr = arr2;
		// 找出较长的数组来减较短的数组
		if (arr1.length > arr2.length) {
			longerArr = arr2;
			shorterArr = arr1;
		}
		for (Integer str : longerArr) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (Integer str : shorterArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			} else {
				if (!history.contains(str)) {
					list.add(str);
				}
			}
		}

		Integer[] result = {};
		return list.toArray(result);
	}
}
