<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-course" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.course}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.course}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
           <g:form controller="course" action="save" method="post">
              <div class="input">
                <label for="course.name">Name:</label>
                <g:textField name="course.name" />
              </div>
              <div class="input">
                <label for="course.books">Books:</label>
                <g:select multiple="multiple" optionKey="id" name="course.books" from="${books}" noSelection="['':'-Choose books-']" />
              </div>
              <div class="input">
                <label>Professor:</label>
                <g:select optionKey="id" name="course.professor" from="${professors}" noSelection="['':'-Choose a Professor-']" />/>
              </div> 
              <div class="input">
                <g:submitButton name="Create" value="Create" />
              </div>
            </g:form>
        </div>
    </body>
</html>
