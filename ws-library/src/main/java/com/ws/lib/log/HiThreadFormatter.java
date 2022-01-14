package com.ws.lib.log;

public class HiThreadFormatter implements HiLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
