package uk.co.dekoorb.jmsstats;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
class StatsStore {
    private AtomicLong messageCount;
    private AtomicLong messageBytes;

    StatsStore() {
        messageCount = new AtomicLong();
        messageBytes = new AtomicLong();
    }

    long incCount() {
        return messageCount.incrementAndGet();
    }

    long incBytes(long bytes) {
        return messageBytes.addAndGet(bytes);
    }

    long getMessageBytes() {
        return messageBytes.get();
    }

    long getMessageCount() {
        return messageCount.get();
    }

    void reset() {
        messageCount.set(0);
        messageBytes.set(0);
    }
}
