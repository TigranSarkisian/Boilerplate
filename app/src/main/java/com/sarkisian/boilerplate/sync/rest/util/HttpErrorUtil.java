package com.sarkisian.boilerplate.sync.rest.util;

public class HttpErrorUtil {

    public class NonNumericStatusCode {

        /**
         * Non-Numeric status code HTTP_SERVER_TIMEOUT: timeout
         */
        public static final String HTTP_SERVER_TIMEOUT = "ETIMEDOUT";

        /**
         * Non-Numeric status code HTTP_CONNECTION_REFUSED: server refused connection
         */
        public static final String HTTP_CONNECTION_REFUSED = "ECONNREFUSED";

        /**
         * Non-Numeric status code UNABLE_TO_RESOLVE_HOST: can not access host due to some
         * network connections problems
         */
        public static final String UNABLE_TO_RESOLVE_HOST = "Unable to resolve host";
    }

    public class NumericStatusCode {

        /**
         * Numeric status code, 202: Accepted
         */
        public static final int HTTP_ACCEPTED = 202;

        /**
         * Numeric status code, 502: Bad Gateway
         */
        public static final int HTTP_BAD_GATEWAY = 502;

        /**
         * Numeric status code, 405: Bad Method
         */
        public static final int HTTP_BAD_METHOD = 405;

        /**
         * Numeric status code, 400: Bad Request
         */
        public static final int HTTP_BAD_REQUEST = 400;

        /**
         * Numeric status code, 408: Client Timeout
         */
        public static final int HTTP_CLIENT_TIMEOUT = 408;

        /**
         * Numeric status code, 409: Conflict
         */
        public static final int HTTP_CONFLICT = 409;

        /**
         * Numeric status code, 201: Created
         */
        public static final int HTTP_CREATED = 201;

        /**
         * Numeric status code, 413: Entity too large
         */
        public static final int HTTP_ENTITY_TOO_LARGE = 413;

        /**
         * Numeric status code, 403: Forbidden
         */
        public static final int HTTP_FORBIDDEN = 403;

        /**
         * Numeric status code, 504: Gateway timeout
         */
        public static final int HTTP_GATEWAY_TIMEOUT = 504;

        /**
         * Numeric status code, 410: Gone
         */
        public static final int HTTP_GONE = 410;

        /**
         * Numeric status code, 500: Internal error
         */
        public static final int HTTP_INTERNAL_ERROR = 500;

        /**
         * Numeric status code, 411: Length required
         */
        public static final int HTTP_LENGTH_REQUIRED = 411;

        /**
         * Numeric status code, 301 Moved permanently
         */
        public static final int HTTP_MOVED_PERM = 301;

        /**
         * Numeric status code, 302: Moved temporarily
         */
        public static final int HTTP_MOVED_TEMP = 302;

        /**
         * Numeric status code, 300: Multiple choices
         */
        public static final int HTTP_MULT_CHOICE = 300;

        /**
         * Numeric status code, 204: No content
         */
        public static final int HTTP_NO_CONTENT = 204;

        /**
         * Numeric status code, 406: Not acceptable
         */
        public static final int HTTP_NOT_ACCEPTABLE = 406;

        /**
         * Numeric status code, 203: Not authoritative
         */
        public static final int HTTP_NOT_AUTHORITATIVE = 203;

        /**
         * Numeric status code, 404: Not found
         */
        public static final int HTTP_NOT_FOUND = 404;

        /**
         * Numeric status code, 501: Not implemented
         */
        public static final int HTTP_NOT_IMPLEMENTED = 501;

        /**
         * Numeric status code, 304: Not modified
         */
        public static final int HTTP_NOT_MODIFIED = 304;

        /**
         * Numeric status code, 200: OK
         */
        public static final int HTTP_OK = 200;

        /**
         * Numeric status code, 206: Partial
         */
        public static final int HTTP_PARTIAL = 206;

        /**
         * Numeric status code, 402: Payment required
         */
        public static final int HTTP_PAYMENT_REQUIRED = 402;

        /**
         * Numeric status code, 412: Precondition failed
         */
        public static final int HTTP_PRECON_FAILED = 412;

        /**
         * Numeric status code, 407: Proxy authentication required
         */
        public static final int HTTP_PROXY_AUTH = 407;

        /**
         * Numeric status code, 414: Request too long
         */
        public static final int HTTP_REQ_TOO_LONG = 414;

        /**
         * Numeric status code, 205: Reset
         */
        public static final int HTTP_RESET = 205;

        /**
         * Numeric status code, 303: See other
         */
        public static final int HTTP_SEE_OTHER = 303;

        /**
         * Numeric status code, 305: Use proxy.
         * <p/>
         * <p>Like Firefox and Chrome, this class doesn't honor this response code.
         * Other implementations respond to this status code by retrying the request
         * using the HTTP proxy named by the response's Location header field.
         */
        public static final int HTTP_USE_PROXY = 305;

        /**
         * Numeric status code, 401: Unauthorized
         */
        public static final int HTTP_UNAUTHORIZED = 401;

        /**
         * Numeric status code, 415: Unsupported type
         */
        public static final int HTTP_UNSUPPORTED_TYPE = 415;

        /**
         * Numeric status code, 503: Unavailable
         */
        public static final int HTTP_UNAVAILABLE = 503;

        /**
         * Numeric status code, 505: Version not supported
         */
        public static final int HTTP_VERSION = 505;

        /**
         * Numeric status code, 1: Server timeout
         */
        public static final int HTTP_SERVER_TIMEOUT = 1;

        /**
         * Numeric status code, 2: Any not handled server or connection error
         */
        public static final int HTTP_UNKNOWN_SERVER_ERROR = 2;

        /**
         * Numeric status code, 3: No internet connection
         */
        public static final int HTTP_NO_NETWORK = 3;

        /**
         * Numeric status code, 4: Unable to resolve host, no address associated with hostname.
         */
        public static final int UNABLE_TO_RESOLVE_HOST = 4;

        /**
         * Numeric status code, 5: Connection refused by server
         */
        public static final int HTTP_CONNECTION_REFUSED = 5;
    }
}
