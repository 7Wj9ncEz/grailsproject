<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Biblioteca</title>

    <asset:link rel="icon" href="book1.png" type="image/x-ico" />
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
                <li><g:link controller='user' action='edit' id='${sec.loggedInUserInfo(field: 'id')}'>Editar informações</g:link></li>
                <li><g:link controller='user' action='delete' id='${sec.loggedInUserInfo(field: 'id')}'>Deletar conta</g:link></li>
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
            <p> Aqui você pode procurar os livros que deseja utilizar, verificar o status do seu empréstimo, entre outras opções! </p>
            <p>"A leitura engrandece a alma." - Voltaire </p>
            <p>Aproveite! </p>
        </section>
    </div>

</body>
</html>
