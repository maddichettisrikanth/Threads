package ThreadCoding;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedData {

    private int value = 0;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + " reading value: " + value);

            Thread.sleep(1000); // simulate reading
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int newValue) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + " writing value: " + newValue);

            Thread.sleep(2000); // simulate writing
            value = newValue;

            System.out.println(Thread.currentThread().getName()
                    + " updated value to: " + value);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        SharedData data = new SharedData();

        Runnable reader = data::read;

        Runnable writer = () -> data.write((int) (Math.random() * 100));

        // Multiple readers
        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
        new Thread(reader, "Reader-3").start();

        // Writer
        new Thread(writer, "Writer-1").start();

        // More readers
        new Thread(reader, "Reader-4").start();
    }
}
