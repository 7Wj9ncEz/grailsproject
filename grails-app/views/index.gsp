<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Paradigmas</title>

    <asset:link rel="icon" href="group-of-people-icon.png" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Opções<span class="caret"></span></a>
            <ul class="dropdown-menu">
            <sec:ifNotLoggedIn>
                <li><g:link controller='user' action='create'>Criar conta</g:link></li>
                <li><g:link controller='login'>Entrar</g:link></li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><g:link controller='user' action='edit'>Editar informações</g:link></li>
                    <li><g:link controller='user' action='delete'>Deletar conta</g:link></li>
                </sec:ifLoggedIn>
            </ul>
            <sec:ifLoggedIn>
                <li><g:link controller='logout'>Sair</g:link></li>
            </sec:ifLoggedIn>
        </li>
        
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="background.jpg" class="grails-logo"/>
        </div>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Seja bem-vindo!</h1>
            <p> Participe desta rede social para interegir com seus amigos! </p>
            <p>Você pode compartilhar informações em sua página e acessar a dos seus amigos. </p>
            <p>Aproveite! </p>
        </section>
    </div>

</body>
</html>
