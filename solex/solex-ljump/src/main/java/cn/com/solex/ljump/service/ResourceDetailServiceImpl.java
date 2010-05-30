package cn.com.solex.ljump.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.com.solex.ljump.entity.system.SysResource;
import cn.com.solex.security.ResourceDetailsService;

/**
 * 实现读取url_role注入
 * 
 * @author Administrator
 * 
 */
@Transactional(readOnly = true)
public class ResourceDetailServiceImpl implements ResourceDetailsService {

	@Autowired
	private SysResourceService service;

	@Override
	public LinkedHashMap<String, String> getRequestMap() throws Exception {
		List<SysResource> resourceList = service
				.getUrlResourceWithAuthorities();
		LinkedHashMap<String, String> requestMap = new LinkedHashMap<String, String>();
		for (SysResource resource : resourceList) {
			requestMap.put(resource.getValue(), resource.getAuthNames());
		}
		return requestMap;
	}

}
