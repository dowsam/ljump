package cn.com.solex.ljump.entity.system;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import cn.com.solex.entity.BaseEntity;
import cn.com.solex.utils.ReflectionUtils;

import com.google.common.collect.Lists;

/**
 * 资 源
 * 
 * @author xuenong_li
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "SYS_RESOURCES")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysResource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5974969493902212528L;

	public static final String QUERY_BY_RESOURCETYPE_WITH_AUTHORITY = "from SysResource r left join fetch r.authorityList WHERE r.resourceType=? ORDER BY r.position ASC";
	public static final String QUERY_BY_RESOURCETYPE_WITH_NO_NULL_AUTHORITY = "from SysResource r left join fetch r.authorityList WHERE r.value is not null ORDER BY r.position ASC";
	public static final String QUERY_BY_RESOURCEPARENT_WHERE_NULL = "from SysResource r left join fetch r.childResource where r.parentId is null and r.resourceType=? order by r.position asc";
	// -- resourceType常量 --//
	public static final String URL_TYPE = "url";
	public static final String MENU_TYPE = "menu";

	private String resourceType;// 资源类型
	private String valueName;// 资源名称
	private String value;// 资源标识
	private double position;// 资源排序字段

	private boolean isShowMenu = false;// 是否左边显示

	public boolean isShowMenu() {
		return isShowMenu;
	}

	public void setShowMenu(boolean isShowMenu) {
		this.isShowMenu = isShowMenu;
	}

	private List<SysAuthority> authorityList = Lists.newArrayList();

	private String parentId;

	private SysResource parentResource;

	private List<SysResource> childResource = Lists.newArrayList();

	/**
	 * 可访问该资源的授权名称字符串, 多个授权用','分隔.
	 */
	@Transient
	public String getAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(authorityList,
				"name", ",");
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	@ManyToMany
	@JoinTable(name = "SYS_RESOURCES_AUTHORITIES", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.JOIN)
	@OrderBy("id DESC")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<SysAuthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<SysAuthority> authorityList) {
		this.authorityList = authorityList;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	@Column(insertable = false, updatable = false)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@ManyToOne
	@JoinColumn(name = "parentId")
	public SysResource getParentResource() {
		return parentResource;
	}

	public void setParentResource(SysResource parentResource) {
		this.parentResource = parentResource;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parentResource")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<SysResource> getChildResource() {
		return childResource;
	}

	public void setChildResource(List<SysResource> childResource) {
		this.childResource = childResource;
	}
}
