package com.alephlabs.assignment.service;

import com.alephlabs.assignment.exception.CommonException;

public interface GeneratorServiceInterface {

    void generateData(Integer count) throws CommonException;
}
