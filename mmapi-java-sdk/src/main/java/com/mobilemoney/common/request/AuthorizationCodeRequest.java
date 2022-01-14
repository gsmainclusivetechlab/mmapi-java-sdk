package com.mobilemoney.common.request;

import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.model.AccountHolderName;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;

/***
 * Class AgentRequest
 */
public class AuthorizationCodeRequest extends ResourceUtils {
    // Instance
    public AuthorisationCode authorisationCode;

    // Call back URL
    private String callBackURL;

    // Callback/polling
    private NotificationType notificationType = NotificationType.CALLBACK;

    /***
     *
     * @param identifiers
     * @param callBackURL
     * @param clientCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAuthorisationCode(Identifiers identifiers, final String callBackURL, final String clientCorrelationId) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }

        if (authorisationCode == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.AUTH_CODE_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = getResourcePath(API.ACCOUNT_AUTHORISATION_CODE, identifiers);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, clientCorrelationId);

        return createRequest(HttpMethod.POST, resourcePath, this.authorisationCode.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     *
     * @param identifiers
     * @param authorisationCode
     * @return
     * @throws MobileMoneyException
     */
    public AuthorisationCode viewAuthorisationCode(Identifiers identifiers, final String authorisationCode) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }

        if (StringUtils.isNullOrEmpty(authorisationCode)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = getResourcePath(API.VIEW_ACCOUNT_AUTHORISATION_CODE, identifiers).replace(Constants.AUTHORISATION_CODE, authorisationCode);

        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, AuthorisationCode.class);
    }

    /***
     * Returns account name details
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AccountHolderName viewAccountName(Identifiers identifiers) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        
        return createRequest(HttpMethod.GET, getResourcePath(API.VIEW_ACCOUNT_NAME, identifiers), AccountHolderName.class);
    }

    /***
     *
     * @param authorisationCode
     */
    public void setAuthorisationCodeRequest(AuthorisationCode authorisationCode) {
        this.authorisationCode = authorisationCode;
    }
    
    /***
     * 
     * @param authorisationCodeJsonString
     */
    public void setAuthorisationCodeRequest(final String authorisationCodeJsonString) {
        this.authorisationCode = JSONFormatter.fromJSON(authorisationCodeJsonString, AuthorisationCode.class);
    }
}
