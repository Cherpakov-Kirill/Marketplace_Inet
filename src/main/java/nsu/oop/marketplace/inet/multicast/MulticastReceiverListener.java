package nsu.oop.marketplace.inet.multicast;

import nsu.oop.marketplace.inet.MarketplaceProto;

public interface MulticastReceiverListener {
    void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg msg, String ip);
}
