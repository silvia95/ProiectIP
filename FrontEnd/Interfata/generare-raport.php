<html>
<head>
    <title>Generare Raport</title>
    <link rel="stylesheet" type="text/css" href="css/generare-raport.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script type="text/javascript" src="js/table_export/tableExport.js"></script>
    <script type="text/javascript" src="js/table_export/jquery.base64.js"></script>
    <script type="text/javascript" src="js/table_export/jspdf/libs/sprintf.js"></script>
    <script type="text/javascript" src="js/table_export/jspdf/jspdf.js"></script>
    <script type="text/javascript" src="js/table_export/jspdf/libs/base64.js"></script>
    <script type="text/javascript" src="js/table_export/html2canvas.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script>
        $(document).ready(function () {
            document.getElementById('perioada_items').style.display = "none";
            document.getElementById('table-to-export').style.display = "none";
            document.getElementById('butoane-de-export').style.display = "none";

            document.getElementById('ps_items').style.display = "none";
            document.getElementById('ps_autor_info').style.display = "none";
            document.getElementById('ps_articol_info').style.display = "none";
            document.getElementById('ps_revista_info').style.display = "none";
            document.getElementById('ps_clasificare_info').style.display = "none";
            document.getElementById('ps_tiparticol_info').style.display = "none";
            document.getElementById('ps_punctaj_info').style.display = "none";

            document.getElementById('is_items').style.display = "none";
            document.getElementById('is_autor_info').style.display = "none";
            document.getElementById('is_articol_info').style.display = "none";
            document.getElementById('is_revista_info').style.display = "none";
            document.getElementById('is_clasificare_info').style.display = "none";
            document.getElementById('is_tiparticol_info').style.display = "none";
            document.getElementById('is_punctaj_info').style.display = "none";

            document.getElementById('aac_items').style.display = "none";
            document.getElementById('aac_implicare_proiecte_info').style.display = "none";
            document.getElementById('aac_organizare_evenimente_info').style.display = "none";
            document.getElementById('aac_brevete_inventii_info').style.display = "none";
            document.getElementById('aac_profesor_universitate_info').style.display = "none";
            document.getElementById('aac_autor_carti_info').style.display = "none";

            document.getElementById('aacip_nume_proiect_info').style.display = "none";
            document.getElementById('aacip_director_info').style.display = "none";
            document.getElementById('aacip_calitate_info').style.display = "none";
            document.getElementById('aacip_valoare_euro_info').style.display = "none";
            document.getElementById('aacip_valoare_ron_info').style.display = "none";
            document.getElementById('aacip_punctaj_info').style.display = "none";

        });

        function toggle(className, obj) {
            var $input = $(obj);
            if ($input.prop('checked'))
                $(className).show();
            else
                $(className).hide();
        }

        $(function () {
            $("#perioada_de_la").datepicker();
        });
        $(function () {
            $("#perioada_pana_la").datepicker();
        });

        function generare_raport_show() {
            document.getElementById('table-to-export').style.display = "";
            document.getElementById('butoane-de-export').style.display = "";
        }

    </script>
