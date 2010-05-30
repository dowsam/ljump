package cn.com.solex.ljump.entity.system;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.solex.entity.BaseEntity;
import cn.com.solex.utils.ReflectionUtils;

import com.google.common.collect.Lists;

/**
 * 用户
 * 
 * @author xuenong_li
 * 
 */

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "SYS_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -499151144962115582L;

	
	private String loginName;// 登入的工号
	private String passWord;// 密码
	private String userName;// 名称
	private String email;// 邮箱
	private boolean disabled = true;// 指示用户是否启用或禁用。
	private boolean accountExpired = true;// 指示是否在用户的帐户已过期。
	private boolean accountLocked = true;// 指示用户是否被锁定或解锁。
	private boolean credentialsExpired = true;// 指示是否用户的凭据（密码）已过期。

	private List<SysRole> roleList = Lists.newArrayList();

	@Transient
	public String getRoleListName() {
		return ReflectionUtils.convertElementPropertyToString(roleList, "name",
				",");
	}

	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 多对多定义
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	// Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	// 集合按id排序.
	@OrderBy("id desc")
	// 集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

}
