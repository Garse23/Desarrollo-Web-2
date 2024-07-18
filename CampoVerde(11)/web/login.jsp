<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="css/styleslogin.css">
    </head>
    <body>
        <main>
            <div class="box">
                <div class="inner-box">
                    <div class="forms-wrap">
                        <form action="procesarLogin" autocomplete="off" class="sign-in-form" method="post">
                            <div class="logo">
                                <img src="./img/logo.png" alt="easyclass" />
                                <a href="index.jsp"><h4>Campo Verde</h4></a>
                            </div>

                            <div class="heading">
                                <h2>Bienvenido</h2>
                                <h6>¿No estás registrado?</h6>
                                <a href="#" class="toggle">Registrate</a>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap">
                                    <input
                                        id="correo"
                                        name="correo"
                                        type="email"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Correo electronico</label>
                                </div>


                                <div class="input-wrap">
                                    <input
                                        id="contrasena"
                                        name="contrasena"
                                        type="password"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Contraseña</label>
                                </div>

                                <input type="submit" value="Iniciar Sesión" class="sign-btn" />


                            </div>
                        </form>

                        <form id="registro" action="procesarRegistro" autocomplete="off" class="sign-up-form" method="post">
                            <div class="logo">
                                <img src="./img/logo.png" alt="easyclass" />
                                <a href="index.jsp"><h4>Campo Verde</h4></a>
                            </div>

                            <div class="heading">
                                <h2>Registrate</h2>
                                <h6>Ya tienes una cuenta?</h6>
                                <a href="#" class="toggle">Iniciar Sesión</a>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        id="nombre"
                                        name="nombre"
                                        required
                                        />
                                    <label>Name</label>
                                </div>

                                <div class="input-wrap">
                                    <input
                                        type="email"
                                        class="input-field"
                                        autocomplete="off"
                                        id="correo"
                                        name="correo"
                                        required
                                        />
                                    <label>Email</label>
                                </div>

                                <div class="input-wrap">
                                    <input
                                        type="password"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        id="contrasena"
                                        name="contrasena"
                                        required
                                        />
                                    <label>Contraseña</label>
                                </div>
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        minlength="4"
                                        maxlength="9"
                                        class="input-field"
                                        autocomplete="off"
                                        id="telefono"
                                        name="telefono"
                                        required
                                        />
                                    <label>Teléfono</label>
                                </div>
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        minlength="8"
                                        maxlength="8"
                                        class="input-field"
                                        autocomplete="off"
                                        id="dni"
                                        name="dni"
                                        required
                                        />
                                    <label>DNI</label>
                                </div>
                                <input type="submit" value="Sign Up" class="sign-btn" />


                            </div>
                        </form>
                    </div>

                    <div class="carousel">
                        <div class="images-wrapper">
                            <img src="./img/image1.png" class="image img-1 show" alt="" />
                            <img src="./img/image2.png" class="image img-2" alt="" />
                            <img src="./img/image3.png" class="image img-3" alt="" />
                        </div>

                        <div class="text-slider">
                            <div class="text-wrap">
                                <div class="text-group">
                                    <h2>Productos Frescos</h2>
                                    <h2>100% Naturales</h2>
                                    <h2>Con la calidad que te mereces</h2>
                                </div>
                            </div>

                            <div class="bullets">
                                <span class="active" data-value="1"></span>
                                <span data-value="2"></span>
                                <span data-value="3"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!-- Javascript file -->

        <script src="js/app.js"></script>

    </body>
</html>

