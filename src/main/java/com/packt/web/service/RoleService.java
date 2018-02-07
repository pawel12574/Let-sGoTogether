package com.packt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.RoleDaoInterface;
import com.packt.web.bean.Role;

@Service
@Transactional
public class RoleService implements RoleServiceInterface {

	@Autowired
	RoleDaoInterface roleDao;
	
	public Role getRole(int id) {
		return roleDao.getRole(id);
		
	}

}
