from rest_framework import serializers
from .models import Processo
from user.serializers import UserSerializer


class ProcessoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Processo
        fields = ('id', 'num_processo', 'pendente', 'finalizadores', 'parecer')
