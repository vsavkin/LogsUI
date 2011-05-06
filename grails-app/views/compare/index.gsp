<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>Compare Files</title>
</head>
<body>

<g:each var="c" in="${collections}">
    <g:render template="/logFile" model="${c}"/>
</g:each>

</body>
</html>