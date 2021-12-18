package com.myfamilybots.instamart.entity;

import com.myfamilybots.instamart.entity.impl.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: Outlets.java
 * Date/time: 15 декабрь 2021 in 7:16
 */
@Component("Outlets")
@Scope("singleton")
public class Outlets {
    @Autowired
    private ApplicationContext context;
    private final Map<String, Outlet> outlets = new ConcurrentHashMap<>();
    private final Map<String, Super> supervisors = new ConcurrentHashMap<>();
    private static Outlets instance;
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();

    private Outlets() {
        instance = this;
    }

    /**
     * @return singleton instance
     */
    public static Outlets getInstance() {
        try {
            if (instance == null) {
                writeLock.lock();
                if (instance == null) {
                    instance = new Outlets();
                }
                writeLock.unlock();
            }
            readLock.lock();
            return instance;
        } finally {
            readLock.unlock();
        }
    }

    public int getAmountOutlets() {
        return outlets.size();
    }

    public Outlet getOutlet(final String outletNumber) {
        try {
            if (!outlets.containsKey(outletNumber)) {
                initOutlet(outletNumber);
            }
            readLock.lock();
            return outlets.get(outletNumber);
        } finally {
            if (readLock.tryLock())
                readLock.unlock();
        }
    }

    private void initOutlet(final String outletNumber) {
        try {
            if (outlets.get(outletNumber) == null) {
                readLock.lock();
                Outlet newOutlet = context.getBean(Outlet.class);
                newOutlet.setOutletNumber(outletNumber);
                outlets.put(outletNumber, newOutlet);
            }
        } finally {
            if (readLock.tryLock())
                readLock.unlock();
        }
    }

    public Employee getSupervisorByPhoneNumber(String phoneNumber) {
        return supervisors.get(phoneNumber);
    }

    public boolean containsSupervisor(String phoneNumber) {
        return supervisors.containsKey(phoneNumber);
    }

    public void addSupervisor(Employee supervisor) {
        supervisors.put(supervisor.getPhoneNumber(), (Super) supervisor);
    }
}
