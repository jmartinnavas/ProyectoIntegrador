<%-- 
    Document   : AddVeh
    Created on : 1/04/2017, 10:44:52 AM
    Author     : root
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Crear Entrega</title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />
        <!-- Bootstrap core CSS     -->
        <link href=<c:url value="/PUBLIC/assets/css/bootstrap.min.css"></c:url> rel="stylesheet" />
            <!--  Material Dashboard CSS    -->
            <link href=<c:url value="/PUBLIC/assets/css/material-dashboard.css"></c:url> rel="stylesheet"/>

            <!--  CSS for Demo Purpose, don't include it in your project     -->
            <link href=<c:url value="/PUBLIC/assets/css/demo.css"></c:url> rel="stylesheet" />

            <!--     Fonts and icons     -->
            <link href=<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/> rel="stylesheet"/>
        <link href="http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons" rel='stylesheet' type='text/css'/>
    </head>
</head>
<body>
    <nav class="navbar navbar-transparent navbar-absolute">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>


            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="material-icons">notifications</i>
                            <span class="notification">5</span>
                            <p class="hidden-lg hidden-md">Notifications</p>
                        </a>
                        <ul class="dropdown-menu"> 
                            <c:choose>
                                <c:when test="${usuario==null}">
                                    <li><a href=<c:url value="Registrarse.htm"></c:url>>Registrate</a></li>
                                        <li><a href="ingresar.htm">Ingresa</a></li>           
                                    </c:when>
                                    <c:otherwise>

                                    <li><a href="cerrar.htm">Cerrar Sesion</a></li>
                                    </c:otherwise>
                                </c:choose>


                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br>
    <br>
    <br>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header" data-background-color="red">
                            <h4 class="title">Registrar Entrega</h4>
                            <p class="category">Complete cada uno de los campos</p>
                        </div>
                        <div class="card-content">
                            <form:form method="post" commandName="Entregas"  >
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Fecha de Entrega</label>
                                            <form:input type="date" path="fecha_entrega" class="form-control"  />
                                            <form:errors path="fecha_entrega"  cssClass="alert alert-danger" />
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Valor</label>
                                            <form:input type="number" path="valor" class="form-control" />
                                            <form:errors path="valor"  cssClass="alert alert-danger" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Descripcion</label>
                                            <form:input path="Descripcion" class="form-control" />
                                            <form:errors path="Descripcion"  cssClass="alert alert-danger" />
                                        </div>
                                    </div>
                                  
                                </div>

                     
                                <div class="col-md-6">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Conductor</label>
                                        <form:input  path="conductor" class="form-control" value="${cedula}" disabled="disabled" />
                                        <form:errors path="conductor"  cssClass="alert alert-danger" />
                                    </div>
                                </div>

                                <input type="submit" class="btn btn-primary pull-right" value="Crear Registro" />
                                <div class="clearfix"></div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div> 

    </div>
</body>
<script src=<c:url value="/PUBLIC/assets/js/jquery-3.1.0.min.js"></c:url> type="text/javascript"></script>
<script src=<c:url value="/PUBLIC/assets/js/bootstrap.min.js"></c:url> type="text/javascript"></script>
<script src=<c:url value="/PUBLIC/assets/js/material.min.js"></c:url> type="text/javascript"></script>
    <!-- Material Dashboard javascript methods -->
    <script src=<c:url value="/PUBLIC/assets/js/material-dashboard.js"></c:url>></script>

    <!-- Material Dashboard DEMO methods, don't include it in your project! -->
    <script src=<c:url value="/PUBLIC/assets/js/demo.js"></c:url>></script>
</html>