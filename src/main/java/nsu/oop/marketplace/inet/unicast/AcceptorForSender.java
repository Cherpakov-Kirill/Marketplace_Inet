package nsu.oop.marketplace.inet.unicast;

public interface AcceptorForSender {
    boolean checkAcceptedMessage(long seqNumber);
}
