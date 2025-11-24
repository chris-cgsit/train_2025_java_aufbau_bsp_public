package at.cgsit.train.java.profi;

public record AppConfig(
        int port,
        String logfile,
        int maxClients,
        boolean verbose
) {
    @Override
    public String toString() {
        return "AppConfig{" +
                "port=" + port +
                ", logfile='" + logfile + '\'' +
                ", maxClients=" + maxClients +
                ", verbose=" + verbose +
                '}';
    }
}
