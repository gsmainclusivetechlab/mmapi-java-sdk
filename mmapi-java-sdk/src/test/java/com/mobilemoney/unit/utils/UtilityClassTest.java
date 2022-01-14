package com.mobilemoney.unit.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;

public class UtilityClassTest extends ResourceUtils {
	@Test
	@DisplayName("Generate Resource Path With Single Identifier Test Success")
	void resourcePathWithSingleIdentifierTestSuccess() throws MobileMoneyException {
		String expectedPath = "test-path/accountid/1";
		List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "1"));
        
		String generatedPath = ResourceUtils.getResourcePath("test-path/{identifierType}/{identifier}", new Identifiers(identifierList));
		
		assertEquals(expectedPath, generatedPath);
	}
	
	@Test
	@DisplayName("Generate Resource Path With Single Identifier Test Failure")
	void resourcePathWithSingleIdentifierTestFailure() throws MobileMoneyException {
		String expectedPath = "test-path/accountid/2";
		List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "1"));
        
		String generatedPath = ResourceUtils.getResourcePath("test-path/{identifierType}/{identifier}", new Identifiers(identifierList));
		
		assertNotEquals(expectedPath, generatedPath);
	}
	
	@Test
	@DisplayName("Generate Resource Path With Multiple Identifier Test Success")
	void resourcePathWithMultipleIdentifierTestSuccess() throws MobileMoneyException {
		String expectedPath = "test-path/accountid@1$walletid@2";
		List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "1"));
        identifierList.add(new AccountIdentifier("walletid", "2"));
        
		String generatedPath = ResourceUtils.getResourcePath("test-path/{identifierType}/{identifier}", new Identifiers(identifierList));
		
		assertEquals(expectedPath, generatedPath);
	}
	
	@Test
	@DisplayName("Generate Resource Path With Multiple Identifier Test Failure")
	void resourcePathWithMultipleIdentifierTestFailure() throws MobileMoneyException {
		String expectedPath = "test-path/accountid@1&walletid@2";
		List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "1"));
        identifierList.add(new AccountIdentifier("walletid", "2"));
        
		String generatedPath = ResourceUtils.getResourcePath("test-path/{identifierType}/{identifier}", new Identifiers(identifierList));
		
		assertNotEquals(expectedPath, generatedPath);
	}
	
	@Test
	@DisplayName("String Value Is Empty Or Null Test Success")
	void isNullOrEmptyTestSuccess() {
		assertEquals(true, StringUtils.isNullOrEmpty(null));
		assertEquals(true, StringUtils.isNullOrEmpty(""));
		assertEquals(true, StringUtils.isNullOrEmpty(" "));
		assertEquals(false, StringUtils.isNullOrEmpty("mmapi"));
	}
}
