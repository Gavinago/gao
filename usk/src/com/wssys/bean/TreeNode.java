package com.wssys.bean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * easyui tree bean
 * @author q
 *
 */
public class TreeNode {

    private Integer id;          //要显示的子节点的ID
    private String text;        //要显示的子节点的 Text
    private boolean checked;
    private boolean selected;	//combox是否选择


	private String iconCls;     //节点的图标
    //private String url;			//地址
	private Integer parentId;    //父节点的ID
    private List<TreeNode>  children;   //孩子节点的List
    public Object attributes;	//参数

    
    public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    public Object getAttribute() {
		return attributes;
	}

	public void setAttribute(Object attributes) {
		this.attributes = attributes;
	}

	public TreeNode(){}

    public TreeNode(Integer id, String text, String iconCls,String url, Integer parentId,

           List<TreeNode>children,Object attributes) {

       super();
       this.id= id;
       this.text= text;
       this.iconCls= iconCls;
      // this.url=url;
       this.parentId= parentId;
       this.children= children;
       this.attributes=attributes;
    }

//   
//    public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
    public Integer getId() {

       return id;

    }

    public void setId(Integer id) {
       this.id= id;
    }

    public String getText() {
       return text;
    }

    public void setText(String text) {
       this.text= text;
    }

    public String getIconCls() {
       return iconCls;
    }

    public void setIconCls(String iconCls) {
       this.iconCls= iconCls;
    }

    public Integer getParentId(){ 
       return parentId;
    }

    public void setParentId(Integer parentId) {
       this.parentId= parentId;
    }

    public List<TreeNode> getChildren() {
       return children;
    }

    public void setChildren(List<TreeNode> children) {
       this.children= children;
    }

    //添加孩子的方法

    public void addChild(TreeNode node){
       if(this.children == null){
           children= new ArrayList<TreeNode>();
           children.add(node);
       }else{
           children.add(node);
       }
          
    }
   
}