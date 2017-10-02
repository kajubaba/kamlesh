package com.narendra.sams.communication.service;

import java.util.Date;

public interface SmsSender {
    void sendFeeDepositSMS(Long l, long j, Date date);
}
