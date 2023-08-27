package com.alephlabs.assignment.service.impl;

import com.alephlabs.assignment.appconfig.CommonConfig;
import com.alephlabs.assignment.entity.RandomCodes;
import com.alephlabs.assignment.exception.CommonException;
import com.alephlabs.assignment.repository.RandomCodesRepository;
import com.alephlabs.assignment.service.GeneratorServiceInterface;
import com.alephlabs.assignment.util.GeneratorUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RandomGeneratorServiceImpl implements GeneratorServiceInterface {
    Logger logger = LoggerFactory.getLogger(RandomGeneratorServiceImpl.class);

    List<String> uniqueCodeList = new ArrayList<>();
    List<RandomCodes> randomDataList = new ArrayList<>();

    @Autowired
    private RandomCodesRepository randomCodesRepository;

    /**
     * Generating data with random Alpha numeric unique codes
     * @param count no of records needs to be added
     * @throws CommonException with error details
     */
    @Transactional
    @Override
    public void generateData(Integer count) throws CommonException {
        try {
            for (long i = 0; i < count; i++) {
                RandomCodes randomCode = generateRandomCodeData();
                randomDataList.add(randomCode);
                if (CommonConfig.IS_BATCH && randomDataList.size() >= CommonConfig.BATCH_SIZE) {
                    storeData();
                }
            }

            if (!randomDataList.isEmpty()) {
                storeData();
            }

        } catch (CommonException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new CommonException("Error Occurred - " + e.getMessage(), e);
        }
    }

    /**
     * Generate {@link RandomCodes} data with unique key and UUID
     * @return {@link RandomCodes} object
     */
    private RandomCodes generateRandomCodeData() {
        String random = GeneratorUtil.generateAlphaNumericString();
        //validating code duplicated in existing list
        while (uniqueCodeList.contains(random)) {
            System.out.println("Code Duplicated : " + random);
            generateRandomCodeData();
        }
        uniqueCodeList.add(random);

        RandomCodes randomCodes = new RandomCodes();
        randomCodes.setId(UUID.randomUUID());
        randomCodes.setUniqueCode(random);

        return randomCodes;
    }

    /**
     * Validate and Store {@link RandomCodes} in a batch process
     *
     * @throws CommonException with Error data
     */
    private void storeData() throws CommonException {
        try {
            randomCodesRepository.saveAllAndFlush(randomDataList);
            logger.info(" Data Stored  : " + randomDataList.size());
            randomDataList.clear();
        } catch (UnexpectedRollbackException | DataIntegrityViolationException e) {
            logger.error(e.getMessage(),e);
            randomDataList.clear();
            throw new CommonException("Duplicated Data Found. Please try again", e);
        }
    }
}
