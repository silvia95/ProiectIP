

<html>
<head>
    <title>Pagina de start</title>
    <link rel="stylesheet" type="text/css" href="css/container.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            document.getElementById('activitate_cercetare_show').style.display = "none";
            document.getElementById('editare_profil_show').style.display = "none";
            document.getElementById('generare_raport_show').style.display = "none";
            document.getElementById('productie_stiintifica_show').style.display = "none";
            document.getElementById('impact_stiintific_show').style.display = "none";
            document.getElementById('alte_realizari_show').style.display = "none";
        });

        function editare_profil_show() {
            document.getElementById('activitate_cercetare_show').style.display = "none";
            document.getElementById('editare_profil_show').style.display = "block";
            document.getElementById('generare_raport_show').style.display = "none";
            document.getElementById('productie_stiintifica_show').style.display = "none";
            document.getElementById('impact_stiintific_show').style.display = "none";
            document.getElementById('alte_realizari_show').style.display = "none";
        }
        function generare_raport_show() {
            document.getElementById('activitate_cercetare_show').style.display = "none";
            document.getElementById('editare_profil_show').style.display = "none";
            document.getElementById('generare_raport_show').style.display = "block";
            document.getElementById('productie_stiintifica_show').style.display = "none";
            document.getElementById('impact_stiintific_show').style.display = "none";
            document.getElementById('alte_realizari_show').style.display = "none";
        }

        function activitate_cercetare_show() {
            document.getElementById('activitate_cercetare_show').style.display = "block";
            document.getElementById('editare_profil_show').style.display = "none";
            document.getElementById('generare_raport_show').style.display = "none";
            document.getElementById('productie_stiintifica_show').style.display = "none";
            document.getElementById('impact_stiintific_show').style.display = "none";
            document.getElementById('alte_realizari_show').style.display = "none";
        }
        function productie_stiintifica_show() {
            document.getElementById('activitate_cercetare_show').style.display = "block";
            document.getElementById('editare_profil_show').style.display = "none";
            document.getElementById('generare_raport_show').style.display = "none";
            document.getElementById('productie_stiintifica_show').style.display = "block";
            document.getElementById('impact_stiintific_show').style.display = "none";
            document.getElementById('alte_realizari_show').style.display = "none";
        }
        function impact_stiintific_show() {
            document.getElementById('activitate_cercetare_show').style.display = "block";
            document.getElementById('editare_profil_show').style.display = "none";
            document.getElementById('generare_raport_show').style.display = "none";
            document.getElementById('productie_stiintifica_show').style.display = "none";
            document.getElementById('impact_stiintific_show').style.display = "block";
            document.getElementById('alte_realizari_show').style.display = "none";
        }
        function alte_realizari_show() {
            document.getElementById('activitate_cercetare_show').style.display = "block";
            document.getElementById('editare_profil_show').style.display = "none";
            document.getElementById('generare_raport_show').style.display = "none";
            document.getElementById('productie_stiintifica_show').style.display = "none";
            document.getElementById('impact_stiintific_show').style.display = "none";
            document.getElementById('alte_realizari_show').style.display = "block";
        }
    </script>

</head>
<body>

<div class="loader"
     style=" display:none; position: fixed; width: 100%; height: 100%; background-color: rgba(0,0,0,0.7); color: #fff;  z-index: 9999999;">
    <img src="load.gif" style="position: absolute; top:20%; left:40%;">
</div>
<section id="main_section">
    <section id="header_section">
        <a href="http://infoarea.net/container.php"><img id="logo" src="img/logo-fii.png"/></a>
        <!--<a href="#"  id="editare-profil" class="btn btn-default"><b>Editare Profil</b></a>-->
        <input type="button" value="Editare Profil" onclick="editare_profil_show()" class="btn btn-default"
               id="editare-profil"/>
        <input type="button" value="Activitate de cercetare" onclick="activitate_cercetare_show()"
               class="btn btn-default" id="activitate_cercetare"/>
        <input type="button" value="Generare Raport" onclick="generare_raport_show()" class="btn btn-default"
               id="generare-raport"/>
        <section id="align-right">
            <form id="navbar-search" role="search">
                <section class="input-group">
                    <input id="textbox-search" type="text" class="form-control" placeholder="Search" name="search">
                    <section class="input-group-btn">
                        <button id="button-search" class="btn btn-default" type="submit"><i
                                class="glyphicon glyphicon-search"></i></button>
                        <a href="#" id="logout" class="btn btn-danger"><i class="glyphicon glyphicon-off"></i></a>
                        <input type="button" value="Admin" onclick="admin_show()" class="btn btn-default" id="admin"
                               style="display:none"/>
                        <input type="button" value="Decan" onclick="dec_show()" class="btn btn-default" id="decan"
                               style="display:none"/>
                    </section>
                </section>
            </form>
        </section>
    </section>

    <section id="da_big_section">

        <!-- editare profil page -->
        <iframe width="100%"
                height="100%"
                frameborder="0"
                title="Editare profil"
                src="editprofil.html"
                id="editare_profil_show">
        </iframe>


        <!-- activitate stiintifica buttons -->
        <section class="butoane" id="activitate_cercetare_show">
            <input type="button" value="Productie stiintifica" onclick="productie_stiintifica_show()"
                   class="btn btn-default" id="productie-stiintifica"/>
            <input type="button" value="Impact stiintific" onclick="impact_stiintific_show()" class="btn btn-default"
                   id="impact-stiintific"/>
            <input type="button" value="Alte tipuri de realizari" onclick="alte_realizari_show()"
                   class="btn btn-default" id="alte-realizari"/>
        </section>

        <!-- Productie stiintifica -->
        <iframe width="100%"
                height="100%"
                frameborder="0"
                title="Editare productie stiintifica"
                src="butoane/prod-stiintifica.html"
                id="productie_stiintifica_show">
        </iframe>
        <!-- Impact Stiintific -->
        <iframe width="100%"
                height="100%"
                frameborder="0"
                title="Editare impact-stiintific"
                src="butoane/impact-stiintific.html"
                id="impact_stiintific_show">
        </iframe>
        <!-- Alte realizari -->
        <iframe width="100%"
                height="100%"
                frameborder="0"
                title="Editare alte realizari"
                src="butoane/alte-realizari.html"
                id="alte_realizari_show">
        </iframe>

        <!-- Generare Raport -->
        <iframe width="100%"
                height="100%"
                frameborder="0"
                title="Generare raport"
                src="generare-raport.php"
                id="generare_raport_show">
        </iframe>

    </section>

    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>

        <p id="fii">Facultatea de Informatica</p>
    </section>
</section>
</body>
</html>
