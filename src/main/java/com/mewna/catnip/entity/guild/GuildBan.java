package com.mewna.catnip.entity.guild;

import com.mewna.catnip.entity.Entity;
import com.mewna.catnip.entity.user.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author amy
 * @since 10/6/18.
 */
public interface GuildBan extends Entity {
    @Nonnull
    User user();
    
    @Nullable
    String reason();
}
