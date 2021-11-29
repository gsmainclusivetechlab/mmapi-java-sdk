package com.mobilemoney.base.model;

import java.util.List;

/***
 * Class HttpErrorResponse
 */
public final class HttpErrorResponse {
    private String errorCategory;
    private String errorCode;
    private String errorDescription;
    private String errorDateTime;
    private List<HttpErrorMetaData> errorParameters;

    /***
     * Constructor with builder object
     * @param builder
     */
    private HttpErrorResponse(HttpErrorResponseBuilder builder) {
        this.errorCategory = builder.errorCategory;
        this.errorCode = builder.errorCode;
        this.errorDescription = builder.errorDescription;
        this.errorDateTime = builder.errorDateTime;
        this.errorParameters = builder.errorParameters;
    }

    /***
     *
     * @return
     */
    public String getErrorCategory() {
        return errorCategory;
    }

    /***
     *
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

    /***
     *
     * @return
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /***
     *
     * @return
     */
    public String getErrorDateTime() {
        return errorDateTime;
    }

    /***
     *
     * @return
     */
    public List<HttpErrorMetaData> getErrorParameters() {
        return errorParameters;
    }

    /***
     * Creates HttpErrorResponse object
     */
    public static class HttpErrorResponseBuilder {
        private String errorCategory;
        private String errorCode;
        private String errorDescription;
        private String errorDateTime;
        private List<HttpErrorMetaData> errorParameters;

        /***
         * Constructor with mandatory properties
         * @param errorCategory
         * @param errorCode
         */
        public HttpErrorResponseBuilder(String errorCategory, String errorCode) {
            this.errorCategory = errorCategory;
            this.errorCode = errorCode;
        }

        /***
         *
         * @param errorDescription
         * @return
         */
        public HttpErrorResponseBuilder errorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        /***
         *
         * @param errorDateTime
         * @return
         */
        public HttpErrorResponseBuilder errorDateTime(String errorDateTime) {
            this.errorDateTime = errorDateTime;
            return this;
        }

        /***
         *
         * @param errorParameters
         * @return
         */
        public HttpErrorResponseBuilder errorParameters(List<HttpErrorMetaData> errorParameters) {
            this.errorParameters = errorParameters;
            return this;
        }

        /***
         * Returns constructed HttpErrorResponse object
         * @return
         */
        public HttpErrorResponse build() {
            HttpErrorResponse httpErrorResponse = new HttpErrorResponse(this);
            return httpErrorResponse;
        }
    }
}
