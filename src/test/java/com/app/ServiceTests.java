package com.app;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.service.Service;


@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

	Service service;
	
	@Before
	public void init() {
		service = new Service();
	}
	
    @Test
    public void testLowerCase() {
        Set<String> test = service.validatePassword("qwert");
        assertFalse(test.contains(Service.PASSWORD_CASE_ERR));
    }

    @Test
    public void testUpperCase() {
        Set<String> test = service.validatePassword("QWERT");
        assertThat(test, hasItem(Service.PASSWORD_CASE_ERR));
    }

    @Test
    public void testLettersAndDigits() {
        Set<String> test = service.validatePassword("ab12");
        assertFalse(test.contains(Service.LETTER_AND_DIGIT_ERR));
    }

    @Test
    public void testDigitsAndLetters() {
        Set<String> test = service.validatePassword("12ab");
        assertFalse(test.contains(Service.LETTER_AND_DIGIT_ERR));
    }

    @Test
    public void testLetters() {
        Set<String> test = service.validatePassword("q");
        assertThat(test, hasItem(Service.LETTER_AND_DIGIT_ERR));
    }

    @Test
    public void testDigits() {
        Set<String> test = service.validatePassword("1");
        assertThat(test, hasItem(Service.LETTER_AND_DIGIT_ERR));
    }

    @Test
    public void test5orMoreDigits() {
        Set<String> test = service.validatePassword("12345");
        assertFalse(test.contains(Service.PASSWORD_LENGTH_ERR));
    }

    @Test
    public void test5orLessDigits() {
        Set<String> test = service.validatePassword("1234");
        assertThat(test, hasItem(Service.PASSWORD_LENGTH_ERR));
    }

    @Test
    public void test12orLessDigits() {
        Set<String> test = service.validatePassword("123456789");
        assertFalse(test.contains(Service.PASSWORD_LENGTH_ERR));
    }

    @Test
    public void test12orMoreDigits() {
        Set<String> test = service.validatePassword("12345678912345");
        assertThat(test, hasItem(Service.PASSWORD_LENGTH_ERR));
    }

    @Test
    public void testNotSequentialOrder() {
        Set<String> test = service.validatePassword("qwert12345");
        assertFalse(test.contains(Service.PASSWORD_SEQUENCE_ERR));
    }

    @Test
    public void testSequentialOrder() {
        Set<String> test = service.validatePassword("qw1qw1");
        assertThat(test, hasItem(Service.PASSWORD_SEQUENCE_ERR));
    }

}
