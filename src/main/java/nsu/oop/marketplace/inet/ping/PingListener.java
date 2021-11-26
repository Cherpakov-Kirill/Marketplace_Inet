package nsu.oop.marketplace.inet.ping;

public interface PingListener {
    void disconnectUser(String errorMessage, int userId);
    void sendPing(int playerId);
}
