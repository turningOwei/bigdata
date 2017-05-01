package pub.entityref.domain;

/**
 * 实体的描述信息。
 * 
 * @author zhaowei
 *
 */
public class EntityInfo {

	private String entityType;

	private String entityId;

	public EntityInfo() {
	};

	public EntityInfo(Object entity, String entityId) {
		this.entityType = entity.getClass().getName();
		this.entityId = entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}
