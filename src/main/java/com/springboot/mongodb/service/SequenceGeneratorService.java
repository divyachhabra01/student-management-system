package com.springboot.mongodb.service;

public interface SequenceGeneratorService {
    int generateSequenceNumber(String sequenceName);
}
