package com.wssys.test;

import java.util.UUID;

import com.wssys.utils.StringUtil;

public class testhh {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(StringUtil.getGoodsnum());
		System.out.println(UUID.randomUUID());
		String uuid=String.valueOf(UUID.randomUUID());
		String [] prefix=uuid.split("-", -1);
		String goodsnum=prefix[0]+StringUtil.getGoodsnum();
		System.out.println(goodsnum);
	}

}
