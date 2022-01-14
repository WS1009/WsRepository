package com.ws.lib.log;

public interface HiLogFormatter<T> {

    String format(T data);
}