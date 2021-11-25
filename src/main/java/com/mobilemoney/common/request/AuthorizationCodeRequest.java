package com.mobilemoney.common.request;

import com.mobilemoney.common.model.AccountNameResponse;
import com.mobilemoney.common.model.AuthorisationCodeRequest;
import com.mobilemoney.common.model.AuthorisationCodeResponse;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;

/***
 * Class AgentRequest
 */
public class AuthorizationCodeRequest extends ResourceUtils {
    // Instance
    public AuthorisationCodeRequest authorisationCodeRequest;

    // Call back URL
    private String callBackURL;

    // Callback/polling
    private NotificationType notificationType = NotificationType.CALLBACK;

    /***
     *
     * @param identifiers
     * @param clientCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAuthorisationCode(Identifiers identifiers, final String clientCorrelationId) throws MobileMoneyException {
        String resourcePath = getResourcePath(API.ACCOUNT_AUTHORISATION_CODE, identifiers);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, clientCorrelationId);

        return createRequest(HttpMethod.POST, resourcePath, this.authorisationCodeRequest.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     *
     * @param identifiers
     * @param authorisationCode
     * @return
     * @throws MobileMoneyException
     */
    public AuthorisationCodeResponse viewAuthorisationCode(Identifiers identifiers, final String authorisationCode) throws MobileMoneyException {
        String resourcePath = getResourcePath(API.VIEW_ACCOUNT_AUTHORISATION_CODE, identifiers).replace(Constants.AUTHORISATION_CODE, authorisationCode);

        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, AuthorisationCodeResponse.class);
    }

    /***
     * Returns account name details
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AccountNameResponse viewAccountName(Identifiers identifiers) throws MobileMoneyException {
        return createRequest(HttpMethod.GET, getResourcePath(API.VIEW_ACCOUNT_NAME, identifiers), AccountNameResponse.class);
    }

    /***
     *
     * @param authorisationCodeRequest
     */
    public void setAuthorisationCodeRequest(AuthorisationCodeRequest authorisationCodeRequest) {
        this.authorisationCodeRequest = authorisationCodeRequest;
    }
}
