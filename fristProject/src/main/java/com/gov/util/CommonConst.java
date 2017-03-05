package com.gov.util;


/**
 * Constant : 常量类
 *
 * @author LiYiLin
 * @since 2015-09-09
 */
public class CommonConst {
	/**
	 * CMS
	 */
	public static final int CONTENT_LINK_NO = 0;			//无链接
	public static final int CONTENT_LINK_INFO = 1;			//内部信息
	public static final int CONTENT_LINK_URL = 2;			//外部链接
	
	public static final int CONTENT_SHOW_TITLE = 0;			//标题
	public static final int CONTENT_SHOW_THUMB = 1;			//略图
	public static final int CONTENT_SHOW_TITLE_THUMB = 2;	//标题+略图
	public static final int CONTENT_SHOW_ABSTRACT = 3;		//摘要
	
	public static final int CONTENT_STATE_REJECT = -2;		//不同意
	public static final int CONTENT_STATE_HIDE = -1;		//隐藏
	public static final int CONTENT_STATE_PREPARE = 0;		//准备
	public static final int CONTENT_STATE_ONLINE = 1;		//上线	
	
	public static final String MODEL_KEY_COLUMNROOT = "columnroot";
	public static final String MODEL_KEY_COLUMNID = "columnid";
	public static final String MODEL_KEY_COLUMN = "column";

}
