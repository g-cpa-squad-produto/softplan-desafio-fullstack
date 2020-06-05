from django.db import models


class Processo(models.Model):
    TIPOS_DE_USUARIOS = [
        ('s', 'Sim'),
        ('n', 'Não'),
    ]
    num_processo = models.CharField(max_length=30, unique=True, null=True)
    pendente = models.CharField(max_length=10, default='s')
    parecer = models.TextField('parecer', blank=True, null=True)

    finalizadores = models.ManyToManyField(
        "user.User",
        related_name='processos',
        verbose_name=("Finalizadores"),
        blank=True,
    )

    class Meta:
        verbose_name = ("Processo")
        verbose_name_plural = ("Processos")

    def __str__(self):
        return self.num_processo
