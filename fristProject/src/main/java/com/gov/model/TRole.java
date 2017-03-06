package com.gov.model;

import java.math.*;
import java.util.*;
import java.io.*;

/**
 * table: t_role
 * @author jtj
 *
 */
public class TRole implements Serializable {
    private Integer roleid;
    private String rolename;
    private String rolecode;
    private String roleparam;
    private String rolememo;
    private Date timestamp;

   
    public Integer getRoleid()
    {
        return roleid;
    }
    public void setRoleid(Integer roleid)
    {
        this.roleid = roleid;
    }

    public String getRolename()
    {
        return rolename;
    }
    public void setRolename(String rolename)
    {
        this.rolename = rolename;
    }

    public String getRolecode()
    {
        return rolecode;
    }
    public void setRolecode(String rolecode)
    {
        this.rolecode = rolecode;
    }

 
    public String getRoleparam()
    {
        return roleparam;
    }
    public void setRoleparam(String roleparam)
    {
        this.roleparam = roleparam;
    }

    public String getRolememo()
    {
        return rolememo;
    }
    public void setRolememo(String rolememo)
    {
        this.rolememo = rolememo;
    }
    public Date getTimestamp()
    {
        return timestamp;
    }
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    public TRole clone(){
        TRole c = new TRole();
        c.setRoleid(this.getRoleid());
        c.setRolename(this.getRolename());
        c.setRolecode(this.getRolecode());
        c.setRoleparam(this.getRoleparam());
        c.setRolememo(this.getRolememo());
        c.setTimestamp(this.getTimestamp());
        return c;
    }

    @Override
    public String toString() {
        return "TRole [roleid="+roleid+
			",rolename="+rolename+
			",rolecode="+rolecode+
			",roleparam="+roleparam+
			",rolememo="+rolememo+
			",timestamp="+timestamp+"]";
    }
    
   
}