package fitnesse;

import java.net.*;

public class HostnameResolver {

    public static class SingletonHolder {
        public static final HostnameResolver HOLDER_INSTANCE = new HostnameResolver();
    }

    public static HostnameResolver getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private String hostname;

    private HostnameResolver() {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
    }

    public String getHostname() {
        return hostname;
    }
}