package listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

public class Log implements LogEventListener {
    private static final Logger LOG = getLogger(Log.class);

    @Override
    public void beforeEvent(LogEvent logEvent) {
        LOG.info(logEvent.getSubject());
    }

    @Override
    public void afterEvent(LogEvent logEvent) {
        if (logEvent.getSubject().matches("(.*)should(.*)")) {
            switch (logEvent.getStatus()) {
                case PASS:
                    String subject = logEvent.getSubject();
                    int beginIndex = subject.indexOf("(") + 1;
                    int lastIndex = subject.lastIndexOf(")");
                    LOG.info("  Текущий результат совпал с эталонным - " + subject.substring(beginIndex, lastIndex));
                    break;
                case FAIL:
                    LOG.warn(logEvent.getError().toString());
                    break;
            }
        }
    }
}
