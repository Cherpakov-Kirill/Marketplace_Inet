package nsu.oop.marketplace.inet;

public interface PlayersForInet {
    void disconnectPlayer(int playerId);
    MarketplaceProto.User getGamePLayerById(int id);
    void sendPing(int playerId);
}
