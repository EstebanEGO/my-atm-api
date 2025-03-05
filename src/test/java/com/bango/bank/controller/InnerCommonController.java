package com.bango.bank.controller;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class InnerCommonController implements BindingResult {

    List<FieldError> list;

    public InnerCommonController(boolean isError) {
        list = new ArrayList<>();
        if (isError) {
            FieldError fieldError =  new FieldError("name", "name", "requerido");
            list.add(fieldError);
        }
    }

    @Override
    public List<FieldError> getFieldErrors() {
        return list;
    }

    @Override
    @Nullable
    public Object getFieldValue(String arg0) {
        return new Object();
    }

    @Override
    public List<ObjectError> getGlobalErrors() {
        return new ArrayList<>();
    }

    @Override
    public String getObjectName() {
        return "";
    }

    @Override
    public void reject(String arg0, @Nullable Object[] arg1, @Nullable String arg2) {
        //No use this method
    }

    @Override
    public void rejectValue(@Nullable String arg0, String arg1, @Nullable Object[] arg2, @Nullable String arg3) {
        //No use this method
    }

    @Override
    public void addError(ObjectError error) {
        //No use this method
    }

    @Override
    @Nullable
    public PropertyEditor findEditor(@Nullable String arg0, @Nullable Class<?> arg1) {
        return null;
    }

    @Override
    public Map<String, Object> getModel() {
        return null; 
    }

    @Override
    @Nullable
    public PropertyEditorRegistry getPropertyEditorRegistry() {
        return null;
    }

    @Override
    @Nullable
    public Object getRawFieldValue(String arg0) {
        return new Object();
    }

    @Override
    @Nullable
    public Object getTarget() {
        return new Object();
    }

    @Override
    public String[] resolveMessageCodes(String errorCode) {
        return new String[] {};
    }

    @Override
    public String[] resolveMessageCodes(String errorCode, String field) {
        return new String[] {};
    }

    
}