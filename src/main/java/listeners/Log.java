package listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.logevents.LogEvent.EventStatus.FAIL;
import static org.apache.log4j.Logger.getLogger;

public class Log implements LogEventListener {
    private static final Logger LOG = getLogger(Log.class);

    private static String infoLog;

    public void setLog(String infoLog) {
        this.infoLog = infoLog;
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {
        LOG.info(infoLog + ": " + logEvent.getSubject());
    }

    @Override
    public void afterEvent(LogEvent logEvent) {
        String event;
        if (logEvent.getSubject().matches("(.*)should(.*)")) {
            switch (logEvent.getStatus()) {
                case PASS:
                    String subject = logEvent.getSubject();
                    int beginIndex = subject.indexOf("(") + 1;
                    int lastIndex = subject.lastIndexOf(")");
                    LOG.info(infoLog + ": Current result is as expected - " + subject.substring(beginIndex, lastIndex));
                    break;
                case FAIL:
                    event = logEvent.getError().getMessage();
                    LOG.warn(infoLog + ": " + event.substring(0, event.indexOf("Screenshot:")));
                    break;
            }
        } else if (logEvent.getStatus() == FAIL) {
            event = logEvent.getError().getMessage();
            LOG.error(infoLog + ": " + event.substring(0, event.indexOf("Screenshot:")));
        }
    }
}
