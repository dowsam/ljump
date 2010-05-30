package cn.com.solex.ljump.entity.system;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.solex.entity.BaseEntity;

import com.google.common.collect.Lists;

/**
 * 权限
 * 
 * @author xuenong_li
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "SYS_ROLES")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 8118464017410776914L;

	private String name;

	private List<SysAuthority> authorityList = Lists.newArrayList();

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "SYS_ROLES_AUTHORITIES", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<SysAuthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<SysAuthority> authorityList) {
		this.authorityList = authorityList;
	}
}
