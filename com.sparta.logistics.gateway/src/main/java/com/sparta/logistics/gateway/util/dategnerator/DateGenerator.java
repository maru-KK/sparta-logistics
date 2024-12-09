package com.sparta.logistics.gateway.util.dategnerator;

import java.util.Date;

public interface DateGenerator {

    Date getCurrentDate();

    Date getExpireDate(long exp);
}
