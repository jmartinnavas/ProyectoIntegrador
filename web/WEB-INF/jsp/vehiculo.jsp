<%-- 
    Document   : vehiculo
    Created on : 26/03/2017, 07:41:46 PM
    Author     : root
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Vehiculo</title>
        <link href=<c:url value="/PUBLIC/assets/css/bootstrap.min.css"></c:url> rel="stylesheet" />
            <!--  Material Dashboard CSS    -->
            <link href=<c:url value="/PUBLIC/assets/css/material-dashboard.css"></c:url> rel="stylesheet"/>

            <!--  CSS for Demo Purpose, don't include it in your project     -->
            <link href=<c:url value="/PUBLIC/assets/css/demo.css"></c:url> rel="stylesheet" />
            <link href="" rel="stylesheet" />
            <!--     Fonts and icons     -->
            <link href=<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/> rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons" rel='stylesheet' type='text/css'>

        <link href="<c:url value="/PUBLIC/resources/css/style.css"></c:url>" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/PUBLIC/resources/materialize/css/materialize.css"></c:url>" 
              media="screen,projection"/>
            <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        </head>
        <body>



            <div class="wrapper">

                <div class="sidebar" data-color="red">
                    <!--
                    Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"
    
                    Tip 2: you can also add an image using data-image tag
                    -->

                    <div class="logo">
                        <p class="simple-text">
                            ADMIN TAXI S.A
                        </p>
                    </div>

                    <div style="black" class="sidebar-wrapper">
                        <ul  class="nav">
                            <li>
                                <a href="<c:url value="index.htm"></c:url>">
                                    <i class="material-icons">dashboard</i>
                                    <p>Dashboard</p>
                                </a>
                            </li>
                            <li>
                                <a href="user.html">
                                    <i class="material-icons">person</i>
                                    <p>Perfil del Administrador</p>
                                </a>
                            </li>
                            <li class="active">
                                <a href=<c:url value="vehiculo.htm"></c:url>>
                                    <i class="material-icons">content_paste</i>
                                    <p>Modulo Vehiculos</p>
                                </a>
                            </li>
                            <li >
                                <a href=<c:url value="entrega.htm"></c:url>>
                                    <i class="material-icons">library_books</i>
                                    <p>Modulo Entrega</p>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="conductor.htm"></c:url>">
                                    <i class="material-icons">bubble_chart</i>
                                    <p>Modulo Conductores</p>
                                </a>
                            </li>
                            <li>
                                <a href="maps.html">
                                    <i class="material-icons">location_on</i>
                                    <p>Modulo Incidencias</p>
                                </a>
                            </li>
                            <li>
                                <a href="notifications.html">
                                    <i class="material-icons text-gray">notifications</i>
                                    <p>Modulo Reportes</p>
                                </a>
                            </li>
                            <li class="active-pro">
                                <a href="upgrade.html">
                                    <i class="material-icons">unarchive</i>
                                    <p>Acerca de</p>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="main-panel" data-color="red">
                    <nav class="navbar navbar-transparent navbar-absolute">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            <c:choose>
                                <c:when test="${usuario!=null}">
                                    <a class="navbar-brand" href="#">Bienvenido ${usuario}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="navbar-brand" href="#">Bienvenido Invitado</a>
                                </c:otherwise>
                            </c:choose>                            </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="material-icons">notifications</i>
                                        <span class="notification">5</span>
                                        <p class="hidden-lg hidden-md">Notifications</p>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Configuracion de mi perfil</a></li>
                                        <li><a href="#">Reportar Problema</a></li>
                                        <li><a href="#">Cerrar Sesion</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                
                </nav>

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header" data-background-color="red">
                                        <h4 class="title">Vehiculos</h4>
                                        <p class="category">Informacion General del modulo</p>
                                        <a class="btn-floating" href=<c:url value="AddVeh.htm"></c:url>><i class="material-icons">add</i></a>
                                        </div>
                                        <div class="card-content table-responsive">
                                            <table class="table">
                                                <thead class="text-primary">
                                                <th data-field="placa"  >Placa</th>
                                                <th data-field="marca" >Marca</th>
                                                <th data-field="modelo" >Modelo</th>
                                                <th data-field="motor" >Motor</th>
                                                <th data-field="fecha_ingreso">Fecha de registro</th>
                                                <th data-field="fecha_ingreso">Fecha de soat</th>

                                                </thead>
                                                <tbody>
                                                <c:forEach items="${datos}" var="dato">
                                                    <tr style="background-color:#FFFF;">
                                                        <td><c:out value="${dato.placa}"/></td>
                                                        <td><c:out value="${dato.marca}"/></td>
                                                        <td><c:out value="${dato.modelo}"/></td>
                                                        <td><c:out value="${dato.motor}"/></td>
                                                        <td><c:out value="${dato.fecha_ingreso}"/></td>
                                                        <td><c:out value="${dato.fecha_soat}"/></td>

                                                        <td>
                                                            <a  href="<c:url value="ConsultVeh.htm?id=${dato.placa}"></c:url>">
                                                                    <i class="material-icons right">zoom_in</i>
                                                                </a>

                                                                <a  href="<c:url value="EditVeh.htm?id=${dato.placa}"></c:url>">
                                                                    <i class="material-icons right">edit</i>
                                                                </a>
                                                                <a  href="<c:url value="DeleteVeh.htm?id=${dato.placa}"></c:url>">
                                                                    <i class="material-icons right">delete</i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Import jQuery before materialize.js-->
                <script type="text/javascript" src="<c:url value="/PUBLIC/resources/js/jquery.js"></c:url>"></script>
                <script type="text/javascript" src="<c:url value="/PUBLIC/resources/materialize/js/materialize.js"></c:url>"></script>
                </body>
                </html>
