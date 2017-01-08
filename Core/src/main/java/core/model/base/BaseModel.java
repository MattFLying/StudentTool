package core.model.base;

import model.entity.Entity;

public abstract class BaseModel {
	protected abstract class Builder<T> {
		public abstract T convertFromEntity(Entity entity);
	}
}