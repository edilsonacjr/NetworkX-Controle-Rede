<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entidades.Aluno" %>
<%@page import="entidades.Curso" %>
<%@page import="entidades.Periodo" %>
<%@page import="entidades.Materia" %>
<%@page import="java.util.*" %>
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
        <link href="dashboard.css" rel="stylesheet">
    </head>

    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="principalAdmin.jsp">SisAlunos
                        <br>
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="ServLogoff">Sair
                                <br>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li>
                            <a href="principalAdmin.jsp" >Página Principal</a>
                        </li>
                        <li>
                            <a href="Professores.jsp">Professores</a>
                        </li>
                        <li>
                            <a href="Alunos.jsp">Alunos</a>
                        </li>
                        <li>
                            <a href="Cursos.jsp">Cursos</a>
                        </li>
                        <li>
                            <a href="Usuarios.jsp">Usuários</a>
                        </li>
                        <li>
                            <a href="Periodos.jsp">Períodos</a>
                        </li>
                        <li>
                            <a href="MateriasAdmin.jsp">Matérias</a>
                        </li>
                        <li class="active">
                            <a href="AlunosMaterias.jsp">Matrícula de Alunos</a>
                        </li>
                    </ul>

                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Editar Relação Aluno Matéria  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        <br>
                    </h1>

                    <form class="form-horizontal">
                        <fieldset>

                            <div class="form-group">
                                <c:choose>
                                    <c:when test="${empty erro}"></c:when>
                                    <c:otherwise>
                                        ${erro}
                                    </c:otherwise>
                                </c:choose> 
                            </div>


                            <!-- Select Basic -->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="selectbasic">Aluno</label>
                                <div class="col-md-5">
                                    <select id="selectbasic" name="aluno" class="form-control">
                                        <c:forEach items="${alunos}" var="aluno">
                                            <c:if test="${aluno.id == alunomateria.aluno.id}">
                                                <option value="${aluno.id}" selected>${aluno.nome}</option>
                                            </c:if>
                                            <c:if test="${aluno.id != alunomateria.aluno.id}">
                                                <option value="${aluno.id}">${aluno.nome}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="selectbasic">Materias</label>
                                <div class="col-md-5">
                                    <select id="selectbasic" name="materia" class="form-control">
                                        <c:forEach items="${materias}" var="materia">
                                            <c:if test="${materia.id == alunomateria.materia.id}">
                                                <option value="${materia.id}" selected>${materia.nome}</option>
                                            </c:if>
                                            <c:if test="${materia.id != alunomateria.materia.id}">
                                                <option value="${materia.id}">${materia.nome}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <!-- Button (Double) -->
                            <div class="form-group">
                                <label class="col-md-4 control-label text-left" for="button1id"></label>
                                <div class="col-md-5 text-right">
                                    <button id="button1id" name="button1id" class="btn btn-success" onclick="form.action = 'ServAtualizarAlunoMateria';
                                            form.method = 'post'" value="${alunomateria.id}" >Salvar</button>
                                    <button id="button2id" name="button2id" class="btn btn-danger" onclick="form.action = 'AlunoMaterias.jsp';
                                            form.method = 'post';">Cancelar</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>

                    <div class="table-responsive">

                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
          ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
        <script src="assets/js/docs.min.js"></script>

    </body>

</html>