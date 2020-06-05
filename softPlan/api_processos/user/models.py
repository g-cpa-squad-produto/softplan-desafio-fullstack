from django.contrib.auth.models import AbstractUser
from django.db import models


class User(AbstractUser):
    nome = models.CharField(("Nome"), max_length=200, blank=False, null=True)
    TIPOS_DE_USUARIOS = [
        ('Administrador', 'Administrador'),
        ('Triador', 'Triador'),
        ('Finalizador', 'Finalizador'),
    ]
    tipo_de_usuario = models.CharField(("Tipo de usuario"),
                                       max_length=50,
                                       choices=TIPOS_DE_USUARIOS,
                                       default='Administrador')
