package nl.svb.dms.ddd_lease_api.sales.infrastructure.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuoteEventsListener {

    @EventListener(SpringEventQuoteFilledOut.class)
    void handleQuoteFilledOut(SpringEventQuoteFilledOut event) {
        log.info("Handling quote filled out event: {}", event);
    }
}
