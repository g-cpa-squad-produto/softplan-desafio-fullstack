package com.process.processmanagerapi.unit.service;

import com.process.processmanagerapi.entity.Process;
import com.process.processmanagerapi.entity.User;
import com.process.processmanagerapi.entity.UserType;
import com.process.processmanagerapi.exception.*;
import com.process.processmanagerapi.repository.ProcessRepository;
import com.process.processmanagerapi.service.ProcessService;
import com.process.processmanagerapi.service.UserService;
import com.process.processmanagerapi.vo.CreateProcessVO;
import com.process.processmanagerapi.vo.FinishProcessVO;
import com.process.processmanagerapi.vo.ProcessOpinionVO;
import com.process.processmanagerapi.vo.ViewProcessByProcessNumberVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessServiceTest {

    @InjectMocks
    private ProcessService processService;

    @Mock
    private UserService userService;

    @Mock
    private ProcessRepository processRepository;

    private Process process;

    @Before
    public void setUp() {
        process = new Process(1, "test", new Date(), "user Test");
        process.setAuthorizedUsers(new ArrayList<>());
    }

    @Test
    public void validateCreationProcessTestHappyScenario() {
        int parameter = 1;
        Object[] inputArray = {parameter};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessCreation", inputArray);
    }

    @Test(expected = ProcessAlreadyExistException.class)
    public void validateCreationProcessTestWhenProcessWithSameIdExist() {
        doReturn(process).when(processRepository).findByProcessNumber(Mockito.anyInt());
        int parameter = 1;
        Object[] inputArray = {parameter};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessCreation", inputArray);
    }

    @Test
    public void validateProcessBeforeIncludeOpinionTestHappyScenario() {
        Object[] inputArray = {process};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessBeforeIncludeOpinion", inputArray);
    }

    @Test(expected = ProcessNotFoundException.class)
    public void validateProcessBeforeIncludeOpinionTestWhenProcessIsNull() {
        Object[] inputArray = {null};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessBeforeIncludeOpinion", inputArray);
    }

    @Test(expected = ProcessAlreadyFinishedDuringIncludeProcessOpinionException.class)
    public void validateProcessBeforeIncludeOpinionTestWhenProcessIsFinished() {
        process.setFinishDate(new Date());
        Object[] inputArray = {process};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessBeforeIncludeOpinion", inputArray);
    }

    @Test
    public void getProcessByProcessNumberTestHappyScenario() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.TRIADOR_USER));
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        doReturn(process).when(processRepository).findByProcessNumber(Mockito.anyInt());
        assertEquals(process, processService.getProcessByProcessNumber(buildViewProcessByProcessNumberVO(1, "userTest")));
    }

    @Test(expected = UserNotAuthorizedException.class)
    public void getProcessByProcessNumberTestWhenUserIsNotTriadorUser() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.FINISHER_USER));
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        doThrow(UserNotAuthorizedException.class).when(userService).validateUser(Mockito.any(User.class), Mockito.anyString());
        processService.getProcessByProcessNumber(buildViewProcessByProcessNumberVO(1, "userTest"));
    }

    @Test(expected = UserNotFoundException.class)
    public void getProcessByProcessNumberTesWhenUserIsNotFound() {
        doThrow(UserNotFoundException.class).when(userService).validateUser(null, UserService.TRIADOR_USER);
        processService.getProcessByProcessNumber(buildViewProcessByProcessNumberVO(1, "userTest"));
    }

    @Test
    public void finishProcessTestHappyScenario() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.FINISHER_USER));
        process.setAuthorizedUsers(Arrays.asList(user));
        doReturn(true).when(userService).isUserAuthorizedToIncludeProcessOpinion(user, process.getAuthorizedUsers());
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        doReturn(process).when(processRepository).findByProcessNumber(Mockito.anyInt());
        doReturn(process).when(processRepository).save(Mockito.any(Process.class));
        assertEquals(process, processService.finishProcess(buildFinishProcessVO(1, "userTest")));
    }

    @Test(expected = ProcessAlreadyFinishedDuringFinishProcessException.class)
    public void finishProcessTestWhenProcessAlreadyFinished() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.FINISHER_USER));
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        process.setFinishDate(new Date());
        process.setAuthorizedUsers(Arrays.asList(user));
        doReturn(process).when(processRepository).findByProcessNumber(Mockito.anyInt());
        doReturn(process).when(processRepository).save(Mockito.any(Process.class));
        processService.finishProcess(buildFinishProcessVO(1, "userTest"));
    }

    @Test
    public void validateProcessBeforeFinishProcessTestHappyScenario() {
        Object[] inputArray = {process};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessBeforeFinishProcess", inputArray);
    }

    @Test(expected = ProcessNotFoundException.class)
    public void validateProcessBeforeFinishProcessTestWhenProcessIsNull() {
        Object[] inputArray = {null};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessBeforeFinishProcess", inputArray);
    }

    @Test(expected = ProcessAlreadyFinishedDuringFinishProcessException.class)
    public void validateProcessBeforeFinishProcessTestWhenProcessIsFinished() {
        process.setFinishDate(new Date());
        Object[] inputArray = {process};
        ReflectionTestUtils.invokeMethod(processService, "validateProcessBeforeFinishProcess", inputArray);
    }

    @Test
    public void includeProcessOpinionTestHappyScenario() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.FINISHER_USER));
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        doReturn(process).when(processRepository).findByProcessNumber(Mockito.anyInt());
        doReturn(process).when(processRepository).save(Mockito.any(Process.class));
        doReturn(true).when(userService).isUserAuthorizedToIncludeProcessOpinion(Mockito.any(User.class), Mockito.anyList());
        assertEquals(process, processService.includeProcessOpinion(buildProcessOpinionVO(1, "Bla bla bla", "userNameTest")));
    }

    @Test(expected = UserNotAuthorizedToIncludeProcessOpinionException.class)
    public void includeProcessOpinionTestWhenUserIsNotAuthorizedToIncludeOpinion() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.FINISHER_USER));
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        doReturn(process).when(processRepository).findByProcessNumber(Mockito.anyInt());
        doReturn(process).when(processRepository).save(Mockito.any(Process.class));
        doReturn(false).when(userService).isUserAuthorizedToIncludeProcessOpinion(Mockito.any(User.class), Mockito.anyList());
        processService.includeProcessOpinion(buildProcessOpinionVO(1, "Bla bla bla", "userNameTest"));
    }

    @Test
    public void createProcessTestHappyScenario() {
        User user = new User("userTest", "passtest", new Date(), "createdByTest", new UserType(UserService.TRIADOR_USER));
        doReturn(user).when(userService).findUserByName(Mockito.anyString());
        doReturn(process).when(processRepository).save(Mockito.any(Process.class));
        CreateProcessVO createProcessVO = new CreateProcessVO();
        createProcessVO.setProcessNumber(1);
        createProcessVO.setProcessDescription("Bla bla bla");
        createProcessVO.setCreateBy("userNameTest");
        assertEquals(process, processService.createProcess(createProcessVO));
    }

    private ViewProcessByProcessNumberVO buildViewProcessByProcessNumberVO(final int processNumber, final String viewBy) {
        ViewProcessByProcessNumberVO viewProcessByProcessNumberVO = new ViewProcessByProcessNumberVO();
        viewProcessByProcessNumberVO.setProcessNumber(processNumber);
        viewProcessByProcessNumberVO.setViewBy(viewBy);
        return viewProcessByProcessNumberVO;
    }

    private FinishProcessVO buildFinishProcessVO(final int processNumber, final String finishBy) {
        FinishProcessVO finishProcessVO = new FinishProcessVO();
        finishProcessVO.setProcessNumber(processNumber);
        finishProcessVO.setFinishBy(finishBy);
        return finishProcessVO;
    }

    private ProcessOpinionVO buildProcessOpinionVO(final int processNumber, final String processOpinion, final String user) {
        ProcessOpinionVO processOpinionVO = new ProcessOpinionVO();
        processOpinionVO.setProcessNumber(processNumber);
        processOpinionVO.setProcessOpinion(processOpinion);
        processOpinionVO.setUserName(user);
        return processOpinionVO;
    }

}