</head>
<body>
<section style="width:100%" class="row">
    <section class="col-xs-2">

        <section class="checkbox">
            <label> <input id="perioada" type="checkbox" value="" onclick="toggle('.perioada_items', this)"><b>
                    Perioada </b> </label>
            <section class="form-inline perioada_items" id="perioada_items">
                <input type="text" class="form-control" id="perioada_de_la" placeholder="De la"/> -
                <input type="text" class="form-control" id="perioada_pana_la" placeholder="Pana la"/>
            </section>
        </section>

        <section class="checkbox">
            <label> <input id="centralizare" type="checkbox" value=""><b>
                    Centralizare </b> </label>
        </section>

        <p><b>Activitate de cercetare</b></p>
        <section class="checkbox">
            <label> <input id="productie_stiintifica" type="checkbox" value="" onclick="toggle('.ps_items', this)"><b>
                    Productie Stiintifica </b></input> </label>
            <section class="ps_items" id="ps_items">

                <label> <input id="ps_articol" type="checkbox" value="" onclick="toggle('.ps_articol_info', this)">
                    Numele articolului </input> </label>
                <section class="form-inline ps_articol_info " id="ps_articol_info">
                    <input type="text" class="form-control" placeholder="Numele articolului"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>

                <label> <input id="ps_autor" type="checkbox" value="" onclick="toggle('.ps_autor_info', this)">
                    Contine autorul/autorii </input> </label>
                <section class="form-inline ps_autor_info " id="ps_autor_info">
                    <input type="text" class="form-control" placeholder="Numele autorului"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>

                <label> <input id="ps_revista" type="checkbox" value="" onclick="toggle('.ps_revista_info', this)">
                    Publicat in revista </input> </label>
                <section class="form-inline ps_revista_info " id="ps_revista_info">
                    <input type="text" class="form-control" placeholder="Numele revistei"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>
                <br>
                <label> <input id="ps_clasificare" type="checkbox" value=""
                               onclick="toggle('.ps_clasificare_info', this)">
                    Clasificare </input> </label>
                <section class="form-inline ps_clasificare_info dropdown" id="ps_clasificare_info">
                    <button class="btn btn-default dropdown-toggle" type="button" id="menu1 ps_clasificare_info"
                            data-toggle="dropdown">
                        Clasificare
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                        <li role="ps_revista"><a role="menuitem" tabindex="-1" href="#">A</a></li>
                        <li role="ps_revista"><a role="menuitem" tabindex="-1" href="#">B</a></li>
                        <li role="ps_revista"><a role="menuitem" tabindex="-1" href="#">C</a></li>
                    </ul>
                </section>
                <br>
                <label> <input id="ps_tiparticol" type="checkbox" value=""
                               onclick="toggle('.ps_tiparticol_info', this)">
                    Tip Articol </input> </label>
                <section class="form-inline ps_tiparticol_info " id="ps_tiparticol_info">
                    <input type="text" class="form-control" placeholder="Tipul articolului"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>
                <br>
                <label> <input id="ps_punctaj" type="checkbox" value=""
                               onclick="toggle('.ps_punctaj_info', this)">
                    Punctaj </input> </label>
                <section class="form-inline ps_punctaj_info " id="ps_punctaj_info">
                    <input type="text" class="form-control" id="punctaj_de_la" placeholder="De la"/> -
                    <input type="text" class="form-control" id="punctaj_pana_la" placeholder="Pana la"/>
                </section>

            </section>
        </section>

        <section class="checkbox">
            <label> <input id="impact_stiintific" type="checkbox" value="" onclick="toggle('.is_items', this)"><b>
                    Impact Stiintific </b></input> </label>
            <section class="is_items" id="is_items">

                <label> <input id="is_articol" type="checkbox" value="" onclick="toggle('.is_articol_info', this)">
                    Numele articolului </input> </label>
                <section class="form-inline is_articol_info " id="is_articol_info">
                    <input type="text" class="form-control" placeholder="Numele articolului"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>

                <label> <input id="is_autor" type="checkbox" value="" onclick="toggle('.is_autor_info', this)">
                    Contine autorul/autorii </input> </label>
                <section class="form-inline is_autor_info " id="is_autor_info">
                    <input type="text" class="form-control" placeholder="Numele autorului"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>

                <label> <input id="is_revista" type="checkbox" value="" onclick="toggle('.is_revista_info', this)">
                    Publicat in revista </input> </label>
                <section class="form-inline ps_revista_info " id="is_revista_info">
                    <input type="text" class="form-control" placeholder="Numele revistei"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>
                <br>
                <label> <input id="is_clasificare" type="checkbox" value=""
                               onclick="toggle('.is_clasificare_info', this)">
                    Clasificare </input> </label>
                <section class="form-inline is_clasificare_info dropdown" id="is_clasificare_info">
                    <button class="btn btn-default dropdown-toggle" type="button" id="menu1 is_clasificare_info"
                            data-toggle="dropdown">
                        Clasificare
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                        <li role="is_revista"><a role="menuitem" tabindex="-1" href="#">A</a></li>
                        <li role="is_revista"><a role="menuitem" tabindex="-1" href="#">B</a></li>
                        <li role="is_revista"><a role="menuitem" tabindex="-1" href="#">C</a></li>
                        <li role="is_revista"><a role="menuitem" tabindex="-1" href="#">D</a></li>
                    </ul>
                </section>
                <br>
                <label> <input id="is_tiparticol" type="checkbox" value=""
                               onclick="toggle('.is_tiparticol_info', this)">
                    Tip Articol </input> </label>
                <section class="form-inline is_tiparticol_info " id="is_tiparticol_info">
                    <input type="text" class="form-control" placeholder="Tipul articolului"/>
                    <button type="button" class="btn btn-success">+</button>
                    <button type="button" class="btn btn-danger">X</button>
                </section>
                <br>
                <label> <input id="is_punctaj" type="checkbox" value=""
                               onclick="toggle('.is_punctaj_info', this)">
                    Punctaj </input> </label>
                <section class="form-inline is_punctaj_info " id="is_punctaj_info">
                    <input type="text" class="form-control" id="punctaj_de_la" placeholder="De la"/> -
                    <input type="text" class="form-control" id="punctaj_pana_la" placeholder="Pana la"/>
                </section>

            </section>
        </section>

        <section class="checkbox">
            <label> <input id="alte_activitati_cercetare" type="checkbox" value="" onclick="toggle('.aac_items', this)"><b>
                    Alte tipuri de activitati </b></input> </label>
            <section class="aac_items" id="aac_items">

                <label> <input id="aac_implicare_proiecte" type="checkbox" value="" onclick="toggle('.aac_implicare_proiecte_info', this)"/><b> Implicare in proiecte </b></label>
                    <section class="aac_implicare_proiecte_info" id="aac_implicare_proiecte_info">
                        <label> <input id="aacip_nume_proiect" type="checkbox" value="" onclick="toggle('.aacip_nume_proiect_info', this)"/> Nume proiect </label>
                        <br><label> <input id="aacip_director" type="checkbox" value="" onclick="toggle('.aacip_director_info', this)"/> Director </label>
                        <br><label> <input id="aacip_calitate" type="checkbox" value="" onclick="toggle('.aacip_calitate_info', this)"/> Calitate </label>
                        <br><label> <input id="aacip_valoare_euro" type="checkbox" value="" onclick="toggle('.aacip_valoare_euro_info', this)"/> Valoare Euro </label>
                        <br><label> <input id="aacip_valoare_ron" type="checkbox" value="" onclick="toggle('.aacip_valoare_ron_info', this)"/> Valoare Ron </label>
                        <br><label> <input id="aacip_punctaj" type="checkbox" value="" onclick="toggle('.aacip_punctaj_info', this)"/> Punctaj </label>
                    </section>

                <label> <input id="aac_organizare_evenimente" type="checkbox" value="" onclick="toggle('.aac_organizare_evenimente_info', this)"/><b> Organizare evenimente </b></label>
                    <section class="aac_organizare_evenimente_info" id="aac_organizare_evenimente_info">

                    </section>

                <label> <input id="aac_brevete_inventii" type="checkbox" value="" onclick="toggle('.aac_brevete_inventii_info', this)"/><b> Brevete si inventii </b></label>
                    <section class="aac_brevete_inventii_info" id="aac_brevete_inventii_info">

                    </section>

                <label> <input id="aac_profesor_universitate" type="checkbox" value="" onclick="toggle('.aac_profesor_universitate_info', this)"/><b> Profesor invitat la o universitate </b></label>
                    <section class="aac_profesor_universitate_info" id="aac_profesor_universitate_info">

                    </section>

                <label> <input id="aac_autor_carti" type="checkbox" value=""onclick="toggle('.aac_autor_carti_info', this)"/><b> Autor carti/capitole </b></label>
                    <section class="aac_autor_carti_info" id="aac_autor_carti_info">

                    </section>

            </section>
        </section>


        <button id="generate-raport" class="btn btn-default" onclick="generare_raport_show()">Generate Raport</button>


        <div class="btn-group" id="butoane-de-export">
            <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">
                Export This <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'excel',escape:'false',tableName:'exported-table'});">Export
                        as Excel</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'doc',escape:'false',tableName:'exported-table'});">Export
                        as Word</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'pdf',escape:'false',tableName:'exported-table'});">Export
                        as PDF</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'png',escape:'false',tableName:'exported-table'});">Export
                        as PNG</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'xml',escape:'false',tableName:'exported-table'});">Export
                        as XML</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'json',escape:'false',tableName:'exported-table'});">Export
                        as JSON</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'csv',escape:'false',tableName:'exported-table'});">Export
                        as CSV</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'txt',escape:'false',tableName:'exported-table'});">Export
                        as TXT</a></li>
                <li><a href="#"
                       onClick="$('#table-to-export').tableExport({type:'sql',escape:'false',tableName:'exported-table'});">Export
                        as SQL</a></li>
            </ul>
        </div>

    </section>
    <section class="col-xs-9">
        <table class="table" id="table-to-export">
            <thead>
            <tr>
                <th>#</th>
                <th>Column 1</th>
                <th>Column 2</th>
                <th>Column 3</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Row1Column1</td>
                <td>Row1Column2</td>
                <td>Row1Column3</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Row2Column1</td>
                <td>Row2Column2</td>
                <td>Row2Column3</td>
            </tr>
            <tr>
                <td>3</td>
                <td>Row3Column1</td>
                <td>Row3Column2</td>
                <td>Row3Column3</td>
            </tr>
            <tr>
                <td>4</td>
                <td>Row4Column1</td>
                <td>Row4Column2</td>
                <td>Row4Column3</td>
            </tr>
            <tr>
                <td>5</td>
                <td>Row5Column1</td>
                <td>Row5Column2</td>
                <td>Row5Column3</td>
            </tr>
            </tbody>
        </table>
    </section>

</section>
</body>
</html>