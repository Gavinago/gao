package com.gov.model;

import java.math.*;
import java.util.*;
import java.io.*;

/**
 * table: t_role_rights
 * @author jtj
 *
 */
public class TRoleRights implements Serializable {
    private Integer rightid;
    private Integer roleid;

    public Integer getRightid()
    {
        return rightid;
    }
    public void setRightid(Integer rightid)
    {
        this.rightid = rightid;
    }

  
    public Integer getRoleid()
    {
        return roleid;
    }
    public void setRoleid(Integer roleid)
    {
        this.roleid = roleid;
    }

    public TRoleRights clone(){
        TRoleRights c = new TRoleRights();
        c.setRightid(this.getRightid());
        c.setRoleid(this.getRoleid());
        return c;
    }

    @Override
    public String toString() {
        return "TRoleRights [rightid="+rightid+" roleid="+roleid+"]";
    }
}