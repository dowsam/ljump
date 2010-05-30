package cn.com.solex.listener;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.solex.entity.BaseEntity;
import cn.com.solex.utils.SpringSecurityUtils;


/**
 * 实体监听器（保存或更新）
 * @author xuenong_li
 *
 */
public class EntityListener implements SaveOrUpdateEventListener {

	private static final long serialVersionUID = 8125251878646756031L;
	private static Logger logger=LoggerFactory.getLogger(EntityListener.class);

	public void onSaveOrUpdate(SaveOrUpdateEvent arg0)
			throws HibernateException {
		Object object = arg0.getObject();

		//如果对象是AuditableEntity子类,添加审计信息.
		if (object instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) object;
			String loginName = SpringSecurityUtils.getCurrentUserName();

			if (entity.getId() == null) {
				//创建新对象
				entity.setCreateTime(new Date());
				entity.setCreateBy(loginName);
			} else {
				//修改旧对象
				entity.setLastModifyTime(new Date());
				entity.setLastModifyBy(loginName);

				logger.info("{}对象(ID:{}) 被 {} 在 {} 修改", new Object[] { arg0.getEntityName(), entity.getId(),
						loginName, new Date() });
			}
		}
	}

}
