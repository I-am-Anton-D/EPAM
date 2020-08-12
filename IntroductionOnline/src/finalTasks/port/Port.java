package finalTasks.port;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Port entity
 */

public class Port {
    private final AtomicInteger containers = new AtomicInteger(); // count of containers in port
    private final AtomicInteger busy = new AtomicInteger(); //count of busy ports
    private final int maximumContainers; //capacity of the port
    private final int countOfDocks; //count of docks in port

    public Port(int containersInHarbor, int maximumContainers,int countOfDocks) {
        this.containers.set(containersInHarbor);
        this.maximumContainers = maximumContainers;
        this.countOfDocks = countOfDocks;
        busy.set(0);
    }

    public boolean addContainer() {
        if (containers.get() < maximumContainers) {
            containers.incrementAndGet();
            return true;
        }
        else
            return false;
    }

    public boolean getContainer() {
        if (containers.get() > 0) {
            containers.decrementAndGet();
            return true;
        }
        else
            return false;
    }

    public synchronized boolean takeDock(){
        if (busy.get()<countOfDocks) {
            busy.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfDock() {
        return busy.get();
    }

    public int containersInPort() {
        return containers.get();
    }

    public void freeDock() {
        if (busy.get()>0) {
            busy.decrementAndGet();
        }
    }
}
