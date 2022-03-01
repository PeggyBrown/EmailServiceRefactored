package com.st;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SendingEmailIT {

    @Test
    void shouldReallySendEmail() {
        MainExecutor mainExecutor  = new MainExecutor();
        ExecutionStatus result = mainExecutor.execute();
        assertThat(result).isEqualTo(ExecutionStatus.SUCCESS);
    }
}
