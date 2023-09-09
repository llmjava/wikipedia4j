package llmjava.wikipedia;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.Duration;

public class RateLimiter {

    Boolean RATE_LIMIT = false;
    LocalDateTime RATE_LIMIT_LAST_CALL;
    Duration RATE_LIMIT_MIN_WAIT;

    public RateLimiter() {
        RATE_LIMIT_MIN_WAIT = Duration.ofMillis(50);
    }
    
    /**
     * 
     * @return true if last call was too recent
     */
    private Boolean isLastCallTooRecent() {
        return RATE_LIMIT_LAST_CALL != null && RATE_LIMIT_LAST_CALL.plus(RATE_LIMIT_MIN_WAIT).isAfter(LocalDateTime.now());
    }

    /**
     * Invoked before calling the request to pause the execution for that many seconds if:
     *   - the rate limit is enabled
     *   - last call was too recent
     */
    public void before() {
        if (RATE_LIMIT && isLastCallTooRecent()) {

            // Calculate how long to wait until the next request can be made
            Duration waitTime = Duration.between(LocalDateTime.now(), RATE_LIMIT_LAST_CALL.plus(RATE_LIMIT_MIN_WAIT));

            // Pause the execution for the wait time in seconds
            try {
                Thread.sleep((int) waitTime.get(ChronoUnit.MILLIS));
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
    }

    /**
     * Invoked after calling the request to reset state
     */
    public void after() {
        if(RATE_LIMIT) {
            RATE_LIMIT_LAST_CALL = LocalDateTime.now();
        }
    }
}
