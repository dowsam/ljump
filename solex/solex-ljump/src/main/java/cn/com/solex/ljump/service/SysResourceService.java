package cn.com.solex.ljump.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.solex.ljump.entity.system.SysResource;
import cn.com.solex.service.BaseService;

@Service
@Transactional
public class SysResourceService extends BaseService {

	/**
	 * 查找URL类型的资源并初始化可访问该资源的授权.
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SysResource> getUrlResourceWithAuthorities() {
		Query query = createQuery(SysResource.QUERY_BY_RESOURCETYPE_WITH_NO_NULL_AUTHORITY);
		return distinct(query).list();
	}
}
