from django.conf.urls import url
from django.urls import path, include

from django.contrib import admin
from rest_framework import routers

from processos.views import ProcessoViewSet
from user.views import UserViewSet, LoginView

router = routers.DefaultRouter()
router.register(r'usuarios', UserViewSet)
router.register(r'processos', ProcessoViewSet, basename='processos')

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    path('api/', include(router.urls)),
    path(
        'api/login/',
        LoginView.as_view(),
        name='Login',
    )
]
