package com.wssys.test;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONObject;
//
//import org.json.JSONArray;
//
//import cn.com.zhongway.base.database.eoms.dbutils.DBProxy;
//import cn.com.zhongway.base.database.eoms.dbutils.DBUtilInterface;
//import cn.com.zhongway.base.database.eoms.dbutils.DbUtil;
//import cn.com.zhongway.base.pub.common.esb.ESBServerClassInterface;
////
///**
// * 2013/4/12
// * 
// * @author leon
// * 
// */
//public class GetComboTreeJson implements ESBServerClassInterface {
//	public String deal(HttpServletRequest request,
//			HttpServletResponse response, Object arg2) throws Exception {
//		String jsonData = "";
//		// 获取传入参数
//		String ls_sqlid = request.getParameter("in_sqlid");// 子节点查询条件
//
//		DBProxy proxy = new DBProxy(new DbUtil(), request);
//		DBUtilInterface db = proxy.getDb();
//		// 传入参数名称
//		String[] paramName = { "in_sqlid"};
//		// 传入参数
//		String[] param = { ls_sqlid };
//		// 返回游标的名称
//		String[] cursor = { "queryresult"};
//		// 执行的存储过程
//		String proc = "{call proc_cz_query_comboTree(?,?)}";
//		Map dataMap = db.callproc_more_cursor(proc, paramName, param, cursor);// 获取数据
//		List dataList = (List) dataMap.get("queryresult");
//		
//		TreeNode treeNode = this.getTreeNode(dataList);
//		String str = "["+getTree(treeNode).substring(0,getTree(treeNode).length()-1)+"]";
//		
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().print(str);
//		return null;
//	}
//	//递归输出子节点
////	public void printChild(TreeNode rootNode){
////		for(TreeNode childNode : rootNode.getChildList()){
////			StringBuilder nodePrefix = new StringBuilder();
////			for(int i=0;i<childNode.getLevel();i++){
////				nodePrefix.append("  ");
////			}
////			
////			if(childNode.isLeaf()==true){
////				nodePrefix.append("--");
////			}else{
////				nodePrefix.append("++");
////			}
////			System.out.println(nodePrefix+"{\"id\":\""+childNode.getId()+"\",\"text\":\""+childNode.getText()+"\"}");
////			//str += (nodePrefix);
////			this.printChild(childNode);
////		}
////	}
//	public String getTree(TreeNode rootNode){
//		String str="";
//		for(TreeNode childNode : rootNode.getChildList()){
//			if(childNode.isLeaf()==true){
//				str+= "{id:"+childNode.getId()+",text:'"+childNode.getText()+"'},";
//			}else{
//				str+= "{id:"+childNode.getId()+",text:'"+childNode.getText()+"',children:["+getTree(childNode).substring(0,getTree(childNode).length()-1)+"]},";
//			}
//		}
//		return str;
//	}
//	
//	//得到根节点
//	public TreeNode getTreeNode(List dataList){
//		//Map rootMap = (Map) dataList.get(0);
//		
//		TreeNode rootNode = new TreeNode();
//		rootNode.setId("");
//		rootNode.setText("");
////		rootNode.setId(rootMap.get("ID").toString());
////		rootNode.setText(rootMap.get("TEXT").toString());
//		rootNode.setLeaf(false);
//		rootNode.setLevel(0);
//		
//		List<TreeBean> nodeList = this.getNodeList(dataList);
//		
//		rootNode = this.constructTree(rootNode, nodeList, 0);
//		return rootNode;
//	}
//	//转换数据格式List<Map> to List<TreeBean>
//	public List<TreeBean> getNodeList(List queryResult){
//		List<TreeBean> nodeList = new ArrayList<TreeBean>();
//		TreeBean bean = null;
//		for(int i=0;i<queryResult.size();i++){
//			Map curMap = (Map) queryResult.get(i);
//			String id = curMap.get("ID").toString();
//			String text = curMap.get("TEXT").toString();
//			String pid = curMap.get("PID").toString();
//			bean = new TreeBean(id,text,pid);
//			nodeList.add(bean);
//		}
//		return nodeList;
//	}
//	//递归得到树
//	public TreeNode constructTree(TreeNode rootNode,List<TreeBean> nodeList,int rootLevel){
//		List<TreeNode> childList = new ArrayList<TreeNode>();
//		//构造根节点
//		for(int i=0;i<nodeList.size();i++){
//			TreeBean bean = nodeList.get(i);
//			if(bean.getPid().equalsIgnoreCase(rootNode.getId())){
//				TreeNode childNode = new TreeNode();
//				childNode.setId(bean.getId());
//				childNode.setText(bean.getText());
//				childNode.setParent(rootNode);
//				childNode.setLevel(rootLevel+1);
//				
//				childList.add(childNode);
//			}
//		}
//		//设置子节点
//		rootNode.setChildList(childList);
//		//设置是否为叶子节点
//		if(childList.size()==0){
//			rootNode.setLeaf(true);
//		}else{
//			rootNode.setLeaf(false);
//		}
//		//递归构造子节点
//		for(int i=0;i<childList.size();i++){
//			constructTree(childList.get(i), nodeList, ++rootLevel);
//			--rootLevel;
//		}
//		return rootNode;
//	}
//}