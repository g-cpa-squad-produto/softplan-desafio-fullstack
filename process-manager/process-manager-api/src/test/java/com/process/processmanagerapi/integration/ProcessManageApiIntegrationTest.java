package com.process.processmanagerapi.integration;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@Sql({"/sql/purge.sql", "/sql/seed.sql"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProcessManageApiIntegrationTest {

    private static final String requestEndpointBase = "http://localhost:8090/process-management";

    @Autowired
    private TestRestTemplate testRestTemplate;

    private RestTemplate patchTestRestTemplate;

    private RestTemplate buildPatchRestTemplate(){
        RestTemplate patchRestTemplate = testRestTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        return patchRestTemplate;
    }

    private static String readJSON(String filename) {
        try {
            return FileUtils.readFileToString(ResourceUtils.getFile("classpath:" + filename), "UTF-8");
        } catch (IOException exception) {
            return null;
        }
    }

    private HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    //Tests for Process Controller

    @Test
    public void shouldReturn200WhenCreateNewProcessWithSuccess() {
        String payload = readJSON("request/createProcessWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn400WhenCreateNewProcessWhenUserNotExist() {
        String payload = readJSON("request/createProcessWithUserNotExistOnDatabasePayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenCreateNewProcessWhenUserIsNotAuthorizedToCreateProcess() {
        String payload = readJSON("request/createProcessWithUserNotExistOnDatabasePayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenProcessIdAlreadyExist() {
        String payload = readJSON("request/createProcessWhenUserIsNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn200WhenIncludeNewProcessOpinionWithSuccess() {
        String payload = readJSON("request/includeProcessOpinionWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/includeProcessOpinion"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn400WhenIncludeNewProcessOpinionWithProcessNotFound() {
        String payload = readJSON("request/includeProcessOpinionWhenProcessNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/includeProcessOpinion"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Process not found"));
    }

    @Test
    public void shouldReturn400WhenIncludeNewProcessOpinionWithProcessFinished() {
        String payload = readJSON("request/includeProcessOpinionWhenProcessIsFinishedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/includeProcessOpinion"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Process already finished"));
    }

    @Test
    public void shouldReturn400WhenIncludeNewProcessOpinionWithUserNotAuthorized() {
        String payload = readJSON("request/includeProcessOpinionWithUserNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/includeProcessOpinion"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn400WhenIncludeNewProcessOpinionWithUserIsNotAuthorizedToIncludeOpinionForThisProcess() {
        String payload = readJSON("request/includeProcessOpinionWhenUserIsNotAuthorizedToIncludeOpinionForThisProcessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/includeProcessOpinion"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to add process opinion for this process"));
    }

    @Test
    public void shouldReturn200WhenFinishProcessWithSuccess() {
        String payload = readJSON("request/finishProcessWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/finish"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenFinishProcessWithUserNotFound() {
        String payload = readJSON("request/finishProcessWithUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/finish"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenFinishProcessWithUserNotAuthorizedToPerformThisOperation() {
        String payload = readJSON("request/finishProcessWithUserIsNotAuthorizedToPerformThisOperationPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/finish"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn400WhenFinishProcessWithProcessNotFound() {
        String payload = readJSON("request/finishProcessWithProcessNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/finish"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Process not found"));
    }

    @Test
    public void shouldReturn400WhenFinishProcessWithProcessAlreadyFinished() {
        String payload = readJSON("request/finishProcessWithProcessAlreadyFinishedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/finish"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Process already finished"));
    }

    @Test
    public void shouldReturn400WhenFinishProcessWithUseIsNotAuthorizedToPerformThisOperationInThisProcess() {
        String payload = readJSON("request/finishProcessWithUserIsNotAuthorizedToFinishThisProcessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/finish"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to finish this process"));
    }

    @Test
    public void shouldReturn200WhenFindByProcessNumberWithSuccess() {
        String payload = readJSON("request/findProcessByProcessNumberWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findByProcessNumber"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenFindByProcessNumberWithProcessNotFound() {
        String payload = readJSON("request/findProcessByProcessNumberWithProcessNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findByProcessNumber"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Process not found"));
    }

    @Test
    public void shouldReturn404WhenFindByProcessNumberWithUserNotFound() {
        String payload = readJSON("request/findProcessByProcessNumberWithUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findByProcessNumber"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenFindByProcessNumberWithUserNotAuthorizedToPerformThisOperation() {
        String payload = readJSON("request/findProcessByProcessNumberWithUserIsNotAuthorizedToPerformThisOperationPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findByProcessNumber"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn200WhenFindAllByProcessWithSuccess() {
        String payload = readJSON("request/findAllProcessWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findAll"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn400WhenFindAllProcessWithUserNotFound() {
        String payload = readJSON("request/findAllProcessWithUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findAll"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenFindAllProcessWithUserNotAuthorizedToPerformThisOperation() {
        String payload = readJSON("request/findAllProcessWithUserIsNotAuthorizedToPerformThisOperationPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/findAll"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    //Tests for User Controller

    @Test
    public void shouldReturn200WhenCreateANewUserWithSuccess() {
        String payload = readJSON("request/createNewUserWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenCreateUserWithUserNotFound() {
        String payload = readJSON("request/createNewUserWithUserAdminNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenCreateUserWithAdminUserIsNotAuthorized() {
        String payload = readJSON("request/createNewUserWithUserAdminIsNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn400WhenCreateUserWithUserNameAlreadyExist() {
        String payload = readJSON("request/createNewUserWithUserNameAlreadyExistPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User with user name specified already exist"));
    }

    @Test
    public void shouldReturn400WhenCreateUserWithUserTypeNotExist() {
        String payload = readJSON("request/createNewUserWithUserTypeNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/create"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User type specified not found"));
    }

    @Test
    public void shouldReturn200WhenGetAllUsersWithSuccess() {
        String payload = readJSON("request/getAllUsersWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/users"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenGetAllUsersWithAdminUserNotFound() {
        String payload = readJSON("request/getAllUsersWithViewedNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/users"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenGetAllUsersWithAdminUserIsNotAuthorized() {
        String payload = readJSON("request/getAllUserWithUserAdminIsNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/users"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn200WhenGetUserByUserNameWithSuccess() {
        String payload = readJSON("request/getUserByUserNameWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/user"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenGetUserByNameWithAdminUserNotFound() {
        String payload = readJSON("request/getUserByUserNameWithAdminUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/user"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenGetUserByNameWithAdminUserIsNotAuthorized() {
        String payload = readJSON("request/getUserByUserNameWithAdminUserNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/user"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn404WhenGetUserByNameWithUserNotFound() {
        String payload = readJSON("request/getUserByUserNameWithUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/user"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn200WhenEditUserWithSuccess() {
        String payload = readJSON("request/editUserWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        patchTestRestTemplate = buildPatchRestTemplate();
        ResponseEntity<String> response = patchTestRestTemplate.exchange(requestEndpointBase.concat("/user/editUser"), HttpMethod.PATCH, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenEditUserWithAdminUserNotFound() {
        String payload = readJSON("request/editUserWithAdminUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        patchTestRestTemplate = buildPatchRestTemplate();
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/editUser"), HttpMethod.PATCH, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenEditUserWithAdminUserNotAuthorized() {
        String payload = readJSON("request/editUserWithAdminUserNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        patchTestRestTemplate = buildPatchRestTemplate();
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/editUser"), HttpMethod.PATCH, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn400WhenEditUserWithUserNotFound() {
        String payload = readJSON("request/editUserWithUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        patchTestRestTemplate = buildPatchRestTemplate();
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/user/editUser"), HttpMethod.PATCH, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn200WhenAuthorizeUserWithSuccess() {
        String payload = readJSON("request/authorizeUserWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenAuthorizeUserWithTriadorUserNotFound() {
        String payload = readJSON("request/authorizeUserWithTriadorUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenAuthorizeUserWithTriadorUserNotAuthorized() {
        String payload = readJSON("request/authorizeUserWithTriadorUserNotAutorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn404WhenAuthorizeUserWithUserNotFound() {
        String payload = readJSON("request/authorizeUserWithUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenAuthorizeUserWithUserNotFinishUser() {
        String payload = readJSON("request/authorizeUserWithUserNotFinishUserPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn400WhenAuthorizeUserWithUserAlreadyAuthorized() {
        String payload = readJSON("request/authorizeUserWithUserAlreadyAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User already authorized"));
    }

    @Test
    public void shouldReturn200WhenUnauthorizeUserWithSuccess() {
        String payload = readJSON("request/unauthorizeUserWithSuccessPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/unauthorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn404WhenUnauthorizeUserWithTriadorUserNotFound() {
        String payload = readJSON("request/unauthorizeUserWithTriadorUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

    @Test
    public void shouldReturn400WhenUnauthorizeUserWithTriadorUserNotAuthorized() {
        String payload = readJSON("request/unauthorizeUserWithTriadorUserNotAuthorizedPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User is not authorized to perform this operation"));
    }

    @Test
    public void shouldReturn404WhenUnauthorizeUserWithUserNotFound() {
        String payload = readJSON("request/getUserByUserNameWithAdminUserNotFoundPayload.json");
        HttpEntity<String> entity = new HttpEntity<String>(payload, buildHttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(requestEndpointBase.concat("/process/authorizeUserToManageProcess"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("User not found"));
    }

}
