/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.utilities;

public class Errors {

    //STATUS CODE ERRORS
    public static final int STATUS_CODE_BAD_REQUEST = 400;

    //ERROR MESSAGES
    public final static String ERROR_MSG_SEARCH_NO_PARAMS = "NO_PARAMS";

    //login
    public final static String ERROR_MSG_AUTH_CREDENTIALS = "BAD_CREDENTIALS";

    //alias
    public final static String ERROR_MSG_ALIAS_NOT_EXISTS = "ALIAS_NOT_EXISTS";
    public final static String ERROR_MSG_ALIAS_BAD_ALIAS = "BAD_ALIAS";
    public final static String ERROR_MSG_ALIAS_BAD_BUSINESSNAME = "BAD_BUSINESSNAME";
    public final static String ERROR_MSG_ALIAS_BAD_VATNUMBER = "BAD_VATNUMBER";
    public final static String ERROR_MSG_ALIAS_BAD_ADDRESS = "BAD_ADDRESS";
    public final static String ERROR_MSG_ALIAS_BAD_CITY = "BAD_CITY";
    public final static String ERROR_MSG_ALIAS_BAD_POSTCODE = "BAD_POSTCODE";
    public final static String ERROR_MSG_ALIAS_BAD_PROVINCE = "BAD_PROVINCE";
    public final static String ERROR_MSG_ALIAS_BAD_COUNTRY = "BAD_COUNTRY";
    public final static String ERROR_MSG_ALIAS_BAD_EMAIL = "BAD_EMAIL";
    public final static String ERROR_MSG_ALIAS_BAD_PHONE = "BAD_PHONE";
    public final static String ERROR_MSG_ALIAS_BAD_TAXCODE = "BAD_TAXCODE";
    public final static String ERROR_MSG_ALIAS_BAD_PEC = "BAD_PEC";
    public final static String ERROR_MSG_ALIAS_DUPLICATE_ALIAS = "DUPLICATE_ALIAS";

    //invio
    public final static String ERROR_MSG_SEND_FROM = "BAD_FROM";
    public final static String ERROR_MSG_SEND_RECIPIENT = "NO_VALID_RECIPIENT";
    public final static String ERROR_MSG_SEND_CREDIT = "BAD_CREDIT";
    public final static String ERROR_MSG_SEND_GROUP = "BAD_GROUP";
    public final static String ERROR_MSG_SEND_TO = "BAD_TO";
    public final static String ERROR_MSG_SEND_MSISDN = "BAD_MSISDN";
    public final static String ERROR_MSG_SEND_NO_CREDIT = "NO_CREDIT";
    public final static String ERROR_MSG_SEND_NO_CREDIT_RESELLER = "SENDING_DISABLED";
    public final static String ERROR_MSG_SEND_DUPLICATE_SMS = "DUPLICATE_SMS";
    public final static String ERROR_MSG_SEND_DATE = "BAD_DATE";
    public final static String ERROR_MSG_SEND_TRANSACTIONID = "BAD_TRANSACTIONID";
    public final static String ERROR_MSG_SEND_TEXT = "BAD_TEXT";
    public final static String ERROR_MSG_SEND_FILE = "BAD_FILE";
    public final static String ERROR_MSG_SEND_FILE_EXPIRATION_DATE = "BAD_FILE_EXPIRATION_DATE";
    public final static String ERROR_MSG_SEND_TEXT_PLACEHOLDER = "BAD_TEXT_PLACEHOLDER";
    public final static String ERROR_MSG_SEND_CALLBACK = "BAD_CALLBACK";
    public final static String ERROR_MSG_SEND_ENCODING = "BAD_ENCODING";
    public final static String ERROR_MSG_SEND_QUALITY = "BAD_QUALITY";
    public final static String ERROR_MSG_SEND_SEARCH_BAD_IDS = "BAD_ID";
    public final static String ERROR_MSG_SEND_SEARCH_BAD_MSISDN = "BAD_MSISDN";
    public final static String ERROR_MSG_SEND_SEARCH_BAD_DATE = "BAD_DATE";
    public final static String ERROR_MSG_SEND_SEARCH_BAD_STATUS = "BAD_STATUS";
    public final static String ERROR_MSG_RECEIVED_BAD_RECEIVEON = "BAD_RECEIVE_ON";

    //gruppi
    public final static String ERROR_MSG_GROUP_NOT_EXISTS = "GROUP_NOT_EXISTS";
    public final static String ERROR_MSG_GROUP_NAME = "BAD_GROUP_NAME";
    public final static String ERROR_MSG_GROUP_DESCRIPTION = "BAD_GROUP_DESCRIPTION";
    public final static String ERROR_MSG_GROUP_ALREADY_EXISTS = "GROUP_ALREADY_EXISTS";

    //contatti
    public final static String ERROR_MSG_CONTACT_NOT_EXISTS = "CONTACT_NOT_EXISTS";
    public final static String ERROR_MSG_CONTACT_NAME = "BAD_CONTACT_NAME";
    public final static String ERROR_MSG_CONTACT_LASTNAME = "BAD_CONTACT_LASTNAME";
    public final static String ERROR_MSG_CONTACT_GROUP = "BAD_CONTACT_GROUP";
    public final static String ERROR_MSG_CONTACT_MSISDN = "BAD_CONTACT_MSISDN";
    public final static String ERROR_MSG_MATCH_POLICY = "BAD_MATCH_POLICY";
    public final static String ERROR_MSG_CONTACT_COUNTRY = "BAD_CONTACT_COUNTRY";
    public final static String ERROR_MSG_CONTACT_ALREADY_EXISTS = "CONTACT_ALREADY_EXISTS";
    public final static String ERROR_MSG_CONTACT_EMAIL = "BAD_CONTACT_EMAIL";
    public final static String ERROR_MSG_CONTACT_FIELD_UNIQUE_KEY = "BAD_FIELD_UNIQUE_KEY";
    public final static String ERROR_MSG_CONTACT_FIELD_VALUE = "BAD_FIELD_VALUE";

    //EMAIL
    public final static String ERROR_MSG_MS_SEND_BAD_TEMPLATEID = "BAD_TEMPLATEID";
    public final static String ERROR_MSG_MS_SEND_BAD_FROM = "BAD_FROM";
    public final static String ERROR_MSG_MS_SEND_FROM_NOT_VALIDATED = "FROM_NOT_VALIDATED";
    public final static String ERROR_MSG_MS_SEND_BAD_TO = "BAD_TO";
    public final static String ERROR_MSG_MS_SEND_BAD_TO_SIZE = "BAD_TO_SIZE";
    public final static String ERROR_MSG_MS_SEND_BAD_SUBJECT = "BAD_SUBJECT";
    public final static String ERROR_MSG_MS_SEND_BAD_GROUP = "BAD_GROUP";
    public final static String ERROR_MSG_MS_CAMPAIGN_NOT_EXISTS = "CAMPAIGN_NOT_EXISTS";
    public final static String ERROR_MSG_MS_TEMPLATE_NOT_EXISTS = "TEMPLATE_NOT_EXISTS";
    public final static String ERROR_MSG_MS_EMAIL_NOT_EXISTS = "EMAIL_NOT_EXISTS";
    public final static String ERROR_MSG_MS_SEND_BAD_CAMPAIGN_TITLE = "BAD_CAMPAIGN_TITLE";

}
