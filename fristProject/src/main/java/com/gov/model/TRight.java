package com.gov.model;

import java.math.*;
import java.util.*;
import java.io.*;

/**
 * table: t_right
 * @author jtj
 *
 */
public class TRight implements Serializable {
    private Integer rightid;
    private Integer folderid;
    private String rightname;
    private String righturl;
    private String rightcode;
    private String rightparam;
    private String rightmemo;
    private Date timestamp;

    public Integer getRightid()
    {
        return rightid;
    }
    public void setRightid(Integer rightid)
    {
        this.rightid = rightid;
    }

    public Integer getFolderid()
    {
        return folderid;
    }
    public void setFolderid(Integer folderid)
    {
        this.folderid = folderid;
    }

    public String getRightname()
    {
        return rightname;
    }
    public void setRightname(String rightname)
    {
        this.rightname = rightname;
    }

 
    public String getRighturl()
    {
        return righturl;
    }
    public void setRighturl(String righturl)
    {
        this.righturl = righturl;
    }

    public String getRightcode()
    {
        return rightcode;
    }
    public void setRightcode(String rightcode)
    {
        this.rightcode = rightcode;
    }

    public String getRightparam()
    {
        return rightparam;
    }
    public void setRightparam(String rightparam)
    {
        this.rightparam = rightparam;
    }

  
    public String getRightmemo()
    {
        return rightmemo;
    }
    public void setRightmemo(String rightmemo)
    {
        this.rightmemo = rightmemo;
    }

 
    public Date getTimestamp()
    {
        return timestamp;
    }
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    public TRight clone(){
        TRight c = new TRight();
        c.setRightid(this.getRightid());
        c.setFolderid(this.getFolderid());
        c.setRightname(this.getRightname());
        c.setRighturl(this.getRighturl());
        c.setRightcode(this.getRightcode());
        c.setRightparam(this.getRightparam());
        c.setRightmemo(this.getRightmemo());
        c.setTimestamp(this.getTimestamp());
        return c;
    }

    @Override
    public String toString() {
        return "TRight [rightid="+rightid+
			",folderid="+folderid+
			",rightname="+rightname+
			",righturl="+righturl+
			",rightcode="+rightcode+
			",rightparam="+rightparam+
			",rightmemo="+rightmemo+
			",timestamp="+timestamp+"]";
    }
 
}