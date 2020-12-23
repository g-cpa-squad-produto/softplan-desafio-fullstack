package teste.softplan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import teste.softplan.domain.UserAdmin;
import teste.softplan.requests.UserAdminPostRequestBody;
import teste.softplan.requests.UserAdminPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class UserAdminMapper {
    public static final UserAdminMapper INSTANCE = Mappers.getMapper(UserAdminMapper.class);

    public abstract UserAdmin toUser(UserAdminPostRequestBody userAdminPostRequestBody);

    public abstract UserAdmin toUser(UserAdminPutRequestBody userAdminPutRequestBody);
}
