package teste.softplan.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import teste.softplan.domain.UserAdmin;
import teste.softplan.requests.UserAdminPostRequestBody;
import teste.softplan.requests.UserAdminPutRequestBody;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-23T14:56:01-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserAdminMapperImpl extends UserAdminMapper {

    @Override
    public UserAdmin toUser(UserAdminPostRequestBody userAdminPostRequestBody) {
        if ( userAdminPostRequestBody == null ) {
            return null;
        }

        UserAdmin userAdmin = new UserAdmin();

        return userAdmin;
    }

    @Override
    public UserAdmin toUser(UserAdminPutRequestBody userAdminPutRequestBody) {
        if ( userAdminPutRequestBody == null ) {
            return null;
        }

        UserAdmin userAdmin = new UserAdmin();

        return userAdmin;
    }
}
