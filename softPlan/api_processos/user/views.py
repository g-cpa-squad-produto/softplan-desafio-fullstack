from rest_framework import viewsets
from rest_framework.views import APIView
from .models import User
from .serializers import UserSerializer
from rest_framework.response import Response
from rest_framework import status
from django.contrib.auth import authenticate
from rest_framework_jwt.settings import api_settings
from rest_framework.decorators import action


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    @action(detail=False)
    def finalizadores(self, request):
        all_finali = User.objects.filter(tipo_de_usuario='Finalizador')

        page = self.paginate_queryset(all_finali)
        if page is not None:
            serializer = self.get_serializer(page, many=True)
            return self.get_paginated_response(serializer.data)

        serializer = self.get_serializer(all_finali, many=True)
        return Response(serializer.data)

    @action(detail=False)
    def triadores(self, request):
        all_triadores = User.objects.filter(tipo_de_usuario='Triador')

        page = self.paginate_queryset(all_triadores)
        if page is not None:
            serializer = self.get_serializer(page, many=True)
            return self.get_paginated_response(serializer.data)

        serializer = self.get_serializer(all_triadores, many=True)
        return Response(serializer.data)


class LoginView(APIView):
    def post(self, request):
        email_informado = request.data.get('username', None)
        senha_informada = request.data.get('password', None)

        if not email_informado:
            resposta_da_requisicao = {
                'has_error': True,
                'code': 400,
                'message': 'Informe o e-mail para autenticação.',
                'data': {
                    'email': ['Informe o e-mail para autenticação.'],
                },
            }
            return Response(resposta_da_requisicao,
                            status=status.HTTP_400_BAD_REQUEST)

        if not senha_informada:
            resposta_da_requisicao = {
                'has_error': True,
                'code': 400,
                'message': 'Informe o senha para autenticação.',
                'data': {
                    'senha': ['Informe uma senha para autenticação.'],
                },
            }
            return Response(resposta_da_requisicao,
                            status=status.HTTP_400_BAD_REQUEST)

        usuario = authenticate(username=email_informado,
                               password=senha_informada)
        if usuario is None:
            resposta_da_requisicao = {
                'has_error': True,
                'code': 403,
                'message': 'Usuario ou Senha incorreto, tente novamente.',
            }
            return Response(resposta_da_requisicao,
                            status=status.HTTP_403_FORBIDDEN)

        jwt_payload_handler = api_settings.JWT_PAYLOAD_HANDLER
        jwt_encode_handler = api_settings.JWT_ENCODE_HANDLER

        payload = jwt_payload_handler(usuario)
        token = jwt_encode_handler(payload)
        user_serializer = UserSerializer(usuario)

        resposta_da_requisicao = {
            'has_error': False,
            'code': 200,
            'token': token,
            'user': user_serializer.data
        }

        return Response(resposta_da_requisicao, status=status.HTTP_200_OK)
