package com.luanrubensf.challenge.representation;

import com.luanrubensf.challenge.core.EntityBuilder;
import com.luanrubensf.challenge.core.IEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractRepresentation<T extends IEntity, D extends IEntityDto, B extends EntityBuilder> {

    @Autowired
    private EntityManager entityManager;

    private Class<T> entityClass;

    private Supplier<B> create;

    private Function<T, B> from;


    protected AbstractRepresentation(Supplier<B> create, Function<T, B> from) {
        Objects.requireNonNull(create);
        Objects.requireNonNull(from);

        this.create = create;
        this.from = from;

        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) (type).getActualTypeArguments()[0];
    }

    public abstract D toRepresentation(T entity);

    public abstract T fromRepresentation(D dto, B builder);

    public T fromRepresentation(D dto) {
        if (dto.getId() != null) {
            T originalEntity = getOriginalEntity(dto);
            return fromRepresentation(dto, from.apply(originalEntity));
        }

        return fromRepresentation(dto, create.get());
    }

    public List<D> toRepresentation(List<T> entities) {
        Objects.requireNonNull(entities);

        return entities.stream()
                .map(this::toRepresentation)
                .collect(Collectors.toList());
    }

    public List<T> fromRepresentation(List<D> dtos) {
        Objects.requireNonNull(dtos);

        return dtos.stream()
                .map(this::fromRepresentation)
                .collect(Collectors.toList());
    }

    private T getOriginalEntity(D dto) {
        return entityManager.find(entityClass, dto.getId());
    }
}
