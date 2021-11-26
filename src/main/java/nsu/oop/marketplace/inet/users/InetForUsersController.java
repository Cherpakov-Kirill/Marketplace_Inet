package nsu.oop.marketplace.inet.users;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface InetForUsersController {
    void sendMessage(MarketplaceProto.User user, MarketplaceProto.Message message);
    void removeUserFromPing(int userId);
}
