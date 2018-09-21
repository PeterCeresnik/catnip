package com.mewna.catnip.entity.impl;

import com.mewna.catnip.entity.Presence;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author amy
 * @since 9/21/18.
 */
@Getter
@Setter
@Builder
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class PresenceImpl implements Presence {
    private OnlineStatus status;
    private Activity activity;
    
    @Getter
    @Setter
    @Builder
    @Accessors(fluent = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityTimestampsImpl implements ActivityTimestamps {
        private long start;
        private long end;
    }
    
    @Getter
    @Setter
    @Builder
    @Accessors(fluent = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityPartyImpl implements ActivityParty {
        private String id;
        private int currentSize;
        private int maxSize;
    }
    
    @Getter
    @Setter
    @Builder
    @Accessors(fluent = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityAssetsImpl implements ActivityAssets {
        private String largeImage;
        private String largeText;
        private String smallImage;
        private String smallText;
    }
    
    @Getter
    @Setter
    @Builder
    @Accessors(fluent = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivitySecretsImpl implements ActivitySecrets {
        private String join;
        private String spectate;
        private String match;
    }
    
    @Getter
    @Setter
    @Builder
    @Accessors(fluent = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityImpl implements Activity {
        private String name;
        private ActivityType type;
        private String url;
        private ActivityTimestamps timestamps;
        private String applicationId;
        private String details;
        private String state;
        private ActivityParty party;
        private ActivityAssets assets;
        private ActivitySecrets secrets;
        private boolean instance;
        private Set<ActivityFlag> flags;
    }
}
