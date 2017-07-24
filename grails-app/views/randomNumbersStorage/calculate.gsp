<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Sara's app</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    </head>
    <body>
        <main class="container-fluid">
            <a href="#list-randomNumbersStorage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div class="row">
                <form action="/randomNumbersStorage/graph" method="post" class="col-lg-6 col-lg-offset-3">
                    <div class="form-group">
                        <label for="firstNumber">Insert the first number:</label>
                        <input type="text" id="firstNumber" name="firstNumber" placeholder="1"  class="form-control" >
                    </div>
                    <div class="form-group">
                        <label for="secondNumber">Insert the second number:</label>
                        <input type="text" id="secondNumber" name="secondNumber" placeholder="1"  class="form-control" >
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Do calculations</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>