package asc.portfolio.ascSb.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TableUpdateScheduler {

    @Scheduled(fixedDelay = 1000)
    public void test() {
        log.debug("1초");
    }
}
