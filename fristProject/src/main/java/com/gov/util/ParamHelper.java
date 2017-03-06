package com.gov.util;


public class ParamHelper {

		public static void NormalizePage(ParamPage param){
			if(param==null)return;
			if(param.getPageNum()==null || param.getPageNum().intValue()<1)
				param.setPageNum(1);
			if(param.getPageSize()==null || param.getPageSize().intValue()<1)
				param.setPageSize(15);
		}

}
