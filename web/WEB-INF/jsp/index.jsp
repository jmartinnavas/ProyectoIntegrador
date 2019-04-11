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

        <title>AdminTaxi</title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />

        <!-- Bootstrap core CSS     -->
        <link href=<c:url value="/PUBLIC/assets/css/bootstrap.min.css"></c:url> rel="stylesheet" />
            <!--  Material Dashboard CSS    -->
            <link href=<c:url value="/PUBLIC/assets/css/material-dashboard.css"></c:url> rel="stylesheet"/>

            <!--  CSS for Demo Purpose, don't include it in your project     -->
            <link href=<c:url value="/PUBLIC/assets/css/demo.css"></c:url> rel="stylesheet" />
            <link href="" rel="stylesheet" />
            <!--     Fonts and icons     -->
            <link href=<c:url value="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/> rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons" rel='stylesheet' type='text/css'>
    </head>

    <body>

        <div class="wrapper">

            <div class="sidebar" data-color="purple">
                <!--
                Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

                Tip 2: you can also add an image using data-image tag
                -->

                <div class="logo">
                    <p class="simple-text">
                        Admitaxi SA
                    </p>
                </div>

                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="active">
                            <a href="<c:url value="index.htm"></c:url>">
                                    <i class="material-icons">dashboard</i>
                                                                    <p>Pagina Principal</p>

                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="vehiculo.htm"></c:url>">
                                    <i class="material-icons">content_paste</i>
                                    <p>Modulo Vehiculos</p>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="entrega.htm"></c:url>">
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
                                <a href=<c:url value="regfalla.htm"></c:url>>
                                    <i class="material-icons">location_on</i>
                                    <p>Modulo Incidencias</p>
                                </a>
                            </li>

                            <li>
                                <a href=<c:url value="reportes.htm"></c:url>>
                                    <i class="material-icons">location_on</i>
                                    <p>Modulo reportes</p>
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

                <div class="main-panel">
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
                            </c:choose>
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
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header" data-background-color="orange">
                                        <i class="material-icons">content_copy</i>
                                    </div>
                                    <div class="card-content">
                                        <c:forEach items="${datos3}" var="dato3">
                                            <p class="category">Vehiculos con soat Vencido</p>
                                            <h3 class="title">${dato3.vencidos} / ${dato3.totalc} </h3>
                                        </c:forEach>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons text-danger">warning</i> <a href="#pablo">Revisar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header" data-background-color="green">
                                        <i class="material-icons">store</i>
                                    </div>
                                    <div class="card-content">
                                        <p class="category">Ingresos del Dia</p>
                                        <c:forEach items="${datos4}" var="dato4">
                                            <h3 class="title">$${dato4.totaldia}</h3>
                                        </c:forEach>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">date_range</i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header" data-background-color="red">
                                        <i class="material-icons">info_outline</i>
                                    </div>
                                    <div class="card-content">

                                        <p class="category">Gastos por incidencias</p>
                                        <c:forEach items="${datos}" var="dato">
                                            <h3 class="title">$${dato.totalGastos}</h3>
                                        </c:forEach>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">local_offer</i> 
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header" data-background-color="blue">
                                        <i class="fa fa-twitter"></i>
                                    </div>
                                    <div class="card-content">
                                        <p class="category">Conductores Activos </p>
                                        <c:forEach items="${datos2}" var="dato2">
                                            <h3 class="title">${dato2.activos} / ${dato2.total}</h3>
                                        </c:forEach>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">update</i> Just Updated
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4 col-md-12">
                                <img src="https://admintaxi.com/wp-content/uploads/2017/06/retina-logo-admintaxi-autorizado-fleet-managment-system-in-the-cloud-sistema-de-administracion-de-vehiculos-de-transporte-publico.png" />
                            </div>

                            <div class="col-lg-8 col-md-12">
                                <div class="card">
                                    <div class="card-header" data-background-color="purple">
                                        <h4 class="title">Resumen de Conductores Activos</h4>

                                    </div>
                                    <div class="card-content table-responsive">
                                        <table class="table table-hover">
                                            <thead class="text-warning">
                                            <th>Cedula</th>
                                            <th>Nombres</th>
                                            <th>Apellidos</th>
                                            <th>Telefono</th>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${datos5}" var="dato5">
                                                    <tr>
                                                        <td><c:out value="${dato5.cedula}"/></td>
                                                        <td><c:out value="${dato5.nombre}"/></td>
                                                        <td><c:out value="${dato5.apellido}"/></td>
                                                        <td><c:out value="${dato5.telefono}"/></td>
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

                <footer class="footer">
                    <div class="container-fluid">
                        <nav class="pull-left">
                            <ul>
                                <li>
                                    <a href="#">
                                        Home
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Company
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Portfolio
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        Blog
                                    </a>
                                </li>
                            </ul>
                        </nav>
                        <p class="copyright pull-right">
                            &copy; <script>document.write(new Date().getFullYear())</script> <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
                        </p>
                    </div>
                </footer>
            </div>
        </div>

    </body>

    <!--   Core JS Files   -->
    <script src=<c:url value="/PUBLIC/assets/js/jquery-3.1.0.min.js"></c:url> type="text/javascript"></script>
    <script src=<c:url value="/PUBLIC/assets/js/bootstrap.min.js"></c:url> type="text/javascript"></script>
    <script src=<c:url value="/PUBLIC/assets/js/material.min.js"></c:url> type="text/javascript"></script>

        <!-- Material Dashboard javascript methods -->
        <script src=<c:url value="/PUBLIC/assets/js/material-dashboard.js"></c:url>></script>

        <!-- Material Dashboard DEMO methods, don't include it in your project! -->
        <script src=<c:url value="/PUBLIC/assets/js/demo.js"></c:url></script>

</html>