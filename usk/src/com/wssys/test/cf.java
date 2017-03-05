package com.wssys.test;

import java.util.Arrays;

public class cf {
	public static void main(String[] args) {
        int size = 1186;//总数
        int listCount = 10;//拆分数量
        int[] arr = new int[listCount];
        int avg = size / listCount;//平均数
        int addIndex = size - avg * listCount;//需要增加1个数量的最大下标
        for(int i = 0;i < listCount;++i) {
            arr[i] = i < addIndex ? avg + 1 : avg;
        }
        System.out.println(Arrays.toString(arr));
    }
}
