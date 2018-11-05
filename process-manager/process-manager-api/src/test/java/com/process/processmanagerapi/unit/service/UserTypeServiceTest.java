package com.process.processmanagerapi.unit.service;

import com.process.processmanagerapi.entity.UserType;
import com.process.processmanagerapi.exception.UserTypeServiceException;
import com.process.processmanagerapi.repository.UserTypeRepository;
import com.process.processmanagerapi.service.UserService;
import com.process.processmanagerapi.service.UserTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTypeServiceTest {

    @InjectMocks
    private UserTypeService userTypeService;

    @Mock
    private UserTypeRepository userTypeRepository;

    private UserType userType;

    @Before
    public void setUp() {
        userType = new UserType(1, UserService.ADMIN_TYPE);
    }

    @Test
    public void findUserTypeByUserTypeNameTestHappyScenario() {
        doReturn(userType).when(userTypeRepository).findByUserTypeName(Mockito.anyString());
        assertEquals(userType, userTypeService.findUserTypeByUserTypeName(Mockito.anyString()));
    }

    @Test(expected = UserTypeServiceException.class)
    public void findUserByUserNameTestWhenSomeExceptionOccurred() {
        doThrow(IllegalArgumentException.class).when(userTypeRepository).findByUserTypeName(Mockito.anyString());
        userTypeService.findUserTypeByUserTypeName(Mockito.anyString());
    }
}
