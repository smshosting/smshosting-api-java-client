/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidationUtils {

    public final static String SDF_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String validateCreateAlias(String alias, String businessname, String address, String city, String postcode, String province, String country, String vatnumber, String email, String phone, String taxcode, String pec, String authKey, String authSecret) {

        Set<String> errors = new HashSet<>();
        String result = null;

        if (alias == null || alias.length() == 0 || alias.length() > 11) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_ALIAS);
        }

        if (businessname == null || businessname.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_BUSINESSNAME);
        }

        if (address == null || address.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_ADDRESS);
        }

        if (city == null || city.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_CITY);
        }

        if (postcode == null || postcode.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_POSTCODE);
        }

        if (province == null || province.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_PROVINCE);
        }

        if (country == null || country.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_COUNTRY);
        }

        if (country != null && "IT".equals(country)) {
            if (vatnumber == null || vatnumber.length() == 0) {
                errors.add(Errors.ERROR_MSG_ALIAS_BAD_VATNUMBER);
            }
            if (taxcode == null || taxcode.length() == 0) {
                errors.add(Errors.ERROR_MSG_ALIAS_BAD_TAXCODE);
            }
            if (pec == null || pec.length() == 0) {
                errors.add(Errors.ERROR_MSG_ALIAS_BAD_PEC);
            }
        }

        if (email == null || email.length() == 0) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_EMAIL);
        }

        if (phone == null || phone.length() == 0 || phone.length() > 25) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_PHONE);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetAlias(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateDeleteAlias(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        Pattern pattern = Pattern.compile("\\d+");

        if (!checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_ALIAS_BAD_ALIAS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateSendSms(String from, String to, String group, String text, String sendDate, String transactionId, Boolean sandbox, String statusCallback, String encoding, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (transactionId != null && transactionId.length() > 0) {
            if (transactionId.trim().length() > 60) {
                errors.add(Errors.ERROR_MSG_SEND_TRANSACTIONID);
            }
        }

        Date dataInvio = null;
        if (sendDate != null && sendDate.trim().length() > 0) {
            dataInvio = checkData(sendDate, true);
            if (dataInvio == null) {
                errors.add(Errors.ERROR_MSG_SEND_DATE);

            }
        }

        if (text == null || text.trim().length() < 1) {
            errors.add(Errors.ERROR_MSG_SEND_TEXT);
        }

        if (from == null || from.length() == 0) {
            errors.add(Errors.ERROR_MSG_SEND_FROM);
        }

        if ((group == null || group.length() == 0) && (to == null || to.length() == 0)) {
            return Errors.ERROR_MSG_SEND_RECIPIENT;
        }

        if (encoding != null) {
            if (!"AUTO".equalsIgnoreCase(encoding) && !"7BIT".equalsIgnoreCase(encoding) && !"UCS2".equalsIgnoreCase(encoding)) {
                return Errors.ERROR_MSG_SEND_ENCODING;
            }
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateEstimateSendSms(String from, String to, String group, String text, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (text == null || text.trim().length() < 1) {
            errors.add(Errors.ERROR_MSG_SEND_TEXT);
        }

        if (from == null || from.length() == 0) {
            errors.add(Errors.ERROR_MSG_SEND_FROM);
        }

        if ((group == null || group.length() == 0) && (to == null || to.length() == 0)) {
            return Errors.ERROR_MSG_SEND_RECIPIENT;
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateCancelSms(String id, String transactionId, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if ((id == null || id.length() == 0) && (transactionId == null || transactionId.length() == 0)) {
            return Errors.ERROR_MSG_SEARCH_NO_PARAMS;
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateSearchSms(String id, String transactionId, String msisdn, String fromDate, String toDate, String status, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if ((id == null || id.length() == 0) && (transactionId == null || transactionId.length() == 0) && (msisdn == null || msisdn.length() == 0)
                && (fromDate == null || fromDate.length() == 0) && (toDate == null || toDate.length() == 0) && (status == null || status.length() == 0)) {
            errors.add(Errors.ERROR_MSG_SEARCH_NO_PARAMS);
        }

        if (fromDate != null && fromDate.length() > 0) {
            if (checkData(fromDate) == null) {
                errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_DATE);
            }
        }

        if (toDate != null && toDate.length() > 0) {
            if (checkData(toDate) == null) {
                errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_DATE);
            }
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateSearchSmsReceived(String from, String simIdRef, String fromDate, String toDate, Integer offset, Integer limit, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if ((simIdRef == null || simIdRef.length() == 0) && (from == null || from.length() == 0) && (fromDate == null || fromDate.length() == 0)
                && (toDate == null || toDate.length() == 0)) {
            errors.add(Errors.ERROR_MSG_SEARCH_NO_PARAMS);
        }

        if (fromDate != null && fromDate.length() > 0) {
            if (checkData(fromDate) == null) {
                errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_DATE);
            }
        }

        if (toDate != null && toDate.length() > 0) {
            if (checkData(toDate) == null) {
                errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_DATE);
            }
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetSimForReceiveSmsList(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetGroupList(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetGroup(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateAddGroup(String name, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (name == null || name.length() == 0) {
            errors.add(Errors.ERROR_MSG_GROUP_NAME);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateUpdateGroup(String id, String name, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        if (name == null || name.length() == 0) {
            errors.add(Errors.ERROR_MSG_GROUP_NAME);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateDeleteGroup(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateSearchContacts(String msisdn, String fieldKey, String fieldValue, String email, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetContact(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateAddContact(String name, String groupsId, String customFieldUniqueKey, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if ((name == null || name.length() == 0) && (customFieldUniqueKey == null || customFieldUniqueKey.length() == 0)) {
            errors.add(Errors.ERROR_MSG_SEARCH_NO_PARAMS);
        }

        if (groupsId != null && groupsId.length() > 0) {
            String[] ids = groupsId.split(",");
            if (ids != null) {
                for (String idStr : ids) {
                    if (!checkIfValidId(idStr)) {
                        errors.add(Errors.ERROR_MSG_CONTACT_GROUP);
                    }
                }
            }
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateUpdateContact(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateDeleteContact(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetEmailSenderList(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetEmailTemplatesList(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetEmailTemplate(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_TEMPLATEID);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetEmailCampaignsList(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetEmailCampaign(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetEmail(String id, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (id == null || id.length() == 0 || !checkIfValidId(id)) {
            errors.add(Errors.ERROR_MSG_SEND_SEARCH_BAD_IDS);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateSendEmailCampaign(String templateId, String from, String group, String subject, String campaignTitle, String date, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (templateId == null || templateId.length() == 0 || !checkIfValidId(templateId)) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_TEMPLATEID);
        }

        if (group == null || group.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_GROUP);
        }

        if (from == null || from.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_FROM);
        }

        if (subject == null || subject.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_SUBJECT);
        }

        if (campaignTitle == null || campaignTitle.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_CAMPAIGN_TITLE);
        }

        if (date != null && date.length() > 0 && checkData(date, true) == null) {
            errors.add(Errors.ERROR_MSG_SEND_DATE);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateSendSingleEmail(String templateId, String from, String to, String subject, String date, String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        if (templateId == null || templateId.length() == 0 || !checkIfValidId(templateId)) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_TEMPLATEID);
        }

        if (to == null || to.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_TO);
        }

        if (from == null || from.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_FROM);
        }

        if (subject == null || subject.length() == 0) {
            errors.add(Errors.ERROR_MSG_MS_SEND_BAD_SUBJECT);
        }

        if (date != null && date.length() > 0 && checkData(date) == null) {
            errors.add(Errors.ERROR_MSG_SEND_DATE);
        }

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateGetUser(String authKey, String authSecret) {
        Set<String> errors = new HashSet<>();
        String result = null;

        String credentialError = validateCredentials(authKey, authSecret);
        if (credentialError != null) {
            errors.add(credentialError);
        }

        if (!errors.isEmpty()) {
            result = String.join(",", errors);
        }

        return result;
    }

    public static String validateCredentials(String authKey, String authSecret) {

        String error = null;

        if (authKey == null || authKey.length() == 0 || authSecret == null || authSecret.length() == 0) {
            error = Errors.ERROR_MSG_AUTH_CREDENTIALS;
        }

        return error;
    }

    public static Date checkData(String dateTime) {
        return checkData(dateTime, false);
    }

    public static Date checkData(String dateTime, boolean checkDifferito) {
        if (dateTime == null) {
            return null;
        }
        Date ora = new Date();
        Date dataInvio = null;

        try {
            dataInvio = new SimpleDateFormat(SDF_FORMAT).parse(dateTime);
        } catch (Exception e) {
            return null;
        }

        if (checkDifferito) {
            if (dataInvio != null && dataInvio.before(ora)) {
                return null;
            }
        }

        return dataInvio;

    }

    public static boolean checkIfValidId(String id) {

        if (id == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(id);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

}
