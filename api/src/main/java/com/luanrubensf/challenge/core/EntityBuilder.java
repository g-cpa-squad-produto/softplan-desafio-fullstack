package com.luanrubensf.challenge.core;

import java.util.Objects;


public abstract class EntityBuilder<T> implements IBuilder<T> {

    protected T entity;

    private boolean validate = true;

    protected final EntityState state;

    protected EntityBuilder(T entity, EntityState state) {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(state);

//        ContextProvider.get().getAutowireCapableBeanFactory()
//                .autowireBean(this);

        this.entity = entity;
        this.state = state;
    }

    public <B extends EntityBuilder<T>> B validate(final boolean validate) {
        this.validate = validate;
        return (B) this;
    }

    public <B extends EntityBuilder<T>> B skipValidate() {
        return (B) this.validate(false);
    }

    public void onBuild() {

    }

    @Override
    public final T build() {
        Objects.requireNonNull(entity);

        onBuild();

        try {
            return entity;
        } finally {
            entity = null;
        }
    }

    protected enum EntityState {
        NEW,
        BUILT
    }
}
