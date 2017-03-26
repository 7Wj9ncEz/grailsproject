<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'libraryStaff.label', default: 'LibraryStaff')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-libraryStaff" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-libraryStaff" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.libraryStaff}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.libraryStaff}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <f:input bean="libraryStaff" property="jobTitle" class="form-control"/>
                    <f:input bean="user" property="firstName" class="form-control" placeholder="Primeiro Nome"/>
                    <f:input bean="user" property="lastName" class="form-control" placeholder="Sobrenome"/>
                    <f:input bean="user" property="email" class="form-control" placeholder="E-mail"/>
                    <f:input bean="user" property="username" class="form-control" placeholder="Nome de usuário"/>
                    <f:input bean="user" property="password" class="form-control" placeholder="Senha"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
