<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="assets/img/faviconsss.ico">
        <title>SisAlunos</title>
        <link href="dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="jumbotron.css" rel="stylesheet">
    </head>

    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">SisAlunos
                        <br>
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" role="form" action="ServLogin" method="post">
                        <div class="form-group">
                            <input name="login" type="text" placeholder="Login" class="form-control">
                        </div>
                        <div class="form-group">
                            <input name="senha" type="password" placeholder="Senha" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Entrar</button>
                    </form>
                </div>
                <!--/.navbar-collapse -->
            </div>
        </div>

        <!-- Main jumbotron for a primary marketing message or call to action -->
        ${erro}
        <div class="jumbotron">
            <div class="container">
                
                <h1>SisAlunos</h1>
                <p>O SisAlunos é um sistema de controle de alunos e professores, criado para melhorar o acesso a informação por parte dos alunos, e prover uma ferramente de controle ao professor. Além de permitir a integração desse com outros sistemas como: controle
                    de biblioteca, acesso wireless e etc.</p>
                <p></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4">
                    <h2>Alunos</h2>
                    <p>O SisAlunos é uma ferramenta criada para facilitar o acesso a informações úteis para o aluno, como notas e faltas.</p>
                    <p></p>
                </div>
                <div class="col-md-4">
                    <h2>Professores</h2>
                    <p>O SisAlunos provê ao professor uma plataforma para o controle dos dados dos alunos.</p>
                    <p></p>
                </div>
                <div class="col-md-4">
                    <h2>Administrativos</h2>
                    <p>O SisAlunos permite aos administrativos um controle mais fácil de alunos e professores.</p>
                    <p></p>
                </div>
            </div>

            <hr>

            <footer>
                <p>© Grupo Thread 2014</p>
            </footer>
        </div>
        <!-- /container -->


        <!-- Bootstrap core JavaScript
          ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
    </body>
</html>