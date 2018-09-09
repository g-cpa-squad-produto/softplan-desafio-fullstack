import {createAuthRequest, createPublicRequest}  from "../helpers/RequestHelper";

export const apiAuth = createAuthRequest();
export const api = createPublicRequest();