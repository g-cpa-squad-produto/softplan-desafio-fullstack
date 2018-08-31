-- ======================================== DEFAULT ROLES ========================================

INSERT INTO CHL_OWNER.ROLES VALUES (1, 'ROLE_ADMIN');
INSERT INTO CHL_OWNER.ROLES VALUES (2, 'ROLE_TRIADOR');
INSERT INTO CHL_OWNER.ROLES VALUES (3, 'ROLE_FINALIZADOR');

-- ======================================== DEFAULT USERS ========================================

INSERT INTO CHL_OWNER.USERS VALUES (1, 'adm@challenge.com', '{noop}password', 'Admin', true, 1);

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
    900000,
    1800000,
    NULL,
    TRUE
  );
