package nsu.oop.marketplace.inet.unicast;

public interface PingForSender {
    boolean isAlivePlayer(int playerId);
    void setTimeOfSentMessage(int playerId);
}
