# Softplan fullstack challenge - João Gabriel

Implementação parcial do desáfio fullstack da softplan

## Instalação

Estou rodando uma versão do docker toolbox para windows, o que me faz preferir não rodar a aplicação inteira com docker já que não há mapeamento entre o nome da aplicação e o alias network no docker.


database 

```
docker-compose up
```

application

```
mvn spring-boot:run
```


front-end

```
npm start
```


Funcionalidades Backend:

1) Spring security com JWT
2) Proteção de rotas
3) Registrar, autenticar e listar usuários
4) Listar e criar Processos

Funcionalidades Backend:

1) Login de usuário
2) Proteção de rotas
3) Listar processos
4) Criar novo processo
5) Criar novo usuário
6) Logout
