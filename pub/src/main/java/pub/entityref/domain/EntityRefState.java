package pub.entityref.domain;

/**
 * 实体的引用状态
 * 
 * @author zhaowei
 *
 */
public class EntityRefState {

	private Long id;

	private EntityInfo entityInfo;

	private int refCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityInfo getEntityInfo() {
		return entityInfo;
	}

	public void setEntityInfo(EntityInfo entityInfo) {
		this.entityInfo = entityInfo;
	}

	public int getRefCount() {
		return refCount;
	}

	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}

	public void increaseRefCount() {
		refCount++;
	}

	public boolean hasRef() {
		return refCount > 0;
	}

	public void decreaseRefCount() {
		refCount--;
	}

}
