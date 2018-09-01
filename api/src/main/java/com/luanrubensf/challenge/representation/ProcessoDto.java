package com.luanrubensf.challenge.representation;

import com.luanrubensf.challenge.model.Processo;
import org.springframework.stereotype.Component;

public class ProcessoDto implements IEntityDto<Long> {

    private Long id;
    private String name;
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Component
    public static class Representation extends AbstractRepresentation<Processo, ProcessoDto, Processo.Builder> {

        public Representation() {
            super(Processo.Builder::create, Processo.Builder::from);
        }

        @Override
        public ProcessoDto toRepresentation(Processo entity) {
            return Builder.create()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .build();
        }

        @Override
        public Processo fromRepresentation(ProcessoDto dto, Processo.Builder builder) {
            return builder
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .build();
        }
    }

    public static class Builder {
        private ProcessoDto entity;

        private Builder(ProcessoDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new ProcessoDto());
        }

        public static Builder from(ProcessoDto entity) {
            return new Builder(entity);
        }

        public Builder id(Long id) {
            entity.id = id;
            return this;
        }

        public Builder name (String name) {
            entity.name = name;
            return this;
        }
        public Builder description (String description) {
            entity.description = description;
            return this;
        }

        public ProcessoDto build() {
            return entity;
        }
    }
}
