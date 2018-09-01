package com.luanrubensf.challenge.representation;

import com.luanrubensf.challenge.model.Parecer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ParecerDto implements IEntityDto<Long> {

    private Long id;
    private String parecer;
    private UserDto user;
    private ProcessoDto processo;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ProcessoDto getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoDto processo) {
        this.processo = processo;
    }

    @Component
    public static class Representation extends AbstractRepresentation<Parecer, ParecerDto, Parecer.Builder> {

        @Autowired
        private UserDto.Representation userRepresentation;

        @Autowired
        private ProcessoDto.Representation processoRepresentation;

        public Representation() {
            super(Parecer.Builder::create, Parecer.Builder::from);
        }

        @Override
        public ParecerDto toRepresentation(Parecer entity) {
            return Builder.create()
                    .id(entity.getId())
                    .parecer(entity.getParecer())
                    .user(userRepresentation.toRepresentation(entity.getUser()))
                    .processoDto(processoRepresentation.toRepresentation(entity.getProcesso()))
                    .build();
        }

        @Override
        public Parecer fromRepresentation(ParecerDto dto, Parecer.Builder builder) {
            return builder
                    .parecer(dto.getParecer())
                    .user(userRepresentation.fromRepresentation(dto.getUser()))
                    .processo(processoRepresentation.fromRepresentation(dto.getProcesso()))
                    .build();
        }
    }

    public static class Builder {
        private ParecerDto entity;

        private Builder(ParecerDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new ParecerDto());
        }

        public static Builder from(ParecerDto entity) {
            return new Builder(entity);
        }

        public Builder id(Long id) {
            entity.id = id;
            return this;
        }

        public Builder parecer(String parecer) {
            entity.parecer = parecer;
            return this;
        }

        public Builder user(UserDto user) {
            entity.user = user;
            return this;
        }

        public Builder processoDto(ProcessoDto processoDto) {
            entity.processo = processoDto;
            return this;
        }

        public ParecerDto build() {
            return entity;
        }
    }
}
