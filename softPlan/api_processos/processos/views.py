from rest_framework import viewsets
from .models import Processo
from .serializers import ProcessoSerializer
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated


class ProcessoViewSet(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated, )
    queryset = Processo.objects.all()
    serializer_class = ProcessoSerializer

    def list(self, request):
        if request.user.tipo_de_usuario == 'Finalizador':
            queryset = Processo.objects.filter(finalizadores=request.user)
        elif request.user.tipo_de_usuario == 'Triador':
            queryset = Processo.objects.filter(pendente='s')
        else:
            queryset = Processo.objects.all()

        serializer = ProcessoSerializer(queryset,
                                        context={'request': request},
                                        many=True)
        return Response(serializer.data)
