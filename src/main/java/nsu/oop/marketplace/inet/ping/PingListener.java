package nsu.oop.marketplace.inet.ping;

public interface PingListener {
    void disconnectPlayer(int playerId);
    void sendPing(int playerId);
}
