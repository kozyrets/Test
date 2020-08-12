package listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;

public class Selenid implements LogEventListener {

    @Override
    public void beforeEvent(LogEvent logEvent) {

    }

    @Override
    public void afterEvent(LogEvent logEvent) {
        if (logEvent.getSubject().matches("(.*)should(.*)")) {
            switch (logEvent.getStatus()) {
                case PASS:
                    String subject = logEvent.getSubject();
                    int beginIndex = subject.indexOf("(") + 1;
                    int lastIndex = subject.lastIndexOf(")");
                    //Log.info("  Текущий результат совпал с эталонным - " + subject.substring(beginIndex, lastIndex));
                    break;
                case FAIL:
                    //Log.warn(logEvent.getError().toString());
                    break;
            }
        }
    }
}
