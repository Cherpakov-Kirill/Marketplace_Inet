package nsu.oop.marketplace.inet.messages;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface MessageAcceptorListener {
    void launchClientCore(int playerId);
    MarketplaceProto.User getGamePlayerById(int id);
    void sendAckMessage(MarketplaceProto.User user, MarketplaceProto.Message message);
    void setTimeOfReceivedMessage(int playerId);
}
