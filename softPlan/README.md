# Gerenciador de Processos Judiciais
<p> O Projeto aqui apresentado tem como ojetivo, cumprir com os requisitos propostos no
Desafio da SoftPlan, para candidatos a uma vaga de Desenvolvedor FullStack.</p>
<p>" Sinta-se confortável com ambas responsabilidades e seja engajado com o
que há de melhor no mercado e boas práticas para trabalhar com inovação,
tecnologias modernas e emergentes na Softplan "</p>
<p>De acordo com a citação acima, dita pela SoftPlan, desenvolvi meu Projeto/Desafio,
utilizando o que há de melhor e mais inovador em Tecnologias e abordagens para
Desenvolvimento Web.</p>

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

<p>O Projeto/Desafio consiste em uma aplicação Web para Gerenciar Processos Judiciais,
aonde os usuários terão suas obrigações de acordo com o seu "Tipo" previamente cadastrado,
conforme descrito abaixo:</p>

<p>
Usuário Tipo: Administrador
Atribuições: Incluir os demais usuários, como também, todos os processos pendentes.

Usuário Tipo: Triador
Atribuições: Fazer a triagem dos processos pendentes de Pareceres destinando-os para o(os) finalizador(es),
cadastrados.

Usuário Tipo: Finalizador
Atribuições: Editar o Parecer para o(s) Processo(s) a ele destinados pelo Triador.</p>



![](../header.png)

## Instalação

**OS X & Linux:**

<p>A partir do local aonde foi descompactado o projeto navegar até a pasta: /softPlan/api_processos<br />
 Digitar comandos:<br />
*      pip install -r requirements.txt<br />
*      python manage.py makemigrations<br />
*      python manage.py migrate</p>  

<p>Ainda dentro da mesma pasta criar um Usuário inicial.<br />
Digitar comando:<br />
*     python manage.py createsuperuser</p>  
  
<p>Ainda dentro da mesma pasta startar o servidor.<br />
Digitar comando:<br />
*     python manage.py runserver</p>  

<p>Navegar até a pasta: /softPlan/frontend_processos<br />
Digitar comandos:<br />
* npm install<br />
*     npm start<br />

Obs: É necessário ter instalado o "node" versão: v10.19.0 ou superior.<br />

**Windows:**

No site oficial do Git (http://git-scm.com/) clique em "Download for Windows" e instale o Git Bash.<br />
Após a instalação e configuração do Git Bash, repita todas as etapas descritas acima.


## informações depois da instalação
* Back-end  rodando na porta 8000<br />
* Front-end rodando na porta 3000<br />

Gerenciamento do Back-end no endereço<br />
* http://localhost:8000/admin

Endpoints das APIs no endereço<br />
* http://localhost:8000/api

## Exemplo de uso

<p>Procedimentos para o devido uso do sistema:<br />
* Logar na aplicação como usuário admin (usuário criado na instalação);<br />
* Como admin: Incluir os demais usuários tipos - Triador - Finalizador;<br />
* Logar na aplicação como usuário Triador e destinar os processos pendentes de parecer aos respectivos Finalizadores;<br />
* Logar na aplicação como usuário Finalizador e incluir o parecer no(s) processo(s) que estiverem a disposição uma vez destinados pelo Triador.</p>

## Histórico de lançamentos

* 0.0.1
    * MUDANÇA: Atualização de docs (código do módulo permanece inalterado)

## Conclusão
Como já descrito na introdução desse, o desafio foi implementado com tecnologia e abordagens inovadoras conforme
descrito abaixo.<br />

**Front-End**
*  framework React;<br />
* gerenciamento de Estados;<br />
* responsividade;<br />
* temas;<br />
* animações;<br />
* thunks;<br />
* hooks;<br />
* persist.</p>

**Back-End**
*  Utilizando-se do Django Rest Framework como ferramenta para integração das APIs.</p>

 <p> - As páginas foram desenvolvidas com responsividade ( para uso em dispositivo móvel )<br />
- As páginas estão disponíveis para utilização com Docker</p>

[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/seunome/seuprojeto/wiki
