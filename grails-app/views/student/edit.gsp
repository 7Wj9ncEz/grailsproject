<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-student" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.student}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.student}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.student}" method="PUT">
                <g:hiddenField name="version" value="${this.student?.version}" />
                <fieldset class="form">
                    <f:input bean="student" property="matriculation" class="form-control" placeholder="Matrícula do Aluno"/>
                    <f:input bean="student" property="firstName" class="form-control" placeholder="Primeiro Nome"/>
                    <f:input bean="student" property="lastName" class="form-control" placeholder="Sobrenome"/>
                    <f:input bean="student" property="degree" class="form-control" placeholder="Curso da Gradução"/>
                    <f:input bean="student" property="semester" class="form-control" placeholder="Semestre"/>
                    <f:input bean="student" property="email" class="form-control" placeholder="E-mail"/>
                    <f:input bean="student" property="username" class="form-control" placeholder="Nome de usuário"/>
                    <f:input bean="student" property="password" class="form-control" placeholder="Senha"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
