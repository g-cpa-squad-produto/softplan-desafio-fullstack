-- ======================================== DEFAULT ROLES ========================================

INSERT INTO CHL_OWNER.ROLES VALUES (1, 'ROLE_ADMIN');
INSERT INTO CHL_OWNER.ROLES VALUES (2, 'ROLE_TRIADOR');
INSERT INTO CHL_OWNER.ROLES VALUES (3, 'ROLE_FINALIZADOR');

-- ======================================== DEFAULT USERS ========================================

INSERT INTO CHL_OWNER.USERS VALUES (1, 'adm@challenge.com', '{noop}password', 'Admin', true, 1);
INSERT INTO CHL_OWNER.USERS VALUES (2, 'processo@processo', '{noop}password', 'Processo', true, 2);
INSERT INTO CHL_OWNER.USERS VALUES (3, 'fin@fin', '{noop}123', 'Fin 1', true, 3);
INSERT INTO CHL_OWNER.USERS VALUES (4, 'fin2@fin', '{noop}123', 'Fin 2', true, 3);

-- ======================================== DEFAULT OAUTH2 CLIENT ========================================

INSERT INTO AUTH_OWNER.OAUTH_CLIENT_DETAILS
(client_id, RESOURCE_IDS, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
  ('challenge_client',
    'oauth2-resource',
    '{noop}secret',
    'read,write,trust',
    'client_credentials,password,refresh_token',
    NULL,
    'USER',
    600,
    1300,
    NULL,
    TRUE
  );
