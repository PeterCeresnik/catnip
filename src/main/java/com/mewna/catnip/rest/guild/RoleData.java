package com.mewna.catnip.rest.guild;

import com.mewna.catnip.entity.util.Permission;
import com.mewna.catnip.util.JsonConvertible;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.LongUnaryOperator;

@Accessors(fluent = true)
@Getter
@Setter
public class RoleData implements JsonConvertible {
    private final int id;
    private final boolean publicRole;
    private Long permissions;
    private String name;
    private Integer color;
    private Integer position;
    private Boolean mentionable;
    private Boolean hoisted;
    
    public RoleData(@Nonnegative final int id) {
        this.id = id;
        publicRole = id == 0;
    }
    
    @Nonnull
    @CheckReturnValue
    public static RoleData create(@Nonnegative final int id) {
        if(id == 0) {
            return new PublicRoleData();
        }
        return new RoleData(id);
    }
    
    @Nonnull
    public RoleData permissions(@Nullable final Long permissions) {
        this.permissions = permissions;
        return this;
    }
    
    @Nonnull
    public RoleData permissions(@Nonnull final Permission... permissions) {
        return permissions(Permission.from(permissions));
    }
    
    @Nonnull
    public RoleData permissions(@Nonnull final Iterable<Permission> permissions) {
        return permissions(Permission.from(permissions));
    }
    
    @Nonnull
    public RoleData addPermissions(@Nonnull final Permission... permissions) {
        return addPermissions(Arrays.asList(permissions));
    }
    
    @Nonnull
    public RoleData addPermissions(@Nonnull final Iterable<Permission> permissions) {
        //1101 | 0010
        //1111
        return updatePermissions(v->v | Permission.from(permissions));
    }
    
    @Nonnull
    public RoleData removePermissions(@Nonnull final Permission... permissions) {
        return addPermissions(Arrays.asList(permissions));
    }
    
    @Nonnull
    public RoleData removePermissions(@Nonnull final Iterable<Permission> permissions) {
        //1111 & ~0010
        //1111 & 1101
        //1101
        return updatePermissions(v->v & ~Permission.from(permissions));
    }
    
    @Nonnull
    public RoleData updatePermissions(@Nonnull final LongUnaryOperator updater) {
        return permissions(updater.applyAsLong(permissions == null ? 0 : permissions));
    }
    
    @Override
    @Nonnull
    @CheckReturnValue
    public JsonObject toJson() {
        final JsonObject object = new JsonObject().put("id", Integer.toString(id));
        if(permissions != null) {
            object.put("permissions", permissions);
        }
        if(name != null) {
            object.put("name", name);
        }
        if(color != null) {
            object.put("color", color & 0xFFFFFF);
        }
        if(position != null) {
            object.put("position", position);
        }
        if(mentionable != null) {
            object.put("mentionable", mentionable);
        }
        if(hoisted != null) {
            object.put("hoisted", hoisted);
        }
        return object;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof RoleData && ((RoleData) obj).id == id;
    }
    
    @Override
    public String toString() {
        return "RoleData(id = " + id + ')';
    }
    
    private static class PublicRoleData extends RoleData {
        PublicRoleData() {
            super(0);
        }
    
        @Override
        public RoleData name(final String name) {
            throw new IllegalStateException("Cannot change name of public role");
        }
    
        @Override
        public RoleData color(final Integer color) {
            throw new IllegalStateException("Cannot change color of public role");
        }
    
        @Override
        public RoleData position(final Integer position) {
            throw new IllegalStateException("Cannot change position of public role");
        }
    
        @Override
        public RoleData mentionable(final Boolean mentionable) {
            throw new IllegalStateException("Cannot change mentionable of public role");
        }
    
        @Override
        public RoleData hoisted(final Boolean mentionable) {
            throw new IllegalStateException("Cannot change hoisted of public role");
        }
    }
}
