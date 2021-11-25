package nsu.oop.marketplace.inet.unicast;

public interface AcceptorForReceiver {
    boolean acceptMessage(int playerId, long messageSequence);
    void receiveAckMsg(int receiverId, int senderId, long messageSequence);
}
