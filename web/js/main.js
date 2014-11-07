/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#NovaVenda").hide(0.0000001);

    $("#BotaoNovo").click(function () {
        $('#BotaoEfetuadas').removeClass("active");
        $('#BotaoNovo').addClass("active");
        $("#VendasEfetuadas").hide(0.0000001);
        $("#NovaVenda").fadeIn('slow');
    });

    $("#BotaoEfetuadas").click(function () {
        $('#BotaoNovo').removeClass("active");
        $('#BotaoEfetuadas').addClass("active");
        $("#NovaVenda").hide(0.0000001);
        $("#VendasEfetuadas").fadeIn('slow');
    });
    
    $(".BotaoAdd").click(function () {
        var cod = $(this).parent().prev().prev().prev().prev().html();
        var qtde = $(this).prev().val();
        var nome = $(this).parent().prev().prev().prev().html();
        var aux = $(this).parent().prev().prev().html().split(' ');
        var preco = parseFloat(aux[1]);
        var total = parseFloat($('#total').html());
        var app;
        
        var max = $(this).prev().attr("max");
        $(this).prev().attr("max",max - qtde);
        
        $.ajax({
            url:  "ServletLote",
            type: 'post',
            data: {'codigo':cod, 'quantidade':qtde, 'tipo':'add'},
            success: function(data, textStatus, jqXHR) {
                data.trim();
                var arr = data.split('#');
                if(arr.length == 5){
                    app = "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[4]+"</td><td>"+arr[1]+"</td><td>"+arr[2]+"</td><td>"+arr[3]+"</td><td>"+(preco*parseFloat(arr[4]))+"</td></tr>";
                    total = total + (preco*parseFloat(arr[4]));
                    $('#total').html(total);
                    $('#flagTotal').val(total);
                }else{
                    i = 1;
                    while(i < arr.length){
                        if((i+4) >= arr.length ) {
                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+qtde+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td><td>"+(preco*parseFloat(qtde))+"</td></tr>";
                            total = total + (preco*parseFloat(qtde));
                            $('#total').html(total);
                            $('#flagTotal').val(total);
                        } else {
                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[i+3]+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td><td>"+(preco*parseFloat(arr[i+3]))+"</td></tr>";
                            total = total + (preco*parseFloat(arr[i+3]));
                            $('#total').html(total);
                            $('#flagTotal').val(total);
                        }
                        qtde = qtde-arr[i+3];
                        i = i+4;
                    }
                }
                $('#TabelaVenda').children().next().append(app);
            }
        });
        
        $(this).parent().prev().html(max-qtde);       
        
        $('#flagProduto').val("1");
        console.log($('#SelCliente').val());
        if($('#SelCliente').val() != "-1"){
            $('#AlertVenda').fadeOut();
            $('#FinalizaVenda').removeAttr('disabled');
        }
        
    });
    
    $("#SelCliente").change(function (){
        if( ($('#flagProduto').val() == 1) && ($("#SelCliente").val() != -1) ){
            $('#AlertVenda').fadeOut();
            $('#FinalizaVenda').removeAttr('disabled');
        }
    });
    
//    $(".BotaoDel").click(function () {
//        var cod = $(this).parent().prev().prev().prev().html();
//        var qtde = $(this).prev().val();
//        var nome = $(this).parent().prev().prev().html();
//        var app;
//        
//        var max = $(this).prev().attr("max");
//        $(this).prev().attr("max",max - qtde);
//        
//        $.ajax({
//            url:  "ServletLote",
//            type: 'post',
//            data: {'codigo':cod, 'quantidade':qtde, 'tipo':'add'},
//            success: function(data, textStatus, jqXHR) {
//                data.trim();
//                var arr = data.split('#');
//                if(arr.length == 5){
//                    app = "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[4]+"</td><td>"+arr[1]+"</td><td>"+arr[2]+"</td><td>"+arr[3]+"</td></tr>";
//                }else{
//                    i = 1;
//                    while(i < arr.length){
//                        if((i+4) >= arr.length )
//                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+qtde+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td></tr>";
//                        else
//                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[i+3]+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td></tr>";
//                        qtde = qtde-arr[i+3];
//                        i = i+4;
//                    }
//                }
//                $('#TabelaVenda').children().next().append(app);
//            }
//        });
//        
//        $(this).parent().prev().html(max-qtde);       
//    });
    
    $("#FinalizaVenda").click(function () {
        var cli = $('#SelCliente').val();
        var lotes = "";
        var quantidade = "";
        var total = $('#flagTotal').val();
        
        var apontador = $('#TabelaVenda').children().next().children();
        while(apontador.children().length > 0){
            lotes = lotes + apontador.children().next().next().next().html();
            apontador = apontador.next();
            if (apontador.children().length > 0)
                lotes = lotes+"#";
        }
        
        var apontador = $('#TabelaVenda').children().next().children();
        while(apontador.children().length > 0){
            quantidade = quantidade + apontador.children().next().next().html();
            apontador = apontador.next();
            if (apontador.children().length > 0)
                quantidade = quantidade+"#";
        }
        
        $('<form action="ServletVendas" method="POST">' + 
            '<input type="hidden" name="tipo" value="salva">' +
            '<input type="hidden" name="cliente" value="'+cli+'">' +
            '<input type="hidden" name="quantidade" value="'+quantidade+'">' +
            '<input type="hidden" name="lotes" value="'+lotes+'">' +
            '<input type="hidden" name="total" value="'+total+'">' +
            '</form>').submit();
        
//        $.ajax({
//            url:  "ServletLote",
//            type: 'post',
//            data: {'tipo':'salva', 'cliente':cli, 'quantidade':quantidade},
//            success: function(data, textStatus, jqXHR) {
//                window.location.href = "http://stackoverflow.com";
//            }
//        });
    });

    $('#TableProdutos').dataTable({
        info: false,
        ordering: false,
        "language": {
            "lengthMenu":     "Mostrar _MENU_ produtos",
            "infoEmpty":      "Não há produtos cadastrados.",
            "search":         "Filtre um produto:",
            "paginate": {
                "first":      "Primeira",
                "last":       "Ultima",
                "next":       "Próxima",
                "previous":   "Anterior"
            }
        }
    });
});