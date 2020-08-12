package finalTasks.port;

import java.util.Random;

/**
 * Class for ship
 */

public class Ship implements Runnable {

    private final Port port;
    private final int capacity;
    private String name;
    private final int timeToContainer;
    private int loading;
    private boolean wasUploading;

    public Ship(Port port, int loading, int capacity) {

        this.port = port;
        this.loading = loading;
        this.capacity = capacity;
        this.timeToContainer = new Random().nextInt(150);
        this.wasUploading = loading==0;
    }

    @Override
    public void run() {
        this.name = Thread.currentThread().getName();
        while (loading != capacity || !wasUploading) {
            if (port.takeDock()) {
                System.out.println(
                    name + " - go to the dock " + port.getNumberOfDock() + ", in port " + port.containersInPort()
                        + " containers");
                if (loading > 0 && !wasUploading) {
                    upload();
                }
                if (loading == 0) {
                    load();
                }
            }
        }
        port.freeDock();
        System.out.println(name + " - go away from port");
    }

    private void load() {
        while (this.loading != this.capacity) {
            if (!port.getContainer()) {
                port.freeDock();
                System.out.println(name + " - free dock");
                sleep(timeToContainer*10);
                return;
            }
            this.loading++;
            System.out.println(
                name + " - loading, current loading = " + this.loading + " (capacity = " + this.capacity + ")"
                    + ", in port " + port.containersInPort() + " containers");

            sleep(timeToContainer);
        }
        System.out.println(name + " - fully load");
    }

    private void upload() {
        while (this.loading != 0) {
            if (!port.addContainer()) {
                port.freeDock();
                System.out.println(name + " - free dock");
                sleep(timeToContainer*10);
                return;
            }
            System.out.println(
                name + " - uploading, current loading = " + this.loading + ", in port " + port.containersInPort()
                    + " containers");
            this.loading--;
            sleep(timeToContainer);
        }
        System.out.println(name + " - fully upload");
        this.wasUploading = true;
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
