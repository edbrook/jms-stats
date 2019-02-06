package uk.co.dekoorb.jmsstats;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StatsScheduler {

    private final StatsStore statsStore;

    public StatsScheduler(StatsStore statsStore) {
        this.statsStore = statsStore;
    }

    @Scheduled(fixedDelayString = "${stats.rate}")
    void displayStats() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        long msg;
        long totBytes;
        synchronized (statsStore) {
            msg = statsStore.getMessageCount();
            totBytes = statsStore.getMessageBytes();
            statsStore.reset();
        }
        double avgBytes = totBytes / (double) (msg > 0 ? msg : 1);
        System.out.printf("[%s] msgs: %d, avg_bytes: %.2f, tot_bytes: %d%n", timestamp, msg, avgBytes, totBytes);
    }
}
