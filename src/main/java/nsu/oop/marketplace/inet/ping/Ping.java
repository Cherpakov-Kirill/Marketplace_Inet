package nsu.oop.marketplace.inet.ping;

import nsu.oop.marketplace.inet.unicast.PingForSender;

import java.util.*;

public class Ping extends Thread implements PingForSender {
    private final PingListener listener;
    private final PingSender pingSender;
    private final Map<Integer, Long> lastMessageTimeFromNode;
    private final int nodeTimeOut;

    public Ping(PingListener listener, int pingDelay, int nodeTimeOut) {
        this.listener = listener;
        this.nodeTimeOut = nodeTimeOut;
        this.lastMessageTimeFromNode = new HashMap<>();
        this.pingSender = new PingSender(listener, pingDelay);
    }

    public void setTimeOfReceivedMessage(int userId) {
        //System.out.println("Ping setTimeOfReceivedMessage player id = " + playerId);
        lastMessageTimeFromNode.put(userId, (new Date()).getTime());
    }

    @Override
    public void setTimeOfSentMessage(int userId) {
        pingSender.setUserPingTime(userId);
    }

    @Override
    public boolean isAlivePlayer(int userId) {
        return lastMessageTimeFromNode.containsKey(userId);
    }

    public void removeUser(int userId) {
        lastMessageTimeFromNode.remove(userId);
        pingSender.removeUser(userId);
        System.out.println("Ping: delete dead node = " + userId);
    }

    private void checkNodes() {
        synchronized (lastMessageTimeFromNode) {
            for (int idUser : new LinkedList<>(lastMessageTimeFromNode.keySet())) {
                long now = (new Date()).getTime();
                if (now - lastMessageTimeFromNode.get(idUser) > nodeTimeOut) {
                    listener.disconnectUser("Sorry, you have exceeded the disappearance timeout!", idUser);
                    lastMessageTimeFromNode.remove(idUser);
                    pingSender.removeUser(idUser);
                }
            }
        }
    }

    @Override
    public void run() {
        int delay = nodeTimeOut / 4 > 0 ? nodeTimeOut / 4 : 1;
        pingSender.start();
        try {
            Thread.sleep(delay);
            while (!isInterrupted()) {
                checkNodes();
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.out.println("Ping: " + e.getMessage());
        } finally {
            lastMessageTimeFromNode.clear();
            System.out.println("Ping finished");
            pingSender.interrupt();
        }
    }
}
