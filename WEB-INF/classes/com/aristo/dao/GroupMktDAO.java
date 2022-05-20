package com.aristo.dao;

import java.sql.Connection;
import java.util.List;

import com.aristo.valueobject.GroupMktFormBean;

public interface GroupMktDAO {
	public List getAllGroupMkt(Connection con, String typ,int eyear);
	
	public int AddGroupMkt(GroupMktFormBean tfb, Connection con, String typ,int eyear);
	
	
}
