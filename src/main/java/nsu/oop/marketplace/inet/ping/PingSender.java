package nsu.oop.marketplace.inet.ping;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class PingSender extends Thread {
    private final PingListener listener;
    private final Map<Integer, Long> usersPingTime;
    private final int pingDelay;
    private final Ping ping;

    PingSender(PingListener listener, Ping ping, int pingDelay) {
        this.listener = listener;
        this.usersPingTime = new HashMap<>();
        this.pingDelay = pingDelay;
        this.ping = ping;
    }

    public void removeUser(int userId) {
        synchronized (this) {
            usersPingTime.remove(userId);
        }
    }

    public void setUserPingTime(int userId) {
        //System.out.println("Ping Sender setPlayerPingTime player id = " + userId);
        usersPingTime.put(userId, (new Date()).getTime());
    }

    @Override
    public void run() {
        try {
            int delay = pingDelay / 4 > 0 ? pingDelay / 4 : 1;
            while (!isInterrupted()) {
                for (int id : new LinkedList<>(usersPingTime.keySet())) {
                    long now = (new Date()).getTime();
                    if (now - usersPingTime.get(id) > pingDelay) {
                        if (ping.isAlivePlayer(id)) listener.sendPing(id);
                        else removeUser(id);
                        //System.out.println("Ping Sender id = " + id + " Delay = " + (now - usersPingTime.get(id)));
                    }
                }
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.out.println("Ping Sender: " + e.getMessage());
        } finally {
            usersPingTime.clear();
            System.out.println("Ping Sender finished");

        }
    }
}
