package cn.com.solex.ljump.entity;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.solex.ljump.entity.system.SysAuthority;
import cn.com.solex.ljump.entity.system.SysResource;
import cn.com.solex.ljump.entity.system.SysRole;
import cn.com.solex.ljump.entity.system.SysUser;
import cn.com.solex.service.BaseService;
import cn.com.solex.utils.SpringSecurityPasswordUtils;

/**
 * 测试用例，初化始数据
 * 
 * @author Administrator
 * 
 */
public class InitData {
	@Autowired
	private BaseService baseService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.info("初始化数据!!");
		initSysAuthority();
		initSysResource();
		initSysRole();
		initSysUser();
		logger.info("初始化完毕!!");
	}

	private void initSysUser() {
		SysUser sysUser = new SysUser();
		sysUser.setLoginName("admin");
		sysUser.setPassWord(SpringSecurityPasswordUtils.getMd5PasswordEncoder(
				"admin", sysUser.getLoginName()));
		sysUser.setUserName("开发人员");
		sysUser.setEmail("xuenong_li@solex.com");

		sysUser.setRoleList(baseService.getAll(SysRole.class));
		baseService.save(sysUser);
	}

	private void initSysRole() {
		SysRole sysRole = new SysRole();
		sysRole.setName("超级管理员");

		sysRole.setAuthorityList(baseService.getAll(SysAuthority.class));
		baseService.save(sysRole);
	}

	private void initSysResource() {
		SysResource resource = new SysResource();

		resource.setResourceType(SysResource.URL_TYPE);
		resource.setValue("/index**");
		resource.setValueName("首页");
		resource.setPosition(Double.valueOf(0));

		resource.setAuthorityList(baseService.getAll(SysAuthority.class));
		baseService.save(resource);

		resource = new SysResource();

		resource.setResourceType(SysResource.MENU_TYPE);
		resource.setValue("/system/**");
		resource.setValueName("系统管理");
		resource.setShowMenu(true);
		resource.setPosition(Double.valueOf(1));

		resource.setAuthorityList(baseService.getAll(SysAuthority.class));
		baseService.save(resource);

		SysResource resource1 = new SysResource();
		resource1.setParentResource(resource);
		resource1.setShowMenu(true);
		resource1.setResourceType(SysResource.MENU_TYPE);
		resource1.setValue("/system/sysResource");
		resource1.setValueName("资源管理");
		resource1.setPosition(Double.valueOf(2));
		baseService.save(resource1);

		SysResource resource2 = new SysResource();
		resource2.setParentResource(resource);
		resource2.setShowMenu(true);
		resource2.setResourceType(SysResource.MENU_TYPE);
		resource2.setValue("/system/sysAuthority");
		resource2.setValueName("角色管理");
		resource2.setPosition(Double.valueOf(3));
		baseService.save(resource2);

		SysResource resource3 = new SysResource();
		resource3.setParentResource(resource);
		resource3.setShowMenu(true);
		resource3.setResourceType(SysResource.MENU_TYPE);
		resource3.setValue("/system/sysRole");
		resource3.setValueName("权限管理");
		resource3.setPosition(Double.valueOf(4));
		baseService.save(resource3);

		SysResource resource5 = new SysResource();
		resource5.setParentResource(resource);
		resource5.setShowMenu(true);
		resource5.setResourceType(SysResource.MENU_TYPE);
		resource5.setValue("/system/sysUser");
		resource5.setValueName("用户管理");
		resource5.setPosition(Double.valueOf(5));
		baseService.save(resource5);

	}

	private void initSysAuthority() {
		SysAuthority authority = new SysAuthority();
		authority.setName("A_VIEW_USER");
		authority.setDisplayName("查看用户");
		baseService.save(authority);
	}

}
