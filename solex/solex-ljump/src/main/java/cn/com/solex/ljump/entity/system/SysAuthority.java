package cn.com.solex.ljump.entity.system;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.solex.entity.BaseEntity;

/**
 * 角色
 * 
 * @author xuenong_li
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "SYS_AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysAuthority extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3236793224545602379L;

	private String name;// 角色名称

	private String displayName;// 角色的标志

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
