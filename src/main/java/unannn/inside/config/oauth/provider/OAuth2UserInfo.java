package unannn.inside.config.oauth.provider;

public interface OAuth2UserInfo {
    String getProviderId();

    String getProvider();

    String getEmail();

    default String getUsername() {
        return getProvider() + "-" + getProviderId();
    }

    String getName();
}
