<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-course" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <tr>
                    <td>Name</td>
                    <td>Books</td>
                </tr>
                <g:each in="${courseList}" var="course">
                <tr>
                    <td><g:link action="show" id="${course.id}">${course.name}</g:link></td>
                    <td>
                        <g:each in="${course.books}" var="book">
                            <g:link controller="book" action="show" id="${book.id}">${book.name}</g:link>
                        </g:each>
                    </td>
                </tr>
                </g:each>        
            </table>

            <div class="pagination">
                <g:paginate total="${courseCount ?: 0}" />
            </div>
        </div>
    </body>
</html>