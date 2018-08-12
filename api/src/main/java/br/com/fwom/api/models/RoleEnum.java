package br.com.fwom.api.models;

public enum RoleEnum {
    ADMIN(1L), TRIAL(2L), EDITOR(3L);

    private final long value;

    RoleEnum(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }
}
