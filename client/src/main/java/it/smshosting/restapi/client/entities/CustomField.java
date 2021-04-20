/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomField {

    private String fieldKey;
    private String fieldName;
    private String fieldType;
    private String fieldValue;
    private List<CustomFieldOption> fieldOptions;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public List<CustomFieldOption> getFieldOptions() {
        return fieldOptions;
    }

    public void setFieldOptions(List<CustomFieldOption> fieldOptions) {
        this.fieldOptions = fieldOptions;
    }

    public void addFieldOption(CustomFieldOption fieldOption) {
        if (fieldOptions == null) {
            fieldOptions = new ArrayList<CustomFieldOption>();
        }
        fieldOptions.add(fieldOption);
    }

    public static class CustomFieldOption {

        private int optionValue;
        private String optionName;

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        public int getOptionValue() {
            return optionValue;
        }

        public void setOptionValue(int optionValue) {
            this.optionValue = optionValue;
        }
    }

}
