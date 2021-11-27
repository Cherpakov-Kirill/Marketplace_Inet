package nsu.oop.marketplace.inet.messages;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface MessageAcceptorListener {
    void setNewNodeId(int nodeId);
    MarketplaceProto.User getUserById(int id);
    void sendAckMessage(MarketplaceProto.User user, MarketplaceProto.Message message);
    void setTimeOfReceivedMessage(int playerId);
}
