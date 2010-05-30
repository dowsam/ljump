package cn.com.solex.ljump.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import cn.com.solex.dao.BaseDao;
import cn.com.solex.ljump.entity.OperatorUser;
import cn.com.solex.ljump.entity.system.SysAuthority;
import cn.com.solex.ljump.entity.system.SysRole;
import cn.com.solex.ljump.entity.system.SysUser;
import cn.com.solex.utils.DateUtils;

import com.google.common.collect.Lists;

/**
 * 实现spring security 用户对象
 * 
 * @author Administrator
 * 
 */
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private BaseDao baseDao;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		SysUser user = baseDao.findUniqueBy(SysUser.class, "loginName",
				userName);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + userName + " 不存在");
		}
		List<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);
		OperatorUser userdetail = new OperatorUser(user.getLoginName(), user
				.getPassWord(), user.isDisabled(), user.isAccountExpired(),
				user.isCredentialsExpired(), user.isAccountLocked(),
				grantedAuths);
		userdetail.setLoginName(user.getUserName());
		userdetail.setLoginTime(DateUtils.formatDate(new Date(), null));
		userdetail.setRoleList(user.getRoleList());
		return userdetail;
	}

	public List<GrantedAuthority> obtainGrantedAuthorities(SysUser user) {
		List<GrantedAuthority> authList = Lists.newArrayList();
		for (SysRole role : user.getRoleList()) {
			for (SysAuthority auth : role.getAuthorityList()) {
				authList.add(new GrantedAuthorityImpl(auth.getName()));
			}
		}
		return authList;
	}

}
