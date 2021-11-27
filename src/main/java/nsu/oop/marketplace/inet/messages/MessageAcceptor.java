package nsu.oop.marketplace.inet.messages;

import nsu.oop.marketplace.inet.unicast.AcceptorForReceiver;
import nsu.oop.marketplace.inet.unicast.AcceptorForSender;
import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.HashSet;
import java.util.Set;

public class MessageAcceptor implements AcceptorForSender, AcceptorForReceiver {
    private final MessageAcceptorListener listener;
    private final Set<Long> acceptedMessages;
    private int thisNodeId;

    public MessageAcceptor(MessageAcceptorListener listener) {
        this.listener = listener;
        this.acceptedMessages = new HashSet<>();
        this.thisNodeId = 1;
    }

    @Override
    public boolean acceptMessage(int userId, long messageSequence) {
        listener.setTimeOfReceivedMessage(userId);
        MarketplaceProto.User user = listener.getUserById(userId);
        if (user != null) {
            listener.sendAckMessage(user, MessageBuilder.ackMsgBuilder(messageSequence, thisNodeId, userId));
            //System.out.println("Message acceptor sent ACK to " + playerId);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAcceptedMessage(long seqNumber) {
        synchronized (acceptedMessages) {
            return acceptedMessages.contains(seqNumber);
        }
    }

    @Override
    public void receiveAckMsg(int receiverId, int senderId, long messageSequence) {
        listener.setTimeOfReceivedMessage(senderId);
        synchronized (acceptedMessages) {
            acceptedMessages.add(messageSequence);
        }
        if (messageSequence == 1 && receiverId != 0) {
            thisNodeId = receiverId;
            listener.setNewNodeId(thisNodeId);
        }
    }
}
