package com.bango.bank.controller;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class InnerCommonControllerTest implements BindingResult {

    List<FieldError> list;

    public InnerCommonControllerTest(boolean isError) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFieldValue'");
    }

    @Override
    public List<ObjectError> getGlobalErrors() {
        return new ArrayList<>();
    }

    @Override
    public String getObjectName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObjectName'");
    }

    @Override
    public void reject(String arg0, @Nullable Object[] arg1, @Nullable String arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reject'");
    }

    @Override
    public void rejectValue(@Nullable String arg0, String arg1, @Nullable Object[] arg2, @Nullable String arg3) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rejectValue'");
    }

    @Override
    public void addError(ObjectError error) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addError'");
    }

    @Override
    @Nullable
    public PropertyEditor findEditor(@Nullable String arg0, @Nullable Class<?> arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findEditor'");
    }

    @Override
    public Map<String, Object> getModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModel'");
    }

    @Override
    @Nullable
    public PropertyEditorRegistry getPropertyEditorRegistry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPropertyEditorRegistry'");
    }

    @Override
    @Nullable
    public Object getRawFieldValue(String arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRawFieldValue'");
    }

    @Override
    @Nullable
    public Object getTarget() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTarget'");
    }

    @Override
    public String[] resolveMessageCodes(String errorCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveMessageCodes'");
    }

    @Override
    public String[] resolveMessageCodes(String errorCode, String field) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveMessageCodes'");
    }

    
}