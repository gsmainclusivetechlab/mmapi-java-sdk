package com.mobilemoney.base;

import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.exception.UnauthorizedException;

public interface ExecuteTask {
    /***
     *
     * @return
     * @throws MobileMoneyException
     * @throws UnauthorizedException
     */
    HttpResponse execute() throws MobileMoneyException, UnauthorizedException;
}
