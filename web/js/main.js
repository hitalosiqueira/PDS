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
        var cod = $(this).parent().prev().prev().prev().html();
        var qtde = $(this).prev().val();
        var nome = $(this).parent().prev().prev().html();
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
                    app = "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[4]+"</td><td>"+arr[1]+"</td><td>"+arr[2]+"</td><td>"+arr[3]+"</td></tr>";
                }else{
                    i = 1;
                    while(i < arr.length){
                        if((i+4) >= arr.length )
                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+qtde+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td></tr>";
                        else
                            app = app + "<tr class='gradeA'><td>"+cod+"</td><td>"+nome+"</td><td>"+arr[i+3]+"</td><td>"+arr[i]+"</td><td>"+arr[i+1]+"</td><td>"+arr[i+2]+"</td></tr>";
                        qtde = qtde-arr[i+3];
                        i = i+4;
                    }
                }
                $('#TabelaVenda').children().next().append(app);
            }
        });
        
        $(this).parent().prev().html(max-qtde);       
    });
    
    $("#FinalizaVenda").click(function () {
        var cli = $('#SelCliente').val();
        var lotes = "";
        var quantidade = "";
        
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
        
        $.post( "ServletVendas", { tipo: "salva", cliente: cli, lotes: lotes, quantidade: quantidade } );          
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